package com.ucarhu.demo.protocol.channel.socket;

public class FutureRequestManager {

    private static final java.lang.String f463d = "FutureRequestManager";

    private static final com.ucarhu.demo.protocol.channel.socket.FutureRequestManager f464e = new com.ucarhu.demo.protocol.channel.socket.FutureRequestManager();

    private volatile boolean f465a = false;

    private final java.util.concurrent.DelayQueue<com.ucarhu.demo.protocol.channel.socket.RequestFuture<?>> f466b = new java.util.concurrent.DelayQueue<>();

    private final java.util.Map<java.lang.Integer, com.ucarhu.demo.protocol.channel.socket.RequestFuture<?>> f467c = new java.util.concurrent.ConcurrentHashMap();

    private FutureRequestManager() {
        final com.ucarhu.demo.protocol.logging.ProtocolLogger interfaceC0082cM582a = com.ucarhu.demo.protocol.ProtocolConfig.getLogger();
        java.lang.Thread thread = new java.lang.Thread(new java.lang.Runnable() {
            @Override
            public final void run() {
                FutureRequestManager.this.m365e(interfaceC0082cM582a);
            }
        }, "req-timeout-task");
        thread.setDaemon(true);
        thread.start();
    }

    public static com.ucarhu.demo.protocol.channel.socket.FutureRequestManager m364a() {
        return f464e;
    }

    public void m365e(com.ucarhu.demo.protocol.logging.ProtocolLogger interfaceC0082c) {
        this.f465a = true;
        while (this.f465a) {
            try {
                com.ucarhu.demo.protocol.channel.socket.RequestFuture delayedC0076o = (com.ucarhu.demo.protocol.channel.socket.RequestFuture) this.f466b.take();
                if (delayedC0076o != null && !delayedC0076o.isCancelled() && !delayedC0076o.isDone() && this.f467c.containsKey(java.lang.Integer.valueOf(delayedC0076o.m380P()))) {
                    java.lang.String str = "request " + delayedC0076o.m380P() + " timeout: " + delayedC0076o.m381Q();
                    interfaceC0082c.error(f463d, str);
                    delayedC0076o.m379O(new java.util.concurrent.TimeoutException(str));
                    m367b(delayedC0076o.m380P());
                }
            } catch (java.lang.InterruptedException unused) {
            }
        }
        interfaceC0082c.info(f463d, "FutureRequest timeout thread exit.");
    }

    public void m367b(int i) {
        this.f467c.remove(java.lang.Integer.valueOf(i));
    }

    public void m368c(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        int iM671B = c0102w.getSequenceId();
        com.ucarhu.demo.protocol.channel.socket.RequestFuture<?> delayedC0076o = this.f467c.get(java.lang.Integer.valueOf(iM671B));
        if (delayedC0076o != null) {
            delayedC0076o.m378N(c0102w);
            m367b(iM671B);
            return;
        }
        com.ucarhu.demo.protocol.ProtocolConfig.getLogger().warn(f463d, "receive timeout or unknown response: " + iM671B);
    }

    public synchronized void m369d(com.ucarhu.demo.protocol.channel.socket.RequestFuture<?> delayedC0076o) {
        if (delayedC0076o == null) {
            return;
        }
        int iM380P = delayedC0076o.m380P();
        if (this.f467c.containsKey(java.lang.Integer.valueOf(iM380P))) {
            com.ucarhu.demo.protocol.ProtocolConfig.getLogger().warn(f463d, "request id: " + iM380P + " repeat add.");
        }
        this.f467c.put(java.lang.Integer.valueOf(iM380P), delayedC0076o);
        this.f466b.put(delayedC0076o);
    }

    public java.util.Map<java.lang.Integer, com.ucarhu.demo.protocol.channel.socket.RequestFuture<?>> m370f() {
        return this.f467c;
    }

    public void m371g() {
        this.f465a = false;
    }
}
