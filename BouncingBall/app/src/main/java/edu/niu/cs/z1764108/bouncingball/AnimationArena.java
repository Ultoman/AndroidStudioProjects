package edu.niu.cs.z1764108.bouncingball;

import android.graphics.Canvas;

/**
 * Created by JAB on 4/13/2017.
 */

public class AnimationArena {

    private Ball myBall;

    public AnimationArena()
    {
        myBall = new Ball();
    }

    public void update(int width, int height)
    {
        myBall.move(0,0,width,height);
    }

    public void draw(Canvas canvas)
    {
        canvas.drawRGB(156,174,216);
        myBall.draw(canvas);
    }
}
