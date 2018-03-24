package com.example.z1764108.fishy;

/**
 * Created by z1764108 on 2/14/2018.
 */

public class Fish {
    public int x, y;
    public static final int ISHUNGRY = 1;
    public static final int ISSWIMMING = 2;
    public static final int ISEATING = 3;
    private int mCondition, mVelocity, mStomachCapacity, mFoodInStomach,mTankWidth, mTankHeight,mDirection;
    private int playX, playY, foodX, foodY;

    public Fish(int xPos, int yPos, int condition, int tankW, int tankH)
    {
        mCondition = condition;
        mVelocity = 3;
        mStomachCapacity = 80;
        mFoodInStomach = mStomachCapacity;
        mTankHeight = tankH;
        mTankWidth = tankW;
        x = xPos;
        y = yPos;
        mDirection = 1;
        foodY = (int) mTankWidth / 2 - 100;
        foodX = (int) (Math.ceil(Math.random() * mTankWidth) - mTankWidth/2 );
        playX = (int) (Math.ceil(Math.random() * mTankWidth) - mTankWidth/2 );
        playY = (int) (Math.random() * mTankHeight/2) + 100;
    }

    public void move()
    {
        switch (mCondition)
        {
            case ISSWIMMING:
                swim();
                break;
            case ISHUNGRY:
                findFood();
                break;
            case ISEATING:
                eatFood();
                break;
        }
    }

    private void eatFood()
    {
        mFoodInStomach += 4;
        if (mFoodInStomach >= mStomachCapacity)
        {
            mCondition = ISSWIMMING;
            playX = (int) (Math.ceil(Math.random() * mTankWidth) - mTankWidth/2);
            playY = (int) - (Math.random() * mTankHeight/2) + 100;
        }
    }

    private void findFood()
    {
        int xDistance = foodX - x;
        int yDistance = foodY - y;
        x += xDistance/mVelocity;
        y += yDistance/mVelocity;

        if (foodX < x)
        {
            mDirection = -1;
        }
        else
        {
            mDirection = 1;
        }

        if (Math.abs(x - foodX) <= 10 && Math.abs(y - foodY) <= 10)
        {
            mCondition = ISEATING;
        }
    }

    public void swim()
    {
        mFoodInStomach--;
        int xDistance = playX - x;
        int yDistance = playY - y;
        if (playX < x)
        {
            mDirection = -1;
        }
        else
        {
            mDirection = 1;
        }
        if (Math.abs(xDistance) < 5 && Math.abs(yDistance) < 5)
        {
            playY = (int) - (Math.random() * mTankHeight/2) + 100;
            playX = (int) (Math.ceil(Math.random() * mTankWidth) - mTankWidth/2);
        }
        if (mFoodInStomach <= 0)
        {
            mCondition = ISHUNGRY;
            foodX = (int) (Math.ceil(Math.random() * mTankWidth) - mTankWidth/2) - 100;
        }
    }

    public int getFacingDirection()
    {
        return mDirection;
    }


}
