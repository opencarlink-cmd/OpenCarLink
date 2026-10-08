package com.ucarhu.demo.util.binary;

public class HexStringUtils {
    public static int parseIntOrDefault(java.lang.String str, int i, int i2) {
        try {
            return java.lang.Integer.parseInt(str, i2);
        } catch (java.lang.NumberFormatException unused) {
            return i;
        }
    }

    public static java.lang.String toHex(int i) {
        java.lang.String hexString = java.lang.Integer.toHexString(i);
        if (hexString.length() % 2 == 1) {
            hexString = "0" + hexString;
        }
        return hexString.toUpperCase();
    }

    public static java.lang.String toFixedWidthHex(int i, int i2) {
        java.lang.String hexString = java.lang.Integer.toHexString(i);
        if (hexString.length() % 2 == 1) {
            hexString = "0" + hexString;
        }
        return leftPadZero(hexString.toUpperCase(), i2);
    }

    public static java.lang.String hexToAscii(java.lang.String str) {
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        int length = str.length() / 2;
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            sb.append(java.lang.String.valueOf((char) hexToInt(str.substring(i2, i2 + 2))));
        }
        return sb.toString();
    }

    public static java.lang.String chunkedHexToString(java.lang.String str, int i) {
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        int length = str.length() / i;
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 * i;
            i2++;
            sb.append((char) hexToInt(str.substring(i3, i2 * i)));
        }
        return sb.toString();
    }

    public static java.lang.String bytesToHex(byte[] bArr) {
        if (bArr == null) {
            throw new java.lang.IllegalArgumentException("Argument b ( byte array ) is null! ");
        }
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        for (byte b2 : bArr) {
            java.lang.String hexString = java.lang.Integer.toHexString(b2 & 0xFF);
            if (hexString.length() == 1) {
                sb.append("0");
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase();
    }

    public static int binaryToInt(java.lang.String str) {
        int iPow = 0;
        int length2 = str.length();
        for (int length = str.length(); length > 0; length--) {
            iPow = (int) (iPow + (java.lang.Math.pow(2.0d, length2 - length) * (str.charAt(length - 1) - '0')));
        }
        return iPow;
    }

    public static int parseDecimalOrDefault(java.lang.String str, int i) {
        try {
            return java.lang.Integer.parseInt(str);
        } catch (java.lang.NumberFormatException unused) {
            return i;
        }
    }

    public static java.lang.String bytesToAscii(byte[] bArr) {
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        for (byte b2 : bArr) {
            sb.append((char) b2);
        }
        return sb.toString();
    }

    public static java.lang.String leftPadZero(java.lang.String str, int i) {
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        for (int i2 = 0; i2 < i - str.length(); i2++) {
            sb.insert(0, "0");
        }
        return (((java.lang.Object) sb) + str).substring(0, i);
    }

    public static byte[] hexToBytes(java.lang.String str) throws java.lang.IllegalArgumentException {
        if (str.length() % 2 != 0) {
            throw new java.lang.IllegalArgumentException();
        }
        char[] charArray = str.toCharArray();
        byte[] bArr = new byte[str.length() / 2];
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            java.lang.StringBuilder sb = new java.lang.StringBuilder();
            sb.append("");
            int i3 = i + 1;
            sb.append(charArray[i]);
            sb.append(charArray[i3]);
            bArr[i2] = java.lang.Integer.valueOf(java.lang.Integer.parseInt(sb.toString(), 16) & 255).byteValue();
            i = i3 + 1;
            i2++;
        }
        return bArr;
    }

    public static int hexToInt(java.lang.String str) {
        java.lang.String upperCase = str.toUpperCase();
        int iPow = 0;
        int length2 = upperCase.length();
        for (int length = upperCase.length(); length > 0; length--) {
            char cCharAt = upperCase.charAt(length - 1);
            iPow = (int) (iPow + (java.lang.Math.pow(16.0d, length2 - length) * ((cCharAt < '0' || cCharAt > '9') ? (cCharAt - 'A') + 10 : cCharAt - '0')));
        }
        return iPow;
    }

    public static java.lang.String hexToBinaryString(java.lang.String str) {
        java.lang.String str2;
        java.lang.String upperCase = str.toUpperCase();
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        int length = upperCase.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = upperCase.charAt(i);
            switch (cCharAt) {
                case '0':
                    str2 = "0000";
                    sb.append(str2);
                    break;
                case '1':
                    str2 = "0001";
                    sb.append(str2);
                    break;
                case '2':
                    str2 = "0010";
                    sb.append(str2);
                    break;
                case '3':
                    str2 = "0011";
                    sb.append(str2);
                    break;
                case '4':
                    str2 = "0100";
                    sb.append(str2);
                    break;
                case '5':
                    str2 = "0101";
                    sb.append(str2);
                    break;
                case '6':
                    str2 = "0110";
                    sb.append(str2);
                    break;
                case '7':
                    str2 = "0111";
                    sb.append(str2);
                    break;
                case '8':
                    str2 = "1000";
                    sb.append(str2);
                    break;
                case '9':
                    str2 = "1001";
                    sb.append(str2);
                    break;
                default:
                    switch (cCharAt) {
                        case 'A':
                            str2 = "1010";
                            sb.append(str2);
                            break;
                        case 'B':
                            str2 = "1011";
                            sb.append(str2);
                            break;
                        case 'C':
                            str2 = "1100";
                            sb.append(str2);
                            break;
                        case 'D':
                            str2 = "1101";
                            sb.append(str2);
                            break;
                        case 'E':
                            str2 = "1110";
                            sb.append(str2);
                            break;
                        case 'F':
                            str2 = "1111";
                            sb.append(str2);
                            break;
                    }
            }
        }
        return sb.toString();
    }

    public static byte[] signedHexToBytes(java.lang.String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        java.lang.String strM755m = hexToBinaryString(str);
        int i = 0;
        while (i < length) {
            int i2 = i * 8;
            int i3 = i + 1;
            bArr[i] = (byte) binaryToInt(strM755m.substring(i2 + 1, i3 * 8));
            if (strM755m.charAt(i2) == '1') {
                bArr[i] = (byte) (-bArr[i]);
            }
            i = i3;
        }
        return bArr;
    }

    public static java.lang.String normalizeHexString(java.lang.String str) {
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            sb.append(java.lang.Integer.toHexString(str.charAt(i)));
        }
        return sb.toString();
    }
}
