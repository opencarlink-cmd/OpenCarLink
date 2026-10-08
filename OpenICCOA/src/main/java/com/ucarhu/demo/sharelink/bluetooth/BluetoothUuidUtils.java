package com.ucarhu.demo.sharelink.bluetooth;

public class BluetoothUuidUtils {

    public static final android.os.ParcelUuid BLUETOOTH_BASE_UUID = android.os.ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");

    public static final int UUID_BYTES_16_BIT = 2;

    public static final int UUID_BYTES_32_BIT = 4;

    public static final int UUID_BYTES_128_BIT = 16;

    public static int getServiceIdentifier(android.os.ParcelUuid parcelUuid) {
        return (int) ((parcelUuid.getUuid().getMostSignificantBits() & (-4294967296L)) >>> 32);
    }

    public static android.os.ParcelUuid parseUuidFromBytes(byte[] uuidBytes) {
        long shortUuid;
        if (uuidBytes == null) {
            throw new java.lang.IllegalArgumentException("uuidBytes cannot be null");
        }
        int length = uuidBytes.length;
        if (length != UUID_BYTES_16_BIT && length != UUID_BYTES_32_BIT && length != UUID_BYTES_128_BIT) {
            throw new java.lang.IllegalArgumentException("uuidBytes length invalid - " + length);
        }
        if (length == UUID_BYTES_128_BIT) {
            java.nio.ByteBuffer byteBufferOrder = java.nio.ByteBuffer.wrap(uuidBytes).order(java.nio.ByteOrder.LITTLE_ENDIAN);
            return new android.os.ParcelUuid(new java.util.UUID(byteBufferOrder.getLong(8), byteBufferOrder.getLong(0)));
        }
        if (length == UUID_BYTES_16_BIT) {
            shortUuid = (uuidBytes[0] & 0xFF) + ((uuidBytes[1] & 0xFF) << 8);
        } else {
            shortUuid = ((uuidBytes[3] & 0xFF) << 24) + (uuidBytes[0] & 0xFF) + ((uuidBytes[1] & 0xFF) << 8) + ((uuidBytes[2] & 0xFF) << 16);
        }
        android.os.ParcelUuid baseUuid = BLUETOOTH_BASE_UUID;
        return new android.os.ParcelUuid(new java.util.UUID(baseUuid.getUuid().getMostSignificantBits() + (shortUuid << 32), baseUuid.getUuid().getLeastSignificantBits()));
    }

    public static boolean is16BitUuid(android.os.ParcelUuid parcelUuid) {
        java.util.UUID uuid = parcelUuid.getUuid();
        return uuid.getLeastSignificantBits() == BLUETOOTH_BASE_UUID.getUuid().getLeastSignificantBits() && (uuid.getMostSignificantBits() & (-281470681743361L)) == 4096;
    }

    public static boolean is32BitUuid(android.os.ParcelUuid parcelUuid) {
        java.util.UUID uuid = parcelUuid.getUuid();
        return uuid.getLeastSignificantBits() == BLUETOOTH_BASE_UUID.getUuid().getLeastSignificantBits() && !is16BitUuid(parcelUuid) && (uuid.getMostSignificantBits() & 4294967295L) == 4096;
    }

    public static byte[] uuidToBytes(android.os.ParcelUuid parcelUuid) {
        if (parcelUuid == null) {
            throw new java.lang.IllegalArgumentException("uuid cannot be null");
        }
        if (is16BitUuid(parcelUuid)) {
            int iM69a = getServiceIdentifier(parcelUuid);
            return new byte[]{(byte) (iM69a & 255), (byte) ((iM69a & 65280) >> 8)};
        }
        if (is32BitUuid(parcelUuid)) {
            int iM69a2 = getServiceIdentifier(parcelUuid);
            return new byte[]{(byte) (iM69a2 & 255), (byte) ((65280 & iM69a2) >> 8), (byte) ((16711680 & iM69a2) >> 16), (byte) ((iM69a2 & -16777216) >> 24)};
        }
        long mostSignificantBits = parcelUuid.getUuid().getMostSignificantBits();
        long leastSignificantBits = parcelUuid.getUuid().getLeastSignificantBits();
        byte[] bArr = new byte[16];
        java.nio.ByteBuffer byteBufferOrder = java.nio.ByteBuffer.wrap(bArr).order(java.nio.ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder.putLong(8, mostSignificantBits);
        byteBufferOrder.putLong(0, leastSignificantBits);
        return bArr;
    }
}
