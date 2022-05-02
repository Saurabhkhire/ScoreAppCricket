package com.example.scoreappcricket.quickmatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.TeamDatabase;
import com.example.scoreappcricket.teams.CreateTeamScreen;

import java.util.ArrayList;

public class NormalMatchScreen extends AppCompatActivity {

    private Button AutomaticTossBtn, ManualTossBtn;
    private TeamDatabase dbHandler;
    private Spinner TeamOneList, TeamTwoList;

    public NormalMatchScreen() {
    }

    // try {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_match_screen);

        // initializing all our variables.
        TeamOneList = findViewById(R.id.TeamOneList);
        TeamTwoList = findViewById(R.id.TeamTwoList);
        AutomaticTossBtn = findViewById(R.id.automatictoss_button);
        ManualTossBtn = findViewById(R.id.manualtoss_button);

        dbHandler = new TeamDatabase(NormalMatchScreen.this);
        dbHandler.readTeams();

        String[] TeamOneArrayList = dbHandler.readTeams().toArray(new String[0]);
        ArrayAdapter<String> TeamOneArrayListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, TeamOneArrayList);
        TeamOneList.setAdapter(TeamOneArrayListAdapter);
        String[] TeamTwoArrayList = dbHandler.readTeams().toArray(new String[0]);
        ArrayAdapter<String> TeamTwoArrayListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, TeamTwoArrayList);
        TeamTwoList.setAdapter(TeamTwoArrayListAdapter);

//        String TeamOneName = TeamOneList.getSelectedItem().toString();
//        String TeamTwoName = TeamTwoList.getSelectedItem().toString();
//
//        TeamOneList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//
//                    ArrayList<String> TeamTwoNewArrayList = dbHandler.readTeams();
//                    TeamTwoNewArrayList.remove(TeamOneName);
//                    ArrayAdapter<String> TeamOneArrayListAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, TeamTwoNewArrayList);
//                    TeamTwoList.setAdapter(TeamOneArrayListAdapter);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                return;
//            }
//
//        });





        // below line is to add on click listener for our add course button.
        AutomaticTossBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String TeamOne = TeamOneList.getSelectedItem().toString();
                String TeamTwo = TeamTwoList.getSelectedItem().toString();

                if(TeamOne.equals(TeamTwo)) {

                    Toast.makeText(NormalMatchScreen.this, "Please dont select same teams", Toast.LENGTH_SHORT).show();


                } else {

                    Intent intent = new Intent(NormalMatchScreen.this, AutomaticTossScreen.class);
                    intent.putExtra("teamone", TeamOne);
                    intent.putExtra("teamtwo", TeamTwo);
                    // start the activity connect to the specified class
                    startActivity(intent);

                }

            }
        });

        ManualTossBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String TeamOne = TeamOneList.getSelectedItem().toString();
                String TeamTwo = TeamTwoList.getSelectedItem().toString();

                if(TeamOne.equals(TeamTwo)) {

                    Toast.makeText(NormalMatchScreen.this, "Please dont select same teams", Toast.LENGTH_SHORT).show();

                } else {

                    Intent intent = new Intent(NormalMatchScreen.this, ManualTossScreen.class);
                    intent.putExtra("teamone", TeamOne);
                    intent.putExtra("teamtwo", TeamTwo);
                    // start the activity connect to the specified class
                    startActivity(intent);
                }

            }
        });
    }
}
