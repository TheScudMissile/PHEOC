package com.scuddertechnologies.pheoc;

import android.content.ContentValues;
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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class Past_Experiments extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>{

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

        //get loader to use so action is done on background thread
        getSupportLoaderManager().initLoader(0, null, this);
    }

    private void insertExperiment(String title, String problem, String hypothesis,
                                  String data, String observations, String conclusion) {

        //add values to database columns
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.TITLE, title);
        values.put(DBOpenHelper.P, problem);
        values.put(DBOpenHelper.H, hypothesis);
        values.put(DBOpenHelper.E, data);
        values.put(DBOpenHelper.O, observations);
        values.put(DBOpenHelper.C, conclusion);

        //insert into database
        getContentResolver().insert(ExperimentsProvider.CONTENT_URI, values);
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
}