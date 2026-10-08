package com.ucarhu.demo.sharelink.util;

public class CarVerifier {

    private static final java.lang.String f186c = "CarVerifier";

    private static final java.lang.String f187d = "ccd.data";

    private android.content.Context f188a;

    private java.lang.String f189b;

    public CarVerifier(android.content.Context context) {
        this.f188a = context;
        this.f189b = f187d;
    }

    public CarVerifier(android.content.Context context, java.lang.String str) {
        this.f188a = context;
        if (isSupportedPath(str)) {
            this.f189b = str;
        } else {
            this.f189b = f187d;
        }
    }

    private boolean isSupportedPath(java.lang.String str) {
        java.lang.String str2;
        if (android.text.TextUtils.isEmpty(str)) {
            str2 = "ccd file path is empty!";
        } else {
            java.io.File file = new java.io.File(str);
            if (file.exists() && file.isFile() && file.canRead()) {
                return true;
            }
            str2 = "ccd file path is invalid!";
        }
        com.ucarhu.demo.logging.EasyLogger.error(f186c, str2);
        return false;
    }

    private java.io.Reader openDataReader() {
        return f187d.equals(this.f189b) ? openAssetReader() : openFileReader();
    }

    private java.io.Reader openVerifiedReader() {
        com.ucarhu.demo.logging.EasyLogger.info(f186c, "getDataReader, file: " + this.f189b);
        if (this.f188a != null) {
            return openDataReader();
        }
        com.ucarhu.demo.logging.EasyLogger.error(f186c, "getDataReader null context");
        return null;
    }

    private java.io.Reader openAssetReader() {
        com.ucarhu.demo.logging.EasyLogger.info(f186c, "getDefaultReader");
        try {
            return new java.io.InputStreamReader(this.f188a.getResources().getAssets().open(f187d), java.nio.charset.StandardCharsets.UTF_8);
        } catch (java.io.IOException e2) {
            com.ucarhu.demo.logging.EasyLogger.error(f186c, "getDefaultReader failed: " + e2);
            return null;
        }
    }

    private java.io.Reader openFileReader() {
        com.ucarhu.demo.logging.EasyLogger.info(f186c, "getReader, file: " + this.f189b);
        try {
            return new java.io.InputStreamReader(new java.io.FileInputStream(this.f189b), java.nio.charset.StandardCharsets.UTF_8);
        } catch (java.io.FileNotFoundException e2) {
            com.ucarhu.demo.logging.EasyLogger.error(f186c, "getReader error: " + e2);
            return null;
        }
    }

    public com.ucarhu.demo.sharelink.util.CarVerificationData readCarVerificationData() {
        try {
            java.io.Reader readerM151d = openVerifiedReader();
            try {
                com.ucarhu.demo.sharelink.util.CarVerificationData c0023bM147b = new com.ucarhu.demo.sharelink.util.CarVerificationData.Builder().reader(readerM151d).build();
                if (readerM151d != null) {
                    readerM151d.close();
                }
                return c0023bM147b;
            } catch (java.lang.Throwable th) {
                if (readerM151d != null) {
                    try {
                        readerM151d.close();
                    } catch (java.lang.Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw new RuntimeException();
            }
        } catch (java.lang.RuntimeException e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f186c, "getCarData exception: ", e2);
            return null;
        }
    }

    public com.share.connect.Device readCarDevice() {
        com.ucarhu.demo.sharelink.util.CarVerificationData c0023bM154a = readCarVerificationData();
        if (c0023bM154a != null) {
            return c0023bM154a.toDevice();
        }
        return null;
    }

    public java.lang.String readRawCarData() {
        try {
            java.io.Reader readerM151d = openVerifiedReader();
            try {
                java.lang.String strM148c = new com.ucarhu.demo.sharelink.util.CarVerificationData.Builder().reader(readerM151d).readEncodedContent();
                if (readerM151d != null) {
                    readerM151d.close();
                }
                return strM148c;
            } catch (java.lang.Throwable th) {
                if (readerM151d != null) {
                    try {
                        readerM151d.close();
                    } catch (java.lang.Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw new RuntimeException();
            }
        } catch (java.lang.RuntimeException e2) {
            com.ucarhu.demo.logging.EasyLogger.error(f186c, "readCarData exception: " + e2);
            return null;
        }
    }
}
