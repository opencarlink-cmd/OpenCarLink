package com.ucarhu.demo.sharelink.core;

/**
 * ShareLink 观察者分发器，负责把连接、认证、进度、设备发现等事件转发给上层监听者。
 * 每个通知方法都捕获单个观察者异常，避免一个回调失败影响其他观察者。
 */


public class ShareLinkObserverDispatcher {

    private static final java.lang.String f57b = "ShareObservers";

    private final java.util.List<com.share.connect.ShareLinkObserver> f58a = new java.util.ArrayList();

    public synchronized void clearObservers() {
        this.f58a.clear();
    }

    public synchronized void notifyConnectFailed(int i) {
        com.ucarhu.demo.logging.EasyLogger.debug(f57b, "notifyConnectFailed: " + i);
        java.util.Iterator<com.share.connect.ShareLinkObserver> it = this.f58a.iterator();
        while (it.hasNext()) {
            try {
                it.next().onConnectFailed(i);
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f57b, "Observer.onConnectFailed(" + i + ") invoke failed.", e2);
            }
        }
    }

    public synchronized void notifyDeviceLost(com.share.connect.Device device) {
        java.util.Iterator<com.share.connect.ShareLinkObserver> it = this.f58a.iterator();
        while (it.hasNext()) {
            try {
                it.next().onDeviceDiscover(false, device);
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f57b, "Observer.onDeviceDiscover(false, " + device.getVin() + "...) invoke failed.", e2);
            }
        }
    }

    public synchronized void addObserver(com.share.connect.ShareLinkObserver shareLinkObserver) {
        if (!this.f58a.contains(shareLinkObserver)) {
            this.f58a.add(shareLinkObserver);
        }
    }

    public synchronized void notifyClientAddress(java.lang.String str) {
        com.ucarhu.demo.logging.EasyLogger.debug(f57b, "notifyClientAddress: " + str);
        java.util.Iterator<com.share.connect.ShareLinkObserver> it = this.f58a.iterator();
        while (it.hasNext()) {
            try {
                it.next().receivedClientAddress(str);
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f57b, "Observer.receivedClientAddress(" + str + ") invoke failed.", e2);
            }
        }
    }

    public synchronized void notifyClientHello(java.lang.String str, java.lang.String str2, java.lang.String str3) {
        com.ucarhu.demo.logging.EasyLogger.debug(f57b, "notifyClientHello: " + str + " " + str2);
        java.util.Iterator<com.share.connect.ShareLinkObserver> it = this.f58a.iterator();
        while (it.hasNext()) {
            try {
                it.next().receivedClientHello(str, str2, str3);
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f57b, "Observer.receiveClientHello(" + str + ", " + str2 + ") invoke failed.", e2);
            }
        }
    }

    public synchronized void notifyUserInterventionNeeded(boolean z) {
        com.ucarhu.demo.logging.EasyLogger.debug(f57b, "notifyNeedUserIntervention");
        java.util.Iterator<com.share.connect.ShareLinkObserver> it = this.f58a.iterator();
        while (it.hasNext()) {
            try {
                it.next().onUserInterventionNeeded(z);
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f57b, "Observer.onUserInterventionNeeded() invoke failed.", e2);
            }
        }
    }

    public synchronized void notifyAuthenticationSucceeded() {
        com.ucarhu.demo.logging.EasyLogger.debug(f57b, "notifyAuthSucceeded");
        java.util.Iterator<com.share.connect.ShareLinkObserver> it = this.f58a.iterator();
        while (it.hasNext()) {
            try {
                it.next().onAuthenticationOk();
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f57b, "Observer.onAuthenticationOk() invoke failed.", e2);
            }
        }
    }

    public synchronized void notifyProgress(int i) {
        com.ucarhu.demo.logging.EasyLogger.debug(f57b, "notifyProgress: " + i);
        java.util.Iterator<com.share.connect.ShareLinkObserver> it = this.f58a.iterator();
        while (it.hasNext()) {
            try {
                it.next().onProgress(i);
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f57b, "Observer.onProgress(" + i + ") invoke failed.", e2);
            }
        }
    }

    public synchronized void notifyDeviceDiscovered(com.share.connect.Device device) {
        java.util.Iterator<com.share.connect.ShareLinkObserver> it = this.f58a.iterator();
        while (it.hasNext()) {
            try {
                it.next().onDeviceDiscover(true, device);
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f57b, "Observer.onDeviceDiscover(true, " + device.getVin() + "...) invoke failed.", e2);
            }
        }
    }

    public synchronized void removeObserver(com.share.connect.ShareLinkObserver shareLinkObserver) {
        this.f58a.remove(shareLinkObserver);
    }

    public synchronized void notifyClientInfo(java.lang.String str) {
        com.ucarhu.demo.logging.EasyLogger.debug(f57b, "notifyClientInfo: " + str);
        java.util.Iterator<com.share.connect.ShareLinkObserver> it = this.f58a.iterator();
        while (it.hasNext()) {
            try {
                it.next().receivedClientInfo(str);
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f57b, "Observer.receivedClientInfo()", e2);
            }
        }
    }

    public synchronized void notifyOpenResult(boolean z) {
        com.ucarhu.demo.logging.EasyLogger.debug(f57b, "notifyOpenResult: " + z);
        java.util.Iterator<com.share.connect.ShareLinkObserver> it = this.f58a.iterator();
        while (it.hasNext()) {
            try {
                it.next().onOpenResult(z);
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f57b, "Observer.onOpenResult(" + z + ") invoke failed.", e2);
            }
        }
    }

    public synchronized void notifyConnected() {
        com.ucarhu.demo.logging.EasyLogger.debug(f57b, "notifyConnected: ");
        java.util.Iterator<com.share.connect.ShareLinkObserver> it = this.f58a.iterator();
        while (it.hasNext()) {
            try {
                it.next().onConnected();
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f57b, "Observer.onConnected() invoke failed.", e2);
            }
        }
    }

    public synchronized void notifyDisconnected() {
        com.ucarhu.demo.logging.EasyLogger.debug(f57b, "notifyDisconnected");
        java.util.Iterator<com.share.connect.ShareLinkObserver> it = this.f58a.iterator();
        while (it.hasNext()) {
            try {
                it.next().onDisconnected();
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f57b, "Observer.onDisconnected() invoke failed.", e2);
            }
        }
    }
}
