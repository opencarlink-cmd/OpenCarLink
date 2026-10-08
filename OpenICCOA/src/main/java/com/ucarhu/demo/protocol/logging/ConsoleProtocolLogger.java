package com.ucarhu.demo.protocol.logging;

public class ConsoleProtocolLogger implements com.ucarhu.demo.protocol.logging.ProtocolLogger {

    private int logLevel = LEVEL_INFO;

    private boolean verboseLogging = false;

    private synchronized void printLine(java.lang.String tag, java.lang.String message, java.lang.String levelName) {
        for (java.lang.String line : message.split("\n")) {
            java.lang.System.out.println(java.lang.System.currentTimeMillis() + " " + java.lang.Thread.currentThread().getId() + " " + levelName + "/" + tag + "(" + java.lang.Thread.currentThread().getName() + "): " + line);
        }
    }

    private synchronized void printThrowable(java.lang.Throwable throwable, java.lang.String tag, java.lang.String levelName) {
        if (throwable == null) {
            return;
        }
        java.lang.StackTraceElement[] stackTrace = throwable.getStackTrace();
        java.io.PrintStream printStream = java.lang.System.out;
        if (levelName.equals("E")) {
            printStream = java.lang.System.err;
        }
        long threadId = java.lang.Thread.currentThread().getId();
        java.lang.String threadName = java.lang.Thread.currentThread().getName();
        printStream.println(java.lang.System.currentTimeMillis() + " " + threadId + " " + levelName + "/" + tag + "(" + threadName + "): " + throwable.toString());
        for (java.lang.StackTraceElement stackTraceElement : stackTrace) {
            printStream.println(java.lang.System.currentTimeMillis() + " " + threadId + " " + levelName + "/" + tag + "(" + threadName + "): \tat " + stackTraceElement.toString());
        }
    }

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
        printLine(tag, message, "I");
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
        printLine(tag, message, "V");
    }

    @Override
    public void error(java.lang.String tag, java.lang.String message) {
        if (this.logLevel > LEVEL_ERROR) {
            return;
        }
        printLine(tag, message, "E");
    }

    @Override
    public void debug(java.lang.String tag, java.lang.String message) {
        if (this.logLevel > LEVEL_DEBUG) {
            return;
        }
        printLine(tag, message, "D");
    }

    @Override
    public void errorWithThrowable(java.lang.String tag, java.lang.String message, java.lang.Throwable throwable) {
        if (this.logLevel > LEVEL_ERROR) {
            return;
        }
        printLine(tag, message, "E");
        printThrowable(throwable, tag, "E");
    }

    @Override
    public void warnWithThrowable(java.lang.String tag, java.lang.String message, java.lang.Throwable throwable) {
        if (this.logLevel > LEVEL_WARN) {
            return;
        }
        printLine(tag, message, "W");
        printThrowable(throwable, tag, "W");
    }

    @Override
    public void warn(java.lang.String tag, java.lang.String message) {
        if (this.logLevel > LEVEL_WARN) {
            return;
        }
        printLine(tag, message, "W");
    }
}
