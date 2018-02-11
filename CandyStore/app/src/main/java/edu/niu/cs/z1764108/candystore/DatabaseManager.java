package edu.niu.cs.z1764108.candystore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by JAB on 4/18/2017.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "candydb";

    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CANDY = "candy",
                                ID = "id",
                                NAME = "name",
                                PRICE = "price";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //build SQL statement
        String sqlCreate = "create table " + TABLE_CANDY + "( " +
                            ID + " integer primary key autoincrement, " +
                            NAME + " text, " +
                            PRICE + " real )";
        db.execSQL(sqlCreate);
    }//onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CANDY);
        onCreate(db);
    }

    public void insert( Candy candy )
    {
        SQLiteDatabase db = getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_CANDY + " values( null, '" + candy.getName() +
                            "', '" + candy.getPrice() + "' )";
        db.execSQL(sqlInsert);
        db.close();
    }//insert

    public void deleteById(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_CANDY + " where " + ID + " = " + id;
        db.execSQL(sqlDelete);
        db.close();
    }//deleteById

    public void updateById( int id, String name, double price)
    {
        String sqlUpdate = "update " + TABLE_CANDY + " set " + NAME + " = '" + name + "', " +
                           PRICE + " = '" + price + "'" + " where " + ID + " = " + id;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlUpdate);
        db.close();
    }//updateById

    public ArrayList<Candy> selectAll()
    {
        String sqlSelect = "select * from " + TABLE_CANDY;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);

        ArrayList<Candy> candies = new ArrayList<>();

        while(cursor.moveToNext())
        {
            Candy currentCandy = new Candy( Integer.parseInt(cursor.getString(0)),
                                            cursor.getString(1), cursor.getDouble(2));
            candies.add(currentCandy);
        }

        db.close();
        return candies;
    }//selectAll

    public Candy selectByID(int id)
    {
        String sqlSelect = "select * from " + TABLE_CANDY + " where " + ID + " = " + id;
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(sqlSelect, null);
        Candy candy = null;

        if (cursor.moveToFirst())
        {
            candy = new Candy( Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getDouble(2));
        }
        db.close();
        return candy;
    }//selectByID



}//class
