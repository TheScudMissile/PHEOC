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

public class Past_Experiments extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>{

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
        insertSample();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newExperiment(view);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void linkToAdapter() {
        //from columns
        String[] from = {DBOpenHelper.TITLE, DBOpenHelper.P, DBOpenHelper.H,
                DBOpenHelper.E, DBOpenHelper.O, DBOpenHelper.C};
        //to view
        int[] to = {android.R.id.text1};
        cursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, null, from, to, 0);

        //connect adapter to listView
        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(cursorAdapter);

        getSupportLoaderManager().initLoader(0, null, this);
    }

    private void insertExperiment(String title) {
        //add items to ListView with selected name
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.TITLE, title);

        //display the item
        getContentResolver().insert(ExperimentsProvider.CONTENT_URI, values);
    }

    private void newExperiment(View view) {

        Intent intent = new Intent(this, Experiment.class);
        startActivity(intent);
    }

    private void insertSample() {

        insertExperiment("title1");
        insertExperiment("title2 with a bunch of extra \n shit");
        insertExperiment("title3 that is going to be super long so I can see that it looks " +
                "bad");
        getSupportLoaderManager().restartLoader(0, null, this);
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
}