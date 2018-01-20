package bmtestv4.android.mohalim.bmtestv4.Result;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import bmtestv4.android.mohalim.bmtestv4.MainActivity;
import bmtestv4.android.mohalim.bmtestv4.ResultActivity;

/**
 * Created by Mohamed ALi on 1/19/2018.
 */

public class Circle extends View {

    private static final int START_ANGLE_POINT = 90;

    private final Paint paint;
    private final RectF rect;

    private float angle;

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);

        final int strokeWidth = MainActivity.circleStroke;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);

        //size 200x200 example
        rect = new RectF(strokeWidth, strokeWidth, MainActivity.circlCorners + strokeWidth,
                MainActivity.circlCorners + strokeWidth);

        Log.v("string",""+MainActivity.circlCorners);

        //Initial Angle (optional, it can be zero)
        angle = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rect, START_ANGLE_POINT, angle, false, paint);
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setColor(int color){
        paint.setColor(color);
    }


}

