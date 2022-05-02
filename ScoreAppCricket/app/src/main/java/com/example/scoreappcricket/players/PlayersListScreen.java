package com.example.scoreappcricket.players;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.PlayerDatabase;
import com.example.scoreappcricket.db.TeamDatabase;
import com.example.scoreappcricket.teams.TeamsListAdapter;

import java.util.ArrayList;

public class PlayersListScreen extends AppCompatActivity {

    private ListView mListview;
    private ArrayList<String> mArrData;
    private PlayersListAdapter mAdapter;
    private PlayerDatabase dbHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_names_listview);

        mListview = (ListView) findViewById(R.id.PlayerNamesList);

        dbHandler = new PlayerDatabase(PlayersListScreen.this);


       // ArrayAdapter<String> arr;
       // ArrayList<String> array_list = dbHandler.readTeams();
        // Set some data to array list
      //mArrData = new ArrayList<String>(Arrays.asList("111,222,333,444,555,666".split(",")));
        mArrData = dbHandler.readPlayers();

        // Initialize adapter and set adapter to list view
        mAdapter = new PlayersListAdapter(PlayersListScreen.this, mArrData);
        mListview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }
}