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

import java.util.ArrayList;

public class NormalMatchRunningSelectNewBowlerPopup {

    private Button ZeroRunsBtn, OneRunsBtn, TwoRunsBtn, ThreeRunsBtn, FourRunsBtn, FiveRunsBtn, SixRunsBtn, NewBowlerNextBtn, NonStrikerPlayerNextBtn, BowlerNextBtn ;
    private QuickMatchDatabase quickMatchDatabase;
    private TeamDatabase teamDatabase;
    private Spinner NewBowlerList,NonStrikerPlayerList,BowlerNamelist;
    private RelativeLayout relativeLayout;

    public void NewBowlerPopup(final View view,String bowlingteamname, String BowlerName, Integer BowlerRuns, Double BowlerOvers, Integer BowlerWickets, Integer BowlerMaidens, Double BowlerEconomy,TextView BowlerNameEdit, TextView BowlerRunsEdit, TextView BowlerOversEdit, TextView BowlerWicketsEdit, TextView BowlerMaidensEdit, TextView BowlerEconomyEdit) {

    LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View SelectStrikerBatsmanPopupView = inflater.inflate(R.layout.normal_match_running_select_new_bowler_popup, relativeLayout);

    // create the popup window
    int width = RelativeLayout.LayoutParams.WRAP_CONTENT;
    int height = RelativeLayout.LayoutParams.WRAP_CONTENT;
    boolean focusable = true; // lets taps outside the popup also dismiss it
    final PopupWindow SelectNewBowlerPopupWindow =new PopupWindow(SelectStrikerBatsmanPopupView, width, height, focusable);

   //     SelectStrikerBatsmanPopupWindow.setBackgroundDrawable(null);
    // show the popup window
    // which view you pass in doesn't matter, it is only used for the window tolken
        SelectNewBowlerPopupWindow.showAtLocation(view,Gravity.CENTER,0,0);


        NewBowlerList = SelectStrikerBatsmanPopupView.findViewById(R.id.NewBowlersList);

        NewBowlerNextBtn = SelectStrikerBatsmanPopupView.findViewById(R.id.next_button);

        quickMatchDatabase = new QuickMatchDatabase(view.getContext());

        quickMatchDatabase.NormalMatchTeamTwoBowlersTemp(BowlerName, BowlerOvers, BowlerRuns, BowlerWickets, BowlerMaidens, BowlerEconomy);

        teamDatabase =new TeamDatabase(view.getContext());

    String[] NewBowlerArray = teamDatabase.readPlayersfromTeam(bowlingteamname).toArray(new String[0]);
    ArrayAdapter<String> NewBowlerArrayAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, NewBowlerArray);
        NewBowlerList.setAdapter(NewBowlerArrayAdapter);

        NewBowlerNextBtn.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){

            String NewBowler = NewBowlerList.getSelectedItem().toString();

            ArrayList<String> newBowlerStats = quickMatchDatabase.getNewBowler(NewBowler);

            if (newBowlerStats.size()==0) {

                BowlerNameEdit.setText(NewBowler);
                BowlerRunsEdit.setText("0");
                BowlerOversEdit.setText("0.0");
                BowlerWicketsEdit.setText("0");
                BowlerMaidensEdit.setText("0");
                BowlerEconomyEdit.setText("0.0");

            } else {

                BowlerNameEdit.setText(newBowlerStats.get(0));
                BowlerOversEdit.setText(newBowlerStats.get(1));
                BowlerRunsEdit.setText(newBowlerStats.get(2));
                BowlerWicketsEdit.setText(newBowlerStats.get(3));
                BowlerMaidensEdit.setText(newBowlerStats.get(4));
                BowlerEconomyEdit.setText(newBowlerStats.get(5));

            }


            SelectNewBowlerPopupWindow.dismiss();

    }

    });

}
}
