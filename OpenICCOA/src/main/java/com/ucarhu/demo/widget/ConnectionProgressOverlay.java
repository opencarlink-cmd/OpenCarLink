package com.ucarhu.demo.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.opencarlink.iccoa.R;

/**
 * 连接进度浮层。
 *
 * 展示设备名称、百分比和旋转动画，只负责视图显示，不参与连接状态判断。
 */
public class ConnectionProgressOverlay {

    private ImageView spinnerView;

    private Animation progressAnimation;

    private TextView progressTextView;

    private TextView modelTextView;

    private View contentView;

    private FrameLayout rootContainer;

    private boolean showing = false;

    public ConnectionProgressOverlay(Context context, FrameLayout frameLayout) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.dialog_progess, (ViewGroup) null);
        this.contentView = viewInflate;
        this.spinnerView = (ImageView) viewInflate.findViewById(R.id.img);
        this.progressTextView = (TextView) this.contentView.findViewById(R.id.tv_progress);
        this.modelTextView = (TextView) this.contentView.findViewById(R.id.tv_model);
        this.progressAnimation = AnimationUtils.loadAnimation(context, R.anim.progress);
        this.rootContainer = frameLayout;
    }

    public void dismiss() {
        if (this.showing) {
            this.rootContainer.removeView(this.contentView);
            this.showing = false;
        }
        this.spinnerView.clearAnimation();
    }

    public void show() {
        if (!this.showing) {
            this.rootContainer.addView(this.contentView, new FrameLayout.LayoutParams(-2, -2, 17));
            this.showing = true;
        }
        this.spinnerView.startAnimation(this.progressAnimation);
    }

    public void updateProgress(String str, int i) {
        this.modelTextView.setText(str);
        this.progressTextView.setText(i + "%");
    }
}
