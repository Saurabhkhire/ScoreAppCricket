package com.example.scoreappcricket.quickmatch;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.teams.PlayersListforTeamScreen;

import java.util.ArrayList;

public class FirstInningsScorecardAdapter extends BaseAdapter {


    private Context mContext;
    private ArrayList<String> mA;
    private ArrayList<String> mB;
    private ArrayList<String> mC;

    public FirstInningsScorecardAdapter(Context context, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c) {
        super();
        mContext = context;
        mA = a;
        mB = b;
        mC = c;
    }

    public int getCount() {
        // return the number of records
        return mA.size();
    }


    // getView method is called for each item of ListView
    public View getView(int position, View view, ViewGroup parent) {
        // inflate the layout for each item of listView
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.normal_match_first_innings_scorecard_screen, parent, false);

        // get the reference of textView and button

        //batsmanFours.setText(mArrSchoolData.get(position));
       // batsmanSixes.setText(mArrSchoolData.get(position));
       // batsmanSr.setText(mArrSchoolData.get(position));
        //batsmanWicketType.setText(mArrSchoolData.get(position));

        return view;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }}


