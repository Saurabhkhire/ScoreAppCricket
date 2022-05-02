package com.example.scoreappcricket.teams;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.TeamDatabase;


import java.util.ArrayList;

public class DeletePlayerfromTeamScreen extends AppCompatActivity {

   private ListView mListview;
    private ArrayList<String> mArrData;
    private DeletePlayerfromTeamAdapter mAdapter;
    private TeamDatabase dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_player_from_team_list);
        Bundle bundle = getIntent().getExtras();
        String teamname = bundle.getString("teamname");
        // initializing all our variables.
        mListview = (ListView) findViewById(R.id.listDeletePlayerfromTeam);

        dbHandler = new TeamDatabase(DeletePlayerfromTeamScreen.this);

        mArrData = dbHandler.readPlayersfromTeam(teamname);

        // Initialize adapter and set adapter to list view
        mAdapter = new DeletePlayerfromTeamAdapter(DeletePlayerfromTeamScreen.this, mArrData, dbHandler, teamname);
        mListview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }
}
