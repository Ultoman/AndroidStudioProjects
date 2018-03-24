package com.example.z1764108.fishy;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Thread calculateMovementThread;

    //Tank elements and properties
    private ImageView fishImageView;
    private Fish mFish;
    private int tankWidth, tankHeight;
    private FrameLayout fishTankLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fishTankLayout = findViewById(R.id.container);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        tankWidth = size.x;
        tankHeight = size.y;
        int initialXPos = 0;
        int initialYPos = 0;

        mFish = new Fish(initialXPos, initialYPos, 2, tankWidth, tankHeight);

        buildTank();

        calculateMovementThread = new Thread(calculateMovement);
        calculateMovementThread.start();

    }

    public void buildTank()
    {
        LayoutInflater inflater;
        inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//add foliage
        ImageView foliageImage = (ImageView)inflater.inflate(R.layout.foliage,null);
        foliageImage.setX((float)0);
        foliageImage.setY((float)0);
        foliageImage.setAlpha((float).97);
        fishTankLayout.addView(foliageImage,0);

        LayoutInflater fishInflater;
        fishInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//add fish
        fishImageView = (ImageView)fishInflater.inflate(R.layout.fish,null);
        fishImageView.setX((float)0);
        fishImageView.setY((float)0);
        fishImageView.setScaleX((float).3);
        fishImageView.setScaleY((float).2);
        fishTankLayout.addView(fishImageView,0);
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
                    mFish.move();
                    Thread.sleep(DELAY);
                    updateTankHandler.sendEmptyMessage(0);
                }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    };

    public Handler updateTankHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            //turn fish in correct direction
            fishImageView.setScaleX((float).3*mFish.getFacingDirection());
            //put fish in correct location
            fishImageView.setX((float)mFish.x);
            fishImageView.setY((float)mFish.y);
        }
    };
}
