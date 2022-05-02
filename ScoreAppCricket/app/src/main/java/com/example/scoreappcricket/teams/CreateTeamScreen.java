package com.example.scoreappcricket.teams;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.PlayerDatabase;
import com.example.scoreappcricket.db.TeamDatabase;
import com.example.scoreappcricket.players.CreatePlayerScreen;

import java.util.ArrayList;


public class CreateTeamScreen extends AppCompatActivity {

    private EditText TeamNameEdt;
    private Button addNewTeamBtn;
    private TeamDatabase dbHandler;


    // try {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_teams_screen);

        // initializing all our variables.
        TeamNameEdt = findViewById(R.id.TeamNameEdit);
        addNewTeamBtn = findViewById(R.id.save_button);


        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new TeamDatabase(CreateTeamScreen.this);

        // below line is to add on click listener for our add course button.
        addNewTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String TeamName = TeamNameEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (TeamName.isEmpty()) {
                    Toast.makeText(CreateTeamScreen.this, "Please enter team name", Toast.LENGTH_SHORT).show();
                }

                else {

                    ArrayList<String> playerList = dbHandler.readTeams();
                    if(playerList.contains(TeamName)) {
                        Toast.makeText(CreateTeamScreen.this, "Team already there", Toast.LENGTH_SHORT).show();

                    } else {
                        dbHandler.addNewTeam(TeamName);

                        // after adding the data we are displaying a toast message.
                        Toast.makeText(CreateTeamScreen.this, "New Team has been added", Toast.LENGTH_SHORT).show();
//                PlayerNameEdt.setText("");
//                PlayerCountryEdt.setText("");

                    }
               }
            }
        });

//            showPlayersBtn.setOnClickListener(new View.OnClickListener() {
//
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(MainActivity.this, PlayersList.class);
//
//                    // start the activity connect to the specified class
//                    startActivity(intent);
//
//
//
//                }
//            });
    }

}
