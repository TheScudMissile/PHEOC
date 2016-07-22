package com.scuddertechnologies.pheoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Simple class that handles creation and navigation from main menu
 */
public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    //go to Experiment activity
    public void startNewExperiment(View view) {

        Intent intent = new Intent(this, Experiment.class);
        startActivity(intent);
    }

    //go to Past_Experiments activity
    public void viewPastExperiments(View view) {

        Intent intent = new Intent(this, Past_Experiments.class);
        startActivity(intent);
    }
}
