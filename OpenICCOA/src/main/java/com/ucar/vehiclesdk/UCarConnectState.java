package com.ucar.vehiclesdk;

public class UCarConnectState {
    public static final int DISCONNECT_BY_USER = 11;
    public static final int UCAR_DEVICE_CAST_CONNECTED = 4;
    public static final int UCAR_DEVICE_CAST_DISCONNECTED = 5;
    public static final int UCAR_DEVICE_CONNECTED = 2;
    public static final int UCAR_DEVICE_CONNECT_FAILED = 3;
    public static final int UCAR_DEVICE_DISCONNECT = 1;
    public static final int UCAR_DEVICE_GO_TO_HOME = 6;

    public static class ConnectType {
        public static final int CONNECT_TYPE_USB = 2;
        public static final int CONNECT_TYPE_WIFI_P2P = 1;
        public static final int CONNECT_TYPE_WIFI_SOFT_AP = 3;
    }

    public static class ErrorCode {
        public static final int ERROR_CONNECT_AUTH_ERROR = 10009;
        public static final int ERROR_CONNECT_INNER_FAILED = 10008;
        public static final int ERROR_CONNECT_TIMEOUT = 10006;
        public static final int ERROR_INIT_FAILED = 10001;
        public static final int ERROR_INVALID_PARAMETER = 10002;
        public static final int ERROR_START_CAST_FAILED = 10005;
        public static final int ERROR_USB_CONNECT_FAILED = 10004;
        public static final int ERROR_USER_INTERVENTION_TIMEOUT = 10007;
        public static final int ERROR_WIRELESS_CONNECT_FAILED = 10003;
    }
}
