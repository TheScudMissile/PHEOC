package com.scuddertechnologies.pheoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void startNewExperiment(View view) {

        Intent intent = new Intent(this, Experiment.class);
        startActivity(intent);
    }

    public void viewPastExperiments(View view) {

        Toast.makeText(MainMenu.this, "Frig off Lahey this doesn't work yet!",
                Toast.LENGTH_SHORT).show();

    }
}
