package com.ucarhu.demo.sharelink.core;

/**
 * 连接状态集中管理器，统一记录 USB、Wi-Fi P2P、Wi-Fi AP 等连接阶段。
 *
 * <p>这里只维护状态字符串和查询方法，不直接发起连接，避免状态判断分散在各业务类中。</p>
 */
public class ConnectionStateHolder {

    public static final java.lang.String STATE_IDLE = "idle";

    public static final java.lang.String STATE_USB_CONNECTING = "usb_connecting";

    public static final java.lang.String STATE_USB_PREPARING = "usb_prepare_connecting";

    public static final java.lang.String STATE_USB_CONNECTED = "usb_connected";

    public static final java.lang.String STATE_USB_DISCONNECTED = "usb_disconnect";

    public static final java.lang.String STATE_WIFI_CONNECTING = "wifi_connecting";

    public static final java.lang.String STATE_WIFI_CONNECTED = "wifi_connected";

    public static final java.lang.String STATE_WIFI_DISCONNECTED = "wifi_disconnect";

    private static final java.lang.String STATE_KEYWORD_CONNECTING = "connecting";

    private static final java.lang.String STATE_KEYWORD_CONNECTED = "connected";

    private static final java.lang.String STATE_KEYWORD_DISCONNECT = "disconnect";

    public static final java.lang.String TRANSPORT_USB = "usb";

    public static final java.lang.String TRANSPORT_WIFI_P2P = "wifi_p2p";

    public static final java.lang.String TRANSPORT_WIFI_AP = "wifi_ap";

    public static final java.lang.String TRANSPORT_WIFI = "wifi";

    public static final int CONNECT_TYPE_WIFI_P2P = 1001;

    public static final int CONNECT_TYPE_WIFI_AP = 1002;

    public static final int CONNECT_TYPE_WIFI_AUTO = 1003;

    private static volatile com.ucarhu.demo.sharelink.core.ConnectionStateHolder instance;

    private volatile java.lang.String state = STATE_IDLE;

    public static com.ucarhu.demo.sharelink.core.ConnectionStateHolder getInstance() {
        if (instance == null) {
            synchronized (com.ucarhu.demo.sharelink.core.ConnectionStateHolder.class) {
                if (instance == null) {
                    instance = new com.ucarhu.demo.sharelink.core.ConnectionStateHolder();
                }
            }
        }
        return instance;
    }

    public synchronized java.lang.String getState() {
        return this.state;
    }

    public synchronized void setState(java.lang.String state) {
        this.state = state;
    }

    public synchronized boolean isConnected() {
        return this.state.contains(STATE_KEYWORD_CONNECTED);
    }

    public synchronized boolean isConnecting() {
        return this.state.contains(STATE_KEYWORD_CONNECTING);
    }

    public synchronized boolean isDisconnectedState() {
        return this.state.contains(STATE_KEYWORD_DISCONNECT);
    }

    public synchronized boolean isIdleOrDisconnected() {
        return this.state.equals(STATE_IDLE) || isDisconnectedState();
    }

    public synchronized boolean isUsbConnected() {
        return this.state.equals(STATE_USB_CONNECTED);
    }

    public synchronized boolean isUsbPreparingOrConnecting() {
        return this.state.equals(STATE_USB_CONNECTING) || this.state.equals(STATE_USB_PREPARING);
    }

    public synchronized boolean isWifiConnected() {
        return this.state.equals(STATE_WIFI_CONNECTED);
    }

    public synchronized boolean isWifiConnecting() {
        return this.state.equals(STATE_WIFI_CONNECTING);
    }
}
