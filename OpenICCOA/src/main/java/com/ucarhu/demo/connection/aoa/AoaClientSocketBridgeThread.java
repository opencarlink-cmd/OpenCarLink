package com.ucarhu.demo.connection.aoa;

public class AoaClientSocketBridgeThread extends com.ucarhu.demo.connection.aoa.AoaSocketBridgeThread {

    private static final java.lang.String f391u = "AOALocalClientSocketReadThread";

    private static final int f392v = 20;

    private static final int f393w = 100;

    private static final java.lang.String f394x = "\\.";

    private static final int f395y = 4;

    public AoaClientSocketBridgeThread(int i, java.lang.String str, int i2, int i3) {
        super(i, str, i2);
        setSocketType(2);
        if (getChannelId() == -1) {
            com.ucarhu.demo.logging.EasyLogger.error(f391u, "invalid channel id");
        }
        setPort(i3);
    }

    private static java.net.InetSocketAddress loopbackAddress(java.lang.String str, int i) throws java.net.UnknownHostException {
        java.lang.String[] strArrSplit = str.split(f394x);
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

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public java.net.Socket openSocket(){
        int i = 20;
        while (true) {
            int i2 = i - 1;
            if (i <= 0 || !isRunning()) {
                break;
            }
            try {
                java.net.Socket socket = new java.net.Socket();
                socket.connect(loopbackAddress("127.0.0.1", getPort()), 100);
                socket.setTcpNoDelay(true);
                socket.setSendBufferSize(327680);
                socket.setReceiveBufferSize(327680);
                return socket;
            } catch (java.io.IOException e2) {
                com.ucarhu.demo.logging.EasyLogger.infoWithThrowable(f391u, "start Connect socket fail " + getChannelName(), e2);
                try {
                    if (isRunning()) {
                        java.lang.Thread.sleep(100L);
                    }
                } catch (java.lang.InterruptedException e3) {
                    com.ucarhu.demo.logging.EasyLogger.infoWithThrowable(f391u, "sleep exception: " + getChannelName(), e3);
                }
                i = i2;
            }
        }
        return null;
    }
}
