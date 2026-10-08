package com.ucarhu.demo.protocol;

public class UCarMessagePool {

    private static final java.lang.String f630c = "UCarMessagePool";

    private static final int f631d = 20;

    private static final com.ucarhu.demo.protocol.UCarMessagePool f632e = new com.ucarhu.demo.protocol.UCarMessagePool();

    private final java.util.Stack<com.ucarhu.demo.protocol.UCarMessage> f633a = new java.util.Stack<>();

    private final java.util.Set<java.lang.Integer> f634b = new java.util.HashSet();

    private UCarMessagePool() {
        for (int i = 0; i < 20; i++) {
            com.ucarhu.demo.protocol.UCarMessage c0102wM666c = com.ucarhu.demo.protocol.UCarMessage.createPooled(true);
            this.f633a.add(c0102wM666c);
            this.f634b.add(java.lang.Integer.valueOf(c0102wM666c.m698z()));
        }
    }

    public static com.ucarhu.demo.protocol.UCarMessagePool getInstance() {
        return f632e;
    }

    public synchronized void recycle(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        if (c0102w.isPooled() && !this.f634b.contains(java.lang.Integer.valueOf(c0102w.m698z()))) {
            this.f633a.push(c0102w);
            this.f634b.add(java.lang.Integer.valueOf(c0102w.m698z()));
        }
    }

    public synchronized com.ucarhu.demo.protocol.UCarMessage obtain() {
        if (this.f633a.isEmpty()) {
            com.ucarhu.demo.protocol.ProtocolConfig.getLogger().warn(f630c, "UCarMessage pool is empty, please check whether release message correctly or not");
            return com.ucarhu.demo.protocol.UCarMessage.createPooled(false);
        }
        com.ucarhu.demo.protocol.UCarMessage c0102wPop = this.f633a.pop();
        this.f634b.remove(java.lang.Integer.valueOf(c0102wPop.m698z()));
        return c0102wPop;
    }
}
