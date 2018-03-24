package com.example.z1764108.puzzlegame;

import android.content.res.Resources;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    public static int STATUSBARHEIGHT = 24;
    public static int ACTIONBARHEIGHT = 56;
    private Puzzle puzzle;
    private PuzzleView pv;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        puzzle = new Puzzle();
        // Screen size data
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        screenHeight = size.y;
        int puzzleWidth = size.x;
        Resources res = getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        float pixelDensity = metrics.density;
        int actionBarHeight = (int)(pixelDensity * ACTIONBARHEIGHT);

        int statusBarHeight = (int)(pixelDensity * STATUSBARHEIGHT);
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId != 0) // found a status bar
        {
            statusBarHeight = res.getDimensionPixelOffset(resourceId);
        }
        int puzzleHeight = screenHeight - statusBarHeight;
        pv = new PuzzleView(this,puzzleWidth, puzzleHeight, puzzle.getNumParts(), puzzle.getString());
        String [] scrambled = puzzle.scramble();
        pv.filledGui(scrambled);
        pv.enableListener(this);
        setContentView(pv);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        int index = pv.indexOfTextView(view);
        int action = motionEvent.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                pv.updateStartPositions(index, (int)motionEvent.getRawY());
                pv.bringChildToFront(view);
                break;
            case MotionEvent.ACTION_MOVE:
                pv.moveTextViewVertically(index, (int)motionEvent.getRawY());
                break;
            case MotionEvent.ACTION_UP:
                int newPosition = pv.tvPosition(index);
                pv.placeTextViewAtPosition(index, newPosition);
                if (puzzle.solved(pv.currentSolution()))
                {
                    Toast.makeText(this, "Complete!", Toast.LENGTH_SHORT).show();
                    pv.disableListener();
                }
        }//switch
        return true;
    }

}
