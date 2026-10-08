package com.ucarhu.demo.protocol.logging;

public class AndroidProtocolLogger implements com.ucarhu.demo.protocol.logging.ProtocolLogger {

    private static final java.lang.String TAG = "UCarProtocol";

    private int logLevel = LEVEL_INFO;

    private boolean verboseLogging = false;

    @Override
    public void setLogLevel(int level) {
        if (level > LEVEL_ERROR) {
            this.logLevel = LEVEL_ERROR;
        }
        if (level < LEVEL_VERBOSE) {
            this.logLevel = LEVEL_VERBOSE;
        }
        this.logLevel = level;
    }

    @Override
    public void info(java.lang.String tag, java.lang.String message) {
        if (this.logLevel > LEVEL_INFO) {
            return;
        }
        android.util.Log.i(TAG, tag + "(" + java.lang.Thread.currentThread().getName() + "): " + message);
    }

    @Override
    public void setVerboseLogging(boolean enabled) {
        int level;
        if (enabled) {
            this.verboseLogging = true;
            level = LEVEL_VERBOSE;
        } else {
            this.verboseLogging = false;
            level = LEVEL_INFO;
        }
        this.logLevel = level;
    }

    @Override
    public boolean isVerboseLogging() {
        return this.verboseLogging;
    }

    @Override
    public void verbose(java.lang.String tag, java.lang.String message) {
        if (this.logLevel > LEVEL_VERBOSE) {
            return;
        }
        android.util.Log.v(TAG, tag + "(" + java.lang.Thread.currentThread().getName() + "): " + message);
    }

    @Override
    public void error(java.lang.String tag, java.lang.String message) {
        if (this.logLevel > LEVEL_ERROR) {
            return;
        }
        android.util.Log.e(TAG, tag + "(" + java.lang.Thread.currentThread().getName() + "): " + message);
    }

    @Override
    public void debug(java.lang.String tag, java.lang.String message) {
        if (this.logLevel > LEVEL_DEBUG) {
            return;
        }
        android.util.Log.d(TAG, tag + "(" + java.lang.Thread.currentThread().getName() + "): " + message);
    }

    @Override
    public void errorWithThrowable(java.lang.String tag, java.lang.String message, java.lang.Throwable throwable) {
        if (this.logLevel > LEVEL_ERROR) {
            return;
        }
        if (throwable == null) {
            android.util.Log.e(TAG, tag + "(" + java.lang.Thread.currentThread().getName() + "): " + message);
            return;
        }
        android.util.Log.e(TAG, tag + "(" + java.lang.Thread.currentThread().getName() + "): " + message, throwable);
    }

    @Override
    public void warnWithThrowable(java.lang.String tag, java.lang.String message, java.lang.Throwable throwable) {
        if (this.logLevel > LEVEL_WARN) {
            return;
        }
        if (throwable == null) {
            android.util.Log.w(TAG, tag + "(" + java.lang.Thread.currentThread().getName() + "): " + message);
            return;
        }
        android.util.Log.w(TAG, tag + "(" + java.lang.Thread.currentThread().getName() + "): " + message, throwable);
    }

    @Override
    public void warn(java.lang.String tag, java.lang.String message) {
        if (this.logLevel > LEVEL_WARN) {
            return;
        }
        android.util.Log.w(TAG, tag + "(" + java.lang.Thread.currentThread().getName() + "): " + message);
    }
}
