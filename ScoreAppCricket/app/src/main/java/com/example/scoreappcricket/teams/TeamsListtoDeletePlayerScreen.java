package com.example.scoreappcricket.teams;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.TeamDatabase;

import java.util.ArrayList;

public class TeamsListtoDeletePlayerScreen extends AppCompatActivity {

    private ListView mListview;
    private ArrayList<String> mArrData;
    private TeamsListtoDeletePlayerAdapter mAdapter;
    private TeamDatabase dbHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teams_list_for_deleting_players);

        mListview = (ListView) findViewById(R.id.listTeams);

        dbHandler = new TeamDatabase(TeamsListtoDeletePlayerScreen.this);


       // ArrayAdapter<String> arr;
       // ArrayList<String> array_list = dbHandler.readTeams();
        // Set some data to array list
      //mArrData = new ArrayList<String>(Arrays.asList("111,222,333,444,555,666".split(",")));
        mArrData = dbHandler.readTeams();

        // Initialize adapter and set adapter to list view
        mAdapter = new TeamsListtoDeletePlayerAdapter(TeamsListtoDeletePlayerScreen.this, mArrData);
        mListview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }
}