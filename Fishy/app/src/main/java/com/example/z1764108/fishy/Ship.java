package com.example.z1764108.fishy;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by JAB on 3/29/2018.
 */

public class Ship {
    public int x, y;
    public static final int ISHUNGRY = 1;
    public static final int ISFLYING = 2;
    public static final int ISABDUCTING = 3;
    private int mCondition, mVelocity, mStorageCapacity, mCowsInStorage,mFieldWidth, mFieldHeight,mDirection;
    // public cowX and cowY allow for cowImageView in MainActivity to be displayed at the right position
    public int cowX, cowY;
    // Coordinates for the alien to wait
    private int waitX, waitY;
    // Keep track of when cow is touched
    public boolean touchedCow;

    public Ship(int xPos, int yPos, int condition, int fieldW, int fieldH)
    {
        mCondition = condition;
        mVelocity = 3;
        mStorageCapacity = 80;
        mCowsInStorage = mStorageCapacity;
        mFieldHeight = fieldH;
        mFieldWidth = fieldW;
        touchedCow = false;
        x = xPos;
        y = yPos;
        mDirection = 1;
        cowY = (int) mFieldWidth / 2 - 100;
        cowX = (int) (Math.ceil(Math.random() * mFieldWidth) - mFieldWidth/2 );
        waitX = (int) (Math.ceil(Math.random() * mFieldWidth) - mFieldWidth/2 );
        waitY = (int) (Math.random() * mFieldHeight/2) + 100;
    }

    public void move()
    {
        //Log.d("ship", "Count : " + mCowsInStorage);
        switch (mCondition)
        {
            case ISFLYING:
                fly();
                break;
            case ISHUNGRY:
                findCow();
                break;
            case ISABDUCTING:
                abductCow();
                break;
        }
    }

    private void abductCow()
    {
        mCowsInStorage += 4;
        if (mCowsInStorage >= mStorageCapacity)
        {
            mCondition = ISFLYING;
            // Cow goes off screen
            cowX = (int) mFieldWidth * -2;
            // Turn off cow touched
            touchedCow = false;
            waitX = (int) (Math.ceil(Math.random() * mFieldWidth) - mFieldWidth/2);
            waitY = (int) - (Math.random() * mFieldHeight/2) + 100;
        }
    }

    private void findCow()
    {
        // Update and move to cow point
        int xDistance = cowX - x;
        int yDistance = cowY - y;
        x += xDistance/mVelocity;
        y += yDistance/mVelocity;

        if (cowX < x)
        {
            mDirection = -1;
        }
        else
        {
            mDirection = 1;
        }

        if (Math.abs(x - cowX) <= 10 && Math.abs(y - cowY) <= 10)
        {
            mCondition = ISABDUCTING;
        }
    }

    public void fly()
    {
        // If cow storage is empty then do not remove more cows
        if (mCowsInStorage != 0) {
            mCowsInStorage--;
        }
        // If about to become hungry, make cow appear
        if (mCowsInStorage == 1)
        {
            cowX = (int) (Math.ceil(Math.random() * mFieldWidth) - mFieldWidth/2) - 100;
        }
        // Update and move to waiting point
        int xDistance = waitX - x;
        int yDistance = waitY - y;
        x += xDistance/mVelocity;
        y += yDistance/mVelocity;

        if (waitX < x)
        {
            mDirection = -1;
        }
        else
        {
            mDirection = 1;
        }
        if (Math.abs(xDistance) < 5 && Math.abs(yDistance) < 5)
        {
            waitY = (int) - (Math.random() * mFieldHeight/2) + 100;
            waitX = (int) (Math.ceil(Math.random() * mFieldWidth) - mFieldWidth/2);
        }
        if (mCowsInStorage <= 0 && touchedCow)
        {
            mCondition = ISHUNGRY;
            //cowX = (int) (Math.ceil(Math.random() * mFieldWidth) - mFieldWidth/2) - 100;
        }
    }

    public int getFacingDirection()
    {
        return mDirection;
    }

}
