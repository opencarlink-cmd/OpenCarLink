package com.ucarhu.demo.protocol.channel.socket;

/**
 * 基于 TCP Socket 的协议通道实现，既可以作为服务端等待连接，也可以作为客户端主动连接。
 * 负责消息发送、同步请求等待、加密开关、通道生命周期和入站消息分发。
 */


public class SocketChannel extends com.ucarhu.demo.protocol.channel.socket.AbstractNetChannel {

    private static final java.lang.String f480A = "\\.";

    private static final int f481B = 4;

    private static final java.lang.String f482C = "SocketChannel";

    private static final long f483D = 100;

    private static final int f484E = 100;

    public static final java.lang.String f485u = "TcpNoDelay";

    public static final java.lang.String f486v = "ReuseAddress";

    public static final java.lang.String f487w = "SoLingerMode";

    public static final java.lang.String f488x = "Linger";

    public static final java.lang.String f489y = "TrafficClass";

    public static final int f490z = 192;

    private volatile boolean f491i;

    private java.net.ServerSocket f492j;

    private java.net.Socket f493k;

    private com.ucarhu.demo.protocol.channel.NetChannel.a f494l;

    private com.ucarhu.demo.protocol.crypto.AesGcmSessionCrypto f495m;

    private java.util.concurrent.ExecutorService f496n;

    private java.util.concurrent.ExecutorService f497o;

    private com.ucarhu.demo.protocol.channel.socket.SocketChannelReadTask f498p;

    private int f499q;

    private volatile boolean f500r;

    private boolean f501s;

    private final java.lang.Object f502t;

    public class a implements com.ucarhu.demo.protocol.channel.FutureCallback<java.lang.Boolean> {

        public final com.ucarhu.demo.protocol.channel.SendCallback f503a;

        public a(com.ucarhu.demo.protocol.channel.SendCallback interfaceC0060e) {
            this.f503a = interfaceC0060e;
        }

        @Override
        public java.lang.Boolean mapResponse(com.ucarhu.demo.protocol.UCarMessage c0102w) {
            return java.lang.Boolean.valueOf(c0102w != null);
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.protocol.channel.SendCallback interfaceC0060e = this.f503a;
            if (interfaceC0060e != null) {
                interfaceC0060e.onFailure(exc);
            }
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.protocol.channel.SendCallback interfaceC0060e = this.f503a;
            if (interfaceC0060e != null) {
                interfaceC0060e.onSuccess(java.lang.Boolean.valueOf(bool != null));
            }
        }
    }

    public SocketChannel(com.ucarhu.demo.protocol.channel.ChannelType enumC0057b) {
        this(enumC0057b, false);
    }

    public SocketChannel(com.ucarhu.demo.protocol.channel.ChannelType enumC0057b, boolean z) {
        this(enumC0057b, z, true);
    }

    public SocketChannel(com.ucarhu.demo.protocol.channel.ChannelType enumC0057b, boolean z, boolean z2) {
        super(enumC0057b, z);
        this.f491i = false;
        this.f499q = 100;
        this.f500r = false;
        this.f501s = false;
        this.f502t = new java.lang.Object();
        if (z2) {
            this.f495m = new com.ucarhu.demo.protocol.crypto.AesGcmSessionCrypto();
        }
    }

    private void m382A0(java.util.Map<java.lang.String, java.lang.Integer> map) throws java.io.IOException {
        if (this.f493k == null || map == null) {
            return;
        }
        com.ucarhu.demo.protocol.ProtocolConfig.getLogger().info(f482C, " set Socket Extra Options!");
        if (map.containsKey(f485u)) {
            this.f493k.setTcpNoDelay(map.get(f485u).intValue() != 0);
        }
        if (map.containsKey(f486v)) {
            this.f493k.setReuseAddress(map.get(f486v).intValue() != 0);
        }
        if (map.containsKey(f487w) && map.containsKey(f488x)) {
            this.f493k.setSoLinger(map.get(f487w).intValue() != 0, map.get(f488x).intValue());
        }
        if (map.containsKey(f489y)) {
            this.f493k.setTrafficClass(map.get(f489y).intValue());
        }
    }

    public java.lang.Boolean m383B0(com.ucarhu.demo.protocol.UCarMessage c0102w, com.ucarhu.demo.protocol.channel.SendCallback interfaceC0060e) throws java.lang.Exception {
        java.lang.Boolean bool = java.lang.Boolean.TRUE;
        try {
            try {
                java.io.OutputStream outputStream = this.f493k.getOutputStream();
                if (m416W0()) {
                    com.ucarhu.demo.protocol.UCarMessage c0102wM664H = com.ucarhu.demo.protocol.UCarMessage.obtain();
                    com.ucarhu.demo.protocol.UCarMessageHeader c0092mM558f = c0102wM664H.getHeader().copyFrom(c0102w.getHeader());
                    java.nio.ByteBuffer byteBufferM517i = this.f495m.encrypt(c0102w.writeTo());
                    c0092mM558f.m569u(byteBufferM517i.remaining() + 20);
                    byte[] bArrM552D = c0092mM558f.toHeaderBytes();
                    c0102wM664H.recycle();
                    outputStream.write(bArrM552D);
                    outputStream.write(byteBufferM517i.array(), 0, byteBufferM517i.remaining());
                } else {
                    outputStream.write(c0102w.getHeader().toHeaderBytes());
                    outputStream.write(c0102w.getBodyArray(), c0102w.writeTo().arrayOffset(), c0102w.getBodyLength());
                }
                outputStream.flush();
                if (interfaceC0060e != null) {
                    interfaceC0060e.onSuccess(bool);
                }
                return bool;
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.protocol.ProtocolConfig.getLogger().errorWithThrowable(f482C, "Send message error: \n" + c0102w.describe(), e2);
                if (interfaceC0060e != null) {
                    interfaceC0060e.onFailure(e2);
                }
                c0102w.recycle();
                return java.lang.Boolean.FALSE;
            }
        } finally {
            c0102w.recycle();
        }
    }

    public java.lang.Thread m384C0(java.lang.Runnable runnable) {
        java.lang.Thread thread = new java.lang.Thread(runnable, m373k("W"));
        thread.setDaemon(true);
        return thread;
    }

    private java.util.concurrent.Future<java.lang.Boolean> m385D0(final int i, final java.lang.String str, final java.util.Map<java.lang.String, java.lang.Integer> map) {
        synchronized (this.f502t) {
            mo359q0();
            this.f500r = false;
        }
        java.util.concurrent.FutureTask futureTask = new java.util.concurrent.FutureTask(new java.util.concurrent.Callable() {
            @Override
            public final java.lang.Object call() throws java.lang.Exception {
                return SocketChannel.this.m399T(map, str, i);
            }
        });
        java.lang.Thread thread = new java.lang.Thread(futureTask, m373k("C"));
        thread.setDaemon(true);
        thread.start();
        return futureTask;
    }

    private void m386G0(boolean z) {
        this.f491i = z;
    }

    private java.util.concurrent.Future<java.lang.Boolean> m387H0(final int i, final java.lang.String str, final java.util.Map<java.lang.String, java.lang.Integer> map) {
        synchronized (this.f502t) {
            mo359q0();
            this.f500r = false;
        }
        java.util.concurrent.FutureTask futureTask = new java.util.concurrent.FutureTask(new java.util.concurrent.Callable() {
            @Override
            public final java.lang.Object call() throws java.lang.Exception {
                return SocketChannel.this.m397S(str, i, map);
            }
        });
        java.lang.Thread thread = new java.lang.Thread(futureTask, m373k("A"));
        thread.setDaemon(true);
        thread.start();
        return futureTask;
    }

    private void m388J0() {
        com.ucarhu.demo.protocol.ProtocolConfig.getLogger().info(f482C, "Wait more client connection.");
        java.lang.Thread thread = new java.lang.Thread(new java.lang.Runnable() {
            @Override
            public final void run() {
                try {
                    SocketChannel.this.m403X0();
                } catch (java.io.IOException e) {
                    com.ucarhu.demo.protocol.ProtocolConfig.getLogger().errorWithThrowable(f482C, "accept more connection error", e);
                }
            }
        });
        thread.setDaemon(true);
        thread.setName(m373k("M"));
        thread.start();
    }

    public java.lang.Boolean m397S(java.lang.String str, int i, java.util.Map map) throws java.lang.Exception {
        com.ucarhu.demo.protocol.logging.ProtocolLogger interfaceC0082cM582a = com.ucarhu.demo.protocol.ProtocolConfig.getLogger();
        try {
            java.net.ServerSocket serverSocket = new java.net.ServerSocket();
            this.f492j = serverSocket;
            serverSocket.setReuseAddress(true);
            this.f492j.bind(m402W(str, i));
            onChannelBound();
            interfaceC0082cM582a.info(f482C, mo350a() + " server channel bind at " + m400T0());
            try {
                java.net.Socket socketAccept = this.f492j.accept();
                this.f493k = socketAccept;
                socketAccept.setKeepAlive(true);
                m382A0(map);
                interfaceC0082cM582a.info(f482C, mo350a() + " accept an client channel: " + this.f493k.getRemoteSocketAddress());
                synchronized (this.f502t) {
                    if (this.f500r) {
                        return java.lang.Boolean.FALSE;
                    }
                    m406a1();
                    m386G0(true);
                    m405Z0();
                    interfaceC0082cM582a.info(f482C, mo350a() + " server channel connect success!");
                    mo349Z();
                    m388J0();
                    return java.lang.Boolean.TRUE;
                }
            } catch (java.io.IOException e2) {
                mo135O();
                throw new com.ucarhu.demo.protocol.channel.ChannelIOException(mo350a() + " server channel accept error.", e2);
            }
        } catch (java.lang.Exception e3) {
            java.lang.String str2 = mo350a() + " server channel start fail: " + e3.getMessage();
            interfaceC0082cM582a.errorWithThrowable(f482C, str2, e3);
            close();
            throw new com.ucarhu.demo.protocol.channel.ChannelIOException(str2, e3);
        }
    }

    public java.lang.Boolean m399T(java.util.Map map, java.lang.String str, int i) throws java.lang.Exception {
        java.lang.Boolean bool = java.lang.Boolean.FALSE;
        com.ucarhu.demo.protocol.logging.ProtocolLogger interfaceC0082cM582a = com.ucarhu.demo.protocol.ProtocolConfig.getLogger();
        for (int i2 = 1; i2 <= this.f499q; i2++) {
            try {
                java.net.Socket socket = new java.net.Socket();
                this.f493k = socket;
                socket.setKeepAlive(true);
                m382A0(map);
                this.f493k.connect(m402W(str, i), 100);
                synchronized (this.f502t) {
                    if (this.f500r) {
                        return bool;
                    }
                    m406a1();
                    m386G0(true);
                    m405Z0();
                    interfaceC0082cM582a.info(f482C, mo350a() + " client channel connect success!");
                    mo349Z();
                    return java.lang.Boolean.TRUE;
                }
            } catch (java.io.IOException e2) {
                java.lang.String str2 = mo350a() + " channel connect error " + m400T0() + ": " + e2.getMessage() + ", try times: " + i2;
                if (this.f500r) {
                    interfaceC0082cM582a.errorWithThrowable(f482C, str2 + ", socket closed", e2);
                    return bool;
                }
                m410z0(this.f493k);
                if (i2 < this.f499q) {
                    if (i2 % 10 == 0) {
                        interfaceC0082cM582a.warn(f482C, str2 + ", will try again.");
                    }
                    java.lang.Thread.sleep(f483D);
                } else {
                    interfaceC0082cM582a.errorWithThrowable(f482C, str2, e2);
                    mo360v0(e2);
                }
            }
        }
        throw new com.ucarhu.demo.protocol.channel.ChannelIOException(mo350a() + " channel connect fail after " + this.f499q + " times");
    }

    private java.lang.String m400T0() {
        return mo354c() + ":" + mo357i();
    }

    public java.lang.Thread m401V(java.lang.Runnable runnable) {
        java.lang.Thread thread = new java.lang.Thread(runnable, m373k("R"));
        thread.setDaemon(true);
        return thread;
    }

    private static java.net.InetSocketAddress m402W(java.lang.String str, int i) throws java.net.UnknownHostException {
        java.lang.String[] strArrSplit = str.split(f480A);
        if (strArrSplit.length != 4) {
            throw new java.net.UnknownHostException("ip address is of illegal length");
        }
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            if (!android.text.TextUtils.isDigitsOnly(strArrSplit[i2])) {
                throw new java.net.UnknownHostException("invalid ip address");
            }
            bArr[i2] = (byte) (java.lang.Integer.parseInt(strArrSplit[i2]) & 255);
        }
        return new java.net.InetSocketAddress(java.net.InetAddress.getByAddress(bArr), i);
    }

    public void m403X0() throws java.io.IOException {
        com.ucarhu.demo.protocol.logging.ProtocolLogger interfaceC0082cM582a = com.ucarhu.demo.protocol.ProtocolConfig.getLogger();
        while (!this.f492j.isClosed()) {
            try {
                java.net.Socket socketAccept = this.f492j.accept();
                if (mo353b()) {
                    interfaceC0082cM582a.error(f482C, "ATTENTION: Found unknown connection from:" + socketAccept.getRemoteSocketAddress());
                    socketAccept.close();
                } else {
                    try {
                        interfaceC0082cM582a.info(f482C, mo350a() + " channel accept another client: " + this.f493k.getRemoteSocketAddress());
                        m410z0(this.f493k);
                        this.f493k = socketAccept;
                        socketAccept.setKeepAlive(true);
                        this.f493k.setTcpNoDelay(true);
                        synchronized (this.f502t) {
                            if (!this.f500r) {
                                m386G0(true);
                                m405Z0();
                                interfaceC0082cM582a.info(f482C, mo350a() + " server channel reconnect success!");
                                mo349Z();
                            }
                        }
                    } catch (java.io.IOException e2) {
                        interfaceC0082cM582a.errorWithThrowable(f482C, "connect more connect error: " + e2.getMessage(), e2);
                        m410z0(this.f493k);
                        m386G0(false);
                        mo352a(true);
                    }
                }
            } catch (java.lang.Exception e3) {
                interfaceC0082cM582a.errorWithThrowable(f482C, "accept more connection error: " + e3.getMessage(), e3);
                return;
            }
        }
    }

    public void m404Y0() throws java.io.IOException {
        this.f498p.run();
        m386G0(false);
    }

    private void m405Z0() throws java.io.IOException {
        m410z0(this.f498p);
        this.f498p = new com.ucarhu.demo.protocol.channel.socket.SocketChannelReadTask(this);
        this.f497o.submit(new java.lang.Runnable() {
            @Override
            public final void run() {
                try {
                    SocketChannel.this.m404Y0();
                } catch (java.io.IOException e) {
                    com.ucarhu.demo.protocol.ProtocolConfig.getLogger().errorWithThrowable(f482C, "restart reader error", e);
                }
            }
        });
    }

    private void m406a1() {
        this.f497o = java.util.concurrent.Executors.newSingleThreadExecutor(new java.util.concurrent.ThreadFactory() {
            @Override
            public final java.lang.Thread newThread(java.lang.Runnable runnable) {
                return SocketChannel.this.m401V(runnable);
            }
        });
        this.f496n = java.util.concurrent.Executors.newSingleThreadExecutor(new java.util.concurrent.ThreadFactory() {
            @Override
            public final java.lang.Thread newThread(java.lang.Runnable runnable) {
                return SocketChannel.this.m384C0(runnable);
            }
        });
    }

    private java.util.concurrent.Future<java.lang.Boolean> m407p0(com.ucarhu.demo.protocol.UCarMessage c0102w, long j, com.ucarhu.demo.protocol.channel.SendCallback interfaceC0060e) {
        return mo348X(c0102w.setMessageType(com.ucarhu.demo.protocol.MessageType.SEND_SYNC), j, new com.ucarhu.demo.protocol.channel.socket.SocketChannel.a(interfaceC0060e));
    }

    public void m408w0(com.ucarhu.demo.protocol.UCarMessage c0102w, com.ucarhu.demo.protocol.channel.socket.RequestFuture delayedC0076o, long j) {
        int iM671B = c0102w.getSequenceId();
        try {
            com.ucarhu.demo.protocol.channel.socket.FutureRequestManager.m364a().m369d(delayedC0076o);
            if (mo355c(c0102w, null).get(j, java.util.concurrent.TimeUnit.MILLISECONDS).booleanValue()) {
                return;
            }
            com.ucarhu.demo.protocol.channel.socket.FutureRequestManager.m364a().m367b(iM671B);
            delayedC0076o.m379O(new com.ucarhu.demo.protocol.channel.ChannelIOException("Send request fail: " + iM671B));
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.protocol.channel.socket.FutureRequestManager.m364a().m367b(iM671B);
            delayedC0076o.m379O(e2);
        }
    }

    public static void m409y0(com.ucarhu.demo.protocol.logging.ProtocolLogger interfaceC0082c, com.ucarhu.demo.protocol.UCarMessage c0102w, java.lang.Exception exc) {
        interfaceC0082c.errorWithThrowable(f482C, "send ack fail:" + c0102w.describe(), exc);
    }

    private void m410z0(java.io.Closeable closeable) throws java.io.IOException {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.protocol.ProtocolConfig.getLogger().debug(f482C, "close quietly:" + e2.getMessage());
        }
    }

    public final java.util.concurrent.Future<com.ucarhu.demo.protocol.UCarMessage> m411E0(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return m420t0(c0102w, com.ucarhu.demo.protocol.channel.socket.DefaultFutureCallback.m361d());
    }

    public void m412F0(int i) {
        this.f499q = i;
    }

    public void m413I0(boolean z) {
        this.f501s = z;
    }

    @Override
    public void onChannelBound() {
    }

    @Override
    public void mo135O() {
    }

    public java.net.Socket m414U0() {
        return this.f493k;
    }

    public com.ucarhu.demo.protocol.crypto.AesGcmSessionCrypto m415V0() {
        return this.f495m;
    }

    public boolean m416W0() {
        return m415V0() != null;
    }

    @Override
    public final <T> java.util.concurrent.Future<T> mo348X(final com.ucarhu.demo.protocol.UCarMessage c0102w, long j, com.ucarhu.demo.protocol.channel.FutureCallback<T> interfaceC0058c) {
        if (j <= 0) {
            j = Long.MAX_VALUE;
        }
        final long j2 = j;
        if (c0102w == null) {
            com.ucarhu.demo.protocol.channel.socket.RequestFuture delayedC0076o = new com.ucarhu.demo.protocol.channel.socket.RequestFuture(interfaceC0058c, 0);
            delayedC0076o.m379O(new com.ucarhu.demo.protocol.ProtocolException("Can not send a null message"));
            return delayedC0076o;
        }
        final com.ucarhu.demo.protocol.channel.socket.RequestFuture delayedC0076o2 = new com.ucarhu.demo.protocol.channel.socket.RequestFuture(interfaceC0058c, j2, c0102w.getSequenceId());
        com.ucarhu.demo.protocol.ProtocolConfig.getRequestExecutor().submit(new java.lang.Runnable() {
            @Override
            public final void run() {
                SocketChannel.this.m408w0(c0102w, delayedC0076o2, j2);
            }
        });
        return delayedC0076o2;
    }

    @Override
    public void mo349Z() {
    }

    @Override
    public void mo351a(final com.ucarhu.demo.protocol.UCarMessage c0102w) {
        boolean z;
        final com.ucarhu.demo.protocol.logging.ProtocolLogger interfaceC0082cM582a = com.ucarhu.demo.protocol.ProtocolConfig.getLogger();
        com.ucarhu.demo.protocol.channel.NetChannel.a aVar = this.f494l;
        if (aVar != null) {
            try {
                aVar.mo94a(c0102w);
                z = true;
            } catch (java.lang.Exception e2) {
                z = false;
                interfaceC0082cM582a.errorWithThrowable(f482C, mo350a() + ": handle message " + c0102w.getSequenceId() + " error.", e2);
            }
        } else {
            z = true;
        }
        if (com.ucarhu.demo.protocol.UCarMessage.isSendSync(c0102w) && z) {
            mo355c(com.ucarhu.demo.protocol.UCarMessage.createAckResponse(c0102w.getSequenceId()), new com.ucarhu.demo.protocol.channel.SendCallback() {
                @Override
                public final void onFailure(java.lang.Exception exc) {
                    com.ucarhu.demo.protocol.channel.socket.SocketChannel.m409y0(interfaceC0082cM582a, c0102w, exc);
                }
            });
        }
    }

    @Override
    public void mo352a(boolean z) {
    }

    public java.util.concurrent.Future<java.lang.Boolean> m417a0(int i, java.lang.String str) throws java.io.IOException {
        if (mo347I() == com.ucarhu.demo.protocol.channel.ChannelType.CUSTOM) {
            m374n(i);
        }
        m372K(str);
        return mo356f();
    }

    @Override
    public final boolean mo353b() {
        java.net.Socket socket;
        return this.f491i && (socket = this.f493k) != null && socket.isConnected();
    }

    public boolean m418b1() {
        return this.f501s;
    }

    @Override
    public java.util.concurrent.Future<java.lang.Boolean> mo355c(com.ucarhu.demo.protocol.UCarMessage c0102w, com.ucarhu.demo.protocol.channel.SendCallback interfaceC0060e) {
        return m421u0(c0102w, interfaceC0060e, false, -1L);
    }

    @Override
    public final void close(){
        boolean zMo353b = mo353b();
        synchronized (this.f502t) {
            this.f500r = true;
            java.util.concurrent.ExecutorService executorService = this.f497o;
            if (executorService != null) {
                executorService.shutdown();
                this.f497o = null;
            }
            java.util.concurrent.ExecutorService executorService2 = this.f496n;
            if (executorService2 != null) {
                executorService2.shutdown();
                this.f496n = null;
            }
        }
        try {
            m410z0(this.f498p);
            m410z0(this.f493k);
            m410z0(this.f492j);
        } catch (java.io.IOException e) {
            com.ucarhu.demo.protocol.ProtocolConfig.getLogger().errorWithThrowable(f482C, "close channel error", e);
        }
        m386G0(false);
        if (zMo353b) {
            com.ucarhu.demo.protocol.logging.ProtocolLogger interfaceC0082cM582a = com.ucarhu.demo.protocol.ProtocolConfig.getLogger();
            java.lang.StringBuilder sb = new java.lang.StringBuilder();
            sb.append(mo350a());
            sb.append(mo358j() ? " server" : " client");
            sb.append(" disconnect.");
            interfaceC0082cM582a.info(f482C, sb.toString());
            mo352a(mo358j());
        }
    }

    @Override
    public java.util.concurrent.Future<java.lang.Boolean> mo356f() throws java.io.IOException {
        if (mo358j()) {
            com.ucarhu.demo.protocol.ProtocolConfig.getLogger().info(f482C, mo350a() + " server channel starting: " + m400T0());
            return m387H0(mo357i(), mo354c(), null);
        }
        com.ucarhu.demo.protocol.ProtocolConfig.getLogger().info(f482C, mo350a() + " client channel starting: " + m400T0());
        return m385D0(mo357i(), mo354c(), null);
    }

    public java.util.concurrent.Future<java.lang.Boolean> m419g0(int i, java.lang.String str, java.util.Map<java.lang.String, java.lang.Integer> map) throws java.io.IOException {
        if (mo347I() == com.ucarhu.demo.protocol.channel.ChannelType.CUSTOM) {
            m374n(i);
        }
        m372K(str);
        if (mo358j()) {
            com.ucarhu.demo.protocol.ProtocolConfig.getLogger().info(f482C, mo350a() + " server channel starting: " + m400T0());
            return m387H0(mo357i(), mo354c(), map);
        }
        com.ucarhu.demo.protocol.ProtocolConfig.getLogger().info(f482C, mo350a() + " client channel starting: " + m400T0());
        return m385D0(mo357i(), mo354c(), map);
    }

    @Override
    public int mo357i() {
        int iM345a;
        java.net.ServerSocket serverSocket;
        int i = this.f472d;
        if (i != 0) {
            return i;
        }
        if (mo347I() != com.ucarhu.demo.protocol.channel.ChannelType.CUSTOM) {
            iM345a = mo347I().getPort();
        } else {
            if (!mo358j() || (serverSocket = this.f492j) == null || serverSocket.isClosed()) {
                java.net.Socket socket = this.f493k;
                if (socket != null && socket.isConnected()) {
                    java.net.InetSocketAddress inetSocketAddress = (java.net.InetSocketAddress) this.f493k.getRemoteSocketAddress();
                    if (inetSocketAddress == null) {
                        com.ucarhu.demo.protocol.ProtocolConfig.getLogger().error(f482C, "InetSocketAddress is invalid");
                        return -1;
                    }
                    iM345a = inetSocketAddress.getPort();
                }
                return this.f472d;
            }
            iM345a = this.f492j.getLocalPort();
        }
        this.f472d = iM345a;
        return this.f472d;
    }

    public final <T> java.util.concurrent.Future<T> m420t0(com.ucarhu.demo.protocol.UCarMessage c0102w, com.ucarhu.demo.protocol.channel.FutureCallback<T> interfaceC0058c) {
        return mo348X(c0102w, com.ucarhu.demo.protocol.channel.socket.RequestFuture.f475n, interfaceC0058c);
    }

    public java.util.concurrent.Future<java.lang.Boolean> m421u0(final com.ucarhu.demo.protocol.UCarMessage c0102w, final com.ucarhu.demo.protocol.channel.SendCallback interfaceC0060e, boolean z, long j) {
        if (z) {
            return m407p0(c0102w, j, interfaceC0060e);
        }
        java.lang.Boolean bool = java.lang.Boolean.FALSE;
        java.util.concurrent.CompletableFuture completableFutureCompletedFuture = java.util.concurrent.CompletableFuture.completedFuture(bool);
        if (c0102w == null) {
            completableFutureCompletedFuture.completeExceptionally(new com.ucarhu.demo.protocol.ProtocolException("Can not send a null message"));
            return completableFutureCompletedFuture;
        }
        synchronized (this.f502t) {
            java.util.concurrent.ExecutorService executorService = this.f496n;
            if (executorService != null && !executorService.isShutdown()) {
                return this.f496n.submit(new java.util.concurrent.Callable() {
                    @Override
                    public final java.lang.Object call() throws java.lang.Exception {
                        return SocketChannel.this.m383B0(c0102w, interfaceC0060e);
                    }
                });
            }
            java.lang.String str = mo350a() + " channel not ready";
            com.ucarhu.demo.protocol.ProtocolConfig.getLogger().error(f482C, str);
            if (interfaceC0060e != null) {
                interfaceC0060e.onFailure(new com.ucarhu.demo.protocol.channel.ChannelIOException(str));
            }
            completableFutureCompletedFuture.complete(bool);
            return completableFutureCompletedFuture;
        }
    }

    @Override
    public void mo360v0(java.lang.Throwable th) {
    }

    public void m422x0(com.ucarhu.demo.protocol.channel.NetChannel.a aVar) {
        if (mo353b()) {
            com.ucarhu.demo.protocol.ProtocolConfig.getLogger().warn(f482C, "Set message handler after channel connected may loss message!");
        }
        this.f494l = aVar;
    }
}
