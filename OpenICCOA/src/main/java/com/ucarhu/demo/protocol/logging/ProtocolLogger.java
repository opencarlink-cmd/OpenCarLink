package com.ucarhu.demo.protocol.logging;

public interface ProtocolLogger {

    int LEVEL_VERBOSE = 2;

    int LEVEL_DEBUG = 3;

    int LEVEL_INFO = 4;

    int LEVEL_WARN = 5;

    int LEVEL_ERROR = 6;

    void setLogLevel(int level);

    void info(java.lang.String tag, java.lang.String message);

    void setVerboseLogging(boolean enabled);

    boolean isVerboseLogging();

    void verbose(java.lang.String tag, java.lang.String message);

    void error(java.lang.String tag, java.lang.String message);

    void debug(java.lang.String tag, java.lang.String message);

    void errorWithThrowable(java.lang.String tag, java.lang.String message, java.lang.Throwable throwable);

    void warnWithThrowable(java.lang.String tag, java.lang.String message, java.lang.Throwable throwable);

    void warn(java.lang.String tag, java.lang.String message);
}
