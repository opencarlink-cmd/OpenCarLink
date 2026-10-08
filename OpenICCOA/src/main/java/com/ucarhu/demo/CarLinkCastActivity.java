package com.ucarhu.demo;

import android.bluetooth.BluetoothManager;
import android.provider.Settings;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import com.opencarlink.iccoa.R;
import com.ucar.sdk.BuildConfig;
import com.ucar.vehiclesdk.ICameraInfoListener;
import com.ucar.vehiclesdk.ICarConnectListener;
import com.ucar.vehiclesdk.ICarInitCallback;
import com.ucar.vehiclesdk.IPhoneDataListener;
import com.ucar.vehiclesdk.UCarAdapter;
import com.ucar.vehiclesdk.UCarCommon;
import com.ucar.vehiclesdk.UCarConfig;
import com.ucar.vehiclesdk.UCarSurfaceView;
import com.ucar.vehiclesdk.camera.AbstractCamera;
import com.ucarhu.demo.DialogOverlayFactory;
import com.ucarhu.demo.widget.FullScreenOverlay;
import com.ucarhu.demo.widget.ConnectionProgressOverlay;
import com.ucarhu.demo.widget.UCarPinCodeView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;

/**
 * CarLink 投屏主界面。
 *
 * 该 Activity 负责权限完成后的 SDK 初始化、设备发现、连接进度展示、PIN 码展示、
 * Surface 投屏承载以及生命周期内的音视频焦点处理。重构时保留原有业务调用顺序，
 * 只把反编译命名整理为可读名称。
 */
public class CarLinkCastActivity extends PermissionAwareActivity {

    /** UCar SDK 连接状态变化。 */
    public static final int MSG_CONNECT_STATE_CHANGED = 101;

    /** 开始广播发现并启用 USB 设备检测。 */
    public static final int MSG_START_ADVERTISE = 102;

    /** 停止广播发现并关闭 USB 设备检测。 */
    public static final int MSG_STOP_ADVERTISE = 103;

    /** 连接进度变化，用于刷新进度弹层。 */
    public static final int MSG_CONNECTING_PROGRESS = 104;

    /** 隐藏连接进度弹层。 */
    public static final int MSG_DISMISS_PROGRESS = 105;

    /** 收到手机端配对 PIN 码。 */
    public static final int MSG_PIN_CODE = 106;

    public static final int DEFAULT_DPI = 320;

    public static final int DEFAULT_FPS = 30;

    public static final int WINDOW_READY_DELAY_MS = 100;

    private static final int VIDEO_SIZE_ALIGNMENT = 16;

    private static final int CAR_ID_BYTE_LENGTH = 6;

    private static final int PREF_MODE_PRIVATE = 0;

    private static final int FULL_PROGRESS = 100;

    private static final String CAR_ID_PREFS_NAME = "car_id_prefs";

    private static final String CAR_ID_PREF_KEY = "car_id_key";

    private static final String PLACEHOLDER_BLUETOOTH_MAC = "02:00:00:00:00:00";

    private static final String DEFAULT_CAMERA_ID = "1";

    private static final String DEFAULT_CAMERA_NAME = "Car_Camera_1";

    /** SDK 初始化配置，窗口尺寸计算完成后创建。 */
    private UCarConfig carConfig;

    private ConnectionProgressOverlay progressOverlay;

    /** 当前是否正在展示连接进度，连接失败时用于先关闭进度再弹失败提示。 */
    private boolean progressOverlayShowing;

    private UCarPinCodeView pinCodeView;

    private TextView deviceNameView;

    private FrameLayout rootContainer;

    private int screenWidth;

    private int screenHeight;

    private int videoDisplayWidth;

    private int videoDisplayHeight;

    private int displayDpi;

    private ICarConnectListener carConnectListener;

    private IPhoneDataListener phoneDataListener;

    private ICameraInfoListener cameraInfoListener;

    private UCarSurfaceView castSurfaceView;

    private SurfaceHolder.Callback castSurfaceCallback;

    private FullScreenOverlay failureDialogOverlay;

    private static final String LOG_TAG = CarLinkCastActivity.class.getSimpleName();

    /** SDK 是否已初始化成功，避免窗口焦点变化时重复初始化。 */
    private static boolean sdkInitialized = false;

    /** 投屏通道是否已连接。 */
    private boolean castConnected = false;

    /** 当前连接周期内是否已经进入过投屏态，用于断开后的自动重连判断。 */
    private boolean castSessionStarted = false;

    /** 缓存上一帧手机状态，只在状态变化时打印日志并同步。 */
    private UCarCommon.PhoneStateInfo lastPhoneState = new UCarCommon.PhoneStateInfo();

    /** Activity 前后台状态，决定连接成功后是否立即恢复音频焦点。 */
    private boolean activityInForeground = false;

    private final Handler mainHandler = new MainEventHandler(Looper.getMainLooper());

    class CarConnectListenerImpl implements ICarConnectListener {
        CarConnectListenerImpl() {
        }

        @Override
        public void onConnectStateChanged(String str, int i, int i2) {
            Bundle bundle = new Bundle();
            bundle.putString("ID", str);
            bundle.putInt("STATE", i);
            bundle.putInt("PARAMETER", i2);
            Message messageObtain = Message.obtain();
            messageObtain.what = MSG_CONNECT_STATE_CHANGED;
            messageObtain.obj = bundle;
            CarLinkCastActivity.this.mainHandler.sendMessage(messageObtain);
        }

        @Override
        public void onConnectingProgress(String str, String str2, int i) {
            Log.i(CarLinkCastActivity.LOG_TAG, "onConnectingProgress: " + i);
            Bundle bundle = new Bundle();
            bundle.putString("ID", str);
            bundle.putString("NAME", str2);
            bundle.putInt("PROGRESS", i);
            Message messageObtain = Message.obtain();
            messageObtain.what = MSG_CONNECTING_PROGRESS;
            messageObtain.obj = bundle;
            CarLinkCastActivity.this.mainHandler.sendMessage(messageObtain);
        }

        @Override
        public void onPinCode(String str, String str2) {
            Log.i(CarLinkCastActivity.LOG_TAG, "onPinCode, pin code: " + str + ", device name: " + str2);
            Message messageObtain = Message.obtain();
            messageObtain.what = MSG_PIN_CODE;
            Bundle bundle = new Bundle();
            bundle.putString("PIN_CODE", str);
            bundle.putString("DEVICE_NAME", str2);
            messageObtain.obj = bundle;
            CarLinkCastActivity.this.mainHandler.sendMessage(messageObtain);
        }
    }

    class PhoneDataListenerImpl implements IPhoneDataListener {
        PhoneDataListenerImpl() {
        }

        @Override
        public UCarCommon.AudioAttributes getAudioAttributesByType(UCarCommon.AudioType audioType) {
            int i = 10;
            int i2 = 5;
            int i3 = 3;
            int i4 = 4;
            switch (AudioTypeMapping.AUDIO_TYPE_SWITCH_MAP[audioType.ordinal()]) {
                case 1:
                case 2:
                    i2 = 0;
                    i3 = 4;
                    i = 2;
                    i4 = 1;
                    break;
                case 3:
                    i2 = 10;
                    i3 = 2;
                    i4 = 1;
                    i = 16;
                    break;
                case 4:
                    i = 4;
                    i2 = 2;
                    i3 = 2;
                    break;
                case 5:
                    i = 5;
                    break;
                case 6:
                    i = 12;
                    i4 = 1;
                    break;
                case 7:
                    i2 = 1;
                    break;
                default:
                    i2 = 3;
                    i4 = 2;
                    i = 1;
                    i3 = 1;
                    break;
            }
            return new UCarCommon.AudioAttributes(new AudioAttributes.Builder().setUsage(i).setContentType(i4).build(), i3, i2);
        }

        @Override
        public void onMusicInfoReceived(String str, UCarCommon.MusicInfo musicInfo) {
            String str2;
            String str3;
            if (musicInfo.isPlaying()) {
                str2 = CarLinkCastActivity.LOG_TAG;
                str3 = "ucar music is playing";
            } else {
                str2 = CarLinkCastActivity.LOG_TAG;
                str3 = "ucar music is idle";
            }
            Log.i(str2, str3);
        }

        @Override
        public void onNavigationInfoReceived(String str, UCarCommon.NavigationInfo navigationInfo) {
            String str2;
            String str3;
            if (navigationInfo.isNavigating()) {
                str2 = CarLinkCastActivity.LOG_TAG;
                str3 = "ucar navigation is active";
            } else {
                str2 = CarLinkCastActivity.LOG_TAG;
                str3 = "ucar navigation is idle";
            }
            Log.i(str2, str3);
        }

        @Override
        public void onPhoneStateInfoReceived(String str, UCarCommon.PhoneStateInfo phoneStateInfo) {
            CarLinkCastActivity.this.postPhoneStateInfo(phoneStateInfo);
        }

        @Override
        public void onRequestAudioFocus(UCarCommon.AudioType audioType, int i) {
            Log.i(CarLinkCastActivity.LOG_TAG, "carlink need request audio focus");
        }
    }

    class CameraInfoListenerImpl implements ICameraInfoListener {
        CameraInfoListenerImpl() {
        }

        @Override
        public ArrayList<UCarCommon.CameraInfo> getAndroidCameraInfo() {
            ArrayList<UCarCommon.CameraInfo> arrayList = new ArrayList<>();
            arrayList.add(new UCarCommon.CameraInfo(CarLinkCastActivity.DEFAULT_CAMERA_ID, CarLinkCastActivity.DEFAULT_CAMERA_NAME, UCarCommon.LensFacing.LENS_FACING_BACK));
            return arrayList;
        }

        @Override
        public ArrayList<AbstractCamera> getNativeCamera() {
            return null;
        }
    }

    class MainEventHandler extends Handler {
        MainEventHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case MSG_CONNECT_STATE_CHANGED:
                    CarLinkCastActivity.this.handleConnectStateMessage(message);
                    break;
                case MSG_START_ADVERTISE:
                    UCarAdapter.getInstance().startAdvertise();
                    UCarAdapter.getInstance().enableUsbDeviceDetection(true);
                    break;
                case MSG_STOP_ADVERTISE:
                    UCarAdapter.getInstance().stopAdvertise();
                    UCarAdapter.getInstance().enableUsbDeviceDetection(false);
                    break;
                case MSG_CONNECTING_PROGRESS:
                    Bundle bundle = (Bundle) message.obj;
                    String string = bundle.getString("NAME");
                    int i = bundle.getInt("PROGRESS");
                    if (i != 0 && i != 100) {
                        CarLinkCastActivity.this.progressOverlayShowing = true;
                        CarLinkCastActivity.this.progressOverlay.updateProgress(string, i);
                        CarLinkCastActivity.this.progressOverlay.show();
                        if (CarLinkCastActivity.this.failureDialogOverlay != null && CarLinkCastActivity.this.failureDialogOverlay.isShowing()) {
                            CarLinkCastActivity.this.failureDialogOverlay.dismiss();
                            break;
                        }
                    } else {
                        CarLinkCastActivity.this.mainHandler.sendEmptyMessage(MSG_DISMISS_PROGRESS);
                        break;
                    }
                    break;
                case MSG_DISMISS_PROGRESS:
                    CarLinkCastActivity.this.progressOverlay.dismiss();
                    CarLinkCastActivity.this.progressOverlayShowing = false;
                    break;
                case MSG_PIN_CODE:
                    Bundle bundle2 = (Bundle) message.obj;
                    String string2 = bundle2.getString("PIN_CODE");
                    String string3 = bundle2.getString("DEVICE_NAME");
                    CarLinkCastActivity.this.pinCodeView.setPinCode(string2);
                    CarLinkCastActivity.this.deviceNameView.setText(string3);
                    break;
            }
        }
    }

    class CastSurfaceCallback implements SurfaceHolder.Callback {
        CastSurfaceCallback() {
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            Log.i(CarLinkCastActivity.LOG_TAG, "surfaceChanged, width:" + i2 + ",height:" + i3);
            if (!CarLinkCastActivity.this.startCastIfSurfaceReady(surfaceHolder)) {
                Log.i(CarLinkCastActivity.LOG_TAG, "SurfaceHolder or Surface is null or invalid");
                UCarAdapter.getInstance().pauseCast();
            }
        }

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            Log.d(CarLinkCastActivity.LOG_TAG, "surfaceCreated");
            surfaceHolder.setSizeFromLayout();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            Log.d(CarLinkCastActivity.LOG_TAG, "surfaceDestroyed");
            UCarAdapter.getInstance().pauseCast();
        }
    }

    class CarSdkInitCallback implements ICarInitCallback {
        CarSdkInitCallback() {
        }

        public void handleInitFailureCode(int i) {
            CarLinkCastActivity.this.showConnectionFailureDialog(i);
        }

        @Override
        public void onInitFailed(final int i) {
            Log.e(CarLinkCastActivity.LOG_TAG, "UCarAdapter init failed");
            CarLinkCastActivity.this.mainHandler.post(new Runnable() {
                @Override
                public final void run() {
                    CarSdkInitCallback.this.handleInitFailureCode(i);
                }
            });
        }

        @Override
        public void onInitSuccess() {
            Log.i(CarLinkCastActivity.LOG_TAG, "UCarAdapter init success");
            boolean unused = CarLinkCastActivity.sdkInitialized = true;
            CarLinkCastActivity.this.registerUCarListeners();
            CarLinkCastActivity.this.mainHandler.sendEmptyMessage(MSG_START_ADVERTISE);
        }
    }

    /**
     * 反编译代码为 switch(enum) 生成的映射表。这里保留数组写法，避免改变原有分支结果。
     */
    static class AudioTypeMapping {

        static final int[] AUDIO_TYPE_SWITCH_MAP;

        static {
            int[] iArr = new int[UCarCommon.AudioType.values().length];
            AUDIO_TYPE_SWITCH_MAP = iArr;
            try {
                iArr[UCarCommon.AudioType.STREAM_IP_CALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                AUDIO_TYPE_SWITCH_MAP[UCarCommon.AudioType.STREAM_MODEM_CALL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                AUDIO_TYPE_SWITCH_MAP[UCarCommon.AudioType.STREAM_AI_ASSISTANT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                AUDIO_TYPE_SWITCH_MAP[UCarCommon.AudioType.STREAM_RING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                AUDIO_TYPE_SWITCH_MAP[UCarCommon.AudioType.STREAM_NOTIFICATION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                AUDIO_TYPE_SWITCH_MAP[UCarCommon.AudioType.STREAM_TTS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                AUDIO_TYPE_SWITCH_MAP[UCarCommon.AudioType.STREAM_SYSTEM.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                AUDIO_TYPE_SWITCH_MAP[UCarCommon.AudioType.STREAM_CAST_MUSIC.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public void initializeCarSdkAfterWindowReady() {
        if (sdkInitialized) {
            return;
        }
        calculateDisplayMetrics();
        byte[] carIdBytes = getOrCreateCarId();
        String str = LOG_TAG;
        Log.d(str, "carId = " + Arrays.toString(carIdBytes));
        this.carConfig = new UCarConfig.Builder().setCarBrMac(carIdBytes).setDpi(this.displayDpi).setFps(DEFAULT_FPS).setScreenWidth(this.screenWidth).setScreenHeight(this.screenHeight).setVideoDisplayWidth(this.videoDisplayWidth).setVideoDisplayHeight(this.videoDisplayHeight).setSupportP2P(true).setSupportCamera(false).setSupportSoftAP(false).setSupportLowLatencyDecodingMode(false).setSupportStereoRecord(true).build();
        if (UCarAdapter.getInstance().init(getApplicationContext(), this.carConfig, new CarSdkInitCallback())) {
            return;
        }
        Log.e(str, "init ucar sdk failed");
        finish();
    }

    public boolean requestReconnectFromDialog() {
        this.mainHandler.sendEmptyMessage(MSG_START_ADVERTISE);
        return false;
    }

    public boolean cancelConnectAndFinish() {
        finish();
        return false;
    }

    public void handlePhoneStateInfo(UCarCommon.PhoneStateInfo phoneStateInfo) {
        String str = LOG_TAG;
        String str2 = null;
        String str3;
        String str4;
        boolean zIsUseMicrophone = phoneStateInfo.isUseMicrophone();
        if (zIsUseMicrophone != this.lastPhoneState.isUseMicrophone()) {
            if (phoneStateInfo.isUseMicrophone()) {
                str3 = LOG_TAG;
                str4 = "ucar request use mic";
            } else {
                str3 = LOG_TAG;
                str4 = "ucar has released mic";
            }
            Log.i(str3, str4);
            this.lastPhoneState.setUseMicrophone(zIsUseMicrophone);
        }
        UCarCommon.ModemCallState modemCallState = phoneStateInfo.getModemCallState();
        if (modemCallState != this.lastPhoneState.getModemCallState()) {
            if (modemCallState == UCarCommon.ModemCallState.RINGING || modemCallState == UCarCommon.ModemCallState.OFFHOOK) {
                str = LOG_TAG;
                str2 = "modem call state changed : " + modemCallState;
            } else {
                if (modemCallState == UCarCommon.ModemCallState.IDLE) {
                    str = LOG_TAG;
                    str2 = "modem call state is idle";
                }
                this.lastPhoneState.setModemCallState(modemCallState);
            }
            if (str2 != null) {
                Log.i(str, str2);
            }
            this.lastPhoneState.setModemCallState(modemCallState);
        }
        boolean zIsVoipCall = phoneStateInfo.isVoipCall();
        if (zIsVoipCall != this.lastPhoneState.isVoipCall()) {
            Log.i(LOG_TAG, zIsVoipCall ? "voip call state is active" : "voip call state is idle");
            this.lastPhoneState.setVoipCall(zIsVoipCall);
        }
        boolean zIsVoiceAssistantActive = phoneStateInfo.isVoiceAssistantActive();
        if (zIsVoiceAssistantActive != this.lastPhoneState.isVoiceAssistantActive()) {
            Log.i(LOG_TAG, zIsVoiceAssistantActive ? "voice assistant state is active" : "voice assistant state is idle");
            this.lastPhoneState.setVoiceAssistantActive(zIsVoiceAssistantActive);
        }
    }

    static void pauseCastInBackground() {
        Log.i(LOG_TAG, "pause cast");
        UCarAdapter.getInstance().pauseCast();
    }

    public void onExitButtonClicked(View view) {
        this.progressOverlay.dismiss();
        finish();
    }

    public void registerUCarListeners() {
        this.carConnectListener = new CarConnectListenerImpl();
        UCarAdapter.getInstance().registerCarConnectListener(this.carConnectListener);
        this.phoneDataListener = new PhoneDataListenerImpl();
        UCarAdapter.getInstance().registerPhoneDataListener(this.phoneDataListener);
        if (this.carConfig.isSupportCamera()) {
            this.cameraInfoListener = new CameraInfoListenerImpl();
            UCarAdapter.getInstance().registerCameraInfoListener(this.cameraInfoListener);
        }
    }

    private void applyCastSurfaceSize() {
        this.castSurfaceView.setLayoutParams(new RelativeLayout.LayoutParams(this.videoDisplayWidth, this.videoDisplayHeight));
    }

    private void bindConnectViews() {
        findViewById(R.id.tv_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                CarLinkCastActivity.this.onExitButtonClicked(view);
            }
        });
        this.deviceNameView = (TextView) findViewById(R.id.tv_device_name);
        this.pinCodeView = (UCarPinCodeView) findViewById(R.id.upc_pin_code);
    }

    private void ensureCastSurfaceReady() {
        if (this.castSurfaceCallback != null) {
            if (this.castSurfaceView != null) {
                startCastIfSurfaceReady(this.castSurfaceView.getHolder());
            }
            return;
        }
        String str = LOG_TAG;
        Log.d(str, "create mSurfaceHolderCallback!!");
        this.castSurfaceCallback = new CastSurfaceCallback();
        Log.d(str, "get mSurfaceView!!");
        UCarSurfaceView uCarSurfaceView = (UCarSurfaceView) findViewById(R.id.surface_view);
        this.castSurfaceView = uCarSurfaceView;
        if (uCarSurfaceView == null || uCarSurfaceView.getHolder() == null) {
            return;
        }
        Log.d(str, "add surface holder callback!!");
        applyCastSurfaceSize();
        this.castSurfaceView.getHolder().addCallback(this.castSurfaceCallback);
        startCastIfSurfaceReady(this.castSurfaceView.getHolder());
    }

    private boolean startCastIfSurfaceReady(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            return false;
        }
        Surface surface = surfaceHolder.getSurface();
        if (surface == null || !surface.isValid() || this.castSurfaceView == null) {
            return false;
        }
        int width = this.castSurfaceView.getWidth();
        int height = this.castSurfaceView.getHeight();
        if (width <= 0 || height <= 0) {
            Log.i(LOG_TAG, "cast surface size is not ready, wait for surfaceChanged");
            return false;
        }
        return UCarAdapter.getInstance().startCast(surface, width, height);
    }

    private void setCastSurfaceVisible(boolean z) {
        Log.d(LOG_TAG, "setCastSurfaceVisibility : " + z);
        View viewFindViewById = findViewById(R.id.cl_ucar_connect_container);
        View viewFindViewById2 = findViewById(R.id.rl_cast_player);
        if (z) {
            viewFindViewById2.setVisibility(0);
            viewFindViewById.setVisibility(8);
            viewFindViewById2.post(new Runnable() {
                @Override
                public void run() {
                    CarLinkCastActivity.this.ensureCastSurfaceReady();
                }
            });
        } else {
            releaseCastSurface();
            viewFindViewById.setVisibility(0);
            viewFindViewById2.setVisibility(8);
        }
    }

    private void renderSdkVersion() {
        ((TextView) findViewById(R.id.tv_sdk_version)).setText(BuildConfig.SDK_VERSION);
    }

    private void unregisterUCarListeners() {
        UCarAdapter.getInstance().unregisterCarConnectListener(this.carConnectListener);
        UCarAdapter.getInstance().unregisterPhoneDataListener(this.phoneDataListener);
        UCarAdapter.getInstance().unregisterCameraInfoListener(this.cameraInfoListener);
    }

    private void goHomePage() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        startActivity(intent);
    }

    private byte[] getOrCreateCarId() {
        SharedPreferences sharedPreferences = getSharedPreferences(CAR_ID_PREFS_NAME, PREF_MODE_PRIVATE);
        String string = sharedPreferences.getString(CAR_ID_PREF_KEY, "");
        byte[] hardwareCarId = readHardwareBluetoothAddressBytes();
        if (hardwareCarId != null && shouldReplaceCachedCarId(string, hardwareCarId)) {
            string = Base64.getEncoder().encodeToString(hardwareCarId);
            sharedPreferences.edit().putString(CAR_ID_PREF_KEY, string).apply();
            Log.d(LOG_TAG, "use hardware car id = " + Arrays.toString(hardwareCarId));
        } else if (TextUtils.isEmpty(string) || isPlaceholderCarId(string)) {
            byte[] newCarIdBytes = createCarIdFromBluetoothMac();
            string = Base64.getEncoder().encodeToString(newCarIdBytes);
            sharedPreferences.edit().putString(CAR_ID_PREF_KEY, string).apply();
            Log.d(LOG_TAG, "raw id = " + Arrays.toString(newCarIdBytes));
        }
        Log.d(LOG_TAG, "id = " + string);
        return Base64.getDecoder().decode(string);
    }

    private boolean shouldReplaceCachedCarId(String encodedCarId, byte[] hardwareCarId) {
        if (TextUtils.isEmpty(encodedCarId) || isPlaceholderCarId(encodedCarId)) {
            return true;
        }
        try {
            return !Arrays.equals(Base64.getDecoder().decode(encodedCarId), hardwareCarId);
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    private boolean isPlaceholderCarId(String encodedCarId) {
        try {
            byte[] carId = Base64.getDecoder().decode(encodedCarId);
            return carId.length == CAR_ID_BYTE_LENGTH
                    && carId[0] == 0x02
                    && carId[1] == 0
                    && carId[2] == 0
                    && carId[3] == 0
                    && carId[4] == 0
                    && carId[5] == 0;
        } catch (IllegalArgumentException e) {
            Log.w(LOG_TAG, "cached car id is invalid, recreate it", e);
            return true;
        }
    }

    public void showConnectionFailureDialog(int i) {
        StringBuilder sb;
        int i2;
        String str = LOG_TAG;
        Log.i(str, "ucar connect failed, error code:" + i);
        FullScreenOverlay overlay = this.failureDialogOverlay;
        if (overlay != null) {
            overlay.show();
            return;
        }
        String string = getString(R.string.connect_failed);
        if (i == 10009) {
            sb = new StringBuilder();
            sb.append("\n");
            i2 = R.string.auth_failed;
        } else {
            sb = new StringBuilder();
            sb.append("\n");
            sb.append(getString(R.string.wireless_reconnect_method));
            sb.append("\n");
            sb.append(getString(R.string.usb_reconnect_method));
            sb.append("\n");
            sb.append(getString(R.string.need_user_intervention_method));
            sb.append("\n");
            sb.append(getString(R.string.unsupport_wireless_method));
            sb.append("\n");
            i2 = R.string.restart_car_method;
        }
        sb.append(getString(i2));
        sb.append("\n");
        String string2 = sb.toString();
        Log.i(str, "error message:" + string2);
        if (this.progressOverlayShowing) {
            this.mainHandler.sendEmptyMessage(MSG_DISMISS_PROGRESS);
        }
        FullScreenOverlay failureOverlay = DialogOverlayFactory.createConfirmCancelDialog(this, R.layout.dialog_connect_failed_notice, 0, string, string2, getString(R.string.reconnect), getString(R.string.cancel), new DialogOverlayFactory.DialogActionCallback() {
            @Override
            public final boolean onDialogAction() {
                return CarLinkCastActivity.this.requestReconnectFromDialog();
            }
        }, new DialogOverlayFactory.DialogActionCallback() {
            @Override
            public final boolean onDialogAction() {
                return CarLinkCastActivity.this.cancelConnectAndFinish();
            }
        });
        this.failureDialogOverlay = failureOverlay;
        failureOverlay.show();
    }

    public void handleConnectStateMessage(Message message) {
        Bundle bundle = (Bundle) message.obj;
        String string = bundle.getString("ID");
        int i = bundle.getInt("STATE");
        int i2 = bundle.getInt("PARAMETER");
        switch (i) {
            case 1:
                boolean z = i2 == 11;
                Log.i(LOG_TAG, "ucar device disconnected, is disconnect by user : " + z);
                this.castConnected = false;
                handleDeviceDisconnected(string, z);
                break;
            case 2:
                Log.i(LOG_TAG, "ucar device connected");
                if (!this.activityInForeground) {
                    UCarAdapter.getInstance().pauseCast();
                } else {
                    UCarAdapter.getInstance().allowGainAudioFocus();
                }
                break;
            case 3:
                showConnectionFailureDialog(i2);
                break;
            case 4:
                Log.d(LOG_TAG, "cast connected");
                this.castConnected = true;
                this.castSessionStarted = true;
                setCastSurfaceVisible(true);
                this.mainHandler.sendEmptyMessage(MSG_DISMISS_PROGRESS);
                break;
            case 5:
                Log.d(LOG_TAG, "cast disconnected");
                this.castConnected = false;
                setCastSurfaceVisible(false);
                break;
            case 6:
                goHomePage();
                break;
        }
    }

    private void handleDeviceDisconnected(String str, boolean z) {
        if (this.castSessionStarted) {
            this.castSessionStarted = false;
            int connectType = UCarAdapter.getInstance().getConnectType(str);
            setCastSurfaceVisible(false);
            boolean z2 = connectType == 1 || connectType == 3;
            if (z || !z2) {
                Log.i(LOG_TAG, "move the activity to the end of its task stack");
                moveTaskToBack(true);
            } else {
                Log.i(LOG_TAG, "prepare for auto reconnection");
                this.mainHandler.sendEmptyMessage(MSG_START_ADVERTISE);
            }
        }
        if (this.progressOverlayShowing) {
            this.mainHandler.sendEmptyMessage(MSG_DISMISS_PROGRESS);
        }
    }

    public void postPhoneStateInfo(final UCarCommon.PhoneStateInfo phoneStateInfo) {
        this.mainHandler.post(new Runnable() {
            @Override
            public final void run() {
                CarLinkCastActivity.this.handlePhoneStateInfo(phoneStateInfo);
            }
        });
    }

    private void enterImmersiveMode() {
        getWindow().getDecorView().setSystemUiVisibility(4102);
    }

    private void calculateDisplayMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        String str = LOG_TAG;
        Log.d(str, "device dpi = " + displayMetrics.densityDpi);
        this.displayDpi = DEFAULT_DPI;
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        this.screenWidth = Math.max(rect.width(), rect.height());
        this.screenHeight = Math.min(rect.width(), rect.height());
        Log.d(str, "display frame width = " + this.screenWidth + ", height = " + this.screenHeight);
        this.screenWidth = (this.screenWidth / VIDEO_SIZE_ALIGNMENT) * VIDEO_SIZE_ALIGNMENT;
        this.screenHeight = (this.screenHeight / VIDEO_SIZE_ALIGNMENT) * VIDEO_SIZE_ALIGNMENT;
        Log.d(str, "aligned width = " + this.screenWidth + ", height = " + this.screenHeight);
        this.videoDisplayWidth = this.screenWidth;
        this.videoDisplayHeight = this.screenHeight;
        Log.d(str, "use metrics: width = " + this.videoDisplayWidth + ", height = " + this.videoDisplayHeight + ", dpi = " + this.displayDpi);
    }

    void releaseCastSurface() {
        UCarSurfaceView uCarSurfaceView = this.castSurfaceView;
        if (uCarSurfaceView == null || uCarSurfaceView.getHolder() == null) {
            return;
        }
        Log.i(LOG_TAG, "remove surface holder callback");
        this.castSurfaceView.getHolder().removeCallback(this.castSurfaceCallback);
        this.castSurfaceCallback = null;
        this.castSurfaceView = null;
    }

    @Override
    protected String[] requiredPermissions() {
        return new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO", "android.permission.INTERNET"};
    }

    @Override
    protected void onRequiredPermissionsDenied() {
        finish();
    }

    @Override
    protected void onRequiredPermissionsGranted() {
        bindConnectViews();
    }

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Log.i(LOG_TAG, "onCreate");
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_ucar_cast);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fl_root);
        this.rootContainer = frameLayout;
        this.progressOverlay = new ConnectionProgressOverlay(this, frameLayout);
        renderSdkVersion();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String str = LOG_TAG;
        Log.d(str, "onDestroy");
        FullScreenOverlay overlay = this.failureDialogOverlay;
        if (overlay != null && overlay.isShowing()) {
            this.failureDialogOverlay.dismiss();
        }
        unregisterUCarListeners();
        UCarAdapter.getInstance().deInit();
        Log.d(str, "exit process");
        System.exit(0);
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Log.i(LOG_TAG, "onKeyDown: " + keyEvent.toString());
        UCarAdapter.getInstance().sendKeyEvent(UCarCommon.KeyEventActionType.KEY_EVENT_ACTION_DOWN, UCarCommon.KeyCodeType.fromInt(keyEvent.getKeyCode()), keyEvent.getMetaState());
        return super.onKeyDown(i, keyEvent);
    }

    @Override
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        Log.i(LOG_TAG, "onKeyUp: " + keyEvent.toString() + ", event action: " + keyEvent.getAction() + ". event key code: " + keyEvent.getKeyCode());
        UCarAdapter.getInstance().sendKeyEvent(UCarCommon.KeyEventActionType.KEY_EVENT_ACTION_UP, UCarCommon.KeyCodeType.fromInt(keyEvent.getKeyCode()), keyEvent.getMetaState());
        return super.onKeyUp(i, keyEvent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(LOG_TAG, "onNewIntent");
    }

    @Override
    protected void onResume() {
        super.onResume();
        String str = LOG_TAG;
        Log.i(str, "onResume");
        this.activityInForeground = true;
        enterImmersiveMode();
        if (sdkInitialized && !this.castConnected) {
            Log.d(str, "start advertise and scan device");
            this.mainHandler.sendEmptyMessage(MSG_START_ADVERTISE);
        } else if (this.castConnected) {
            ensureCastSurfaceReady();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        String str = LOG_TAG;
        Log.i(str, "onStop");
        this.activityInForeground = false;
        if (this.castConnected) {
            new Thread(new Runnable() {
                @Override
                public final void run() {
                    CarLinkCastActivity.pauseCastInBackground();
                }
            }).start();
            releaseCastSurface();
        } else {
            // 未建立投屏前继续保持 BLE 广播，避免车机界面被系统弹层短暂遮挡后手机端扫不到 PIN 弹窗。
            Log.d(str, "keep advertise while waiting for phone connection");
        }
    }

    @Override
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        Log.d(LOG_TAG, "onWindowFocusChanged: " + z);
        if (z) {
            this.mainHandler.postDelayed(new Runnable() {
                @Override
                public final void run() {
                    CarLinkCastActivity.this.initializeCarSdkAfterWindowReady();
                }
            }, WINDOW_READY_DELAY_MS);
            if (sdkInitialized) {
                UCarAdapter.getInstance().allowGainAudioFocus();
            }
        }
    }

    public byte[] createCarIdFromBluetoothMac() {
        byte[] bArr = new byte[CAR_ID_BYTE_LENGTH];
        String address = resolveBluetoothAddress();
        String str = LOG_TAG;
        Log.i(str, "car mac:" + address + ", is connected before:" + UCarAdapter.existedCarBluetoothMac(address, getApplicationContext()));
        if (!TextUtils.isEmpty(address) && !PLACEHOLDER_BLUETOOTH_MAC.equals(address)) {
            String[] strArrSplit = address.split(":");
            for (int i = 0; i < strArrSplit.length; i++) {
                bArr[i] = (byte) Integer.parseInt(strArrSplit[i], 16);
            }
        } else {
            Log.e(str, "Temporarily use random numbers as IDs, only for testing");
            new Random().nextBytes(bArr);
        }
        return bArr;
    }

    private byte[] readHardwareBluetoothAddressBytes() {
        String address = resolveBluetoothAddress();
        if (TextUtils.isEmpty(address) || PLACEHOLDER_BLUETOOTH_MAC.equals(address)) {
            return null;
        }
        String[] parts = address.split(":");
        if (parts.length != CAR_ID_BYTE_LENGTH) {
            return null;
        }
        byte[] addressBytes = new byte[CAR_ID_BYTE_LENGTH];
        try {
            for (int i = 0; i < parts.length; i++) {
                addressBytes[i] = (byte) Integer.parseInt(parts[i], 16);
            }
            return addressBytes;
        } catch (NumberFormatException e) {
            Log.w(LOG_TAG, "invalid bluetooth address: " + address, e);
            return null;
        }
    }

    private String resolveBluetoothAddress() {
        String address = ((BluetoothManager) getSystemService("bluetooth")).getAdapter().getAddress();
        if (isValidBluetoothAddress(address)) {
            return address;
        }
        address = Settings.Secure.getString(getContentResolver(), "bluetooth_address");
        if (isValidBluetoothAddress(address)) {
            return address;
        }
        address = formatBluetoothAddress(readSystemProperty("persist.sys.external.DevicesAddr"));
        if (isValidBluetoothAddress(address)) {
            return address;
        }
        return null;
    }

    private boolean isValidBluetoothAddress(String address) {
        return !TextUtils.isEmpty(address) && !PLACEHOLDER_BLUETOOTH_MAC.equals(address) && address.split(":").length == CAR_ID_BYTE_LENGTH;
    }

    private String formatBluetoothAddress(String rawAddress) {
        if (TextUtils.isEmpty(rawAddress)) {
            return null;
        }
        String hex = rawAddress.replace(":", "").trim();
        if (hex.length() != 12) {
            return rawAddress;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            if (builder.length() > 0) {
                builder.append(':');
            }
            builder.append(hex, i, i + 2);
        }
        return builder.toString();
    }

    private String readSystemProperty(String key) {
        try {
            Class<?> systemProperties = Class.forName("android.os.SystemProperties");
            Object value = systemProperties.getMethod("get", String.class).invoke(null, key);
            return value instanceof String ? (String) value : null;
        } catch (Exception e) {
            Log.w(LOG_TAG, "read system property failed: " + key, e);
            return null;
        }
    }
}
