package com.example.scoreappcricket.players;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.PlayerDatabase;
import com.example.scoreappcricket.db.TeamDatabase;

import java.util.ArrayList;

public class DeletePlayerScreen extends AppCompatActivity{

    private ListView mListview;
    private ArrayList<String> mArrData, mArrDataTeams;
    private DeletePlayerAdapter mAdapter;
    private PlayerDatabase dbHandler;
    private TeamDatabase tmdbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_player_list);

        mListview = (ListView) findViewById(R.id.PlayerList);

        dbHandler = new PlayerDatabase(DeletePlayerScreen.this);
        tmdbHandler = new TeamDatabase(DeletePlayerScreen.this);

        // ArrayAdapter<String> arr;
        // ArrayList<String> array_list = dbHandler.readTeams();
        // Set some data to array list
        //mArrData = new ArrayList<String>(Arrays.asList("111,222,333,444,555,666".split(",")));
        mArrData = dbHandler.readPlayers();
        mArrDataTeams = tmdbHandler.readTeams();

        // Initialize adapter and set adapter to list view
        mAdapter = new DeletePlayerAdapter(DeletePlayerScreen.this, mArrData, mArrDataTeams, dbHandler);
        mListview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}

