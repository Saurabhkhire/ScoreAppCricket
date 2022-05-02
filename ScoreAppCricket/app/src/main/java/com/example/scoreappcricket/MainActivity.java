package com.example.scoreappcricket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.scoreappcricket.db.PlayerDatabase;
import com.example.scoreappcricket.players.CreatePlayerScreen;
import com.example.scoreappcricket.players.PlayersScreen;
import com.example.scoreappcricket.quickmatch.QuickMatchScreen;
import com.example.scoreappcricket.teams.TeamsScreen;


public class MainActivity extends AppCompatActivity {


    private Button PlayersBtn, TeamsBtn, QuickMatchBtn;
    private PlayerDatabase db;
   // try {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new PlayerDatabase(MainActivity.this);
        db.createTables();

        PlayersBtn = findViewById(R.id.players_button);
        TeamsBtn = findViewById(R.id.teams_button);
        QuickMatchBtn = findViewById(R.id.quickmatch_button);

        // below line is to add on click listener for our add course button.
        PlayersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this, PlayersScreen.class);

                    // start the activity connect to the specified class
                    startActivity(intent);


            }
        });

        TeamsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, TeamsScreen.class);

                // start the activity connect to the specified class
                startActivity(intent);


            }
        });

        QuickMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, QuickMatchScreen.class);

                // start the activity connect to the specified class
                startActivity(intent);


            }
        });

    }

}
