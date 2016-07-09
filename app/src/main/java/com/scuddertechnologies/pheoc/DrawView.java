package com.scuddertechnologies.pheoc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Scudder on 7/8/2016.
 */
public class DrawView extends View {
    private Paint paint = new Paint();
    private Path path = new Path();

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //smoother
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        //rounded w/ direction change
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);

        //5 pixels thick
        paint.setStrokeWidth(5f);
    }

    //override onDraw of View class to draw user drawing
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    //override onTouchEvent of View class to draw user drawing on touch
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //get point on view
        float xPos = event.getX();
        float yPos = event.getY();

        switch (event.getAction()) {

            //touch down on screen to start drawing
            case MotionEvent.ACTION_DOWN:
                path.moveTo(xPos, yPos);
                return true;

            //draw
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xPos, yPos);
                break;

            //removed touch from screen (do nothing)
            case MotionEvent.ACTION_UP:
                break;

            //anything else
            default:
                return false;
        }

        //schedule a repaint
        invalidate();
        return true;
    }
}
