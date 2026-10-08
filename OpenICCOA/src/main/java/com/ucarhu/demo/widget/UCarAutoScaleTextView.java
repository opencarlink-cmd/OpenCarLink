package com.ucarhu.demo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import com.opencarlink.iccoa.R;

/**
 * 按屏幕宽度约束自动缩放文字的 TextView。
 *
 * widthPercent 决定控件测量宽度，lengthByChar 可用固定字符数估算最大文字宽度。
 */
public class UCarAutoScaleTextView extends AppCompatTextView {

    private static final String WIDTH_REFERENCE_CHAR = "W";

    private final TextWatcher textScaleWatcher;

    private final float widthPercent;

    private final int lengthByChar;

    private final int screenWidth;

    private final float originalTextSizePx;

    private class TextScaleWatcher implements TextWatcher {
        TextScaleWatcher() {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            UCarAutoScaleTextView.this.refreshTextScale();
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        }
    }

    public UCarAutoScaleTextView(Context context) {
        this(context, null);
    }

    public UCarAutoScaleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UCarAutoScaleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.textScaleWatcher = new TextScaleWatcher();
        float parsedWidthPercent = 1.0f;
        int parsedLengthByChar = 0;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UCarAutoScaleTextView, defStyleAttr, 0);
        if (typedArray != null) {
            try {
                parsedWidthPercent = typedArray.getFloat(R.styleable.UCarAutoScaleTextView_widthPercent, 1.0f);
                parsedLengthByChar = typedArray.getInt(R.styleable.UCarAutoScaleTextView_lengthByChar, 0);
            } finally {
                typedArray.recycle();
            }
        }
        this.lengthByChar = parsedLengthByChar;
        this.widthPercent = Math.max(0.0f, Math.min(parsedWidthPercent, 1.0f));
        this.screenWidth = getResources().getDisplayMetrics().widthPixels;
        this.originalTextSizePx = getTextSize();
    }

    public void refreshTextScale() {
        CharSequence text = getText();
        if (text == null || text.length() == 0 || this.originalTextSizePx <= 0.0f) {
            return;
        }
        int availableWidth = getMeasuredWidth();
        if (availableWidth <= 0) {
            availableWidth = (int) (this.screenWidth * this.widthPercent);
        }
        availableWidth -= getPaddingLeft() + getPaddingRight();
        if (availableWidth <= 0) {
            return;
        }

        TextPaint paint = new TextPaint(getPaint());
        paint.setTextSize(this.originalTextSizePx);
        float textWidth = this.lengthByChar > 0
                ? measureFixedCharWidth(paint, this.lengthByChar)
                : measureLongestLine(paint, text.toString().split("\\n", -1));
        if (textWidth <= 0.0f) {
            return;
        }
        float targetTextSize = textWidth > availableWidth
                ? this.originalTextSizePx * (availableWidth / textWidth)
                : this.originalTextSizePx;
        setTextSize(TypedValue.COMPLEX_UNIT_PX, targetTextSize);
    }

    private float measureFixedCharWidth(TextPaint textPaint, int charCount) {
        return textPaint.measureText(WIDTH_REFERENCE_CHAR) * charCount;
    }

    private float measureLongestLine(TextPaint textPaint, String[] lines) {
        float maxWidth = 0.0f;
        for (String line : lines) {
            float lineWidth = textPaint.measureText(line);
            if (lineWidth > maxWidth) {
                maxWidth = lineWidth;
            }
        }
        return maxWidth;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        refreshTextScale();
        addTextChangedListener(this.textScaleWatcher);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeTextChangedListener(this.textScaleWatcher);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (View.MeasureSpec.getMode(widthMeasureSpec) != View.MeasureSpec.EXACTLY) {
            int fixedWidth = (int) ((this.screenWidth * this.widthPercent) + getPaddingLeft() + getPaddingRight());
            widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(fixedWidth, View.MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        refreshTextScale();
    }
}
