package com.maame.words;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.UserDictionary;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
  private static final String DATABASE_NAME ="wordsDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Entered Words";
    private static final String CORRECT = "correctWord";
    private static final String WRONG = "wrongWord";


    public DatabaseManager(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);

    }
    public void onCreate(SQLiteDatabase db){
        //build SQL create statement
        String sqlCreate = "create table " + TABLE_NAME + "( " + CORRECT;
         sqlCreate += " String primary key autoincrement, " + WRONG + " text )" ;

        db.execSQL( sqlCreate );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE_NAME );
        // Re-create tables
        onCreate( db );

    }

    public void insert(words word) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " + TABLE_NAME;
        sqlInsert += " values( null, '" + word.getCorrectWord( );
        sqlInsert += "', '" + word.getWrongWord( ) + "' )";

        db.execSQL( sqlInsert );
        db.close( );
    }

    public void deleteByCORRECt(String correctWord){
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDelete = "delete from " + TABLE_NAME;
        sqlDelete += " where " + CORRECT + "=" + correctWord;

        db.execSQL( sqlDelete );
        db.close( );
    }

    public void updateByCORRECt(String correctWord, String wrongWord){
        SQLiteDatabase db = this.getWritableDatabase( );

        String sqlUpdate = " update " + TABLE_NAME;
        sqlUpdate += " set " + WRONG + " ='" + wrongWord + "', ";
        sqlUpdate += " where " + CORRECT + "=" + wrongWord;

        db.execSQL( sqlUpdate );
        db.close( );
    }
    public ArrayList<words> selectAll(){
        String sqlQuery = "select * from " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<words> wordS = new ArrayList<words>();
        while ( cursor.moveToNext()) {
            words currentWord
                    = new words(String.valueOf(cursor.getString(0)), cursor.getString(1));

            words.add(currentWord);


        }
        db.close();
        return wordS;

    }
    public words selectedByCORRECt(String correctWord){
        String sqlQuery = " select * from " + TABLE_NAME;
        sqlQuery += " where " + CORRECT + "=" + correctWord ;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery(sqlQuery, null);

        words word = null;
        if(cursor.moveToFirst()){
            word = new words((String.valueOf(cursor.getString(0))),
                    cursor.getString(1));

            return word;
        }

return word;}
}
