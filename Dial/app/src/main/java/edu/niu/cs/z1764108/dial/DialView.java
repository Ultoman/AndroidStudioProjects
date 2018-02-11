package edu.niu.cs.z1764108.dial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by JAB on 4/11/2017.
 */

public class DialView extends View {
    private float angle;
    private Paint paint;

    public DialView(Context context) {
        super(context);

        angle = 0;
        //Create paint object and set width
        paint = new Paint(Paint.ANTI_ALIAS_FLAG); //smoother line
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(50);
    }//Constructor

    @Override
    protected void onDraw(Canvas canvas) {
        //set background color
        canvas.drawRGB(200,200,200);

        int canvasWidth = getWidth(),
            canvasHeight = getHeight(),
            radius;

        //set the radius based on orientation
        if(canvasWidth > canvasHeight)
        {
            radius = canvasHeight / 2;
        }
        else
        {
            radius = canvasWidth / 2;
        }

        angle++;
        if (angle >= 360)
        {
            angle = 0;
        }

        float radians = (float)(angle * 180 / Math.PI),
              startX = canvasWidth / 2,
              startY = canvasHeight / 2,
              endX = (float)(startX + radius * Math.sin(radians)),
              endY = (float)(startY - radius * Math.cos(radians));

        paint.setColor(Color.BLUE);

        //draw line
        canvas.drawLine(startX, startY, endX, endY, paint);
    }//onDraw


    public void update()
    {
        invalidate();
    }

}//DialView
