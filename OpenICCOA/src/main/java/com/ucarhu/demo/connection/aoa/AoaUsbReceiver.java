package com.ucarhu.demo.connection.aoa;

public class AoaUsbReceiver extends android.content.BroadcastReceiver {

    private static final java.lang.String f260c = "AOAAccessoryReceiver";

    public static final java.lang.String f261d = "com.android.usb.USB_PERMISSION";

    private android.content.Context f262a;

    private boolean f263b;

    public AoaUsbReceiver() {
        this.f262a = null;
        this.f263b = false;
    }

    public AoaUsbReceiver(android.content.Context context) {
        this.f262a = null;
        this.f263b = false;
        this.f262a = context;
    }

    public synchronized void registerReceiver() {
        if (!this.f263b) {
            com.ucarhu.demo.logging.EasyLogger.info(f260c, "registerReceiver");
            android.content.IntentFilter intentFilter = new android.content.IntentFilter();
            intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
            intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
            this.f262a.registerReceiver(this, intentFilter);
            this.f263b = true;
        }
    }

    public synchronized void unregisterReceiver() {
        if (this.f263b) {
            try {
                com.ucarhu.demo.logging.EasyLogger.info(f260c, "unregisterReceiver");
                this.f262a.unregisterReceiver(this);
            } catch (java.lang.IllegalArgumentException e2) {
                com.ucarhu.demo.logging.EasyLogger.error(f260c, "Failed to unregister receiver: " + e2);
            }
            this.f263b = false;
        }
    }

    @Override
    public void onReceive(android.content.Context context, android.content.Intent intent) {
        java.lang.String str;
        java.lang.String str2;
        java.lang.String action = intent.getAction();
        com.ucarhu.demo.logging.EasyLogger.info(f260c, action);
        try {
            if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(action)) {
                com.ucarhu.demo.logging.EasyLogger.debug(f260c, "USB Device Attached, current state:" + com.ucarhu.demo.sharelink.core.ConnectionStateHolder.getInstance().getState());
                com.ucarhu.demo.connection.aoa.AoaConnectionManager.getInstance().resetConnectionState();
                if (com.ucarhu.demo.sharelink.core.ConnectionStateHolder.getInstance().isUsbPreparingOrConnecting() || com.ucarhu.demo.sharelink.core.ConnectionStateHolder.getInstance().isIdleOrDisconnected()) {
                    com.ucarhu.demo.connection.aoa.AoaConnectionManager.getInstance().startAoaConnectThread();
                    return;
                }
                return;
            }
            if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action)) {
                com.ucarhu.demo.logging.EasyLogger.debug(f260c, "USB Device Detached, current state:" + com.ucarhu.demo.sharelink.core.ConnectionStateHolder.getInstance().getState());
                if (com.ucarhu.demo.sharelink.core.ConnectionStateHolder.getInstance().isUsbPreparingOrConnecting()) {
                    com.ucarhu.demo.connection.aoa.AoaConnectionManager.getInstance().handleUsbDetachedDuringConnect();
                    return;
                } else {
                    com.ucarhu.demo.connection.aoa.AoaConnectionManager.getInstance().stopAoaConnectThread();
                    com.ucarhu.demo.connection.aoa.AoaConnectionManager.getInstance().handleConnectTimeout();
                    return;
                }
            }
            if (f261d.equals(action)) {
                synchronized (this) {
                    android.hardware.usb.UsbDevice usbDevice = (android.hardware.usb.UsbDevice) intent.getParcelableExtra("device");
                    if (usbDevice == null) {
                        return;
                    }
                    com.ucarhu.demo.logging.EasyLogger.info(f260c, usbDevice.toString());
                    if (intent.getBooleanExtra("permission", false)) {
                        str = f260c;
                        str2 = "permission success";
                    } else {
                        str = f260c;
                        str2 = "permission denied";
                    }
                    com.ucarhu.demo.logging.EasyLogger.info(str, str2);
                }
            }
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.infoWithThrowable(f260c, "get unknown exception", e2);
        }
    }
}
