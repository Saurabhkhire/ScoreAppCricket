package com.example.scoreappcricket.players;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.teams.PlayersListforTeamScreen;

import java.util.ArrayList;

public class PlayersListAdapter extends BaseAdapter {


    private Context mContext;
    private ArrayList<String> mArrSchoolData;

    public PlayersListAdapter(Context context, ArrayList arrSchoolData) {
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
        view = inflater.inflate(R.layout.player_names_list_screen, parent, false);


        // get the reference of textView and button
        TextView txtPlayersList = (TextView) view.findViewById(R.id.txtPlayersList);
        Button btnAction = (Button) view.findViewById(R.id.PlayersAttributesBtn);

        // Set the title and button name
        txtPlayersList.setText(mArrSchoolData.get(position));
        btnAction.setText("Select");

        // Click listener of button
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(view.getContext(), mArrSchoolData.get(position), Toast.LENGTH_SHORT).show();

                String playername =  mArrSchoolData.get(position);
                Intent intent = new Intent(view.getContext(), PlayersListforTeamScreen.class);
                intent.putExtra("playername",playername);

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


