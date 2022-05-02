package com.example.scoreappcricket.players;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.scoreappcricket.R;


public class PlayersScreen extends AppCompatActivity {


    private Button CreateNewPlayerBtn, DeleteaPlayerBtn , PlayersListBtn;
    // try {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.players_screen);

        // initializing all our variables.

        CreateNewPlayerBtn = findViewById(R.id.createnewplayer_button);
        DeleteaPlayerBtn = findViewById(R.id.deleteaplayer_button);
        PlayersListBtn = findViewById(R.id.playerslist_button);

        // below line is to add on click listener for our add course button.
        CreateNewPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PlayersScreen.this, CreatePlayerScreen.class);

                // start the activity connect to the specified class
                startActivity(intent);


            }
        });

        // below line is to add on click listener for our add course button.
        DeleteaPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PlayersScreen.this, DeletePlayerScreen.class);

                // start the activity connect to the specified class
                startActivity(intent);


            }
        });

        // below line is to add on click listener for our add course button.
        PlayersListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PlayersScreen.this, PlayersListScreen.class);

                // start the activity connect to the specified class
                startActivity(intent);


            }
        });


    }

}