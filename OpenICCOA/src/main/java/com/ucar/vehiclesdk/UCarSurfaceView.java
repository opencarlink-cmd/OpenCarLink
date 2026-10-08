package com.ucar.vehiclesdk;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import com.ucarhu.demo.logging.EasyLogger;

public class UCarSurfaceView extends SurfaceView {

    private static final String TAG = "UCarSurfaceView";

    public UCarSurfaceView(Context context) {
        super(context);
    }

    public UCarSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        EasyLogger.info(TAG, "onTouchEvent, action:" + motionEvent.getAction());
        int action = motionEvent.getAction();
        int pointerCount = motionEvent.getPointerCount();
        int[] pointerIds = new int[pointerCount];
        int[] xCoordinates = new int[pointerCount];
        int[] yCoordinates = new int[pointerCount];
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = motionEvent.getPointerId(i);
            int pointerIndex = motionEvent.findPointerIndex(pointerId);
            pointerIds[i] = pointerId;
            xCoordinates[i] = (int) motionEvent.getX(pointerIndex);
            yCoordinates[i] = (int) motionEvent.getY(pointerIndex);
        }
        UCarAdapter.getInstance().sendTouchEvent(action, pointerCount, pointerIds, xCoordinates, yCoordinates);
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            performClick();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
