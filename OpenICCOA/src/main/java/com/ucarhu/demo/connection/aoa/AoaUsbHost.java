package com.ucarhu.demo.connection.aoa;

/**
 * AOA USB Host 操作封装，负责识别 USB 设备、切换 Accessory 模式、批量读写和资源释放。
 * USB native 调用和重试策略保留原实现，仅增加语义化命名与职责注释。
 */


public class AoaUsbHost {

    private static final int f295A = 0;

    private static final int f296B = 8;

    private static final int f297C = 6;

    private static final int f298D = 80;

    private static final int f299E = 11520;

    private static final int f300F = 11521;

    private static final int f301G = 11524;

    private static final int f302H = 11525;

    private static final int f303I = 6353;

    private static final int f304J = 0;

    private static final int f305K = 1;

    private static final int f306L = 51;

    private static final int f307M = 52;

    private static final int f308N = 53;

    private static final java.lang.String f309O = "ICCOA";

    private static final java.lang.String f310P = "CarLink";

    private static final java.lang.String f311Q = "ICCOA CarLink";

    private static final java.lang.String f312R = "1.0.0";

    private static final java.lang.String f313S = "http://www.iccoa.cn/";

    private static final int f314T = 0;

    private static final int f315U = 1;

    private static final int f316V = 2;

    private static final int f317W = 3;

    private static final int f318X = 4;

    private static final int f319Y = 5;

    private static final int f320Z = 300;

    private static final int f321a0 = 0;

    private static final int f322b0 = 500;

    private static final int f323c0 = 10;

    private static final int f324d0 = 10;

    private static final int f325e0 = 5;

    private static final int f326f0 = 20;

    private static final int f327g0 = 40;

    private static final int f328h0 = 60;

    private static final int f329i0 = 80;

    private static final int f330j0 = 100;

    private static final int f331k0 = 2;

    private static final int f332l0 = 1;

    private static final int f333m0 = 2;

    private static final com.ucarhu.demo.connection.aoa.AoaUsbHost f334n0 = new com.ucarhu.demo.connection.aoa.AoaUsbHost();

    private static final int f335o0 = 16384;

    private static final java.util.Map<java.lang.Integer, java.lang.String> f336p0;

    private static final java.lang.String f337q = "AOAHostSetup";

    private static final java.lang.String f338r = "mtp";

    private static final java.lang.String f339s = "ptp";

    private static final java.lang.String f340t = "adb";

    private static final java.lang.String f341u = "midi";

    private static final java.lang.String f342v = "accessory";

    private static final int f343w = 6;

    private static final int f344x = 6;

    private static final int f345y = 1;

    private static final int f346z = 1;

    private java.lang.String f357k;

    private java.util.concurrent.CountDownLatch f358l;

    private android.content.Context f347a = null;

    private android.hardware.usb.UsbManager f348b = null;

    private android.hardware.usb.UsbDevice f349c = null;

    private android.hardware.usb.UsbInterface f350d = null;

    private android.hardware.usb.UsbDeviceConnection f351e = null;

    private android.hardware.usb.UsbEndpoint f352f = null;

    private android.hardware.usb.UsbEndpoint f353g = null;

    private int f354h = 0;

    private int f355i = 0;

    private int f356j = 0;

    private boolean f359m = false;

    private boolean f360n = false;

    private android.app.PendingIntent f361o = null;

    private boolean f362p = false;

    static {
        java.util.HashMap map = new java.util.HashMap();
        f336p0 = map;
        map.put(java.lang.Integer.valueOf(f303I), "google");
        map.put(8921, "oppo");
        map.put(11669, "vivo");
        map.put(java.lang.Integer.valueOf(com.ucar.vehiclesdk.UCarConnectState.ErrorCode.ERROR_USER_INTERVENTION_TIMEOUT), "xiaomi");
        map.put(10864, "oneplus");
    }

    private AoaUsbHost() {
    }

    private boolean sendAoaIdentity() {
        java.lang.String str;
        if (controlTransfer(52, 0, f309O.getBytes(java.nio.charset.StandardCharsets.UTF_8)) < 0) {
            str = "send identity AOA_MANUFACTURER fail";
        } else if (controlTransfer(52, 1, f310P.getBytes(java.nio.charset.StandardCharsets.UTF_8)) < 0) {
            str = "send identity AOA_MODEL_NAME fail";
        } else if (controlTransfer(52, 2, f311Q.getBytes(java.nio.charset.StandardCharsets.UTF_8)) < 0) {
            str = "send identity AOA_DESCRIPTION fail";
        } else if (controlTransfer(52, 3, f312R.getBytes(java.nio.charset.StandardCharsets.UTF_8)) < 0) {
            str = "send identity AOA_VERSION fail";
        } else if (controlTransfer(52, 4, f313S.getBytes(java.nio.charset.StandardCharsets.UTF_8)) < 0) {
            str = "send identity AOA_URI fail";
        } else {
            if (controlTransfer(52, 5, this.f357k.getBytes(java.nio.charset.StandardCharsets.UTF_8)) >= 0) {
                com.ucarhu.demo.logging.EasyLogger.info(f337q, "send identity string success");
                return true;
            }
            str = "send identity AOA_SERIAL_NUMBER fail";
        }
        com.ucarhu.demo.logging.EasyLogger.info(f337q, str);
        return false;
    }

    private boolean startAccessoryMode() {
        if (controlTransfer(53, 0, null) < 0) {
            com.ucarhu.demo.logging.EasyLogger.info(f337q, "start accessory mode fail");
            return false;
        }
        com.ucarhu.demo.logging.EasyLogger.info(f337q, "start accessory mode success");
        return true;
    }

    private int controlTransfer(int i, int i2, byte[] bArr) {
        android.hardware.usb.UsbDeviceConnection usbDeviceConnection = this.f351e;
        if (usbDeviceConnection == null) {
            return -1;
        }
        return usbDeviceConnection.controlTransfer(64, i, 0, i2, bArr, bArr == null ? 0 : bArr.length, 0);
    }

    private int readProtocolVersion(byte[] bArr) {
        android.hardware.usb.UsbDeviceConnection usbDeviceConnection = this.f351e;
        if (usbDeviceConnection == null) {
            return -1;
        }
        return usbDeviceConnection.controlTransfer(com.ucarhu.demo.protocol.channel.socket.SocketChannel.f490z, 51, 0, 0, bArr, bArr == null ? 0 : bArr.length, 0);
    }

    private int bulkReadChunk(byte[] bArr, int i, int i2) throws java.io.IOException {
        int iM9921a = com.ucar.connect.aoa.UsbNative.bulkRead(this.f351e, this.f352f, bArr, i, i2, 0L);
        if (iM9921a < 0) {
            com.ucarhu.demo.logging.EasyLogger.error(f337q, "bulkTransferIn error, ret = " + iM9921a + " , expect len = " + i2 + " , offset = " + i);
            try {
                java.util.concurrent.CountDownLatch countDownLatch = this.f358l;
                if (countDownLatch != null && !countDownLatch.await(300L, java.util.concurrent.TimeUnit.MILLISECONDS)) {
                    throw new java.io.IOException("wait reconnect usb device timeout.");
                }
                iM9921a = com.ucar.connect.aoa.UsbNative.bulkRead(this.f351e, this.f352f, bArr, i, i2, 0L);
                if (iM9921a < 0) {
                    throw new java.io.IOException("retry bulkTransferIn error, ret = " + iM9921a);
                }
            } catch (java.lang.InterruptedException unused) {
                throw new java.io.IOException("wait reconnect usb device Interrupted.");
            }
        }
        return iM9921a;
    }

    public java.lang.Integer bulkWriteChunk(int i, byte[] bArr, int i2) {
        int iM9922b;
        com.ucarhu.demo.logging.EasyLogger.error(f337q, "bulkTransferOut begin,len: " + i);
        try {
            iM9922b = com.ucar.connect.aoa.UsbNative.bulkWrite(this.f351e, this.f353g, bArr, i2, i, 0L);
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f337q, "bulkTransferOut end, error", e2);
            iM9922b = 0;
        }
        com.ucarhu.demo.logging.EasyLogger.error(f337q, "bulkTransferOut end, len: " + iM9922b);
        return java.lang.Integer.valueOf(iM9922b);
    }

    private void claimUsbInterface(android.hardware.usb.UsbDeviceConnection usbDeviceConnection) {
        try {
            java.lang.reflect.Method declaredMethod = java.lang.Class.forName("android.hardware.usb.UsbDeviceConnection").getDeclaredMethod("resetDevice", new java.lang.Class[0]);
            declaredMethod.setAccessible(true);
            com.ucarhu.demo.logging.EasyLogger.info(f337q, "reset usb connection is " + ((java.lang.Boolean) declaredMethod.invoke(usbDeviceConnection, new java.lang.Object[0])).booleanValue());
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f337q, "try to reset usb connection fail.", e2);
        }
    }

    private boolean switchToAccessoryMode() {
        java.lang.String str;
        com.ucarhu.demo.logging.EasyLogger.info(f337q, "changeToAccessoryMode");
        if (this.f349c == null) {
            return false;
        }
        if (!checkProtocolVersion()) {
            str = "Change Accessory Mode getProtocolVersion Fail";
        } else if (!sendAoaIdentity()) {
            str = "Change Accessory Mode sendIdentityStrings Fail";
        } else {
            if (startAccessoryMode()) {
                com.ucarhu.demo.logging.EasyLogger.info(f337q, "Change Accessory Mode Success");
                return true;
            }
            str = "Change Accessory Mode startAccessoryMode Fail";
        }
        com.ucarhu.demo.logging.EasyLogger.error(f337q, str);
        return false;
    }

    private boolean connectUsbDevice(android.hardware.usb.UsbDevice usbDevice) {
        com.ucarhu.demo.logging.EasyLogger.info(f337q, "initUsbDevice");
        closeUsbDeviceResources();
        if (usbDevice == null) {
            com.ucarhu.demo.logging.EasyLogger.error(f337q, "device is null, initUsbDevice fail");
            return false;
        }
        if (this.f348b == null) {
            com.ucarhu.demo.logging.EasyLogger.error(f337q, "mUsbManager is null, initUsbDevice fail");
            return false;
        }
        this.f349c = usbDevice;
        if (this.f354h < 40) {
            this.f354h = 40;
        }
        if (openAccessoryDevice()) {
            return false;
        }
        if (!openUsbDevice()) {
            if (this.f354h < 60) {
                this.f354h = 60;
            }
            if (switchToAccessoryMode()) {
                this.f359m = true;
                com.ucarhu.demo.connection.aoa.AoaConnectionManager.getInstance().setDeviceName(this.f349c.getProductName());
                return false;
            }
            int i = this.f356j + 1;
            this.f356j = i;
            if (i < 10) {
                return false;
            }
            com.ucarhu.demo.logging.EasyLogger.error(f337q, "can't change to accessory mode");
            com.ucarhu.demo.connection.aoa.AoaConnectionManager.getInstance().handleAoaDisconnected();
            this.f356j = 0;
            return true;
        }
        this.f356j = 0;
        if (this.f354h < 60) {
            com.ucarhu.demo.connection.aoa.AoaConnectionManager.getInstance().setDeviceName(this.f349c.getProductName());
        }
        if (this.f354h < 80) {
            this.f354h = 80;
        }
        if (!this.f359m && !this.f360n) {
            claimUsbInterface(this.f351e);
            this.f360n = true;
        }
        closeUsbConnection();
        if (this.f354h < 100) {
            this.f354h = 100;
        }
        com.ucarhu.demo.connection.aoa.AoaConnectionManager.getInstance().notifyAccessoryReady();
        this.f354h = 0;
        this.f362p = false;
        com.ucarhu.demo.logging.EasyLogger.info(f337q, "initUsbDevice success");
        return true;
    }

    @android.annotation.SuppressLint({"DiscouragedPrivateApi", "PrivateApi", "SoonBlockedPrivateApi"})
    private boolean requestUsbPermission(android.hardware.usb.UsbDevice usbDevice, android.content.Context context) {
        try {
            android.content.pm.PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                com.ucarhu.demo.logging.EasyLogger.error(f337q, "can't get package manager");
                return false;
            }
            android.content.pm.ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            java.lang.reflect.Method declaredMethod = java.lang.Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", java.lang.String.class);
            declaredMethod.setAccessible(true);
            android.os.IBinder iBinder = (android.os.IBinder) declaredMethod.invoke(null, com.ucarhu.demo.sharelink.core.ConnectionStateHolder.TRANSPORT_USB);
            java.lang.Class<?> cls = java.lang.Class.forName("android.hardware.usb.IUsbManager");
            java.lang.reflect.Method declaredMethod2 = java.lang.Class.forName("android.hardware.usb.IUsbManager$Stub").getDeclaredMethod("asInterface", android.os.IBinder.class);
            declaredMethod2.setAccessible(true);
            java.lang.Object objInvoke = declaredMethod2.invoke(null, iBinder);
            com.ucarhu.demo.logging.EasyLogger.info(f337q, "UID : " + applicationInfo.uid + " " + applicationInfo.processName + " " + applicationInfo.permission);
            java.lang.reflect.Method declaredMethod3 = cls.getDeclaredMethod("grantDevicePermission", android.hardware.usb.UsbDevice.class, java.lang.Integer.TYPE);
            declaredMethod3.setAccessible(true);
            declaredMethod3.invoke(objInvoke, usbDevice, java.lang.Integer.valueOf(applicationInfo.uid));
            com.ucarhu.demo.logging.EasyLogger.info(f337q, "Method OK : " + iBinder + "  " + objInvoke);
            return true;
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f337q, "Error trying to assigning automatic usb permission : ", e2);
            return false;
        }
    }

    private int bulkWriteFully(byte[] bArr, int i) {
        try {
            if (this.f351e == null || this.f353g == null) {
                throw new java.io.IOException("mUsbDeviceConnection or mUsbEndpointIn is null");
            }
            int i2 = 0;
            int i3 = i;
            int i4 = 0;
            while (i3 > 0) {
                int iM263m = bulkWriteWithRetry(bArr, i2, java.lang.Math.min(i3, 16384));
                if (iM263m <= 0) {
                    break;
                }
                i4 = i2 + iM263m;
                i3 -= iM263m;
                i2 = i4;
            }
            if (i4 == i) {
                return i4;
            }
            throw new java.io.IOException("Expect send " + i + " bytes, but sent " + i4 + " bytes");
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f337q, "bulkTransferOut catch exception" + e2.getMessage(), e2);
            com.ucarhu.demo.connection.aoa.AoaConnectionManager.getInstance().handleAoaDisconnected();
            return -1;
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int bulkWriteWithRetry(byte[] bArr, int i, int i2) throws java.util.concurrent.ExecutionException, java.lang.InterruptedException, java.util.concurrent.TimeoutException, java.io.IOException {
        java.lang.Integer num;
        java.lang.Exception e2;
        java.util.concurrent.CompletableFuture<java.lang.Integer> completableFutureM266p = writeChunkAsync(bArr, i, i2);
        try {
            num = completableFutureM266p.get(500L, java.util.concurrent.TimeUnit.MILLISECONDS);
            if (num == null) {
                return -1;
            }
        } catch (java.lang.Exception e3) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f337q, "bulkTransferOut error", e3);
            reopenUsbDevice();
            try {
                num = completableFutureM266p.get(10L, java.util.concurrent.TimeUnit.MILLISECONDS);
                if (num == null) {
                    try {
                        com.ucarhu.demo.logging.EasyLogger.error(f337q, "ret is null after retrieve write result again");
                        num = -1;
                    } catch (java.lang.Exception e4) {
                        e2 = e4;
                        com.ucarhu.demo.logging.EasyLogger.infoWithThrowable(f337q, "try get last bulkTransferOut result again error: ", e2);
                        if (num.intValue() <= 0) {
                        }
                        return num.intValue();
                    }
                }
            } catch (java.lang.Exception e5) {
                num = 0;
                e2 = e5;
            }
        }
        if (num.intValue() <= 0) {
            com.ucarhu.demo.logging.EasyLogger.info(f337q, "retry bulkTransferOut");
            try {
                num = writeChunkAsync(bArr, i, i2).get(500L, java.util.concurrent.TimeUnit.MILLISECONDS);
                if (num == null) {
                    return -1;
                }
            } catch (java.lang.Exception e6) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f337q, "retry bulkTransferOut error", e6);
                closeUsbDeviceResources();
                throw new java.io.IOException("retry bulkTransferOut error");
            }
        }
        return num.intValue();
    }

    private void releaseUsbInterface() {
        android.hardware.usb.UsbDeviceConnection usbDeviceConnection = this.f351e;
        if (usbDeviceConnection != null) {
            usbDeviceConnection.releaseInterface(this.f350d);
            this.f351e.close();
        }
    }

    private boolean isSupportedUsbDevice(android.hardware.usb.UsbDevice usbDevice) {
        java.lang.String str;
        if (usbDevice != null && usbDevice.getManufacturerName() != null && usbDevice.getProductName() != null && usbDevice.getSerialNumber() != null) {
            if (f336p0.containsKey(java.lang.Integer.valueOf(usbDevice.getVendorId()))) {
                str = "this device match vendor id";
            } else {
                int i = 0;
                while (true) {
                    if (i < usbDevice.getInterfaceCount()) {
                        android.hardware.usb.UsbInterface usbInterface = usbDevice.getInterface(i);
                        if (usbInterface != null) {
                            if (usbDevice.getInterfaceCount() != 1 || i != 0 || 8 != usbInterface.getInterfaceClass() || 6 != usbInterface.getInterfaceSubclass() || 80 != usbInterface.getInterfaceProtocol()) {
                                if (6 == usbInterface.getInterfaceClass() && 6 == usbInterface.getInterfaceSubclass() && 1 == usbInterface.getInterfaceProtocol()) {
                                    str = "this device is ptp device";
                                    break;
                                }
                            } else {
                                com.ucarhu.demo.logging.EasyLogger.info(f337q, "this device is storage device, ignore it");
                                return false;
                            }
                        }
                        i++;
                    } else {
                        for (int i2 = 0; i2 < usbDevice.getConfigurationCount(); i2++) {
                            java.lang.String name = usbDevice.getConfiguration(i2).getName();
                            if (name != null) {
                                if (java.util.regex.Pattern.compile(java.util.regex.Pattern.quote(f342v), 2).matcher(name).find()) {
                                    str = "this device has accessory configuration";
                                } else if (java.util.regex.Pattern.compile(java.util.regex.Pattern.quote(f340t), 2).matcher(name).find()) {
                                    str = "this device has adb configuration";
                                } else if (java.util.regex.Pattern.compile(java.util.regex.Pattern.quote(f338r), 2).matcher(name).find()) {
                                    str = "this device has mtp configuration";
                                } else if (java.util.regex.Pattern.compile(java.util.regex.Pattern.quote(f339s), 2).matcher(name).find()) {
                                    str = "this device has ptp configuration";
                                } else if (java.util.regex.Pattern.compile(java.util.regex.Pattern.quote(f341u), 2).matcher(name).find()) {
                                    str = "this device has midi configuration";
                                }
                            }
                        }
                    }
                }
            }
            com.ucarhu.demo.logging.EasyLogger.info(f337q, str);
            return true;
        }
        return false;
    }

    private java.util.concurrent.CompletableFuture<java.lang.Integer> writeChunkAsync(final byte[] bArr, final int i, final int i2) {
        return java.util.concurrent.CompletableFuture.supplyAsync(new java.util.function.Supplier() {
            @Override
            public final java.lang.Object get() {
                return AoaUsbHost.this.bulkWriteChunk(i2, bArr, i);
            }
        });
    }

    private void closeUsbDeviceResources() {
        com.ucarhu.demo.logging.EasyLogger.info(f337q, "deInitUsbDevice");
        try {
            this.f352f = null;
            this.f353g = null;
            this.f349c = null;
            releaseUsbInterface();
            this.f351e = null;
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f337q, "deInitUsbDevice fail", e2);
        }
    }

    public static com.ucarhu.demo.connection.aoa.AoaUsbHost getInstance() {
        return f334n0;
    }

    private boolean checkProtocolVersion() {
        byte[] bArr = new byte[2];
        if (readProtocolVersion(bArr) < 0) {
            com.ucarhu.demo.logging.EasyLogger.info(f337q, "get protocol version fail");
            return false;
        }
        int i = bArr[0] | (bArr[1] << 8);
        if (i < 1 || i > 2) {
            com.ucarhu.demo.logging.EasyLogger.error(f337q, "usb device not capable of AOA 1.0 or 2.0, version = " + i);
            return false;
        }
        com.ucarhu.demo.logging.EasyLogger.info(f337q, "usb device AOA version is " + i);
        return true;
    }

    private void closeUsbConnection() {
        com.ucarhu.demo.logging.EasyLogger.info(f337q, "get endpoint to read/write");
        this.f352f = this.f350d.getEndpoint(0);
        this.f353g = this.f350d.getEndpoint(1);
    }

    private boolean openAccessoryDevice() {
        try {
            this.f350d = this.f349c.getInterface(0);
            android.hardware.usb.UsbDeviceConnection usbDeviceConnectionOpenDevice = this.f348b.openDevice(this.f349c);
            this.f351e = usbDeviceConnectionOpenDevice;
            usbDeviceConnectionOpenDevice.claimInterface(this.f350d, true);
            return false;
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f337q, "initUsbDevice fail", e2);
            closeUsbDeviceResources();
            return true;
        }
    }

    private boolean openUsbDevice() {
        com.ucarhu.demo.logging.EasyLogger.info(f337q, "isAccessoryMode");
        android.hardware.usb.UsbDevice usbDevice = this.f349c;
        boolean z = false;
        if (usbDevice == null) {
            return false;
        }
        int vendorId = usbDevice.getVendorId();
        int productId = this.f349c.getProductId();
        if (vendorId == f303I && (productId == f299E || productId == f300F || productId == f301G || productId == f302H)) {
            z = true;
        }
        com.ucarhu.demo.logging.EasyLogger.info(f337q, z ? "Android device attached in Accessory Mode" : "Android device attached not in Accessory Mode");
        return z;
    }

    private void reopenUsbDevice() {
        com.ucarhu.demo.logging.EasyLogger.info(f337q, "reConnectUsbDevice");
        this.f358l = new java.util.concurrent.CountDownLatch(1);
        releaseUsbInterface();
        openAccessoryDevice();
        closeUsbConnection();
        this.f358l.countDown();
    }

    public int bulkReadFully(byte[] bArr, int i) throws java.io.IOException {
        try {
            if (this.f351e == null || this.f352f == null) {
                throw new java.io.IOException("mUsbDeviceConnection or mUsbEndpointIn is null");
            }
            int i2 = 0;
            int i3 = i;
            int i4 = 0;
            while (i3 > 0) {
                int iM256d = bulkReadChunk(bArr, i2, java.lang.Math.min(i3, 16384));
                if (iM256d <= 0) {
                    break;
                }
                i4 = i2 + iM256d;
                i3 -= iM256d;
                i2 = i4;
            }
            if (i4 != 0 && i4 != i) {
                throw new java.io.IOException("Expect bulkTransferIn " + i + " bytes, but received " + i4 + " bytes");
            }
            return i4;
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f337q, "bulkTransferIn catch exception " + e2.getMessage(), e2);
            com.ucarhu.demo.connection.aoa.AoaConnectionManager.getInstance().handleAoaDisconnected();
            return -1;
        }
    }

    public synchronized int bulkWriteHeaderAndBody(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (bulkWriteFully(bArr, i) < 0) {
            com.ucarhu.demo.logging.EasyLogger.error(f337q, "bulkTransferOut fail 1");
            return -1;
        }
        if (bulkWriteFully(bArr2, i2) < 0) {
            com.ucarhu.demo.logging.EasyLogger.error(f337q, "bulkTransferOut fail 2");
            return -1;
        }
        return i + i2;
    }

    public void initialize(android.content.Context context, java.lang.String str, java.lang.String str2, java.lang.String str3, java.lang.String str4, java.lang.String str5, java.lang.String str6) {
        com.ucarhu.demo.logging.EasyLogger.info(f337q, "init");
        this.f347a = context;
        this.f357k = com.ucarhu.demo.connection.aoa.AoaIdentityFormatter.formatIdentity(str2, str, str3, str4, str5, str6);
        this.f348b = (android.hardware.usb.UsbManager) this.f347a.getSystemService(com.ucarhu.demo.sharelink.core.ConnectionStateHolder.TRANSPORT_USB);
        com.ucar.connect.aoa.UsbNative.setTargetSdkVersion(this.f347a.getApplicationInfo().targetSdkVersion);
        this.f361o = android.app.PendingIntent.getBroadcast(this.f347a, 0, new android.content.Intent(com.ucarhu.demo.connection.aoa.AoaUsbReceiver.f261d), 0);
    }

    public void releaseUsbDevice() {
        closeUsbDeviceResources();
        this.f359m = false;
        this.f360n = false;
        this.f362p = false;
    }

    public void resetConnectProgress() {
        this.f354h = 0;
        this.f356j = 0;
        this.f355i = 0;
    }

    public boolean scanAndConnectUsbDevice() {
        android.hardware.usb.UsbManager usbManager;
        if (this.f347a == null || (usbManager = this.f348b) == null || this.f361o == null) {
            com.ucarhu.demo.logging.EasyLogger.error(f337q, "scanUsbDevices fail");
            return false;
        }
        java.util.HashMap<java.lang.String, android.hardware.usb.UsbDevice> deviceList = usbManager.getDeviceList();
        com.ucarhu.demo.logging.EasyLogger.info(f337q, "device count = " + deviceList.size());
        if (deviceList.size() == 0) {
            if (this.f354h > 0) {
                int i = this.f355i + 1;
                this.f355i = i;
                if (i < 5) {
                    return false;
                }
                com.ucarhu.demo.logging.EasyLogger.info(f337q, "can't get devices again after change to AOA mode");
                this.f355i = 0;
                return true;
            }
            this.f355i = 0;
        }
        for (android.hardware.usb.UsbDevice usbDevice : deviceList.values()) {
            if (usbDevice != null) {
                int vendorId = usbDevice.getVendorId();
                int productId = usbDevice.getProductId();
                com.ucarhu.demo.logging.EasyLogger.info(f337q, "vid = 0x" + com.ucarhu.demo.util.binary.HexStringUtils.toFixedWidthHex(vendorId, 4));
                com.ucarhu.demo.logging.EasyLogger.info(f337q, "pid = 0x" + com.ucarhu.demo.util.binary.HexStringUtils.toFixedWidthHex(productId, 4));
                com.ucarhu.demo.logging.EasyLogger.info(f337q, usbDevice.toString());
                if (!isSupportedUsbDevice(usbDevice)) {
                    com.ucarhu.demo.logging.EasyLogger.info(f337q, "ignore non Android device");
                } else if (this.f348b.hasPermission(usbDevice) || requestUsbPermission(usbDevice, this.f347a)) {
                    if (this.f354h < 20) {
                        this.f354h = 20;
                    }
                    if (connectUsbDevice(usbDevice)) {
                        com.ucarhu.demo.logging.EasyLogger.info(f337q, "init device success");
                        return true;
                    }
                } else if (!this.f362p) {
                    this.f348b.requestPermission(usbDevice, this.f361o);
                    this.f362p = true;
                }
            }
        }
        return false;
    }
}
