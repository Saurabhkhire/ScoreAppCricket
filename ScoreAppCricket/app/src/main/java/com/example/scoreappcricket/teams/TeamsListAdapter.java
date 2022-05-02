package com.example.scoreappcricket.teams;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.scoreappcricket.R;

import java.util.ArrayList;

public class TeamsListAdapter extends BaseAdapter {


    private Context mContext;
    private ArrayList<String> mArrSchoolData;

    public TeamsListAdapter(Context context, ArrayList arrSchoolData) {
        super();
        mContext = context;
        mArrSchoolData = arrSchoolData;
    }

    public int getCount() {
        // return the number of records
        return mArrSchoolData.size();
    }

    // getView method is called for each item of ListView
    public View getView(int position, View view, ViewGroup parent) {
        // inflate the layout for each item of listView
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.teams_list_screen, parent, false);


        // get the reference of textView and button
        TextView txtSchoolTitle = (TextView) view.findViewById(R.id.txtTeamsList);
        Button btnAction = (Button) view.findViewById(R.id.PlayersTeambtn);

        // Set the title and button name
        txtSchoolTitle.setText(mArrSchoolData.get(position));
        btnAction.setText("Players");

        // Click listener of button
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(view.getContext(), mArrSchoolData.get(position), Toast.LENGTH_SHORT).show();

                String teamname =  mArrSchoolData.get(position);
                Intent intent = new Intent(view.getContext(), PlayersListforTeamScreen.class);
                intent.putExtra("teamname",teamname);

                mContext.startActivity(intent);

            }
        });

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


