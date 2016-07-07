package com.scuddertechnologies.pheoc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.MenuRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Experiment extends AppCompatActivity {

    public final static String GRAPHPOINTS = "com.scuddertechnologies.pheoc.GRAPHPOINTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
        //fab.setImageResource(R.drawable.checkmark);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPastExperiments(view);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //this is what is used to graph shit from data table
    public void viewPastExperiments(View view) {

        Intent intent = new Intent(this, MainMenu.class);
        double[] graphPoints = new double[16];

        EditText rowX1 = (EditText) findViewById(R.id.independent_variable1);
        EditText rowY1 = (EditText) findViewById(R.id.dependent_variable1);
        graphPoints[0] = Double.parseDouble(rowX1.getText().toString());
        graphPoints[1] = Double.parseDouble(rowY1.getText().toString());

        EditText rowX2 = (EditText) findViewById(R.id.independent_variable2);
        EditText rowY2 = (EditText) findViewById(R.id.dependent_variable2);
        graphPoints[2] = Double.parseDouble(rowX2.getText().toString());
        graphPoints[3] = Double.parseDouble(rowY2.getText().toString());

        EditText rowX3 = (EditText) findViewById(R.id.independent_variable3);
        EditText rowY3 = (EditText) findViewById(R.id.dependent_variable3);
        graphPoints[4] = Double.parseDouble(rowX3.getText().toString());
        graphPoints[5] = Double.parseDouble(rowY3.getText().toString());

        EditText rowX4 = (EditText) findViewById(R.id.independent_variable4);
        EditText rowY4 = (EditText) findViewById(R.id.dependent_variable4);
        graphPoints[6] = Double.parseDouble(rowX4.getText().toString());
        graphPoints[7] = Double.parseDouble(rowY4.getText().toString());

        EditText rowX5 = (EditText) findViewById(R.id.independent_variable5);
        EditText rowY5 = (EditText) findViewById(R.id.dependent_variable5);
        graphPoints[8] = Double.parseDouble(rowX5.getText().toString());
        graphPoints[9] = Double.parseDouble(rowY5.getText().toString());

        EditText rowX6 = (EditText) findViewById(R.id.independent_variable6);
        EditText rowY6 = (EditText) findViewById(R.id.dependent_variable6);
        graphPoints[10] = Double.parseDouble(rowX6.getText().toString());
        graphPoints[11] = Double.parseDouble(rowY6.getText().toString());

        EditText rowX7 = (EditText) findViewById(R.id.independent_variable7);
        EditText rowY7 = (EditText) findViewById(R.id.dependent_variable7);
        graphPoints[12] = Double.parseDouble(rowX7.getText().toString());
        graphPoints[13] = Double.parseDouble(rowY7.getText().toString());

        EditText rowX8 = (EditText) findViewById(R.id.independent_variable8);
        EditText rowY8 = (EditText) findViewById(R.id.dependent_variable8);
        graphPoints[14] = Double.parseDouble(rowX8.getText().toString());
        graphPoints[15] = Double.parseDouble(rowY8.getText().toString());


        intent.putExtra(GRAPHPOINTS, graphPoints);
        startActivity(intent);

    }
}
