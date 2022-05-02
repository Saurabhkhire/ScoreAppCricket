package com.example.scoreappcricket.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class QuickMatchDatabase extends SQLiteOpenHelper{

    private static final String DATABASENAME = "ScoreApp";


    public QuickMatchDatabase(Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }

    public void createTablesforNormalMatch() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "CREATE TABLE IF NOT EXISTS NormalMatchTeamOneBatsmanTemp(BatsmanName TEXT PRIMARY KEY UNIQUE,WicketType TEXT,BatsmanRuns INTEGER,BatsmanBalls INTEGER,BatsmanFours INTEGER,BatsmanSixes INTEGER, BatsmanStrikeRate REAL)";

        sqLiteDatabase.execSQL(query);

        String query1 = "CREATE TABLE IF NOT EXISTS NormalMatchTeamTwoBowlersTemp(BowlerName TEXT PRIMARY KEY UNIQUE,BowlerOvers REAL,BowlerRuns INTEGER,BowlerWickets INTEGER,BowlerMaidens INTEGER,BowlerEconomy REAL)";
        sqLiteDatabase.execSQL(query1);

    }

    public void NormalMatchTeamOneBatsmanTemp(String OutBatsman, String WicketType, Integer Runs,Integer Balls, Integer Fours, Integer Sixes, Double StrikeRate) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();


        // on below line we are creating a
        // variable for content values.
         ContentValues values = new ContentValues();
//
         values.put("BatsmanName", OutBatsman);
        values.put("WicketType", WicketType);
        values.put("BatsmanRuns", Runs);
       values.put("BatsmanBalls", Balls);
       values.put("BatsmanFours", Fours);
       values.put("BatsmanSixes", Sixes);
        values.put("BatsmanStrikeRate", StrikeRate);

        db.insert("NormalMatchTeamOneBatsmanTemp", null, values);
//


        db.close();
    }


    public ArrayList<String> getNewBowler(String Bowler) {
        ArrayList<String> array_list = new ArrayList<String>();
        String selectQuery = "SELECT * FROM NormalMatchTeamTwoBowlersTemp where BowlerName = '" + Bowler + "'";
        String BowlerName = null;
        Double BowlerOvers = null;
        Integer BowlerRuns = null;
        Integer BowlerWickets = null;
        Integer BowlerMaidens = null;
        Double BowlerEconomy = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()==true) {
            do {

                BowlerName = cursor.getString(0);
                array_list.add(BowlerName);
                BowlerOvers = cursor.getDouble(1);
                array_list.add(BowlerOvers.toString());
                BowlerRuns = cursor.getInt(2);
                array_list.add(BowlerRuns.toString());
                BowlerWickets = cursor.getInt(3);
                array_list.add(BowlerWickets.toString());
                BowlerMaidens = cursor.getInt(4);
                array_list.add(BowlerMaidens.toString());
                BowlerEconomy = cursor.getDouble(5);
                array_list.add(BowlerEconomy.toString());

            } while (cursor.moveToNext());


        }

        return array_list;
    }


    public ArrayList<String> getOutBatsman() {
        ArrayList<String> array_list = new ArrayList<String>();
        String selectQuery = "SELECT * FROM NormalMatchTeamOneBatsmanTemp";
        String BatsmanName = null;
        String WicketType = null;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()==true) {
            do {

                BatsmanName = cursor.getString(0);
                array_list.add(BatsmanName);
                WicketType = cursor.getString(1);
                if (!WicketType.contains("Retire Hurt")) {
                    array_list.add(BatsmanName);
                }


            } while (cursor.moveToNext());


        }

        return array_list;
    }

    public ArrayList<String> getRetiredHurtBatsman(String Batsman) {
        ArrayList<String> array_list = new ArrayList<String>();
        String selectQuery = "SELECT * FROM NormalMatchTeamOneBatsmanTemp where BatsmanName = '" + Batsman + "'";
        String BatsmanName = null;
        String WicketType = null;
        Integer BatsmanRuns = null;
        Integer BatsmanBalls = null;
        Integer BatsmanFours = null;
        Integer BatsmanSixes = null;
        Double BatsmanStrikeRate = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()==true) {
            do {

                WicketType = cursor.getString(1);

                if (WicketType.contains("Retire Hurt")) {

                    BatsmanName = cursor.getString(0);
                    array_list.add(BatsmanName);
                    BatsmanRuns = cursor.getInt(2);
                    array_list.add(BatsmanRuns.toString());
                    BatsmanBalls = cursor.getInt(3);
                    array_list.add(BatsmanBalls.toString());
                    BatsmanFours = cursor.getInt(4);
                    array_list.add(BatsmanFours.toString());
                    BatsmanSixes = cursor.getInt(5);
                    array_list.add(BatsmanSixes.toString());
                    BatsmanStrikeRate = cursor.getDouble(6);
                    array_list.add(BatsmanStrikeRate.toString());

                }

            } while (cursor.moveToNext());


        }

        return array_list;
    }



    public void deleteTablesofNormalMatch()
    {
        //String deleteQuery = "DELETE FROM TeamsList where TeamName = " +  Team_Name;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS NormalMatchTeamOneBatsmanTemp");
        db.execSQL("DROP TABLE IF EXISTS NormalMatchTeamTwoBowlersTemp");

    }



    public void NormalMatchTeamTwoBowlersTemp(String Bowler, Double Overs, Integer Runs, Integer Wickets, Integer Maidens, Double Economy) {


        String selectQuery = "SELECT * FROM NormalMatchTeamTwoBowlersTemp where BowlerName = '" + Bowler + "'";


        String BowlerName = "None";
        SQLiteDatabase readdb = this.getReadableDatabase();
        Cursor cursor = readdb.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()==true) {
            do {

                BowlerName = "Bowler";

            } while (cursor.moveToNext());


        }

        SQLiteDatabase db = this.getWritableDatabase();


        if (BowlerName.equals("None")) {

            ContentValues values = new ContentValues();

            values.put("BowlerName", Bowler);
            values.put("BowlerOvers", Overs);
            values.put("BowlerRuns", Runs);
            values.put("BowlerWickets", Wickets);
            values.put("BowlerMaidens", Maidens);
            values.put("BowlerEconomy", Economy);

            db.insert("NormalMatchTeamTwoBowlersTemp", null, values);

        } else {

            ContentValues values = new ContentValues();

           // values.put("BowlerName", Bowler);
            values.put("BowlerOvers", Overs);
            values.put("BowlerRuns", Runs);
            values.put("BowlerWickets", Wickets);
            values.put("BowlerMaidens", Maidens);

            db.update("NormalMatchTeamTwoBowlersTemp", values,"BowlerName" +" = ?", new String[]{Bowler});
//            db.update("NormalMatchTeamTwoBowlersTemp", values,"BowlerOvers" +" = ?", new String[]{Overs.toString()});
//            db.update("NormalMatchTeamTwoBowlersTemp", values,"BowlerRuns" +" = ?", new String[]{Runs.toString()});
//            db.update("NormalMatchTeamTwoBowlersTemp", values,"BowlerWickets" +" = ?", new String[]{Wickets.toString()});
//            db.update("NormalMatchTeamTwoBowlersTemp", values,"BowlerMaidens" +" = ?", new String[]{Maidens.toString()});
//            db.update("NormalMatchTeamTwoBowlersTemp", values,"BowlerEconomy" +" = ?", new String[]{Economy.toString()});


        }

        db.close();


    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
