package com.example.paint_secondly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyCanvas extends View {
    private Path myPath;
    Canvas canvas;
    private int paintColor = 0xFF660000;
    boolean erase = false;
    Paint painting, canvasPainting;
    Bitmap bitmap;
    private float brushSize, lastBrushSize;
    public MyCanvas(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        setupDrawing();
    }
    public void startNew(){
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }
    public void erasing(){
        this.erase = !erase;
        if(erase){
            painting.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }
        else{
            painting.setXfermode(null);
        }}
    @Override
    protected void onSizeChanged(@Nullable int weight, @Nullable int height, int old_weight, int old_height){
        super.onSizeChanged(weight, height, old_weight, old_height);
        bitmap = Bitmap.createBitmap(weight, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }
    public void setBrushSize(float newSize){
        float amount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, newSize, getResources().getDisplayMetrics());
        brushSize = amount;
        painting.setStrokeWidth(brushSize);
    }
    public void setLastBrushSize(float lastSize){
        lastBrushSize = lastSize;
    }
    public float getBrushSize(){
        return lastBrushSize;
    }
    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(bitmap, 0, 0, canvasPainting);
        canvas.drawPath( myPath, painting);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float myX = event.getX();
        float myY = event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                myPath.moveTo(myX, myY);
                break;
            case MotionEvent.ACTION_MOVE:
                myPath.lineTo(myX, myY);
                break;
            case MotionEvent.ACTION_UP:
                canvas.drawPath(myPath, painting);
                myPath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }
    public void setColor(String newColor){
        invalidate();
        paintColor = Color.parseColor(newColor);
        painting.setColor(paintColor);
    }
    public void setupDrawing(){
        myPath = new Path();
        painting = new Paint();
        painting.setColor(paintColor);
        painting.setAntiAlias(true);
        painting.setStrokeWidth(5);
        painting.setStyle(Paint.Style.STROKE);
        painting.setStrokeJoin(Paint.Join.ROUND);
        painting.setStrokeCap(Paint.Cap.ROUND);
        canvasPainting = new Paint(Paint.DITHER_FLAG);
        brushSize = 5;
        lastBrushSize = brushSize;
        painting.setStrokeWidth(brushSize);


    }
}
