package edu.niu.cs.z1764108.bouncingball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by JAB on 4/13/2017.
 */

public class Ball {
    private final int RADIUS = 15, REVERSE = -1;

    private int x, y, velX, velY;

    public Ball() {
        x = 100; y = 100; velX = 20; velY = 20;
    }

    public void move(int leftWall, int topWall, int rightWall, int bottomWall)
    {
        x += velX;
        y += velY;

        if(y > bottomWall - RADIUS)
        {
            y = bottomWall - RADIUS;
            velY *= REVERSE;
        }
        else if(y < topWall + RADIUS)
        {
            y = topWall + RADIUS;
            velY *= REVERSE;
        }

        if(x > rightWall - RADIUS)
        {
            x = rightWall - RADIUS;
            velX *= REVERSE;
        }
        else if (x < leftWall + RADIUS)
        {
            x = leftWall + RADIUS;
            velX *= REVERSE;
        }
    }

    public void draw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(200,0,0));
        canvas.drawCircle(x,y,RADIUS,paint);
    }

}
