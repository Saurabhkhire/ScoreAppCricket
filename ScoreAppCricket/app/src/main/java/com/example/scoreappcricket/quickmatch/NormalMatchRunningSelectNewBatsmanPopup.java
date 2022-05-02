package com.example.scoreappcricket.quickmatch;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.PlayerDatabase;
import com.example.scoreappcricket.db.QuickMatchDatabase;
import com.example.scoreappcricket.db.TeamDatabase;
import com.example.scoreappcricket.model.NormalMatchRunningScreenModel;

import java.util.ArrayList;

public class NormalMatchRunningSelectNewBatsmanPopup {

    private Button ZeroRunsBtn, OneRunsBtn, TwoRunsBtn, ThreeRunsBtn, FourRunsBtn, FiveRunsBtn, SixRunsBtn, NewBatsmanNextBtn, NonStrikerPlayerNextBtn, BowlerNextBtn ;
    private QuickMatchDatabase quickMatchDatabase;
    private TeamDatabase teamDatabase;
    private Spinner NewBatsmanList,NonStrikerPlayerList,BowlerNamelist;
    private RelativeLayout relativeLayout;

    public void NewBatsmanPopup(final View view,String battingteamname, String BatsmanType, Integer StrikerRuns, Integer StrikerBalls, Integer StrikerFours, Integer StrikerSixes, Double StrikerStrikeRate, Integer NonStrikerRuns, Integer NonStrikerBalls, Integer NonStrikerFours, Integer NonStrikerSixes, Double NonStrikerStrikeRate, TextView StrikerPlayerEdit, TextView StrikerRunsEdit, TextView StrikerBallsEdit, TextView StrikerFoursEdit, TextView StrikerSixesEdit, TextView StrikerStrikeRateEdit, TextView NonStrikerPlayerEdit, TextView NonStrikerRunsEdit, TextView NonStrikerBallsEdit, TextView NonStrikerFoursEdit, TextView NonStrikerSixesEdit, TextView NonStrikerStrikeRateEdit, boolean LastBallofOver, Integer SelectedRuns, String NonOutBatsman, String bowlingteamname, String BowlerName, Integer BowlerRuns, Double BowlerOvers, Integer BowlerWickets, Integer BowlerMaidens, Double BowlerEconomy,TextView BowlerNameEdit, TextView BowlerRunsEdit, TextView BowlerOversEdit, TextView BowlerWicketsEdit, TextView BowlerMaidensEdit, TextView BowlerEconomyEdit) {


    LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View SelectStrikerBatsmanPopupView = inflater.inflate(R.layout.normal_match_running_select_new_batsman_popup, relativeLayout);


    // create the popup window
    int width = RelativeLayout.LayoutParams.WRAP_CONTENT;
    int height = RelativeLayout.LayoutParams.WRAP_CONTENT;
    boolean focusable = true; // lets taps outside the popup also dismiss it
    final PopupWindow SelectNewBatsmanPopupWindow =new PopupWindow(SelectStrikerBatsmanPopupView, width, height, focusable);

   //     SelectStrikerBatsmanPopupWindow.setBackgroundDrawable(null);
    // show the popup window
    // which view you pass in doesn't matter, it is only used for the window tolken
        SelectNewBatsmanPopupWindow.showAtLocation(view,Gravity.CENTER,0,0);


        NewBatsmanList = SelectStrikerBatsmanPopupView.findViewById(R.id.NewBatsmanList);

        NewBatsmanNextBtn = SelectStrikerBatsmanPopupView.findViewById(R.id.next_button);

        quickMatchDatabase =new QuickMatchDatabase(view.getContext());

        ArrayList<String> outBatsman  = quickMatchDatabase.getOutBatsman();

        teamDatabase =new TeamDatabase(view.getContext());

        ArrayList<String> StrikerPlayers =  teamDatabase.readPlayersfromTeam(battingteamname);

        StrikerPlayers.removeAll(outBatsman);
        StrikerPlayers.remove(NonOutBatsman);

        String[] StrikerPlayersArray = StrikerPlayers.toArray(new String[0]);
        ArrayAdapter<String> StrikerPlayerAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, StrikerPlayersArray);
        NewBatsmanList.setAdapter(StrikerPlayerAdapter);

        NewBatsmanNextBtn.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){

            String NewBatsman = NewBatsmanList.getSelectedItem().toString();

            ArrayList<String> rdBatsman  = quickMatchDatabase.getRetiredHurtBatsman(NewBatsman);

            if(BatsmanType.equals("Striker")) {

                if (!LastBallofOver) {

                    if (rdBatsman.size() != 0) {

                        StrikerPlayerEdit.setText(rdBatsman.get(0));
                        StrikerRunsEdit.setText(rdBatsman.get(1));
                        StrikerBallsEdit.setText(rdBatsman.get(2));
                        StrikerFoursEdit.setText(rdBatsman.get(3));
                        StrikerSixesEdit.setText(rdBatsman.get(4));
                        StrikerStrikeRateEdit.setText(rdBatsman.get(5));

                    } else {
                        StrikerPlayerEdit.setText(NewBatsman);
                    }


                } else {

                if (rdBatsman.size() != 0) {

                    NonStrikerPlayerEdit.setText(rdBatsman.get(0));
                    NonStrikerRunsEdit.setText(rdBatsman.get(1));
                    NonStrikerBallsEdit.setText(rdBatsman.get(2));
                    NonStrikerFoursEdit.setText(rdBatsman.get(3));
                    NonStrikerSixesEdit.setText(rdBatsman.get(4));
                    NonStrikerStrikeRateEdit.setText(rdBatsman.get(5));

                } else {
                    NonStrikerPlayerEdit.setText(NewBatsman);
            }


                }

            } else {

                if (!LastBallofOver) {

                    if (rdBatsman.size() != 0) {

                        NonStrikerPlayerEdit.setText(rdBatsman.get(0));
                        NonStrikerRunsEdit.setText(rdBatsman.get(1));
                        NonStrikerBallsEdit.setText(rdBatsman.get(2));
                        NonStrikerFoursEdit.setText(rdBatsman.get(3));
                        NonStrikerSixesEdit.setText(rdBatsman.get(4));
                        NonStrikerStrikeRateEdit.setText(rdBatsman.get(5));

                    } else {
                        NonStrikerPlayerEdit.setText(NewBatsman);
                }

                } else {

                    if (rdBatsman.size() != 0) {

                        StrikerPlayerEdit.setText(rdBatsman.get(0));
                        StrikerRunsEdit.setText(rdBatsman.get(1));
                        StrikerBallsEdit.setText(rdBatsman.get(2));
                        StrikerFoursEdit.setText(rdBatsman.get(3));
                        StrikerSixesEdit.setText(rdBatsman.get(4));
                        StrikerStrikeRateEdit.setText(rdBatsman.get(5));

                    } else {

                        StrikerPlayerEdit.setText(NewBatsman);
                    }



                }

            }


            SelectNewBatsmanPopupWindow.dismiss();

            if (LastBallofOver) {
                Integer BowlerMaidens = 0;

                NormalMatchRunningSelectNewBowlerPopup SelectNewBowlerPopUpClass = new NormalMatchRunningSelectNewBowlerPopup();

                SelectNewBowlerPopUpClass.NewBowlerPopup(v, bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, BowlerMaidens, BowlerEconomy, BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);
            }

    }

    });

}
}
