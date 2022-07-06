package com.example.scoreappcricket.quickmatch;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.common.CommonLogic;
import com.example.scoreappcricket.db.PlayerDatabase;
import com.example.scoreappcricket.db.QuickMatchDatabase;
import com.example.scoreappcricket.model.BatsmanScorecardList;
import com.example.scoreappcricket.model.BowlersScorecardList;
import com.example.scoreappcricket.players.PlayersListAdapter;
import com.example.scoreappcricket.players.PlayersListScreen;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstInningsScorecardScreen extends AppCompatActivity {

    private TextView PlayerNameTxt, PlayerCountryTxt, PlayerRoleTxt, BattingStyleTxt, BowlingStyleTxt, BattingPositionTxt, BowlerNameTxt, BowlerOversTxt, BowlerRunsTxt, BowlerWicketsTxt, BowlersEconomyTxt, BowlersMaidensTxt;
    private ListView mListview;
    private ArrayList<String> mArrData;
    private FirstInningsScorecardAdapter mAdapter;
    private QuickMatchDatabase quickMatchDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_match_first_innings_scorecard_screen);

        PlayerNameTxt = findViewById(R.id.PlayerNameTxt);
        PlayerCountryTxt = findViewById(R.id.PlayerCountryTxt);
        PlayerRoleTxt = findViewById(R.id.PlayerRoleTxt);
        BattingStyleTxt = findViewById(R.id.BattingStyleTxt);
        BowlingStyleTxt = findViewById(R.id.BowlingStyleTxt);
        BattingPositionTxt = findViewById(R.id.BattingPositionTxt);
        BowlerNameTxt = findViewById(R.id.PlayerNameTxt);
        BowlerOversTxt = findViewById(R.id.PlayerCountryTxt);
        BowlerRunsTxt = findViewById(R.id.PlayerRoleTxt);
        BowlerWicketsTxt = findViewById(R.id.BattingStyleTxt);
        BowlersEconomyTxt = findViewById(R.id.BowlingStyleTxt);
        BowlersMaidensTxt = findViewById(R.id.BattingPositionTxt);

        //mListview = findViewById(R.id.BatsmanNamesList);
        quickMatchDatabase = new QuickMatchDatabase(FirstInningsScorecardScreen.this);

        BatsmanScorecardList batsmanScorecardList = quickMatchDatabase.getBattingScorecard();
        BowlersScorecardList bowlersScorecardList = quickMatchDatabase.getBowlingScorecard();
        TableLayout prices = (TableLayout) findViewById(R.id.tableL);
        CommonLogic.batsmanScoreTable(PlayerNameTxt, PlayerCountryTxt, PlayerRoleTxt, BattingStyleTxt, BowlingStyleTxt, BattingPositionTxt, batsmanScorecardList.getBatsmanNamesList(), batsmanScorecardList.getBatsmanRunsList(), batsmanScorecardList.getBatsmanBallsList(), batsmanScorecardList.getBatsmanFoursList(), batsmanScorecardList.getBatsmanSixesList(), batsmanScorecardList.getBatsmanStrikeRateList(), batsmanScorecardList.getWicketTypeList(), prices, FirstInningsScorecardScreen.this, "normal");
        TableLayout pricess = (TableLayout) findViewById(R.id.tableM);
        CommonLogic.bowlersScoreTable(BowlerNameTxt, BowlerOversTxt, BowlerRunsTxt, BowlerWicketsTxt, BowlersEconomyTxt, BowlersMaidensTxt, bowlersScorecardList.getBowlersNamesList(), bowlersScorecardList.getBowlerOversList(), bowlersScorecardList.getBowlersNamesList(), bowlersScorecardList.getBowlersWicketsList(), bowlersScorecardList.getBowlersMaidensList(), bowlersScorecardList.getBowlersEconomyList(), pricess, FirstInningsScorecardScreen.this, "normal");



        //support_simple_spinner_dropdown_item
    }
}