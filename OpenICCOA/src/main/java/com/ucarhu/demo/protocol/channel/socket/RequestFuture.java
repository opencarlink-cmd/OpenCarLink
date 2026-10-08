package com.ucarhu.demo.protocol.channel.socket;

public class RequestFuture<ResT> extends java.util.concurrent.CompletableFuture<ResT> implements java.util.concurrent.Delayed {

    public static final long f475n = 3000;

    private final com.ucarhu.demo.protocol.channel.FutureCallback<ResT> f476j;

    private final long f477k;

    private final long f478l;

    private final int f479m;

    public RequestFuture(com.ucarhu.demo.protocol.channel.FutureCallback<ResT> interfaceC0058c, int i) {
        this(interfaceC0058c, f475n, i);
    }

    public RequestFuture(com.ucarhu.demo.protocol.channel.FutureCallback<ResT> interfaceC0058c, long j, int i) {
        this.f477k = android.os.SystemClock.uptimeMillis();
        this.f476j = interfaceC0058c;
        this.f478l = j;
        this.f479m = i;
    }

    @Override
    public int compareTo(java.util.concurrent.Delayed delayed) {
        java.util.concurrent.TimeUnit timeUnit = java.util.concurrent.TimeUnit.MILLISECONDS;
        return (int) (getDelay(timeUnit) - delayed.getDelay(timeUnit));
    }

    public com.ucarhu.demo.protocol.channel.FutureCallback<ResT> m377M() {
        return this.f476j;
    }

    public void m378N(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        com.ucarhu.demo.protocol.channel.FutureCallback<ResT> interfaceC0058c = this.f476j;
        if (interfaceC0058c != null) {
            ResT restMo346a = interfaceC0058c.mapResponse(c0102w);
            if (!isCancelled()) {
                complete(restMo346a);
                this.f476j.onSuccess(restMo346a);
            }
            if ((restMo346a instanceof com.ucarhu.demo.protocol.channel.socket.DefaultFutureCallback) || c0102w == null) {
                return;
            }
            c0102w.recycle();
        }
    }

    public void m379O(java.lang.Exception exc) {
        com.ucarhu.demo.protocol.channel.FutureCallback<ResT> interfaceC0058c = this.f476j;
        if (interfaceC0058c != null) {
            interfaceC0058c.onFailure(exc);
        }
        completeExceptionally(exc);
    }

    public int m380P() {
        return this.f479m;
    }

    public long m381Q() {
        return this.f478l;
    }

    @Override
    public long getDelay(java.util.concurrent.TimeUnit timeUnit) {
        long j = this.f478l;
        if (j == 0) {
            return 0L;
        }
        return timeUnit.convert((this.f477k + j) - android.os.SystemClock.uptimeMillis(), java.util.concurrent.TimeUnit.MILLISECONDS);
    }

    @Override
    @androidx.annotation.NonNull
    public java.lang.String toString() {
        return "RequestFuture{start=" + this.f477k + ", timeoutMs=" + this.f478l + ", requestId=" + this.f479m + '}';
    }
}
