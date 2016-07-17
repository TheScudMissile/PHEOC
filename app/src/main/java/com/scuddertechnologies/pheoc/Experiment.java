package com.scuddertechnologies.pheoc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class Experiment extends AppCompatActivity {

    public final static String EXPERIMENT = "com.scuddertechnologies.pheoc.EXPERIMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.checkmark);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPastExperiments(view);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void viewPastExperiments(View view) {
        Intent intent = new Intent(this, Past_Experiments.class);
        startActivity(intent);
    }

    private void graphData(View view) {

        double[] graphPoints = new double[16];

        //first row
        EditText rowX1 = (EditText) findViewById(R.id.independent_variable1);
        EditText rowY1 = (EditText) findViewById(R.id.dependent_variable1);
        String rowX1val;
        String rowY1val;

        if(!validInput(rowX1, rowY1)) {
            Toast.makeText(this, "You did not enter all necessary values", Toast.LENGTH_SHORT).show();
            return;
        }

        else {
            rowX1val = rowX1.getText().toString();
            rowY1val = rowY1.getText().toString();
        }

        graphPoints[0] = Double.parseDouble(rowX1val);
        graphPoints[1] = Double.parseDouble(rowY1val);


        //second row
        EditText rowX2 = (EditText) findViewById(R.id.independent_variable2);
        EditText rowY2 = (EditText) findViewById(R.id.dependent_variable2);
        String rowX2val;
        String rowY2val;

        if(!validInput(rowX2, rowY2)) {
            Toast.makeText(this, "You did not enter all necessary values", Toast.LENGTH_SHORT).show();
            return;
        }

        else {
            rowX2val = rowX2.getText().toString();
            rowY2val = rowY2.getText().toString();
        }

        graphPoints[2] = Double.parseDouble(rowX2val);
        graphPoints[3] = Double.parseDouble(rowY2val);


        //third row
        EditText rowX3 = (EditText) findViewById(R.id.independent_variable3);
        EditText rowY3 = (EditText) findViewById(R.id.dependent_variable3);
        String rowX3val;
        String rowY3val;

        if(!validInput(rowX3, rowY3)) {
            Toast.makeText(this, "You did not enter all necessary values", Toast.LENGTH_SHORT).show();
            return;
        }

        else {
            rowX3val = rowX3.getText().toString();
            rowY3val = rowY3.getText().toString();
        }

        graphPoints[4] = Double.parseDouble(rowX3val);
        graphPoints[5] = Double.parseDouble(rowY3val);

        //fourth row
        EditText rowX4 = (EditText) findViewById(R.id.independent_variable4);
        EditText rowY4 = (EditText) findViewById(R.id.dependent_variable4);
        String rowX4val;
        String rowY4val;

        if(!validInput(rowX4, rowY4)) {
            Toast.makeText(this, "You did not enter all necessary values", Toast.LENGTH_SHORT).show();
            return;
        }

        else {
            rowX4val = rowX4.getText().toString();
            rowY4val = rowY4.getText().toString();
        }

        graphPoints[6] = Double.parseDouble(rowX4val);
        graphPoints[7] = Double.parseDouble(rowY4val);


        //fifth row
        EditText rowX5 = (EditText) findViewById(R.id.independent_variable5);
        EditText rowY5 = (EditText) findViewById(R.id.dependent_variable5);
        String rowX5val;
        String rowY5val;

        if(!validInput(rowX5, rowY5)) {
            Toast.makeText(this, "You did not enter all necessary values", Toast.LENGTH_SHORT).show();
            return;
        }

        else {
            rowX5val = rowX5.getText().toString();
            rowY5val = rowY5.getText().toString();
        }

        graphPoints[8] = Double.parseDouble(rowX5val);
        graphPoints[9] = Double.parseDouble(rowY5val);


        //sixth row
        EditText rowX6 = (EditText) findViewById(R.id.independent_variable6);
        EditText rowY6 = (EditText) findViewById(R.id.dependent_variable6);
        String rowX6val;
        String rowY6val;

        if(!validInput(rowX6, rowY6)) {
            Toast.makeText(this, "You did not enter all necessary values", Toast.LENGTH_SHORT).show();
            return;
        }

        else {
            rowX6val = rowX6.getText().toString();
            rowY6val = rowY6.getText().toString();
        }

        graphPoints[10] = Double.parseDouble(rowX6val);
        graphPoints[11] = Double.parseDouble(rowY6val);


        //seventh row
        EditText rowX7 = (EditText) findViewById(R.id.independent_variable7);
        EditText rowY7 = (EditText) findViewById(R.id.dependent_variable7);
        String rowX7val;
        String rowY7val;

        if(!validInput(rowX7, rowY7)) {
            Toast.makeText(this, "You did not enter all necessary values", Toast.LENGTH_SHORT).show();
            return;
        }

        else {
            rowX7val = rowX7.getText().toString();
            rowY7val = rowY7.getText().toString();
        }

        graphPoints[12] = Double.parseDouble(rowX7val);
        graphPoints[13] = Double.parseDouble(rowY7val);


        //eighth row
        EditText rowX8 = (EditText) findViewById(R.id.independent_variable8);
        EditText rowY8 = (EditText) findViewById(R.id.dependent_variable8);
        String rowX8val;
        String rowY8val;

        if(!validInput(rowX8, rowY8)) {
            Toast.makeText(this, "You did not enter all necessary values",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        else {
            rowX8val = rowX8.getText().toString();
            rowY8val = rowY8.getText().toString();
        }

        graphPoints[14] = Double.parseDouble(rowX8val);
        graphPoints[15] = Double.parseDouble(rowY8val);


        //initialize graph
        GraphView graph = (GraphView) findViewById(R.id.graph);

        //make new list of Data points (takes array as single param)
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

        //graph points
        graph.addSeries(series);
    }

    private boolean validInput(EditText x, EditText y) {

        String xVal = x.getText().toString();
        String yVal = y.getText().toString();

        if (xVal.matches("") || yVal.matches("")) {
            return false;
        }

        else {
            return true;
        }
    }

    private void delete() {

        DialogInterface.OnClickListener dialogClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int button) {
                        if (button == DialogInterface.BUTTON_POSITIVE) {

                            //add code to delete experiemnt

                            Toast.makeText(Experiment.this, getString(R.string.deleted),
                                    Toast.LENGTH_SHORT).show();
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