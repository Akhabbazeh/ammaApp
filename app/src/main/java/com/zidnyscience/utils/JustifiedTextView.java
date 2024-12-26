package com.zidnyscience.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

public class JustifiedTextView extends androidx.appcompat.widget.AppCompatTextView {
    private static final float MIN_TEXT_SIZE = 12;
    private static final float MAX_TEXT_SIZE = 65;

    public JustifiedTextView(Context context) {
        super(context);
    }

    public JustifiedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JustifiedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = getPaint();
        float textSize = adjustTextSize(paint);
        paint.setTextSize(textSize);

        String text = getText().toString();
        String[] lines = text.split("\n");

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float lineHeight = fontMetrics.descent - fontMetrics.ascent;
        float lineSpacingExtra = 10;
        float totalTextHeight = lines.length * lineHeight + (lines.length - 1) * lineSpacingExtra;

        float availableHeight = getHeight() - getPaddingTop() - getPaddingBottom();
        float availableWidth = getWidth() - getPaddingLeft() - getPaddingRight();

        float startY = getPaddingTop() + (availableHeight - totalTextHeight) / 2 - fontMetrics.ascent;

        for (String line : lines) {
            String[] words = line.trim().split("\\s+");
            float totalWordsWidth = 0;

            for (String word : words) {
                totalWordsWidth += paint.measureText(word);
            }

            float remainingSpaceForLine = availableWidth - totalWordsWidth;
            float spaceBetweenWords = words.length > 1 ? remainingSpaceForLine / (words.length - 1) : 0;

            float x = remainingSpaceForLine > 0 && textSize == MAX_TEXT_SIZE
                    ? getPaddingLeft() + remainingSpaceForLine / 2
                    : getPaddingLeft();

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                canvas.drawText(word, x, startY, paint);
                x += paint.measureText(word);

                if (i < words.length - 1) {
                    x += spaceBetweenWords;
                }
            }

            startY += lineHeight + lineSpacingExtra;
        }
    }



    private float adjustTextSize(Paint paint) {
        float testTextSize = MAX_TEXT_SIZE;
        float width = getWidth() - getPaddingLeft() - getPaddingRight();
        String text = getText().toString();

        paint.setTextSize(testTextSize);

        while (isTextTooWide(text, paint, width) && testTextSize > MIN_TEXT_SIZE) {
            testTextSize -= 1;
            paint.setTextSize(testTextSize);
        }

        return testTextSize;
    }

    private boolean isTextTooWide(String text, Paint paint, float maxWidth) {
        String[] lines = text.split("\n");

        for (String line : lines) {
            if (paint.measureText(line) > maxWidth) {
                return true;
            }
        }

        return false;
    }
}
