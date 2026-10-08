package com.ucarhu.demo.vehicle.sdk;

public class RandomTouchTestThread extends java.lang.Thread {

    private static final java.lang.String TAG = "RandomTouchTestThread";

    private static final int TOUCH_DOWN_DURATION_MS = 100;

    private static final int MAX_TOUCH_INTERVAL_DELTA_MS = 900;

    private static final int MIN_TOUCH_INTERVAL_MS = 100;

    private final int screenWidth;

    private final int screenHeight;

    private int[] pointerIds;

    private int[] xCoordinates;

    private int[] yCoordinates;

    private long nextTouchIntervalMs;

    private boolean running = false;

    private final java.util.Random random = new java.util.Random();

    private final int pointerCount = 1;

    public RandomTouchTestThread(int width, int height) {
        this.screenHeight = height;
        this.screenWidth = width;
    }

    @Override
    public void interrupt() {
        this.running = false;
        super.interrupt();
    }

    @Override
    public void run() {
        super.run();
        while (this.running && !java.lang.Thread.interrupted()) {
            this.pointerIds = new int[this.pointerCount];
            this.xCoordinates = new int[this.pointerCount];
            this.yCoordinates = new int[this.pointerCount];
            for (int i = 0; i < this.pointerCount; i++) {
                this.pointerIds[i] = 0;
                this.xCoordinates[i] = this.random.nextInt(this.screenWidth);
                this.yCoordinates[i] = this.random.nextInt(this.screenHeight);
            }
            com.ucar.vehiclesdk.UCarAdapter.getInstance().sendTouchEvent(com.ucar.vehiclesdk.UCarCommon.KeyEventActionType.KEY_EVENT_ACTION_DOWN.getValue(), this.pointerCount, this.pointerIds, this.xCoordinates, this.yCoordinates);
            try {
                java.lang.Thread.sleep(TOUCH_DOWN_DURATION_MS);
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(TAG, "sleep duration 1 error.", e2);
            }
            com.ucar.vehiclesdk.UCarAdapter.getInstance().sendTouchEvent(com.ucar.vehiclesdk.UCarCommon.KeyEventActionType.KEY_EVENT_ACTION_UP.getValue(), this.pointerCount, this.pointerIds, this.xCoordinates, this.yCoordinates);
            this.nextTouchIntervalMs = this.random.nextInt(MAX_TOUCH_INTERVAL_DELTA_MS + 1) + MIN_TOUCH_INTERVAL_MS;
            try {
                java.lang.Thread.sleep(this.nextTouchIntervalMs);
            } catch (java.lang.Exception e3) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(TAG, "sleep duration 2 error.", e3);
            }
        }
    }

    @Override
    public synchronized void start() {
        this.running = true;
        super.start();
    }
}
