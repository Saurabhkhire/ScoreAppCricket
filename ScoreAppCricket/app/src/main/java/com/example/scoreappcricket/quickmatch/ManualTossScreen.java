package com.example.scoreappcricket.quickmatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.TeamDatabase;

public class ManualTossScreen extends AppCompatActivity {

    private Button StartMatchBtn;
    private TeamDatabase dbHandler;
    private Spinner TossWinnerList, DecisionList, TotalWicketsList;
    private EditText TotalOversEdit;

    // try {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_toss_screen);
        Bundle bundle = getIntent().getExtras();
        String teamone = bundle.getString("teamone");
        String teamtwo = bundle.getString("teamtwo");

        // initializing all our variables.
        TossWinnerList = findViewById(R.id.TossWinnerTeamList);
        DecisionList = findViewById(R.id.TeamDescisionList);
        StartMatchBtn = findViewById(R.id.startthematch_button);
        TotalWicketsList = findViewById(R.id.NumberofWicketsList);
        TotalOversEdit = findViewById(R.id.NumberofOversEdit);

        String[] TotalWicketsArrayList = new String[]{"1", "2","3","4","5","6","7","8","9","10","11"};
        ArrayAdapter<String> TotalWicketsListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, TotalWicketsArrayList);
        TotalWicketsList.setAdapter(TotalWicketsListAdapter);
        String[] TossWinnerArrayList = new String[]{teamone, teamtwo};
        ArrayAdapter<String> TossWinnerArrayListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, TossWinnerArrayList);
        TossWinnerList.setAdapter(TossWinnerArrayListAdapter);
        String[] DecisionArrayList = new String[]{"Batting", "Bowling"};
        ArrayAdapter<String> DecisionArrayListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, DecisionArrayList);
        DecisionList.setAdapter(DecisionArrayListAdapter);


        StartMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String TossWinner = TossWinnerList.getSelectedItem().toString();
                String Decision = DecisionList.getSelectedItem().toString();
                String TotalWickets = TotalWicketsList.getSelectedItem().toString();
                String TotalOvers = TotalOversEdit.getText().toString();

                if(TotalOvers.isEmpty()) {

                    TotalOvers = "100000";

                }


                    String tossloser = null;
                    if (!teamone.equals(TossWinner)) {

                        tossloser = teamone;

                    } else if (!teamtwo.equals(TossWinner)) {

                        tossloser = teamtwo;

                    }

                    Intent intent = new Intent(ManualTossScreen.this, NormalMatchRunningSelectInitialPlayersScreen.class);

                    if (Decision.equals("Batting")) {

                        intent.putExtra("battingteamname", TossWinner);
                        intent.putExtra("bowlingteamname", tossloser);

                    } else if (Decision.equals("Bowling")) {

                        intent.putExtra("battingteamname", tossloser);
                        intent.putExtra("bowlingteamname", TossWinner);

                    }

                    intent.putExtra("totalwickets", TotalWickets);
                    intent.putExtra("totalovers", TotalOvers);
                    intent.putExtra("innings", "1");

                    startActivity(intent);



            }
        });
    }
}
