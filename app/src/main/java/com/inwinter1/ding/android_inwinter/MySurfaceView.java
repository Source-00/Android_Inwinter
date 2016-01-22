package com.inwinter1.ding.android_inwinter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * MySurfaceView
 *
 * @author: DING
 * @time: 2016/1/22 10:18
 */
class MySurfaceView extends SurfaceView
        implements SurfaceHolder.Callback
    ,Runnable
{

    public final static String TAG="MySurfaceView";

    private Canvas canvas;
    private Paint paint;
    private SurfaceHolder holder;
    private Thread thread;
    private  boolean flag;
    private float x;
    private float y;
    private float speedx,speedy;
    private int radius;
    private int color;




    public MySurfaceView(Context context) {
        super(context);
        init();
        initGame();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initGame();
    }

    /**
     * 初始化
     */
    private void init(){

        holder=getHolder();
        holder.addCallback(this);

        paint=new Paint();
        paint.setColor(Color.YELLOW);
        paint.setAntiAlias(true);//抗锯齿


    }

    private void initGame(){
        x=getWidth()/2;
        y=getHeight()/2;
        speedx=10;
        speedy=10;
        color=Color.YELLOW;
        radius=100;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
       Log.i(TAG, "surfaceCreated");

        flag=true;

        thread=new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        Log.i(TAG, "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        Log.i(TAG, "surfaceDestroyed");
    }

    /**
     *
     * @param canvas,
     */
    private void myDraw(Canvas canvas){

        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        paint.setColor(Color.RED);

        canvas.drawCircle(x, y, 100, paint);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x=(int)event.getX();
        int y=(int)event.getY();


        int[] colors =new int[]{Color.BLUE,Color.GRAY,Color.YELLOW,Color.GREEN};
        color=colors[new Random().nextInt(colors.length)];

        color=Color.argb(0,x*y%255, x % 255, y % 255);
        radius=new Random().nextInt(10)+50;
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 逻辑
     */
    private void logic(){
        x+=speedx;
        y+=speedy;
        if(x>=getWidth()||x<0)
        {
            speedx=-speedx;
        }
        if(y>=getHeight()||y<0)
        {
            speedy=-speedy;
        }

    }

    @Override
    public void run() {
        while(flag)
        {

            long start=System.currentTimeMillis();
            canvas=holder.lockCanvas();
            if(null!=canvas){
                myDraw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
            logic();
            long end=System.currentTimeMillis();

            if(end-start<50){
                try {
                    Thread.sleep(50-(end-start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
