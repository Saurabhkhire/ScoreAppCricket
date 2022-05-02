package com.example.scoreappcricket.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PlayerDatabase extends SQLiteOpenHelper{

    private static final String DATABASENAME = "ScoreApp";
    private static final String TABLENAME = "PlayersList";
    private static final String COL_Player_Name = "PlayerName";
    private static final String COL_Player_Country = "PlayerCountry";
    private static final String COL_Player_Role = "PlayerRole";
    private static final String COL_Batting_Style = "BattingStyle";
    private static final String COL_Bowling_Style = "BowlingStyle";
    private static final String COL_Batting_Position = "BattingPosition";

    public PlayerDatabase(Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {




    }

    public void createTables() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "CREATE TABLE IF NOT EXISTS PlayersList (PlayerName TEXT PRIMARY KEY UNIQUE,PlayerCountry TEXT,PlayerRole TEXT,BattingStyle TEXT,BowlingStyle TEXT,BattingPosition TEXT)";

        sqLiteDatabase.execSQL(query);

        String query1 = "CREATE TABLE IF NOT EXISTS TeamsList (TeamName TEXT PRIMARY KEY UNIQUE)";
        sqLiteDatabase.execSQL(query1);
    }

    public void addNewPlayer(String Player_Name, String Player_Country, String Player_Role, String Batting_Style, String Bowling_Style, String Batting_Position) {



        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        values.put(COL_Player_Name, Player_Name);
        values.put(COL_Player_Country, Player_Country);
        values.put(COL_Player_Role, Player_Role);
        values.put(COL_Batting_Style, Batting_Style);
        values.put(COL_Bowling_Style, Bowling_Style);
        values.put(COL_Batting_Position, Batting_Position);

        db.insert(TABLENAME, null, values);

        db.close();
    }


    public ArrayList<String> readPlayers() {
        ArrayList<String> array_list = new ArrayList<String>();
        String selectQuery = "SELECT * FROM PlayersList";
        String PlayerName = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                PlayerName = cursor.getString(0);
                array_list.add(PlayerName);

            } while (cursor.moveToNext());


        }

        return array_list;
    }

    public ArrayList<String> getPlayerAttributes(String Player) {
        ArrayList<String> array_list = new ArrayList<String>();
        String selectQuery = "SELECT * FROM PlayersList where PlayerName = '" + Player + "'";
        String PlayerName = null;
        String PlayerCountry = null;
        String PlayerRole = null;
        String BattingStyle = null;
        String BowlingStyle = null;
        String BattingPosition = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()==true) {
            do {

                PlayerName = cursor.getString(0);
                array_list.add(PlayerName);
                PlayerCountry = cursor.getString(1);
                array_list.add(PlayerCountry);
                PlayerRole = cursor.getString(2);
                array_list.add(PlayerRole);
                BattingStyle = cursor.getString(3);
                array_list.add(BattingStyle);
                BowlingStyle = cursor.getString(4);
                array_list.add(BowlingStyle);
                BattingPosition = cursor.getString(5);
                array_list.add(BattingPosition);

            } while (cursor.moveToNext());


        }

        return array_list;
    }

    public void deletePlayer(String Player_Name,ArrayList<String> mArrDataTeams)
    {
        //String deleteQuery = "DELETE FROM PlayersList where PlayerName = " +  Player_Name;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLENAME, COL_Player_Name + "=?",new String[]{Player_Name});

        if(mArrDataTeams != null) {
            for (int val = 0; val < mArrDataTeams.size(); val++) {

                db.delete((mArrDataTeams.get(val)), COL_Player_Name + "=?",new String[]{Player_Name});

            }
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
