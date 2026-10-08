package com.ucarhu.demo.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ucarhu.demo.logging.EasyLogger;
import java.util.Locale;

/**
 * 六位 PIN 码展示控件。
 *
 * 控件内部固定创建 6 个等宽 TextView。setPinCode() 会先把输入规范化为 6 位数字，
 * 再按位写入每个格子，避免前导 0 丢失或异常 PIN 长度导致界面显示不完整。
 */
public class UCarPinCodeView extends LinearLayout {

    private static final String TAG = "UCarPinCodeView";

    private static final int PIN_LENGTH = 6;

    private static final int DEFAULT_CELL_WIDTH_DP = 58;

    private static final int DEFAULT_CELL_HEIGHT_DP = 68;

    private static final int DEFAULT_CELL_GAP_DP = 10;

    private final TextView[] digitViews;

    private final int cellWidth;

    private final int cellHeight;

    private final int cellGap;

    private String pinCode;

    public UCarPinCodeView(@NonNull Context context) {
        this(context, null);
    }

    public UCarPinCodeView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UCarPinCodeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.digitViews = new TextView[PIN_LENGTH];
        this.pinCode = "";
        this.cellWidth = dpToPx(DEFAULT_CELL_WIDTH_DP);
        this.cellHeight = dpToPx(DEFAULT_CELL_HEIGHT_DP);
        this.cellGap = dpToPx(DEFAULT_CELL_GAP_DP);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        for (int index = 0; index < PIN_LENGTH; index++) {
            TextView textView = new TextView(context);
            configureDigitView(textView);
            this.digitViews[index] = textView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.cellWidth, this.cellHeight);
            if (index > 0) {
                layoutParams.leftMargin = this.cellGap;
            }
            addView(textView, layoutParams);
        }
    }

    public String getPinCode() {
        return this.pinCode;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getChildCount() != PIN_LENGTH) {
            throw new RuntimeException("pin code must be length 6");
        }
        int desiredWidth = (this.cellWidth * PIN_LENGTH) + (this.cellGap * (PIN_LENGTH - 1));
        int desiredHeight = this.cellHeight;
        int measuredWidth = View.resolveSize(desiredWidth, widthMeasureSpec);
        int measuredHeight = View.resolveSize(desiredHeight, heightMeasureSpec);
        super.onMeasure(
                View.MeasureSpec.makeMeasureSpec(measuredWidth, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(measuredHeight, View.MeasureSpec.EXACTLY)
        );
    }

    public void setPinCode(@NonNull String pinCode) {
        this.pinCode = normalizePinCode(pinCode);
        for (int index = 0; index < PIN_LENGTH; index++) {
            int nextIndex = index + 1;
            this.digitViews[index].setText(this.pinCode.substring(index, nextIndex));
        }
    }

    private void configureDigitView(TextView textView) {
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setIncludeFontPadding(false);
        GradientDrawable background = new GradientDrawable();
        background.setColor(0x55FFFFFF);
        background.setCornerRadius(dpToPx(2));
        textView.setBackground(background);
    }

    private String normalizePinCode(String rawPinCode) {
        String digitsOnly = rawPinCode.replaceAll("\\D", "");
        if (digitsOnly.length() > PIN_LENGTH) {
            EasyLogger.warn(TAG, "PIN code is longer than 6 digits, keep the last 6 digits.");
            digitsOnly = digitsOnly.substring(digitsOnly.length() - PIN_LENGTH);
        }
        if (digitsOnly.length() < PIN_LENGTH) {
            EasyLogger.warn(TAG, "PIN code is shorter than 6 digits, left pad with 0.");
            return String.format(Locale.US, "%6s", digitsOnly).replace(' ', '0');
        }
        return digitsOnly;
    }

    private int dpToPx(int dp) {
        return Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                getResources().getDisplayMetrics()
        ));
    }
}
