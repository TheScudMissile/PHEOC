package com.scuddertechnologies.pheoc;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Handles all functions for a particular experiment: saving EditTexts, graphing datasets, and
 * deleting the current experiment
 */
public class Experiment extends AppCompatActivity {

    //data fields for current editTexts
    private EditText title;
    private EditText problem;
    private EditText hypothesis;
    private EditText indepVar;
    private EditText depVar;
    private EditText data1;
    private EditText data2;
    private EditText data3;
    private EditText data4;
    private EditText data5;
    private EditText data6;
    private EditText data7;
    private EditText data8;
    private EditText data9;
    private EditText data10;
    private EditText data11;
    private EditText data12;
    private EditText data13;
    private EditText data14;
    private EditText data15;
    private EditText data16;
    private EditText observations;
    private EditText conclusion;

    //data fields to get saved data and restore it
    private String experimentFilter;
    private String initTitle;
    private String initProblem;
    private String initHypothesis;
    private String[] initDataToArray;
    private String initObservations;
    private String initConclusion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.check_icon);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doneWithExperiment();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //restore saved content
        initializeEditTexts();

        //get ref to intent and uri for complex data type, Experiment
        Intent intent = getIntent();
        Uri uri = intent.getParcelableExtra(ExperimentsProvider.CONTENT_ITEM_TYPE);

        //if the uri gets the path to an existing experiment...
        if (uri != null) {

            //where clause to specify that single desired experiment
            experimentFilter = DataBaseHelper.EXPERIMENT_ID + "=" + uri.getLastPathSegment();

            //get access to experiment from database by assigning to Cursor object
            Cursor cursor = getContentResolver().query(uri, DataBaseHelper.ALL_COLUMNS,
                    experimentFilter, null, null, null);

            //get all needed textviews ready to fill with saved data
            getDBRow(cursor);

            //put that data in EditTexts to resume where activity was left
            setEditTexts();
        }
    }

    //adds options menu to toolbar giving "delete" option
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    //saves the experiment when toolbar back button is pressed, deletes when "delete" is pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                doneWithExperiment();
                break;

            case R.id.delete:
                deleteExperiment();
                break;
        }

        return true;
    }

    //saves the experiment when phone back button is pressed
    @Override
    public void onBackPressed() {

        doneWithExperiment();
    }

    //graphs the user provided dataset to the GraphView (as long as all data is valid)
    public void graphData(View view) {

        //array to hold points
        double[] graphPoints = new double[16];

        //first row
        String rowX1val;
        String rowY1val;

        if (!validInput(data1, data2)) {
            Toast.makeText(this, "You did not enter all necessary values",
                    Toast.LENGTH_SHORT).show();
            return;

        } else {

            rowX1val = data1.getText().toString();
            rowY1val = data2.getText().toString();
        }

        graphPoints[0] = Double.parseDouble(rowX1val);
        graphPoints[1] = Double.parseDouble(rowY1val);


        //second row
        String rowX2val;
        String rowY2val;

        if (!validInput(data3, data4)) {
            Toast.makeText(this, "You did not enter all necessary values", Toast.LENGTH_SHORT).show();
            return;
        } else {
            rowX2val = data3.getText().toString();
            rowY2val = data4.getText().toString();
        }

        graphPoints[2] = Double.parseDouble(rowX2val);
        graphPoints[3] = Double.parseDouble(rowY2val);


        //third row
        String rowX3val;
        String rowY3val;

        if (!validInput(data5, data6)) {
            Toast.makeText(this, "You did not enter all necessary values", Toast.LENGTH_SHORT).show();
            return;
        } else {
            rowX3val = data5.getText().toString();
            rowY3val = data6.getText().toString();
        }

        graphPoints[4] = Double.parseDouble(rowX3val);
        graphPoints[5] = Double.parseDouble(rowY3val);

        //fourth row
        String rowX4val;
        String rowY4val;

        if (!validInput(data7, data8)) {
            Toast.makeText(this, "You did not enter all necessary values", Toast.LENGTH_SHORT).show();
            return;
        } else {
            rowX4val = data7.getText().toString();
            rowY4val = data8.getText().toString();
        }

        graphPoints[6] = Double.parseDouble(rowX4val);
        graphPoints[7] = Double.parseDouble(rowY4val);


        //fifth row
        String rowX5val;
        String rowY5val;

        if (!validInput(data9, data10)) {
            Toast.makeText(this, "You did not enter all necessary values", Toast.LENGTH_SHORT).show();
            return;
        } else {
            rowX5val = data9.getText().toString();
            rowY5val = data10.getText().toString();
        }

        graphPoints[8] = Double.parseDouble(rowX5val);
        graphPoints[9] = Double.parseDouble(rowY5val);


        //sixth row
        String rowX6val;
        String rowY6val;

        if (!validInput(data11, data12)) {
            Toast.makeText(this, "You did not enter all necessary values", Toast.LENGTH_SHORT).show();
            return;
        } else {
            rowX6val = data11.getText().toString();
            rowY6val = data12.getText().toString();
        }

        graphPoints[10] = Double.parseDouble(rowX6val);
        graphPoints[11] = Double.parseDouble(rowY6val);


        //seventh row
        String rowX7val;
        String rowY7val;

        if (!validInput(data13, data14)) {
            Toast.makeText(this, "You did not enter all necessary values", Toast.LENGTH_SHORT).show();
            return;
        } else {
            rowX7val = data13.getText().toString();
            rowY7val = data14.getText().toString();
        }

        graphPoints[12] = Double.parseDouble(rowX7val);
        graphPoints[13] = Double.parseDouble(rowY7val);


        //eighth row
        String rowX8val;
        String rowY8val;

        if (!validInput(data15, data16)) {
            Toast.makeText(this, "You did not enter all necessary values",
                    Toast.LENGTH_SHORT).show();
            return;
        } else {
            rowX8val = data15.getText().toString();
            rowY8val = data16.getText().toString();
        }

        graphPoints[14] = Double.parseDouble(rowX8val);
        graphPoints[15] = Double.parseDouble(rowY8val);


        //initialize graph and add this experiment's title as graph title
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.setTitle(title.getText().toString());

        //make new list of DataPoints from the given array of double values
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(graphPoints[0], graphPoints[1]),
                new DataPoint(graphPoints[2], graphPoints[3]),
                new DataPoint(graphPoints[4], graphPoints[5]),
                new DataPoint(graphPoints[6], graphPoints[7]),
                new DataPoint(graphPoints[8], graphPoints[9]),
                new DataPoint(graphPoints[10], graphPoints[11]),
                new DataPoint(graphPoints[12], graphPoints[13]),
                new DataPoint(graphPoints[14], graphPoints[15])
        });

        //clear GraphView
        graph.removeAllSeries();

        //graph points to GraphView
        graph.addSeries(series);
    }

    //prevent user from leaving EditText blank or inserting a decimal point only
    private boolean validInput(EditText x, EditText y) {

        String xVal = x.getText().toString();
        String yVal = y.getText().toString();

        return !(xVal.equals("") || yVal.equals("") || xVal.equals(".") || yVal.equals("."));
    }

    //assign EditTexts of activity to data fields
    private void initializeEditTexts() {

        title = (EditText) findViewById(R.id.titleET);

        problem = (EditText) findViewById(R.id.problem);

        hypothesis = (EditText) findViewById(R.id.hypothesis);

        indepVar = (EditText) findViewById(R.id.independent_variable);
        depVar = (EditText) findViewById(R.id.dependent_variable);

        data1 = (EditText) findViewById(R.id.independent_variable1);
        data2 = (EditText) findViewById(R.id.dependent_variable1);

        data3 = (EditText) findViewById(R.id.independent_variable2);
        data4 = (EditText) findViewById(R.id.dependent_variable2);

        data5 = (EditText) findViewById(R.id.independent_variable3);
        data6 = (EditText) findViewById(R.id.dependent_variable3);

        data7 = (EditText) findViewById(R.id.independent_variable4);
        data8 = (EditText) findViewById(R.id.dependent_variable4);

        data9 = (EditText) findViewById(R.id.independent_variable5);
        data10 = (EditText) findViewById(R.id.dependent_variable5);

        data11 = (EditText) findViewById(R.id.independent_variable6);
        data12 = (EditText) findViewById(R.id.dependent_variable6);

        data13 = (EditText) findViewById(R.id.independent_variable7);
        data14 = (EditText) findViewById(R.id.dependent_variable7);

        data15 = (EditText) findViewById(R.id.independent_variable8);
        data16 = (EditText) findViewById(R.id.dependent_variable8);

        observations = (EditText) findViewById(R.id.observations);

        conclusion = (EditText) findViewById(R.id.conclusion);
    }

    //restore data (if necessary) by assigning previous user data to "init" (initial) data fields
    private void getDBRow(Cursor c) {

        //move to first row of database
        c.moveToFirst();

        //get text from DB for all columns
        initTitle = c.getString(c.getColumnIndex(DataBaseHelper.TITLE));
        initProblem = c.getString(c.getColumnIndex(DataBaseHelper.P));
        initHypothesis = c.getString(c.getColumnIndex(DataBaseHelper.H));

        //data table section of EditTexts: get the saved string and separate into array
        String initExperimentData = c.getString(c.getColumnIndex(DataBaseHelper.E));
        initDataToArray = initExperimentData.split(",");

        initObservations = c.getString(c.getColumnIndex(DataBaseHelper.O));
        initConclusion = c.getString(c.getColumnIndex(DataBaseHelper.C));
    }

    //
    private void setEditTexts() {

        //title, P, H
        title.setText(initTitle);
        problem.setText(initProblem);
        hypothesis.setText(initHypothesis);

        //E
        indepVar.setText(initDataToArray[0]);
        depVar.setText(initDataToArray[1]);
        data1.setText(initDataToArray[2]);
        data2.setText(initDataToArray[3]);
        data3.setText(initDataToArray[4]);
        data4.setText(initDataToArray[5]);
        data5.setText(initDataToArray[6]);
        data6.setText(initDataToArray[7]);
        data7.setText(initDataToArray[8]);
        data8.setText(initDataToArray[9]);
        data9.setText(initDataToArray[10]);
        data10.setText(initDataToArray[11]);
        data11.setText(initDataToArray[12]);
        data12.setText(initDataToArray[13]);
        data13.setText(initDataToArray[14]);
        data14.setText(initDataToArray[15]);
        data15.setText(initDataToArray[16]);
        data16.setText(initDataToArray[17]);

        //O, C
        observations.setText(initObservations);
        conclusion.setText(initConclusion);
    }

    private void insertExperiment(ContentValues vals, EditText[] dataArray) {

        vals.put(DataBaseHelper.P, problem.getText().toString());
        vals.put(DataBaseHelper.H, hypothesis.getText().toString());
        vals.put(DataBaseHelper.E, getDataString(dataArray));
        vals.put(DataBaseHelper.O, observations.getText().toString());
        vals.put(DataBaseHelper.C, conclusion.getText().toString());

        getContentResolver().insert(ExperimentsProvider.CONTENT_URI, vals);
        Toast.makeText(this, R.string.experiment_saved, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
    }

    private void updateExperiment(EditText[] dataArray) {

        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.TITLE, title.getText().toString());
        values.put(DataBaseHelper.P, problem.getText().toString());
        values.put(DataBaseHelper.H, hypothesis.getText().toString());
        values.put(DataBaseHelper.E, getDataString(dataArray));
        values.put(DataBaseHelper.O, observations.getText().toString());
        values.put(DataBaseHelper.C, conclusion.getText().toString());

        getContentResolver().update(ExperimentsProvider.CONTENT_URI, values, experimentFilter,
                null);
        Toast.makeText(this, R.string.experiment_updated, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
    }

    private void deleteExperiment() {

        //DialogInterface to confirm that user wants to delete this experiment
        DialogInterface.OnClickListener dialogClickListener =
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int button) {
                        if (button == DialogInterface.BUTTON_POSITIVE) {

                            //if this experiment is in the database, delete it
                            if (experimentFilter != null) {
                                getContentResolver().delete(ExperimentsProvider.CONTENT_URI,
                                        experimentFilter, null);
                            }

                            Toast.makeText(Experiment.this, R.string.deleted,
                                    Toast.LENGTH_SHORT).show();

                            //go back to main menu
                            Intent intent = new Intent(Experiment.this, MainMenu.class);
                            startActivity(intent);
                        }
                    }
                };

        //the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.are_you_sure_single))
                .setPositiveButton(getString(android.R.string.yes), dialogClickListener)
                .setNegativeButton(getString(android.R.string.no), dialogClickListener)
                .show();
    }

    //take data from "E" section array and make a single String for simplicity in database column
    private String getDataString(EditText[] dataTable) {

        String result = "";

        for (int i = 0; i < 18; i++) {

            //if a user saves an experiment with a blank in dataTable, make value 0 to avoid NPE
            if (dataTable[i].getText().toString().equals("")) {
                result = result + "0,";
            } else {
                result = result + dataTable[i].getText().toString() + ",";
            }
        }
        return result;
    }

    //get all data input and put in database
    private void doneWithExperiment() {

        EditText[] dataTable = {indepVar, depVar, data1, data2, data3, data4,
                data5, data6, data7, data8, data9, data10, data11, data12, data13, data14,
                data15, data16};

        ContentValues valuesForInsert = new ContentValues();

        //prevent NPE
        if (initTitle == null) {
            initTitle = "";
        }

        //if a title wasn't provided, use default title and add as new row in DB
        if (title.getText().toString().equals("")) {
            valuesForInsert.put(DataBaseHelper.TITLE, getString(R.string.title_default));
            insertExperiment(valuesForInsert, dataTable);

            //update if title match found
        } else if (title.getText().toString().equals(initTitle)) {
            updateExperiment(dataTable);

            //otherwise, add new row with new title
        } else {
            valuesForInsert.put(DataBaseHelper.TITLE, title.getText().toString());
            insertExperiment(valuesForInsert, dataTable);
        }

        //go back to main menu
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}