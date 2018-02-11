package edu.niu.cs.z1764108.bouncingball;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by JAB on 4/13/2017.
 */

public class BounceSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private BounceThread bounceThread;

    public BounceSurfaceView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        bounceThread = new BounceThread(surfaceHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        bounceThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        bounceThread.endBounce();
        Thread dummyThread = bounceThread;
        bounceThread = null;
        dummyThread.interrupt();
    }
}
