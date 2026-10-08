package com.ucarhu.demo.connection.aoa;

public abstract class AoaSocketBridgeThread extends java.lang.Thread {

    public static final int f399n = 327680;

    public static final int f400o = 327680;

    private static final int f401p = 500;

    private static final java.lang.String f402q = "AOASocketReadThread";

    private static final java.lang.String f403r = "SocketReadThread";

    public static final int f404s = 8;

    private static final java.util.concurrent.atomic.AtomicInteger f405t = new java.util.concurrent.atomic.AtomicInteger(16);

    private final int f408d;

    private boolean f409e;

    private final java.lang.String f410f;

    private final java.lang.String f411g;

    private final java.util.concurrent.CountDownLatch f406b = new java.util.concurrent.CountDownLatch(1);

    private final byte[] f407c = new byte[8];

    private java.net.Socket f412h = null;

    private java.io.BufferedInputStream f413i = null;

    private java.io.BufferedOutputStream f414j = null;

    private volatile int f415k = -1;

    private int f416l = -1;

    private int f417m = 2;

    public static class a {

        public static final int[] f418a;

        static {
            com.ucarhu.demo.protocol.channel.ChannelType.values();
            int[] iArr = new int[10];
            f418a = iArr;
            try {
                com.ucarhu.demo.protocol.channel.ChannelType enumC0057b = com.ucarhu.demo.protocol.channel.ChannelType.RTP;
                iArr[3] = 1;
            } catch (java.lang.NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = f418a;
                com.ucarhu.demo.protocol.channel.ChannelType enumC0057b2 = com.ucarhu.demo.protocol.channel.ChannelType.RTSP;
                iArr2[2] = 2;
            } catch (java.lang.NoSuchFieldError unused2) {
            }
            try {
                int[] iArr3 = f418a;
                com.ucarhu.demo.protocol.channel.ChannelType enumC0057b3 = com.ucarhu.demo.protocol.channel.ChannelType.UIBC;
                iArr3[1] = 3;
            } catch (java.lang.NoSuchFieldError unused3) {
            }
            try {
                int[] iArr4 = f418a;
                com.ucarhu.demo.protocol.channel.ChannelType enumC0057b4 = com.ucarhu.demo.protocol.channel.ChannelType.AUTH;
                iArr4[4] = 4;
            } catch (java.lang.NoSuchFieldError unused4) {
            }
            try {
                int[] iArr5 = f418a;
                com.ucarhu.demo.protocol.channel.ChannelType enumC0057b5 = com.ucarhu.demo.protocol.channel.ChannelType.CONTROL;
                iArr5[5] = 5;
            } catch (java.lang.NoSuchFieldError unused5) {
            }
            try {
                int[] iArr6 = f418a;
                com.ucarhu.demo.protocol.channel.ChannelType enumC0057b6 = com.ucarhu.demo.protocol.channel.ChannelType.MEDIA;
                iArr6[6] = 6;
            } catch (java.lang.NoSuchFieldError unused6) {
            }
            try {
                int[] iArr7 = f418a;
                com.ucarhu.demo.protocol.channel.ChannelType enumC0057b7 = com.ucarhu.demo.protocol.channel.ChannelType.SENSOR;
                iArr7[7] = 7;
            } catch (java.lang.NoSuchFieldError unused7) {
            }
            try {
                int[] iArr8 = f418a;
                com.ucarhu.demo.protocol.channel.ChannelType enumC0057b8 = com.ucarhu.demo.protocol.channel.ChannelType.CERT;
                iArr8[8] = 8;
            } catch (java.lang.NoSuchFieldError unused8) {
            }
        }
    }

    public AoaSocketBridgeThread(int i, java.lang.String str, int i2) {
        this.f408d = i;
        java.lang.String str2 = str + f403r;
        this.f411g = str2;
        setName(str2);
        com.ucarhu.demo.logging.EasyLogger.info(f402q, "Create " + str2 + ",ChannelId " + i2);
        this.f410f = str;
        if (i2 != -1) {
            setChannelId(i2);
        }
        this.f409e = true;
    }

    public int allocateChannelId(int i) {
        switch (com.ucarhu.demo.protocol.channel.ChannelType.fromPort(i).ordinal()) {
            case 1:
                return 5;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 7;
            case 5:
                return 2;
            case 6:
                return 6;
            case 7:
                return 8;
            case 8:
                return 9;
            default:
                return f405t.getAndIncrement();
        }
    }

    public int readFully(byte[] bArr, int i, int i2) throws java.io.IOException {
        try {
            if (this.f413i == null) {
                throw new java.io.IOException("mInputStream is null");
            }
            int i3 = 0;
            int i4 = i2;
            while (i4 > 0) {
                int i5 = this.f413i.read(bArr, i + i3, i4);
                if (i5 <= 0) {
                    throw new java.io.IOException("Receive Data Error: ret = " + i5);
                }
                i4 -= i5;
                i3 += i5;
            }
            if (i3 == i2) {
                return i3;
            }
            throw new java.io.IOException("Expect receive " + i2 + " bytes, but received " + i3 + " bytes");
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f402q, this.f410f + " readData exception:" + e2.getMessage(), e2);
            return -1;
        }
    }

    public void closeResources() throws java.io.IOException {
        try {
            java.net.Socket socket = this.f412h;
            if (socket != null) {
                socket.close();
                this.f412h = null;
            }
            java.io.BufferedInputStream bufferedInputStream = this.f413i;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
                this.f413i = null;
            }
            java.io.BufferedOutputStream bufferedOutputStream = this.f414j;
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
                this.f414j = null;
            }
            this.f409e = false;
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f402q, "Close " + this.f411g + " fail. ", e2);
        }
    }

    public int tryRead(byte[] bArr, int i, int i2) throws java.io.IOException {
        try {
            java.io.BufferedInputStream bufferedInputStream = this.f413i;
            if (bufferedInputStream != null) {
                return bufferedInputStream.read(bArr, i, i2);
            }
            throw new java.io.IOException("mInputStream is null");
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f402q, this.f410f + " tryReadData exception:" + e2.getMessage(), e2);
            return -1;
        }
    }

    public java.lang.String describe() {
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        sb.append("--------------------------------\n");
        sb.append("Socket name(");
        sb.append(getChannelName());
        sb.append(")\nSocket port(");
        sb.append(getPort());
        sb.append(")\nChannel ID(");
        sb.append(getChannelId());
        sb.append(")\nSocket type(");
        sb.append(isServerSocket() ? "server" : "client");
        sb.append(")\nMsg type(");
        sb.append(isUcarMessageChannel() ? "ucar" : "raw");
        sb.append(")\n");
        return sb.toString();
    }

    public void setChannelId(int i) {
        if (i == -1) {
            com.ucarhu.demo.logging.EasyLogger.error(f402q, "set " + this.f410f + " channel id failed, use invalid channel id");
            return;
        }
        if (this.f415k == -1) {
            this.f415k = i;
            java.lang.System.arraycopy(com.ucarhu.demo.util.binary.ByteOrderUtils.toInt32BeBytes(this.f415k), 0, this.f407c, 0, 4);
            return;
        }
        com.ucarhu.demo.logging.EasyLogger.warn(f402q, "set " + this.f410f + " channel id failed, channel id already set");
    }

    public int getChannelId() {
        return this.f415k;
    }

    public int writeFully(byte[] bArr, int i, int i2) throws java.io.IOException {
        try {
            java.io.BufferedOutputStream bufferedOutputStream = this.f414j;
            if (bufferedOutputStream == null) {
                throw new java.io.IOException("mOutputStream is null");
            }
            bufferedOutputStream.write(bArr, i, i2);
            this.f414j.flush();
            return i2;
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f402q, this.f410f + " writeData exception:" + e2.getMessage(), e2);
            return -1;
        }
    }

    public void setPort(int i) {
        if (i == 1 || i == 2) {
            this.f417m = i;
        } else {
            com.ucarhu.demo.logging.EasyLogger.error(f402q, "invalid msg type");
        }
    }

    public int getChannelMessageType() {
        return this.f417m;
    }

    public void setSocketType(int i) {
        this.f416l = i;
    }

    public java.lang.String getChannelName() {
        return this.f410f;
    }

    public int getPort() {
        return this.f408d;
    }

    public int getSocketType() {
        return this.f416l;
    }

    public java.lang.String getThreadName() {
        return this.f411g;
    }

    public boolean isRunning() {
        return this.f409e;
    }

    public boolean isServerSocket() {
        return getSocketType() == 1;
    }

    public boolean isUcarMessageChannel() {
        return getChannelMessageType() == 1;
    }

    @Override
    public void run() {
        java.lang.String str;
        com.ucarhu.demo.logging.EasyLogger.info(f402q, "Begin to run in " + this.f411g);
        if (getSocketType() != 2) {
            markRunning();
        }
        java.net.Socket socketMo285v = openSocket();
        this.f412h = socketMo285v;
        if (socketMo285v != null) {
            try {
                this.f413i = new java.io.BufferedInputStream(this.f412h.getInputStream());
                this.f414j = new java.io.BufferedOutputStream(this.f412h.getOutputStream());
                onSocketReady();
            } catch (java.io.IOException e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f402q, "Get Exception in " + this.f411g, e2);
            }
        }
        if (getSocketType() == 2) {
            markRunning();
        }
        try {
            byte[] bArr = new byte[20];
            byte[] bArr2 = new byte[65536];
            while (true) {
                java.net.Socket socket = this.f412h;
                if (socket == null || !this.f409e) {
                    return;
                }
                if (!socket.isConnected()) {
                    str = "socket is disconnected when read data";
                    break;
                }
                if (this.f417m != 1) {
                    int iM291d = tryRead(bArr2, 0, 65536);
                    if (iM291d < 0) {
                        return;
                    }
                    com.ucarhu.demo.util.binary.ByteOrderUtils.writeInt32Be(iM291d, this.f407c, 4);
                    if (com.ucarhu.demo.connection.aoa.AoaUsbHost.getInstance().bulkWriteHeaderAndBody(this.f407c, 8, bArr2, java.lang.Math.max(iM291d, 8)) < 0) {
                        str = "raw data bulkTransferOut fail";
                        break;
                    }
                } else {
                    if (readFully(bArr, 0, 20) < 0) {
                        return;
                    }
                    int iM720b = com.ucarhu.demo.util.binary.ByteOrderUtils.readInt32Be(bArr, 0);
                    int i = iM720b - 20;
                    com.ucarhu.demo.util.binary.ByteOrderUtils.writeInt32Be(iM720b, this.f407c, 4);
                    if (bArr.length < iM720b) {
                        byte[] bArr3 = new byte[iM720b];
                        java.lang.System.arraycopy(bArr, 0, bArr3, 0, 20);
                        bArr = bArr3;
                    }
                    if (readFully(bArr, 20, i) < 0) {
                        return;
                    }
                    if (com.ucarhu.demo.connection.aoa.AoaUsbHost.getInstance().bulkWriteHeaderAndBody(this.f407c, 8, bArr, iM720b) < 0) {
                        str = "ucar data bulkTransferOut fail";
                        break;
                    }
                }
            }
            com.ucarhu.demo.logging.EasyLogger.error(f402q, str);
        } catch (java.lang.Exception e3) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f402q, "get Exception in ReadThread", e3);
        }
    }

    public void markRunning() {
        this.f406b.countDown();
    }

    public void onSocketReady() {
    }

    @Override
    @androidx.annotation.NonNull
    public java.lang.String toString() {
        return describe();
    }

    public void awaitSocketReady() {
        try {
            com.ucarhu.demo.logging.EasyLogger.debug(f402q, "Waiting for socket running");
            if (this.f406b.await(500L, java.util.concurrent.TimeUnit.MILLISECONDS)) {
                return;
            }
            com.ucarhu.demo.logging.EasyLogger.debug(f402q, "Waiting for socket ready timeout");
        } catch (java.lang.InterruptedException e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f402q, "Socket READY.await() failed.", e2);
        }
    }

    public abstract java.net.Socket openSocket();
}
