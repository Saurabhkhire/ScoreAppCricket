package com.example.scoreappcricket.common;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.widget.ContentFrameLayout;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CommonLogic {

    private static ArrayList<String> modelArrayList;


    public static void batsmanScoreTable(TextView BatsmanEdit,TextView BatsmanRunsEdit,TextView BatsmanFoursEdit,TextView BatsmanSixesEdit,TextView BatsmanStrikeRateEdit,TextView BatsmanWicketTypeEdit, ArrayList<String> batsmanList, ArrayList<String> batsmanRunsList, ArrayList<String> batsmanBallsList, ArrayList<String> batsmanFoursList, ArrayList<String> batsmanSixesList, ArrayList<String> batsmanStrikeRateList, ArrayList<String> batsmanWicketTypeList, TableLayout prices, Context context, String team) {

        String colorList = "#ffffff,#000000";
        String[] colorListSplit = colorList.split(",");

        BatsmanEdit.setBackgroundColor(Color.parseColor(colorListSplit[0]));
        BatsmanEdit.setTextColor(Color.parseColor(colorListSplit[1]));
        BatsmanRunsEdit.setBackgroundColor(Color.parseColor(colorListSplit[0]));
        BatsmanRunsEdit.setTextColor(Color.parseColor(colorListSplit[1]));
        BatsmanFoursEdit.setBackgroundColor(Color.parseColor(colorListSplit[0]));
        BatsmanFoursEdit.setTextColor(Color.parseColor(colorListSplit[1]));
        BatsmanSixesEdit.setBackgroundColor(Color.parseColor(colorListSplit[0]));
        BatsmanSixesEdit.setTextColor(Color.parseColor(colorListSplit[1]));
        BatsmanStrikeRateEdit.setBackgroundColor(Color.parseColor(colorListSplit[0]));
        BatsmanStrikeRateEdit.setTextColor(Color.parseColor(colorListSplit[1]));
        BatsmanWicketTypeEdit.setBackgroundColor(Color.parseColor(colorListSplit[0]));
        BatsmanWicketTypeEdit.setTextColor(Color.parseColor(colorListSplit[1]));

        GradientDrawable border = new GradientDrawable();
        border.setColor(Color.parseColor(colorListSplit[0])); //white background
        border.setStroke(1, Color.parseColor(colorListSplit[0])); //black border with full opacity
        prices.setBackground(border);
        for(int i = 0; i < batsmanList.size(); i++) {
            TableRow tr =  new TableRow(context);
            //tr.setBackgroundColor(Color.BLACK);
            TextView c1 = new TextView(context);
            c1.setText(batsmanList.get(i));
            c1.setGravity(Gravity.CENTER);
            c1.setBackground(border);
            c1.setTextColor(Color.parseColor(colorListSplit[1]));
            TextView c2 = new TextView(context);
            c2.setText(batsmanRunsList.get(i) + " (" + batsmanBallsList.get(i) + ")");
            c2.setBackground(border);
            c2.setTextColor(Color.parseColor(colorListSplit[1]));
            TextView c3 = new TextView(context);
            c3.setText(batsmanFoursList.get(i));
            c3.setBackground(border);
            c3.setTextColor(Color.parseColor(colorListSplit[1]));
            TextView c4 = new TextView(context);
            c4.setText(batsmanSixesList.get(i));
            c4.setBackground(border);
            c4.setTextColor(Color.parseColor(colorListSplit[1]));
            TextView c5 = new TextView(context);
            c5.setText(batsmanStrikeRateList.get(i));
            c5.setBackground(border);
            c5.setTextColor(Color.parseColor(colorListSplit[1]));
           // TextView c6 = new TextView(context);
            //c6.setText(batsmanWicketTypeList.get(i));
           // c6.setBackground(border);
           // c6.setTextColor(Color.parseColor(colorListSplit[1]));
            tr.addView(c1);
            tr.addView(c2);
            tr.addView(c3);
            tr.addView(c4);
            tr.addView(c5);
          //  tr.addView(c6);

            prices.addView(tr);
        }
    }

    public static void bowlersScoreTable(TextView BatsmanEdit,TextView BatsmanRunsEdit,TextView BatsmanFoursEdit,TextView BatsmanSixesEdit,TextView BatsmanStrikeRateEdit,TextView BatsmanWicketTypeEdit, ArrayList<String> batsmanList, ArrayList<String> batsmanRunsList, ArrayList<String> batsmanBallsList, ArrayList<String> batsmanFoursList, ArrayList<String> batsmanSixesList, ArrayList<String> batsmanStrikeRateList, TableLayout prices, Context context, String team) {

        String colorList = "#ffffff,#000000";
        String[] colorListSplit = colorList.split(",");

        BatsmanEdit.setBackgroundColor(Color.parseColor(colorListSplit[0]));
        BatsmanEdit.setTextColor(Color.parseColor(colorListSplit[1]));
        BatsmanRunsEdit.setBackgroundColor(Color.parseColor(colorListSplit[0]));
        BatsmanRunsEdit.setTextColor(Color.parseColor(colorListSplit[1]));
        BatsmanFoursEdit.setBackgroundColor(Color.parseColor(colorListSplit[0]));
        BatsmanFoursEdit.setTextColor(Color.parseColor(colorListSplit[1]));
        BatsmanSixesEdit.setBackgroundColor(Color.parseColor(colorListSplit[0]));
        BatsmanSixesEdit.setTextColor(Color.parseColor(colorListSplit[1]));
        BatsmanStrikeRateEdit.setBackgroundColor(Color.parseColor(colorListSplit[0]));
        BatsmanStrikeRateEdit.setTextColor(Color.parseColor(colorListSplit[1]));
        BatsmanWicketTypeEdit.setBackgroundColor(Color.parseColor(colorListSplit[0]));
        BatsmanWicketTypeEdit.setTextColor(Color.parseColor(colorListSplit[1]));

        GradientDrawable border = new GradientDrawable();
        border.setColor(Color.parseColor(colorListSplit[0])); //white background
        border.setStroke(1, Color.parseColor(colorListSplit[0])); //black border with full opacity
        prices.setBackground(border);
        for(int i = 0; i < batsmanList.size(); i++) {
            TableRow tr =  new TableRow(context);
            //tr.setBackgroundColor(Color.BLACK);
            TextView c1 = new TextView(context);
            c1.setText(batsmanList.get(i));
            c1.setGravity(Gravity.CENTER);
            c1.setBackground(border);
            c1.setTextColor(Color.parseColor(colorListSplit[1]));
            TextView c2 = new TextView(context);
            c2.setText(batsmanRunsList.get(i));
            c2.setBackground(border);
            c2.setTextColor(Color.parseColor(colorListSplit[1]));
            TextView c3 = new TextView(context);
            c3.setText(batsmanFoursList.get(i));
            c3.setBackground(border);
            c3.setTextColor(Color.parseColor(colorListSplit[1]));
            TextView c4 = new TextView(context);
            c4.setText(batsmanSixesList.get(i));
            c4.setBackground(border);
            c4.setTextColor(Color.parseColor(colorListSplit[1]));
            TextView c5 = new TextView(context);
            c5.setText(batsmanStrikeRateList.get(i));
            c5.setBackground(border);
            c5.setTextColor(Color.parseColor(colorListSplit[1]));
            //TextView c6 = new TextView(context);
            //c6.setText(batsmanWicketTypeList.get(i));
            //c6.setBackground(border);
            //c6.setTextColor(Color.parseColor(colorListSplit[1]));
            tr.addView(c1);
            tr.addView(c2);
            tr.addView(c3);
            tr.addView(c4);
            tr.addView(c5);
            //tr.addView(c6);

            prices.addView(tr);
        }
    }

    public static void addPlayersTable(ArrayList<String> playerNamesList, ArrayList<String> priceList, TableLayout prices, Context context, String username, String roomId, String teamName) {

        modelArrayList = new ArrayList<>();
        GradientDrawable border = new GradientDrawable();
        border.setColor(0xFFFFFFFF); //white background
        border.setStroke(1, 0xFF000000); //black border with full opacity
        prices.setBackground(border);
        for(int i = 0; i < playerNamesList.size(); i++){
            TableRow tr =  new TableRow(context);
            //tr.setMinimumHeight(30);
            //tr.setBackgroundColor(Color.BLACK);
            TextView c1 = new TextView(context);
            c1.setText(playerNamesList.get(i));
            c1.setGravity(Gravity.CENTER);
            c1.setBackground(border);
            c1.setHeight(50);
            TextView c2 = new TextView(context);
            c2.setText(priceList.get(i));
            c2.setGravity(Gravity.CENTER);
            c2.setBackground(border);
            c2.setHeight(50);

                CheckBox c3 = new CheckBox(context);
                c3.setChecked(false);
                c3.setGravity(Gravity.CENTER);
                c3.setBackground(border);
                c3.setHeight(50);
                tr.addView(c1);
                tr.addView(c2);
                tr.addView(c3);
                prices.addView(tr);

                c3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            modelArrayList.add(c1.getText().toString());
                        } else {
                            modelArrayList.remove(c1.getText().toString());
                        }
                    }
                });




        }
    }

    public static ArrayList<String> list() {
         return modelArrayList;
    }

}


/*         Picasso.with(this).load("http://imageUrl").into(new Target() {
@Override
public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        mYourLayout.setBackground(new BitmapDrawable(bitmap));
        }

@Override
public void onBitmapFailed(Drawable errorDrawable) {

        }

@Override
public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
        });*/
