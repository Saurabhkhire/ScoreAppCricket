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

public class NormalMatchRunningSelectOutBatsmanPopup {

    private Button ZeroRunsBtn, OneRunsBtn, TwoRunsBtn, ThreeRunsBtn, FourRunsBtn, FiveRunsBtn, SixRunsBtn, StrikerPlayerNextBtn, NonStrikerPlayerNextBtn, BowlerNextBtn ;
    private PlayerDatabase playerDatabase;
    private TeamDatabase teamDatabase;
    private QuickMatchDatabase quickMatchDatabase;
    private Spinner OutBatsmanList,WicketTypeList;
    private RelativeLayout relativeLayout;


    public void OutBatsmanPopup(final View view, String battingteamname, String StrikerBatsman, String NonStrikerBatsman, Integer StrikerRuns, Integer StrikerBalls, Integer StrikerFours, Integer StrikerSixes, Double StrikerStrikeRate, Integer NonStrikerRuns, Integer NonStrikerBalls, Integer NonStrikerFours, Integer NonStrikerSixes, Double NonStrikerStrikeRate, TextView StrikerPlayerEdit,TextView StrikerRunsEdit,TextView StrikerBallsEdit,TextView StrikerFoursEdit,TextView StrikerSixesEdit,TextView StrikerStrikeRateEdit,TextView NonStrikerPlayerEdit,TextView NonStrikerRunsEdit,TextView NonStrikerBallsEdit,TextView NonStrikerFoursEdit,TextView NonStrikerSixesEdit,TextView NonStrikerStrikeRateEdit,boolean LastBallofOver,Integer SelectedRuns, String bowlingteamname, String BowlerName, Integer BowlerRuns, Double BowlerOvers, Integer BowlerWickets, Integer BowlerMaidens, Double BowlerEconomy,TextView BowlerNameEdit, TextView BowlerRunsEdit, TextView BowlerOversEdit, TextView BowlerWicketsEdit, TextView BowlerMaidensEdit, TextView BowlerEconomyEdit) {

        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View SelectStrikerBatsmanPopupView = inflater.inflate(R.layout.normal_match_running_select_out_batsman_popup, relativeLayout);


        // create the popup window
        int width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        int height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        //boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow SelectOutBatsmanPopup =new PopupWindow(SelectStrikerBatsmanPopupView, width, height);

        //     SelectStrikerBatsmanPopupWindow.setBackgroundDrawable(null);
        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        SelectOutBatsmanPopup.showAtLocation(view,Gravity.CENTER,0,0);


        OutBatsmanList = SelectStrikerBatsmanPopupView.findViewById(R.id.OutBatsmanList);
        WicketTypeList = SelectStrikerBatsmanPopupView.findViewById(R.id.WicketTypeList);
        StrikerPlayerNextBtn = SelectStrikerBatsmanPopupView.findViewById(R.id.next_button);

        quickMatchDatabase = new QuickMatchDatabase(view.getContext());
        teamDatabase =new TeamDatabase(view.getContext());

        String[] OutBatsmanArray = {StrikerBatsman, NonStrikerBatsman};
        ArrayAdapter<String> OutBatsmanAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, OutBatsmanArray);
        OutBatsmanList.setAdapter(OutBatsmanAdapter);

        String[] WicketTypeArray = {"Run Out","Caught","Bowled","LBW","Stumped","Hit Wicket"};
        ArrayAdapter<String> WicketTypeAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, WicketTypeArray);
        WicketTypeList.setAdapter(WicketTypeAdapter);

        StrikerPlayerNextBtn.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){

                String BatsmanType = null;
                String OutBatsman = OutBatsmanList.getSelectedItem().toString();
                String WicketType = WicketTypeList.getSelectedItem().toString();

                if(OutBatsman.equals(StrikerBatsman)) {

                    quickMatchDatabase.NormalMatchTeamOneBatsmanTemp(StrikerBatsman, WicketType, StrikerRuns, StrikerBalls, StrikerFours, StrikerSixes, StrikerStrikeRate);

                    BatsmanType = "Striker";

                    if (!LastBallofOver) {

                        StrikerPlayerEdit.setText("");
                        StrikerBallsEdit.setText("0");
                        StrikerRunsEdit.setText("0");
                        StrikerFoursEdit.setText("0");
                        StrikerSixesEdit.setText("0");
                        StrikerStrikeRateEdit.setText("0");
                        NonStrikerPlayerEdit.setText(NonStrikerBatsman);
                        NonStrikerRunsEdit.setText(NonStrikerRuns.toString());
                        NonStrikerFoursEdit.setText(NonStrikerFours.toString());
                        NonStrikerSixesEdit.setText(NonStrikerSixes.toString());
                        NonStrikerBallsEdit.setText(NonStrikerBalls.toString());
                        NonStrikerStrikeRateEdit.setText(String.format("%.2f", NonStrikerStrikeRate));

                    } else {

                        StrikerPlayerEdit.setText(NonStrikerBatsman);
                        StrikerBallsEdit.setText(NonStrikerBalls.toString());
                        StrikerRunsEdit.setText(NonStrikerRuns.toString());
                        StrikerFoursEdit.setText(NonStrikerFours.toString());
                        StrikerSixesEdit.setText(NonStrikerSixes.toString());
                        StrikerStrikeRateEdit.setText(String.format("%.2f", NonStrikerStrikeRate));
                        NonStrikerPlayerEdit.setText("");
                        NonStrikerRunsEdit.setText("0");
                        NonStrikerFoursEdit.setText("0");
                        NonStrikerSixesEdit.setText("0");
                        NonStrikerBallsEdit.setText("0");
                        NonStrikerStrikeRateEdit.setText("0");

                    }

                } else {


                    quickMatchDatabase.NormalMatchTeamOneBatsmanTemp(NonStrikerBatsman, WicketType, NonStrikerRuns, NonStrikerBalls, NonStrikerFours, NonStrikerSixes, NonStrikerStrikeRate);

                    BatsmanType = "Non Striker";

                    if (!LastBallofOver) {

                        StrikerPlayerEdit.setText(StrikerBatsman);
                        StrikerBallsEdit.setText(StrikerBalls.toString());
                        StrikerRunsEdit.setText(StrikerRuns.toString());
                        StrikerFoursEdit.setText(StrikerFours.toString());
                        StrikerSixesEdit.setText(StrikerSixes.toString());
                        StrikerStrikeRateEdit.setText(String.format("%.2f", StrikerStrikeRate));
                        NonStrikerPlayerEdit.setText("");
                        NonStrikerRunsEdit.setText("0");
                        NonStrikerFoursEdit.setText("0");
                        NonStrikerSixesEdit.setText("0");
                        NonStrikerBallsEdit.setText("0");
                        NonStrikerStrikeRateEdit.setText("0");

                    } else {

                        StrikerPlayerEdit.setText("");
                        StrikerBallsEdit.setText("0");
                        StrikerRunsEdit.setText("0");
                        StrikerFoursEdit.setText("0");
                        StrikerSixesEdit.setText("0");
                        StrikerStrikeRateEdit.setText("0");
                        NonStrikerPlayerEdit.setText(StrikerBatsman);
                        NonStrikerRunsEdit.setText(StrikerRuns.toString());
                        NonStrikerFoursEdit.setText(StrikerFours.toString());
                        NonStrikerSixesEdit.setText(StrikerSixes.toString());
                        NonStrikerBallsEdit.setText(StrikerBalls.toString());
                        NonStrikerStrikeRateEdit.setText(String.format("%.2f", StrikerStrikeRate));

                    }

                }
          
                SelectOutBatsmanPopup.dismiss();

                String NonOutBatsman = null;

                if (OutBatsman.equals(StrikerBatsman)) {

                    NonOutBatsman = NonStrikerBatsman;

                } else {

                    NonOutBatsman = StrikerBatsman;


                }

                NormalMatchRunningSelectNewBatsmanPopup SelectNewBatsmanPopUpClass = new NormalMatchRunningSelectNewBatsmanPopup();

                SelectNewBatsmanPopUpClass.NewBatsmanPopup(v,battingteamname,BatsmanType,StrikerRuns,StrikerBalls,StrikerFours,StrikerSixes,StrikerStrikeRate,NonStrikerRuns,NonStrikerBalls,NonStrikerFours,NonStrikerSixes,NonStrikerStrikeRate,StrikerPlayerEdit, StrikerRunsEdit, StrikerBallsEdit, StrikerFoursEdit, StrikerSixesEdit, StrikerStrikeRateEdit, NonStrikerPlayerEdit, NonStrikerRunsEdit, NonStrikerBallsEdit, NonStrikerFoursEdit, NonStrikerSixesEdit,NonStrikerStrikeRateEdit,LastBallofOver,0, NonOutBatsman, bowlingteamname, BowlerName, BowlerRuns, BowlerOvers, BowlerWickets, BowlerMaidens, BowlerEconomy,BowlerNameEdit, BowlerRunsEdit, BowlerOversEdit, BowlerWicketsEdit, BowlerMaidensEdit, BowlerEconomyEdit);


            }

        });

    }
}
