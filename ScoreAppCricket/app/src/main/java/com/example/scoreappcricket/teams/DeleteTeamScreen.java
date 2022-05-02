package com.example.scoreappcricket.teams;


import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.TeamDatabase;

import java.util.ArrayList;

public class DeleteTeamScreen extends AppCompatActivity{

    private ListView mListview;
    private ArrayList<String> mArrData;
    private DeleteTeamAdapter mAdapter;
    private TeamDatabase dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_team_list);

        mListview = (ListView) findViewById(R.id.TeamList);

        dbHandler = new TeamDatabase(DeleteTeamScreen.this);

        mArrData = dbHandler.readTeams();

        // Initialize adapter and set adapter to list view
        mAdapter = new DeleteTeamAdapter(DeleteTeamScreen.this, mArrData, dbHandler);
        mListview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}

