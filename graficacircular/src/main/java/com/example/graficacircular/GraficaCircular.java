package com.example.graficacircular;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GraficaCircular extends View {

    private final float TEXT_SIZE = 110;
    private final int DEFAULT_SIZE = 300;
    private int width, height;

    private int graphColor = Color.YELLOW;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Integer percentage;

    public GraficaCircular(Context context) {
        super(context);


    }

    public GraficaCircular(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = this.getContext().obtainStyledAttributes(attrs, R.styleable.GraficaCircular);
        this.percentage = ta.getInt(R.styleable.GraficaCircular_percentage, 0);

        ta.recycle();

    }

    // Esto hay que generarlo
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
        paint.setColor(graphColor);
        paint.setStyle(Paint.Style.FILL);
        float radius = width / 2f;
        canvas.drawCircle(width / 2f, height / 2f, radius, paint);
         */

        Rect rec = canvas.getClipBounds();
        // Parte positiva del porcentaje (en rojo)
        float anguloprev = (360*percentage)/(float)100.0;
        Paint p1 = new Paint(); p1.setStyle(Paint.Style.FILL);
        p1.setColor(Color.RED);
        //canvas.drawArc(new RectF(rec), 0, anguloprev, true, p1);
        canvas.drawArc(new RectF(rec.top, rec.left, rec.right, rec.bottom ), 0, anguloprev, true, p1);
        // Parte negativa del porcentaje (en azul)
        float angulopost = (360*(100-percentage))/(float)100.0; p1.setColor(Color.BLUE);
        //canvas.drawArc(new RectF(rec), anguloprev, angulopost, true, p2);
        canvas.drawArc(new RectF(rec.top, rec.left, rec.right, rec.bottom), anguloprev, angulopost, true, p1);

        // Texto con el porcentaje
        Paint ptexto = new Paint(); ptexto.setTypeface(Typeface.SANS_SERIF); ptexto.setTextAlign(Paint.Align.CENTER); ptexto.setAntiAlias(true);
        ptexto.setColor(Color.BLACK);
        ptexto.setTextSize(TEXT_SIZE);
        canvas.drawText(percentage + "%", width / 2, height - 2, ptexto);
    }

    // Esto hay que generarlo
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // Permite redimensionar el circulo a nuestro gusto
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        width = DEFAULT_SIZE; height = DEFAULT_SIZE;

        switch(widthMode) {
            case MeasureSpec.EXACTLY:// dp o match_constraint
                width=widthSize;
                break;
            case MeasureSpec.AT_MOST: //wrap_content
                if (width > widthSize) {
                    width = widthSize;
                } break;
        }

        switch(heightMode) {
            case MeasureSpec.EXACTLY:
                height=heightSize;
                break;
            case MeasureSpec.AT_MOST:
                if (height > heightSize) {
                    height = heightSize;
                } break;
        }
        this.setMeasuredDimension(width, height);
    }


}
