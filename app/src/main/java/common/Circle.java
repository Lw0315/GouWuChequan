package common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.example.asus.gouwuche2.R;

/**
 * Created by asus on 2017/10/24.
 */

public class Circle extends View {
    private Paint paint=new Paint();
    private float mRadius;
    public Circle(Context context) {
       this(context,null);
    }

    public Circle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Circle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray array=getContext().obtainStyledAttributes(attrs, R.styleable.Circle);
        int mColor=array.getColor(R.styleable.Circle_android_color, Color.BLUE);
        mRadius=array.getDimension(R.styleable.Circle_radius,50);//半径
        int mAlpha=array.getInteger(R.styleable.Circle_alpha,10);
        array.recycle();
        paint.setDither(true);//防抖
        paint.setAntiAlias(true);//抗锯齿
        paint.setStrokeWidth(50);//设置画笔的线宽
        paint.setAlpha(mAlpha);
    }

    @Override
    protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
        DisplayMetrics dm = getResources().getDisplayMetrics(); //得到屏幕
        int width = dm.widthPixels/1;
        int height =  dm.heightPixels/2;
        Log.d("TAG","width" + width + "------" + "height" + height);
        canvas.drawCircle(width, height, mRadius, paint);
        canvas.save();
    }
}
