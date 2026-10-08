package com.ucarhu.demo.protocol.channel.socket;

public class SocketChannelReadTask implements java.lang.Runnable, java.io.Closeable {

    private static final java.lang.String f505f = "SocketChannelReadTask";

    private final com.ucarhu.demo.protocol.channel.socket.SocketChannel f506b;

    private final com.ucarhu.demo.protocol.crypto.AesGcmSessionCrypto f507c;

    private volatile boolean f508d = true;

    private java.io.InputStream f509e = null;

    public SocketChannelReadTask(com.ucarhu.demo.protocol.channel.socket.SocketChannel c0077p) {
        java.util.Objects.requireNonNull(c0077p, "SocketChannel is null");
        this.f506b = c0077p;
        this.f507c = c0077p.m415V0();
    }

    private java.io.InputStream m424K() {
        com.ucarhu.demo.protocol.logging.ProtocolLogger interfaceC0082cM582a = com.ucarhu.demo.protocol.ProtocolConfig.getLogger();
        if (!this.f506b.mo353b()) {
            interfaceC0082cM582a.error(f505f, "Start read thread error: socket channel is not connected");
            return null;
        }
        try {
            java.net.Socket socketM414U0 = this.f506b.m414U0();
            if (socketM414U0 != null) {
                return socketM414U0.getInputStream();
            }
            interfaceC0082cM582a.error(f505f, "Start read thread error: socket is null");
            return null;
        } catch (java.io.IOException e2) {
            interfaceC0082cM582a.errorWithThrowable(f505f, "Start read thread error: " + e2.getMessage(), e2);
            return null;
        }
    }

    private void m425O() {
        if (this.f509e == null) {
            return;
        }
        com.ucarhu.demo.protocol.logging.ProtocolLogger interfaceC0082cM582a = com.ucarhu.demo.protocol.ProtocolConfig.getLogger();
        try {
            int iAvailable = this.f509e.available();
            if (iAvailable > 0) {
                interfaceC0082cM582a.warn(f505f, "Skip data len: " + this.f509e.skip(iAvailable));
            }
        } catch (java.io.IOException e2) {
            interfaceC0082cM582a.errorWithThrowable(f505f, "Skip data len error: " + e2.getMessage(), e2);
        }
    }

    private int m427k(java.nio.channels.ReadableByteChannel readableByteChannel, java.nio.ByteBuffer byteBuffer, int i) throws java.io.IOException {
        java.lang.StringBuilder sb;
        java.lang.String str;
        if (byteBuffer == null || i == 0) {
            return 0;
        }
        com.ucarhu.demo.protocol.logging.ProtocolLogger interfaceC0082cM582a = com.ucarhu.demo.protocol.ProtocolConfig.getLogger();
        byteBuffer.limit(i);
        int i2 = readableByteChannel.read(byteBuffer);
        if (i2 != -1) {
            if (i2 < 0 || i2 > i) {
                throw new com.ucarhu.demo.protocol.ProtocolException("Read bytes too long or too short: " + i2 + ", expect: " + i);
            }
            while (i2 < i) {
                int i3 = readableByteChannel.read(byteBuffer);
                if (i3 == -1) {
                    sb = new java.lang.StringBuilder();
                    sb.append(this.f506b.mo350a());
                    str = " channel read channel closed.";
                } else {
                    i2 += i3;
                }
            }
            if (i2 == i) {
                return i2;
            }
            throw new com.ucarhu.demo.protocol.ProtocolException("Read bytes length less than buff length: " + i2);
        }
        sb = new java.lang.StringBuilder();
        sb.append(this.f506b.mo350a());
        str = " read channel closed.";
        sb.append(str);
        interfaceC0082cM582a.info(f505f, sb.toString());
        return -1;
    }

    private java.nio.ByteBuffer m428n(int i, java.nio.ByteBuffer byteBuffer) {
        if (i >= byteBuffer.capacity() || !this.f506b.m418b1()) {
            return java.nio.ByteBuffer.allocate(i);
        }
        byteBuffer.clear();
        byteBuffer.limit(i);
        return byteBuffer;
    }

    public void m429x(com.ucarhu.demo.protocol.logging.ProtocolLogger interfaceC0082c, com.ucarhu.demo.protocol.UCarMessage c0102w) {
        if (interfaceC0082c.isVerboseLogging()) {
            interfaceC0082c.info(f505f, c0102w.describe());
        }
        if (c0102w.getMessageType() == com.ucarhu.demo.protocol.MessageType.RES) {
            com.ucarhu.demo.protocol.channel.socket.FutureRequestManager.m364a().m368c(c0102w);
        } else {
            this.f506b.mo351a(c0102w);
            c0102w.recycle();
        }
    }

    public boolean m430L() {
        return this.f507c != null;
    }

    @Override
    public void close(){
        this.f508d = false;
        java.io.InputStream inputStream = this.f509e;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (java.io.IOException e2) {
                com.ucarhu.demo.protocol.ProtocolConfig.getLogger().errorWithThrowable(f505f, "Close input stream error: " + e2.getMessage(), e2);
            }
        }
    }

    @Override
    public void run() {
        com.ucarhu.demo.protocol.UCarMessage c0102w;
        final com.ucarhu.demo.protocol.logging.ProtocolLogger interfaceC0082cM582a = com.ucarhu.demo.protocol.ProtocolConfig.getLogger();
        java.io.InputStream inputStreamM424K = m424K();
        this.f509e = inputStreamM424K;
        if (inputStreamM424K == null) {
            return;
        }
        java.nio.channels.ReadableByteChannel readableByteChannelNewChannel = java.nio.channels.Channels.newChannel(inputStreamM424K);
        java.nio.ByteBuffer byteBufferAllocate = java.nio.ByteBuffer.allocate(20);
        com.ucarhu.demo.protocol.UCarMessageHeader c0092mM542B = com.ucarhu.demo.protocol.UCarMessageHeader.createDefault();
        java.nio.ByteBuffer byteBufferAllocate2 = java.nio.ByteBuffer.allocate(131072);
        while (this.f508d) {
            boolean zM418b1 = this.f506b.m418b1();
            try {
                byteBufferAllocate.clear();
                if (m427k(readableByteChannelNewChannel, byteBufferAllocate, 20) == -1) {
                    return;
                }
                byteBufferAllocate.flip();
                c0092mM542B.m563l(byteBufferAllocate.array());
                int iM568t = c0092mM542B.getMessageLength() - 20;
                if (iM568t < 0) {
                    interfaceC0082cM582a.error(f505f, "Read data parse not normal, find body len < 0");
                    m425O();
                } else {
                    final com.ucarhu.demo.protocol.UCarMessage c0102wM664H;
                    java.nio.ByteBuffer byteBufferM428n = m428n(iM568t, byteBufferAllocate2);
                    if (m427k(readableByteChannelNewChannel, byteBufferM428n, iM568t) == -1) {
                        return;
                    }
                    byteBufferM428n.flip();
                    if (zM418b1) {
                        c0102wM664H = com.ucarhu.demo.protocol.UCarMessage.obtain();
                        com.ucarhu.demo.protocol.UCarMessageHeader c0092mM558f = c0102wM664H.getHeader().copyFrom(c0092mM542B);
                        if (m430L()) {
                            java.nio.ByteBuffer byteBufferM515c = this.f507c.decrypt(byteBufferM428n, false);
                            c0092mM558f.m566r(byteBufferM515c.remaining());
                            c0102wM664H.setBody(byteBufferM515c, byteBufferM515c.remaining());
                        } else {
                            c0102wM664H.setBody(byteBufferM428n, iM568t);
                        }
                    } else {
                        com.ucarhu.demo.protocol.UCarMessageHeader c0092mM558f2 = com.ucarhu.demo.protocol.UCarMessageHeader.createDefault().copyFrom(c0092mM542B);
                        if (m430L()) {
                            java.nio.ByteBuffer byteBufferM514b = this.f507c.decryptToHeapBuffer(byteBufferM428n);
                            c0102w = new com.ucarhu.demo.protocol.UCarMessage(c0092mM558f2, byteBufferM514b, byteBufferM514b.remaining());
                            c0092mM558f2.m566r(byteBufferM514b.remaining());
                        } else {
                            c0102w = new com.ucarhu.demo.protocol.UCarMessage(c0092mM558f2, byteBufferM428n, iM568t);
                        }
                        c0102wM664H = c0102w;
                    }
                    com.ucarhu.demo.protocol.ProtocolConfig.getMessageExecutor().submit(new java.lang.Runnable() {
                        @Override
                        public final void run() {
                            SocketChannelReadTask.this.m429x(interfaceC0082cM582a, c0102wM664H);
                        }
                    });
                }
            } catch (com.ucarhu.demo.protocol.ProtocolException e2) {
                interfaceC0082cM582a.errorWithThrowable(f505f, "parse message error: " + e2.getMessage(), e2);
                m425O();
            } catch (java.io.IOException e3) {
                if (this.f508d) {
                    com.ucarhu.demo.protocol.ProtocolConfig.getLogger().errorWithThrowable(f505f, "Read data in channel: " + this.f506b.mo350a() + " Error.", e3);
                }
                this.f508d = false;
                return;
            }
        }
    }
}
