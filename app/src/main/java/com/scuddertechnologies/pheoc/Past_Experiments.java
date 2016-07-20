package com.scuddertechnologies.pheoc;

import android.content.ContentValues;
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
import android.widget.TextView;
import android.widget.Toast;

public class Past_Experiments extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int REQUEST_CODE = 1;
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
        //from columns
        String[] from = {DBOpenHelper.TITLE, DBOpenHelper.P, DBOpenHelper.H,
                DBOpenHelper.E, DBOpenHelper.O, DBOpenHelper.C};

        //to custom listItem
        int[] to = {R.id.tvExperiment};
        cursorAdapter = new SimpleCursorAdapter(this, R.layout.past_experiments_item,
                null, from, to, 0);

        //connect adapter to listView
        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(cursorAdapter);

        //when desired experiment is clicked, it recreates the needed view
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //causes app to react when item in listView is clicked
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Intent intent = new Intent(Past_Experiments.this, Experiment.class);
                Uri uri = Uri.parse(ExperimentsProvider.CONTENT_URI + "/" + id);
                intent.putExtra(ExperimentsProvider.CONTENT_ITEM_TYPE, uri);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        //get loader to use so action is done on background thread
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

        if (requestCode == REQUEST_CODE && requestCode == RESULT_OK) {

            //use loader so action is done on background thread
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    private void deleteAll() {

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

                            Intent intent = new Intent(Past_Experiments.this, MainMenu.class);
                            startActivity(intent);
                        }
                    }
                };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.are_you_sure))
                .setPositiveButton(getString(android.R.string.yes), dialogClickListener)
                .setNegativeButton(getString(android.R.string.no), dialogClickListener)
                .show();
    }
}