package com.ucar.connect.aoa;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.system.OsConstants;
import com.ucarhu.demo.logging.EasyLogger;

public class UsbNative {

    private static final String TAG = "UsbNative";

    private static final int LEGACY_MAX_BULK_TRANSFER_LENGTH = 16384;

    private static int targetSdkVersion;

    static {
        System.loadLibrary("usbio");
        targetSdkVersion = -1;
    }

    /**
     * Android P 以前的 UsbDeviceConnection bulkTransfer 存在 16KB 长度限制，这里保持原始兼容逻辑。
     */
    private static int clampBulkTransferLength(int requestedLength) {
        int sdkVersion = targetSdkVersion;
        if (sdkVersion == -1) {
            return -1;
        }
        if (sdkVersion >= 28 || requestedLength <= LEGACY_MAX_BULK_TRANSFER_LENGTH) {
            return requestedLength;
        }
        return LEGACY_MAX_BULK_TRANSFER_LENGTH;
    }

    public static int bulkRead(UsbDeviceConnection usbDeviceConnection, UsbEndpoint usbEndpoint, byte[] bArr, int i, int i2, long j) {
        int safeLength = clampBulkTransferLength(i2);
        if (usbDeviceConnection == null || bArr == null || usbEndpoint == null || safeLength < 0 || i < 0 || i + safeLength > bArr.length) {
            EasyLogger.error(TAG, "invalid parameter for bulkRead");
            return -OsConstants.EINVAL;
        }
        if (usbEndpoint.getDirection() == 128) {
            return nativeBulkRead(usbDeviceConnection.getFileDescriptor(), usbEndpoint.getAddress(), bArr, i, safeLength, j);
        }
        EasyLogger.error(TAG, "wrong direction of usb endpoint for bulkRead");
        return -OsConstants.EINVAL;
    }

    public static int bulkWrite(UsbDeviceConnection usbDeviceConnection, UsbEndpoint usbEndpoint, byte[] bArr, int i, int i2, long j) {
        int safeLength = clampBulkTransferLength(i2);
        if (usbDeviceConnection == null || bArr == null || usbEndpoint == null || safeLength < 0 || i < 0 || i + safeLength > bArr.length) {
            EasyLogger.error(TAG, "invalid parameter for bulkWrite");
            return -OsConstants.EINVAL;
        }
        if (usbEndpoint.getDirection() == 0) {
            return nativeBulkWrite(usbDeviceConnection.getFileDescriptor(), usbEndpoint.getAddress(), bArr, i, safeLength, j);
        }
        EasyLogger.error(TAG, "wrong direction of usb endpoint for bulkWrite");
        return -OsConstants.EINVAL;
    }

    public static void setTargetSdkVersion(int sdkVersion) {
        targetSdkVersion = sdkVersion;
    }

    private static native int nativeBulkRead(int i, int i2, byte[] bArr, int i3, int i4, long j);

    private static native int nativeBulkWrite(int i, int i2, byte[] bArr, int i3, int i4, long j);
}
