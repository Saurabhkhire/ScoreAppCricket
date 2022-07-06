package com.example.scoreappcricket.quickmatch;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.PlayerDatabase;
import com.example.scoreappcricket.db.QuickMatchDatabase;
import com.example.scoreappcricket.db.TeamDatabase;
import com.example.scoreappcricket.model.NormalMatchRunningScreenModel;


public class NormalMatchRunningScreen extends AppCompatActivity {

    private TextView StrikerRunsEdit, NonStrikerRunsEdit, BowlerOversEdit,TotalRunsEdit,StrikerBallsEdit,NonStrikerBallsEdit,
            BowlerRunsEdit,StrikerFoursEdit,NonStrikerFoursEdit,StrikerSixesEdit,NonStrikerSixesEdit,BowlerWicketsEdit,TotalWicketsEdit,
            TotalOversEdit,TotalSelctedOversEdit,BowlerMaidensEdit,StrikerStrikeRateEdit,NonStrikerStrikeRateEdit,BowlerEconomyEdit,StrikerPlayerEdit,NonStrikerPlayerEdit,BowlerNameEdit;
    private Button ZeroRunsBtn, OneRunsBtn, TwoRunsBtn, ThreeRunsBtn, FourRunsBtn, FiveRunsBtn, SixRunsBtn, StrikerPlayerNextBtn, NonStrikerPlayerNextBtn, BowlerNextBtn, ScorecardBtn ;
    private PlayerDatabase playerDatabase;
    private TeamDatabase teamDatabase;
    private QuickMatchDatabase quickMatchDatabase;
    private Spinner StrikerPlayerList,NonStrikerPlayerList,BowlerNamelist;
    private RelativeLayout relativeLayout;
    private PopupWindow SelectStrikerBatsmanPopupWindow;
    private CheckBox checkBoxByes, checkBoxWide, checkBoxNoBall, checkBoxWicket;


    // try {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_match_running_screen);
        Bundle bundle = getIntent().getExtras();
        String battingteamname = bundle.getString("battingteamname");
        String bowlingteamname = bundle.getString("bowlingteamname");
        String totalwicketsstr = bundle.getString("totalwickets");
        String totaloversstr = bundle.getString("totalovers");
        String strikerplayername = bundle.getString("strikerplayername");
        String nonstrikerplayername = bundle.getString("nonstrikerplayername");
        String bowlername = bundle.getString("bowlername");
        String innings = bundle.getString("innings");
        //Integer innings = Integer.parseInt(inningsstr);
        Integer totalwickets = Integer.parseInt(totalwicketsstr);
        Double totalovers = Double.parseDouble(totaloversstr);


        relativeLayout = (RelativeLayout) findViewById(R.id.MatchRunning);

        quickMatchDatabase = new QuickMatchDatabase(NormalMatchRunningScreen.this);
        quickMatchDatabase.createTablesforNormalMatch();


        // initializing all our variables.
        StrikerRunsEdit = findViewById(R.id.StrikerRunsEdit);
        NonStrikerRunsEdit = findViewById(R.id.NonStrikerRunsEdit);
        BowlerOversEdit = findViewById(R.id.BowlerOversEdit);
        TotalRunsEdit = findViewById(R.id.TotalRunsEdit);
        StrikerBallsEdit = findViewById(R.id.StrikerBallsEdit);
        NonStrikerBallsEdit = findViewById(R.id.NonStrikerBallsEdit);
        BowlerRunsEdit = findViewById(R.id.BowlerRunsEdit);
        StrikerFoursEdit = findViewById(R.id.StrikerFoursEdit);
        NonStrikerFoursEdit = findViewById(R.id.NonStrikerFoursEdit);
        StrikerSixesEdit = findViewById(R.id.StrikerSixesEdit);
        NonStrikerSixesEdit = findViewById(R.id.NonStrikerSixesEdit);
        BowlerWicketsEdit = findViewById(R.id.BowlerWicketsEdit);
        TotalWicketsEdit = findViewById(R.id.TotalWicketsEdit);
        TotalOversEdit = findViewById(R.id.TotalOversEdit);
        TotalSelctedOversEdit = findViewById(R.id.TotalSelectedOversTxt);
        BowlerMaidensEdit = findViewById(R.id.BowlerMaidensEdit);
        StrikerStrikeRateEdit = findViewById(R.id.StrikerStrikeRateEdit);
        NonStrikerStrikeRateEdit = findViewById(R.id.NonStrikerStrikeRateEdit);
        BowlerEconomyEdit = findViewById(R.id.BowlerEconomyEdit);
        StrikerPlayerEdit = findViewById(R.id.StrikerPlayerList);
        NonStrikerPlayerEdit= findViewById(R.id.NonStrikerPlayerList);
        BowlerNameEdit = findViewById(R.id.BowlerNameList);
        ZeroRunsBtn = findViewById(R.id.zeroruns_button);
        OneRunsBtn = findViewById(R.id.oneruns_button);
        TwoRunsBtn = findViewById(R.id.tworuns_button);
        ThreeRunsBtn = findViewById(R.id.threeruns_button);
        FourRunsBtn = findViewById(R.id.fourruns_button);
        FiveRunsBtn = findViewById(R.id.fiveruns_button);
        SixRunsBtn = findViewById(R.id.sixruns_button);
        checkBoxByes = (CheckBox) findViewById(R.id.radioByes);
        checkBoxWide = (CheckBox) findViewById(R.id.radioWide);
        checkBoxNoBall = (CheckBox) findViewById(R.id.radioNoBall);
        checkBoxWicket = (CheckBox) findViewById(R.id.radioWicket);
        ScorecardBtn = findViewById(R.id.scorecard_button);

        StrikerRunsEdit.setText("0");
        NonStrikerRunsEdit.setText("0");
        StrikerBallsEdit.setText("0");
        NonStrikerBallsEdit.setText("0");
        BowlerOversEdit.setText("0.0");
        BowlerRunsEdit.setText("0");
        BowlerWicketsEdit.setText("0");
        TotalRunsEdit.setText("0");
        TotalWicketsEdit.setText("0");
        TotalOversEdit.setText("0.0");
        StrikerFoursEdit.setText("0");
        NonStrikerFoursEdit.setText("0");
        StrikerSixesEdit.setText("0");
        NonStrikerSixesEdit.setText("0");
        BowlerMaidensEdit.setText("0");
        StrikerStrikeRateEdit.setText("0");
        NonStrikerStrikeRateEdit.setText("0");
        BowlerEconomyEdit.setText("0");
        TotalSelctedOversEdit.setText(totalovers.toString());
        TotalWicketsEdit.setText("0");
        StrikerPlayerEdit.setText(strikerplayername);
        NonStrikerPlayerEdit.setText(nonstrikerplayername);
        BowlerNameEdit.setText(bowlername);



        if (!checkBoxWide.isChecked() && checkBoxNoBall.isChecked()) {
            checkBoxWide.setChecked(false);
        }

        if (checkBoxWide.isChecked() && !checkBoxNoBall.isChecked()) {
            checkBoxNoBall.setChecked(false);
        }







//        String[] NonStrikerPlayer = teamDatabase.readPlayersfromTeam(battingteamname).toArray(new String[0]);
//        ArrayAdapter<String> NonStrikerPlayerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, NonStrikerPlayer);
//        NonStrikerPlayerList.setAdapter(NonStrikerPlayerAdapter);
//        String[] Bowlers = teamDatabase.readPlayersfromTeam(bowlingteamname).toArray(new String[0]);
//        ArrayAdapter<String> BowlersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Bowlers);
//        BowlerNameList.setAdapter(BowlersAdapter);




        // creating a new dbhandler class
        // and passing our context to it.


        // below line is to add on click listener for our add course button.
        ZeroRunsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                NormalMatchRunningSelectStrikerPopup popUpClass = new NormalMatchRunningSelectStrikerPopup();
//                popUpClass.showPopupWindow(v,battingteamname);

                // below line is to get data from all edit text fields.
                String StrikerRunsStr = StrikerRunsEdit.getText().toString();
                String NonStrikerRunsStr = NonStrikerRunsEdit.getText().toString();
                String StrikerBallsStr = StrikerBallsEdit.getText().toString();
                String NonStrikerBallsStr = NonStrikerBallsEdit.getText().toString();
                String BowlerOversStr = BowlerOversEdit.getText().toString();
                String BowlerRunsStr = BowlerRunsEdit.getText().toString();
                String BowlerWicketsStr = BowlerWicketsEdit.getText().toString();
                String TotalRunsStr = TotalRunsEdit.getText().toString();
                String TotalWicketsStr =  TotalWicketsEdit.getText().toString();
                String TotalOversStr = TotalOversEdit.getText().toString();
                String StrikerFoursStr = StrikerFoursEdit.getText().toString();
                String NonStrikerFoursStr = NonStrikerFoursEdit.getText().toString();
                String StrikerSixesStr = StrikerSixesEdit.getText().toString();
                String NonStrikerSixesStr = NonStrikerSixesEdit.getText().toString();
                String StrikerPlayer = StrikerPlayerEdit.getText().toString();
                String NonStrikerPlayer = NonStrikerPlayerEdit.getText().toString();
                String BowlerName = BowlerNameEdit.getText().toString();
                String StrikerStrikeRate = StrikerStrikeRateEdit.getText().toString();
                String NonStrikerStrikeRateStr = NonStrikerStrikeRateEdit.getText().toString();
                String BowlerEconomyRate = BowlerEconomyEdit.getText().toString();

                Integer StrikerRuns = Integer.parseInt(StrikerRunsStr);
                Integer NonStrikerRuns = Integer.parseInt(NonStrikerRunsStr);
                Integer StrikerBalls = Integer.parseInt(StrikerBallsStr);
                Integer NonStrikerBalls = Integer.parseInt(NonStrikerBallsStr);
                Double BowlerOvers = Double.parseDouble(BowlerOversStr);
                Integer BowlerRuns = Integer.parseInt(BowlerRunsStr);
                Integer BowlerWickets = Integer.parseInt(BowlerWicketsStr);
                Integer TotalRuns = Integer.parseInt(TotalRunsStr);
                Integer TotalWickets = Integer.parseInt(TotalWicketsStr);
                Double TotalOvers = Double.parseDouble(TotalOversStr);

                Integer StrikerFours = Integer.parseInt(StrikerFoursStr);
                Integer StrikerSixes = Integer.parseInt(StrikerSixesStr);
                Integer NonStrikerFours = Integer.parseInt(NonStrikerFoursStr);
                Integer NonStrikerSixes = Integer.parseInt(NonStrikerSixesStr);
                Double NonStrikerStrikeRate = Double.parseDouble(NonStrikerStrikeRateStr);


                if (BowlerOversStr.contains(".5") || TotalOversStr.contains(".5")) {

                    if(checkBoxByes.isChecked()) {

                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 1;
                            BowlerRuns = BowlerRuns + 1;

                        }  else if(checkBoxNoBall.isChecked()) {

                            TotalRuns = TotalRuns + 1;
                            BowlerRuns = BowlerRuns + 1;

                        }else {

                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.5;
                            TotalOvers = TotalOvers + 0.5;


                        }}
                    else {


                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 1;
                            BowlerRuns = BowlerRuns + 1;

                        } else if(checkBoxNoBall.isChecked()) {

                            BowlerRuns = BowlerRuns + 1;
                            TotalRuns = TotalRuns + 1;

                        } else {


                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.5;
                            TotalOvers = TotalOvers + 0.5;



                        }

                    }

//                    StrikerBalls = StrikerBalls + 1;
//                    BowlerOvers = BowlerOvers + 0.5;
//                    TotalOvers = TotalOvers + 0.5;

                    Double Economy;
                    Double StrikerRunsDouble = Double.valueOf(StrikerRuns);
                    Double StrikerBallsDouble = Double.valueOf(StrikerBalls);
                    Double StrikeRate = (StrikerRunsDouble/StrikerBallsDouble) * 100;

                    if (BowlerOvers.toString().contains(".")){

                        String[] OverString = BowlerOvers.toString().split("\\.");
                        String OversStr = OverString[0];
                        String BallsStr = OverString[1];
                        Double Balls = (Double.parseDouble(OversStr)*6)+Double.parseDouble(BallsStr);
                        Economy = (BowlerRuns/Balls)*6;
                    } else {
                        Economy = (BowlerRuns/BowlerOvers);
                    }

                    if(checkBoxWicket.isChecked() && !checkBoxNoBall.isChecked()) {

                        BowlerWickets = BowlerWickets + 1;
                        TotalWickets = TotalWickets + 1;
                        BowlerWicketsEdit.setText(BowlerWickets.toString());
                        TotalWicketsEdit.setText(TotalWickets.toString());

                        NormalMatchRunningSelectOutBatsmanPopup SelectOutBatsmanPopUpClass = new NormalMatchRunningSelectOutBatsmanPopup();
//
                        SelectOutBatsmanPopUpClass.OutBatsmanPopup(v,battingteamname,StrikerPlayer,NonStrikerPlayer,StrikerRuns,StrikerBalls,StrikerFours,StrikerSixes,StrikeRate,NonStrikerRuns,NonStrikerBalls,NonStrikerFours,NonStrikerSixes,NonStrikerStrikeRate,StrikerPlayerEdit, StrikerRunsEdit, StrikerBallsEdit, StrikerFoursEdit, StrikerSixesEdit, StrikerStrikeRateEdit, NonStrikerPlayerEdit, NonStrikerRunsEdit, NonStrikerBallsEdit, NonStrikerFoursEdit, NonStrikerSixesEdit,NonStrikerStrikeRateEdit,true,0, bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, 0, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);



                    } else  {

                        StrikerPlayerEdit.setText(NonStrikerPlayer);
                        StrikerBallsEdit.setText(NonStrikerBallsStr);
                        StrikerRunsEdit.setText(NonStrikerRunsStr);
                        StrikerFoursEdit.setText(NonStrikerFoursStr);
                        StrikerSixesEdit.setText(NonStrikerSixesStr);
                        StrikerStrikeRateEdit.setText(NonStrikerStrikeRateStr);
                        NonStrikerPlayerEdit.setText(StrikerPlayer);
                        NonStrikerRunsEdit.setText(StrikerRunsStr);
                        NonStrikerFoursEdit.setText(StrikerFoursStr);
                        NonStrikerSixesEdit.setText(StrikerSixesStr);
                        NonStrikerBallsEdit.setText(StrikerBalls.toString());
                        NonStrikerStrikeRateEdit.setText(String.format("%.2f", StrikeRate));

                        Integer BowlerMaidens = 0;

                        NormalMatchRunningSelectNewBowlerPopup SelectNewBowlerPopUpClass = new NormalMatchRunningSelectNewBowlerPopup();

                        SelectNewBowlerPopUpClass.NewBowlerPopup(v,bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, BowlerMaidens, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);

                    }



                    TotalOversEdit.setText(String.format("%.1f", TotalOvers));
                    TotalRunsEdit.setText(TotalRuns.toString());


                } else {

                    if(checkBoxByes.isChecked()) {

                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 1;
                            BowlerRuns = BowlerRuns + 1;

                        }  else if(checkBoxNoBall.isChecked()) {

                            TotalRuns = TotalRuns + 1;
                            BowlerRuns = BowlerRuns + 1;

                        }else {

                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.1;
                            TotalOvers = TotalOvers + 0.1;


                        }}
                    else {


                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 1;
                            BowlerRuns = BowlerRuns + 1;

                        } else if(checkBoxNoBall.isChecked()) {

                            BowlerRuns = BowlerRuns + 1;
                            TotalRuns = TotalRuns + 1;

                        } else {


                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.1;
                            TotalOvers = TotalOvers + 0.1;



                        }

                    }

//                    StrikerBalls = StrikerBalls + 1;
//                    BowlerOvers = BowlerOvers + 0.1;
//                    TotalOvers = TotalOvers + 0.1;

                    Double StrikerRunsDouble = Double.valueOf(StrikerRuns);
                    Double StrikerBallsDouble = Double.valueOf(StrikerBalls);
                    Double StrikeRate = (StrikerRunsDouble/StrikerBallsDouble) * 100;
                    Double Economy;

                    if (BowlerOvers.toString().contains(".")){

                        String[] OverString = BowlerOvers.toString().split("\\.");
                        String OversStr = OverString[0];
                        String BallsStr = OverString[1];
                        Double Balls = (Double.parseDouble(OversStr)*6)+Double.parseDouble(BallsStr);
                        Economy = (BowlerRuns/Balls)*6;
                    } else {
                        Economy = (BowlerRuns/BowlerOvers);
                    }

                    if(checkBoxWicket.isChecked() && !checkBoxNoBall.isChecked()) {

                        BowlerWickets = BowlerWickets + 1;
                        TotalWickets = TotalWickets + 1;
                        BowlerWicketsEdit.setText(BowlerWickets.toString());
                        TotalWicketsEdit.setText(TotalWickets.toString());

                        NormalMatchRunningSelectOutBatsmanPopup SelectOutBatsmanPopUpClass = new NormalMatchRunningSelectOutBatsmanPopup();
//
                        SelectOutBatsmanPopUpClass.OutBatsmanPopup(v,battingteamname,StrikerPlayer,NonStrikerPlayer,StrikerRuns,StrikerBalls,StrikerFours,StrikerSixes,StrikeRate,NonStrikerRuns,NonStrikerBalls,NonStrikerFours,NonStrikerSixes,NonStrikerStrikeRate,StrikerPlayerEdit, StrikerRunsEdit, StrikerBallsEdit, StrikerFoursEdit, StrikerSixesEdit, StrikerStrikeRateEdit, NonStrikerPlayerEdit, NonStrikerRunsEdit, NonStrikerBallsEdit, NonStrikerFoursEdit, NonStrikerSixesEdit,NonStrikerStrikeRateEdit,false,0, bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, 0, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);


                    } else {

                        StrikerBallsEdit.setText(StrikerBalls.toString());
                        StrikerStrikeRateEdit.setText(String.format("%.2f", StrikeRate));
                    }




                    BowlerRunsEdit.setText(BowlerRuns.toString());
                    BowlerOversEdit.setText(String.format("%.1f", BowlerOvers));
                    TotalOversEdit.setText(String.format("%.1f", TotalOvers));
                    TotalRunsEdit.setText(TotalRuns.toString());
                    BowlerEconomyEdit.setText(String.format("%.1f", Economy));



                }

                if (TotalOvers.equals(totalovers)) {

                    if (innings.equals("1")) {

                        Intent intent = new Intent(NormalMatchRunningScreen.this, NormalMatchRunningSelectInitialPlayersScreen.class);
                        intent.putExtra("battingteamname", bowlingteamname);
                        intent.putExtra("bowlingteamname", battingteamname);
                        intent.putExtra("totalwickets", totalwicketsstr);
                        intent.putExtra("totalovers", totaloversstr);
                        intent.putExtra("innings", "2");
                        startActivity(intent);

                    } else {

                    }
                }


            }

        });

        OneRunsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String StrikerRunsStr = StrikerRunsEdit.getText().toString();
                String NonStrikerRunsStr = NonStrikerRunsEdit.getText().toString();
                String StrikerBallsStr = StrikerBallsEdit.getText().toString();
                String NonStrikerBallsStr = NonStrikerBallsEdit.getText().toString();
                String BowlerOversStr = BowlerOversEdit.getText().toString();
                String BowlerRunsStr = BowlerRunsEdit.getText().toString();
                String BowlerWicketsStr = BowlerWicketsEdit.getText().toString();
                String TotalRunsStr = TotalRunsEdit.getText().toString();
                String TotalWicketsStr =  TotalWicketsEdit.getText().toString();
                String TotalOversStr = TotalOversEdit.getText().toString();
                String StrikerFoursStr = StrikerFoursEdit.getText().toString();
                String NonStrikerFoursStr = NonStrikerFoursEdit.getText().toString();
                String StrikerSixesStr = StrikerSixesEdit.getText().toString();
                String NonStrikerSixesStr = NonStrikerSixesEdit.getText().toString();
                String StrikerPlayer = StrikerPlayerEdit.getText().toString();
                String NonStrikerPlayer = NonStrikerPlayerEdit.getText().toString();
                String BowlerName = BowlerNameEdit.getText().toString();
                String StrikerStrikeRate = StrikerStrikeRateEdit.getText().toString();
                String NonStrikerStrikeRateStr = NonStrikerStrikeRateEdit.getText().toString();
                String BowlerEconomyRate = BowlerEconomyEdit.getText().toString();

                Integer StrikerRuns = Integer.parseInt(StrikerRunsStr);
                Integer NonStrikerRuns = Integer.parseInt(NonStrikerRunsStr);
                Integer StrikerBalls = Integer.parseInt(StrikerBallsStr);
                Integer NonStrikerBalls = Integer.parseInt(NonStrikerBallsStr);
                Double BowlerOvers = Double.parseDouble(BowlerOversStr);
                Integer BowlerRuns = Integer.parseInt(BowlerRunsStr);
                Integer BowlerWickets = Integer.parseInt(BowlerWicketsStr);
                Integer TotalRuns = Integer.parseInt(TotalRunsStr);
                Integer TotalWickets = Integer.parseInt(TotalWicketsStr);
                Double TotalOvers = Double.parseDouble(TotalOversStr);

                Integer StrikerFours = Integer.parseInt(StrikerFoursStr);
                Integer StrikerSixes = Integer.parseInt(StrikerSixesStr);
                Integer NonStrikerFours = Integer.parseInt(NonStrikerFoursStr);
                Integer NonStrikerSixes = Integer.parseInt(NonStrikerSixesStr);
                Double NonStrikerStrikeRate = Double.parseDouble(NonStrikerStrikeRateStr);

                NormalMatchRunningScreenModel normalMatchRunningScreenModel = new NormalMatchRunningScreenModel();


                if (BowlerOversStr.contains(".5") || TotalOversStr.contains(".5")) {

                    if(checkBoxByes.isChecked()) {

                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 2;
                            BowlerRuns = BowlerRuns + 1;

                        }  else if(checkBoxNoBall.isChecked()) {

                            TotalRuns = TotalRuns + 2;
                            BowlerRuns = BowlerRuns + 1;

                        }else {

                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.5;
                            TotalOvers = TotalOvers + 0.5;

                            TotalRuns = TotalRuns + 1;

                        }}
                    else {


                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 2;
                            BowlerRuns = BowlerRuns + 2;

                        } else if(checkBoxNoBall.isChecked()) {

                            StrikerRuns = StrikerRuns + 1;
                            BowlerRuns = BowlerRuns + 2;
                            TotalRuns = TotalRuns + 2;

                        } else {


                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.5;
                            TotalOvers = TotalOvers + 0.5;

                            StrikerRuns = StrikerRuns + 1;
                            BowlerRuns = BowlerRuns + 1;
                            TotalRuns = TotalRuns + 1;

                        }

                    }

//                    StrikerBalls = StrikerBalls + 1;
//                    BowlerOvers = BowlerOvers + 0.5;
//                    TotalOvers = TotalOvers + 0.5;
//
//                    StrikerRuns = StrikerRuns + 1;
//                    BowlerRuns = BowlerRuns + 1;
//                    TotalRuns = TotalRuns + 1;

                    Double StrikerRunsDouble = Double.valueOf(StrikerRuns);
                    Double StrikerBallsDouble = Double.valueOf(StrikerBalls);
                    Double StrikeRate = (StrikerRunsDouble/StrikerBallsDouble) * 100;
                    Double Economy;

                    if (BowlerOvers.toString().contains(".")){

                        String[] OverString = BowlerOvers.toString().split("\\.");
                        String OversStr = OverString[0];
                        String BallsStr = OverString[1];
                        Double Balls = (Double.parseDouble(OversStr)*6)+Double.parseDouble(BallsStr);
                        Economy = (BowlerRuns/Balls)*6;
                    } else {
                        Economy = (BowlerRuns/BowlerOvers);
                    }

                    if(checkBoxWicket.isChecked() && !checkBoxNoBall.isChecked()) {

                        BowlerWickets = BowlerWickets + 1;
                        TotalWickets = TotalWickets + 1;
                        BowlerWicketsEdit.setText(BowlerWickets.toString());
                        TotalWicketsEdit.setText(TotalWickets.toString());

                        NormalMatchRunningSelectOutBatsmanPopup SelectOutBatsmanPopUpClass = new NormalMatchRunningSelectOutBatsmanPopup();

                        SelectOutBatsmanPopUpClass.OutBatsmanPopup(v,battingteamname,StrikerPlayer,NonStrikerPlayer,StrikerRuns,StrikerBalls,StrikerFours,StrikerSixes,StrikeRate,NonStrikerRuns,NonStrikerBalls,NonStrikerFours,NonStrikerSixes,NonStrikerStrikeRate,StrikerPlayerEdit, StrikerRunsEdit, StrikerBallsEdit, StrikerFoursEdit, StrikerSixesEdit, StrikerStrikeRateEdit, NonStrikerPlayerEdit, NonStrikerRunsEdit, NonStrikerBallsEdit, NonStrikerFoursEdit, NonStrikerSixesEdit,NonStrikerStrikeRateEdit,true,1, bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, 0, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);


                    } else {

                        StrikerRunsEdit.setText(StrikerRuns.toString());
                        StrikerBallsEdit.setText(StrikerBalls.toString());
                        StrikerStrikeRateEdit.setText(String.format("%.2f", StrikeRate));

                        Integer BowlerMaidens = 0;

                        NormalMatchRunningSelectNewBowlerPopup SelectNewBowlerPopUpClass = new NormalMatchRunningSelectNewBowlerPopup();

                        SelectNewBowlerPopUpClass.NewBowlerPopup(v,bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, BowlerMaidens, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);

                    }



                    TotalRunsEdit.setText(TotalRuns.toString());
                    TotalOversEdit.setText(String.format("%.1f", TotalOvers));


                } else {

                    if(checkBoxByes.isChecked()) {

                        if(checkBoxWide.isChecked()) {

                            BowlerRuns = BowlerRuns + 1;
                            TotalRuns = TotalRuns + 2;


                        }  else if(checkBoxNoBall.isChecked()) {

                            BowlerRuns = BowlerRuns + 1;
                            TotalRuns = TotalRuns + 2;

                        }else {

                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.1;
                            TotalOvers = TotalOvers + 0.1;

                            TotalRuns = TotalRuns + 1;

                        }}
                    else {


                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 2;
                            BowlerRuns = BowlerRuns + 2;

                        } else if(checkBoxNoBall.isChecked()) {

                            StrikerRuns = StrikerRuns + 1;
                            BowlerRuns = BowlerRuns + 2;
                            TotalRuns = TotalRuns + 2;

                        } else {


                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.1;
                            TotalOvers = TotalOvers + 0.1;

                            StrikerRuns = StrikerRuns + 1;
                            BowlerRuns = BowlerRuns + 1;
                            TotalRuns = TotalRuns + 1;

                        }

                    }

//                    StrikerBalls = StrikerBalls + 1;
//                    BowlerOvers = BowlerOvers + 0.1;
//                    TotalOvers = TotalOvers + 0.1;
//
//                    StrikerRuns = StrikerRuns + 1;
//                    BowlerRuns = BowlerRuns + 1;
//                    TotalRuns = TotalRuns + 1;

                    Double StrikerRunsDouble = Double.valueOf(StrikerRuns);
                    Double StrikerBallsDouble = Double.valueOf(StrikerBalls);
                    Double StrikeRate = (StrikerRunsDouble/StrikerBallsDouble) * 100;
                    Double Economy;

                    if (BowlerOvers.toString().contains(".")){

                        String[] OverString = BowlerOvers.toString().split("\\.");
                        String OversStr = OverString[0];
                        String BallsStr = OverString[1];
                        Double Balls = (Double.parseDouble(OversStr)*6)+Double.parseDouble(BallsStr);
                        Economy = (BowlerRuns/Balls)*6;
                    } else {
                        Economy = (BowlerRuns/BowlerOvers);
                    }

                    if(checkBoxWicket.isChecked() && !checkBoxNoBall.isChecked()) {

                        BowlerWickets = BowlerWickets + 1;
                        TotalWickets = TotalWickets + 1;
                        BowlerWicketsEdit.setText(BowlerWickets.toString());
                        TotalWicketsEdit.setText(TotalWickets.toString());

                        NormalMatchRunningSelectOutBatsmanPopup SelectOutBatsmanPopUpClass = new NormalMatchRunningSelectOutBatsmanPopup();

                        SelectOutBatsmanPopUpClass.OutBatsmanPopup(v,battingteamname,StrikerPlayer,NonStrikerPlayer,StrikerRuns,StrikerBalls,StrikerFours,StrikerSixes,StrikeRate,NonStrikerRuns,NonStrikerBalls,NonStrikerFours,NonStrikerSixes,NonStrikerStrikeRate,StrikerPlayerEdit, StrikerRunsEdit, StrikerBallsEdit, StrikerFoursEdit, StrikerSixesEdit, StrikerStrikeRateEdit, NonStrikerPlayerEdit, NonStrikerRunsEdit, NonStrikerBallsEdit, NonStrikerFoursEdit, NonStrikerSixesEdit,NonStrikerStrikeRateEdit,false,1, bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, 0, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);

                    } else {

                        StrikerPlayerEdit.setText(NonStrikerPlayer);
                        StrikerBallsEdit.setText(NonStrikerBallsStr);
                        StrikerRunsEdit.setText(NonStrikerRunsStr);
                        StrikerFoursEdit.setText(NonStrikerFoursStr);
                        StrikerSixesEdit.setText(NonStrikerSixesStr);
                        StrikerStrikeRateEdit.setText(NonStrikerStrikeRateStr);
                        NonStrikerPlayerEdit.setText(StrikerPlayer);
                        NonStrikerBallsEdit.setText(StrikerBalls.toString());
                        NonStrikerRunsEdit.setText(StrikerRuns.toString());
                        NonStrikerFoursEdit.setText(StrikerFoursStr);
                        NonStrikerSixesEdit.setText(StrikerSixesStr);
                        NonStrikerStrikeRateEdit.setText(String.format("%.2f", StrikeRate));

                    }

                    BowlerRunsEdit.setText(BowlerRuns.toString());
                    TotalRunsEdit.setText(TotalRuns.toString());
                    BowlerOversEdit.setText(String.format("%.1f", BowlerOvers));
                    TotalOversEdit.setText(String.format("%.1f", TotalOvers));
                    BowlerEconomyEdit.setText(String.format("%.1f", Economy));


                }

                if (TotalOvers.equals(totalovers)) {

                    if (innings.equals("1")) {

                        Intent intent = new Intent(NormalMatchRunningScreen.this, NormalMatchRunningSelectInitialPlayersScreen.class);
                        intent.putExtra("battingteamname", bowlingteamname);
                        intent.putExtra("bowlingteamname", battingteamname);
                        intent.putExtra("totalwickets", totalwicketsstr);
                        intent.putExtra("totalovers", totaloversstr);
                        intent.putExtra("innings", "2");
                        startActivity(intent);

                    } else {

                    }
                }



            }

        });

        TwoRunsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String StrikerRunsStr = StrikerRunsEdit.getText().toString();
                String NonStrikerRunsStr = NonStrikerRunsEdit.getText().toString();
                String StrikerBallsStr = StrikerBallsEdit.getText().toString();
                String NonStrikerBallsStr = NonStrikerBallsEdit.getText().toString();
                String BowlerOversStr = BowlerOversEdit.getText().toString();
                String BowlerRunsStr = BowlerRunsEdit.getText().toString();
                String BowlerWicketsStr = BowlerWicketsEdit.getText().toString();
                String TotalRunsStr = TotalRunsEdit.getText().toString();
                String TotalWicketsStr =  TotalWicketsEdit.getText().toString();
                String TotalOversStr = TotalOversEdit.getText().toString();
                String StrikerFoursStr = StrikerFoursEdit.getText().toString();
                String NonStrikerFoursStr = NonStrikerFoursEdit.getText().toString();
                String StrikerSixesStr = StrikerSixesEdit.getText().toString();
                String NonStrikerSixesStr = NonStrikerSixesEdit.getText().toString();
                String StrikerPlayer = StrikerPlayerEdit.getText().toString();
                String NonStrikerPlayer = NonStrikerPlayerEdit.getText().toString();
                String BowlerName = BowlerNameEdit.getText().toString();
                String StrikerStrikeRate = StrikerStrikeRateEdit.getText().toString();
                String NonStrikerStrikeRateStr = NonStrikerStrikeRateEdit.getText().toString();
                String BowlerEconomyRate = BowlerEconomyEdit.getText().toString();

                Integer StrikerRuns = Integer.parseInt(StrikerRunsStr);
                Integer NonStrikerRuns = Integer.parseInt(NonStrikerRunsStr);
                Integer StrikerBalls = Integer.parseInt(StrikerBallsStr);
                Integer NonStrikerBalls = Integer.parseInt(NonStrikerBallsStr);
                Double BowlerOvers = Double.parseDouble(BowlerOversStr);
                Integer BowlerRuns = Integer.parseInt(BowlerRunsStr);
                Integer BowlerWickets = Integer.parseInt(BowlerWicketsStr);
                Integer TotalRuns = Integer.parseInt(TotalRunsStr);
                Integer TotalWickets = Integer.parseInt(TotalWicketsStr);
                Double TotalOvers = Double.parseDouble(TotalOversStr);

                Integer StrikerFours = Integer.parseInt(StrikerFoursStr);
                Integer StrikerSixes = Integer.parseInt(StrikerSixesStr);
                Integer NonStrikerFours = Integer.parseInt(NonStrikerFoursStr);
                Integer NonStrikerSixes = Integer.parseInt(NonStrikerSixesStr);
                Double NonStrikerStrikeRate = Double.parseDouble(NonStrikerStrikeRateStr);


                if (BowlerOversStr.contains(".5") || TotalOversStr.contains(".5")) {

                    if(checkBoxByes.isChecked()) {

                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 3;
                            BowlerRuns = BowlerRuns + 1;

                        }  else if(checkBoxNoBall.isChecked()) {

                            BowlerRuns = BowlerRuns + 1;
                            TotalRuns = TotalRuns + 3;

                        }else {

                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.5;
                            TotalOvers = TotalOvers + 0.5;

                            TotalRuns = TotalRuns + 2;

                        }}
                    else {


                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 3;
                            BowlerRuns = BowlerRuns + 3;

                        } else if(checkBoxNoBall.isChecked()) {

                            StrikerRuns = StrikerRuns + 2;
                            BowlerRuns = BowlerRuns + 3;
                            TotalRuns = TotalRuns + 3;

                        } else {


                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.5;
                            TotalOvers = TotalOvers + 0.5;

                            StrikerRuns = StrikerRuns + 2;
                            BowlerRuns = BowlerRuns + 2;
                            TotalRuns = TotalRuns + 2;

                        }

                    }


//                    StrikerBalls = StrikerBalls + 1;
//                    BowlerOvers = BowlerOvers + 0.5;
//                    TotalOvers = TotalOvers + 0.5;
//
//                    StrikerRuns = StrikerRuns + 2;
//                    BowlerRuns = BowlerRuns + 2;
//                    TotalRuns = TotalRuns + 2;

                    Double StrikerRunsDouble = Double.valueOf(StrikerRuns);
                    Double StrikerBallsDouble = Double.valueOf(StrikerBalls);
                    Double StrikeRate = (StrikerRunsDouble/StrikerBallsDouble) * 100;
                    Double Economy ;

                    if (BowlerOvers.toString().contains(".")){

                        String[] OverString = BowlerOvers.toString().split("\\.");
                        String OversStr = OverString[0];
                        String BallsStr = OverString[1];
                        Double Balls = (Double.parseDouble(OversStr)*6)+Double.parseDouble(BallsStr);
                        Economy = (BowlerRuns/Balls)*6;
                    } else {
                        Economy = (BowlerRuns/BowlerOvers);
                    }

                    if(checkBoxWicket.isChecked() && !checkBoxNoBall.isChecked()) {

                        BowlerWickets = BowlerWickets + 1;
                        TotalWickets = TotalWickets + 1;
                        BowlerWicketsEdit.setText(BowlerWickets.toString());
                        TotalWicketsEdit.setText(TotalWickets.toString());

                        NormalMatchRunningSelectOutBatsmanPopup SelectOutBatsmanPopUpClass = new NormalMatchRunningSelectOutBatsmanPopup();

                        SelectOutBatsmanPopUpClass.OutBatsmanPopup(v,battingteamname,StrikerPlayer,NonStrikerPlayer,StrikerRuns,StrikerBalls,StrikerFours,StrikerSixes,StrikeRate,NonStrikerRuns,NonStrikerBalls,NonStrikerFours,NonStrikerSixes,NonStrikerStrikeRate,StrikerPlayerEdit, StrikerRunsEdit, StrikerBallsEdit, StrikerFoursEdit, StrikerSixesEdit, StrikerStrikeRateEdit, NonStrikerPlayerEdit, NonStrikerRunsEdit, NonStrikerBallsEdit, NonStrikerFoursEdit, NonStrikerSixesEdit,NonStrikerStrikeRateEdit,true,2, bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, 0, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);

                    } else {

                        StrikerPlayerEdit.setText(NonStrikerPlayer);
                        StrikerBallsEdit.setText(NonStrikerBallsStr);
                        StrikerRunsEdit.setText(NonStrikerRunsStr);
                        StrikerFoursEdit.setText(NonStrikerFoursStr);
                        StrikerSixesEdit.setText(NonStrikerSixesStr);
                        StrikerStrikeRateEdit.setText(NonStrikerStrikeRateStr);
                        NonStrikerPlayerEdit.setText(StrikerPlayer);
                        NonStrikerBallsEdit.setText(StrikerBalls.toString());
                        NonStrikerRunsEdit.setText(StrikerRuns.toString());
                        NonStrikerFoursEdit.setText(StrikerFoursStr);
                        NonStrikerSixesEdit.setText(StrikerSixesStr);
                        NonStrikerStrikeRateEdit.setText(String.format("%.2f", StrikeRate));

                        Integer BowlerMaidens = 0;

                        NormalMatchRunningSelectNewBowlerPopup SelectNewBowlerPopUpClass = new NormalMatchRunningSelectNewBowlerPopup();

                        SelectNewBowlerPopUpClass.NewBowlerPopup(v,bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, BowlerMaidens, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);


                    }



                    TotalRunsEdit.setText(TotalRuns.toString());
                    TotalOversEdit.setText(String.format("%.1f", TotalOvers));




                } else {

                    if(checkBoxByes.isChecked()) {

                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 3;
                            BowlerRuns = BowlerRuns + 1;


                        }  else if(checkBoxNoBall.isChecked()) {

                            TotalRuns = TotalRuns + 3;
                            BowlerRuns = BowlerRuns + 1;

                        }else {

                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.1;
                            TotalOvers = TotalOvers + 0.1;

                            TotalRuns = TotalRuns + 2;

                        }}
                    else {


                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 3;
                            BowlerRuns = BowlerRuns + 3;

                        } else if(checkBoxNoBall.isChecked()) {

                            StrikerRuns = StrikerRuns + 2;
                            TotalRuns = TotalRuns + 3;
                            BowlerRuns = BowlerRuns + 3;

                        } else {


                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.1;
                            TotalOvers = TotalOvers + 0.1;

                            StrikerRuns = StrikerRuns + 2;
                            BowlerRuns = BowlerRuns + 2;
                            TotalRuns = TotalRuns + 2;

                        }

                    }

//                    StrikerBalls = StrikerBalls + 1;
//                    BowlerOvers = BowlerOvers + 0.1;
//                    TotalOvers = TotalOvers + 0.1;
//
//                    StrikerRuns = StrikerRuns + 2;
//                    BowlerRuns = BowlerRuns + 2;
//                    TotalRuns = TotalRuns + 2;

                    Double StrikerRunsDouble = Double.valueOf(StrikerRuns);
                    Double StrikerBallsDouble = Double.valueOf(StrikerBalls);
                    Double StrikeRate = (StrikerRunsDouble/StrikerBallsDouble) * 100;
                    Double Economy;

                    if (BowlerOvers.toString().contains(".")){

                        String[] OverString = BowlerOvers.toString().split("\\.");
                        String OversStr = OverString[0];
                        String BallsStr = OverString[1];
                        Double Balls = (Double.parseDouble(OversStr)*6)+Double.parseDouble(BallsStr);
                        Economy = (BowlerRuns/Balls)*6;
                    } else {
                        Economy = (BowlerRuns/BowlerOvers);
                    }

                    if(checkBoxWicket.isChecked() && !checkBoxNoBall.isChecked()) {

                        BowlerWickets = BowlerWickets + 1;
                        TotalWickets = TotalWickets + 1;
                        BowlerWicketsEdit.setText(BowlerWickets.toString());
                        TotalWicketsEdit.setText(TotalWickets.toString());

                        NormalMatchRunningSelectOutBatsmanPopup SelectOutBatsmanPopUpClass = new NormalMatchRunningSelectOutBatsmanPopup();

                        SelectOutBatsmanPopUpClass.OutBatsmanPopup(v,battingteamname,StrikerPlayer,NonStrikerPlayer,StrikerRuns,StrikerBalls,StrikerFours,StrikerSixes,StrikeRate,NonStrikerRuns,NonStrikerBalls,NonStrikerFours,NonStrikerSixes,NonStrikerStrikeRate,StrikerPlayerEdit, StrikerRunsEdit, StrikerBallsEdit, StrikerFoursEdit, StrikerSixesEdit, StrikerStrikeRateEdit, NonStrikerPlayerEdit, NonStrikerRunsEdit, NonStrikerBallsEdit, NonStrikerFoursEdit, NonStrikerSixesEdit,NonStrikerStrikeRateEdit,false,2, bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, 0, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);

                    } else {

                        StrikerRunsEdit.setText(StrikerRuns.toString());
                        StrikerBallsEdit.setText(StrikerBalls.toString());
                        StrikerStrikeRateEdit.setText(String.format("%.2f", StrikeRate));
                    }


                    BowlerRunsEdit.setText(BowlerRuns.toString());
                    TotalRunsEdit.setText(TotalRuns.toString());

                    BowlerOversEdit.setText(String.format("%.1f", BowlerOvers));
                    TotalOversEdit.setText(String.format("%.1f", TotalOvers));
                    BowlerEconomyEdit.setText(String.format("%.1f", Economy));

                }

                if (TotalOvers.equals(totalovers)) {

                    if (innings.equals("1")) {

                        Intent intent = new Intent(NormalMatchRunningScreen.this, NormalMatchRunningSelectInitialPlayersScreen.class);
                        intent.putExtra("battingteamname", bowlingteamname);
                        intent.putExtra("bowlingteamname", battingteamname);
                        intent.putExtra("totalwickets", totalwicketsstr);
                        intent.putExtra("totalovers", totaloversstr);
                        intent.putExtra("innings", "2");
                        startActivity(intent);

                    } else {

                    }
                }


            }

        });

        ThreeRunsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String StrikerRunsStr = StrikerRunsEdit.getText().toString();
                String NonStrikerRunsStr = NonStrikerRunsEdit.getText().toString();
                String StrikerBallsStr = StrikerBallsEdit.getText().toString();
                String NonStrikerBallsStr = NonStrikerBallsEdit.getText().toString();
                String BowlerOversStr = BowlerOversEdit.getText().toString();
                String BowlerRunsStr = BowlerRunsEdit.getText().toString();
                String BowlerWicketsStr = BowlerWicketsEdit.getText().toString();
                String TotalRunsStr = TotalRunsEdit.getText().toString();
                String TotalWicketsStr =  TotalWicketsEdit.getText().toString();
                String TotalOversStr = TotalOversEdit.getText().toString();
                String StrikerFoursStr = StrikerFoursEdit.getText().toString();
                String NonStrikerFoursStr = NonStrikerFoursEdit.getText().toString();
                String StrikerSixesStr = StrikerSixesEdit.getText().toString();
                String NonStrikerSixesStr = NonStrikerSixesEdit.getText().toString();
                String StrikerPlayer = StrikerPlayerEdit.getText().toString();
                String NonStrikerPlayer = NonStrikerPlayerEdit.getText().toString();
                String BowlerName = BowlerNameEdit.getText().toString();
                String StrikerStrikeRate = StrikerStrikeRateEdit.getText().toString();
                String NonStrikerStrikeRateStr = NonStrikerStrikeRateEdit.getText().toString();
                String BowlerEconomyRate = BowlerEconomyEdit.getText().toString();

                Integer StrikerRuns = Integer.parseInt(StrikerRunsStr);
                Integer NonStrikerRuns = Integer.parseInt(NonStrikerRunsStr);
                Integer StrikerBalls = Integer.parseInt(StrikerBallsStr);
                Integer NonStrikerBalls = Integer.parseInt(NonStrikerBallsStr);
                Double BowlerOvers = Double.parseDouble(BowlerOversStr);
                Integer BowlerRuns = Integer.parseInt(BowlerRunsStr);
                Integer BowlerWickets = Integer.parseInt(BowlerWicketsStr);
                Integer TotalRuns = Integer.parseInt(TotalRunsStr);
                Integer TotalWickets = Integer.parseInt(TotalWicketsStr);
                Double TotalOvers = Double.parseDouble(TotalOversStr);
                Integer StrikerFours = Integer.parseInt(StrikerFoursStr);
                Integer StrikerSixes = Integer.parseInt(StrikerSixesStr);
                Integer NonStrikerFours = Integer.parseInt(NonStrikerFoursStr);
                Integer NonStrikerSixes = Integer.parseInt(NonStrikerSixesStr);
                Double NonStrikerStrikeRate = Double.parseDouble(NonStrikerStrikeRateStr);

                NormalMatchRunningScreenModel normalMatchRunningScreenModel = new NormalMatchRunningScreenModel();



                if (BowlerOversStr.contains(".5") || TotalOversStr.contains(".5")) {

                    if(checkBoxByes.isChecked()) {

                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 4;
                            BowlerRuns = BowlerRuns + 1;


                        }  else if(checkBoxNoBall.isChecked()) {

                            TotalRuns = TotalRuns + 4;
                            BowlerRuns = BowlerRuns + 1;

                        }else {

                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.5;
                            TotalOvers = TotalOvers + 0.5;

                            TotalRuns = TotalRuns + 3;

                        }}
                    else {


                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 4;
                            BowlerRuns = BowlerRuns + 4;

                        } else if(checkBoxNoBall.isChecked()) {

                            StrikerRuns = StrikerRuns + 3;
                            TotalRuns = TotalRuns + 4;
                            BowlerRuns = BowlerRuns + 4;

                        } else {


                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.5;
                            TotalOvers = TotalOvers + 0.5;

                            StrikerRuns = StrikerRuns + 3;
                            BowlerRuns = BowlerRuns + 3;
                            TotalRuns = TotalRuns + 3;

                        }

                    }

//                    StrikerBalls = StrikerBalls + 1;
//                    BowlerOvers = BowlerOvers + 0.5;
//                    TotalOvers = TotalOvers + 0.5;
//
//                    StrikerRuns = StrikerRuns + 3;
//                    BowlerRuns = BowlerRuns + 3;
//                    TotalRuns = TotalRuns + 3;

                    Double StrikerRunsDouble = Double.valueOf(StrikerRuns);
                    Double StrikerBallsDouble = Double.valueOf(StrikerBalls);
                    Double StrikeRate = (StrikerRunsDouble/StrikerBallsDouble) * 100;
                    Double Economy = (BowlerRuns/BowlerOvers);

                    if(checkBoxWicket.isChecked() && !checkBoxNoBall.isChecked()) {

                        BowlerWickets = BowlerWickets + 1;
                        TotalWickets = TotalWickets + 1;
                        BowlerWicketsEdit.setText(BowlerWickets.toString());
                        TotalWicketsEdit.setText(TotalWickets.toString());

                        NormalMatchRunningSelectOutBatsmanPopup SelectOutBatsmanPopUpClass = new NormalMatchRunningSelectOutBatsmanPopup();

                        SelectOutBatsmanPopUpClass.OutBatsmanPopup(v,battingteamname,StrikerPlayer,NonStrikerPlayer,StrikerRuns,StrikerBalls,StrikerFours,StrikerSixes,StrikeRate,NonStrikerRuns,NonStrikerBalls,NonStrikerFours,NonStrikerSixes,NonStrikerStrikeRate,StrikerPlayerEdit, StrikerRunsEdit, StrikerBallsEdit, StrikerFoursEdit, StrikerSixesEdit, StrikerStrikeRateEdit, NonStrikerPlayerEdit, NonStrikerRunsEdit, NonStrikerBallsEdit, NonStrikerFoursEdit, NonStrikerSixesEdit,NonStrikerStrikeRateEdit,true,3, bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, 0, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);

                    } else {

                        StrikerRunsEdit.setText(StrikerRuns.toString());
                        StrikerBallsEdit.setText(StrikerBalls.toString());
                        StrikerStrikeRateEdit.setText(String.format("%.2f", StrikeRate));

                        Integer BowlerMaidens = 0;

                        NormalMatchRunningSelectNewBowlerPopup SelectNewBowlerPopUpClass = new NormalMatchRunningSelectNewBowlerPopup();

                        SelectNewBowlerPopUpClass.NewBowlerPopup(v,bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, BowlerMaidens, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);


                    }



                    TotalRunsEdit.setText(TotalRuns.toString());
                   TotalOversEdit.setText(String.format("%.1f", TotalOvers));


                } else {

                    if(checkBoxByes.isChecked()) {

                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 4;
                            BowlerRuns = BowlerRuns + 1;


                        }  else if(checkBoxNoBall.isChecked()) {

                            TotalRuns = TotalRuns + 4;
                            BowlerRuns = BowlerRuns + 1;

                        }else {

                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.1;
                            TotalOvers = TotalOvers + 0.1;

                            TotalRuns = TotalRuns + 3;

                        }}
                    else {


                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 4;
                            BowlerRuns = BowlerRuns + 4;

                        } else if(checkBoxNoBall.isChecked()) {

                            StrikerRuns = StrikerRuns + 3;
                            TotalRuns = TotalRuns + 4;
                            BowlerRuns = BowlerRuns + 4;

                        } else {


                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.1;
                            TotalOvers = TotalOvers + 0.1;

                            StrikerRuns = StrikerRuns + 3;
                            BowlerRuns = BowlerRuns + 3;
                            TotalRuns = TotalRuns + 3;

                        }

                    }

//                    StrikerBalls = StrikerBalls + 1;
//                    BowlerOvers = BowlerOvers + 0.1;
//                    TotalOvers = TotalOvers + 0.1;
//
//                    StrikerRuns = StrikerRuns + 3;
//                    BowlerRuns = BowlerRuns + 3;
//                    TotalRuns = TotalRuns + 3;

                    Double StrikerRunsDouble = Double.valueOf(StrikerRuns);
                    Double StrikerBallsDouble = Double.valueOf(StrikerBalls);
                    Double StrikeRate = (StrikerRunsDouble/StrikerBallsDouble) * 100;
                    Double Economy;

                    if (BowlerOvers.toString().contains(".")){

                        String[] OverString = BowlerOvers.toString().split("\\.");
                        String OversStr = OverString[0];
                        String BallsStr = OverString[1];
                        Double Balls = (Double.parseDouble(OversStr)*6)+Double.parseDouble(BallsStr);
                        Economy = (BowlerRuns/Balls)*6;
                    } else {
                        Economy = (BowlerRuns/BowlerOvers);
                    }

                    if(checkBoxWicket.isChecked() && !checkBoxNoBall.isChecked()) {

                        BowlerWickets = BowlerWickets + 1;
                        TotalWickets = TotalWickets + 1;
                        BowlerWicketsEdit.setText(BowlerWickets.toString());
                        TotalWicketsEdit.setText(TotalWickets.toString());

                        NormalMatchRunningSelectOutBatsmanPopup SelectOutBatsmanPopUpClass = new NormalMatchRunningSelectOutBatsmanPopup();

                        SelectOutBatsmanPopUpClass.OutBatsmanPopup(v,battingteamname,StrikerPlayer,NonStrikerPlayer,StrikerRuns,StrikerBalls,StrikerFours,StrikerSixes,StrikeRate,NonStrikerRuns,NonStrikerBalls,NonStrikerFours,NonStrikerSixes,NonStrikerStrikeRate,StrikerPlayerEdit, StrikerRunsEdit, StrikerBallsEdit, StrikerFoursEdit, StrikerSixesEdit, StrikerStrikeRateEdit, NonStrikerPlayerEdit, NonStrikerRunsEdit, NonStrikerBallsEdit, NonStrikerFoursEdit, NonStrikerSixesEdit,NonStrikerStrikeRateEdit,false,3, bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, 0, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);

                    } else {

                        StrikerPlayerEdit.setText(NonStrikerPlayer);
                        StrikerBallsEdit.setText(NonStrikerBallsStr);
                        StrikerRunsEdit.setText(NonStrikerRunsStr);
                        StrikerFoursEdit.setText(NonStrikerFoursStr);
                        StrikerSixesEdit.setText(NonStrikerSixesStr);
                        StrikerStrikeRateEdit.setText(NonStrikerStrikeRateStr);
                        NonStrikerPlayerEdit.setText(StrikerPlayer);
                        NonStrikerBallsEdit.setText(StrikerBalls.toString());
                        NonStrikerRunsEdit.setText(StrikerRuns.toString());
                        NonStrikerFoursEdit.setText(StrikerFoursStr);
                        NonStrikerSixesEdit.setText(StrikerSixesStr);
                        NonStrikerStrikeRateEdit.setText(String.format("%.2f", StrikeRate));

                    }


                    BowlerRunsEdit.setText(BowlerRuns.toString());
                    TotalRunsEdit.setText(TotalRuns.toString());

                    BowlerOversEdit.setText(String.format("%.1f", BowlerOvers));
                    TotalOversEdit.setText(String.format("%.1f", TotalOvers));
                    BowlerEconomyEdit.setText(String.format("%.1f", Economy));
                    BowlerEconomyEdit.setText(Economy.toString());

                }

                if (TotalOvers.equals(totalovers)) {

                    if (innings.equals("1")) {

                        Intent intent = new Intent(NormalMatchRunningScreen.this, NormalMatchRunningSelectInitialPlayersScreen.class);
                        intent.putExtra("battingteamname", bowlingteamname);
                        intent.putExtra("bowlingteamname", battingteamname);
                        intent.putExtra("totalwickets", totalwicketsstr);
                        intent.putExtra("totalovers", totaloversstr);
                        intent.putExtra("innings", "2");
                        startActivity(intent);

                    } else {

                    }
                }

            }

        });

        FourRunsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String StrikerRunsStr = StrikerRunsEdit.getText().toString();
                String NonStrikerRunsStr = NonStrikerRunsEdit.getText().toString();
                String StrikerBallsStr = StrikerBallsEdit.getText().toString();
                String NonStrikerBallsStr = NonStrikerBallsEdit.getText().toString();
                String BowlerOversStr = BowlerOversEdit.getText().toString();
                String BowlerRunsStr = BowlerRunsEdit.getText().toString();
                String BowlerWicketsStr = BowlerWicketsEdit.getText().toString();
                String TotalRunsStr = TotalRunsEdit.getText().toString();
                String TotalWicketsStr =  TotalWicketsEdit.getText().toString();
                String TotalOversStr = TotalOversEdit.getText().toString();
                String StrikerFoursStr = StrikerFoursEdit.getText().toString();
                String NonStrikerFoursStr = NonStrikerFoursEdit.getText().toString();
                String StrikerSixesStr = StrikerSixesEdit.getText().toString();
                String NonStrikerSixesStr = NonStrikerSixesEdit.getText().toString();
                String StrikerPlayer = StrikerPlayerEdit.getText().toString();
                String NonStrikerPlayer = NonStrikerPlayerEdit.getText().toString();
                String BowlerName = BowlerNameEdit.getText().toString();
                String StrikerStrikeRate = StrikerStrikeRateEdit.getText().toString();
                String NonStrikerStrikeRateStr = NonStrikerStrikeRateEdit.getText().toString();
                String BowlerEconomyRate = BowlerEconomyEdit.getText().toString();

                Integer StrikerRuns = Integer.parseInt(StrikerRunsStr);
                Integer NonStrikerRuns = Integer.parseInt(NonStrikerRunsStr);
                Integer StrikerBalls = Integer.parseInt(StrikerBallsStr);
                Integer NonStrikerBalls = Integer.parseInt(NonStrikerBallsStr);
                Double BowlerOvers = Double.parseDouble(BowlerOversStr);
                Integer BowlerRuns = Integer.parseInt(BowlerRunsStr);
                Integer BowlerWickets = Integer.parseInt(BowlerWicketsStr);
                Integer TotalRuns = Integer.parseInt(TotalRunsStr);
                Integer TotalWickets = Integer.parseInt(TotalWicketsStr);
                Double TotalOvers = Double.parseDouble(TotalOversStr);
                Integer StrikerFours = Integer.parseInt(StrikerFoursStr);
                Integer StrikerSixes = Integer.parseInt(StrikerSixesStr);
                Integer NonStrikerFours = Integer.parseInt(NonStrikerFoursStr);
                Integer NonStrikerSixes = Integer.parseInt(NonStrikerSixesStr);
                Double NonStrikerStrikeRate = Double.parseDouble(NonStrikerStrikeRateStr);

                NormalMatchRunningScreenModel normalMatchRunningScreenModel = new NormalMatchRunningScreenModel();


                if (BowlerOversStr.contains(".5") || TotalOversStr.contains(".5")) {

                    if(checkBoxByes.isChecked()) {

                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 5;
                            BowlerRuns = BowlerRuns + 1;


                        }  else if(checkBoxNoBall.isChecked()) {

                            TotalRuns = TotalRuns + 5;
                            BowlerRuns = BowlerRuns + 1;

                        }else {

                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.5;
                            TotalOvers = TotalOvers + 0.5;

                            TotalRuns = TotalRuns + 4;

                        }}
                    else {


                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 5;
                            BowlerRuns = BowlerRuns + 5;

                        } else if(checkBoxNoBall.isChecked()) {

                            StrikerRuns = StrikerRuns + 4;
                            TotalRuns = TotalRuns + 5;
                            BowlerRuns = BowlerRuns + 5;
                            StrikerFours = StrikerFours + 1;

                        } else {


                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.5;
                            TotalOvers = TotalOvers + 0.5;
                            StrikerFours = StrikerFours + 1;

                            StrikerRuns = StrikerRuns + 4;
                            BowlerRuns = BowlerRuns + 4;
                            TotalRuns = TotalRuns + 4;

                        }

                    }

//                    StrikerBalls = StrikerBalls + 1;
//                    BowlerOvers = BowlerOvers + 0.5;
//                    TotalOvers = TotalOvers + 0.5;
//
//                    StrikerRuns = StrikerRuns + 4;
//                    BowlerRuns = BowlerRuns + 4;
//                    TotalRuns = TotalRuns + 4;
//                    StrikerFours = StrikerFours + 1;

                    Double StrikerRunsDouble = Double.valueOf(StrikerRuns);
                    Double StrikerBallsDouble = Double.valueOf(StrikerBalls);
                    Double StrikeRate = (StrikerRunsDouble/StrikerBallsDouble) * 100;
                    Double Economy;

                    if (BowlerOvers.toString().contains(".")){

                        String[] OverString = BowlerOvers.toString().split("\\.");
                        String OversStr = OverString[0];
                        String BallsStr = OverString[1];
                        Double Balls = (Double.parseDouble(OversStr)*6)+Double.parseDouble(BallsStr);
                        Economy = (BowlerRuns/Balls)*6;
                    } else {
                        Economy = (BowlerRuns/BowlerOvers);
                    }

                    if(checkBoxWicket.isChecked() && !checkBoxNoBall.isChecked()) {

                        BowlerWickets = BowlerWickets + 1;
                        TotalWickets = TotalWickets + 1;
                        BowlerWicketsEdit.setText(BowlerWickets.toString());
                        TotalWicketsEdit.setText(TotalWickets.toString());

                        NormalMatchRunningSelectOutBatsmanPopup SelectOutBatsmanPopUpClass = new NormalMatchRunningSelectOutBatsmanPopup();

                        SelectOutBatsmanPopUpClass.OutBatsmanPopup(v,battingteamname,StrikerPlayer,NonStrikerPlayer,StrikerRuns,StrikerBalls,StrikerFours,StrikerSixes,StrikeRate,NonStrikerRuns,NonStrikerBalls,NonStrikerFours,NonStrikerSixes,NonStrikerStrikeRate,StrikerPlayerEdit, StrikerRunsEdit, StrikerBallsEdit, StrikerFoursEdit, StrikerSixesEdit, StrikerStrikeRateEdit, NonStrikerPlayerEdit, NonStrikerRunsEdit, NonStrikerBallsEdit, NonStrikerFoursEdit, NonStrikerSixesEdit,NonStrikerStrikeRateEdit,true,4, bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, 0, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);

                    } else {

                        StrikerPlayerEdit.setText(NonStrikerPlayer);
                        StrikerBallsEdit.setText(NonStrikerBallsStr);
                        StrikerRunsEdit.setText(NonStrikerRunsStr);
                        StrikerFoursEdit.setText(NonStrikerFoursStr);
                        StrikerSixesEdit.setText(NonStrikerSixesStr);
                        StrikerStrikeRateEdit.setText(NonStrikerStrikeRateStr);
                        NonStrikerPlayerEdit.setText(StrikerPlayer);
                        NonStrikerBallsEdit.setText(StrikerBalls.toString());
                        NonStrikerRunsEdit.setText(StrikerRuns.toString());
                        NonStrikerFoursEdit.setText(StrikerFours.toString());
                        NonStrikerSixesEdit.setText(StrikerSixesStr);
                        NonStrikerStrikeRateEdit.setText(String.format("%.2f", StrikeRate));

                        Integer BowlerMaidens = 0;

                        NormalMatchRunningSelectNewBowlerPopup SelectNewBowlerPopUpClass = new NormalMatchRunningSelectNewBowlerPopup();

                        SelectNewBowlerPopUpClass.NewBowlerPopup(v,bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, BowlerMaidens, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);


                    }



                    TotalRunsEdit.setText(TotalRuns.toString());
                    TotalOversEdit.setText(String.format("%.1f", TotalOvers));



                } else {

                    if(checkBoxByes.isChecked()) {

                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 5;
                            BowlerRuns = BowlerRuns + 1;


                        }  else if(checkBoxNoBall.isChecked()) {

                            TotalRuns = TotalRuns + 5;
                            BowlerRuns = BowlerRuns + 1;

                        }else {

                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.1;
                            TotalOvers = TotalOvers + 0.1;

                            TotalRuns = TotalRuns + 4;

                        }}
                    else {


                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 5;
                            BowlerRuns = BowlerRuns + 5;

                        } else if(checkBoxNoBall.isChecked()) {

                            StrikerRuns = StrikerRuns + 4;
                            TotalRuns = TotalRuns + 5;
                            BowlerRuns = BowlerRuns + 5;
                            StrikerFours = StrikerFours + 1;

                        } else {


                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.1;
                            TotalOvers = TotalOvers + 0.1;
                            StrikerFours = StrikerFours + 1;

                            StrikerRuns = StrikerRuns + 4;
                            BowlerRuns = BowlerRuns + 4;
                            TotalRuns = TotalRuns + 4;

                        }

                    }

//                    StrikerBalls = StrikerBalls + 1;
//                    BowlerOvers = BowlerOvers + 0.1;
//                    TotalOvers = TotalOvers + 0.1;
//
//                    StrikerRuns = StrikerRuns + 4;
//                    BowlerRuns = BowlerRuns + 4;
//                    TotalRuns = TotalRuns + 4;
//                    StrikerFours = StrikerFours + 1;

                    Double StrikerRunsDouble = Double.valueOf(StrikerRuns);
                    Double StrikerBallsDouble = Double.valueOf(StrikerBalls);
                    Double StrikeRate = (StrikerRunsDouble/StrikerBallsDouble) * 100;
                    Double Economy;

                    if (BowlerOvers.toString().contains(".")){

                        String[] OverString = BowlerOvers.toString().split("\\.");
                        String OversStr = OverString[0];
                        String BallsStr = OverString[1];
                        Double Balls = (Double.parseDouble(OversStr)*6)+Double.parseDouble(BallsStr);
                        Economy = (BowlerRuns/Balls)*6;
                    } else {
                        Economy = (BowlerRuns/BowlerOvers);
                    }

                    if(checkBoxWicket.isChecked() && !checkBoxNoBall.isChecked()) {

                        BowlerWickets = BowlerWickets + 1;
                        TotalWickets = TotalWickets + 1;
                        BowlerWicketsEdit.setText(BowlerWickets.toString());
                        TotalWicketsEdit.setText(TotalWickets.toString());

                        NormalMatchRunningSelectOutBatsmanPopup SelectOutBatsmanPopUpClass = new NormalMatchRunningSelectOutBatsmanPopup();

                        SelectOutBatsmanPopUpClass.OutBatsmanPopup(v,battingteamname,StrikerPlayer,NonStrikerPlayer,StrikerRuns,StrikerBalls,StrikerFours,StrikerSixes,StrikeRate,NonStrikerRuns,NonStrikerBalls,NonStrikerFours,NonStrikerSixes,NonStrikerStrikeRate,StrikerPlayerEdit, StrikerRunsEdit, StrikerBallsEdit, StrikerFoursEdit, StrikerSixesEdit, StrikerStrikeRateEdit, NonStrikerPlayerEdit, NonStrikerRunsEdit, NonStrikerBallsEdit, NonStrikerFoursEdit, NonStrikerSixesEdit,NonStrikerStrikeRateEdit,false,4, bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, 0, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);

                    } else {

                        StrikerRunsEdit.setText(StrikerRuns.toString());
                        StrikerBallsEdit.setText(StrikerBalls.toString());
                        StrikerFoursEdit.setText(StrikerFours.toString());
                        StrikerStrikeRateEdit.setText(String.format("%.2f", StrikeRate));
                    }


                    BowlerRunsEdit.setText(BowlerRuns.toString());
                    TotalRunsEdit.setText(TotalRuns.toString());
                    BowlerOversEdit.setText(BowlerOvers.toString());
                    BowlerOversEdit.setText(String.format("%.1f", BowlerOvers));
                    TotalOversEdit.setText(String.format("%.1f", TotalOvers));

                    BowlerEconomyEdit.setText(String.format("%.1f", Economy));

                }

                if (TotalOvers.equals(totalovers)) {

                    if (innings.equals("1")) {

                        Intent intent = new Intent(NormalMatchRunningScreen.this, NormalMatchRunningSelectInitialPlayersScreen.class);
                        intent.putExtra("battingteamname", bowlingteamname);
                        intent.putExtra("bowlingteamname", battingteamname);
                        intent.putExtra("totalwickets", totalwicketsstr);
                        intent.putExtra("totalovers", totaloversstr);
                        intent.putExtra("innings", "2");
                        startActivity(intent);

                    } else {

                    }
                }

            }

        });

        FiveRunsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String StrikerRunsStr = StrikerRunsEdit.getText().toString();
                String NonStrikerRunsStr = NonStrikerRunsEdit.getText().toString();
                String StrikerBallsStr = StrikerBallsEdit.getText().toString();
                String NonStrikerBallsStr = NonStrikerBallsEdit.getText().toString();
                String BowlerOversStr = BowlerOversEdit.getText().toString();
                String BowlerRunsStr = BowlerRunsEdit.getText().toString();
                String BowlerWicketsStr = BowlerWicketsEdit.getText().toString();
                String TotalRunsStr = TotalRunsEdit.getText().toString();
                String TotalWicketsStr =  TotalWicketsEdit.getText().toString();
                String TotalOversStr = TotalOversEdit.getText().toString();
                String StrikerFoursStr = StrikerFoursEdit.getText().toString();
                String NonStrikerFoursStr = NonStrikerFoursEdit.getText().toString();
                String StrikerSixesStr = StrikerSixesEdit.getText().toString();
                String NonStrikerSixesStr = NonStrikerSixesEdit.getText().toString();
                String StrikerPlayer = StrikerPlayerEdit.getText().toString();
                String NonStrikerPlayer = NonStrikerPlayerEdit.getText().toString();
                String BowlerName = BowlerNameEdit.getText().toString();
                String StrikerStrikeRate = StrikerStrikeRateEdit.getText().toString();
                String NonStrikerStrikeRateStr = NonStrikerStrikeRateEdit.getText().toString();
                String BowlerEconomyRate = BowlerEconomyEdit.getText().toString();

                Integer StrikerRuns = Integer.parseInt(StrikerRunsStr);
                Integer NonStrikerRuns = Integer.parseInt(NonStrikerRunsStr);
                Integer StrikerBalls = Integer.parseInt(StrikerBallsStr);
                Integer NonStrikerBalls = Integer.parseInt(NonStrikerBallsStr);
                Double BowlerOvers = Double.parseDouble(BowlerOversStr);
                Integer BowlerRuns = Integer.parseInt(BowlerRunsStr);
                Integer BowlerWickets = Integer.parseInt(BowlerWicketsStr);
                Integer TotalRuns = Integer.parseInt(TotalRunsStr);
                Integer TotalWickets = Integer.parseInt(TotalWicketsStr);
                Double TotalOvers = Double.parseDouble(TotalOversStr);
                Integer StrikerFours = Integer.parseInt(StrikerFoursStr);
                Integer StrikerSixes = Integer.parseInt(StrikerSixesStr);
                Integer NonStrikerFours = Integer.parseInt(NonStrikerFoursStr);
                Integer NonStrikerSixes = Integer.parseInt(NonStrikerSixesStr);
                Double NonStrikerStrikeRate = Double.parseDouble(NonStrikerStrikeRateStr);


                if (BowlerOversStr.contains(".5") || TotalOversStr.contains(".5")) {

                    if(checkBoxByes.isChecked()) {

                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 6;
                            BowlerRuns = BowlerRuns + 1;


                        }  else if(checkBoxNoBall.isChecked()) {

                            TotalRuns = TotalRuns + 6;
                            BowlerRuns = BowlerRuns + 1;

                        }else {

                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.5;
                            TotalOvers = TotalOvers + 0.5;

                            TotalRuns = TotalRuns + 5;

                        }}
                    else {


                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 6;
                            BowlerRuns = BowlerRuns + 6;

                        } else if(checkBoxNoBall.isChecked()) {

                            StrikerRuns = StrikerRuns + 5;
                            TotalRuns = TotalRuns + 6;
                            BowlerRuns = BowlerRuns + 6;

                        } else {


                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.5;
                            TotalOvers = TotalOvers + 0.5;

                            StrikerRuns = StrikerRuns + 5;
                            BowlerRuns = BowlerRuns + 5;
                            TotalRuns = TotalRuns + 5;

                        }

                    }

//                    StrikerBalls = StrikerBalls + 1;
//                    BowlerOvers = BowlerOvers + 0.5;
//                    TotalOvers = TotalOvers + 0.5;
//
//                    StrikerRuns = StrikerRuns + 5;
//                    BowlerRuns = BowlerRuns + 5;
//                    TotalRuns = TotalRuns + 5;

                    Double StrikerRunsDouble = Double.valueOf(StrikerRuns);
                    Double StrikerBallsDouble = Double.valueOf(StrikerBalls);
                    Double StrikeRate = (StrikerRunsDouble/StrikerBallsDouble) * 100;
                    Double Economy;

                    if (BowlerOvers.toString().contains(".")){

                        String[] OverString = BowlerOvers.toString().split("\\.");
                        String OversStr = OverString[0];
                        String BallsStr = OverString[1];
                        Double Balls = (Double.parseDouble(OversStr)*6)+Double.parseDouble(BallsStr);
                        Economy = (BowlerRuns/Balls)*6;
                    } else {
                        Economy = (BowlerRuns/BowlerOvers);
                    }

                    if(checkBoxWicket.isChecked() && !checkBoxNoBall.isChecked()) {
                        BowlerWickets = BowlerWickets + 1;
                        TotalWickets = TotalWickets + 1;
                        BowlerWicketsEdit.setText(BowlerWickets.toString());
                        TotalWicketsEdit.setText(TotalWickets.toString());

                        NormalMatchRunningSelectOutBatsmanPopup SelectOutBatsmanPopUpClass = new NormalMatchRunningSelectOutBatsmanPopup();

                        SelectOutBatsmanPopUpClass.OutBatsmanPopup(v,battingteamname,StrikerPlayer,NonStrikerPlayer,StrikerRuns,StrikerBalls,StrikerFours,StrikerSixes,StrikeRate,NonStrikerRuns,NonStrikerBalls,NonStrikerFours,NonStrikerSixes,NonStrikerStrikeRate,StrikerPlayerEdit, StrikerRunsEdit, StrikerBallsEdit, StrikerFoursEdit, StrikerSixesEdit, StrikerStrikeRateEdit, NonStrikerPlayerEdit, NonStrikerRunsEdit, NonStrikerBallsEdit, NonStrikerFoursEdit, NonStrikerSixesEdit,NonStrikerStrikeRateEdit,true,5, bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, 0, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);

                    } else {

                        StrikerRunsEdit.setText(StrikerRuns.toString());
                        StrikerBallsEdit.setText(StrikerBalls.toString());
                        StrikerStrikeRateEdit.setText(String.format("%.2f", StrikeRate));

                        Integer BowlerMaidens = 0;

                        NormalMatchRunningSelectNewBowlerPopup SelectNewBowlerPopUpClass = new NormalMatchRunningSelectNewBowlerPopup();

                        SelectNewBowlerPopUpClass.NewBowlerPopup(v,bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, BowlerMaidens, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);

                    }


                    TotalRunsEdit.setText(TotalRuns.toString());
                    TotalOversEdit.setText(String.format("%.1f", TotalOvers));


                } else {

                    if(checkBoxByes.isChecked()) {

                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 6;
                            BowlerRuns = BowlerRuns + 1;


                        }  else if(checkBoxNoBall.isChecked()) {

                            TotalRuns = TotalRuns + 6;
                            BowlerRuns = BowlerRuns + 1;

                        }else {

                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.1;
                            TotalOvers = TotalOvers + 0.1;

                            TotalRuns = TotalRuns + 5;

                        }}
                    else {


                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 6;
                            BowlerRuns = BowlerRuns + 6;

                        } else if(checkBoxNoBall.isChecked()) {

                            StrikerRuns = StrikerRuns + 5;
                            TotalRuns = TotalRuns + 6;
                            BowlerRuns = BowlerRuns + 6;

                        } else {


                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.1;
                            TotalOvers = TotalOvers + 0.1;

                            StrikerRuns = StrikerRuns + 5;
                            BowlerRuns = BowlerRuns + 5;
                            TotalRuns = TotalRuns + 5;

                        }

                    }

//                    StrikerBalls = StrikerBalls + 1;
//                    BowlerOvers = BowlerOvers + 0.1;
//                    TotalOvers = TotalOvers + 0.1;
//
//                    StrikerRuns = StrikerRuns + 5;
//                    BowlerRuns = BowlerRuns + 5;
//                    TotalRuns = TotalRuns + 5;

                    Double StrikerRunsDouble = Double.valueOf(StrikerRuns);
                    Double StrikerBallsDouble = Double.valueOf(StrikerBalls);
                    Double StrikeRate = (StrikerRunsDouble/StrikerBallsDouble) * 100;
                    Double Economy;

                    if (BowlerOvers.toString().contains(".")){

                        String[] OverString = BowlerOvers.toString().split("\\.");
                        String OversStr = OverString[0];
                        String BallsStr = OverString[1];
                        Double Balls = (Double.parseDouble(OversStr)*6)+Double.parseDouble(BallsStr);
                        Economy = (BowlerRuns/Balls)*6;
                    } else {
                        Economy = (BowlerRuns/BowlerOvers);
                    }

                    if(checkBoxWicket.isChecked() && !checkBoxNoBall.isChecked()) {

                        BowlerWickets = BowlerWickets + 1;
                        TotalWickets = TotalWickets + 1;
                        BowlerWicketsEdit.setText(BowlerWickets.toString());
                        TotalWicketsEdit.setText(TotalWickets.toString());

                        NormalMatchRunningSelectOutBatsmanPopup SelectOutBatsmanPopUpClass = new NormalMatchRunningSelectOutBatsmanPopup();

                        SelectOutBatsmanPopUpClass.OutBatsmanPopup(v,battingteamname,StrikerPlayer,NonStrikerPlayer,StrikerRuns,StrikerBalls,StrikerFours,StrikerSixes,StrikeRate,NonStrikerRuns,NonStrikerBalls,NonStrikerFours,NonStrikerSixes,NonStrikerStrikeRate,StrikerPlayerEdit, StrikerRunsEdit, StrikerBallsEdit, StrikerFoursEdit, StrikerSixesEdit, StrikerStrikeRateEdit, NonStrikerPlayerEdit, NonStrikerRunsEdit, NonStrikerBallsEdit, NonStrikerFoursEdit, NonStrikerSixesEdit,NonStrikerStrikeRateEdit,false,5, bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, 0, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);

                    } else {

                        StrikerPlayerEdit.setText(NonStrikerPlayer);
                        StrikerBallsEdit.setText(NonStrikerBallsStr);
                        StrikerRunsEdit.setText(NonStrikerRunsStr);
                        StrikerFoursEdit.setText(NonStrikerFoursStr);
                        StrikerSixesEdit.setText(NonStrikerSixesStr);
                        StrikerStrikeRateEdit.setText(NonStrikerStrikeRateStr);
                        NonStrikerPlayerEdit.setText(StrikerPlayer);
                        NonStrikerBallsEdit.setText(StrikerBalls.toString());
                        NonStrikerRunsEdit.setText(StrikerRuns.toString());
                        NonStrikerFoursEdit.setText(StrikerFoursStr);
                        NonStrikerSixesEdit.setText(StrikerSixesStr);
                        NonStrikerStrikeRateEdit.setText(String.format("%.2f", StrikeRate));

                    }


                    BowlerRunsEdit.setText(BowlerRuns.toString());
                    TotalRunsEdit.setText(TotalRuns.toString());
                    BowlerOversEdit.setText(String.format("%.1f", BowlerOvers));
                    TotalOversEdit.setText(String.format("%.1f", TotalOvers));

                    BowlerEconomyEdit.setText(String.format("%.1f", Economy));




                }

                if (TotalOvers.equals(totalovers)) {

                    if (innings.equals("1")) {

                        Intent intent = new Intent(NormalMatchRunningScreen.this, NormalMatchRunningSelectInitialPlayersScreen.class);
                        intent.putExtra("battingteamname", bowlingteamname);
                        intent.putExtra("bowlingteamname", battingteamname);
                        intent.putExtra("totalwickets", totalwicketsstr);
                        intent.putExtra("totalovers", totaloversstr);
                        intent.putExtra("innings", "2");
                        startActivity(intent);

                    } else {

                    }
                }

            }

        });

        SixRunsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String StrikerRunsStr = StrikerRunsEdit.getText().toString();
                String NonStrikerRunsStr = NonStrikerRunsEdit.getText().toString();
                String StrikerBallsStr = StrikerBallsEdit.getText().toString();
                String NonStrikerBallsStr = NonStrikerBallsEdit.getText().toString();
                String BowlerOversStr = BowlerOversEdit.getText().toString();
                String BowlerRunsStr = BowlerRunsEdit.getText().toString();
                String BowlerWicketsStr = BowlerWicketsEdit.getText().toString();
                String TotalRunsStr = TotalRunsEdit.getText().toString();
                String TotalWicketsStr =  TotalWicketsEdit.getText().toString();
                String TotalOversStr = TotalOversEdit.getText().toString();
                String StrikerFoursStr = StrikerFoursEdit.getText().toString();
                String NonStrikerFoursStr = NonStrikerFoursEdit.getText().toString();
                String StrikerSixesStr = StrikerSixesEdit.getText().toString();
                String NonStrikerSixesStr = NonStrikerSixesEdit.getText().toString();
                String StrikerPlayer = StrikerPlayerEdit.getText().toString();
                String NonStrikerPlayer = NonStrikerPlayerEdit.getText().toString();
                String BowlerName = BowlerNameEdit.getText().toString();
                String StrikerStrikeRate = StrikerStrikeRateEdit.getText().toString();
                String NonStrikerStrikeRateStr = NonStrikerStrikeRateEdit.getText().toString();
                String BowlerEconomyRate = BowlerEconomyEdit.getText().toString();

                NormalMatchRunningScreenModel normalMatchRunningScreenModel = new NormalMatchRunningScreenModel();


                Integer StrikerRuns = Integer.parseInt(StrikerRunsStr);
                Integer NonStrikerRuns = Integer.parseInt(NonStrikerRunsStr);
                Integer StrikerBalls = Integer.parseInt(StrikerBallsStr);
                Integer NonStrikerBalls = Integer.parseInt(NonStrikerBallsStr);
                Double BowlerOvers = Double.parseDouble(BowlerOversStr);
                Integer BowlerRuns = Integer.parseInt(BowlerRunsStr);
                Integer BowlerWickets = Integer.parseInt(BowlerWicketsStr);
                Integer TotalRuns = Integer.parseInt(TotalRunsStr);
                Integer TotalWickets = Integer.parseInt(TotalWicketsStr);
                Double TotalOvers = Double.parseDouble(TotalOversStr);
                Integer StrikerSixes = Integer.parseInt(StrikerSixesStr);
                Integer StrikerFours = Integer.parseInt(StrikerFoursStr);
                Integer NonStrikerFours = Integer.parseInt(NonStrikerFoursStr);
                Integer NonStrikerSixes = Integer.parseInt(NonStrikerSixesStr);
                Double NonStrikerStrikeRate = Double.parseDouble(NonStrikerStrikeRateStr);

                if (BowlerOversStr.contains(".5") || TotalOversStr.contains(".5")) {

                    if(checkBoxByes.isChecked()) {

                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 7;
                            BowlerRuns = BowlerRuns + 1;


                        }  else if(checkBoxNoBall.isChecked()) {

                            TotalRuns = TotalRuns + 7;
                            BowlerRuns = BowlerRuns + 1;

                        }else {

                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.5;
                            TotalOvers = TotalOvers + 0.5;

                            TotalRuns = TotalRuns + 6;

                        }}
                    else {


                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 7;
                            BowlerRuns = BowlerRuns + 7;

                        } else if(checkBoxNoBall.isChecked()) {

                            StrikerRuns = StrikerRuns + 6;
                            TotalRuns = TotalRuns + 7;
                            BowlerRuns = BowlerRuns + 7;
                            StrikerSixes = StrikerSixes + 1;


                        } else {


                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.5;
                            TotalOvers = TotalOvers + 0.5;
                            StrikerSixes = StrikerSixes + 1;

                            StrikerRuns = StrikerRuns + 6;
                            BowlerRuns = BowlerRuns + 6;
                            TotalRuns = TotalRuns + 6;

                        }

                    }

//                    StrikerBalls = StrikerBalls + 1;
//                    BowlerOvers = BowlerOvers + 0.5;
//                    TotalOvers = TotalOvers + 0.5;
//
//                    StrikerRuns = StrikerRuns + 6;
//                    BowlerRuns = BowlerRuns + 6;
//                    TotalRuns = TotalRuns + 6;
//                    StrikerSixes = StrikerSixes + 1;

                    Double StrikerRunsDouble = Double.valueOf(StrikerRuns);
                    Double StrikerBallsDouble = Double.valueOf(StrikerBalls);
                    Double StrikeRate = (StrikerRunsDouble/StrikerBallsDouble) * 100;
                    Double Economy;

                    if (BowlerOvers.toString().contains(".")){

                        String[] OverString = BowlerOvers.toString().split("\\.");
                        String OversStr = OverString[0];
                        String BallsStr = OverString[1];
                        Double Balls = (Double.parseDouble(OversStr)*6)+Double.parseDouble(BallsStr);
                        Economy = (BowlerRuns/Balls)*6;
                    } else {
                        Economy = (BowlerRuns/BowlerOvers);
                    }

                    if(checkBoxWicket.isChecked() && !checkBoxNoBall.isChecked()) {

                        BowlerWickets = BowlerWickets + 1;
                        TotalWickets = TotalWickets + 1;
                        BowlerWicketsEdit.setText(BowlerWickets.toString());
                        TotalWicketsEdit.setText(TotalWickets.toString());

                        NormalMatchRunningSelectOutBatsmanPopup SelectOutBatsmanPopUpClass = new NormalMatchRunningSelectOutBatsmanPopup();

                        SelectOutBatsmanPopUpClass.OutBatsmanPopup(v,battingteamname,StrikerPlayer,NonStrikerPlayer,StrikerRuns,StrikerBalls,StrikerFours,StrikerSixes,StrikeRate,NonStrikerRuns,NonStrikerBalls,NonStrikerFours,NonStrikerSixes,NonStrikerStrikeRate,StrikerPlayerEdit, StrikerRunsEdit, StrikerBallsEdit, StrikerFoursEdit, StrikerSixesEdit, StrikerStrikeRateEdit, NonStrikerPlayerEdit, NonStrikerRunsEdit, NonStrikerBallsEdit, NonStrikerFoursEdit, NonStrikerSixesEdit,NonStrikerStrikeRateEdit,true,6, bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, 0, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);

                    } else {

                        StrikerPlayerEdit.setText(NonStrikerPlayer);
                        StrikerBallsEdit.setText(NonStrikerBallsStr);
                        StrikerRunsEdit.setText(NonStrikerRunsStr);
                        StrikerFoursEdit.setText(NonStrikerFoursStr);
                        StrikerSixesEdit.setText(NonStrikerSixesStr);
                        StrikerStrikeRateEdit.setText(NonStrikerStrikeRateStr);
                        NonStrikerPlayerEdit.setText(StrikerPlayer);
                        NonStrikerBallsEdit.setText(StrikerBalls.toString());
                        NonStrikerRunsEdit.setText(StrikerRuns.toString());
                        NonStrikerFoursEdit.setText(StrikerFoursStr);
                        NonStrikerSixesEdit.setText(StrikerSixes.toString());
                        NonStrikerStrikeRateEdit.setText(String.format("%.2f", StrikeRate));

                        Integer BowlerMaidens = 0;

                        NormalMatchRunningSelectNewBowlerPopup SelectNewBowlerPopUpClass = new NormalMatchRunningSelectNewBowlerPopup();

                        SelectNewBowlerPopUpClass.NewBowlerPopup(v,bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, BowlerMaidens, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);


                    }


                    TotalRunsEdit.setText(TotalRuns.toString());
                    TotalOversEdit.setText(String.format("%.1f", TotalOvers));




                } else {

                    if(checkBoxByes.isChecked()) {

                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 7;
                            BowlerRuns = BowlerRuns + 1;


                        }  else if(checkBoxNoBall.isChecked()) {

                            TotalRuns = TotalRuns + 7;
                            BowlerRuns = BowlerRuns + 1;

                        }else {

                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.1;
                            TotalOvers = TotalOvers + 0.1;

                            TotalRuns = TotalRuns + 6;

                        }}
                    else {


                        if(checkBoxWide.isChecked()) {

                            TotalRuns = TotalRuns + 7;
                            BowlerRuns = BowlerRuns + 7;

                        } else if(checkBoxNoBall.isChecked()) {

                            StrikerRuns = StrikerRuns + 6;
                            TotalRuns = TotalRuns + 7;
                            BowlerRuns = BowlerRuns + 7;
                            StrikerSixes = StrikerSixes + 1;

                        } else {


                            StrikerBalls = StrikerBalls + 1;
                            BowlerOvers = BowlerOvers + 0.1;
                            TotalOvers = TotalOvers + 0.1;
                            StrikerSixes = StrikerSixes + 1;

                            StrikerRuns = StrikerRuns + 6;
                            BowlerRuns = BowlerRuns + 6;
                            TotalRuns = TotalRuns + 6;

                        }

                    }

//                    StrikerBalls = StrikerBalls + 1;
//                    BowlerOvers = BowlerOvers + 0.1;
//                    TotalOvers = TotalOvers + 0.1;
//
//                    StrikerRuns = StrikerRuns + 6;
//                    BowlerRuns = BowlerRuns + 6;
//                    TotalRuns = TotalRuns + 6;
//                    StrikerSixes = StrikerSixes + 1;

                    Double StrikerRunsDouble = Double.valueOf(StrikerRuns);
                    Double StrikerBallsDouble = Double.valueOf(StrikerBalls);
                    Double StrikeRate = (StrikerRunsDouble/StrikerBallsDouble) * 100;
                    Double Economy;

                    if (BowlerOvers.toString().contains(".")){

                        String[] OverString = BowlerOvers.toString().split("\\.");
                        String OversStr = OverString[0];
                        String BallsStr = OverString[1];
                        Double Balls = (Double.parseDouble(OversStr)*6)+Double.parseDouble(BallsStr);
                        Economy = (BowlerRuns/Balls)*6;
                    } else {
                        Economy = (BowlerRuns/BowlerOvers);
                    }

                    if(checkBoxWicket.isChecked() && !checkBoxNoBall.isChecked()) {

                        BowlerWickets = BowlerWickets + 1;
                        TotalWickets = TotalWickets + 1;
                        BowlerWicketsEdit.setText(BowlerWickets.toString());
                        TotalWicketsEdit.setText(TotalWickets.toString());

                        NormalMatchRunningSelectOutBatsmanPopup SelectOutBatsmanPopUpClass = new NormalMatchRunningSelectOutBatsmanPopup();

                        SelectOutBatsmanPopUpClass.OutBatsmanPopup(v,battingteamname,StrikerPlayer,NonStrikerPlayer,StrikerRuns,StrikerBalls,StrikerFours,StrikerSixes,StrikeRate,NonStrikerRuns,NonStrikerBalls,NonStrikerFours,NonStrikerSixes,NonStrikerStrikeRate,StrikerPlayerEdit, StrikerRunsEdit, StrikerBallsEdit, StrikerFoursEdit, StrikerSixesEdit, StrikerStrikeRateEdit, NonStrikerPlayerEdit, NonStrikerRunsEdit, NonStrikerBallsEdit, NonStrikerFoursEdit, NonStrikerSixesEdit,NonStrikerStrikeRateEdit,false,6, bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, 0, Economy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);

                    } else {

                        StrikerRunsEdit.setText(StrikerRuns.toString());
                        StrikerBallsEdit.setText(StrikerBalls.toString());
                        StrikerSixesEdit.setText(StrikerSixes.toString());
                        StrikerStrikeRateEdit.setText(String.format("%.2f", StrikeRate));

                    }


                    BowlerRunsEdit.setText(BowlerRuns.toString());
                    TotalRunsEdit.setText(TotalRuns.toString());
                    BowlerOversEdit.setText(String.format("%.1f", BowlerOvers));
                    TotalOversEdit.setText(String.format("%.1f", TotalOvers));
                    BowlerEconomyEdit.setText(String.format("%.1f", Economy));
                }

                if (TotalOvers.equals(totalovers)) {

                    if (innings.equals("1")) {

                        Intent intent = new Intent(NormalMatchRunningScreen.this, NormalMatchRunningSelectInitialPlayersScreen.class);
                        intent.putExtra("battingteamname", bowlingteamname);
                        intent.putExtra("bowlingteamname", battingteamname);
                        intent.putExtra("totalwickets", totalwicketsstr);
                        intent.putExtra("totalovers", totaloversstr);
                        intent.putExtra("innings", "2");
                        startActivity(intent);

                    } else {

                    }
                }

            }

        });

        ScorecardBtn.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    Intent intent = new Intent(NormalMatchRunningScreen.this, FirstInningsScorecardScreen.class);

                    // start the activity connect to the specified class
                    startActivity(intent);



                }
            });
    }

}
