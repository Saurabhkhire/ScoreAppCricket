package com.example.scoreappcricket.quickmatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.TeamDatabase;
import com.example.scoreappcricket.teams.CreateTeamScreen;

import java.util.Random;

public class AutomaticTossScreen extends AppCompatActivity {

    public static final Random RANDOM = new Random();
    private Button StartMatchBtn, FlipBtn;
    private TeamDatabase dbHandler;
    private Spinner DecisionList, TotalWicketsList;
    private TextView TossWinnerList;
    private EditText TotalOversEdit;
    //private ImageView coin;

    // try {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.automatic_toss_screen);
        Bundle bundle = getIntent().getExtras();
        String teamone = bundle.getString("teamone");
        String teamtwo = bundle.getString("teamtwo");

        // initializing all our variables.
        TossWinnerList = findViewById(R.id.TossWinnerTeamList);
        DecisionList = findViewById(R.id.TeamDescisionList);
        StartMatchBtn = findViewById(R.id.startthematch_button);
        FlipBtn = findViewById(R.id.refreshtoss_button);
        TotalWicketsList = findViewById(R.id.NumberofWicketsList);
        TotalOversEdit = findViewById(R.id.NumberofOversEdit);

        String[] TotalWicketsArrayList = new String[]{"1", "2","3","4","5","6","7","8","9","10","11"};
        ArrayAdapter<String> TotalWicketsListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, TotalWicketsArrayList);
        TotalWicketsList.setAdapter(TotalWicketsListAdapter);
        String[] DecisionArrayList = new String[]{"Batting", "Bowling"};
        ArrayAdapter<String> DecisionArrayListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, DecisionArrayList);
        DecisionList.setAdapter(DecisionArrayListAdapter);

        FlipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //flipCoin();
                TossWinnerList.setText("");

                String[] arr={teamone, teamtwo};
                Random r=new Random();
                int randomNumber=r.nextInt(arr.length);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                }
                TossWinnerList.setText(arr[randomNumber]);

            }
        });


        StartMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String TossWinner = TossWinnerList.getText().toString();
                String Decision = DecisionList.getSelectedItem().toString();
                String TotalWickets = TotalWicketsList.getSelectedItem().toString();
                String TotalOvers = TotalOversEdit.getText().toString();

                if (TossWinner.isEmpty()) {

                    if(TotalOvers.isEmpty()) {

                        TotalOvers = "100000";

                    }

                    Toast.makeText(AutomaticTossScreen.this, "No Team Selected", Toast.LENGTH_SHORT).show();

                } else {

                    if(TotalOvers.isEmpty()) {

                        TotalOvers = "100000";

                    }


                String tossloser = null;
                if (!teamone.equals(TossWinner)) {

                    tossloser = teamone;

                } else if (!teamtwo.equals(TossWinner)) {

                    tossloser = teamtwo;

                }

                Intent intent = new Intent(AutomaticTossScreen.this, NormalMatchRunningSelectInitialPlayersScreen.class);

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
            }
        });
    }

//    private void flipCoin() {
//        Animation fadeOut = new AlphaAnimation(1, 0);
//        fadeOut.setInterpolator(new AccelerateInterpolator());
//        fadeOut.setDuration(1000);
//        fadeOut.setFillAfter(true);
//        fadeOut.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                coin.setImageResource(RANDOM.nextFloat() > 0.5f ? R.drawable.tails_background : R.drawable.heads_background);
//
//                Animation fadeIn = new AlphaAnimation(0, 1);
//                fadeIn.setInterpolator(new DecelerateInterpolator());
//                fadeIn.setDuration(3000);
//                fadeIn.setFillAfter(true);
//
//                coin.startAnimation(fadeIn);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//
//        coin.startAnimation(fadeOut);
//    }
}
