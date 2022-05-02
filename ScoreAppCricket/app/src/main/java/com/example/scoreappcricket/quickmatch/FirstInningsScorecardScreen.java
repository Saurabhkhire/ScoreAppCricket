package com.example.scoreappcricket.quickmatch;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scoreappcricket.R;
import com.example.scoreappcricket.db.PlayerDatabase;
import com.example.scoreappcricket.db.QuickMatchDatabase;
import com.example.scoreappcricket.players.PlayersListAdapter;
import com.example.scoreappcricket.players.PlayersListScreen;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstInningsScorecardScreen extends AppCompatActivity {

    private ListView mListview;
    private ArrayList<String> mArrData;
    private FirstInningsScorecardAdapter mAdapter;
    private QuickMatchDatabase quickMatchDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_match_first_innings_scorecard_listview);

        //mListview = findViewById(R.id.BatsmanNamesList);
        quickMatchDatabase = new QuickMatchDatabase(FirstInningsScorecardScreen.this);

        mListview = (ListView) findViewById(R.id.BatsmanNamesList);

        String [] listA = {"aaa","bbb","ccc"};
        ArrayList<String> a = new ArrayList<String>();
        a.add("aaaa");
        a.add("bbbb");
        a.add("cccc");
        a.add("cccc");
        a.add("cccc");
        a.add("cccc");
        a.add("cccc");
        a.add("cccc");
        a.add("cccc");
        a.add("cccc");

        String [] listB = {"aaa","bbb","ccc"};
        ArrayList<String> b = new ArrayList<String>();
        b.add("11");
        b.add("22");
        b.add("33");
        b.add("33");
        b.add("33");
        b.add("33");
        b.add("33");
        b.add("33");
        b.add("33");
        b.add("33");

        String [] listC = {"aaa","bbb","ccc"};
        ArrayList<String> c = new ArrayList<String>();
        c.add("xxx");
        c.add("yyy");
        c.add("zzz");
        c.add("zzz");
        c.add("zzz");
        c.add("zzz");
        c.add("zzz");
        c.add("zzz");
        c.add("zzz");
        c.add("zzz");



        mAdapter = new FirstInningsScorecardAdapter(FirstInningsScorecardScreen.this, a, b, c);
        mListview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        Button footer = new Button(this);
        footer.setGravity(Gravity.CENTER);
        footer.setWidth(30);
        footer.setTextSize(5);
        footer.setText("Footer");
        mListview.addFooterView(footer);
        Button footer1 = new Button(this);
        footer1.setGravity(Gravity.CENTER);
        footer1.setWidth(30);
        footer1.setTextSize(5);
        footer1.setText("Footer");
        mListview.addFooterView(footer1);


        //support_simple_spinner_dropdown_item
    }
}