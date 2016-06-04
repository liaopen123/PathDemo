package com.example.liaopenghui.pathdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liaopenghui on 16/6/4.
 */
public class PathView extends View {
    private int mWidth;
    private int mHeight;
    private Paint mPaint;

    public PathView(Context context) {
        super(context);
        initPaint();
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();             // 创建画笔
        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setStrokeWidth(10);              // 边框宽度 - 10
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth/2,mHeight/2);//不然移动canvas的0,0点的位置  ,不然直接调用moveTo方法去调用path的起点
        Path path = addXxxAndArcTo();
        canvas.drawPath(path,mPaint);//把path回执的轨迹变成封闭图形

    }

   //moveTo和setLastPoint的区别
    private Path moveToAndSetLastPoint() {
        Path path = new Path();
        path.lineTo(200,200);
//        path.moveTo(300,00);   //moveTo的意思是把点挪到另外一个点--->是不会画出轨迹的来的
        path.setLastPoint(200,100);//setLastPonint() 是把path最后的一个点的位置挪到另外一个点,相当于改变了目标
        path.lineTo(200,0);
        path.close();
        return path;
    }

    //addXXXX和arcTo的区别:
    private Path addXxxAndArcTo(){
        Path path = new Path();
        path.addCircle(0,0,300, Path.Direction.CCW);
        return path;

    }
}
