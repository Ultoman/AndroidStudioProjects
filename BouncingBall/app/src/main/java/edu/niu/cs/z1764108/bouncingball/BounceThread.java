package edu.niu.cs.z1764108.bouncingball;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by JAB on 4/13/2017.
 */

public class BounceThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private AnimationArena animationArena;
    private boolean isRunning;

    public BounceThread(SurfaceHolder sh)
    {
        surfaceHolder = sh;
        animationArena = new AnimationArena();
        isRunning = true;
    }

    public void run()
    {
        try
        {
            while(isRunning)
            {
                Canvas canvas = surfaceHolder.lockCanvas();
                animationArena.update(canvas.getWidth(), canvas.getHeight());
                animationArena.draw(canvas);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    public void endBounce()
    {
        isRunning = false;
    }
}
