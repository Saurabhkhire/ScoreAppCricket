package com.example.scoreappcricket.quickmatch;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.PlayerDatabase;
import com.example.scoreappcricket.db.TeamDatabase;

import java.util.ArrayList;


public class NormalMatchRunningSelectInitialPlayersScreen extends AppCompatActivity {

    private Button NextBtn;
    private TeamDatabase teamDatabase;
    private Spinner SelectStrikerBatsmanList, SelectNonStrikerBatsmanList, SelectBowlerList;


    // try {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_match_running_select_inital_striker_nonstriker_bowler_screen);
        Bundle bundle = getIntent().getExtras();
        String battingteamname = bundle.getString("battingteamname");
        String bowlingteamname = bundle.getString("bowlingteamname");
        String totalwicketsstr = bundle.getString("totalwickets");
        String totaloversstr = bundle.getString("totalovers");
        String innings = bundle.getString("innings");

        // initializing all our variables.
        SelectStrikerBatsmanList = findViewById(R.id.SelectStrikerBatsmanList);
        SelectNonStrikerBatsmanList = findViewById(R.id.SelectNonStrikerBatsmanList);
        SelectBowlerList = findViewById(R.id.SelectBowlerList);
        NextBtn = findViewById(R.id.next_button);

        teamDatabase = new TeamDatabase(NormalMatchRunningSelectInitialPlayersScreen.this);


        String[] StrikerPlayers = teamDatabase.readPlayersfromTeam(battingteamname).toArray(new String[0]);
        ArrayAdapter<String> StrikerPlayerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, StrikerPlayers);
        SelectStrikerBatsmanList.setAdapter(StrikerPlayerAdapter);
        String[] NonStrikerPlayer = teamDatabase.readPlayersfromTeam(battingteamname).toArray(new String[0]);
        ArrayAdapter<String> NonStrikerPlayerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, NonStrikerPlayer);
        SelectNonStrikerBatsmanList.setAdapter(NonStrikerPlayerAdapter);
        String[] Bowlers = teamDatabase.readPlayersfromTeam(bowlingteamname).toArray(new String[0]);
        ArrayAdapter<String> BowlersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Bowlers);
        SelectBowlerList.setAdapter(BowlersAdapter);

        // creating a new dbhandler class
        // and passing our context to it.


        // below line is to add on click listener for our add course button.
        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.

                String StrikerBatsman = SelectStrikerBatsmanList.getSelectedItem().toString();
                String NonStrikerBatsman = SelectNonStrikerBatsmanList.getSelectedItem().toString();
                String Bowler = SelectBowlerList.getSelectedItem().toString();

                // validating if the text fields are empty or not.
                if (StrikerBatsman.equals(NonStrikerBatsman)) {
                    Toast.makeText(NormalMatchRunningSelectInitialPlayersScreen.this, "Please dont select same batsman", Toast.LENGTH_SHORT).show();
                }

                else {

                    Intent intent = new Intent(NormalMatchRunningSelectInitialPlayersScreen.this, NormalMatchRunningScreen.class);
                    intent.putExtra("battingteamname", battingteamname);
                    intent.putExtra("bowlingteamname", bowlingteamname);
                    intent.putExtra("totalwickets", totalwicketsstr);
                    intent.putExtra("totalovers", totaloversstr);
                    intent.putExtra("strikerplayername", StrikerBatsman);
                    intent.putExtra("nonstrikerplayername", NonStrikerBatsman);
                    intent.putExtra("bowlername", Bowler);
                    intent.putExtra("innings", innings);
                    startActivity(intent);


               }
            }
        });

    }

}
