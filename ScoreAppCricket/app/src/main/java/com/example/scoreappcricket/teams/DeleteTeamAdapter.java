package com.example.scoreappcricket.teams;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.TeamDatabase;

import java.util.ArrayList;

public class DeleteTeamAdapter extends BaseAdapter {


    private Context mContext;
    private ArrayList<String> mArrSchoolData;
    private TeamDatabase dbHandler;

    public DeleteTeamAdapter(Context context, ArrayList arrSchoolData, TeamDatabase dbHandler) {
        super();
        mContext = context;
        mArrSchoolData = arrSchoolData;
        this.dbHandler = dbHandler;
    }

    public int getCount() {
        // return the number of records
        return mArrSchoolData.size();
    }

    // getView method is called for each item of ListView
    public View getView(int position, View view, ViewGroup parent) {
        // inflate the layout for each item of listView
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.delete_team_screen, parent, false);


        // get the reference of textView and button
        TextView txtSchoolTitle = (TextView) view.findViewById(R.id.txtTeamName);
        Button btnAction = (Button) view.findViewById(R.id.DeleteTeambtn);

        // Set the title and button name
        txtSchoolTitle.setText(mArrSchoolData.get(position));
        btnAction.setText("Delete");

        // Click listener of button
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String teamname =  mArrSchoolData.get(position);
                dbHandler.deleteTeam(teamname);
                mArrSchoolData.remove(position);
                notifyDataSetChanged();
                Toast.makeText(view.getContext(), "Team has been deleted", Toast.LENGTH_SHORT).show();


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


