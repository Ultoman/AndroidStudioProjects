package jab.trivia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by JAB on 2/4/2018.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "triviadb";

    private static final int DATABASE_VERSION = 1;

    // Declaring trivia question variables
    private static final String TRIVIA_TABLE = "trivia",
                                T_ID = "id",
                                QUESTION = "question",
                                CORRECT_ANSWER = "correct_answer",
                                INCORRECT_ANSWER_1 = "incorrect_answer_1",
                                INCORRECT_ANSWER_2 = "incorrect_answer_2",
                                INCORRECT_ANSWER_3 = "incorrect_answer_3";
    // Declaring categories variables
    private static final String CATEGORY_TABLE = "categories",
                                C_ID = "id",
                                CATEGORY = "category";
    // Declaring score variables
    private static final String SCORE_TABLE = "scores",
                                S_ID = "id",
                                SCORE = "score";

    public DatabaseManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Build trivia table
        String sqlCreateTrivia = "create table " + TRIVIA_TABLE + "( " +
                            T_ID + " integer primary key, " +
                            QUESTION + " text, " +
                            CORRECT_ANSWER + " text, " +
                            INCORRECT_ANSWER_1 + " text, " +
                            INCORRECT_ANSWER_2 + " text, " +
                            INCORRECT_ANSWER_3 + " text )";
        db.execSQL(sqlCreateTrivia);

        // Build categories table
        String sqlCreateCategories = "create table " + CATEGORY_TABLE + "( " +
                                C_ID + " integer primary key, " +
                                CATEGORY + " text )";
        db.execSQL(sqlCreateCategories);

        // Build score table
        String sqlCreateScore = "create table " + SCORE_TABLE + "( " +
                                S_ID + " integer primary key autoincrement, " +
                                SCORE + " real )";
        db.execSQL(sqlCreateScore);
    }//onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TRIVIA_TABLE);
        onCreate(db);
    }//onUpgrade

    // insert into trivia table
    public void insertTrivia( Trivia trivia )
    {
        SQLiteDatabase db = getWritableDatabase();
        String sqlInsert = "insert into " + TRIVIA_TABLE + " values( " +
                trivia.getId() + ", '" +
                trivia.getQuestion() + "', '" +
                trivia.getCorrect_ans() + "', '" +
                trivia.getInc_ans_1() + "', '" +
                trivia.getInc_ans_2() + "', '" +
                trivia.getInc_ans_3() + "' )";
        db.execSQL(sqlInsert);
        db.close();
    }

    // insert into category table
    public void insertCategory(int id, String category)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sqlInsert = "insert into " + CATEGORY_TABLE + " values( " + id + "', '" + category + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    // insert into score table
    public void insertScore(float score)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sqlInsert = "insert into " + SCORE_TABLE + " values( null, '" + score + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    // get all trivias from database
    public ArrayList<Trivia> selectAllTrivia()
    {
        String sqlSelect = "select * from " + TRIVIA_TABLE;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);
        ArrayList<Trivia> trivias = new ArrayList<Trivia>();

        while(cursor.moveToNext())
        {   //Grab current trivia
            Trivia currentTrivia = new Trivia( Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                                                cursor.getString(3), cursor.getString(4), cursor.getString(5));
            //Add to ArrayList
            trivias.add(currentTrivia);
        }

        db.close();
        return trivias;
    }

    // get Count of trivia questions
    public int getTriviaCount()
    {
        String sqlSelect = "select * from " + TRIVIA_TABLE;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);
        int count = cursor.getCount();
        db.close();
        return count;

    }

    // get all category names from database
    public String [] selectAllCategories()
    {
        String sqlSelect = "select " + C_ID + " from " + CATEGORY_TABLE;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);
        String categories[] = new String[cursor.getCount() + 1];
        int i = 1;
        // set first category to any
        categories[0] = "Any";
        while(cursor.moveToNext())
        {   //Grab current category name
            categories[i] = cursor.getString(1);
            i++;
        }
        db.close();
        return categories;
    }

    // get category id corresponding to name
    public int selectCategory(String name)
    {
        if (name == "Any")
        {
            return 0;
        }
        else {
            String sqlSelect = "select " + C_ID + " from " + CATEGORY_TABLE + " where " + CATEGORY + " = " + name;
            SQLiteDatabase db = getWritableDatabase();
            Cursor cursor = db.rawQuery(sqlSelect, null);
            db.close();
            return cursor.getInt(0);
        }
    }

    // Drop category table
    public void dropCategory()
    {
        String sqlDrop = "drop table " + CATEGORY_TABLE;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlDrop);
        db.close();
    }

    // Drop trivia table
    public void dropTrivia()
    {
        String sqlDrop = "drop table " + TRIVIA_TABLE;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlDrop);
        db.close();
    }

}
