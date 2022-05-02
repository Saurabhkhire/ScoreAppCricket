package com.example.scoreappcricket.teams;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.PlayerDatabase;
import com.example.scoreappcricket.db.TeamDatabase;

import java.util.ArrayList;

public class PlayersListforTeamScreen extends AppCompatActivity{

    private TeamDatabase dbHandler;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_names_list_screen_for_team);
        Bundle bundle = getIntent().getExtras();
        String teamname = bundle.getString("teamname");

        listview =  findViewById(R.id.ListViewforTeam);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new TeamDatabase(PlayersListforTeamScreen.this);


        ArrayAdapter<String> arr;
        ArrayList<String> array_list = dbHandler.readPlayersfromTeam(teamname);
        arr = new ArrayAdapter<String>(this, R.layout.player_names_listview_for_team, array_list);
        listview.setAdapter(arr);
    }
    }
