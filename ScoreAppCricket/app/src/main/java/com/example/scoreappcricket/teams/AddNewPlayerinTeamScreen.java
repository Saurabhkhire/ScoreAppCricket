package com.example.scoreappcricket.teams;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.PlayerDatabase;
import com.example.scoreappcricket.db.TeamDatabase;


import java.util.ArrayList;

public class AddNewPlayerinTeamScreen extends AppCompatActivity {

   private ListView mListview;
    private ArrayList<String> mArrDataofTeam,mArrData;
    private AddNewPlayerinTeamAdapter mAdapter;
    private PlayerDatabase dbHandler;
    private TeamDatabase tmdbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_player_in_team_list);
        Bundle bundle = getIntent().getExtras();
        String teamname = bundle.getString("teamname");
        // initializing all our variables.
        mListview = (ListView) findViewById(R.id.listAddPlayerinTeam);

        dbHandler = new PlayerDatabase(AddNewPlayerinTeamScreen.this);
        tmdbHandler= new TeamDatabase(AddNewPlayerinTeamScreen.this);

        mArrData = dbHandler.readPlayers();
        mArrDataofTeam = tmdbHandler.readPlayersfromTeam(teamname);
        mArrData.removeAll(mArrDataofTeam);

        // Initialize adapter and set adapter to list view
        mAdapter = new AddNewPlayerinTeamAdapter(AddNewPlayerinTeamScreen.this, mArrData, teamname, tmdbHandler);
        mListview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }
}
