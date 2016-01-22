package com.inwinter1.ding.android_inwinter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * MyView
 *
 * @author: DING
 * @time: 2016/1/22 8:28
 */
public class MyView extends View {

    //声明一个Paint变量
    private Paint paint;
    private int x;
    private int y;

    public MyView(Context context) {
        super(context);
        init();
    }

    /**
     * 初始化
     */
    private void init(){
        paint=new Paint();
        x=100;
        y=100;

        paint.setColor(Color.RED);
        paint.setStrokeWidth(15);
        paint.setAntiAlias(true);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        // drawText(canvas);
        // drawPath(canvas);
        //myDraw(canvas);

    }

    private void drawText(Canvas canvas){
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);

        canvas.drawLine(0, 0, getWidth(), getHeight(), paint);
        canvas.drawCircle(100,100,100,paint);
        //画圆
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);

        canvas.drawCircle(100, 200, 100, paint);

        //画矩形
        canvas.drawRect(100, 200, 200, 100, paint);

       // canvas.drawRoundRect(100,700,200,800,10,paint);

    }

    /**
     * 路径绘制
     * @param canvas
     */
    private void drawPath(Canvas canvas){
        Path path=new Path();
        path.moveTo(100, 100);
        path.lineTo(100, 300);
        path.lineTo(200, 250);

        paint.setColor(Color.YELLOW);

        canvas.drawPath(path, paint);

        Path path1=new Path();
        path1.addCircle(500, 500, 200, Path.Direction.CW);
        path1.moveTo(500, 300);
        path1.lineTo(500, 700);
        path1.moveTo(300, 500);
        path1.lineTo(700, 500);


        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        canvas.drawPath(path1, paint);
    }

    /**
     * 画图
     * @param canvas
     */
    private void drawBitmap(Canvas canvas){
//        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
//       // canvas.drawBitmap(bitmap,500,500,paint);
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//
//        //修改坐标系
//        canvas.save();
//        canvas.translate(getWidth() / 2, getHeight() / 2);
//        //canvas.drawCircle(0, 0, 20, paint);
//        canvas.drawLine(0,-getHeight()/2,0,getHeight()/2,paint);
//        canvas.drawLine(-getWidth()/2,0,getWidth()/2,0,paint);
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        canvas.restore();
//
//        canvas.drawCircle(200,200,100,paint);
//
//        //只在save()和restore()中改变
//        canvas.save();
//        canvas.rotate(90);//画布旋转90度
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        canvas.restore();

        canvas.drawLine(0,0,x,y,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x= (int) event.getX();
        y= (int) event.getY();
        invalidate();
        return super.onTouchEvent(event);
    }
}
