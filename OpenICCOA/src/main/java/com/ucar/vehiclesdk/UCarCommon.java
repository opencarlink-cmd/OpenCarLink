package com.ucar.vehiclesdk;

import android.util.Range;
import android.util.Size;
import com.ucar.databus.proto.UCarProto;
import java.util.Arrays;

public class UCarCommon {

    public static class AccelerationInfo {
        public double accX;
        public double accY;
        public double accZ;
        public long timeStamp;

        public AccelerationInfo(double d2, double d3, double d4, long j) {
            this.accX = d2;
            this.accY = d3;
            this.accZ = d4;
            this.timeStamp = j;
        }

        public double getAccX() {
            return this.accX;
        }

        public double getAccY() {
            return this.accY;
        }

        public double getAccZ() {
            return this.accZ;
        }

        public long getTimeStamp() {
            return this.timeStamp;
        }
    }

    public static class AudioAttributes {
        private final android.media.AudioAttributes audioAttributes;
        private final int focusGain;
        private final int streamType;

        public AudioAttributes(android.media.AudioAttributes audioAttributes, int i, int i2) {
            this.audioAttributes = audioAttributes;
            this.focusGain = i;
            this.streamType = i2;
        }

        public android.media.AudioAttributes getAudioAttributes() {
            return this.audioAttributes;
        }

        public int getFocusGain() {
            return this.focusGain;
        }

        public int getStreamType() {
            return this.streamType;
        }
    }

    public static class AudioFormat {
        public int channelConfig;
        public int encodingFormat;
        public String mimeType;
        public int sampleRate;

        public AudioFormat(String str, int i, int i2, int i3) {
            this.mimeType = str;
            this.sampleRate = i2;
            this.channelConfig = i3;
            this.encodingFormat = i;
        }

        public int getChannelConfig() {
            return this.channelConfig;
        }

        public int getEncodingFormat() {
            return this.encodingFormat;
        }

        public String getMimeType() {
            return this.mimeType;
        }

        public int getSampleRate() {
            return this.sampleRate;
        }

        public String toString() {
            return "AudioFormat{mimeType='" + this.mimeType + "', sampleRate=" + this.sampleRate + ", channelConfig=" + this.channelConfig + ", encodingFormat=" + this.encodingFormat + '}';
        }
    }

    public enum AudioType {
        STREAM_UNDEFINED(0),
        STREAM_IP_CALL(1),
        STREAM_MODEM_CALL(2),
        STREAM_AI_ASSISTANT(3),
        STREAM_RING(4),
        STREAM_NOTIFICATION(5),
        STREAM_TTS(6),
        STREAM_SYSTEM(7),
        STREAM_CAST_MUSIC(16);

        private static AudioType[] values = null;
        private final int mValue;

        AudioType(int i) {
            this.mValue = i;
        }

        public static AudioType fromInt(int i) {
            if (values == null) {
                values = values();
            }
            int i2 = 0;
            while (true) {
                AudioType[] audioTypeArr = values;
                if (i2 >= audioTypeArr.length) {
                    return STREAM_UNDEFINED;
                }
                if (audioTypeArr[i2].mValue == i) {
                    return audioTypeArr[i2];
                }
                i2++;
            }
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public static class BatteryInfo {
    }

    public static class BluetoothMacInfo {
        public String bluetoothMac;
        public OPType opType;

        public BluetoothMacInfo(OPType oPType, String str) {
            this.opType = oPType;
            this.bluetoothMac = str;
        }

        public String getBluetoothMac() {
            return this.bluetoothMac;
        }

        public OPType getOpType() {
            return this.opType;
        }

        public void setBluetoothMac(String str) {
            this.bluetoothMac = str;
        }

        public void setOpType(OPType oPType) {
            this.opType = oPType;
        }
    }

    public enum CameraAction {
        CAMERA_OPEN(0),
        CAMERA_CAPTURE(1),
        CAMERA_CLOSE(2),
        CAMERA_UNDEFINE(3);

        private int mValue;

        CameraAction(int i) {
            this.mValue = i;
        }

        public static CameraAction fromInt(int i) {
            return i != 0 ? i != 1 ? i != 2 ? CAMERA_UNDEFINE : CAMERA_CLOSE : CAMERA_CAPTURE : CAMERA_OPEN;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public static class CameraActionArgs {
        public String cameraId;
        public Range<Integer> fpsRange;
        public Size size;

        public CameraActionArgs(String str, Range<Integer> range, Size size) {
            this.cameraId = str;
            this.fpsRange = range;
            this.size = size;
        }

        public String getCameraId() {
            return this.cameraId;
        }

        public Range<Integer> getFpsRange() {
            return this.fpsRange;
        }

        public Size getPictureSize() {
            return this.size;
        }
    }

    public static class CameraInfo {
        public String cameraId;
        public Range<Integer>[] fpsRanges;
        public LensFacing lensFacing;
        public String name;
        public Orientation orientation;
        public Size[] supportedSizes;

        public CameraInfo(String str, String str2, LensFacing lensFacing) {
            this(str, str2, lensFacing, Orientation.ORIENTATION_UNKNOWN, null, null);
        }

        public CameraInfo(String str, String str2, LensFacing lensFacing, Orientation orientation, Size[] sizeArr, Range<Integer>[] rangeArr) {
            this.cameraId = str;
            this.name = str2;
            this.lensFacing = lensFacing;
            this.orientation = orientation;
            this.supportedSizes = sizeArr;
            this.fpsRanges = rangeArr;
        }

        public Range<Integer>[] getFpsRanges() {
            return this.fpsRanges;
        }

        public String getId() {
            return this.cameraId;
        }

        public LensFacing getLensFacing() {
            return this.lensFacing;
        }

        public String getName() {
            return this.name;
        }

        public Orientation getOrientation() {
            return this.orientation;
        }

        public Size[] getSupportedSizes() {
            return this.supportedSizes;
        }
    }

    public enum CameraState {
        CAMERA_STATE_OPENED(0),
        CAMERA_STATE_CLOSED(1),
        CAMERA_STATE_ERROR(2),
        CAMERA_STATE_PREEMPTED(3),
        CAMERA_STATE_NO_PERMISSION(4),
        CAMERA_STATE_BUSY(5),
        CAMERA_STATE_UNDEFINE(6);

        private int mValue;

        CameraState(int i) {
            this.mValue = i;
        }

        public static CameraState fromInt(int i) {
            return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? CAMERA_STATE_UNDEFINE : CAMERA_STATE_BUSY : CAMERA_STATE_NO_PERMISSION : CAMERA_STATE_PREEMPTED : CAMERA_STATE_ERROR : CAMERA_STATE_CLOSED : CAMERA_STATE_OPENED;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public enum DayNightMode {
        UNKNOWN_MODE(0),
        DAY_MODE(1),
        NIGHT_MODE(2);

        private static DayNightMode[] values = null;
        private final int mValue;

        DayNightMode(int i) {
            this.mValue = i;
        }

        public static DayNightMode fromInt(int i) {
            if (values == null) {
                values = values();
            }
            int i2 = 0;
            while (true) {
                DayNightMode[] dayNightModeArr = values;
                if (i2 >= dayNightModeArr.length) {
                    return UNKNOWN_MODE;
                }
                if (dayNightModeArr[i2].mValue == i) {
                    return dayNightModeArr[i2];
                }
                i2++;
            }
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public static class GPSInfo {
        public double altitude;
        public int antennaState;
        public int heading;
        public double latitude;
        public double longitude;
        public int pDop;
        public int satsUsed;
        public int satsVisible;
        public int speed;
        public long timeStamp;

        public GPSInfo(double d2, double d3, double d4, int i, int i2, int i3, int i4, int i5, int i6, long j) {
            this.altitude = d2;
            this.latitude = d3;
            this.longitude = d4;
            this.antennaState = i;
            this.pDop = i2;
            this.speed = i3;
            this.heading = i4;
            this.satsUsed = i5;
            this.satsVisible = i6;
            this.timeStamp = j;
        }

        public double getAltitude() {
            return this.altitude;
        }

        public int getAntennaState() {
            return this.antennaState;
        }

        public int getHeading() {
            return this.heading;
        }

        public double getLatitude() {
            return this.latitude;
        }

        public double getLongitude() {
            return this.longitude;
        }

        public int getSatsUsed() {
            return this.satsUsed;
        }

        public int getSatsVisible() {
            return this.satsVisible;
        }

        public int getSpeed() {
            return this.speed;
        }

        public long getTimeStamp() {
            return this.timeStamp;
        }

        public int getpDop() {
            return this.pDop;
        }
    }

    public enum GearState {
        GEAR_UNKNOWN(0),
        GEAR_PARK(1),
        GEAR_DRIVE(2),
        GEAR_REVERSE(3),
        GEAR_NEUTRAL(4);

        private static GearState[] values = null;
        private final int value;

        GearState(int i) {
            this.value = i;
        }

        public static GearState fromInt(int i) {
            if (values == null) {
                values = values();
            }
            int i2 = 0;
            while (true) {
                GearState[] gearStateArr = values;
                if (i2 >= gearStateArr.length) {
                    return GEAR_UNKNOWN;
                }
                if (gearStateArr[i2].value == i) {
                    return gearStateArr[i2];
                }
                i2++;
            }
        }

        public int getValue() {
            return this.value;
        }
    }

    public static class GearStateInfo {
        public int currentSpeed;
        public GearState gearState;

        public GearStateInfo(GearState gearState, int i) {
            this.gearState = gearState;
            this.currentSpeed = i;
        }

        public int getCurrentSpeed() {
            return this.currentSpeed;
        }

        public GearState getGearState() {
            GearState gearState = this.gearState;
            return gearState != null ? gearState : GearState.GEAR_UNKNOWN;
        }
    }

    public static class GyroscopeInfo {
        public int gyroType;
        public double legyroX;
        public double legyroY;
        public double legyroZ;
        public long timeStamp;

        public GyroscopeInfo(int i, double d2, double d3, double d4, long j) {
            this.gyroType = i;
            this.legyroX = d2;
            this.legyroY = d3;
            this.legyroZ = d4;
            this.timeStamp = j;
        }

        public int getGyroType() {
            return this.gyroType;
        }

        public double getLegyroX() {
            return this.legyroX;
        }

        public double getLegyroY() {
            return this.legyroY;
        }

        public double getLegyroZ() {
            return this.legyroZ;
        }

        public long getTimeStamp() {
            return this.timeStamp;
        }
    }

    public enum KeyCodeType {
        KEY_CODE_UNDEFINED(0),
        KEY_CODE_BACK(4),
        KEY_CODE_CALL(5),
        KEY_CODE_ENDCALL(6),
        KEY_CODE_NUM_0(7),
        KEY_CODE_NUM_1(8),
        KEY_CODE_NUM_2(9),
        KEY_CODE_NUM_3(10),
        KEY_CODE_NUM_4(11),
        KEY_CODE_NUM_5(12),
        KEY_CODE_NUM_6(13),
        KEY_CODE_NUM_7(14),
        KEY_CODE_NUM_8(15),
        KEY_CODE_NUM_9(16),
        KEY_CODE_DPAD_UP(19),
        KEY_CODE_DPAD_DOWN(20),
        KEY_CODE_DPAD_LEFT(21),
        KEY_CODE_DPAD_RIGHT(22),
        KEY_CODE_DPAD_CENTER(23),
        KEY_CODE_ENTER(66),
        KEY_CODE_MEDIA_NEXT(87),
        KEY_CODE_MEDIA_PREVIOUS(88),
        KEY_CODE_MIC_MUTE(91),
        KEY_CODE_PAGE_UP(92),
        KEY_CODE_PAGE_DOWN(93),
        KEY_CODE_MEDIA_PLAY(126),
        KEY_CODE_MEDIA_PAUSE(127),
        KEY_CODE_VOLUME_MUTE(164),
        KEY_CODE_ANDROID_DIVIDE_MARK(7936),
        KEY_CODE_MAIN(UCarProto.CustomKeyEvent.KeyCode.KEY_CODE_MAIN_VALUE),
        KEY_CODE_TEL(UCarProto.CustomKeyEvent.KeyCode.KEY_CODE_TEL_VALUE),
        KEY_CODE_NAVI(UCarProto.CustomKeyEvent.KeyCode.KEY_CODE_NAVI_VALUE),
        KEY_CODE_MEDIA(UCarProto.CustomKeyEvent.KeyCode.KEY_CODE_MEDIA_VALUE),
        KEY_CODE_VR_START(UCarProto.CustomKeyEvent.KeyCode.KEY_CODE_VR_START_VALUE),
        KEY_CODE_VR_STOP(UCarProto.CustomKeyEvent.KeyCode.KEY_CODE_VR_STOP_VALUE),
        KEY_CODE_NAVI_QUIT(UCarProto.CustomKeyEvent.KeyCode.KEY_CODE_NAVI_QUIT_VALUE),
        KEY_CODE_NEXT_FOCUS(UCarProto.CustomKeyEvent.KeyCode.KEY_CODE_NEXT_FOCUS_VALUE),
        KEY_CODE_PRE_FOCUS(UCarProto.CustomKeyEvent.KeyCode.KEY_CODE_PRE_FOCUS_VALUE),
        KEY_CODE_IN_FOCUS(8116),
        KEY_CODE_OUT_FOCUS(8117);

        private static KeyCodeType[] values = null;
        private final int mValue;

        KeyCodeType(int i) {
            this.mValue = i;
        }

        public static KeyCodeType fromInt(int i) {
            if (values == null) {
                values = values();
            }
            int i2 = 0;
            while (true) {
                KeyCodeType[] keyCodeTypeArr = values;
                if (i2 >= keyCodeTypeArr.length) {
                    return KEY_CODE_UNDEFINED;
                }
                if (keyCodeTypeArr[i2].mValue == i) {
                    return keyCodeTypeArr[i2];
                }
                i2++;
            }
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public enum KeyEventActionType {
        KEY_EVENT_ACTION_UNDEFINED(-1),
        KEY_EVENT_ACTION_DOWN(0),
        KEY_EVENT_ACTION_UP(1),
        KEY_EVENT_ACTION_PRESS(2);

        private static KeyEventActionType[] values = null;
        private final int mValue;

        KeyEventActionType(int i) {
            this.mValue = i;
        }

        public static KeyEventActionType fromInt(int i) {
            if (values == null) {
                values = values();
            }
            int i2 = 0;
            while (true) {
                KeyEventActionType[] keyEventActionTypeArr = values;
                if (i2 >= keyEventActionTypeArr.length) {
                    return KEY_EVENT_ACTION_UNDEFINED;
                }
                if (keyEventActionTypeArr[i2].mValue == i) {
                    return keyEventActionTypeArr[i2];
                }
                i2++;
            }
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public enum LensFacing {
        LENS_FACING_FRONT(0),
        LENS_FACING_BACK(1),
        LENS_FACING_EXTERNAL(2);

        private static LensFacing[] values = null;
        private final int mValue;

        LensFacing(int i) {
            this.mValue = i;
        }

        public static LensFacing fromInt(int i) {
            if (values == null) {
                values = values();
            }
            int i2 = 0;
            while (true) {
                LensFacing[] lensFacingArr = values;
                if (i2 >= lensFacingArr.length) {
                    return LENS_FACING_FRONT;
                }
                if (lensFacingArr[i2].mValue == i) {
                    return lensFacingArr[i2];
                }
                i2++;
            }
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public static class LightSensorInfo {
        public double currentLux;
        public double maxLux;
        public double minLux;

        public LightSensorInfo(double d2, double d3, double d4) {
            this.maxLux = d2;
            this.minLux = d3;
            this.currentLux = d4;
        }

        public double getCurrentLux() {
            return this.currentLux;
        }

        public double getMaxLux() {
            return this.maxLux;
        }

        public double getMinLux() {
            return this.minLux;
        }
    }

    public static class LightsInfo {
        public boolean backupLampOn;
        public boolean clearanceLampOn;
        public boolean highBeamOn;
        public boolean lowBeamOn;
        public boolean stopLampOn;

        public LightsInfo(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
            this.lowBeamOn = z;
            this.highBeamOn = z2;
            this.clearanceLampOn = z3;
            this.backupLampOn = z4;
            this.stopLampOn = z5;
        }

        public boolean isBackupLampOn() {
            return this.backupLampOn;
        }

        public boolean isClearanceLampOn() {
            return this.clearanceLampOn;
        }

        public boolean isHighBeamOn() {
            return this.highBeamOn;
        }

        public boolean isLowBeamOn() {
            return this.lowBeamOn;
        }

        public boolean isStopLampOn() {
            return this.stopLampOn;
        }
    }

    public enum ModemCallState {
        UNKNOWN_STATE(0),
        IDLE(1),
        RINGING(2),
        OFFHOOK(3);

        private static ModemCallState[] values = null;
        private final int value;

        ModemCallState(int i) {
            this.value = i;
        }

        public static ModemCallState fromInt(int i) {
            if (values == null) {
                values = values();
            }
            int i2 = 0;
            while (true) {
                ModemCallState[] modemCallStateArr = values;
                if (i2 >= modemCallStateArr.length) {
                    return UNKNOWN_STATE;
                }
                if (modemCallStateArr[i2].value == i) {
                    return modemCallStateArr[i2];
                }
                i2++;
            }
        }

        public int getValue() {
            return this.value;
        }
    }

    public static class MusicInfo {
        public String albumName;
        public String artistName;
        public String authorName;
        public String composerName;
        public byte[] cover;
        public String coverArt;
        public boolean isFavorite;
        public boolean isPlaying;
        public String lyrics;
        public int playingCurrentTimeMs;
        public String title;
        public long totalTimesMs;
        public String writerName;

        public MusicInfo(String str, String str2, String str3, String str4, long j, String str5, String str6, String str7, String str8, int i, boolean z, boolean z2, byte[] bArr) {
            this.artistName = str;
            this.albumName = str2;
            this.coverArt = str3;
            this.lyrics = str4;
            this.totalTimesMs = j;
            this.title = str5;
            this.authorName = str6;
            this.writerName = str7;
            this.composerName = str8;
            this.playingCurrentTimeMs = i;
            this.isFavorite = z;
            this.isPlaying = z2;
            this.cover = bArr;
        }

        public String getAlbumName() {
            return this.albumName;
        }

        public String getArtistName() {
            return this.artistName;
        }

        public String getAuthorName() {
            return this.authorName;
        }

        public String getComposerName() {
            return this.composerName;
        }

        public byte[] getCover() {
            return this.cover;
        }

        public String getCoverArt() {
            return this.coverArt;
        }

        public String getLyrics() {
            return this.lyrics;
        }

        public int getPlayingCurrentTimeMs() {
            return this.playingCurrentTimeMs;
        }

        public String getTitle() {
            return this.title;
        }

        public long getTotalTimesMs() {
            return this.totalTimesMs;
        }

        public String getWriterName() {
            return this.writerName;
        }

        public boolean isFavorite() {
            return this.isFavorite;
        }

        public boolean isPlaying() {
            return this.isPlaying;
        }

        public void setLyrics(String str) {
            this.lyrics = str;
        }

        public void setPlaying(boolean z) {
            this.isPlaying = z;
        }

        public void setPlayingCurrentTimeMs(int i) {
            this.playingCurrentTimeMs = i;
        }

        public String toString() {
            return "Artist: " + this.artistName + ", Album: " + this.albumName + ", Cover: " + this.coverArt + ", Lyrics: " + this.lyrics + ", TotalTimeMs: " + this.totalTimesMs + ", Tile: " + this.title + ", Author: " + this.authorName + ", Writer: " + this.writerName + ", Composer: " + this.composerName + ", CurrentTimeMs: " + this.playingCurrentTimeMs + ", isFavorite: " + this.isFavorite + ", isPlaying: " + this.isPlaying + ", cover: " + Arrays.toString(this.cover);
        }
    }

    public static class NavigationInfo {
        public byte[] directionIcon;
        public String distance;
        public String distanceUnit;
        public boolean isNavigating;
        public String operation;
        public String title1;
        public String title2;
        public String where;

        public NavigationInfo(boolean z, byte[] bArr, String str, String str2, String str3, String str4, String str5, String str6) {
            this.isNavigating = z;
            this.directionIcon = bArr;
            this.distance = str;
            this.distanceUnit = str2;
            this.operation = str3;
            this.where = str4;
            this.title1 = str5;
            this.title2 = str6;
        }

        public byte[] getDirectionIcon() {
            return this.directionIcon;
        }

        public String getDistance() {
            return this.distance;
        }

        public String getDistanceUnit() {
            return this.distanceUnit;
        }

        public String getOperation() {
            return this.operation;
        }

        public String getTitle1() {
            return this.title1;
        }

        public String getTitle2() {
            return this.title2;
        }

        public String getWhere() {
            return this.where;
        }

        public boolean isNavigating() {
            return this.isNavigating;
        }

        public String toString() {
            return "NavigationInfo{isNavigating: " + this.isNavigating + ", directionIcon: " + Arrays.toString(this.directionIcon) + ", distance: " + this.distance + ", distanceUnit: " + this.distanceUnit + ", operation: " + this.operation + ", where: " + this.where + ", title1: " + this.title1 + ", title2: " + this.title2 + '}';
        }
    }

    public enum OPType {
        OP_ADD(0),
        OP_DELETE(1);

        private static OPType[] values = null;
        private final int mValue;

        OPType(int i) {
            this.mValue = i;
        }

        public static OPType fromType(int i) {
            if (values == null) {
                values = values();
            }
            int i2 = 0;
            while (true) {
                OPType[] oPTypeArr = values;
                if (i2 >= oPTypeArr.length) {
                    return OP_ADD;
                }
                if (oPTypeArr[i2].mValue == i) {
                    return oPTypeArr[i2];
                }
                i2++;
            }
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public static class OilInfo {
        public int currentFuel;
        public int maxFuel;
        public int minFuel;

        public OilInfo(int i, int i2, int i3) {
            this.maxFuel = i;
            this.minFuel = i2;
            this.currentFuel = i3;
        }

        public int getCurrentFuel() {
            return this.currentFuel;
        }

        public int getMaxFuel() {
            return this.maxFuel;
        }

        public int getMinFuel() {
            return this.minFuel;
        }
    }

    public enum Orientation {
        ORIENTATION_UNKNOWN(-1),
        ORIENTATION_0(0),
        ORIENTATION_90(90),
        ORIENTATION_180(UCarProto.Orientation.ORIENTATION_180_VALUE),
        ORIENTATION_270(UCarProto.Orientation.ORIENTATION_270_VALUE);

        private static Orientation[] values = null;
        private final int mValue;

        Orientation(int i) {
            this.mValue = i;
        }

        public static Orientation fromInt(int i) {
            if (values == null) {
                values = values();
            }
            int i2 = 0;
            while (true) {
                Orientation[] orientationArr = values;
                if (i2 >= orientationArr.length) {
                    return ORIENTATION_UNKNOWN;
                }
                if (orientationArr[i2].mValue == i) {
                    return orientationArr[i2];
                }
                i2++;
            }
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public static class PhoneStateInfo {
        public boolean isScreenLocked;
        public boolean isUseMicrophone;
        public boolean isVoiceAssistantActive;
        public boolean isVoipCall;
        public ModemCallState modemCallState = ModemCallState.UNKNOWN_STATE;

        public ModemCallState getModemCallState() {
            return this.modemCallState;
        }

        public boolean isScreenLocked() {
            return this.isScreenLocked;
        }

        public boolean isUseMicrophone() {
            return this.isUseMicrophone;
        }

        public boolean isVoiceAssistantActive() {
            return this.isVoiceAssistantActive;
        }

        public boolean isVoipCall() {
            return this.isVoipCall;
        }

        public void setModemCallState(ModemCallState modemCallState) {
            this.modemCallState = modemCallState;
        }

        public void setScreenLocked(boolean z) {
            this.isScreenLocked = z;
        }

        public void setUseMicrophone(boolean z) {
            this.isUseMicrophone = z;
        }

        public void setVoiceAssistantActive(boolean z) {
            this.isVoiceAssistantActive = z;
        }

        public void setVoipCall(boolean z) {
            this.isVoipCall = z;
        }

        public String toString() {
            return "call state: " + this.modemCallState + ", isScreenLocked: " + this.isScreenLocked + ", isWechatQq: " + this.isVoipCall + ", isUseMicrophone: " + this.isUseMicrophone + ", isVoiceAssistantActive: " + this.isVoiceAssistantActive;
        }
    }

    public enum VRCmdType {
        VR_CMD_UNDEFINED(0),
        VR_CMD_MUSIC_PRE(1),
        VR_CMD_MUSIC_NEXT(2),
        VR_CMD_MUSIC_PAUSE(3),
        VR_CMD_MUSIC_PLAY(4),
        VR_CMD_CALL(5),
        VR_CMD_ENDCALL(6),
        VR_CMD_TEL(7),
        VR_CMD_MAP(8),
        VR_CMD_MUSIC(9),
        VR_CMD_VR_START(10),
        VR_CMD_VR_STOP(11),
        VR_CMD_NAVI_QUIT(12),
        VR_CMD_NAVI_HOME(13),
        VR_CMD_NAVI_COMPANY(14),
        VR_CMD_BACK_HOMEPAGE(15);

        private static VRCmdType[] values = null;
        private final int mValue;

        VRCmdType(int i) {
            this.mValue = i;
        }

        public static VRCmdType fromInt(int i) {
            if (values == null) {
                values = values();
            }
            int i2 = 0;
            while (true) {
                VRCmdType[] vRCmdTypeArr = values;
                if (i2 >= vRCmdTypeArr.length) {
                    return VR_CMD_UNDEFINED;
                }
                if (vRCmdTypeArr[i2].mValue == i) {
                    return vRCmdTypeArr[i2];
                }
                i2++;
            }
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public enum VideoType {
        STREAM_UNDEFINED(0),
        STREAM_CAST_VIDEO(1),
        STREAM_COMMUNICATION(2),
        STREAM_CAMERA_PREVIEW(3),
        STREAM_CAMERA_PICTURE(4);

        private static VideoType[] values = null;
        private final int mValue;

        VideoType(int i) {
            this.mValue = i;
        }

        public static VideoType fromInt(int i) {
            if (values == null) {
                values = values();
            }
            int i2 = 0;
            while (true) {
                VideoType[] videoTypeArr = values;
                if (i2 >= videoTypeArr.length) {
                    return STREAM_UNDEFINED;
                }
                if (videoTypeArr[i2].mValue == i) {
                    return videoTypeArr[i2];
                }
                i2++;
            }
        }

        public int getValue() {
            return this.mValue;
        }
    }
}
