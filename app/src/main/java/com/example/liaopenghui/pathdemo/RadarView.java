package com.example.liaopenghui.pathdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by liaopenghui on 16/6/4.
 */
public class RadarView extends View {
    private static final String TAG = "RadarView";
    private Paint mPaint;
    private int mHeight;
    private int mWidth;
    private  int distance = 200;//坐标与0点的距离
    private int rectFNumber = 8;
    ArrayList<Point> pointList = new ArrayList<>();

    public RadarView(Context context) {
        super(context);
        initPaint();

    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();             // 创建画笔
        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setStrokeWidth(10);              // 边框宽度 - 10
        mPaint.setAntiAlias(true);
        initData();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }
    private void initData(){
        Point point;
        int degree = 360/rectFNumber;
        for(int i=0;i<rectFNumber;i++){
            point = new Point();
//            int pointX = (int) (circleX + radius * Math.sin(Math.PI * percentageOf / 180));
//            int pointY = (int) (circleY - radius * Math.cos(Math.PI * percentageOf / 180));
            point.x = (int)(distance* Math.sin(Math.PI * degree*i / 180));
            point.y = (int)(distance* Math.cos(Math.PI * degree*i / 180));
            Log.e(TAG,"得到的第"+i+"的坐标值为"+"("+point.x+","+point.y+")");
            pointList.add(point);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth/2,mHeight/2);
        Path path = new Path();
        path.moveTo(pointList.get(0).x,pointList.get(0).y);
        for(int i = 1;i<rectFNumber;i++){
            path.lineTo(pointList.get(i).x,pointList.get(i).y);
        }
        path.close();
        canvas.rotate(90);//再旋转90°
        canvas.drawPath(path,mPaint);

        invalidate();
    }
}
