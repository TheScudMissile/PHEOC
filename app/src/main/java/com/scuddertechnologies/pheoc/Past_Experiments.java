package com.scuddertechnologies.pheoc;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Holds list of all experiments.  Allows user to create a new experiment, edit an existing one,
 * or delete all.
 */
public class Past_Experiments extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    //request code to identify an intent
    private static final int REQUEST_CODE = 1;

    //CursorAdapter to format list item
    private CursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past__experiments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.plus);

        //use adapter to link database columns to listView
        linkToAdapter();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //start new experiment
                Intent intent = new Intent(Past_Experiments.this, Experiment.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //adds options menu to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete_all, menu);
        return true;
    }

    //saves the experiment when toolbar back button is pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                Intent intent = new Intent(Past_Experiments.this, MainMenu.class);
                startActivity(intent);
                break;

            case R.id.delete_all:
                deleteAll();
                break;
        }

        return true;
    }

    private void linkToAdapter() {

        //from columns of DB
        String[] from = {DataBaseHelper.TITLE, DataBaseHelper.P, DataBaseHelper.H,
                DataBaseHelper.E, DataBaseHelper.O, DataBaseHelper.C};

        //to custom listItem using cursorAdapter
        int[] to = {R.id.tvExperiment};
        cursorAdapter = new SimpleCursorAdapter(this, R.layout.past_experiments_item,
                null, from, to, 0);

        //connect adapter to listView
        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(cursorAdapter);

        //when desired experiment is clicked, recreate needed view
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //causes app to react when item in listView is clicked
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {

                //go to Experiment activity specified by this path and pass it the needed uri
                Intent intent = new Intent(Past_Experiments.this, Experiment.class);
                Uri uri = Uri.parse(ExperimentsProvider.CONTENT_URI + "/" + id);
                intent.putExtra(ExperimentsProvider.CONTENT_ITEM_TYPE, uri);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        //get loader to use so action is done on background thread (make resource use more
        //efficient
        getSupportLoaderManager().initLoader(0, null, this);
    }

    //implementing LoaderManager interface so query method
    //(getting data from database) is executed on background thread
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(this, ExperimentsProvider.CONTENT_URI,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        cursorAdapter.swapCursor(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            //use loader so action is done on background thread
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    private void deleteAll() {

        //DialogInterface to confirm that user wants to delete all experiments
        DialogInterface.OnClickListener dialogClickListener =
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int button) {
                        if (button == DialogInterface.BUTTON_POSITIVE) {

                            //delete everything
                            getContentResolver().delete(ExperimentsProvider.CONTENT_URI, null,
                                    null);

                            Toast.makeText(Past_Experiments.this, R.string.all_deleted,
                                    Toast.LENGTH_SHORT).show();

                            //go back to main menu
                            Intent intent = new Intent(Past_Experiments.this, MainMenu.class);
                            startActivity(intent);
                        }
                    }
                };

        //the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.are_you_sure_all))
                .setPositiveButton(getString(android.R.string.yes), dialogClickListener)
                .setNegativeButton(getString(android.R.string.no), dialogClickListener)
                .show();
    }
}