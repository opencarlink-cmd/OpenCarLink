package com.ucarhu.demo.connection.aoa;

/**
 * Android Open Accessory 连接管理器，负责 USB 插拔、AOA 连接线程、读线程和本地 Socket 桥接。
 * 该类是 AOA 传输链路的主控入口，业务代码通过它启动和释放 AOA 连接资源。
 */


public class AoaConnectionManager {

    private static final java.lang.String f264k = "AOAConnectManager";

    private static final java.lang.String f265l = "AOAConnectThread";

    private static final java.lang.String f266m = "AOAReadThread";

    private static final int f267n = 500;

    private static final int f268o = 67108864;

    private static final long f269p = 2000;

    private static final int f270q = 4;

    private static final int f271r = 0;

    private static final int f272s = 4;

    private static final int f273t = 4;

    private static final int f274u = 1;

    private static final int f275v = 8;

    private static final int f276w = 1;

    private static final int f277x = 9;

    @android.annotation.SuppressLint({"StaticFieldLeak"})
    private static final com.ucarhu.demo.connection.aoa.AoaConnectionManager f278y = new com.ucarhu.demo.connection.aoa.AoaConnectionManager();

    private final android.util.SparseArray<com.ucarhu.demo.connection.aoa.AoaSocketBridgeThread> f279a = new android.util.SparseArray<>();

    private boolean f280b = false;

    private boolean f281c = false;

    private android.content.Context f282d = null;

    private com.ucarhu.demo.connection.aoa.AoaConnectionManager.c f283e = null;

    private com.ucarhu.demo.connection.aoa.AoaConnectionManager.d f284f = null;

    private com.ucarhu.demo.connection.aoa.AoaConnectionManager.b f285g = null;

    private java.util.Timer f286h;

    private java.util.TimerTask f287i;

    private com.ucarhu.demo.connection.aoa.AoaUsbReceiver f288j;

    public class a extends java.util.TimerTask {
        public a() {
        }

        @Override
        public void run() {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.connection.aoa.AoaConnectionManager.f264k, "connect time out, no device attached");
            com.ucarhu.demo.connection.aoa.AoaConnectionManager.this.handleConnectTimeout();
        }
    }

    public interface b {
        void mo245a();

        void mo246a(java.lang.String str);

        void mo247b();

        void mo248c();
    }

    public static class c extends java.lang.Thread {

        private boolean f290b = true;

        public c() {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.connection.aoa.AoaConnectionManager.f264k, "AOAConnectThread Created");
            setName(com.ucarhu.demo.connection.aoa.AoaConnectionManager.f265l);
        }

        public void requestStop() {
            this.f290b = false;
        }

        @Override
        public void run() {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.connection.aoa.AoaConnectionManager.f264k, "Begin to connect UCar by AOA");
            com.ucarhu.demo.connection.aoa.AoaUsbHost.getInstance().resetConnectProgress();
            while (this.f290b) {
                try {
                    if (com.ucarhu.demo.connection.aoa.AoaUsbHost.getInstance().scanAndConnectUsbDevice()) {
                        com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.connection.aoa.AoaConnectionManager.f264k, "UCar connect exit");
                        return;
                    }
                    java.lang.Thread.sleep(500L);
                } catch (java.lang.Exception e2) {
                    com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.connection.aoa.AoaConnectionManager.f264k, "Exception when connect UCar by AOA", e2);
                    return;
                }
            }
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.connection.aoa.AoaConnectionManager.f264k, "UCar connect thread stopped");
        }
    }

    public class d extends java.lang.Thread {

        private boolean f291b = false;

        private byte[] f292c = new byte[8];

        private final byte[] f293d = new byte[8];

        public d() {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.connection.aoa.AoaConnectionManager.f264k, "AOAReadThread Created");
            setName(com.ucarhu.demo.connection.aoa.AoaConnectionManager.f266m);
        }

        public void requestStop() {
            this.f291b = false;
        }

        @Override
        public void run() {
            java.lang.String str = null;
            this.f291b = true;
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.connection.aoa.AoaConnectionManager.f264k, "Begin to read data by AOA");
            while (this.f291b) {
                try {
                    int iM274c = com.ucarhu.demo.connection.aoa.AoaUsbHost.getInstance().bulkReadFully(this.f293d, 8);
                    if (iM274c < 0) {
                        str = "head bulkTransferIn fail";
                    } else if (iM274c != 0) {
                        int iM720b = com.ucarhu.demo.util.binary.ByteOrderUtils.readInt32Be(this.f293d, 0);
                        int iM720b2 = com.ucarhu.demo.util.binary.ByteOrderUtils.readInt32Be(this.f293d, 4);
                        if (iM720b < 1 || iM720b > 240 || iM720b2 < 0 || iM720b2 > com.ucarhu.demo.connection.aoa.AoaConnectionManager.f268o) {
                            str = "channelId or lenMsg is error";
                        } else {
                            int iMax = java.lang.Math.max(iM720b2, 8);
                            if (this.f292c.length < iMax) {
                                this.f292c = new byte[iMax];
                            }
                            if (com.ucarhu.demo.connection.aoa.AoaUsbHost.getInstance().bulkReadFully(this.f292c, iMax) < 0) {
                                str = "body bulkTransferIn fail";
                            } else if (iM720b == 1) {
                                com.ucarhu.demo.connection.aoa.AoaConnectionManager.this.handleCreateSocketMessage(this.f292c, iM720b2);
                            } else {
                                com.ucarhu.demo.connection.aoa.AoaSocketBridgeThread abstractC0050iM220m = com.ucarhu.demo.connection.aoa.AoaConnectionManager.this.findBridgeThreadByChannelId(iM720b);
                                if (abstractC0050iM220m != null) {
                                    abstractC0050iM220m.writeFully(this.f292c, 0, iM720b2);
                                } else {
                                    com.ucarhu.demo.logging.EasyLogger.warn(com.ucarhu.demo.connection.aoa.AoaConnectionManager.f264k, "AOAReadThread channelId " + iM720b + " not found read thread");
                                }
                            }
                        }
                    }
                    if (str != null) {
                        com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.connection.aoa.AoaConnectionManager.f264k, str);
                        return;
                    }
                } catch (java.lang.Exception e2) {
                    com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.connection.aoa.AoaConnectionManager.f264k, "Exception when read data by AOA", e2);
                    return;
                }
            }
        }
    }

    private AoaConnectionManager() {
    }

    private java.lang.String getChannelNameByPort(int i) {
        return com.ucarhu.demo.protocol.channel.ChannelType.fromPort(i).name();
    }

    private void startServerSocketBridge(int i, java.lang.String str, int i2) {
        try {
            com.ucarhu.demo.connection.aoa.AoaServerSocketBridgeThread c0049h = new com.ucarhu.demo.connection.aoa.AoaServerSocketBridgeThread(i, str, i2);
            c0049h.start();
            c0049h.awaitSocketReady();
            this.f279a.append(i, c0049h);
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f264k, "Start ServerSocketRead Thread Fail", e2);
        }
    }

    private void startClientSocketBridge(int i, java.lang.String str, int i2, int i3) {
        try {
            com.ucarhu.demo.connection.aoa.AoaClientSocketBridgeThread c0048g = new com.ucarhu.demo.connection.aoa.AoaClientSocketBridgeThread(i, str, i2, i3);
            c0048g.start();
            c0048g.awaitSocketReady();
            this.f279a.append(i, c0048g);
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f264k, "Start ClientSocketRead Thread Fail", e2);
        }
    }

    public void handleCreateSocketMessage(byte[] bArr, int i) {
        if (i == 10) {
            int iM720b = com.ucarhu.demo.util.binary.ByteOrderUtils.readInt32Be(bArr, 0);
            int iM720b2 = com.ucarhu.demo.util.binary.ByteOrderUtils.readInt32Be(bArr, 4);
            int iM738t = com.ucarhu.demo.util.binary.ByteOrderUtils.readUInt8(bArr, 8);
            int iM738t2 = com.ucarhu.demo.util.binary.ByteOrderUtils.readUInt8(bArr, 9);
            com.ucarhu.demo.logging.EasyLogger.info(f264k, "received create socket msg, port " + iM720b + " channelId" + iM720b2 + " socketType" + iM738t + " channelMsgType" + iM738t2);
            if (iM738t != 1) {
                if (iM738t == 2) {
                    startClientSocketBridge(iM720b, getChannelNameByPort(iM720b), iM720b2, iM738t2);
                    return;
                }
                return;
            }
            if (iM720b2 != -1) {
                com.ucarhu.demo.logging.EasyLogger.warn(f264k, "Expect received sever socket channel id is -1, but received " + iM720b2);
            }
            if (this.f279a.get(iM720b) == null) {
                startServerSocketBridge(iM720b, getChannelNameByPort(iM720b), iM738t2);
            }
            notifyDefaultSocketsReadyIfNeeded();
        }
    }

    public com.ucarhu.demo.connection.aoa.AoaSocketBridgeThread findBridgeThreadByChannelId(int i) {
        for (int i2 = 0; i2 < this.f279a.size(); i2++) {
            com.ucarhu.demo.connection.aoa.AoaSocketBridgeThread abstractC0050iValueAt = this.f279a.valueAt(i2);
            if (abstractC0050iValueAt != null && abstractC0050iValueAt.getChannelId() == i) {
                return abstractC0050iValueAt;
            }
        }
        return null;
    }

    private void notifyDefaultSocketsReadyIfNeeded() {
        if (this.f280b) {
            return;
        }
        for (int i = 0; i < this.f279a.size(); i++) {
            if (this.f279a.valueAt(i) == null) {
                com.ucarhu.demo.logging.EasyLogger.info(f264k, "null read thread port at " + this.f279a.keyAt(i));
                return;
            }
        }
        com.ucarhu.demo.logging.EasyLogger.info(f264k, "all needed default socket ready ");
        cancelConnectTimeoutTimer();
        getInstance().notifyAoaConnected();
        this.f280b = true;
    }

    public static com.ucarhu.demo.connection.aoa.AoaConnectionManager getInstance() {
        return f278y;
    }

    private void createConnectTimeoutTimer() {
        this.f287i = new com.ucarhu.demo.connection.aoa.AoaConnectionManager.a();
        this.f286h = new java.util.Timer();
    }

    private void prepareDefaultSocketSlots() {
        clearSocketBridgeThreads();
        this.f279a.put(com.ucarhu.demo.protocol.channel.ChannelType.RTSP.getPort(), null);
        this.f279a.put(com.ucarhu.demo.protocol.channel.ChannelType.UIBC.getPort(), null);
    }

    public void registerReceiverAndStartConnect() {
        com.ucarhu.demo.connection.aoa.AoaUsbReceiver c0043b = this.f288j;
        if (c0043b != null) {
            c0043b.registerReceiver();
        }
        startAoaConnectThread();
    }

    public void stopAoaConnectThread() {
        com.ucarhu.demo.logging.EasyLogger.info(f264k, "stopAOAConnectThread");
        try {
            com.ucarhu.demo.connection.aoa.AoaConnectionManager.c cVar = this.f283e;
            if (cVar != null) {
                cVar.requestStop();
                this.f283e = null;
            }
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f264k, "Stop AOAConnectThread Fail", e2);
        }
    }

    public void stopAoaReadThread() {
        try {
            com.ucarhu.demo.connection.aoa.AoaConnectionManager.d dVar = this.f284f;
            if (dVar != null) {
                dVar.requestStop();
                this.f284f = null;
            }
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f264k, "Stop AOAReadThread Fail", e2);
        }
    }

    public void clearSocketBridgeThreads() {
        try {
            this.f280b = false;
            for (int i = 0; i < this.f279a.size(); i++) {
                com.ucarhu.demo.connection.aoa.AoaSocketBridgeThread abstractC0050iValueAt = this.f279a.valueAt(i);
                if (abstractC0050iValueAt != null) {
                    abstractC0050iValueAt.closeResources();
                }
            }
            this.f279a.clear();
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f264k, "Stop SocketRead Thread Fail", e2);
        }
    }

    public void m229E() {
        com.ucarhu.demo.connection.aoa.AoaUsbReceiver c0043b = this.f288j;
        if (c0043b != null) {
            c0043b.unregisterReceiver();
        }
        stopAoaConnectThread();
    }

    public void resetConnectionState() {
        if (this.f286h != null) {
            com.ucarhu.demo.logging.EasyLogger.info(f264k, "cancel connect timer");
            this.f286h.cancel();
            this.f287i = null;
            this.f286h = null;
        }
    }

    public void sendCreateSocketMessage(int i, int i2, int i3, int i4) {
        com.ucarhu.demo.logging.EasyLogger.info(f264k, "send create socket msg, port " + i + " channelId " + i2 + " socketType " + i3 + " channelMsgType " + i4);
        byte[] bArr = new byte[8];
        byte[] bArr2 = new byte[10];
        java.lang.System.arraycopy(com.ucarhu.demo.util.binary.ByteOrderUtils.toInt32BeBytes(1), 0, bArr, 0, 4);
        java.lang.System.arraycopy(com.ucarhu.demo.util.binary.ByteOrderUtils.toInt32BeBytes(10), 0, bArr, 4, 4);
        java.lang.System.arraycopy(com.ucarhu.demo.util.binary.ByteOrderUtils.toInt32BeBytes(i), 0, bArr2, 0, 4);
        java.lang.System.arraycopy(com.ucarhu.demo.util.binary.ByteOrderUtils.toInt32BeBytes(i2), 0, bArr2, 4, 4);
        java.lang.System.arraycopy(com.ucarhu.demo.util.binary.ByteOrderUtils.toUInt8Bytes(i3), 0, bArr2, 8, 1);
        java.lang.System.arraycopy(com.ucarhu.demo.util.binary.ByteOrderUtils.toUInt8Bytes(i4), 0, bArr2, 9, 1);
        if (com.ucarhu.demo.connection.aoa.AoaUsbHost.getInstance().bulkWriteHeaderAndBody(bArr, 8, bArr2, 10) < 0) {
            com.ucarhu.demo.logging.EasyLogger.error(f264k, "bulkTransferOut create socket msg failed");
        }
    }

    public void registerServerSocketIfNeeded(int i, boolean z) {
        sendCreateSocketMessage(i, -1, 1, z ? 1 : 2);
    }

    public void m233h(com.ucarhu.demo.connection.aoa.AoaConnectionManager.b bVar) {
        this.f285g = bVar;
    }

    public void initialize(android.content.Context context, java.lang.String str, java.lang.String str2, java.lang.String str3, java.lang.String str4, java.lang.String str5, java.lang.String str6) {
        com.ucarhu.demo.logging.EasyLogger.info(f264k, "init");
        if (this.f282d == null) {
            this.f282d = context;
            com.ucarhu.demo.connection.aoa.AoaUsbHost.getInstance().initialize(this.f282d, str, str2, str3, str4, str5, str6);
            this.f288j = new com.ucarhu.demo.connection.aoa.AoaUsbReceiver(context);
        }
    }

    public void setDeviceName(java.lang.String str) {
        com.ucarhu.demo.connection.aoa.AoaConnectionManager.b bVar = this.f285g;
        if (bVar != null) {
            bVar.mo246a(str);
        }
    }

    public void deinitialize() {
        com.ucarhu.demo.logging.EasyLogger.info(f264k, "deInit");
        stopAoaConnectThread();
        stopAoaReadThread();
        clearSocketBridgeThreads();
        com.ucarhu.demo.connection.aoa.AoaUsbHost.getInstance().releaseUsbDevice();
    }

    public void handleAoaDisconnected() {
        com.ucarhu.demo.logging.EasyLogger.info(f264k, "notify connect error");
        this.f281c = false;
    }

    public void handleConnectTimeout() {
        com.ucarhu.demo.logging.EasyLogger.info(f264k, "notify disconnected, is connect right: " + this.f281c);
        if (this.f281c) {
            return;
        }
        deinitialize();
        com.ucarhu.demo.connection.aoa.AoaConnectionManager.b bVar = this.f285g;
        if (bVar != null) {
            bVar.mo245a();
        }
    }

    public void notifyAoaConnected() {
        this.f281c = true;
        com.ucarhu.demo.connection.aoa.AoaConnectionManager.b bVar = this.f285g;
        if (bVar != null) {
            bVar.mo248c();
        }
    }

    public void notifyAccessoryReady() {
        startAoaReadThread();
        com.ucarhu.demo.connection.aoa.AoaConnectionManager.b bVar = this.f285g;
        if (bVar != null) {
            bVar.mo247b();
        }
        stopAoaConnectThread();
    }

    public void handleUsbDetachedDuringConnect() {
        createConnectTimeoutTimer();
        if (this.f286h != null) {
            com.ucarhu.demo.logging.EasyLogger.info(f264k, "schedule cancel connect timer");
            this.f286h.schedule(this.f287i, f269p);
        }
    }

    public void startAoaConnectThread() {
        com.ucarhu.demo.logging.EasyLogger.info(f264k, "startAOAConnectThread, mAOAConnectThread:" + this.f283e);
        try {
            com.ucarhu.demo.connection.aoa.AoaConnectionManager.c cVar = this.f283e;
            if (cVar == null || !cVar.isAlive()) {
                com.ucarhu.demo.connection.aoa.AoaConnectionManager.c cVar2 = new com.ucarhu.demo.connection.aoa.AoaConnectionManager.c();
                this.f283e = cVar2;
                cVar2.start();
            } else {
                com.ucarhu.demo.logging.EasyLogger.info(f264k, "mAOAConnectThread is alive, not need recreate. ");
            }
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f264k, "Start AOAConnectThread Fail", e2);
        }
    }

    public void startAoaReadThread() {
        try {
            prepareDefaultSocketSlots();
            com.ucarhu.demo.connection.aoa.AoaConnectionManager.d dVar = new com.ucarhu.demo.connection.aoa.AoaConnectionManager.d();
            this.f284f = dVar;
            dVar.start();
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f264k, "Start AOAReadThread Fail", e2);
        }
    }

    public void cancelConnectTimeoutTimer() {
        sendCreateSocketMessage(com.ucarhu.demo.protocol.channel.ChannelType.RTP.getPort(), -1, 1, 2);
    }
}
