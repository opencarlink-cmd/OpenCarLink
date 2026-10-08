package com.ucarhu.demo.connection.aoa;

public class AoaServerSocketBridgeThread extends com.ucarhu.demo.connection.aoa.AoaSocketBridgeThread {

    private static final java.lang.String f396v = "AOALocalServerSocketReadThread";

    public static final boolean f397w = true;

    private java.net.ServerSocket f398u;

    public AoaServerSocketBridgeThread(int i, java.lang.String str, int i2) {
        super(i, str, -1);
        this.f398u = null;
        try {
            this.f398u = new java.net.ServerSocket(i);
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f396v, "Create " + str + " fail.", e2);
        }
        setSocketType(1);
        setPort(i2);
    }

    private void notifyServerSocketCreated() {
        int iM289a = allocateChannelId(getPort());
        setChannelId(iM289a);
        com.ucarhu.demo.connection.aoa.AoaConnectionManager.getInstance().sendCreateSocketMessage(getPort(), iM289a, 2, getChannelMessageType());
    }

    @Override
    public void closeResources() throws java.io.IOException {
        java.net.ServerSocket serverSocket = this.f398u;
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (java.io.IOException e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f396v, "Close " + getThreadName() + " server socket fail", e2);
            }
        }
        super.closeResources();
    }

    @Override
    public void onSocketReady() {
        notifyServerSocketCreated();
    }

    @Override
    public java.net.Socket openSocket(){
        try {
            if (this.f398u == null || !isRunning()) {
                return null;
            }
            java.net.Socket socketAccept = this.f398u.accept();
            if (socketAccept == null) {
                com.ucarhu.demo.logging.EasyLogger.info(f396v, "One client connected fail: " + getThreadName());
            }
            com.ucarhu.demo.logging.EasyLogger.info(f396v, "One client connected in " + getThreadName());
            if (!f397w && socketAccept == null) {
                throw new java.lang.AssertionError();
            }
            socketAccept.setTcpNoDelay(true);
            socketAccept.setSendBufferSize(327680);
            socketAccept.setReceiveBufferSize(327680);
            return socketAccept;
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f396v, "Get Exception in accept:" + getThreadName(), e2);
            return null;
        }
    }
}
