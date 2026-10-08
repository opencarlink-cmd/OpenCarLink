package com.ucarhu.demo.vehicle;

public class ConnectionHeartbeatTimer {

    private static final java.lang.String TAG = "TimerManager";

    private static final long CONNECT_TIMEOUT_MS = 10000;

    private static final long HEARTBEAT_TIMEOUT_MS = 7000;

    private com.ucarhu.demo.vehicle.ConnectionHeartbeatTimer.TimeoutListener timeoutListener;

    private java.util.TimerTask connectTimeoutTask;

    private java.util.Timer connectTimer;

    private java.util.TimerTask heartbeatTimeoutTask;

    private java.util.Timer heartbeatTimer;

    public class ConnectTimeoutTask extends java.util.TimerTask {
        public ConnectTimeoutTask() {
        }

        @Override
        public void run() {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.ConnectionHeartbeatTimer.TAG, "connect time out");
            if (com.ucarhu.demo.vehicle.ConnectionHeartbeatTimer.this.timeoutListener != null) {
                com.ucarhu.demo.vehicle.ConnectionHeartbeatTimer.this.timeoutListener.onConnectTimeout();
            }
        }
    }

    public class HeartbeatTimeoutTask extends java.util.TimerTask {
        public HeartbeatTimeoutTask() {
        }

        @Override
        public void run() {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.ConnectionHeartbeatTimer.TAG, "heart beat time out");
            if (com.ucarhu.demo.vehicle.ConnectionHeartbeatTimer.this.timeoutListener != null) {
                com.ucarhu.demo.vehicle.ConnectionHeartbeatTimer.this.timeoutListener.onHeartbeatTimeout();
            }
        }
    }

    public interface TimeoutListener {
        void onHeartbeatTimeout();

        void onConnectTimeout();
    }

    private void createConnectTimer() {
        this.connectTimeoutTask = new com.ucarhu.demo.vehicle.ConnectionHeartbeatTimer.ConnectTimeoutTask();
        this.connectTimer = new java.util.Timer();
    }

    private void createHeartbeatTimer() {
        this.heartbeatTimeoutTask = new com.ucarhu.demo.vehicle.ConnectionHeartbeatTimer.HeartbeatTimeoutTask();
        this.heartbeatTimer = new java.util.Timer();
    }

    public void setTimeoutListener(com.ucarhu.demo.vehicle.ConnectionHeartbeatTimer.TimeoutListener timeoutListener) {
        this.timeoutListener = timeoutListener;
    }

    public void cancelConnectTimer() {
        if (this.connectTimer != null) {
            com.ucarhu.demo.logging.EasyLogger.info(TAG, "cancel connect timer");
            this.connectTimer.cancel();
            this.connectTimeoutTask = null;
            this.connectTimer = null;
        }
    }

    public void cancelHeartbeatTimer() {
        if (this.heartbeatTimer != null) {
            com.ucarhu.demo.logging.EasyLogger.info(TAG, "cancel heart beat timer");
            this.heartbeatTimer.cancel();
            this.heartbeatTimeoutTask = null;
            this.heartbeatTimer = null;
        }
    }

    public void scheduleConnectTimeout() {
        createConnectTimer();
        if (this.connectTimer != null) {
            com.ucarhu.demo.logging.EasyLogger.info(TAG, "schedule connect timer");
            this.connectTimer.schedule(this.connectTimeoutTask, CONNECT_TIMEOUT_MS);
        }
    }

    public void scheduleHeartbeatTimeout() {
        createHeartbeatTimer();
        if (this.heartbeatTimer != null) {
            com.ucarhu.demo.logging.EasyLogger.info(TAG, "schedule heart beat timer");
            this.heartbeatTimer.schedule(this.heartbeatTimeoutTask, HEARTBEAT_TIMEOUT_MS);
        }
    }

    public void clearListener() {
        this.timeoutListener = null;
    }
}
