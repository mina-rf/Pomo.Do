package com.sharif.PomoDo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by mina on 7/9/16.
 */
public class TimerView extends View {
    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("TAG", "onDraw ");
//        super.onDraw(canvas);
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.WHITE);
//        canvas.drawCircle(200,200,100,p);

        final RectF oval = new RectF();
        oval.set(100,100,500,500);
        p.setStrokeWidth(10);
        canvas.drawArc(oval,0,90,false , p);
//        canvas.drawText("salam",50 , 50 , p);
        Log.d("TAG", "onDraw end ");

    }

}
