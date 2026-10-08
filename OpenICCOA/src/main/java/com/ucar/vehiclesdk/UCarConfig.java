package com.ucar.vehiclesdk;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import java.security.InvalidParameterException;
import java.util.Arrays;
import com.ucarhu.demo.logging.EasyLogger;

public class UCarConfig implements Parcelable {
    private static final int BR_MAC_SIZE = 6;
    private static final int CAR_CUSTOM_FIELD_SIZE = 2;
    public static final Parcelable.Creator<UCarConfig> CREATOR = new Parcelable.Creator<UCarConfig>() {
        @Override
        public UCarConfig createFromParcel(Parcel parcel) {
            return new UCarConfig(parcel);
        }

        @Override
        public UCarConfig[] newArray(int i) {
            return new UCarConfig[i];
        }
    };
    private static final int DEFAULT_DPI = 320;
    private static final int DEFAULT_FPS = 30;
    private static final int MAX_5G_CHANNEL = 165;
    private static final int MAX_DPI = 640;
    private static final int MAX_FPS = 60;
    private static final int MIN_5G_CHANNEL = 36;
    private static final int MIN_DPI = 120;
    private static final int MIN_FPS = 15;
    private static final String TAG = "UCarConfig";
    private byte[] mCarBrMac;
    private byte[] mCarCustomField;
    private String mCcdFilePath;
    private int mDefault5gChannel;
    private int mDpi;
    private int mFps;
    private boolean mIsDataTransMode;
    private boolean mIsSupportCamera;
    private boolean mIsSupportLowLatencyDecodingMode;
    private boolean mIsSupportMic;
    private boolean mIsSupportP2P;
    private boolean mIsSupportRealWifiAddress;
    private boolean mIsSupportSoftAP;
    private boolean mIsSupportStereoRecord;
    private boolean mIsSupportVoiceWaken;
    private int mScreenHeight;
    private int mScreenWidth;
    private int mVideoDisplayHeight;
    private int mVideoDisplayWidth;

    public static class Builder {

        private int f9539b;

        private int f9540c;

        private int f9542e;

        private int f9543f;

        private String f9556s;

        private byte[] f9538a = new byte[6];

        private int f9541d = 320;

        private int f9544g = 30;

        private boolean f9545h = true;

        private boolean f9546i = false;

        private boolean f9547j = false;

        private int f9548k = 36;

        private boolean f9549l = false;

        private boolean f9550m = true;

        private boolean f9551n = true;

        private boolean f9552o = true;

        private boolean f9553p = true;

        private byte[] f9554q = new byte[2];

        private boolean f9555r = true;

        public UCarConfig build() {
            return new UCarConfig(this);
        }

        public Builder setCarBrMac(byte[] bArr) {
            if (bArr == null || bArr.length != 6) {
                EasyLogger.error(UCarConfig.TAG, "Car Br Mac is invalid, the length must be 6 size!");
                throw new InvalidParameterException("Car Br Mac is invalid");
            }
            System.arraycopy(bArr, 0, this.f9538a, 0, 6);
            return this;
        }

        public Builder setCarCustomField(byte[] bArr) {
            if (bArr == null || bArr.length != 2) {
                EasyLogger.error(UCarConfig.TAG, "Car custom filed is invalid, the length must be 2 size!");
                throw new InvalidParameterException("Car custom filed is invalid");
            }
            System.arraycopy(bArr, 0, this.f9554q, 0, 2);
            return this;
        }

        public Builder setCcdFilePath(String str) {
            this.f9556s = str;
            return this;
        }

        public Builder setDataTransMode(boolean z) {
            this.f9547j = this.f9547j;
            return this;
        }

        public Builder setDefault5gChannel(int i) {
            if (i < 36 || i > UCarConfig.MAX_5G_CHANNEL) {
                throw new InvalidParameterException("5G channel must be between 36 and 165");
            }
            this.f9548k = i;
            return this;
        }

        public Builder setDpi(int i) {
            if (i < 120 || i > UCarConfig.MAX_DPI) {
                throw new InvalidParameterException("dpi must be between 120 and 640");
            }
            this.f9541d = i;
            return this;
        }

        public Builder setFps(int i) {
            if (i < 15 || i > 60) {
                throw new InvalidParameterException("fps must be between 15 and 60");
            }
            this.f9544g = i;
            return this;
        }

        public Builder setScreenHeight(int i) {
            if (i < 0) {
                throw new InvalidParameterException("screen height cannot be negative!");
            }
            this.f9540c = i;
            return this;
        }

        public Builder setScreenWidth(int i) {
            if (i < 0) {
                throw new InvalidParameterException("screen width cannot be negative!");
            }
            this.f9539b = i;
            return this;
        }

        public Builder setSupportCamera(boolean z) {
            this.f9549l = z;
            return this;
        }

        public Builder setSupportLowLatencyDecodingMode(boolean z) {
            this.f9551n = z;
            return this;
        }

        public Builder setSupportMic(boolean z) {
            this.f9550m = z;
            return this;
        }

        public Builder setSupportP2P(boolean z) {
            this.f9545h = z;
            return this;
        }

        public Builder setSupportRealWifiAddress(boolean z) {
            this.f9553p = z;
            return this;
        }

        public Builder setSupportSoftAP(boolean z) {
            this.f9546i = z;
            return this;
        }

        public Builder setSupportStereoRecord(boolean z) {
            this.f9555r = z;
            return this;
        }

        public Builder setSupportVoiceWaken(boolean z) {
            this.f9552o = z;
            return this;
        }

        public Builder setVideoDisplayHeight(int i) {
            if (i < 0) {
                throw new InvalidParameterException("video display height cannot be negative!");
            }
            this.f9543f = i;
            return this;
        }

        public Builder setVideoDisplayWidth(int i) {
            if (i < 0) {
                throw new InvalidParameterException("video display height cannot be negative!");
            }
            this.f9542e = i;
            return this;
        }
    }

    private UCarConfig() {
        this.mCarBrMac = new byte[6];
        this.mCarCustomField = new byte[2];
    }

    private UCarConfig(Parcel parcel) {
        this.mCarBrMac = new byte[6];
        this.mCarCustomField = new byte[2];
        readFromParcel(parcel);
    }

    private UCarConfig(Builder builder) {
        this.mCarBrMac = new byte[6];
        this.mCarCustomField = new byte[2];
        if (builder.f9538a == null) {
            throw new InvalidParameterException("Car Br Mac is invalid");
        }
        this.mCarBrMac = Arrays.copyOf(builder.f9538a, builder.f9538a.length);
        this.mScreenWidth = builder.f9539b;
        this.mScreenHeight = builder.f9540c;
        this.mDpi = builder.f9541d;
        this.mVideoDisplayWidth = builder.f9542e;
        this.mVideoDisplayHeight = builder.f9543f;
        this.mFps = builder.f9544g;
        this.mIsSupportP2P = builder.f9545h;
        this.mIsSupportSoftAP = builder.f9546i;
        this.mIsDataTransMode = builder.f9547j;
        this.mDefault5gChannel = builder.f9548k;
        this.mIsSupportCamera = builder.f9549l;
        this.mIsSupportMic = builder.f9550m;
        this.mIsSupportLowLatencyDecodingMode = builder.f9551n;
        this.mIsSupportVoiceWaken = builder.f9552o;
        this.mIsSupportRealWifiAddress = builder.f9553p;
        if (builder.f9554q != null) {
            this.mCarCustomField = Arrays.copyOf(builder.f9554q, builder.f9554q.length);
        }
        this.mIsSupportStereoRecord = builder.f9555r;
        this.mCcdFilePath = builder.f9556s;
    }

    public UCarConfig(UCarConfig uCarConfig) {
        this.mCarBrMac = new byte[6];
        this.mCarCustomField = new byte[2];
        byte[] bArr = uCarConfig.mCarBrMac;
        if (bArr != null) {
            this.mCarBrMac = Arrays.copyOf(bArr, 6);
        }
        this.mScreenWidth = uCarConfig.mScreenWidth;
        this.mScreenHeight = uCarConfig.mScreenHeight;
        this.mDpi = uCarConfig.mDpi;
        this.mVideoDisplayWidth = uCarConfig.mVideoDisplayWidth;
        this.mVideoDisplayHeight = uCarConfig.mVideoDisplayHeight;
        this.mFps = uCarConfig.mFps;
        this.mIsSupportP2P = uCarConfig.mIsSupportP2P;
        this.mIsSupportSoftAP = uCarConfig.mIsSupportSoftAP;
        this.mIsDataTransMode = uCarConfig.mIsDataTransMode;
        this.mDefault5gChannel = uCarConfig.mDefault5gChannel;
        this.mIsSupportCamera = uCarConfig.mIsSupportCamera;
        this.mIsSupportMic = uCarConfig.mIsSupportMic;
        this.mIsSupportLowLatencyDecodingMode = uCarConfig.mIsSupportLowLatencyDecodingMode;
        this.mIsSupportVoiceWaken = uCarConfig.mIsSupportVoiceWaken;
        this.mIsSupportRealWifiAddress = uCarConfig.mIsSupportRealWifiAddress;
        byte[] bArr2 = uCarConfig.mCarCustomField;
        if (bArr2 != null) {
            this.mCarCustomField = Arrays.copyOf(bArr2, 2);
        }
        this.mIsSupportStereoRecord = uCarConfig.mIsSupportStereoRecord;
        this.mCcdFilePath = uCarConfig.mCcdFilePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public byte[] getCarBrMac() {
        return this.mCarBrMac;
    }

    public byte[] getCarCustomField() {
        return this.mCarCustomField;
    }

    public String getCcdFilePath() {
        return this.mCcdFilePath;
    }

    public int getDefault5gChannel() {
        return this.mDefault5gChannel;
    }

    public int getDpi() {
        return this.mDpi;
    }

    public int getFps() {
        return this.mFps;
    }

    public int getScreenHeight() {
        return this.mScreenHeight;
    }

    public int getScreenWidth() {
        return this.mScreenWidth;
    }

    public int getVideoDisplayHeight() {
        return this.mVideoDisplayHeight;
    }

    public int getVideoDisplayWidth() {
        return this.mVideoDisplayWidth;
    }

    public boolean isDataTransMode() {
        return this.mIsDataTransMode;
    }

    public boolean isSupportCamera() {
        return this.mIsSupportCamera;
    }

    public boolean isSupportLowLatencyDecodingMode() {
        return this.mIsSupportLowLatencyDecodingMode;
    }

    public boolean isSupportMic() {
        return this.mIsSupportMic;
    }

    public boolean isSupportP2P() {
        return this.mIsSupportP2P;
    }

    public boolean isSupportRealWifiAddress() {
        return this.mIsSupportRealWifiAddress;
    }

    public boolean isSupportSoftAP() {
        return this.mIsSupportSoftAP;
    }

    public boolean isSupportStereoRecord() {
        return this.mIsSupportStereoRecord;
    }

    public boolean isSupportVoiceWaken() {
        return this.mIsSupportVoiceWaken;
    }

    public void readFromParcel(Parcel parcel) {
        parcel.readByteArray(this.mCarBrMac);
        this.mScreenWidth = parcel.readInt();
        this.mScreenHeight = parcel.readInt();
        this.mDpi = parcel.readInt();
        this.mVideoDisplayWidth = parcel.readInt();
        this.mVideoDisplayHeight = parcel.readInt();
        this.mFps = parcel.readInt();
        this.mIsSupportP2P = parcel.readInt() == 1;
        this.mIsSupportSoftAP = parcel.readInt() == 1;
        this.mIsDataTransMode = parcel.readInt() == 1;
        this.mDefault5gChannel = parcel.readInt();
        this.mIsSupportCamera = parcel.readInt() == 1;
        this.mIsSupportMic = parcel.readInt() == 1;
        this.mIsSupportLowLatencyDecodingMode = parcel.readInt() == 1;
        this.mIsSupportVoiceWaken = parcel.readInt() == 1;
        this.mIsSupportRealWifiAddress = parcel.readInt() == 1;
        parcel.readByteArray(this.mCarCustomField);
        this.mIsSupportStereoRecord = parcel.readInt() == 1;
        this.mCcdFilePath = parcel.readString();
    }

    @NonNull
    public String toString() {
        return "CarID: " + Arrays.toString(this.mCarBrMac) + "\nScreen Width: " + this.mScreenWidth + "\nScreen Height: " + this.mScreenHeight + "\nDpi: " + this.mDpi + "\nVideo Display Width: " + this.mVideoDisplayWidth + "\nVideo Display Height: " + this.mVideoDisplayHeight + "\nFPS: " + this.mFps + "\nSupportP2P: " + this.mIsSupportP2P + "\nSupport SoftAP: " + this.mIsSupportSoftAP + "\nSupport Camera: " + this.mIsSupportCamera + "\nSupport Mic: " + this.mIsSupportMic + "\nSupport Low Latency Decoding Mode: " + this.mIsSupportLowLatencyDecodingMode + "\nSupport Voice Waken: " + this.mIsSupportVoiceWaken + "\nSupport Real Wifi Address: " + this.mIsSupportRealWifiAddress + "\nData transport Mode: " + this.mIsDataTransMode + "\nDefault 5g Channel: " + this.mDefault5gChannel + "\ncustom Field: " + Arrays.toString(this.mCarCustomField) + "\nsupport stereo record:" + this.mIsSupportStereoRecord + "\nccd file path:" + this.mCcdFilePath;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.mCarBrMac);
        parcel.writeInt(this.mScreenWidth);
        parcel.writeInt(this.mScreenHeight);
        parcel.writeInt(this.mDpi);
        parcel.writeInt(this.mVideoDisplayWidth);
        parcel.writeInt(this.mVideoDisplayHeight);
        parcel.writeInt(this.mFps);
        parcel.writeInt(this.mIsSupportP2P ? 1 : 0);
        parcel.writeInt(this.mIsSupportSoftAP ? 1 : 0);
        parcel.writeInt(this.mIsDataTransMode ? 1 : 0);
        parcel.writeInt(this.mDefault5gChannel);
        parcel.writeInt(this.mIsSupportCamera ? 1 : 0);
        parcel.writeInt(this.mIsSupportMic ? 1 : 0);
        parcel.writeInt(this.mIsSupportLowLatencyDecodingMode ? 1 : 0);
        parcel.writeInt(this.mIsSupportVoiceWaken ? 1 : 0);
        parcel.writeInt(this.mIsSupportRealWifiAddress ? 1 : 0);
        parcel.writeByteArray(this.mCarCustomField);
        parcel.writeInt(this.mIsSupportStereoRecord ? 1 : 0);
        parcel.writeString(this.mCcdFilePath);
    }
}
