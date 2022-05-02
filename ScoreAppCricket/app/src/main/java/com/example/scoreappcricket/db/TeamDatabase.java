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

public class TeamDatabase extends SQLiteOpenHelper{

    private static final String DATABASENAME = "ScoreApp";
    private static final String TABLENAME = "TeamsList";
    private static final String COL_Player_Name = "PlayerName";
    private static final String COL_Team_Name = "TeamName";

    public TeamDatabase(Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }

    public void addNewTeam(String Team_Name) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();


        // on below line we are creating a
        // variable for content values.
         ContentValues values = new ContentValues();
//
         values.put(COL_Team_Name, Team_Name);
//        values.put(COL_Player_Country, Player_Country);
//        values.put(COL_Player_Role, Player_Role);
//        values.put(COL_Batting_Style, Batting_Style);
//        values.put(COL_Bowling_Style, Bowling_Style);
//        values.put(COL_Batting_Position, Batting_Position);
//
        db.insert(TABLENAME, null, values);
//


       // SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String query = "CREATE TABLE IF NOT EXISTS " + Team_Name + " ("
                + COL_Player_Name + " TEXT PRIMARY KEY UNIQUE)";
//                + COL_Player_Country + " TEXT,"
//                + COL_Player_Role + " TEXT,"
//                + COL_Batting_Style + " TEXT,"
//                + COL_Bowling_Style + " TEXT,"
//                + COL_Batting_Position + " TEXT)";
//
//        // at last we are calling a exec sql
//        // method to execute above sql query
        db.execSQL(query);
        db.close();
    }


    public ArrayList<String> readTeams() {
        ArrayList<String> array_list = new ArrayList<String>();
        String selectQuery = "SELECT * FROM TeamsList";
        String TeamName = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                TeamName = cursor.getString(0);
                array_list.add(TeamName);

            } while (cursor.moveToNext());


        }

        return array_list;
    }

    public ArrayList<String> readPlayersfromTeam(String teamname) {
        ArrayList<String> array_list = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + teamname;
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

    public void deleteTeam(String Team_Name)
    {
        //String deleteQuery = "DELETE FROM TeamsList where TeamName = " +  Team_Name;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLENAME, COL_Team_Name + "=?",new String[]{Team_Name});
        db.execSQL("DROP TABLE IF EXISTS "+Team_Name);

    }

    public void deletePlayerfromTeam(String Player_Name, String Team_Name) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Team_Name, COL_Player_Name + "=?",new String[]{Player_Name});

    }

    public void updateTeamName(String OldTeamName, String NewTeamName) {

        SQLiteDatabase db = this.getWritableDatabase();


        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();
//
        values.put(COL_Team_Name, NewTeamName);
//        values.put(COL_Player_Country, Player_Country);
//        values.put(COL_Player_Role, Player_Role);
//        values.put(COL_Batting_Style, Batting_Style);
//        values.put(COL_Bowling_Style, Bowling_Style);
//        values.put(COL_Batting_Position, Batting_Position);

//
        db.update(TABLENAME, values,COL_Team_Name+" = ?", new String[]{OldTeamName});

        db.execSQL("ALTER TABLE " + OldTeamName + " RENAME TO " + NewTeamName);

        db.close();

    }

    public void addNewPlayerinTeam(String Player_Name, String Team_Name) {
        SQLiteDatabase db = this.getWritableDatabase();


        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();
//
        values.put(COL_Player_Name, Player_Name);
//        values.put(COL_Player_Country, Player_Country);
//        values.put(COL_Player_Role, Player_Role);
//        values.put(COL_Batting_Style, Batting_Style);
//        values.put(COL_Bowling_Style, Bowling_Style);
//        values.put(COL_Batting_Position, Batting_Position);
//
        db.insert(Team_Name, null, values);
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
