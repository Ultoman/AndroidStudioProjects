package jab.trivia;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by JAB on 2/4/2018.
 */

/*
Handles all writing and reading from database
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

    //get all scores from database
    public ArrayList<Float> selectAllScores()
    {
        String sqlSelect = "select * from " + SCORE_TABLE + " order by " + SCORE + " desc";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);
        ArrayList<Float> scores = new ArrayList<>();

        while(cursor.moveToNext())
        {
            // Grab current score
            float score = cursor.getFloat(1);
            Log.d("score", "Score: " + score);

            scores.add(score);
        }

        db.close();
        return scores;
    }

    // get Count of trivia questions
    public int getTriviaCount()
    {
        SQLiteDatabase db = getReadableDatabase();
        int count = (int)DatabaseUtils.queryNumEntries(db, TRIVIA_TABLE);
        db.close();
        return count;

    }

    //get Count of scores
    public int getScoreCount()
    {
        SQLiteDatabase db = getReadableDatabase();
        int count = (int)DatabaseUtils.queryNumEntries(db, SCORE_TABLE);
        db.close();
        return count;
    }

    // Drop trivia table
    public void deleteTrivia()
    {
        //String sqlDrop = "drop table " + TRIVIA_TABLE;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " + TRIVIA_TABLE);
        db.close();
    }

    // Drop score table
    public void deleteScores()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " + SCORE_TABLE);
        db.close();
    }

}
