package com.ucarhu.demo.sharelink.util;

public class CarVerificationData {

    public static final java.lang.String SIGNATURE_ALGORITHM_SHA256_RSA = "SHA256withRSA";

    private static final int SUPPORTED_VERSION = 1;

    private static final java.lang.String KEY_PUBLIC_KEY = "public_key";

    private static final java.lang.String TAG = "CarVerificationData";

    private static final java.lang.String DEVICE_FIELD_SHORT_NAME = "short_name";

    private static final java.lang.String DEVICE_FIELD_VENDOR_ID = "vendor_id";

    private static final java.lang.String DEVICE_FIELD_VENDOR_NAME = "vendor_name";

    private static final java.lang.String DEVICE_FIELD_PRODUCT_ID = "product_id";

    private static final java.lang.String DEVICE_FIELD_PRODUCT_NAME = "product_name";

    @com.google.gson.annotations.SerializedName("content")
    private byte[] content;

    @com.google.gson.annotations.SerializedName("signature")
    private byte[] signature;

    @com.google.gson.annotations.SerializedName("version")
    private int version;

    @com.google.gson.annotations.SerializedName("cryptoConfigIndex")
    private java.lang.String cryptoConfigIndex;

    @com.google.gson.annotations.SerializedName("SignAlgorithm")
    private java.lang.String signAlgorithm;

    @com.google.gson.annotations.SerializedName("salt")
    private java.lang.String salt;

    public static final class Builder {

        private java.io.Reader reader;

        public com.ucarhu.demo.sharelink.util.CarVerificationData.Builder reader(java.io.Reader reader) {
            this.reader = reader;
            return this;
        }

        public com.ucarhu.demo.sharelink.util.CarVerificationData build() throws java.io.IOException {
            if (this.reader == null) {
                throw new java.lang.IllegalArgumentException("Reader is null");
            }
            java.lang.String encodedContent = readEncodedContent();
            if (android.text.TextUtils.isEmpty(encodedContent)) {
                return null;
            }
            return new com.google.gson.Gson().fromJson(new java.lang.String(java.util.Base64.getDecoder().decode(encodedContent), java.nio.charset.StandardCharsets.UTF_8), com.ucarhu.demo.sharelink.util.CarVerificationData.class);
        }

        public java.lang.String readEncodedContent() throws java.io.IOException {
            if (this.reader == null) {
                com.ucarhu.demo.logging.EasyLogger.error(com.ucarhu.demo.sharelink.util.CarVerificationData.TAG, "CarVerificationData  no reader to build");
                throw new java.lang.IllegalArgumentException("Reader is null");
            }
            java.lang.StringBuilder encodedContent = new java.lang.StringBuilder();
            try {
                char[] buffer = new char[1024];
                while (true) {
                    int length = this.reader.read(buffer);
                    if (length <= 0) {
                        return encodedContent.toString().trim();
                    }
                    encodedContent.append(buffer, 0, length);
                }
            } catch (java.io.IOException e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.sharelink.util.CarVerificationData.TAG, " read car verification data exception ", e2);
                return null;
            }
        }
    }

    private CarVerificationData() {
    }

    @androidx.annotation.Nullable
    public com.share.connect.Device toDevice() {
        try {
            com.google.gson.JsonObject deviceJson = com.google.gson.JsonParser.parseString(new java.lang.String(this.content, java.nio.charset.StandardCharsets.UTF_8)).getAsJsonObject();
            com.share.connect.Device device = new com.share.connect.Device();
            device.setShortName(getStringOrNull(deviceJson, DEVICE_FIELD_SHORT_NAME));
            device.setVid(getStringOrNull(deviceJson, DEVICE_FIELD_VENDOR_ID));
            device.setVName(getStringOrNull(deviceJson, DEVICE_FIELD_VENDOR_NAME));
            device.setPid(getStringOrNull(deviceJson, DEVICE_FIELD_PRODUCT_ID));
            device.setPName(getStringOrNull(deviceJson, DEVICE_FIELD_PRODUCT_NAME));
            return device;
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(TAG, "getCarDevice exception ", e2);
            return null;
        }
    }

    private static java.lang.String getStringOrNull(com.google.gson.JsonObject jsonObject, java.lang.String name) {
        com.google.gson.JsonElement element = jsonObject.get(name);
        return element == null || element.isJsonNull() ? null : element.getAsString();
    }

    public boolean isValid() {
        return this.content != null && this.content.length > 0 && this.signature != null && this.signature.length > 0 && !android.text.TextUtils.isEmpty(this.cryptoConfigIndex) && this.version > 0;
    }
}
