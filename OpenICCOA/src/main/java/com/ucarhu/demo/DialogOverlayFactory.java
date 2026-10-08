package com.ucarhu.demo;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import com.opencarlink.iccoa.R;
import com.ucarhu.demo.widget.FullScreenOverlay;

/**
 * 全屏浮层弹窗工厂。
 *
 * 项目里的连接失败提示不是系统 Dialog，而是把布局直接挂到 Activity 根视图上。
 * 这个类集中负责布局填充、按钮文案设置，以及点击后是否自动关闭浮层的判断。
 */
public class DialogOverlayFactory {

    public interface DialogActionCallback {
        /**
         * @return true 表示回调已自行处理关闭逻辑，false 表示由工厂自动关闭浮层。
         */
        boolean onDialogAction();
    }

    static void handlePositiveActionClick(DialogActionCallback actionCallback, FullScreenOverlay overlay, View view) {
        if (actionCallback == null || !actionCallback.onDialogAction()) {
            overlay.dismiss();
        }
    }

    static void handleNegativeActionClick(DialogActionCallback actionCallback, FullScreenOverlay overlay, View view) {
        if (actionCallback == null || !actionCallback.onDialogAction()) {
            overlay.dismiss();
        }
    }

    public static FullScreenOverlay createConfirmCancelDialog(Activity activity, @DrawableRes int i, int i2, String str, CharSequence charSequence, String str2, String str3, final DialogActionCallback actionCallback, final DialogActionCallback actionCallback2) {
        final FullScreenOverlay overlay = new FullScreenOverlay(activity);
        View viewInflate = LayoutInflater.from(activity).inflate(i, (ViewGroup) null, false);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_dialog_icon_title);
        if (i2 != 0) {
            textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, ContextCompat.getDrawable(activity, i2), (Drawable) null, (Drawable) null);
        }
        textView.setText(str);
        if (i2 == 0 && TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
        }
        ((TextView) viewInflate.findViewById(R.id.tv_dialog_message)).setText(charSequence);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_dialog_done);
        textView2.setText(str2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                DialogOverlayFactory.handlePositiveActionClick(actionCallback, overlay, view);
            }
        });
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.tv_dialog_cancel);
        textView3.setText(str3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                DialogOverlayFactory.handleNegativeActionClick(actionCallback2, overlay, view);
            }
        });
        overlay.setContentView(viewInflate);
        return overlay;
    }
}
