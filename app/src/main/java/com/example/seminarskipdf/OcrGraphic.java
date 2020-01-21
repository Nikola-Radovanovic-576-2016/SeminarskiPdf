package com.example.seminarskipdf;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.seminarskipdf.camera.GraphicOverlay;
import com.google.android.gms.vision.text.TextBlock;


public class OcrGraphic extends GraphicOverlay.Graphic {

    private int id;

    private static final int TEXT_COLOR = Color.WHITE;

    private static Paint rectPaint;
    private static Paint textPaint;
    private final TextBlock text;

    OcrGraphic(GraphicOverlay overlay, TextBlock text) {
        super(overlay);

        this.text = text;

        if (rectPaint == null) {
            rectPaint = new Paint();
            rectPaint.setColor(TEXT_COLOR);
            rectPaint.setStyle(Paint.Style.STROKE);
            rectPaint.setStrokeWidth(4.0f);
        }

        if (textPaint == null) {
            textPaint = new Paint();
            textPaint.setColor(TEXT_COLOR);
            textPaint.setTextSize(54.0f);
        }

        postInvalidate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TextBlock getTextBlock() {
        return text;
    }


    public boolean contains(float x, float y) {
        return false;
    }


    @Override
    public void draw(Canvas canvas) {
        if (text == null) {
            return;
        }

        // Iscratava pravougaonik.
        RectF rect = new RectF(text.getBoundingBox());
        rect = translateRect(rect);
        canvas.drawRect(rect, rectPaint);

        // Ispisuje tekst u donjem levom uglu pravougaonika.
        canvas.drawText(text.getValue(), rect.left, rect.bottom, textPaint);
    }
}
