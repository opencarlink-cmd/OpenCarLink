package com.ucarhu.demo.protocol.channel;

public interface NetChannel extends java.io.Closeable {

    public interface a {
        void mo94a(com.ucarhu.demo.protocol.UCarMessage c0102w) throws java.lang.InterruptedException;
    }

    com.ucarhu.demo.protocol.channel.ChannelType mo347I();

    <T> java.util.concurrent.Future<T> mo348X(com.ucarhu.demo.protocol.UCarMessage c0102w, long j, com.ucarhu.demo.protocol.channel.FutureCallback<T> interfaceC0058c);

    void mo349Z();

    java.lang.String mo350a();

    void mo351a(com.ucarhu.demo.protocol.UCarMessage c0102w);

    void mo352a(boolean z);

    boolean mo353b();

    java.lang.String mo354c();

    java.util.concurrent.Future<java.lang.Boolean> mo355c(com.ucarhu.demo.protocol.UCarMessage c0102w, com.ucarhu.demo.protocol.channel.SendCallback interfaceC0060e);

    java.util.concurrent.Future<java.lang.Boolean> mo356f() throws java.io.IOException;

    int mo357i();

    boolean mo358j();

    void mo359q0();

    void mo360v0(java.lang.Throwable th);
}
