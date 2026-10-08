package com.ucarhu.demo.vehicle.media;

public class ByteBufferQueue {

    private static final int MAX_QUEUE_SIZE = 512;

    private static final int DEFAULT_MAX_BUFFER_BYTES = 8192;

    private static final int LARGE_MAX_BUFFER_BYTES = 524288;

    private static final int DEFAULT_RECYCLE_LIMIT = 30;

    private static final int LARGE_RECYCLE_LIMIT = 15;

    private static final int LOG_EVERY_TOTAL_BUFFERS = 100;

    private static final int LOG_EVERY_QUEUE_SIZE = 5;

    private final java.lang.String tag;

    private int recycleQuota;

    private final boolean useLargeBuffers;

    private final java.util.concurrent.BlockingQueue<java.nio.ByteBuffer> queue = new java.util.concurrent.LinkedBlockingQueue(MAX_QUEUE_SIZE);

    private long totalEnqueuedCount = 0;

    private final java.util.Stack<java.nio.ByteBuffer> recycledBuffers = new java.util.Stack<>();

    private final java.util.concurrent.atomic.AtomicInteger queuedBytes = new java.util.concurrent.atomic.AtomicInteger(0);

    private final java.lang.Object dataAvailableLock = new java.lang.Object();

    public ByteBufferQueue(java.lang.String name, boolean useLargeBuffers) {
        this.useLargeBuffers = useLargeBuffers;
        this.recycleQuota = useLargeBuffers ? LARGE_RECYCLE_LIMIT : DEFAULT_RECYCLE_LIMIT;
        this.tag = "QueueManager-" + name;
    }

    private int nextPowerOfTwo(int value) {
        int valueMinusOne = value - 1;
        int bits = valueMinusOne | (valueMinusOne >>> 1);
        bits |= bits >>> 2;
        bits |= bits >>> 4;
        bits |= bits >>> 8;
        bits |= bits >>> 16;
        if (bits < 0) {
            return 1;
        }
        return 1 + bits;
    }

    public void clear() {
        this.queue.clear();
        this.totalEnqueuedCount = 0L;
        this.queuedBytes.set(0);
    }

    public synchronized void enqueue(java.nio.ByteBuffer byteBuffer) {
        java.nio.ByteBuffer targetBuffer;
        long enqueuedCount = this.totalEnqueuedCount;
        this.totalEnqueuedCount = 1 + enqueuedCount;
        if (enqueuedCount < 0) {
            this.totalEnqueuedCount = 0L;
        }
        try {
            if (this.totalEnqueuedCount % LOG_EVERY_TOTAL_BUFFERS == 0 || (this.queue.size() > 0 && this.queue.size() % LOG_EVERY_QUEUE_SIZE == 0)) {
                com.ucarhu.demo.logging.EasyLogger.verbose(this.tag, "buffer queue size: " + this.queue.size() + ", total: " + this.totalEnqueuedCount);
            }
            int remainingBytes = byteBuffer.remaining();
            if (this.recycledBuffers.isEmpty()) {
                targetBuffer = java.nio.ByteBuffer.allocate(nextPowerOfTwo(remainingBytes));
                com.ucarhu.demo.logging.EasyLogger.info(this.tag, "allocate new buffer: " + remainingBytes);
            } else {
                this.recycleQuota--;
                targetBuffer = this.recycledBuffers.pop();
                if (targetBuffer.capacity() < remainingBytes) {
                    int maxBufferBytes = this.useLargeBuffers ? LARGE_MAX_BUFFER_BYTES : DEFAULT_MAX_BUFFER_BYTES;
                    int capacity = java.lang.Math.max(java.lang.Math.min(nextPowerOfTwo(remainingBytes), maxBufferBytes), remainingBytes);
                    com.ucarhu.demo.logging.EasyLogger.info(this.tag, "allocate new buffer: " + targetBuffer.capacity() + " < " + remainingBytes + ", alloc " + capacity);
                    targetBuffer = java.nio.ByteBuffer.allocate(capacity);
                }
            }
            targetBuffer.clear();
            targetBuffer.limit(remainingBytes);
            targetBuffer.put(byteBuffer);
            targetBuffer.flip();
            this.queue.put(targetBuffer);
            if (this.queue.size() > MAX_QUEUE_SIZE - 1) {
                java.nio.ByteBuffer droppedBuffer = this.queue.poll();
                if (droppedBuffer != null) {
                    this.queuedBytes.addAndGet(-droppedBuffer.remaining());
                }
                com.ucarhu.demo.logging.EasyLogger.error(this.tag, "something wrong, the queue have too many buffer");
            }
            this.queuedBytes.addAndGet(remainingBytes);
            synchronized (this.dataAvailableLock) {
                this.dataAvailableLock.notifyAll();
            }
        } catch (java.lang.InterruptedException e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(this.tag, "Put data to buffer queue error.", e2);
        }
    }

    public java.nio.ByteBuffer take() {
        try {
            java.nio.ByteBuffer byteBuffer = this.queue.take();
            this.queuedBytes.getAndAdd(-byteBuffer.remaining());
            return byteBuffer;
        } catch (java.lang.InterruptedException e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(this.tag, "Get data from buffer queue error.", e2);
            return null;
        }
    }

    public synchronized void recycle(java.nio.ByteBuffer byteBuffer) {
        int quota = this.recycleQuota + 1;
        this.recycleQuota = quota;
        if (quota >= 0) {
            int maxBufferBytes = this.useLargeBuffers ? LARGE_MAX_BUFFER_BYTES : DEFAULT_MAX_BUFFER_BYTES;
            int recycleLimit = this.useLargeBuffers ? LARGE_RECYCLE_LIMIT : DEFAULT_RECYCLE_LIMIT;
            if (byteBuffer.capacity() <= maxBufferBytes && this.recycledBuffers.size() < recycleLimit) {
                this.recycledBuffers.push(byteBuffer);
            }
        }
    }

    public int queuedBytes() {
        return this.queuedBytes.get();
    }

    public int queueSize() {
        return this.queue.size();
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public void waitForData() {
        synchronized (this.dataAvailableLock) {
            try {
                this.dataAvailableLock.wait(100L);
            } catch (java.lang.InterruptedException unused) {
            }
        }
    }
}
