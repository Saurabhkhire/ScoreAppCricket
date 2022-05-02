package com.example.scoreappcricket.teams;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.TeamDatabase;

import java.util.ArrayList;

public class DeletePlayerfromTeamAdapter extends BaseAdapter {


    private Context mContext;
    private ArrayList<String> mArrSchoolData;
    private TeamDatabase dbHandler;
    private String teamname;

    public DeletePlayerfromTeamAdapter(Context context, ArrayList arrSchoolData, TeamDatabase dbHandler, String teamname) {
        super();
        mContext = context;
        mArrSchoolData = arrSchoolData;
        this.dbHandler = dbHandler;
        this.teamname = teamname;

    }

    public int getCount() {
        // return the number of records
        return mArrSchoolData.size();
    }

    // getView method is called for each item of ListView
    public View getView(int position, View view, ViewGroup parent) {
        // inflate the layout for each item of listView
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.delete_player_from_team_screen, parent, false);


        // get the reference of textView and button
        TextView txtSchoolTitle = (TextView) view.findViewById(R.id.txtDeletePlayersList);
        Button btnAction = (Button) view.findViewById(R.id.DeletePlayerinTeambtn);

        // Set the title and button name
        txtSchoolTitle.setText(mArrSchoolData.get(position));
        btnAction.setText("Delete");

        // Click listener of button
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(view.getContext(), mArrSchoolData.get(position), Toast.LENGTH_SHORT).show();

                String playername =  mArrSchoolData.get(position);
                mArrSchoolData.remove(position);
                notifyDataSetChanged();
                dbHandler.deletePlayerfromTeam(playername,teamname);

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


