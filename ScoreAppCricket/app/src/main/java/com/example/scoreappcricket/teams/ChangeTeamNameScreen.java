package com.example.scoreappcricket.teams;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.TeamDatabase;
import com.example.scoreappcricket.quickmatch.NormalMatchScreen;

import java.util.ArrayList;


public class ChangeTeamNameScreen extends AppCompatActivity {

    private EditText NewTeamNameEdit;
    private Button ChangeTeamNameBtn;
    private Spinner SelectTeamList;
    private TeamDatabase teamDatabase;


    // try {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_team_names_screen);

        // initializing all our variables.
        NewTeamNameEdit = findViewById(R.id.NewTeamNameEdit);
        SelectTeamList = findViewById(R.id.SelectTeamList);
        ChangeTeamNameBtn = findViewById(R.id.change_button);

        teamDatabase = new TeamDatabase(ChangeTeamNameScreen.this);

        String[] TeamsArrayList = teamDatabase.readTeams().toArray(new String[0]);
        ArrayAdapter<String> TeamsArrayAdapterList = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, TeamsArrayList);
        SelectTeamList.setAdapter(TeamsArrayAdapterList);



        // below line is to add on click listener for our add course button.
        ChangeTeamNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.

                String SelectedTeam = SelectTeamList.getSelectedItem().toString();
                String NewTeamName = NewTeamNameEdit.getText().toString();

                // validating if the text fields are empty or not.
                if (NewTeamName.isEmpty()) {
                    Toast.makeText(ChangeTeamNameScreen.this, "Please enter new team name", Toast.LENGTH_SHORT).show();
                }

                else {

                     teamDatabase.updateTeamName(SelectedTeam,NewTeamName);

                        // after adding the data we are displaying a toast message.
                        Toast.makeText(ChangeTeamNameScreen.this, "Team Name has been updated", Toast.LENGTH_SHORT).show();



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
