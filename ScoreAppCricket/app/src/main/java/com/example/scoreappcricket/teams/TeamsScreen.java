package com.example.scoreappcricket.teams;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;


public class TeamsScreen extends AppCompatActivity {


    private Button CreateNewTeamBtn, DeleteaTeamBtn , AddNewPlayerTeamsBtn, DeletePlayersBtn, ChangeTeamNameBtn, TeamsListBtn;
    // try {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teams_screen);

        // initializing all our variables.

        CreateNewTeamBtn = findViewById(R.id.createnewteam_button);
        DeleteaTeamBtn = findViewById(R.id.deleteateam_button);
        AddNewPlayerTeamsBtn = findViewById(R.id.addnewplayerinteams_button);
        DeletePlayersBtn = findViewById(R.id.deleteplayerfromteams_button);
        ChangeTeamNameBtn= findViewById(R.id.changeteamname_button);
        TeamsListBtn = findViewById(R.id.teamslist_button);

        // below line is to add on click listener for our add course button.
        CreateNewTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TeamsScreen.this, CreateTeamScreen.class);

                // start the activity connect to the specified class
                startActivity(intent);


            }
        });

        // below line is to add on click listener for our add course button.
        DeleteaTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TeamsScreen.this, DeleteTeamScreen.class);

                // start the activity connect to the specified class
                startActivity(intent);


            }
        });

        // below line is to add on click listener for our add course button.
        AddNewPlayerTeamsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TeamsScreen.this, TeamsListtoAddNewPlayerScreen.class);

                // start the activity connect to the specified class
                startActivity(intent);


            }
        });

        DeletePlayersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TeamsScreen.this, TeamsListtoDeletePlayerScreen.class);

                // start the activity connect to the specified class
                startActivity(intent);


            }
        });

        ChangeTeamNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TeamsScreen.this, ChangeTeamNameScreen.class);

                // start the activity connect to the specified class
                startActivity(intent);


            }
        });

        TeamsListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TeamsScreen.this, TeamsListScreen.class);

                // start the activity connect to the specified class
                startActivity(intent);


            }
        });


    }

}