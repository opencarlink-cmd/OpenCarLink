package com.ucarhu.demo.widget;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 简单的 Activity 全屏浮层容器。
 *
 * 它直接向 android.R.id.content 添加或移除 View，用于复用原项目的弹窗展示方式。
 */
public class FullScreenOverlay {

    private Activity hostActivity;

    private View contentView;

    private boolean showing = false;

    public FullScreenOverlay(Context context) {
        if (!(context instanceof Activity)) {
            throw new IllegalArgumentException("context must be activity");
        }
        this.hostActivity = (Activity) context;
    }

    public void dismiss() {
        if (!this.showing || this.contentView == null) {
            return;
        }
        FrameLayout frameLayout = (FrameLayout) this.hostActivity.findViewById(R.id.content);
        if (frameLayout != null) {
            frameLayout.removeView(this.contentView);
        }
        this.showing = false;
    }

    public boolean isShowing() {
        return this.showing;
    }

    public void setContentView(View view) {
        if (!isShowing()) {
            this.contentView = view;
            return;
        }
        dismiss();
        this.contentView = view;
        show();
    }

    public void show() {
        if (isShowing()) {
            dismiss();
        }
        FrameLayout frameLayout = (FrameLayout) this.hostActivity.findViewById(R.id.content);
        if (frameLayout == null || this.contentView == null) {
            return;
        }
        frameLayout.addView(this.contentView, new FrameLayout.LayoutParams(-1, -1));
        this.showing = true;
    }
}
