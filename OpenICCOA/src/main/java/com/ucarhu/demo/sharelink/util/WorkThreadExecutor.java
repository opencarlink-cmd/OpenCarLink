package com.ucarhu.demo.sharelink.util;

public class WorkThreadExecutor {

    private static final java.lang.String TAG = "WorkThread";

    private static final int CORE_POOL_SIZE = 0;

    private static final int MAX_POOL_SIZE = Integer.MAX_VALUE;

    private static final int KEEP_ALIVE_SECONDS = 20;

    private static final java.util.concurrent.TimeUnit KEEP_ALIVE_TIME_UNIT = java.util.concurrent.TimeUnit.SECONDS;

    private static final java.util.concurrent.BlockingQueue<java.lang.Runnable> TASK_HANDOFF_QUEUE = new java.util.concurrent.SynchronousQueue();

    private final java.util.concurrent.ExecutorService executorService;

    /**
     * 为异步任务补充统一日志，方便定位 ShareLink 连接流程中的耗时任务。
     */
    private static class LoggingRunnable implements java.lang.Runnable {

        private final java.lang.String taskName;

        private final java.lang.Runnable delegate;

        public LoggingRunnable(java.lang.String taskName, java.lang.Runnable delegate) {
            this.taskName = taskName;
            this.delegate = delegate;
        }

        @Override
        public void run() {
            long startThreadTimeMillis = android.os.SystemClock.currentThreadTimeMillis();
            com.ucarhu.demo.logging.EasyLogger.debug(com.ucarhu.demo.sharelink.util.WorkThreadExecutor.TAG, "Executing task: " + this.taskName + "[" + startThreadTimeMillis + "]");
            this.delegate.run();
            com.ucarhu.demo.logging.EasyLogger.debug(com.ucarhu.demo.sharelink.util.WorkThreadExecutor.TAG, "Task: " + this.taskName + "[" + startThreadTimeMillis + "] executed.");
        }
    }

    private static class Holder {

        private static final com.ucarhu.demo.sharelink.util.WorkThreadExecutor INSTANCE = new com.ucarhu.demo.sharelink.util.WorkThreadExecutor();
    }

    private WorkThreadExecutor() {
        this.executorService = new java.util.concurrent.ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_SECONDS, KEEP_ALIVE_TIME_UNIT, TASK_HANDOFF_QUEUE);
    }

    public static void submit(java.lang.Runnable runnable, java.lang.String str) {
        java.lang.String str2;
        com.ucarhu.demo.logging.EasyLogger.debug(TAG, "Submit task: " + str);
        try {
            com.ucarhu.demo.sharelink.util.WorkThreadExecutor.Holder.INSTANCE.executorService.submit(new com.ucarhu.demo.sharelink.util.WorkThreadExecutor.LoggingRunnable(str, runnable));
        } catch (java.util.concurrent.RejectedExecutionException e2) {
            java.lang.Exception e = e2;
            str2 = "Submit runnable failed with RejectedExecutionException.";
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(TAG, str2, e);
        } catch (java.lang.Exception e3) {
            java.lang.Exception e = e3;
            str2 = "Submit runnable failed.";
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(TAG, str2, e);
        }
    }
}
