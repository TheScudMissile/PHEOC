package com.scuddertechnologies.pheoc;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class Past_Experiments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past__experiments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.plus);

        insertExperiment("Problem", "Hypothesis", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0",
                "Observations", "Conclusion");

        //query results in a returned cursor object
        Cursor cursor = getContentResolver().query
                (ExperimentsProvider.Content_Uri, DBOpenHelper.ALL_COLUMNS,
                        null, null, null, null);


        //use adapter to link database columns to listView

        //from columns
        String[] from = {DBOpenHelper.P, DBOpenHelper.H, DBOpenHelper.E,
                DBOpenHelper.O, DBOpenHelper.C};
        //to view
        int[] to = {android.R.id.text1};
        CursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, cursor, from, to, 0);

        //connect adapter to listView
        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(cursorAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //add stuff
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void insertExperiment(String problem, String hypothesis, String experiment,
                                  String observations, String conclusion) {
        //add row to database
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.P, problem);
        values.put(DBOpenHelper.H, hypothesis);
        values.put(DBOpenHelper.E, experiment);
        values.put(DBOpenHelper.O, observations);
        values.put(DBOpenHelper.C, conclusion);
        Uri experimentUri = getContentResolver().insert(ExperimentsProvider.Content_Uri, values);
    }

    private void newExperiment(View view) {
        Intent intent = new Intent(this, Experiment.class);
        startActivity(intent);
    }

}
