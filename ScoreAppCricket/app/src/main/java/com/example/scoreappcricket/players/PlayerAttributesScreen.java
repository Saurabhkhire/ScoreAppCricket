package com.example.scoreappcricket.players;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.PlayerDatabase;

import java.util.ArrayList;


public class PlayerAttributesScreen extends AppCompatActivity {

    private TextView PlayerNameList, PlayerCountryList,PlayerRoleList,BattingStyleList,BowlingStyleList,BattingPositionList;
    private Button addNewPlayerBtn;
    private PlayerDatabase dbHandler;



    // try {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_attributes_screen);
        Bundle bundle = getIntent().getExtras();
        String playername = bundle.getString("playername");

        // initializing all our variables.
        PlayerNameList = findViewById(R.id.PlayerNameList);
        PlayerCountryList = findViewById(R.id.PlayerCountryList);
        PlayerRoleList = findViewById(R.id.PlayerRoleList);
        BattingStyleList = findViewById(R.id.BattingStyleList);
        BowlingStyleList = findViewById(R.id.BowlingStyleList);
        BattingPositionList = findViewById(R.id.BattingPositionList);


        dbHandler = new PlayerDatabase(PlayerAttributesScreen.this);
        ArrayList<String>  playerList = dbHandler.getPlayerAttributes(playername);

        PlayerNameList.setText(playerList.get(0));
        PlayerCountryList.setText(playerList.get(1));
        PlayerRoleList.setText(playerList.get(2));
        BattingStyleList.setText(playerList.get(3));
        BowlingStyleList.setText(playerList.get(4));
        BattingPositionList.setText(playerList.get(5));


    }

}
