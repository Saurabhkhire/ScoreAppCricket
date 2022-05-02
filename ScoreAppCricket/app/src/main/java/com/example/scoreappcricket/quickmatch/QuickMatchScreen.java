package com.example.scoreappcricket.quickmatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.QuickMatchDatabase;


public class QuickMatchScreen extends AppCompatActivity {


    private Button NormalMatchBtn, StrikerOnlyMatchBtn , SinglePlayerMatchBtn;
    // try {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quick_match_screen);

        // initializing all our variables.

        NormalMatchBtn = findViewById(R.id.normalmatch_button);
        StrikerOnlyMatchBtn = findViewById(R.id.strikeronlymatch_button);
        SinglePlayerMatchBtn = findViewById(R.id.singleplayermatch_button);

        // below line is to add on click listener for our add course button.
        NormalMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(QuickMatchScreen.this, NormalMatchScreen.class);

                // start the activity connect to the specified class
                startActivity(intent);


            }
        });

        // below line is to add on click listener for our add course button.
        StrikerOnlyMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(QuickMatchScreen.this, FirstInningsScorecardScreen.class);

                // start the activity connect to the specified class
                startActivity(intent);


            }
        });

        // below line is to add on click listener for our add course button.
        SinglePlayerMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                QuickMatchDatabase quickMatchDatabase = new QuickMatchDatabase(QuickMatchScreen.this);
                quickMatchDatabase.deleteTablesofNormalMatch();

                Intent intent = new Intent(QuickMatchScreen.this, SinglePlayerMatchScreen.class);

                // start the activity connect to the specified class
                startActivity(intent);


            }
        });


    }
}
