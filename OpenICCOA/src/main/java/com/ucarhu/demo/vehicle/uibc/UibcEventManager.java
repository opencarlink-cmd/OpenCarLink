package com.ucarhu.demo.vehicle.uibc;

public class UibcEventManager {

    private static final java.lang.String f780i = "UibcManager";

    private static final java.lang.String f781j = "uibc_session_key";

    private static final java.lang.String f782k = "type";

    private static final java.lang.String f783l = "action";

    private static final java.lang.String f784m = "keycode";

    private static final java.lang.String f785n = "metaState";

    private static final java.lang.String f786o = "width";

    private static final java.lang.String f787p = "height";

    private static final java.lang.String f788q = "count";

    private static final int f789r = 1;

    private static final int f790s = 2;

    private volatile boolean f791a = false;

    private com.ucarhu.demo.protocol.crypto.AesGcmSessionCrypto f792b;

    private java.lang.String f793c;

    private final android.content.Context f794d;

    private int f795e;

    private int f796f;

    private int f797g;

    private int f798h;

    public UibcEventManager(android.content.Context context) {
        this.f794d = context;
    }

    private int m935a(int i) {
        return (i * this.f795e) / this.f797g;
    }

    private int m936g(int i) {
        return (i * this.f796f) / this.f798h;
    }

    private boolean m937h() {
        return this.f792b != null;
    }

    public java.lang.String getSessionId() {
        return this.f793c;
    }

    public void setSessionId(java.lang.String str) {
        this.f793c = str;
    }

    public void configureEncryption(boolean z) {
        com.ucarhu.demo.protocol.crypto.AesGcmSessionCrypto c0084a;
        if (z) {
            com.ucarhu.demo.logging.EasyLogger.debug(f780i, "uibc encryption enabled");
            c0084a = new com.ucarhu.demo.protocol.crypto.AesGcmSessionCrypto(f781j);
        } else {
            com.ucarhu.demo.logging.EasyLogger.debug(f780i, "uibc encryption disabled");
            c0084a = null;
        }
        this.f792b = c0084a;
        this.f791a = true;
    }

    public boolean sendTouchEvent(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        if (iArr.length != i2 || iArr2.length != i2 || iArr3.length != i2) {
            com.ucarhu.demo.logging.EasyLogger.error(f780i, "sendTouchEvent failed: count error!");
            return false;
        }
        if (!this.f791a) {
            com.ucarhu.demo.logging.EasyLogger.error(f780i, "sendTouchEvent failed: encryption is not settled");
            return false;
        }
        if (this.f795e <= 0 || this.f796f <= 0) {
            com.ucarhu.demo.logging.EasyLogger.error(f780i, "sendTouchEvent failed: source size haven't been initialized!");
            return false;
        }
        com.ucarhu.demo.logging.EasyLogger.info(f780i, "sendTouchEvent" + i);
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("type", 1);
            jSONObject.put(f783l, i);
            jSONObject.put(f786o, this.f797g);
            jSONObject.put(f787p, this.f798h);
            jSONObject.put(f788q, i2);
            for (int i3 = 0; i3 < i2; i3++) {
                jSONObject.put("trackID" + i3, iArr[i3]);
                int iM935a = m935a(iArr2[i3]);
                int iM936g = m936g(iArr3[i3]);
                jSONObject.put("x" + i3, iM935a);
                jSONObject.put("y" + i3, iM936g);
                com.ucarhu.demo.logging.EasyLogger.info(f780i, "origin point: (" + iArr2[i3] + ", " + iArr3[i3] + "), display size: (" + this.f797g + ", " + this.f798h + "), source width: (" + this.f795e + ", " + this.f796f + "), mapped point: (" + iM935a + ", " + iM936g + ")");
            }
            byte[] bytes = jSONObject.toString().getBytes(java.nio.charset.StandardCharsets.UTF_8);
            if (m937h()) {
                bytes = this.f792b.encryptBytes(bytes);
            }
            return com.ucarsink.sink.natives.SinkNative.onUibcEvent(this.f793c, bytes);
        } catch (org.json.JSONException e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f780i, "sendTouchEvent JSONException", e2);
            return false;
        }
    }

    public boolean sendKeyEvent(com.ucar.vehiclesdk.UCarCommon.KeyEventActionType keyEventActionType, com.ucar.vehiclesdk.UCarCommon.KeyCodeType keyCodeType, int i) {
        if (keyEventActionType == null || keyCodeType == null || keyEventActionType == com.ucar.vehiclesdk.UCarCommon.KeyEventActionType.KEY_EVENT_ACTION_UNDEFINED || keyCodeType == com.ucar.vehiclesdk.UCarCommon.KeyCodeType.KEY_CODE_UNDEFINED) {
            com.ucarhu.demo.logging.EasyLogger.error(f780i, " sendKeyEvent() args error");
            return false;
        }
        if (!this.f791a) {
            com.ucarhu.demo.logging.EasyLogger.error(f780i, "sendTouchEvent failed: encryption is not settled");
            return false;
        }
        com.ucarhu.demo.logging.EasyLogger.info(f780i, "sendKeyEvent");
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("type", 2);
            jSONObject.put(f783l, keyEventActionType.getValue());
            jSONObject.put(f784m, keyCodeType.getValue());
            jSONObject.put(f785n, i);
            com.ucarhu.demo.logging.EasyLogger.info(f780i, "on key event: " + jSONObject);
            byte[] bytes = jSONObject.toString().getBytes(java.nio.charset.StandardCharsets.UTF_8);
            if (m937h()) {
                bytes = this.f792b.encryptBytes(bytes);
            }
            return com.ucarsink.sink.natives.SinkNative.onUibcEvent(this.f793c, bytes);
        } catch (org.json.JSONException e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f780i, "sendKeyEvent JSONException", e2);
            return false;
        }
    }

    public void setDisplayHeight(int i) {
        this.f798h = i;
    }

    public void setDisplayWidth(int i) {
        this.f797g = i;
    }

    public void setSourceHeight(int i) {
        this.f796f = i;
    }

    public void setSourceWidth(int i) {
        this.f795e = i;
    }
}
