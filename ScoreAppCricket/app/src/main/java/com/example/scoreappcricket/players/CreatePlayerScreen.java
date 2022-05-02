package com.example.scoreappcricket.players;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.PlayerDatabase;

import java.util.ArrayList;


public class CreatePlayerScreen extends AppCompatActivity {

    private EditText PlayerNameEdt, PlayerCountryEdt;
    private Button addNewPlayerBtn;
    private PlayerDatabase dbHandler;
    private Spinner PlayerRoleEdt, BattingStyleEdt, BowlingStyleEdt , BattingPositionEdt;


    // try {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_players_screen);

        // initializing all our variables.
        PlayerNameEdt = findViewById(R.id.TeamNameEdit);
        PlayerCountryEdt = findViewById(R.id.PlayerCountryEdit);
        PlayerRoleEdt = findViewById(R.id.PlayerRoleEdit);
        BattingStyleEdt = findViewById(R.id.BattingStyleEdit);
        BowlingStyleEdt = findViewById(R.id.BowlingStyleEdit);
        BattingPositionEdt = findViewById(R.id.BattingPositionEdit);
        addNewPlayerBtn = findViewById(R.id.save_button);

        PlayerCountryEdt.setText("India");
        String[] PlayerRoleList = new String[]{"Batsman", "Bowler", "Wicket Keeper","All Rounder"};
        ArrayAdapter<String> PlayerRoleListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, PlayerRoleList);
        PlayerRoleEdt.setAdapter(PlayerRoleListAdapter);
        String[] BattingStyleList = new String[]{"Right Handed Batsman", "Left Handed Batsman"};
        ArrayAdapter<String> BattingStyleListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, BattingStyleList);
        BattingStyleEdt.setAdapter(BattingStyleListAdapter);
        String[] BowlingStyleList = new String[]{"Right Arm Fast", "Left Arm Fast", "Right Arm Medium Fast","Left Arm Medium Fast","Right Arm Medium","Left Arm Medium","Right Arm Off Break","Right Arm Leg Break","Left Arm Spin","Left Arm Chinaman"};
        ArrayAdapter<String> BowlingStyleListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, BowlingStyleList);
        BowlingStyleEdt.setAdapter(BowlingStyleListAdapter);
        String[] BattingPositionList = new String[]{"Opener", "Middle Order", "Lower Order","Tail Ender"};
        ArrayAdapter<String> BattingPositionListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, BattingPositionList);
        BattingPositionEdt.setAdapter(BattingPositionListAdapter);




        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new PlayerDatabase(CreatePlayerScreen.this);

        // below line is to add on click listener for our add course button.
        addNewPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String PlayerName = PlayerNameEdt.getText().toString();
                String PlayerCountry = PlayerCountryEdt.getText().toString();
                String PlayerRole = PlayerRoleEdt.getSelectedItem().toString();
                String BattingStyle = BattingStyleEdt.getSelectedItem().toString();
                String BowlingStyle = BowlingStyleEdt.getSelectedItem().toString();
                String BattingPosition = BattingPositionEdt.getSelectedItem().toString();

                // validating if the text fields are empty or not.
                if (PlayerName.isEmpty() || PlayerCountry.isEmpty() || PlayerRole.isEmpty() || BattingStyle.isEmpty() || BowlingStyle.isEmpty() || BattingPosition.isEmpty()) {
                    Toast.makeText(CreatePlayerScreen.this, "Please enter all the data", Toast.LENGTH_SHORT).show();
                }

                else {


                    // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                 ArrayList<String>  playerList = dbHandler.readPlayers();
                 if(playerList.contains(PlayerName)) {
                     Toast.makeText(CreatePlayerScreen.this, "Player already there", Toast.LENGTH_SHORT).show();

                 } else {

                     dbHandler.addNewPlayer(PlayerName, PlayerRole, PlayerCountry, BattingStyle, BowlingStyle, BattingPosition);

                     // after adding the data we are displaying a toast message.
                     Toast.makeText(CreatePlayerScreen.this, "New Player has been added", Toast.LENGTH_SHORT).show();
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
