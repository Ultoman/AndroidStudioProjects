package com.example.z1764108.fishy;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

/*
James Bonasera
Z1764108
Assignment 5 - Virtual Pet
Added touch event for you to tell the alien where the cow is by touching
Changed background and pictures
 */

public class MainActivity extends AppCompatActivity {

    private Thread calculateMovementThread;

    //Field elements and properties
    private ImageView shipImageView, cowImageView;
    private Ship mShip;
    private int fieldWidth, fieldHeight;
    private FrameLayout fieldLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fieldLayout = findViewById(R.id.container);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        fieldWidth = size.x;
        fieldHeight = size.y;
        int initialXPos = 0;
        int initialYPos = 0;

        mShip = new Ship(initialXPos, initialYPos, 2, fieldWidth, fieldHeight);

        buildTank();

        cowImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_UP)
                {
                    mShip.touchedCow = true;
                }
                return true;
            }
        });

        calculateMovementThread = new Thread(calculateMovement);
        calculateMovementThread.start();

    }

    public void buildTank()
    {
        LayoutInflater shipInflater;
        shipInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//add ship
        shipImageView = (ImageView)shipInflater.inflate(R.layout.ship,null);
        shipImageView.setX((float)0);
        shipImageView.setY((float)0);
        shipImageView.setScaleX((float).3);
        shipImageView.setScaleY((float).2);
        fieldLayout.addView(shipImageView,0);

        LayoutInflater inflater;
        inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//add cow
        cowImageView = (ImageView)inflater.inflate(R.layout.cow,null);
        cowImageView.setX((float)0);
        cowImageView.setY((float)0);
        cowImageView.setScaleX((float).3);
        cowImageView.setScaleY((float).2);
        fieldLayout.addView(cowImageView,0);
    }

    /********************************************************************/

    private Runnable calculateMovement = new Runnable()
    {
        private final static int DELAY = 200;
        @Override
        public void run()
        {
            try
            {
                while(true)
                {
                    mShip.move();
                    Thread.sleep(DELAY);
                    updateFieldHandler.sendEmptyMessage(0);
                }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    };

    public Handler updateFieldHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            //turn ship in correct direction
            shipImageView.setScaleX((float).3*mShip.getFacingDirection());
            //put ship in correct location
            shipImageView.setX((float)mShip.x);
            shipImageView.setY((float)mShip.y);
            //put cow in correct location
            cowImageView.setX((float)mShip.cowX);
            cowImageView.setY((float)mShip.cowY);
        }
    };
}
