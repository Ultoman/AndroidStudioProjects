package com.example.z1764108.puzzlegame;

import android.app.Activity;
import android.graphics.Color;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by z1764108 on 1/31/2018.
 */

public class PuzzleView extends RelativeLayout {
    private TextView[] tvs;
    private RelativeLayout.LayoutParams [] params;
    private Button giveUp, goBack;
    private int [] colors;
    private int labelHeight;
    private int startY, startTouchY;
    private int emptyPosition;
    private int [] positions;

    private String [] puzzleString;

    public PuzzleView(Activity activity, int width, int height, int numberPieces, String [] puzzle)
    {
        super(activity);
        buildGuiByCode(activity, width, height, numberPieces);
        puzzleString = puzzle;
    }

    private void buildGuiByCode(final Activity activity, int width, int height, int numberPieces)
    {
        tvs = new TextView[numberPieces];
        positions = new int[numberPieces];
        colors = new int[tvs.length];
        params = new RelativeLayout.LayoutParams[tvs.length];
        Random random = new Random();
        labelHeight = height / numberPieces;
        for (int i = 0; i < tvs.length; i++)
        {
            tvs[i] = new TextView(activity);
            tvs[i].setGravity(Gravity.CENTER);
            colors[i] = Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            tvs[i].setBackgroundColor(colors[i]);

            params[i] = new RelativeLayout.LayoutParams(width, labelHeight);
            params[i].leftMargin = 0;
            params[i].topMargin = labelHeight * i;
            addView(tvs[i], params[i]);
        }

        giveUp = new Button(getContext());
        giveUp.setText("Give Up");
        giveUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                solve();
            }
        });
        goBack = new Button(getContext());
        goBack.setText("Go Back");
        goBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
        RelativeLayout.LayoutParams buttonParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        buttonParams.topMargin = labelHeight * 2;
        addView(giveUp);
        addView(goBack, buttonParams);

    }

    // Solves the puzzle by changing the letters into the original string created by the Puzzle constructor
    public void solve()
    {
        int position;
        String letter;
        for (int i = 0; i < puzzleString.length; i++)
        {
            tvs[positions[i]].setText(puzzleString[i]);
            tvs[i].setBackgroundColor(Color.GRAY);
        }
        disableListener();
    }

    // Fills GUI
    public void filledGui(String[] scrambledText)
    {
        for (int i = 0; i < scrambledText.length; i++)
        {
            tvs[i].setText(scrambledText[i]);
            positions[i] = i;
        }
    }

    // Returns index of a textview
    public int indexOfTextView(View tv)
    {
        if (!(tv instanceof TextView))
        {
            return -1;
        }
        for (int i = 0 ; i < tvs.length; i++)
        {
            if (tv == tvs[i])
                return i;
        }
        return -1;
    }

    // Updates the start positions
    public void updateStartPositions(int index,  int y)
    {
        startY = params[index].topMargin;
        startTouchY = y;
        emptyPosition = tvPosition(index);
    }

    // Returns position given a index
    public int tvPosition(int tvIndex)
    {
        return ((params[tvIndex].topMargin + labelHeight/2)/labelHeight);
    }

    // Moves text vertically
    public void moveTextViewVertically(int index, int y)
    {
        params[index].topMargin = startY + y - startTouchY;
        tvs[index].setLayoutParams(params[index]);
    }

    public void enableListener(View.OnTouchListener listener)
    {
        for(int i = 0; i < tvs.length; i++)
        {
            tvs[i].setOnTouchListener(listener);
        }
    }

    public void disableListener()
    {
        for (int i = 0; i < tvs.length; i++)
        {
            tvs[i].setOnTouchListener(null);
        }
    }

    public void placeTextViewAtPosition(int tvIndex, int toPosition)
    {
        params[tvIndex].topMargin = toPosition * labelHeight;
        tvs[tvIndex].setLayoutParams(params[tvIndex]);
        //move to the position of the one we just moved
        int index = positions[toPosition];
        params[index].topMargin = emptyPosition * labelHeight;
        tvs[index].setLayoutParams(params[index]);
        positions[emptyPosition] = index;
        positions[toPosition] = tvIndex;
    }

    public String [] currentSolution()
    {
        String [] current = new String[tvs.length];

        for (int i = 0; i < tvs.length; i++)
        {
            current[i] = tvs[positions[i]].getText().toString();
        }

        return current;
    }

}
