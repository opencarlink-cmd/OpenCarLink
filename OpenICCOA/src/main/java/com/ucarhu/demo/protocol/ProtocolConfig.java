package com.ucarhu.demo.protocol;

public class ProtocolConfig {

    private static com.ucarhu.demo.protocol.logging.ProtocolLogger logger = new com.ucarhu.demo.protocol.logging.ConsoleProtocolLogger();

    private static final java.lang.String TAG = "ProtocolConfig";

    private static final int MESSAGE_EXECUTOR_CORE_SIZE = 3;

    private static final int MESSAGE_EXECUTOR_MAX_SIZE = 5;

    private static final int MESSAGE_EXECUTOR_KEEP_ALIVE_SECONDS = 5;

    private static final int MESSAGE_EXECUTOR_QUEUE_SIZE = 100;

    private static final int REQUEST_EXECUTOR_CORE_SIZE = 5;

    private static final int REQUEST_EXECUTOR_MAX_SIZE = 10;

    private static final int REQUEST_EXECUTOR_KEEP_ALIVE_SECONDS = 5;

    private static final int REQUEST_EXECUTOR_QUEUE_SIZE = 5;

    public static final int MAX_MESSAGE_BYTES = 131072;

    private static java.util.concurrent.ExecutorService messageExecutor;

    private static final java.util.concurrent.ExecutorService requestExecutor;

    /**
     * 协议消息分发线程，使用 daemon 线程避免进程退出时被后台任务阻塞。
     */
    public static class MessageThreadFactory implements java.util.concurrent.ThreadFactory {

        private final java.util.concurrent.atomic.AtomicInteger threadIndex = new java.util.concurrent.atomic.AtomicInteger(0);

        @Override
        public java.lang.Thread newThread(java.lang.Runnable runnable) {
            java.lang.Thread thread = new java.lang.Thread(runnable, "proto-msg-" + this.threadIndex.getAndIncrement());
            thread.setDaemon(true);
            return thread;
        }
    }

    /**
     * 协议请求回调线程，主要处理异步发送和等待响应的任务。
     */
    public static class RequestThreadFactory implements java.util.concurrent.ThreadFactory {

        private final java.util.concurrent.atomic.AtomicInteger threadIndex = new java.util.concurrent.atomic.AtomicInteger(0);

        @Override
        public java.lang.Thread newThread(java.lang.Runnable runnable) {
            java.lang.Thread thread = new java.lang.Thread(runnable, "proto-req-" + this.threadIndex.getAndIncrement());
            thread.setDaemon(true);
            return thread;
        }
    }

    static {
        java.util.concurrent.TimeUnit timeUnit = java.util.concurrent.TimeUnit.SECONDS;
        messageExecutor = new java.util.concurrent.ThreadPoolExecutor(MESSAGE_EXECUTOR_CORE_SIZE, MESSAGE_EXECUTOR_MAX_SIZE, MESSAGE_EXECUTOR_KEEP_ALIVE_SECONDS, timeUnit, new java.util.concurrent.ArrayBlockingQueue(MESSAGE_EXECUTOR_QUEUE_SIZE), new com.ucarhu.demo.protocol.ProtocolConfig.MessageThreadFactory(), new java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy());
        requestExecutor = new java.util.concurrent.ThreadPoolExecutor(REQUEST_EXECUTOR_CORE_SIZE, REQUEST_EXECUTOR_MAX_SIZE, REQUEST_EXECUTOR_KEEP_ALIVE_SECONDS, timeUnit, new java.util.concurrent.ArrayBlockingQueue(REQUEST_EXECUTOR_QUEUE_SIZE), new com.ucarhu.demo.protocol.ProtocolConfig.RequestThreadFactory(), new java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy());
    }

    public static com.ucarhu.demo.protocol.logging.ProtocolLogger getLogger() {
        if (logger == null) {
            logger = new com.ucarhu.demo.protocol.logging.ConsoleProtocolLogger();
        }
        return logger;
    }

    public static void setLogLevel(int level) {
        getLogger().setLogLevel(level);
    }

    public static void setLocalDevice(com.ucarhu.demo.protocol.SourceDevice localDevice) {
        com.ucarhu.demo.protocol.ProtocolMessageRegistry.setLocalDevice(localDevice);
    }

    public static void setLogger(com.ucarhu.demo.protocol.logging.ProtocolLogger protocolLogger) {
        if (protocolLogger == null) {
            getLogger().error(TAG, "Set logger error, logger is null.");
            return;
        }
        logger = protocolLogger;
        getLogger().info(TAG, "Set logger to: " + protocolLogger.getClass().getCanonicalName());
    }

    public static void setMessageExecutor(java.util.concurrent.ExecutorService executorService) {
        if (executorService == null) {
            return;
        }
        java.util.concurrent.ExecutorService oldExecutor = messageExecutor;
        messageExecutor = executorService;
        if (oldExecutor != null) {
            oldExecutor.shutdown();
        }
    }

    public static void setVerboseLogging(boolean enabled) {
        getLogger().setVerboseLogging(enabled);
    }

    public static java.util.concurrent.ExecutorService getMessageExecutor() {
        return messageExecutor;
    }

    public static java.util.concurrent.ExecutorService getRequestExecutor() {
        return requestExecutor;
    }

    public static com.ucarhu.demo.protocol.SourceDevice getLocalDevice() {
        return com.ucarhu.demo.protocol.ProtocolMessageRegistry.getLocalDevice();
    }
}
