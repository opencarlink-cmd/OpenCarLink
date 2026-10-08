package com.ucar.databus.proto;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

public final class UCarProto {

    public static class C10991 {

        public static final int[] f9470xa1dLEVEL_VERBOSE61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f9470xa1dLEVEL_VERBOSE61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9470xa1dLEVEL_VERBOSE61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9470xa1dLEVEL_VERBOSE61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9470xa1dLEVEL_VERBOSE61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9470xa1dLEVEL_VERBOSE61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9470xa1dLEVEL_VERBOSE61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9470xa1dLEVEL_VERBOSE61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static final class Acceleration extends GeneratedMessageLite<Acceleration, Acceleration.Builder> implements AccelerationOrBuilder {
        public static final int ACC_X_FIELD_NUMBER = 1;
        public static final int ACC_Y_FIELD_NUMBER = 2;
        public static final int ACC_Z_FIELD_NUMBER = 3;
        private static final Acceleration DEFAULT_INSTANCE;
        private static volatile Parser<Acceleration> PARSER = null;
        public static final int TIMESTAMP_FIELD_NUMBER = 4;
        private double accX_;
        private double accY_;
        private double accZ_;
        private long timestamp_;

        public static final class Builder extends GeneratedMessageLite.Builder<Acceleration, Builder> implements AccelerationOrBuilder {
            private Builder() {
                super(Acceleration.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearAccX() {
                copyOnWrite();
                ((Acceleration) this.instance).clearAccX();
                return this;
            }

            public Builder clearAccY() {
                copyOnWrite();
                ((Acceleration) this.instance).clearAccY();
                return this;
            }

            public Builder clearAccZ() {
                copyOnWrite();
                ((Acceleration) this.instance).clearAccZ();
                return this;
            }

            public Builder clearTimestamp() {
                copyOnWrite();
                ((Acceleration) this.instance).clearTimestamp();
                return this;
            }

            @Override
            public double getAccX() {
                return ((Acceleration) this.instance).getAccX();
            }

            @Override
            public double getAccY() {
                return ((Acceleration) this.instance).getAccY();
            }

            @Override
            public double getAccZ() {
                return ((Acceleration) this.instance).getAccZ();
            }

            @Override
            public long getTimestamp() {
                return ((Acceleration) this.instance).getTimestamp();
            }

            public Builder setAccX(double d2) {
                copyOnWrite();
                ((Acceleration) this.instance).setAccX(d2);
                return this;
            }

            public Builder setAccY(double d2) {
                copyOnWrite();
                ((Acceleration) this.instance).setAccY(d2);
                return this;
            }

            public Builder setAccZ(double d2) {
                copyOnWrite();
                ((Acceleration) this.instance).setAccZ(d2);
                return this;
            }

            public Builder setTimestamp(long j) {
                copyOnWrite();
                ((Acceleration) this.instance).setTimestamp(j);
                return this;
            }
        }

        static {
            Acceleration acceleration = new Acceleration();
            DEFAULT_INSTANCE = acceleration;
            GeneratedMessageLite.registerDefaultInstance(Acceleration.class, acceleration);
        }

        private Acceleration() {
        }

        public void clearAccX() {
            this.accX_ = 0.0d;
        }

        public void clearAccY() {
            this.accY_ = 0.0d;
        }

        public void clearAccZ() {
            this.accZ_ = 0.0d;
        }

        public void clearTimestamp() {
            this.timestamp_ = 0L;
        }

        public static Acceleration getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Acceleration acceleration) {
            return DEFAULT_INSTANCE.createBuilder(acceleration);
        }

        public static Acceleration parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Acceleration) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Acceleration parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (Acceleration) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static Acceleration parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (Acceleration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static Acceleration parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Acceleration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static Acceleration parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (Acceleration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static Acceleration parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (Acceleration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static Acceleration parseFrom(InputStream inputStream) throws IOException {
            return (Acceleration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Acceleration parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (Acceleration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static Acceleration parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Acceleration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Acceleration parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Acceleration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static Acceleration parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Acceleration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Acceleration parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Acceleration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<Acceleration> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setAccX(double d2) {
            this.accX_ = d2;
        }

        public void setAccY(double d2) {
            this.accY_ = d2;
        }

        public void setAccZ(double d2) {
            this.accZ_ = d2;
        }

        public void setTimestamp(long j) {
            this.timestamp_ = j;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new Acceleration();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0000\u0002\u0000\u0003\u0000\u0004\u0003", new Object[]{"accX_", "accY_", "accZ_", "timestamp_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Acceleration> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (Acceleration.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public double getAccX() {
            return this.accX_;
        }

        @Override
        public double getAccY() {
            return this.accY_;
        }

        @Override
        public double getAccZ() {
            return this.accZ_;
        }

        @Override
        public long getTimestamp() {
            return this.timestamp_;
        }
    }

    public interface AccelerationOrBuilder extends MessageLiteOrBuilder {
        double getAccX();

        double getAccY();

        double getAccZ();

        long getTimestamp();
    }

    public static final class AudioIndex extends GeneratedMessageLite<AudioIndex, AudioIndex.Builder> implements AudioIndexOrBuilder {
        public static final int AUDIO_TYPE_FIELD_NUMBER = 1;
        private static final AudioIndex DEFAULT_INSTANCE;
        private static volatile Parser<AudioIndex> PARSER;
        private int audioType_;

        public static final class Builder extends GeneratedMessageLite.Builder<AudioIndex, Builder> implements AudioIndexOrBuilder {
            private Builder() {
                super(AudioIndex.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearAudioType() {
                copyOnWrite();
                ((AudioIndex) this.instance).clearAudioType();
                return this;
            }

            @Override
            public AudioType getAudioType() {
                return ((AudioIndex) this.instance).getAudioType();
            }

            @Override
            public int getAudioTypeValue() {
                return ((AudioIndex) this.instance).getAudioTypeValue();
            }

            public Builder setAudioType(AudioType audioType) {
                copyOnWrite();
                ((AudioIndex) this.instance).setAudioType(audioType);
                return this;
            }

            public Builder setAudioTypeValue(int i) {
                copyOnWrite();
                ((AudioIndex) this.instance).setAudioTypeValue(i);
                return this;
            }
        }

        static {
            AudioIndex audioIndex = new AudioIndex();
            DEFAULT_INSTANCE = audioIndex;
            GeneratedMessageLite.registerDefaultInstance(AudioIndex.class, audioIndex);
        }

        private AudioIndex() {
        }

        public void clearAudioType() {
            this.audioType_ = 0;
        }

        public static AudioIndex getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(AudioIndex audioIndex) {
            return DEFAULT_INSTANCE.createBuilder(audioIndex);
        }

        public static AudioIndex parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AudioIndex) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AudioIndex parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (AudioIndex) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static AudioIndex parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (AudioIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static AudioIndex parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AudioIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static AudioIndex parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (AudioIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static AudioIndex parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (AudioIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static AudioIndex parseFrom(InputStream inputStream) throws IOException {
            return (AudioIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AudioIndex parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (AudioIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static AudioIndex parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (AudioIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static AudioIndex parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AudioIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static AudioIndex parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AudioIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AudioIndex parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AudioIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<AudioIndex> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setAudioType(AudioType audioType) {
            this.audioType_ = audioType.getNumber();
        }

        public void setAudioTypeValue(int i) {
            this.audioType_ = i;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new AudioIndex();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", new Object[]{"audioType_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<AudioIndex> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (AudioIndex.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public AudioType getAudioType() {
            AudioType audioTypeForNumber = AudioType.forNumber(this.audioType_);
            return audioTypeForNumber == null ? AudioType.UNRECOGNIZED : audioTypeForNumber;
        }

        @Override
        public int getAudioTypeValue() {
            return this.audioType_;
        }
    }

    public interface AudioIndexOrBuilder extends MessageLiteOrBuilder {
        AudioType getAudioType();

        int getAudioTypeValue();
    }

    public static final class AudioPlayerControl extends GeneratedMessageLite<AudioPlayerControl, AudioPlayerControl.Builder> implements AudioPlayerControlOrBuilder {
        public static final int AUDIO_TYPE_FIELD_NUMBER = 1;
        public static final int BUFFERING_COUNT_FIELD_NUMBER = 2;
        private static final AudioPlayerControl DEFAULT_INSTANCE;
        private static volatile Parser<AudioPlayerControl> PARSER = null;
        public static final int SPEED_ADJUST_STEP_FIELD_NUMBER = 3;
        private int audioType_;
        private int bufferingCount_;
        private int speedAdjustStep_;

        public static final class Builder extends GeneratedMessageLite.Builder<AudioPlayerControl, Builder> implements AudioPlayerControlOrBuilder {
            private Builder() {
                super(AudioPlayerControl.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearAudioType() {
                copyOnWrite();
                ((AudioPlayerControl) this.instance).clearAudioType();
                return this;
            }

            public Builder clearBufferingCount() {
                copyOnWrite();
                ((AudioPlayerControl) this.instance).clearBufferingCount();
                return this;
            }

            public Builder clearSpeedAdjustStep() {
                copyOnWrite();
                ((AudioPlayerControl) this.instance).clearSpeedAdjustStep();
                return this;
            }

            @Override
            public AudioType getAudioType() {
                return ((AudioPlayerControl) this.instance).getAudioType();
            }

            @Override
            public int getAudioTypeValue() {
                return ((AudioPlayerControl) this.instance).getAudioTypeValue();
            }

            @Override
            public int getBufferingCount() {
                return ((AudioPlayerControl) this.instance).getBufferingCount();
            }

            @Override
            public int getSpeedAdjustStep() {
                return ((AudioPlayerControl) this.instance).getSpeedAdjustStep();
            }

            public Builder setAudioType(AudioType audioType) {
                copyOnWrite();
                ((AudioPlayerControl) this.instance).setAudioType(audioType);
                return this;
            }

            public Builder setAudioTypeValue(int i) {
                copyOnWrite();
                ((AudioPlayerControl) this.instance).setAudioTypeValue(i);
                return this;
            }

            public Builder setBufferingCount(int i) {
                copyOnWrite();
                ((AudioPlayerControl) this.instance).setBufferingCount(i);
                return this;
            }

            public Builder setSpeedAdjustStep(int i) {
                copyOnWrite();
                ((AudioPlayerControl) this.instance).setSpeedAdjustStep(i);
                return this;
            }
        }

        static {
            AudioPlayerControl audioPlayerControl = new AudioPlayerControl();
            DEFAULT_INSTANCE = audioPlayerControl;
            GeneratedMessageLite.registerDefaultInstance(AudioPlayerControl.class, audioPlayerControl);
        }

        private AudioPlayerControl() {
        }

        public void clearAudioType() {
            this.audioType_ = 0;
        }

        public void clearBufferingCount() {
            this.bufferingCount_ = 0;
        }

        public void clearSpeedAdjustStep() {
            this.speedAdjustStep_ = 0;
        }

        public static AudioPlayerControl getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(AudioPlayerControl audioPlayerControl) {
            return DEFAULT_INSTANCE.createBuilder(audioPlayerControl);
        }

        public static AudioPlayerControl parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AudioPlayerControl) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AudioPlayerControl parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (AudioPlayerControl) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static AudioPlayerControl parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (AudioPlayerControl) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static AudioPlayerControl parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AudioPlayerControl) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static AudioPlayerControl parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (AudioPlayerControl) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static AudioPlayerControl parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (AudioPlayerControl) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static AudioPlayerControl parseFrom(InputStream inputStream) throws IOException {
            return (AudioPlayerControl) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AudioPlayerControl parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (AudioPlayerControl) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static AudioPlayerControl parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (AudioPlayerControl) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static AudioPlayerControl parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AudioPlayerControl) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static AudioPlayerControl parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AudioPlayerControl) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AudioPlayerControl parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AudioPlayerControl) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<AudioPlayerControl> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setAudioType(AudioType audioType) {
            this.audioType_ = audioType.getNumber();
        }

        public void setAudioTypeValue(int i) {
            this.audioType_ = i;
        }

        public void setBufferingCount(int i) {
            this.bufferingCount_ = i;
        }

        public void setSpeedAdjustStep(int i) {
            this.speedAdjustStep_ = i;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new AudioPlayerControl();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0002\u0004\u0003\u0004", new Object[]{"audioType_", "bufferingCount_", "speedAdjustStep_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<AudioPlayerControl> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (AudioPlayerControl.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public AudioType getAudioType() {
            AudioType audioTypeForNumber = AudioType.forNumber(this.audioType_);
            return audioTypeForNumber == null ? AudioType.UNRECOGNIZED : audioTypeForNumber;
        }

        @Override
        public int getAudioTypeValue() {
            return this.audioType_;
        }

        @Override
        public int getBufferingCount() {
            return this.bufferingCount_;
        }

        @Override
        public int getSpeedAdjustStep() {
            return this.speedAdjustStep_;
        }
    }

    public interface AudioPlayerControlOrBuilder extends MessageLiteOrBuilder {
        AudioType getAudioType();

        int getAudioTypeValue();

        int getBufferingCount();

        int getSpeedAdjustStep();
    }

    public enum AudioType implements Internal.EnumLite {
        UNDEFINED(0),
        STREAM_IP_CALL(1),
        STREAM_MODEM_CALL(2),
        STREAM_AI_ASSISTANT(3),
        STREAM_RING(4),
        STREAM_NOTIFICATION(5),
        STREAM_TTS(6),
        STREAM_SYSTEM(7),
        STREAM_MICROPHONE(9),
        STREAM_NUM(10),
        STREAM_CAST_MUSIC(16),
        UNRECOGNIZED(-1);

        public static final int STREAM_AI_ASSISTANT_VALUE = 3;
        public static final int STREAM_CAST_MUSIC_VALUE = 16;
        public static final int STREAM_IP_CALL_VALUE = 1;
        public static final int STREAM_MICROPHONE_VALUE = 9;
        public static final int STREAM_MODEM_CALL_VALUE = 2;
        public static final int STREAM_NOTIFICATION_VALUE = 5;
        public static final int STREAM_NUM_VALUE = 10;
        public static final int STREAM_RING_VALUE = 4;
        public static final int STREAM_SYSTEM_VALUE = 7;
        public static final int STREAM_TTS_VALUE = 6;
        public static final int UNDEFINED_VALUE = 0;
        private static final Internal.EnumLiteMap<AudioType> internalValueMap = new Internal.EnumLiteMap<AudioType>() {
            @Override
            public AudioType findValueByNumber(int i) {
                return AudioType.forNumber(i);
            }
        };
        private final int value;

        public static final class AudioTypeVerifier implements Internal.EnumVerifier {
            public static final Internal.EnumVerifier INSTANCE = new AudioTypeVerifier();

            private AudioTypeVerifier() {
            }

            @Override
            public boolean isInRange(int i) {
                return AudioType.forNumber(i) != null;
            }
        }

        AudioType(int i) {
            this.value = i;
        }

        public static AudioType forNumber(int i) {
            if (i == 9) {
                return STREAM_MICROPHONE;
            }
            if (i == 10) {
                return STREAM_NUM;
            }
            if (i == 16) {
                return STREAM_CAST_MUSIC;
            }
            switch (i) {
                case 0:
                    return UNDEFINED;
                case 1:
                    return STREAM_IP_CALL;
                case 2:
                    return STREAM_MODEM_CALL;
                case 3:
                    return STREAM_AI_ASSISTANT;
                case 4:
                    return STREAM_RING;
                case 5:
                    return STREAM_NOTIFICATION;
                case 6:
                    return STREAM_TTS;
                case 7:
                    return STREAM_SYSTEM;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<AudioType> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return AudioTypeVerifier.INSTANCE;
        }

        @Deprecated
        public static AudioType valueOf(int i) {
            return forNumber(i);
        }

        @Override
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
    }

    public static final class AuthConfirm extends GeneratedMessageLite<AuthConfirm, AuthConfirm.Builder> implements AuthConfirmOrBuilder {
        public static final int CIPHER_FIELD_NUMBER = 1;
        private static final AuthConfirm DEFAULT_INSTANCE;
        private static volatile Parser<AuthConfirm> PARSER;
        private ByteString cipher_ = ByteString.EMPTY;

        public static final class Builder extends GeneratedMessageLite.Builder<AuthConfirm, Builder> implements AuthConfirmOrBuilder {
            private Builder() {
                super(AuthConfirm.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearCipher() {
                copyOnWrite();
                ((AuthConfirm) this.instance).clearCipher();
                return this;
            }

            @Override
            public ByteString getCipher() {
                return ((AuthConfirm) this.instance).getCipher();
            }

            public Builder setCipher(ByteString abstractC2534u) {
                copyOnWrite();
                ((AuthConfirm) this.instance).setCipher(abstractC2534u);
                return this;
            }
        }

        static {
            AuthConfirm authConfirm = new AuthConfirm();
            DEFAULT_INSTANCE = authConfirm;
            GeneratedMessageLite.registerDefaultInstance(AuthConfirm.class, authConfirm);
        }

        private AuthConfirm() {
        }

        public void clearCipher() {
            this.cipher_ = getDefaultInstance().getCipher();
        }

        public static AuthConfirm getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(AuthConfirm authConfirm) {
            return DEFAULT_INSTANCE.createBuilder(authConfirm);
        }

        public static AuthConfirm parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AuthConfirm) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthConfirm parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (AuthConfirm) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static AuthConfirm parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (AuthConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static AuthConfirm parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AuthConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static AuthConfirm parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (AuthConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static AuthConfirm parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (AuthConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static AuthConfirm parseFrom(InputStream inputStream) throws IOException {
            return (AuthConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthConfirm parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (AuthConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static AuthConfirm parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (AuthConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static AuthConfirm parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AuthConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static AuthConfirm parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AuthConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AuthConfirm parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AuthConfirm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<AuthConfirm> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setCipher(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.cipher_ = abstractC2534u;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new AuthConfirm();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\n", new Object[]{"cipher_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<AuthConfirm> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (AuthConfirm.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public ByteString getCipher() {
            return this.cipher_;
        }
    }

    public interface AuthConfirmOrBuilder extends MessageLiteOrBuilder {
        ByteString getCipher();
    }

    public static final class AuthIndex extends GeneratedMessageLite<AuthIndex, AuthIndex.Builder> implements AuthIndexOrBuilder {
        public static final int AUTH_CONFIRM_FIELD_NUMBER = 3;
        public static final int AUTH_REQUEST_FIELD_NUMBER = 1;
        public static final int AUTH_RESPONSE_FIELD_NUMBER = 2;
        private static final AuthIndex DEFAULT_INSTANCE;
        private static volatile Parser<AuthIndex> PARSER;
        private AuthConfirm authConfirm_;
        private AuthRequest authRequest_;
        private AuthResponse authResponse_;

        public static final class Builder extends GeneratedMessageLite.Builder<AuthIndex, Builder> implements AuthIndexOrBuilder {
            private Builder() {
                super(AuthIndex.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearAuthConfirm() {
                copyOnWrite();
                ((AuthIndex) this.instance).clearAuthConfirm();
                return this;
            }

            public Builder clearAuthRequest() {
                copyOnWrite();
                ((AuthIndex) this.instance).clearAuthRequest();
                return this;
            }

            public Builder clearAuthResponse() {
                copyOnWrite();
                ((AuthIndex) this.instance).clearAuthResponse();
                return this;
            }

            @Override
            public AuthConfirm getAuthConfirm() {
                return ((AuthIndex) this.instance).getAuthConfirm();
            }

            @Override
            public AuthRequest getAuthRequest() {
                return ((AuthIndex) this.instance).getAuthRequest();
            }

            @Override
            public AuthResponse getAuthResponse() {
                return ((AuthIndex) this.instance).getAuthResponse();
            }

            @Override
            public boolean hasAuthConfirm() {
                return ((AuthIndex) this.instance).hasAuthConfirm();
            }

            @Override
            public boolean hasAuthRequest() {
                return ((AuthIndex) this.instance).hasAuthRequest();
            }

            @Override
            public boolean hasAuthResponse() {
                return ((AuthIndex) this.instance).hasAuthResponse();
            }

            public Builder mergeAuthConfirm(AuthConfirm authConfirm) {
                copyOnWrite();
                ((AuthIndex) this.instance).mergeAuthConfirm(authConfirm);
                return this;
            }

            public Builder mergeAuthRequest(AuthRequest authRequest) {
                copyOnWrite();
                ((AuthIndex) this.instance).mergeAuthRequest(authRequest);
                return this;
            }

            public Builder mergeAuthResponse(AuthResponse authResponse) {
                copyOnWrite();
                ((AuthIndex) this.instance).mergeAuthResponse(authResponse);
                return this;
            }

            public Builder setAuthConfirm(AuthConfirm.Builder builder) {
                copyOnWrite();
                ((AuthIndex) this.instance).setAuthConfirm(builder.build());
                return this;
            }

            public Builder setAuthConfirm(AuthConfirm authConfirm) {
                copyOnWrite();
                ((AuthIndex) this.instance).setAuthConfirm(authConfirm);
                return this;
            }

            public Builder setAuthRequest(AuthRequest.Builder builder) {
                copyOnWrite();
                ((AuthIndex) this.instance).setAuthRequest(builder.build());
                return this;
            }

            public Builder setAuthRequest(AuthRequest authRequest) {
                copyOnWrite();
                ((AuthIndex) this.instance).setAuthRequest(authRequest);
                return this;
            }

            public Builder setAuthResponse(AuthResponse.Builder builder) {
                copyOnWrite();
                ((AuthIndex) this.instance).setAuthResponse(builder.build());
                return this;
            }

            public Builder setAuthResponse(AuthResponse authResponse) {
                copyOnWrite();
                ((AuthIndex) this.instance).setAuthResponse(authResponse);
                return this;
            }
        }

        static {
            AuthIndex authIndex = new AuthIndex();
            DEFAULT_INSTANCE = authIndex;
            GeneratedMessageLite.registerDefaultInstance(AuthIndex.class, authIndex);
        }

        private AuthIndex() {
        }

        public void clearAuthConfirm() {
            this.authConfirm_ = null;
        }

        public void clearAuthRequest() {
            this.authRequest_ = null;
        }

        public void clearAuthResponse() {
            this.authResponse_ = null;
        }

        public static AuthIndex getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public void mergeAuthConfirm(AuthConfirm authConfirm) {
            authConfirm.getClass();
            AuthConfirm authConfirm2 = this.authConfirm_;
            if (authConfirm2 != null && authConfirm2 != AuthConfirm.getDefaultInstance()) {
                authConfirm = AuthConfirm.newBuilder(this.authConfirm_).mergeFrom(authConfirm).buildPartial();
            }
            this.authConfirm_ = authConfirm;
        }

        public void mergeAuthRequest(AuthRequest authRequest) {
            authRequest.getClass();
            AuthRequest authRequest2 = this.authRequest_;
            if (authRequest2 != null && authRequest2 != AuthRequest.getDefaultInstance()) {
                authRequest = AuthRequest.newBuilder(this.authRequest_).mergeFrom(authRequest).buildPartial();
            }
            this.authRequest_ = authRequest;
        }

        public void mergeAuthResponse(AuthResponse authResponse) {
            authResponse.getClass();
            AuthResponse authResponse2 = this.authResponse_;
            if (authResponse2 != null && authResponse2 != AuthResponse.getDefaultInstance()) {
                authResponse = AuthResponse.newBuilder(this.authResponse_).mergeFrom(authResponse).buildPartial();
            }
            this.authResponse_ = authResponse;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(AuthIndex authIndex) {
            return DEFAULT_INSTANCE.createBuilder(authIndex);
        }

        public static AuthIndex parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AuthIndex) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthIndex parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (AuthIndex) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static AuthIndex parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (AuthIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static AuthIndex parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AuthIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static AuthIndex parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (AuthIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static AuthIndex parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (AuthIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static AuthIndex parseFrom(InputStream inputStream) throws IOException {
            return (AuthIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthIndex parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (AuthIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static AuthIndex parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (AuthIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static AuthIndex parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AuthIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static AuthIndex parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AuthIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AuthIndex parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AuthIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<AuthIndex> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setAuthConfirm(AuthConfirm authConfirm) {
            authConfirm.getClass();
            this.authConfirm_ = authConfirm;
        }

        public void setAuthRequest(AuthRequest authRequest) {
            authRequest.getClass();
            this.authRequest_ = authRequest;
        }

        public void setAuthResponse(AuthResponse authResponse) {
            authResponse.getClass();
            this.authResponse_ = authResponse;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new AuthIndex();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\t", new Object[]{"authRequest_", "authResponse_", "authConfirm_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<AuthIndex> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (AuthIndex.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public AuthConfirm getAuthConfirm() {
            AuthConfirm authConfirm = this.authConfirm_;
            return authConfirm == null ? AuthConfirm.getDefaultInstance() : authConfirm;
        }

        @Override
        public AuthRequest getAuthRequest() {
            AuthRequest authRequest = this.authRequest_;
            return authRequest == null ? AuthRequest.getDefaultInstance() : authRequest;
        }

        @Override
        public AuthResponse getAuthResponse() {
            AuthResponse authResponse = this.authResponse_;
            return authResponse == null ? AuthResponse.getDefaultInstance() : authResponse;
        }

        @Override
        public boolean hasAuthConfirm() {
            return this.authConfirm_ != null;
        }

        @Override
        public boolean hasAuthRequest() {
            return this.authRequest_ != null;
        }

        @Override
        public boolean hasAuthResponse() {
            return this.authResponse_ != null;
        }
    }

    public interface AuthIndexOrBuilder extends MessageLiteOrBuilder {
        AuthConfirm getAuthConfirm();

        AuthRequest getAuthRequest();

        AuthResponse getAuthResponse();

        boolean hasAuthConfirm();

        boolean hasAuthRequest();

        boolean hasAuthResponse();
    }

    public static final class AuthRequest extends GeneratedMessageLite<AuthRequest, AuthRequest.Builder> implements AuthRequestOrBuilder {
        public static final int AGREEMENTPKSIG_FIELD_NUMBER = 5;
        public static final int AGREEMENTPK_FIELD_NUMBER = 4;
        public static final int AUTHPKHMAC_FIELD_NUMBER = 3;
        public static final int AUTHPK_FIELD_NUMBER = 2;
        private static final AuthRequest DEFAULT_INSTANCE;
        public static final int ID_FIELD_NUMBER = 7;
        public static final int MODEL_FIELD_NUMBER = 8;
        private static volatile Parser<AuthRequest> PARSER = null;
        public static final int RANDOM_FIELD_NUMBER = 6;
        public static final int USERCONFIRMED_FIELD_NUMBER = 9;
        public static final int VERSION_FIELD_NUMBER = 1;
        private ByteString agreementPkSig_;
        private ByteString agreementPk_;
        private ByteString authPkHmac_;
        private ByteString authPk_;
        private ByteString id_;
        private String model_;
        private ByteString random_;
        private boolean userConfirmed_;
        private int version_;

        public static final class Builder extends GeneratedMessageLite.Builder<AuthRequest, Builder> implements AuthRequestOrBuilder {
            private Builder() {
                super(AuthRequest.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearAgreementPk() {
                copyOnWrite();
                ((AuthRequest) this.instance).clearAgreementPk();
                return this;
            }

            public Builder clearAgreementPkSig() {
                copyOnWrite();
                ((AuthRequest) this.instance).clearAgreementPkSig();
                return this;
            }

            public Builder clearAuthPk() {
                copyOnWrite();
                ((AuthRequest) this.instance).clearAuthPk();
                return this;
            }

            public Builder clearAuthPkHmac() {
                copyOnWrite();
                ((AuthRequest) this.instance).clearAuthPkHmac();
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((AuthRequest) this.instance).clearId();
                return this;
            }

            public Builder clearModel() {
                copyOnWrite();
                ((AuthRequest) this.instance).clearModel();
                return this;
            }

            public Builder clearRandom() {
                copyOnWrite();
                ((AuthRequest) this.instance).clearRandom();
                return this;
            }

            public Builder clearUserConfirmed() {
                copyOnWrite();
                ((AuthRequest) this.instance).clearUserConfirmed();
                return this;
            }

            public Builder clearVersion() {
                copyOnWrite();
                ((AuthRequest) this.instance).clearVersion();
                return this;
            }

            @Override
            public ByteString getAgreementPk() {
                return ((AuthRequest) this.instance).getAgreementPk();
            }

            @Override
            public ByteString getAgreementPkSig() {
                return ((AuthRequest) this.instance).getAgreementPkSig();
            }

            @Override
            public ByteString getAuthPk() {
                return ((AuthRequest) this.instance).getAuthPk();
            }

            @Override
            public ByteString getAuthPkHmac() {
                return ((AuthRequest) this.instance).getAuthPkHmac();
            }

            @Override
            public ByteString getId() {
                return ((AuthRequest) this.instance).getId();
            }

            @Override
            public String getModel() {
                return ((AuthRequest) this.instance).getModel();
            }

            @Override
            public ByteString getModelBytes() {
                return ((AuthRequest) this.instance).getModelBytes();
            }

            @Override
            public ByteString getRandom() {
                return ((AuthRequest) this.instance).getRandom();
            }

            @Override
            public boolean getUserConfirmed() {
                return ((AuthRequest) this.instance).getUserConfirmed();
            }

            @Override
            public int getVersion() {
                return ((AuthRequest) this.instance).getVersion();
            }

            public Builder setAgreementPk(ByteString abstractC2534u) {
                copyOnWrite();
                ((AuthRequest) this.instance).setAgreementPk(abstractC2534u);
                return this;
            }

            public Builder setAgreementPkSig(ByteString abstractC2534u) {
                copyOnWrite();
                ((AuthRequest) this.instance).setAgreementPkSig(abstractC2534u);
                return this;
            }

            public Builder setAuthPk(ByteString abstractC2534u) {
                copyOnWrite();
                ((AuthRequest) this.instance).setAuthPk(abstractC2534u);
                return this;
            }

            public Builder setAuthPkHmac(ByteString abstractC2534u) {
                copyOnWrite();
                ((AuthRequest) this.instance).setAuthPkHmac(abstractC2534u);
                return this;
            }

            public Builder setId(ByteString abstractC2534u) {
                copyOnWrite();
                ((AuthRequest) this.instance).setId(abstractC2534u);
                return this;
            }

            public Builder setModel(String str) {
                copyOnWrite();
                ((AuthRequest) this.instance).setModel(str);
                return this;
            }

            public Builder setModelBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((AuthRequest) this.instance).setModelBytes(abstractC2534u);
                return this;
            }

            public Builder setRandom(ByteString abstractC2534u) {
                copyOnWrite();
                ((AuthRequest) this.instance).setRandom(abstractC2534u);
                return this;
            }

            public Builder setUserConfirmed(boolean z) {
                copyOnWrite();
                ((AuthRequest) this.instance).setUserConfirmed(z);
                return this;
            }

            public Builder setVersion(int i) {
                copyOnWrite();
                ((AuthRequest) this.instance).setVersion(i);
                return this;
            }
        }

        static {
            AuthRequest authRequest = new AuthRequest();
            DEFAULT_INSTANCE = authRequest;
            GeneratedMessageLite.registerDefaultInstance(AuthRequest.class, authRequest);
        }

        private AuthRequest() {
            ByteString abstractC2534u = ByteString.EMPTY;
            this.authPk_ = abstractC2534u;
            this.authPkHmac_ = abstractC2534u;
            this.agreementPk_ = abstractC2534u;
            this.agreementPkSig_ = abstractC2534u;
            this.random_ = abstractC2534u;
            this.id_ = abstractC2534u;
            this.model_ = "";
        }

        public void clearAgreementPk() {
            this.agreementPk_ = getDefaultInstance().getAgreementPk();
        }

        public void clearAgreementPkSig() {
            this.agreementPkSig_ = getDefaultInstance().getAgreementPkSig();
        }

        public void clearAuthPk() {
            this.authPk_ = getDefaultInstance().getAuthPk();
        }

        public void clearAuthPkHmac() {
            this.authPkHmac_ = getDefaultInstance().getAuthPkHmac();
        }

        public void clearId() {
            this.id_ = getDefaultInstance().getId();
        }

        public void clearModel() {
            this.model_ = getDefaultInstance().getModel();
        }

        public void clearRandom() {
            this.random_ = getDefaultInstance().getRandom();
        }

        public void clearUserConfirmed() {
            this.userConfirmed_ = false;
        }

        public void clearVersion() {
            this.version_ = 0;
        }

        public static AuthRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(AuthRequest authRequest) {
            return DEFAULT_INSTANCE.createBuilder(authRequest);
        }

        public static AuthRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AuthRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (AuthRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static AuthRequest parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (AuthRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static AuthRequest parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AuthRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static AuthRequest parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (AuthRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static AuthRequest parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (AuthRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static AuthRequest parseFrom(InputStream inputStream) throws IOException {
            return (AuthRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthRequest parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (AuthRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static AuthRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (AuthRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static AuthRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AuthRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static AuthRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AuthRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AuthRequest parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AuthRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<AuthRequest> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setAgreementPk(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.agreementPk_ = abstractC2534u;
        }

        public void setAgreementPkSig(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.agreementPkSig_ = abstractC2534u;
        }

        public void setAuthPk(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.authPk_ = abstractC2534u;
        }

        public void setAuthPkHmac(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.authPkHmac_ = abstractC2534u;
        }

        public void setId(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.id_ = abstractC2534u;
        }

        public void setModel(String str) {
            str.getClass();
            this.model_ = str;
        }

        public void setModelBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.model_ = abstractC2534u.toStringUtf8();
        }

        public void setRandom(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.random_ = abstractC2534u;
        }

        public void setUserConfirmed(boolean z) {
            this.userConfirmed_ = z;
        }

        public void setVersion(int i) {
            this.version_ = i;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new AuthRequest();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\t\u0000\u0000\u0001\t\t\u0000\u0000\u0000\u0001\u0004\u0002\n\u0003\n\u0004\n\u0005\n\u0006\n\u0007\n\bȈ\t\u0007", new Object[]{"version_", "authPk_", "authPkHmac_", "agreementPk_", "agreementPkSig_", "random_", "id_", "model_", "userConfirmed_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<AuthRequest> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (AuthRequest.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public ByteString getAgreementPk() {
            return this.agreementPk_;
        }

        @Override
        public ByteString getAgreementPkSig() {
            return this.agreementPkSig_;
        }

        @Override
        public ByteString getAuthPk() {
            return this.authPk_;
        }

        @Override
        public ByteString getAuthPkHmac() {
            return this.authPkHmac_;
        }

        @Override
        public ByteString getId() {
            return this.id_;
        }

        @Override
        public String getModel() {
            return this.model_;
        }

        @Override
        public ByteString getModelBytes() {
            return ByteString.copyFromUtf8(this.model_);
        }

        @Override
        public ByteString getRandom() {
            return this.random_;
        }

        @Override
        public boolean getUserConfirmed() {
            return this.userConfirmed_;
        }

        @Override
        public int getVersion() {
            return this.version_;
        }
    }

    public interface AuthRequestOrBuilder extends MessageLiteOrBuilder {
        ByteString getAgreementPk();

        ByteString getAgreementPkSig();

        ByteString getAuthPk();

        ByteString getAuthPkHmac();

        ByteString getId();

        String getModel();

        ByteString getModelBytes();

        ByteString getRandom();

        boolean getUserConfirmed();

        int getVersion();
    }

    public static final class AuthResponse extends GeneratedMessageLite<AuthResponse, AuthResponse.Builder> implements AuthResponseOrBuilder {
        public static final int AGREEMENTPKSIG_FIELD_NUMBER = 5;
        public static final int AGREEMENTPK_FIELD_NUMBER = 4;
        public static final int AUTHPKHMAC_FIELD_NUMBER = 3;
        public static final int AUTHPK_FIELD_NUMBER = 2;
        private static final AuthResponse DEFAULT_INSTANCE;
        private static volatile Parser<AuthResponse> PARSER = null;
        public static final int RANDOM_FIELD_NUMBER = 6;
        public static final int RESULT_FIELD_NUMBER = 7;
        public static final int VERSION_FIELD_NUMBER = 1;
        private ByteString agreementPkSig_;
        private ByteString agreementPk_;
        private ByteString authPkHmac_;
        private ByteString authPk_;
        private ByteString random_;
        private int result_;
        private int version_;

        public static final class Builder extends GeneratedMessageLite.Builder<AuthResponse, Builder> implements AuthResponseOrBuilder {
            private Builder() {
                super(AuthResponse.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearAgreementPk() {
                copyOnWrite();
                ((AuthResponse) this.instance).clearAgreementPk();
                return this;
            }

            public Builder clearAgreementPkSig() {
                copyOnWrite();
                ((AuthResponse) this.instance).clearAgreementPkSig();
                return this;
            }

            public Builder clearAuthPk() {
                copyOnWrite();
                ((AuthResponse) this.instance).clearAuthPk();
                return this;
            }

            public Builder clearAuthPkHmac() {
                copyOnWrite();
                ((AuthResponse) this.instance).clearAuthPkHmac();
                return this;
            }

            public Builder clearRandom() {
                copyOnWrite();
                ((AuthResponse) this.instance).clearRandom();
                return this;
            }

            public Builder clearResult() {
                copyOnWrite();
                ((AuthResponse) this.instance).clearResult();
                return this;
            }

            public Builder clearVersion() {
                copyOnWrite();
                ((AuthResponse) this.instance).clearVersion();
                return this;
            }

            @Override
            public ByteString getAgreementPk() {
                return ((AuthResponse) this.instance).getAgreementPk();
            }

            @Override
            public ByteString getAgreementPkSig() {
                return ((AuthResponse) this.instance).getAgreementPkSig();
            }

            @Override
            public ByteString getAuthPk() {
                return ((AuthResponse) this.instance).getAuthPk();
            }

            @Override
            public ByteString getAuthPkHmac() {
                return ((AuthResponse) this.instance).getAuthPkHmac();
            }

            @Override
            public ByteString getRandom() {
                return ((AuthResponse) this.instance).getRandom();
            }

            @Override
            public int getResult() {
                return ((AuthResponse) this.instance).getResult();
            }

            @Override
            public int getVersion() {
                return ((AuthResponse) this.instance).getVersion();
            }

            public Builder setAgreementPk(ByteString abstractC2534u) {
                copyOnWrite();
                ((AuthResponse) this.instance).setAgreementPk(abstractC2534u);
                return this;
            }

            public Builder setAgreementPkSig(ByteString abstractC2534u) {
                copyOnWrite();
                ((AuthResponse) this.instance).setAgreementPkSig(abstractC2534u);
                return this;
            }

            public Builder setAuthPk(ByteString abstractC2534u) {
                copyOnWrite();
                ((AuthResponse) this.instance).setAuthPk(abstractC2534u);
                return this;
            }

            public Builder setAuthPkHmac(ByteString abstractC2534u) {
                copyOnWrite();
                ((AuthResponse) this.instance).setAuthPkHmac(abstractC2534u);
                return this;
            }

            public Builder setRandom(ByteString abstractC2534u) {
                copyOnWrite();
                ((AuthResponse) this.instance).setRandom(abstractC2534u);
                return this;
            }

            public Builder setResult(int i) {
                copyOnWrite();
                ((AuthResponse) this.instance).setResult(i);
                return this;
            }

            public Builder setVersion(int i) {
                copyOnWrite();
                ((AuthResponse) this.instance).setVersion(i);
                return this;
            }
        }

        static {
            AuthResponse authResponse = new AuthResponse();
            DEFAULT_INSTANCE = authResponse;
            GeneratedMessageLite.registerDefaultInstance(AuthResponse.class, authResponse);
        }

        private AuthResponse() {
            ByteString abstractC2534u = ByteString.EMPTY;
            this.authPk_ = abstractC2534u;
            this.authPkHmac_ = abstractC2534u;
            this.agreementPk_ = abstractC2534u;
            this.agreementPkSig_ = abstractC2534u;
            this.random_ = abstractC2534u;
        }

        public void clearAgreementPk() {
            this.agreementPk_ = getDefaultInstance().getAgreementPk();
        }

        public void clearAgreementPkSig() {
            this.agreementPkSig_ = getDefaultInstance().getAgreementPkSig();
        }

        public void clearAuthPk() {
            this.authPk_ = getDefaultInstance().getAuthPk();
        }

        public void clearAuthPkHmac() {
            this.authPkHmac_ = getDefaultInstance().getAuthPkHmac();
        }

        public void clearRandom() {
            this.random_ = getDefaultInstance().getRandom();
        }

        public void clearResult() {
            this.result_ = 0;
        }

        public void clearVersion() {
            this.version_ = 0;
        }

        public static AuthResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(AuthResponse authResponse) {
            return DEFAULT_INSTANCE.createBuilder(authResponse);
        }

        public static AuthResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AuthResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (AuthResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static AuthResponse parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static AuthResponse parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static AuthResponse parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static AuthResponse parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static AuthResponse parseFrom(InputStream inputStream) throws IOException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthResponse parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static AuthResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static AuthResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static AuthResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AuthResponse parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AuthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<AuthResponse> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setAgreementPk(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.agreementPk_ = abstractC2534u;
        }

        public void setAgreementPkSig(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.agreementPkSig_ = abstractC2534u;
        }

        public void setAuthPk(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.authPk_ = abstractC2534u;
        }

        public void setAuthPkHmac(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.authPkHmac_ = abstractC2534u;
        }

        public void setRandom(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.random_ = abstractC2534u;
        }

        public void setResult(int i) {
            this.result_ = i;
        }

        public void setVersion(int i) {
            this.version_ = i;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new AuthResponse();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0000\u0000\u0001\u0004\u0002\n\u0003\n\u0004\n\u0005\n\u0006\n\u0007\u0004", new Object[]{"version_", "authPk_", "authPkHmac_", "agreementPk_", "agreementPkSig_", "random_", "result_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<AuthResponse> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (AuthResponse.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public ByteString getAgreementPk() {
            return this.agreementPk_;
        }

        @Override
        public ByteString getAgreementPkSig() {
            return this.agreementPkSig_;
        }

        @Override
        public ByteString getAuthPk() {
            return this.authPk_;
        }

        @Override
        public ByteString getAuthPkHmac() {
            return this.authPkHmac_;
        }

        @Override
        public ByteString getRandom() {
            return this.random_;
        }

        @Override
        public int getResult() {
            return this.result_;
        }

        @Override
        public int getVersion() {
            return this.version_;
        }
    }

    public interface AuthResponseOrBuilder extends MessageLiteOrBuilder {
        ByteString getAgreementPk();

        ByteString getAgreementPkSig();

        ByteString getAuthPk();

        ByteString getAuthPkHmac();

        ByteString getRandom();

        int getResult();

        int getVersion();
    }

    public static final class AwakenVoiceAssistant extends GeneratedMessageLite<AwakenVoiceAssistant, AwakenVoiceAssistant.Builder> implements AwakenVoiceAssistantOrBuilder {
        public static final int CHANNEL_MASK_FIELD_NUMBER = 3;
        private static final AwakenVoiceAssistant DEFAULT_INSTANCE;
        public static final int ENCODING_FORMAT_FIELD_NUMBER = 4;
        private static volatile Parser<AwakenVoiceAssistant> PARSER = null;
        public static final int PCM_DATA_FIELD_NUMBER = 1;
        public static final int SAMPLE_RATE_FIELD_NUMBER = 2;
        public static final int SOURCE_FIELD_NUMBER = 6;
        public static final int TIMESTAMP_FIELD_NUMBER = 5;
        private int channelMask_;
        private int encodingFormat_;
        private int sampleRate_;
        private long timestamp_;
        private ByteString pcmData_ = ByteString.EMPTY;
        private String source_ = "";

        public static final class Builder extends GeneratedMessageLite.Builder<AwakenVoiceAssistant, Builder> implements AwakenVoiceAssistantOrBuilder {
            private Builder() {
                super(AwakenVoiceAssistant.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearChannelMask() {
                copyOnWrite();
                ((AwakenVoiceAssistant) this.instance).clearChannelMask();
                return this;
            }

            public Builder clearEncodingFormat() {
                copyOnWrite();
                ((AwakenVoiceAssistant) this.instance).clearEncodingFormat();
                return this;
            }

            public Builder clearPcmData() {
                copyOnWrite();
                ((AwakenVoiceAssistant) this.instance).clearPcmData();
                return this;
            }

            public Builder clearSampleRate() {
                copyOnWrite();
                ((AwakenVoiceAssistant) this.instance).clearSampleRate();
                return this;
            }

            public Builder clearSource() {
                copyOnWrite();
                ((AwakenVoiceAssistant) this.instance).clearSource();
                return this;
            }

            public Builder clearTimestamp() {
                copyOnWrite();
                ((AwakenVoiceAssistant) this.instance).clearTimestamp();
                return this;
            }

            @Override
            public ChannelMask getChannelMask() {
                return ((AwakenVoiceAssistant) this.instance).getChannelMask();
            }

            @Override
            public int getChannelMaskValue() {
                return ((AwakenVoiceAssistant) this.instance).getChannelMaskValue();
            }

            @Override
            public EncodingFormat getEncodingFormat() {
                return ((AwakenVoiceAssistant) this.instance).getEncodingFormat();
            }

            @Override
            public int getEncodingFormatValue() {
                return ((AwakenVoiceAssistant) this.instance).getEncodingFormatValue();
            }

            @Override
            public ByteString getPcmData() {
                return ((AwakenVoiceAssistant) this.instance).getPcmData();
            }

            @Override
            public SampleRate getSampleRate() {
                return ((AwakenVoiceAssistant) this.instance).getSampleRate();
            }

            @Override
            public int getSampleRateValue() {
                return ((AwakenVoiceAssistant) this.instance).getSampleRateValue();
            }

            @Override
            public String getSource() {
                return ((AwakenVoiceAssistant) this.instance).getSource();
            }

            @Override
            public ByteString getSourceBytes() {
                return ((AwakenVoiceAssistant) this.instance).getSourceBytes();
            }

            @Override
            public long getTimestamp() {
                return ((AwakenVoiceAssistant) this.instance).getTimestamp();
            }

            public Builder setChannelMask(ChannelMask channelMask) {
                copyOnWrite();
                ((AwakenVoiceAssistant) this.instance).setChannelMask(channelMask);
                return this;
            }

            public Builder setChannelMaskValue(int i) {
                copyOnWrite();
                ((AwakenVoiceAssistant) this.instance).setChannelMaskValue(i);
                return this;
            }

            public Builder setEncodingFormat(EncodingFormat encodingFormat) {
                copyOnWrite();
                ((AwakenVoiceAssistant) this.instance).setEncodingFormat(encodingFormat);
                return this;
            }

            public Builder setEncodingFormatValue(int i) {
                copyOnWrite();
                ((AwakenVoiceAssistant) this.instance).setEncodingFormatValue(i);
                return this;
            }

            public Builder setPcmData(ByteString abstractC2534u) {
                copyOnWrite();
                ((AwakenVoiceAssistant) this.instance).setPcmData(abstractC2534u);
                return this;
            }

            public Builder setSampleRate(SampleRate sampleRate) {
                copyOnWrite();
                ((AwakenVoiceAssistant) this.instance).setSampleRate(sampleRate);
                return this;
            }

            public Builder setSampleRateValue(int i) {
                copyOnWrite();
                ((AwakenVoiceAssistant) this.instance).setSampleRateValue(i);
                return this;
            }

            public Builder setSource(String str) {
                copyOnWrite();
                ((AwakenVoiceAssistant) this.instance).setSource(str);
                return this;
            }

            public Builder setSourceBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((AwakenVoiceAssistant) this.instance).setSourceBytes(abstractC2534u);
                return this;
            }

            public Builder setTimestamp(long j) {
                copyOnWrite();
                ((AwakenVoiceAssistant) this.instance).setTimestamp(j);
                return this;
            }
        }

        static {
            AwakenVoiceAssistant awakenVoiceAssistant = new AwakenVoiceAssistant();
            DEFAULT_INSTANCE = awakenVoiceAssistant;
            GeneratedMessageLite.registerDefaultInstance(AwakenVoiceAssistant.class, awakenVoiceAssistant);
        }

        private AwakenVoiceAssistant() {
        }

        public void clearChannelMask() {
            this.channelMask_ = 0;
        }

        public void clearEncodingFormat() {
            this.encodingFormat_ = 0;
        }

        public void clearPcmData() {
            this.pcmData_ = getDefaultInstance().getPcmData();
        }

        public void clearSampleRate() {
            this.sampleRate_ = 0;
        }

        public void clearSource() {
            this.source_ = getDefaultInstance().getSource();
        }

        public void clearTimestamp() {
            this.timestamp_ = 0L;
        }

        public static AwakenVoiceAssistant getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(AwakenVoiceAssistant awakenVoiceAssistant) {
            return DEFAULT_INSTANCE.createBuilder(awakenVoiceAssistant);
        }

        public static AwakenVoiceAssistant parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AwakenVoiceAssistant) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AwakenVoiceAssistant parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (AwakenVoiceAssistant) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static AwakenVoiceAssistant parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (AwakenVoiceAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static AwakenVoiceAssistant parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AwakenVoiceAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static AwakenVoiceAssistant parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (AwakenVoiceAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static AwakenVoiceAssistant parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (AwakenVoiceAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static AwakenVoiceAssistant parseFrom(InputStream inputStream) throws IOException {
            return (AwakenVoiceAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AwakenVoiceAssistant parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (AwakenVoiceAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static AwakenVoiceAssistant parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (AwakenVoiceAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static AwakenVoiceAssistant parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AwakenVoiceAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static AwakenVoiceAssistant parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AwakenVoiceAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AwakenVoiceAssistant parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (AwakenVoiceAssistant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<AwakenVoiceAssistant> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setChannelMask(ChannelMask channelMask) {
            this.channelMask_ = channelMask.getNumber();
        }

        public void setChannelMaskValue(int i) {
            this.channelMask_ = i;
        }

        public void setEncodingFormat(EncodingFormat encodingFormat) {
            this.encodingFormat_ = encodingFormat.getNumber();
        }

        public void setEncodingFormatValue(int i) {
            this.encodingFormat_ = i;
        }

        public void setPcmData(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.pcmData_ = abstractC2534u;
        }

        public void setSampleRate(SampleRate sampleRate) {
            this.sampleRate_ = sampleRate.getNumber();
        }

        public void setSampleRateValue(int i) {
            this.sampleRate_ = i;
        }

        public void setSource(String str) {
            str.getClass();
            this.source_ = str;
        }

        public void setSourceBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.source_ = abstractC2534u.toStringUtf8();
        }

        public void setTimestamp(long j) {
            this.timestamp_ = j;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new AwakenVoiceAssistant();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0000\u0000\u0001\n\u0002\f\u0003\f\u0004\f\u0005\u0003\u0006Ȉ", new Object[]{"pcmData_", "sampleRate_", "channelMask_", "encodingFormat_", "timestamp_", "source_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<AwakenVoiceAssistant> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (AwakenVoiceAssistant.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public ChannelMask getChannelMask() {
            ChannelMask channelMaskForNumber = ChannelMask.forNumber(this.channelMask_);
            return channelMaskForNumber == null ? ChannelMask.UNRECOGNIZED : channelMaskForNumber;
        }

        @Override
        public int getChannelMaskValue() {
            return this.channelMask_;
        }

        @Override
        public EncodingFormat getEncodingFormat() {
            EncodingFormat encodingFormatForNumber = EncodingFormat.forNumber(this.encodingFormat_);
            return encodingFormatForNumber == null ? EncodingFormat.UNRECOGNIZED : encodingFormatForNumber;
        }

        @Override
        public int getEncodingFormatValue() {
            return this.encodingFormat_;
        }

        @Override
        public ByteString getPcmData() {
            return this.pcmData_;
        }

        @Override
        public SampleRate getSampleRate() {
            SampleRate sampleRateForNumber = SampleRate.forNumber(this.sampleRate_);
            return sampleRateForNumber == null ? SampleRate.UNRECOGNIZED : sampleRateForNumber;
        }

        @Override
        public int getSampleRateValue() {
            return this.sampleRate_;
        }

        @Override
        public String getSource() {
            return this.source_;
        }

        @Override
        public ByteString getSourceBytes() {
            return ByteString.copyFromUtf8(this.source_);
        }

        @Override
        public long getTimestamp() {
            return this.timestamp_;
        }
    }

    public interface AwakenVoiceAssistantOrBuilder extends MessageLiteOrBuilder {
        ChannelMask getChannelMask();

        int getChannelMaskValue();

        EncodingFormat getEncodingFormat();

        int getEncodingFormatValue();

        ByteString getPcmData();

        SampleRate getSampleRate();

        int getSampleRateValue();

        String getSource();

        ByteString getSourceBytes();

        long getTimestamp();
    }

    public static final class BluetoothMacInfo extends GeneratedMessageLite<BluetoothMacInfo, BluetoothMacInfo.Builder> implements BluetoothMacInfoOrBuilder {
        public static final int BLUETOOTH_MAC_FIELD_NUMBER = 2;
        private static final BluetoothMacInfo DEFAULT_INSTANCE;
        public static final int OP_TYPE_FIELD_NUMBER = 1;
        private static volatile Parser<BluetoothMacInfo> PARSER;
        private String bluetoothMac_ = "";
        private int opType_;

        public static final class Builder extends GeneratedMessageLite.Builder<BluetoothMacInfo, Builder> implements BluetoothMacInfoOrBuilder {
            private Builder() {
                super(BluetoothMacInfo.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearBluetoothMac() {
                copyOnWrite();
                ((BluetoothMacInfo) this.instance).clearBluetoothMac();
                return this;
            }

            public Builder clearOpType() {
                copyOnWrite();
                ((BluetoothMacInfo) this.instance).clearOpType();
                return this;
            }

            @Override
            public String getBluetoothMac() {
                return ((BluetoothMacInfo) this.instance).getBluetoothMac();
            }

            @Override
            public ByteString getBluetoothMacBytes() {
                return ((BluetoothMacInfo) this.instance).getBluetoothMacBytes();
            }

            @Override
            public OPType getOpType() {
                return ((BluetoothMacInfo) this.instance).getOpType();
            }

            @Override
            public int getOpTypeValue() {
                return ((BluetoothMacInfo) this.instance).getOpTypeValue();
            }

            public Builder setBluetoothMac(String str) {
                copyOnWrite();
                ((BluetoothMacInfo) this.instance).setBluetoothMac(str);
                return this;
            }

            public Builder setBluetoothMacBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((BluetoothMacInfo) this.instance).setBluetoothMacBytes(abstractC2534u);
                return this;
            }

            public Builder setOpType(OPType oPType) {
                copyOnWrite();
                ((BluetoothMacInfo) this.instance).setOpType(oPType);
                return this;
            }

            public Builder setOpTypeValue(int i) {
                copyOnWrite();
                ((BluetoothMacInfo) this.instance).setOpTypeValue(i);
                return this;
            }
        }

        public enum OPType implements Internal.EnumLite {
            ADD(0),
            DELETE(1),
            UNRECOGNIZED(-1);

            public static final int ADD_VALUE = 0;
            public static final int DELETE_VALUE = 1;
            private static final Internal.EnumLiteMap<OPType> internalValueMap = new Internal.EnumLiteMap<OPType>() {
                @Override
                public OPType findValueByNumber(int i) {
                    return OPType.forNumber(i);
                }
            };
            private final int value;

            public static final class OPTypeVerifier implements Internal.EnumVerifier {
                public static final Internal.EnumVerifier INSTANCE = new OPTypeVerifier();

                private OPTypeVerifier() {
                }

                @Override
                public boolean isInRange(int i) {
                    return OPType.forNumber(i) != null;
                }
            }

            OPType(int i) {
                this.value = i;
            }

            public static OPType forNumber(int i) {
                if (i == 0) {
                    return ADD;
                }
                if (i != 1) {
                    return null;
                }
                return DELETE;
            }

            public static Internal.EnumLiteMap<OPType> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return OPTypeVerifier.INSTANCE;
            }

            @Deprecated
            public static OPType valueOf(int i) {
                return forNumber(i);
            }

            @Override
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
        }

        static {
            BluetoothMacInfo bluetoothMacInfo = new BluetoothMacInfo();
            DEFAULT_INSTANCE = bluetoothMacInfo;
            GeneratedMessageLite.registerDefaultInstance(BluetoothMacInfo.class, bluetoothMacInfo);
        }

        private BluetoothMacInfo() {
        }

        public void clearBluetoothMac() {
            this.bluetoothMac_ = getDefaultInstance().getBluetoothMac();
        }

        public void clearOpType() {
            this.opType_ = 0;
        }

        public static BluetoothMacInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(BluetoothMacInfo bluetoothMacInfo) {
            return DEFAULT_INSTANCE.createBuilder(bluetoothMacInfo);
        }

        public static BluetoothMacInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (BluetoothMacInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static BluetoothMacInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (BluetoothMacInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static BluetoothMacInfo parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (BluetoothMacInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static BluetoothMacInfo parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (BluetoothMacInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static BluetoothMacInfo parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (BluetoothMacInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static BluetoothMacInfo parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (BluetoothMacInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static BluetoothMacInfo parseFrom(InputStream inputStream) throws IOException {
            return (BluetoothMacInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static BluetoothMacInfo parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (BluetoothMacInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static BluetoothMacInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (BluetoothMacInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static BluetoothMacInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (BluetoothMacInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static BluetoothMacInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (BluetoothMacInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static BluetoothMacInfo parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (BluetoothMacInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<BluetoothMacInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setBluetoothMac(String str) {
            str.getClass();
            this.bluetoothMac_ = str;
        }

        public void setBluetoothMacBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.bluetoothMac_ = abstractC2534u.toStringUtf8();
        }

        public void setOpType(OPType oPType) {
            this.opType_ = oPType.getNumber();
        }

        public void setOpTypeValue(int i) {
            this.opType_ = i;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new BluetoothMacInfo();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002Ȉ", new Object[]{"opType_", "bluetoothMac_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<BluetoothMacInfo> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (BluetoothMacInfo.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public String getBluetoothMac() {
            return this.bluetoothMac_;
        }

        @Override
        public ByteString getBluetoothMacBytes() {
            return ByteString.copyFromUtf8(this.bluetoothMac_);
        }

        @Override
        public OPType getOpType() {
            OPType oPTypeForNumber = OPType.forNumber(this.opType_);
            return oPTypeForNumber == null ? OPType.UNRECOGNIZED : oPTypeForNumber;
        }

        @Override
        public int getOpTypeValue() {
            return this.opType_;
        }
    }

    public interface BluetoothMacInfoOrBuilder extends MessageLiteOrBuilder {
        String getBluetoothMac();

        ByteString getBluetoothMacBytes();

        BluetoothMacInfo.OPType getOpType();

        int getOpTypeValue();
    }

    public enum CameraAction implements Internal.EnumLite {
        CAMERA_ACTION_OPEN(0),
        CAMERA_ACTION_CAPTURE(1),
        CAMERA_ACTION_CLOSE(2),
        UNRECOGNIZED(-1);

        public static final int CAMERA_ACTION_CAPTURE_VALUE = 1;
        public static final int CAMERA_ACTION_CLOSE_VALUE = 2;
        public static final int CAMERA_ACTION_OPEN_VALUE = 0;
        private static final Internal.EnumLiteMap<CameraAction> internalValueMap = new Internal.EnumLiteMap<CameraAction>() {
            @Override
            public CameraAction findValueByNumber(int i) {
                return CameraAction.forNumber(i);
            }
        };
        private final int value;

        public static final class CameraActionVerifier implements Internal.EnumVerifier {
            public static final Internal.EnumVerifier INSTANCE = new CameraActionVerifier();

            private CameraActionVerifier() {
            }

            @Override
            public boolean isInRange(int i) {
                return CameraAction.forNumber(i) != null;
            }
        }

        CameraAction(int i) {
            this.value = i;
        }

        public static CameraAction forNumber(int i) {
            if (i == 0) {
                return CAMERA_ACTION_OPEN;
            }
            if (i == 1) {
                return CAMERA_ACTION_CAPTURE;
            }
            if (i != 2) {
                return null;
            }
            return CAMERA_ACTION_CLOSE;
        }

        public static Internal.EnumLiteMap<CameraAction> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return CameraActionVerifier.INSTANCE;
        }

        @Deprecated
        public static CameraAction valueOf(int i) {
            return forNumber(i);
        }

        @Override
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
    }

    public static final class CarCertificate extends GeneratedMessageLite<CarCertificate, CarCertificate.Builder> implements CarCertificateOrBuilder {
        public static final int CONTENT_FIELD_NUMBER = 1;
        private static final CarCertificate DEFAULT_INSTANCE;
        private static volatile Parser<CarCertificate> PARSER;
        private ByteString content_ = ByteString.EMPTY;

        public static final class Builder extends GeneratedMessageLite.Builder<CarCertificate, Builder> implements CarCertificateOrBuilder {
            private Builder() {
                super(CarCertificate.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearContent() {
                copyOnWrite();
                ((CarCertificate) this.instance).clearContent();
                return this;
            }

            @Override
            public ByteString getContent() {
                return ((CarCertificate) this.instance).getContent();
            }

            public Builder setContent(ByteString abstractC2534u) {
                copyOnWrite();
                ((CarCertificate) this.instance).setContent(abstractC2534u);
                return this;
            }
        }

        static {
            CarCertificate carCertificate = new CarCertificate();
            DEFAULT_INSTANCE = carCertificate;
            GeneratedMessageLite.registerDefaultInstance(CarCertificate.class, carCertificate);
        }

        private CarCertificate() {
        }

        public void clearContent() {
            this.content_ = getDefaultInstance().getContent();
        }

        public static CarCertificate getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(CarCertificate carCertificate) {
            return DEFAULT_INSTANCE.createBuilder(carCertificate);
        }

        public static CarCertificate parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CarCertificate) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CarCertificate parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (CarCertificate) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static CarCertificate parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (CarCertificate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static CarCertificate parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (CarCertificate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static CarCertificate parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (CarCertificate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static CarCertificate parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (CarCertificate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static CarCertificate parseFrom(InputStream inputStream) throws IOException {
            return (CarCertificate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CarCertificate parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (CarCertificate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static CarCertificate parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CarCertificate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static CarCertificate parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (CarCertificate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static CarCertificate parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CarCertificate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CarCertificate parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (CarCertificate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<CarCertificate> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setContent(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.content_ = abstractC2534u;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new CarCertificate();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\n", new Object[]{"content_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<CarCertificate> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (CarCertificate.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public ByteString getContent() {
            return this.content_;
        }
    }

    public interface CarCertificateOrBuilder extends MessageLiteOrBuilder {
        ByteString getContent();
    }

    public static final class CertIndex extends GeneratedMessageLite<CertIndex, CertIndex.Builder> implements CertIndexOrBuilder {
        public static final int CERTIFICATE_FIELD_NUMBER = 1;
        private static final CertIndex DEFAULT_INSTANCE;
        private static volatile Parser<CertIndex> PARSER;
        private CarCertificate certificate_;

        public static final class Builder extends GeneratedMessageLite.Builder<CertIndex, Builder> implements CertIndexOrBuilder {
            private Builder() {
                super(CertIndex.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearCertificate() {
                copyOnWrite();
                ((CertIndex) this.instance).clearCertificate();
                return this;
            }

            @Override
            public CarCertificate getCertificate() {
                return ((CertIndex) this.instance).getCertificate();
            }

            @Override
            public boolean hasCertificate() {
                return ((CertIndex) this.instance).hasCertificate();
            }

            public Builder mergeCertificate(CarCertificate carCertificate) {
                copyOnWrite();
                ((CertIndex) this.instance).mergeCertificate(carCertificate);
                return this;
            }

            public Builder setCertificate(CarCertificate.Builder builder) {
                copyOnWrite();
                ((CertIndex) this.instance).setCertificate(builder.build());
                return this;
            }

            public Builder setCertificate(CarCertificate carCertificate) {
                copyOnWrite();
                ((CertIndex) this.instance).setCertificate(carCertificate);
                return this;
            }
        }

        static {
            CertIndex certIndex = new CertIndex();
            DEFAULT_INSTANCE = certIndex;
            GeneratedMessageLite.registerDefaultInstance(CertIndex.class, certIndex);
        }

        private CertIndex() {
        }

        public void clearCertificate() {
            this.certificate_ = null;
        }

        public static CertIndex getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public void mergeCertificate(CarCertificate carCertificate) {
            carCertificate.getClass();
            CarCertificate carCertificate2 = this.certificate_;
            if (carCertificate2 != null && carCertificate2 != CarCertificate.getDefaultInstance()) {
                carCertificate = CarCertificate.newBuilder(this.certificate_).mergeFrom(carCertificate).buildPartial();
            }
            this.certificate_ = carCertificate;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(CertIndex certIndex) {
            return DEFAULT_INSTANCE.createBuilder(certIndex);
        }

        public static CertIndex parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CertIndex) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CertIndex parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (CertIndex) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static CertIndex parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (CertIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static CertIndex parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (CertIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static CertIndex parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (CertIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static CertIndex parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (CertIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static CertIndex parseFrom(InputStream inputStream) throws IOException {
            return (CertIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CertIndex parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (CertIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static CertIndex parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CertIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static CertIndex parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (CertIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static CertIndex parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CertIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CertIndex parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (CertIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<CertIndex> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setCertificate(CarCertificate carCertificate) {
            carCertificate.getClass();
            this.certificate_ = carCertificate;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new CertIndex();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"certificate_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<CertIndex> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (CertIndex.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public CarCertificate getCertificate() {
            CarCertificate carCertificate = this.certificate_;
            return carCertificate == null ? CarCertificate.getDefaultInstance() : carCertificate;
        }

        @Override
        public boolean hasCertificate() {
            return this.certificate_ != null;
        }
    }

    public interface CertIndexOrBuilder extends MessageLiteOrBuilder {
        CarCertificate getCertificate();

        boolean hasCertificate();
    }

    public enum ChannelMask implements Internal.EnumLite {
        UNKNOWN_CHANNEL(0),
        CHANNEL_MONO(1),
        CHANNEL_STEREO(2),
        UNRECOGNIZED(-1);

        public static final int CHANNEL_MONO_VALUE = 1;
        public static final int CHANNEL_STEREO_VALUE = 2;
        public static final int UNKNOWN_CHANNEL_VALUE = 0;
        private static final Internal.EnumLiteMap<ChannelMask> internalValueMap = new Internal.EnumLiteMap<ChannelMask>() {
            @Override
            public ChannelMask findValueByNumber(int i) {
                return ChannelMask.forNumber(i);
            }
        };
        private final int value;

        public static final class ChannelMaskVerifier implements Internal.EnumVerifier {
            public static final Internal.EnumVerifier INSTANCE = new ChannelMaskVerifier();

            private ChannelMaskVerifier() {
            }

            @Override
            public boolean isInRange(int i) {
                return ChannelMask.forNumber(i) != null;
            }
        }

        ChannelMask(int i) {
            this.value = i;
        }

        public static ChannelMask forNumber(int i) {
            if (i == 0) {
                return UNKNOWN_CHANNEL;
            }
            if (i == 1) {
                return CHANNEL_MONO;
            }
            if (i != 2) {
                return null;
            }
            return CHANNEL_STEREO;
        }

        public static Internal.EnumLiteMap<ChannelMask> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return ChannelMaskVerifier.INSTANCE;
        }

        @Deprecated
        public static ChannelMask valueOf(int i) {
            return forNumber(i);
        }

        @Override
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
    }

    public static final class ControlIndex extends GeneratedMessageLite<ControlIndex, ControlIndex.Builder> implements ControlIndexOrBuilder {
        public static final int AUDIO_PLAYER_CONTROL_FIELD_NUMBER = 17;
        public static final int AWAKEN_VOICE_ASSISTANT_FIELD_NUMBER = 16;
        public static final int BLUETOOTH_MAC_FIELD_NUMBER = 22;
        public static final int CAMERA_STATE_FIELD_NUMBER = 21;
        public static final int CUSTOM_KEY_EVENT_FIELD_NUMBER = 12;
        private static final ControlIndex DEFAULT_INSTANCE;
        public static final int DISCONNECT_FIELD_NUMBER = 23;
        public static final int GET_PORT_REQUEST_FIELD_NUMBER = 2;
        public static final int GET_PORT_RESPONSE_FIELD_NUMBER = 3;
        public static final int GET_UCAR_CONFIG_REQUEST_FIELD_NUMBER = 24;
        public static final int GET_UCAR_CONFIG_RESPONSE_FIELD_NUMBER = 25;
        public static final int HEARTBEAT_FIELD_NUMBER = 1;
        public static final int NOTIFY_ADD_CAMERA_FIELD_NUMBER = 18;
        public static final int NOTIFY_AUDIO_PLAYER_STATE_FIELD_NUMBER = 8;
        public static final int NOTIFY_CALL_HUNG_UP_FIELD_NUMBER = 14;
        public static final int NOTIFY_CAR_TO_BACKGROUND_FIELD_NUMBER = 5;
        public static final int NOTIFY_CAR_TO_FOREGROUND_FIELD_NUMBER = 4;
        public static final int NOTIFY_MICROPHONE_STATE_FIELD_NUMBER = 6;
        public static final int NOTIFY_MIRROR_STATE_FIELD_NUMBER = 7;
        public static final int NOTIFY_MUSIC_INFO_FIELD_NUMBER = 10;
        public static final int NOTIFY_NAVIGATION_INFO_FIELD_NUMBER = 11;
        public static final int NOTIFY_PHONE_STATE_FIELD_NUMBER = 9;
        public static final int NOTIFY_REMOVE_CAMERA_FIELD_NUMBER = 19;
        public static final int NOTIFY_SWITCH_DAY_OR_NIGHT_FIELD_NUMBER = 15;
        private static volatile Parser<ControlIndex> PARSER = null;
        public static final int SET_CAMERA_STATE_FIELD_NUMBER = 20;
        public static final int VR_CMD_TO_PHONE_FIELD_NUMBER = 13;
        private AudioPlayerControl audioPlayerControl_;
        private AwakenVoiceAssistant awakenVoiceAssistant_;
        private BluetoothMacInfo bluetoothMac_;
        private NotifyCameraStateChanged cameraState_;
        private CustomKeyEvent customKeyEvent_;
        private Disconnect disconnect_;
        private GetPortRequest getPortRequest_;
        private GetPortResponse getPortResponse_;
        private GetUCarConfigRequest getUcarConfigRequest_;
        private GetUCarConfigResponse getUcarConfigResponse_;
        private Heartbeat heartbeat_;
        private NotifyAddCamera notifyAddCamera_;
        private NotifyAudioPlayerState notifyAudioPlayerState_;
        private NotifyCallHungUp notifyCallHungUp_;
        private NotifyCarToBackground notifyCarToBackground_;
        private NotifyCarToForeground notifyCarToForeground_;
        private NotifyMicrophoneState notifyMicrophoneState_;
        private NotifyMirrorState notifyMirrorState_;
        private NotifyMusicInfo notifyMusicInfo_;
        private NotifyNavigationInfo notifyNavigationInfo_;
        private NotifyPhoneState notifyPhoneState_;
        private NotifyRemoveCamera notifyRemoveCamera_;
        private NotifySwitchDayOrNight notifySwitchDayOrNight_;
        private SetCameraState setCameraState_;
        private VRCmdToPhone vrCmdToPhone_;

        public static final class Builder extends GeneratedMessageLite.Builder<ControlIndex, Builder> implements ControlIndexOrBuilder {
            private Builder() {
                super(ControlIndex.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearAudioPlayerControl() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearAudioPlayerControl();
                return this;
            }

            public Builder clearAwakenVoiceAssistant() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearAwakenVoiceAssistant();
                return this;
            }

            public Builder clearBluetoothMac() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearBluetoothMac();
                return this;
            }

            public Builder clearCameraState() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearCameraState();
                return this;
            }

            public Builder clearCustomKeyEvent() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearCustomKeyEvent();
                return this;
            }

            public Builder clearDisconnect() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearDisconnect();
                return this;
            }

            public Builder clearGetPortRequest() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearGetPortRequest();
                return this;
            }

            public Builder clearGetPortResponse() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearGetPortResponse();
                return this;
            }

            public Builder clearGetUcarConfigRequest() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearGetUcarConfigRequest();
                return this;
            }

            public Builder clearGetUcarConfigResponse() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearGetUcarConfigResponse();
                return this;
            }

            public Builder clearHeartbeat() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearHeartbeat();
                return this;
            }

            public Builder clearNotifyAddCamera() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearNotifyAddCamera();
                return this;
            }

            public Builder clearNotifyAudioPlayerState() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearNotifyAudioPlayerState();
                return this;
            }

            public Builder clearNotifyCallHungUp() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearNotifyCallHungUp();
                return this;
            }

            public Builder clearNotifyCarToBackground() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearNotifyCarToBackground();
                return this;
            }

            public Builder clearNotifyCarToForeground() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearNotifyCarToForeground();
                return this;
            }

            public Builder clearNotifyMicrophoneState() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearNotifyMicrophoneState();
                return this;
            }

            public Builder clearNotifyMirrorState() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearNotifyMirrorState();
                return this;
            }

            public Builder clearNotifyMusicInfo() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearNotifyMusicInfo();
                return this;
            }

            public Builder clearNotifyNavigationInfo() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearNotifyNavigationInfo();
                return this;
            }

            public Builder clearNotifyPhoneState() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearNotifyPhoneState();
                return this;
            }

            public Builder clearNotifyRemoveCamera() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearNotifyRemoveCamera();
                return this;
            }

            public Builder clearNotifySwitchDayOrNight() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearNotifySwitchDayOrNight();
                return this;
            }

            public Builder clearSetCameraState() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearSetCameraState();
                return this;
            }

            public Builder clearVrCmdToPhone() {
                copyOnWrite();
                ((ControlIndex) this.instance).clearVrCmdToPhone();
                return this;
            }

            @Override
            public AudioPlayerControl getAudioPlayerControl() {
                return ((ControlIndex) this.instance).getAudioPlayerControl();
            }

            @Override
            public AwakenVoiceAssistant getAwakenVoiceAssistant() {
                return ((ControlIndex) this.instance).getAwakenVoiceAssistant();
            }

            @Override
            public BluetoothMacInfo getBluetoothMac() {
                return ((ControlIndex) this.instance).getBluetoothMac();
            }

            @Override
            public NotifyCameraStateChanged getCameraState() {
                return ((ControlIndex) this.instance).getCameraState();
            }

            @Override
            public CustomKeyEvent getCustomKeyEvent() {
                return ((ControlIndex) this.instance).getCustomKeyEvent();
            }

            @Override
            public Disconnect getDisconnect() {
                return ((ControlIndex) this.instance).getDisconnect();
            }

            @Override
            public GetPortRequest getGetPortRequest() {
                return ((ControlIndex) this.instance).getGetPortRequest();
            }

            @Override
            public GetPortResponse getGetPortResponse() {
                return ((ControlIndex) this.instance).getGetPortResponse();
            }

            @Override
            public GetUCarConfigRequest getGetUcarConfigRequest() {
                return ((ControlIndex) this.instance).getGetUcarConfigRequest();
            }

            @Override
            public GetUCarConfigResponse getGetUcarConfigResponse() {
                return ((ControlIndex) this.instance).getGetUcarConfigResponse();
            }

            @Override
            public Heartbeat getHeartbeat() {
                return ((ControlIndex) this.instance).getHeartbeat();
            }

            @Override
            public NotifyAddCamera getNotifyAddCamera() {
                return ((ControlIndex) this.instance).getNotifyAddCamera();
            }

            @Override
            public NotifyAudioPlayerState getNotifyAudioPlayerState() {
                return ((ControlIndex) this.instance).getNotifyAudioPlayerState();
            }

            @Override
            public NotifyCallHungUp getNotifyCallHungUp() {
                return ((ControlIndex) this.instance).getNotifyCallHungUp();
            }

            @Override
            public NotifyCarToBackground getNotifyCarToBackground() {
                return ((ControlIndex) this.instance).getNotifyCarToBackground();
            }

            @Override
            public NotifyCarToForeground getNotifyCarToForeground() {
                return ((ControlIndex) this.instance).getNotifyCarToForeground();
            }

            @Override
            public NotifyMicrophoneState getNotifyMicrophoneState() {
                return ((ControlIndex) this.instance).getNotifyMicrophoneState();
            }

            @Override
            public NotifyMirrorState getNotifyMirrorState() {
                return ((ControlIndex) this.instance).getNotifyMirrorState();
            }

            @Override
            public NotifyMusicInfo getNotifyMusicInfo() {
                return ((ControlIndex) this.instance).getNotifyMusicInfo();
            }

            @Override
            public NotifyNavigationInfo getNotifyNavigationInfo() {
                return ((ControlIndex) this.instance).getNotifyNavigationInfo();
            }

            @Override
            public NotifyPhoneState getNotifyPhoneState() {
                return ((ControlIndex) this.instance).getNotifyPhoneState();
            }

            @Override
            public NotifyRemoveCamera getNotifyRemoveCamera() {
                return ((ControlIndex) this.instance).getNotifyRemoveCamera();
            }

            @Override
            public NotifySwitchDayOrNight getNotifySwitchDayOrNight() {
                return ((ControlIndex) this.instance).getNotifySwitchDayOrNight();
            }

            @Override
            public SetCameraState getSetCameraState() {
                return ((ControlIndex) this.instance).getSetCameraState();
            }

            @Override
            public VRCmdToPhone getVrCmdToPhone() {
                return ((ControlIndex) this.instance).getVrCmdToPhone();
            }

            @Override
            public boolean hasAudioPlayerControl() {
                return ((ControlIndex) this.instance).hasAudioPlayerControl();
            }

            @Override
            public boolean hasAwakenVoiceAssistant() {
                return ((ControlIndex) this.instance).hasAwakenVoiceAssistant();
            }

            @Override
            public boolean hasBluetoothMac() {
                return ((ControlIndex) this.instance).hasBluetoothMac();
            }

            @Override
            public boolean hasCameraState() {
                return ((ControlIndex) this.instance).hasCameraState();
            }

            @Override
            public boolean hasCustomKeyEvent() {
                return ((ControlIndex) this.instance).hasCustomKeyEvent();
            }

            @Override
            public boolean hasDisconnect() {
                return ((ControlIndex) this.instance).hasDisconnect();
            }

            @Override
            public boolean hasGetPortRequest() {
                return ((ControlIndex) this.instance).hasGetPortRequest();
            }

            @Override
            public boolean hasGetPortResponse() {
                return ((ControlIndex) this.instance).hasGetPortResponse();
            }

            @Override
            public boolean hasGetUcarConfigRequest() {
                return ((ControlIndex) this.instance).hasGetUcarConfigRequest();
            }

            @Override
            public boolean hasGetUcarConfigResponse() {
                return ((ControlIndex) this.instance).hasGetUcarConfigResponse();
            }

            @Override
            public boolean hasHeartbeat() {
                return ((ControlIndex) this.instance).hasHeartbeat();
            }

            @Override
            public boolean hasNotifyAddCamera() {
                return ((ControlIndex) this.instance).hasNotifyAddCamera();
            }

            @Override
            public boolean hasNotifyAudioPlayerState() {
                return ((ControlIndex) this.instance).hasNotifyAudioPlayerState();
            }

            @Override
            public boolean hasNotifyCallHungUp() {
                return ((ControlIndex) this.instance).hasNotifyCallHungUp();
            }

            @Override
            public boolean hasNotifyCarToBackground() {
                return ((ControlIndex) this.instance).hasNotifyCarToBackground();
            }

            @Override
            public boolean hasNotifyCarToForeground() {
                return ((ControlIndex) this.instance).hasNotifyCarToForeground();
            }

            @Override
            public boolean hasNotifyMicrophoneState() {
                return ((ControlIndex) this.instance).hasNotifyMicrophoneState();
            }

            @Override
            public boolean hasNotifyMirrorState() {
                return ((ControlIndex) this.instance).hasNotifyMirrorState();
            }

            @Override
            public boolean hasNotifyMusicInfo() {
                return ((ControlIndex) this.instance).hasNotifyMusicInfo();
            }

            @Override
            public boolean hasNotifyNavigationInfo() {
                return ((ControlIndex) this.instance).hasNotifyNavigationInfo();
            }

            @Override
            public boolean hasNotifyPhoneState() {
                return ((ControlIndex) this.instance).hasNotifyPhoneState();
            }

            @Override
            public boolean hasNotifyRemoveCamera() {
                return ((ControlIndex) this.instance).hasNotifyRemoveCamera();
            }

            @Override
            public boolean hasNotifySwitchDayOrNight() {
                return ((ControlIndex) this.instance).hasNotifySwitchDayOrNight();
            }

            @Override
            public boolean hasSetCameraState() {
                return ((ControlIndex) this.instance).hasSetCameraState();
            }

            @Override
            public boolean hasVrCmdToPhone() {
                return ((ControlIndex) this.instance).hasVrCmdToPhone();
            }

            public Builder mergeAudioPlayerControl(AudioPlayerControl audioPlayerControl) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeAudioPlayerControl(audioPlayerControl);
                return this;
            }

            public Builder mergeAwakenVoiceAssistant(AwakenVoiceAssistant awakenVoiceAssistant) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeAwakenVoiceAssistant(awakenVoiceAssistant);
                return this;
            }

            public Builder mergeBluetoothMac(BluetoothMacInfo bluetoothMacInfo) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeBluetoothMac(bluetoothMacInfo);
                return this;
            }

            public Builder mergeCameraState(NotifyCameraStateChanged notifyCameraStateChanged) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeCameraState(notifyCameraStateChanged);
                return this;
            }

            public Builder mergeCustomKeyEvent(CustomKeyEvent customKeyEvent) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeCustomKeyEvent(customKeyEvent);
                return this;
            }

            public Builder mergeDisconnect(Disconnect disconnect) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeDisconnect(disconnect);
                return this;
            }

            public Builder mergeGetPortRequest(GetPortRequest getPortRequest) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeGetPortRequest(getPortRequest);
                return this;
            }

            public Builder mergeGetPortResponse(GetPortResponse getPortResponse) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeGetPortResponse(getPortResponse);
                return this;
            }

            public Builder mergeGetUcarConfigRequest(GetUCarConfigRequest getUCarConfigRequest) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeGetUcarConfigRequest(getUCarConfigRequest);
                return this;
            }

            public Builder mergeGetUcarConfigResponse(GetUCarConfigResponse getUCarConfigResponse) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeGetUcarConfigResponse(getUCarConfigResponse);
                return this;
            }

            public Builder mergeHeartbeat(Heartbeat heartbeat) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeHeartbeat(heartbeat);
                return this;
            }

            public Builder mergeNotifyAddCamera(NotifyAddCamera notifyAddCamera) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeNotifyAddCamera(notifyAddCamera);
                return this;
            }

            public Builder mergeNotifyAudioPlayerState(NotifyAudioPlayerState notifyAudioPlayerState) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeNotifyAudioPlayerState(notifyAudioPlayerState);
                return this;
            }

            public Builder mergeNotifyCallHungUp(NotifyCallHungUp notifyCallHungUp) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeNotifyCallHungUp(notifyCallHungUp);
                return this;
            }

            public Builder mergeNotifyCarToBackground(NotifyCarToBackground notifyCarToBackground) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeNotifyCarToBackground(notifyCarToBackground);
                return this;
            }

            public Builder mergeNotifyCarToForeground(NotifyCarToForeground notifyCarToForeground) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeNotifyCarToForeground(notifyCarToForeground);
                return this;
            }

            public Builder mergeNotifyMicrophoneState(NotifyMicrophoneState notifyMicrophoneState) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeNotifyMicrophoneState(notifyMicrophoneState);
                return this;
            }

            public Builder mergeNotifyMirrorState(NotifyMirrorState notifyMirrorState) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeNotifyMirrorState(notifyMirrorState);
                return this;
            }

            public Builder mergeNotifyMusicInfo(NotifyMusicInfo notifyMusicInfo) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeNotifyMusicInfo(notifyMusicInfo);
                return this;
            }

            public Builder mergeNotifyNavigationInfo(NotifyNavigationInfo notifyNavigationInfo) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeNotifyNavigationInfo(notifyNavigationInfo);
                return this;
            }

            public Builder mergeNotifyPhoneState(NotifyPhoneState notifyPhoneState) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeNotifyPhoneState(notifyPhoneState);
                return this;
            }

            public Builder mergeNotifyRemoveCamera(NotifyRemoveCamera notifyRemoveCamera) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeNotifyRemoveCamera(notifyRemoveCamera);
                return this;
            }

            public Builder mergeNotifySwitchDayOrNight(NotifySwitchDayOrNight notifySwitchDayOrNight) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeNotifySwitchDayOrNight(notifySwitchDayOrNight);
                return this;
            }

            public Builder mergeSetCameraState(SetCameraState setCameraState) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeSetCameraState(setCameraState);
                return this;
            }

            public Builder mergeVrCmdToPhone(VRCmdToPhone vRCmdToPhone) {
                copyOnWrite();
                ((ControlIndex) this.instance).mergeVrCmdToPhone(vRCmdToPhone);
                return this;
            }

            public Builder setAudioPlayerControl(AudioPlayerControl.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setAudioPlayerControl(builder.build());
                return this;
            }

            public Builder setAudioPlayerControl(AudioPlayerControl audioPlayerControl) {
                copyOnWrite();
                ((ControlIndex) this.instance).setAudioPlayerControl(audioPlayerControl);
                return this;
            }

            public Builder setAwakenVoiceAssistant(AwakenVoiceAssistant.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setAwakenVoiceAssistant(builder.build());
                return this;
            }

            public Builder setAwakenVoiceAssistant(AwakenVoiceAssistant awakenVoiceAssistant) {
                copyOnWrite();
                ((ControlIndex) this.instance).setAwakenVoiceAssistant(awakenVoiceAssistant);
                return this;
            }

            public Builder setBluetoothMac(BluetoothMacInfo.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setBluetoothMac(builder.build());
                return this;
            }

            public Builder setBluetoothMac(BluetoothMacInfo bluetoothMacInfo) {
                copyOnWrite();
                ((ControlIndex) this.instance).setBluetoothMac(bluetoothMacInfo);
                return this;
            }

            public Builder setCameraState(NotifyCameraStateChanged.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setCameraState(builder.build());
                return this;
            }

            public Builder setCameraState(NotifyCameraStateChanged notifyCameraStateChanged) {
                copyOnWrite();
                ((ControlIndex) this.instance).setCameraState(notifyCameraStateChanged);
                return this;
            }

            public Builder setCustomKeyEvent(CustomKeyEvent.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setCustomKeyEvent(builder.build());
                return this;
            }

            public Builder setCustomKeyEvent(CustomKeyEvent customKeyEvent) {
                copyOnWrite();
                ((ControlIndex) this.instance).setCustomKeyEvent(customKeyEvent);
                return this;
            }

            public Builder setDisconnect(Disconnect.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setDisconnect(builder.build());
                return this;
            }

            public Builder setDisconnect(Disconnect disconnect) {
                copyOnWrite();
                ((ControlIndex) this.instance).setDisconnect(disconnect);
                return this;
            }

            public Builder setGetPortRequest(GetPortRequest.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setGetPortRequest(builder.build());
                return this;
            }

            public Builder setGetPortRequest(GetPortRequest getPortRequest) {
                copyOnWrite();
                ((ControlIndex) this.instance).setGetPortRequest(getPortRequest);
                return this;
            }

            public Builder setGetPortResponse(GetPortResponse.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setGetPortResponse(builder.build());
                return this;
            }

            public Builder setGetPortResponse(GetPortResponse getPortResponse) {
                copyOnWrite();
                ((ControlIndex) this.instance).setGetPortResponse(getPortResponse);
                return this;
            }

            public Builder setGetUcarConfigRequest(GetUCarConfigRequest.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setGetUcarConfigRequest(builder.build());
                return this;
            }

            public Builder setGetUcarConfigRequest(GetUCarConfigRequest getUCarConfigRequest) {
                copyOnWrite();
                ((ControlIndex) this.instance).setGetUcarConfigRequest(getUCarConfigRequest);
                return this;
            }

            public Builder setGetUcarConfigResponse(GetUCarConfigResponse.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setGetUcarConfigResponse(builder.build());
                return this;
            }

            public Builder setGetUcarConfigResponse(GetUCarConfigResponse getUCarConfigResponse) {
                copyOnWrite();
                ((ControlIndex) this.instance).setGetUcarConfigResponse(getUCarConfigResponse);
                return this;
            }

            public Builder setHeartbeat(Heartbeat.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setHeartbeat(builder.build());
                return this;
            }

            public Builder setHeartbeat(Heartbeat heartbeat) {
                copyOnWrite();
                ((ControlIndex) this.instance).setHeartbeat(heartbeat);
                return this;
            }

            public Builder setNotifyAddCamera(NotifyAddCamera.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyAddCamera(builder.build());
                return this;
            }

            public Builder setNotifyAddCamera(NotifyAddCamera notifyAddCamera) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyAddCamera(notifyAddCamera);
                return this;
            }

            public Builder setNotifyAudioPlayerState(NotifyAudioPlayerState.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyAudioPlayerState(builder.build());
                return this;
            }

            public Builder setNotifyAudioPlayerState(NotifyAudioPlayerState notifyAudioPlayerState) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyAudioPlayerState(notifyAudioPlayerState);
                return this;
            }

            public Builder setNotifyCallHungUp(NotifyCallHungUp.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyCallHungUp(builder.build());
                return this;
            }

            public Builder setNotifyCallHungUp(NotifyCallHungUp notifyCallHungUp) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyCallHungUp(notifyCallHungUp);
                return this;
            }

            public Builder setNotifyCarToBackground(NotifyCarToBackground.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyCarToBackground(builder.build());
                return this;
            }

            public Builder setNotifyCarToBackground(NotifyCarToBackground notifyCarToBackground) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyCarToBackground(notifyCarToBackground);
                return this;
            }

            public Builder setNotifyCarToForeground(NotifyCarToForeground.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyCarToForeground(builder.build());
                return this;
            }

            public Builder setNotifyCarToForeground(NotifyCarToForeground notifyCarToForeground) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyCarToForeground(notifyCarToForeground);
                return this;
            }

            public Builder setNotifyMicrophoneState(NotifyMicrophoneState.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyMicrophoneState(builder.build());
                return this;
            }

            public Builder setNotifyMicrophoneState(NotifyMicrophoneState notifyMicrophoneState) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyMicrophoneState(notifyMicrophoneState);
                return this;
            }

            public Builder setNotifyMirrorState(NotifyMirrorState.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyMirrorState(builder.build());
                return this;
            }

            public Builder setNotifyMirrorState(NotifyMirrorState notifyMirrorState) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyMirrorState(notifyMirrorState);
                return this;
            }

            public Builder setNotifyMusicInfo(NotifyMusicInfo.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyMusicInfo(builder.build());
                return this;
            }

            public Builder setNotifyMusicInfo(NotifyMusicInfo notifyMusicInfo) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyMusicInfo(notifyMusicInfo);
                return this;
            }

            public Builder setNotifyNavigationInfo(NotifyNavigationInfo.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyNavigationInfo(builder.build());
                return this;
            }

            public Builder setNotifyNavigationInfo(NotifyNavigationInfo notifyNavigationInfo) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyNavigationInfo(notifyNavigationInfo);
                return this;
            }

            public Builder setNotifyPhoneState(NotifyPhoneState.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyPhoneState(builder.build());
                return this;
            }

            public Builder setNotifyPhoneState(NotifyPhoneState notifyPhoneState) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyPhoneState(notifyPhoneState);
                return this;
            }

            public Builder setNotifyRemoveCamera(NotifyRemoveCamera.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyRemoveCamera(builder.build());
                return this;
            }

            public Builder setNotifyRemoveCamera(NotifyRemoveCamera notifyRemoveCamera) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifyRemoveCamera(notifyRemoveCamera);
                return this;
            }

            public Builder setNotifySwitchDayOrNight(NotifySwitchDayOrNight.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifySwitchDayOrNight(builder.build());
                return this;
            }

            public Builder setNotifySwitchDayOrNight(NotifySwitchDayOrNight notifySwitchDayOrNight) {
                copyOnWrite();
                ((ControlIndex) this.instance).setNotifySwitchDayOrNight(notifySwitchDayOrNight);
                return this;
            }

            public Builder setSetCameraState(SetCameraState.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setSetCameraState(builder.build());
                return this;
            }

            public Builder setSetCameraState(SetCameraState setCameraState) {
                copyOnWrite();
                ((ControlIndex) this.instance).setSetCameraState(setCameraState);
                return this;
            }

            public Builder setVrCmdToPhone(VRCmdToPhone.Builder builder) {
                copyOnWrite();
                ((ControlIndex) this.instance).setVrCmdToPhone(builder.build());
                return this;
            }

            public Builder setVrCmdToPhone(VRCmdToPhone vRCmdToPhone) {
                copyOnWrite();
                ((ControlIndex) this.instance).setVrCmdToPhone(vRCmdToPhone);
                return this;
            }
        }

        static {
            ControlIndex controlIndex = new ControlIndex();
            DEFAULT_INSTANCE = controlIndex;
            GeneratedMessageLite.registerDefaultInstance(ControlIndex.class, controlIndex);
        }

        private ControlIndex() {
        }

        public void clearAudioPlayerControl() {
            this.audioPlayerControl_ = null;
        }

        public void clearAwakenVoiceAssistant() {
            this.awakenVoiceAssistant_ = null;
        }

        public void clearBluetoothMac() {
            this.bluetoothMac_ = null;
        }

        public void clearCameraState() {
            this.cameraState_ = null;
        }

        public void clearCustomKeyEvent() {
            this.customKeyEvent_ = null;
        }

        public void clearDisconnect() {
            this.disconnect_ = null;
        }

        public void clearGetPortRequest() {
            this.getPortRequest_ = null;
        }

        public void clearGetPortResponse() {
            this.getPortResponse_ = null;
        }

        public void clearGetUcarConfigRequest() {
            this.getUcarConfigRequest_ = null;
        }

        public void clearGetUcarConfigResponse() {
            this.getUcarConfigResponse_ = null;
        }

        public void clearHeartbeat() {
            this.heartbeat_ = null;
        }

        public void clearNotifyAddCamera() {
            this.notifyAddCamera_ = null;
        }

        public void clearNotifyAudioPlayerState() {
            this.notifyAudioPlayerState_ = null;
        }

        public void clearNotifyCallHungUp() {
            this.notifyCallHungUp_ = null;
        }

        public void clearNotifyCarToBackground() {
            this.notifyCarToBackground_ = null;
        }

        public void clearNotifyCarToForeground() {
            this.notifyCarToForeground_ = null;
        }

        public void clearNotifyMicrophoneState() {
            this.notifyMicrophoneState_ = null;
        }

        public void clearNotifyMirrorState() {
            this.notifyMirrorState_ = null;
        }

        public void clearNotifyMusicInfo() {
            this.notifyMusicInfo_ = null;
        }

        public void clearNotifyNavigationInfo() {
            this.notifyNavigationInfo_ = null;
        }

        public void clearNotifyPhoneState() {
            this.notifyPhoneState_ = null;
        }

        public void clearNotifyRemoveCamera() {
            this.notifyRemoveCamera_ = null;
        }

        public void clearNotifySwitchDayOrNight() {
            this.notifySwitchDayOrNight_ = null;
        }

        public void clearSetCameraState() {
            this.setCameraState_ = null;
        }

        public void clearVrCmdToPhone() {
            this.vrCmdToPhone_ = null;
        }

        public static ControlIndex getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public void mergeAudioPlayerControl(AudioPlayerControl audioPlayerControl) {
            audioPlayerControl.getClass();
            AudioPlayerControl audioPlayerControl2 = this.audioPlayerControl_;
            if (audioPlayerControl2 != null && audioPlayerControl2 != AudioPlayerControl.getDefaultInstance()) {
                audioPlayerControl = AudioPlayerControl.newBuilder(this.audioPlayerControl_).mergeFrom(audioPlayerControl).buildPartial();
            }
            this.audioPlayerControl_ = audioPlayerControl;
        }

        public void mergeAwakenVoiceAssistant(AwakenVoiceAssistant awakenVoiceAssistant) {
            awakenVoiceAssistant.getClass();
            AwakenVoiceAssistant awakenVoiceAssistant2 = this.awakenVoiceAssistant_;
            if (awakenVoiceAssistant2 != null && awakenVoiceAssistant2 != AwakenVoiceAssistant.getDefaultInstance()) {
                awakenVoiceAssistant = AwakenVoiceAssistant.newBuilder(this.awakenVoiceAssistant_).mergeFrom(awakenVoiceAssistant).buildPartial();
            }
            this.awakenVoiceAssistant_ = awakenVoiceAssistant;
        }

        public void mergeBluetoothMac(BluetoothMacInfo bluetoothMacInfo) {
            bluetoothMacInfo.getClass();
            BluetoothMacInfo bluetoothMacInfo2 = this.bluetoothMac_;
            if (bluetoothMacInfo2 != null && bluetoothMacInfo2 != BluetoothMacInfo.getDefaultInstance()) {
                bluetoothMacInfo = BluetoothMacInfo.newBuilder(this.bluetoothMac_).mergeFrom(bluetoothMacInfo).buildPartial();
            }
            this.bluetoothMac_ = bluetoothMacInfo;
        }

        public void mergeCameraState(NotifyCameraStateChanged notifyCameraStateChanged) {
            notifyCameraStateChanged.getClass();
            NotifyCameraStateChanged notifyCameraStateChanged2 = this.cameraState_;
            if (notifyCameraStateChanged2 != null && notifyCameraStateChanged2 != NotifyCameraStateChanged.getDefaultInstance()) {
                notifyCameraStateChanged = NotifyCameraStateChanged.newBuilder(this.cameraState_).mergeFrom(notifyCameraStateChanged).buildPartial();
            }
            this.cameraState_ = notifyCameraStateChanged;
        }

        public void mergeCustomKeyEvent(CustomKeyEvent customKeyEvent) {
            customKeyEvent.getClass();
            CustomKeyEvent customKeyEvent2 = this.customKeyEvent_;
            if (customKeyEvent2 != null && customKeyEvent2 != CustomKeyEvent.getDefaultInstance()) {
                customKeyEvent = CustomKeyEvent.newBuilder(this.customKeyEvent_).mergeFrom(customKeyEvent).buildPartial();
            }
            this.customKeyEvent_ = customKeyEvent;
        }

        public void mergeDisconnect(Disconnect disconnect) {
            disconnect.getClass();
            Disconnect disconnect2 = this.disconnect_;
            if (disconnect2 != null && disconnect2 != Disconnect.getDefaultInstance()) {
                disconnect = Disconnect.newBuilder(this.disconnect_).mergeFrom(disconnect).buildPartial();
            }
            this.disconnect_ = disconnect;
        }

        public void mergeGetPortRequest(GetPortRequest getPortRequest) {
            getPortRequest.getClass();
            GetPortRequest getPortRequest2 = this.getPortRequest_;
            if (getPortRequest2 != null && getPortRequest2 != GetPortRequest.getDefaultInstance()) {
                getPortRequest = GetPortRequest.newBuilder(this.getPortRequest_).mergeFrom(getPortRequest).buildPartial();
            }
            this.getPortRequest_ = getPortRequest;
        }

        public void mergeGetPortResponse(GetPortResponse getPortResponse) {
            getPortResponse.getClass();
            GetPortResponse getPortResponse2 = this.getPortResponse_;
            if (getPortResponse2 != null && getPortResponse2 != GetPortResponse.getDefaultInstance()) {
                getPortResponse = GetPortResponse.newBuilder(this.getPortResponse_).mergeFrom(getPortResponse).buildPartial();
            }
            this.getPortResponse_ = getPortResponse;
        }

        public void mergeGetUcarConfigRequest(GetUCarConfigRequest getUCarConfigRequest) {
            getUCarConfigRequest.getClass();
            GetUCarConfigRequest getUCarConfigRequest2 = this.getUcarConfigRequest_;
            if (getUCarConfigRequest2 != null && getUCarConfigRequest2 != GetUCarConfigRequest.getDefaultInstance()) {
                getUCarConfigRequest = GetUCarConfigRequest.newBuilder(this.getUcarConfigRequest_).mergeFrom(getUCarConfigRequest).buildPartial();
            }
            this.getUcarConfigRequest_ = getUCarConfigRequest;
        }

        public void mergeGetUcarConfigResponse(GetUCarConfigResponse getUCarConfigResponse) {
            getUCarConfigResponse.getClass();
            GetUCarConfigResponse getUCarConfigResponse2 = this.getUcarConfigResponse_;
            if (getUCarConfigResponse2 != null && getUCarConfigResponse2 != GetUCarConfigResponse.getDefaultInstance()) {
                getUCarConfigResponse = GetUCarConfigResponse.newBuilder(this.getUcarConfigResponse_).mergeFrom(getUCarConfigResponse).buildPartial();
            }
            this.getUcarConfigResponse_ = getUCarConfigResponse;
        }

        public void mergeHeartbeat(Heartbeat heartbeat) {
            heartbeat.getClass();
            Heartbeat heartbeat2 = this.heartbeat_;
            if (heartbeat2 != null && heartbeat2 != Heartbeat.getDefaultInstance()) {
                heartbeat = Heartbeat.newBuilder(this.heartbeat_).mergeFrom(heartbeat).buildPartial();
            }
            this.heartbeat_ = heartbeat;
        }

        public void mergeNotifyAddCamera(NotifyAddCamera notifyAddCamera) {
            notifyAddCamera.getClass();
            NotifyAddCamera notifyAddCamera2 = this.notifyAddCamera_;
            if (notifyAddCamera2 != null && notifyAddCamera2 != NotifyAddCamera.getDefaultInstance()) {
                notifyAddCamera = NotifyAddCamera.newBuilder(this.notifyAddCamera_).mergeFrom(notifyAddCamera).buildPartial();
            }
            this.notifyAddCamera_ = notifyAddCamera;
        }

        public void mergeNotifyAudioPlayerState(NotifyAudioPlayerState notifyAudioPlayerState) {
            notifyAudioPlayerState.getClass();
            NotifyAudioPlayerState notifyAudioPlayerState2 = this.notifyAudioPlayerState_;
            if (notifyAudioPlayerState2 != null && notifyAudioPlayerState2 != NotifyAudioPlayerState.getDefaultInstance()) {
                notifyAudioPlayerState = NotifyAudioPlayerState.newBuilder(this.notifyAudioPlayerState_).mergeFrom(notifyAudioPlayerState).buildPartial();
            }
            this.notifyAudioPlayerState_ = notifyAudioPlayerState;
        }

        public void mergeNotifyCallHungUp(NotifyCallHungUp notifyCallHungUp) {
            notifyCallHungUp.getClass();
            NotifyCallHungUp notifyCallHungUp2 = this.notifyCallHungUp_;
            if (notifyCallHungUp2 != null && notifyCallHungUp2 != NotifyCallHungUp.getDefaultInstance()) {
                notifyCallHungUp = NotifyCallHungUp.newBuilder(this.notifyCallHungUp_).mergeFrom(notifyCallHungUp).buildPartial();
            }
            this.notifyCallHungUp_ = notifyCallHungUp;
        }

        public void mergeNotifyCarToBackground(NotifyCarToBackground notifyCarToBackground) {
            notifyCarToBackground.getClass();
            NotifyCarToBackground notifyCarToBackground2 = this.notifyCarToBackground_;
            if (notifyCarToBackground2 != null && notifyCarToBackground2 != NotifyCarToBackground.getDefaultInstance()) {
                notifyCarToBackground = NotifyCarToBackground.newBuilder(this.notifyCarToBackground_).mergeFrom(notifyCarToBackground).buildPartial();
            }
            this.notifyCarToBackground_ = notifyCarToBackground;
        }

        public void mergeNotifyCarToForeground(NotifyCarToForeground notifyCarToForeground) {
            notifyCarToForeground.getClass();
            NotifyCarToForeground notifyCarToForeground2 = this.notifyCarToForeground_;
            if (notifyCarToForeground2 != null && notifyCarToForeground2 != NotifyCarToForeground.getDefaultInstance()) {
                notifyCarToForeground = NotifyCarToForeground.newBuilder(this.notifyCarToForeground_).mergeFrom(notifyCarToForeground).buildPartial();
            }
            this.notifyCarToForeground_ = notifyCarToForeground;
        }

        public void mergeNotifyMicrophoneState(NotifyMicrophoneState notifyMicrophoneState) {
            notifyMicrophoneState.getClass();
            NotifyMicrophoneState notifyMicrophoneState2 = this.notifyMicrophoneState_;
            if (notifyMicrophoneState2 != null && notifyMicrophoneState2 != NotifyMicrophoneState.getDefaultInstance()) {
                notifyMicrophoneState = NotifyMicrophoneState.newBuilder(this.notifyMicrophoneState_).mergeFrom(notifyMicrophoneState).buildPartial();
            }
            this.notifyMicrophoneState_ = notifyMicrophoneState;
        }

        public void mergeNotifyMirrorState(NotifyMirrorState notifyMirrorState) {
            notifyMirrorState.getClass();
            NotifyMirrorState notifyMirrorState2 = this.notifyMirrorState_;
            if (notifyMirrorState2 != null && notifyMirrorState2 != NotifyMirrorState.getDefaultInstance()) {
                notifyMirrorState = NotifyMirrorState.newBuilder(this.notifyMirrorState_).mergeFrom(notifyMirrorState).buildPartial();
            }
            this.notifyMirrorState_ = notifyMirrorState;
        }

        public void mergeNotifyMusicInfo(NotifyMusicInfo notifyMusicInfo) {
            notifyMusicInfo.getClass();
            NotifyMusicInfo notifyMusicInfo2 = this.notifyMusicInfo_;
            if (notifyMusicInfo2 != null && notifyMusicInfo2 != NotifyMusicInfo.getDefaultInstance()) {
                notifyMusicInfo = NotifyMusicInfo.newBuilder(this.notifyMusicInfo_).mergeFrom(notifyMusicInfo).buildPartial();
            }
            this.notifyMusicInfo_ = notifyMusicInfo;
        }

        public void mergeNotifyNavigationInfo(NotifyNavigationInfo notifyNavigationInfo) {
            notifyNavigationInfo.getClass();
            NotifyNavigationInfo notifyNavigationInfo2 = this.notifyNavigationInfo_;
            if (notifyNavigationInfo2 != null && notifyNavigationInfo2 != NotifyNavigationInfo.getDefaultInstance()) {
                notifyNavigationInfo = NotifyNavigationInfo.newBuilder(this.notifyNavigationInfo_).mergeFrom(notifyNavigationInfo).buildPartial();
            }
            this.notifyNavigationInfo_ = notifyNavigationInfo;
        }

        public void mergeNotifyPhoneState(NotifyPhoneState notifyPhoneState) {
            notifyPhoneState.getClass();
            NotifyPhoneState notifyPhoneState2 = this.notifyPhoneState_;
            if (notifyPhoneState2 != null && notifyPhoneState2 != NotifyPhoneState.getDefaultInstance()) {
                notifyPhoneState = NotifyPhoneState.newBuilder(this.notifyPhoneState_).mergeFrom(notifyPhoneState).buildPartial();
            }
            this.notifyPhoneState_ = notifyPhoneState;
        }

        public void mergeNotifyRemoveCamera(NotifyRemoveCamera notifyRemoveCamera) {
            notifyRemoveCamera.getClass();
            NotifyRemoveCamera notifyRemoveCamera2 = this.notifyRemoveCamera_;
            if (notifyRemoveCamera2 != null && notifyRemoveCamera2 != NotifyRemoveCamera.getDefaultInstance()) {
                notifyRemoveCamera = NotifyRemoveCamera.newBuilder(this.notifyRemoveCamera_).mergeFrom(notifyRemoveCamera).buildPartial();
            }
            this.notifyRemoveCamera_ = notifyRemoveCamera;
        }

        public void mergeNotifySwitchDayOrNight(NotifySwitchDayOrNight notifySwitchDayOrNight) {
            notifySwitchDayOrNight.getClass();
            NotifySwitchDayOrNight notifySwitchDayOrNight2 = this.notifySwitchDayOrNight_;
            if (notifySwitchDayOrNight2 != null && notifySwitchDayOrNight2 != NotifySwitchDayOrNight.getDefaultInstance()) {
                notifySwitchDayOrNight = NotifySwitchDayOrNight.newBuilder(this.notifySwitchDayOrNight_).mergeFrom(notifySwitchDayOrNight).buildPartial();
            }
            this.notifySwitchDayOrNight_ = notifySwitchDayOrNight;
        }

        public void mergeSetCameraState(SetCameraState setCameraState) {
            setCameraState.getClass();
            SetCameraState setCameraState2 = this.setCameraState_;
            if (setCameraState2 != null && setCameraState2 != SetCameraState.getDefaultInstance()) {
                setCameraState = SetCameraState.newBuilder(this.setCameraState_).mergeFrom(setCameraState).buildPartial();
            }
            this.setCameraState_ = setCameraState;
        }

        public void mergeVrCmdToPhone(VRCmdToPhone vRCmdToPhone) {
            vRCmdToPhone.getClass();
            VRCmdToPhone vRCmdToPhone2 = this.vrCmdToPhone_;
            if (vRCmdToPhone2 != null && vRCmdToPhone2 != VRCmdToPhone.getDefaultInstance()) {
                vRCmdToPhone = VRCmdToPhone.newBuilder(this.vrCmdToPhone_).mergeFrom(vRCmdToPhone).buildPartial();
            }
            this.vrCmdToPhone_ = vRCmdToPhone;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(ControlIndex controlIndex) {
            return DEFAULT_INSTANCE.createBuilder(controlIndex);
        }

        public static ControlIndex parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ControlIndex) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ControlIndex parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (ControlIndex) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static ControlIndex parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (ControlIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static ControlIndex parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (ControlIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static ControlIndex parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (ControlIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static ControlIndex parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (ControlIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static ControlIndex parseFrom(InputStream inputStream) throws IOException {
            return (ControlIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ControlIndex parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (ControlIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static ControlIndex parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ControlIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ControlIndex parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (ControlIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static ControlIndex parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ControlIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ControlIndex parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (ControlIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<ControlIndex> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setAudioPlayerControl(AudioPlayerControl audioPlayerControl) {
            audioPlayerControl.getClass();
            this.audioPlayerControl_ = audioPlayerControl;
        }

        public void setAwakenVoiceAssistant(AwakenVoiceAssistant awakenVoiceAssistant) {
            awakenVoiceAssistant.getClass();
            this.awakenVoiceAssistant_ = awakenVoiceAssistant;
        }

        public void setBluetoothMac(BluetoothMacInfo bluetoothMacInfo) {
            bluetoothMacInfo.getClass();
            this.bluetoothMac_ = bluetoothMacInfo;
        }

        public void setCameraState(NotifyCameraStateChanged notifyCameraStateChanged) {
            notifyCameraStateChanged.getClass();
            this.cameraState_ = notifyCameraStateChanged;
        }

        public void setCustomKeyEvent(CustomKeyEvent customKeyEvent) {
            customKeyEvent.getClass();
            this.customKeyEvent_ = customKeyEvent;
        }

        public void setDisconnect(Disconnect disconnect) {
            disconnect.getClass();
            this.disconnect_ = disconnect;
        }

        public void setGetPortRequest(GetPortRequest getPortRequest) {
            getPortRequest.getClass();
            this.getPortRequest_ = getPortRequest;
        }

        public void setGetPortResponse(GetPortResponse getPortResponse) {
            getPortResponse.getClass();
            this.getPortResponse_ = getPortResponse;
        }

        public void setGetUcarConfigRequest(GetUCarConfigRequest getUCarConfigRequest) {
            getUCarConfigRequest.getClass();
            this.getUcarConfigRequest_ = getUCarConfigRequest;
        }

        public void setGetUcarConfigResponse(GetUCarConfigResponse getUCarConfigResponse) {
            getUCarConfigResponse.getClass();
            this.getUcarConfigResponse_ = getUCarConfigResponse;
        }

        public void setHeartbeat(Heartbeat heartbeat) {
            heartbeat.getClass();
            this.heartbeat_ = heartbeat;
        }

        public void setNotifyAddCamera(NotifyAddCamera notifyAddCamera) {
            notifyAddCamera.getClass();
            this.notifyAddCamera_ = notifyAddCamera;
        }

        public void setNotifyAudioPlayerState(NotifyAudioPlayerState notifyAudioPlayerState) {
            notifyAudioPlayerState.getClass();
            this.notifyAudioPlayerState_ = notifyAudioPlayerState;
        }

        public void setNotifyCallHungUp(NotifyCallHungUp notifyCallHungUp) {
            notifyCallHungUp.getClass();
            this.notifyCallHungUp_ = notifyCallHungUp;
        }

        public void setNotifyCarToBackground(NotifyCarToBackground notifyCarToBackground) {
            notifyCarToBackground.getClass();
            this.notifyCarToBackground_ = notifyCarToBackground;
        }

        public void setNotifyCarToForeground(NotifyCarToForeground notifyCarToForeground) {
            notifyCarToForeground.getClass();
            this.notifyCarToForeground_ = notifyCarToForeground;
        }

        public void setNotifyMicrophoneState(NotifyMicrophoneState notifyMicrophoneState) {
            notifyMicrophoneState.getClass();
            this.notifyMicrophoneState_ = notifyMicrophoneState;
        }

        public void setNotifyMirrorState(NotifyMirrorState notifyMirrorState) {
            notifyMirrorState.getClass();
            this.notifyMirrorState_ = notifyMirrorState;
        }

        public void setNotifyMusicInfo(NotifyMusicInfo notifyMusicInfo) {
            notifyMusicInfo.getClass();
            this.notifyMusicInfo_ = notifyMusicInfo;
        }

        public void setNotifyNavigationInfo(NotifyNavigationInfo notifyNavigationInfo) {
            notifyNavigationInfo.getClass();
            this.notifyNavigationInfo_ = notifyNavigationInfo;
        }

        public void setNotifyPhoneState(NotifyPhoneState notifyPhoneState) {
            notifyPhoneState.getClass();
            this.notifyPhoneState_ = notifyPhoneState;
        }

        public void setNotifyRemoveCamera(NotifyRemoveCamera notifyRemoveCamera) {
            notifyRemoveCamera.getClass();
            this.notifyRemoveCamera_ = notifyRemoveCamera;
        }

        public void setNotifySwitchDayOrNight(NotifySwitchDayOrNight notifySwitchDayOrNight) {
            notifySwitchDayOrNight.getClass();
            this.notifySwitchDayOrNight_ = notifySwitchDayOrNight;
        }

        public void setSetCameraState(SetCameraState setCameraState) {
            setCameraState.getClass();
            this.setCameraState_ = setCameraState;
        }

        public void setVrCmdToPhone(VRCmdToPhone vRCmdToPhone) {
            vRCmdToPhone.getClass();
            this.vrCmdToPhone_ = vRCmdToPhone;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new ControlIndex();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0019\u0000\u0000\u0001\u0019\u0019\u0000\u0000\u0000\u0001\t\u0002\t\u0003\t\u0004\t\u0005\t\u0006\t\u0007\t\b\t\t\t\n\t\u000b\t\f\t\r\t\u000e\t\u000f\t\u0010\t\u0011\t\u0012\t\u0013\t\u0014\t\u0015\t\u0016\t\u0017\t\u0018\t\u0019\t", new Object[]{"heartbeat_", "getPortRequest_", "getPortResponse_", "notifyCarToForeground_", "notifyCarToBackground_", "notifyMicrophoneState_", "notifyMirrorState_", "notifyAudioPlayerState_", "notifyPhoneState_", "notifyMusicInfo_", "notifyNavigationInfo_", "customKeyEvent_", "vrCmdToPhone_", "notifyCallHungUp_", "notifySwitchDayOrNight_", "awakenVoiceAssistant_", "audioPlayerControl_", "notifyAddCamera_", "notifyRemoveCamera_", "setCameraState_", "cameraState_", "bluetoothMac_", "disconnect_", "getUcarConfigRequest_", "getUcarConfigResponse_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ControlIndex> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (ControlIndex.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public AudioPlayerControl getAudioPlayerControl() {
            AudioPlayerControl audioPlayerControl = this.audioPlayerControl_;
            return audioPlayerControl == null ? AudioPlayerControl.getDefaultInstance() : audioPlayerControl;
        }

        @Override
        public AwakenVoiceAssistant getAwakenVoiceAssistant() {
            AwakenVoiceAssistant awakenVoiceAssistant = this.awakenVoiceAssistant_;
            return awakenVoiceAssistant == null ? AwakenVoiceAssistant.getDefaultInstance() : awakenVoiceAssistant;
        }

        @Override
        public BluetoothMacInfo getBluetoothMac() {
            BluetoothMacInfo bluetoothMacInfo = this.bluetoothMac_;
            return bluetoothMacInfo == null ? BluetoothMacInfo.getDefaultInstance() : bluetoothMacInfo;
        }

        @Override
        public NotifyCameraStateChanged getCameraState() {
            NotifyCameraStateChanged notifyCameraStateChanged = this.cameraState_;
            return notifyCameraStateChanged == null ? NotifyCameraStateChanged.getDefaultInstance() : notifyCameraStateChanged;
        }

        @Override
        public CustomKeyEvent getCustomKeyEvent() {
            CustomKeyEvent customKeyEvent = this.customKeyEvent_;
            return customKeyEvent == null ? CustomKeyEvent.getDefaultInstance() : customKeyEvent;
        }

        @Override
        public Disconnect getDisconnect() {
            Disconnect disconnect = this.disconnect_;
            return disconnect == null ? Disconnect.getDefaultInstance() : disconnect;
        }

        @Override
        public GetPortRequest getGetPortRequest() {
            GetPortRequest getPortRequest = this.getPortRequest_;
            return getPortRequest == null ? GetPortRequest.getDefaultInstance() : getPortRequest;
        }

        @Override
        public GetPortResponse getGetPortResponse() {
            GetPortResponse getPortResponse = this.getPortResponse_;
            return getPortResponse == null ? GetPortResponse.getDefaultInstance() : getPortResponse;
        }

        @Override
        public GetUCarConfigRequest getGetUcarConfigRequest() {
            GetUCarConfigRequest getUCarConfigRequest = this.getUcarConfigRequest_;
            return getUCarConfigRequest == null ? GetUCarConfigRequest.getDefaultInstance() : getUCarConfigRequest;
        }

        @Override
        public GetUCarConfigResponse getGetUcarConfigResponse() {
            GetUCarConfigResponse getUCarConfigResponse = this.getUcarConfigResponse_;
            return getUCarConfigResponse == null ? GetUCarConfigResponse.getDefaultInstance() : getUCarConfigResponse;
        }

        @Override
        public Heartbeat getHeartbeat() {
            Heartbeat heartbeat = this.heartbeat_;
            return heartbeat == null ? Heartbeat.getDefaultInstance() : heartbeat;
        }

        @Override
        public NotifyAddCamera getNotifyAddCamera() {
            NotifyAddCamera notifyAddCamera = this.notifyAddCamera_;
            return notifyAddCamera == null ? NotifyAddCamera.getDefaultInstance() : notifyAddCamera;
        }

        @Override
        public NotifyAudioPlayerState getNotifyAudioPlayerState() {
            NotifyAudioPlayerState notifyAudioPlayerState = this.notifyAudioPlayerState_;
            return notifyAudioPlayerState == null ? NotifyAudioPlayerState.getDefaultInstance() : notifyAudioPlayerState;
        }

        @Override
        public NotifyCallHungUp getNotifyCallHungUp() {
            NotifyCallHungUp notifyCallHungUp = this.notifyCallHungUp_;
            return notifyCallHungUp == null ? NotifyCallHungUp.getDefaultInstance() : notifyCallHungUp;
        }

        @Override
        public NotifyCarToBackground getNotifyCarToBackground() {
            NotifyCarToBackground notifyCarToBackground = this.notifyCarToBackground_;
            return notifyCarToBackground == null ? NotifyCarToBackground.getDefaultInstance() : notifyCarToBackground;
        }

        @Override
        public NotifyCarToForeground getNotifyCarToForeground() {
            NotifyCarToForeground notifyCarToForeground = this.notifyCarToForeground_;
            return notifyCarToForeground == null ? NotifyCarToForeground.getDefaultInstance() : notifyCarToForeground;
        }

        @Override
        public NotifyMicrophoneState getNotifyMicrophoneState() {
            NotifyMicrophoneState notifyMicrophoneState = this.notifyMicrophoneState_;
            return notifyMicrophoneState == null ? NotifyMicrophoneState.getDefaultInstance() : notifyMicrophoneState;
        }

        @Override
        public NotifyMirrorState getNotifyMirrorState() {
            NotifyMirrorState notifyMirrorState = this.notifyMirrorState_;
            return notifyMirrorState == null ? NotifyMirrorState.getDefaultInstance() : notifyMirrorState;
        }

        @Override
        public NotifyMusicInfo getNotifyMusicInfo() {
            NotifyMusicInfo notifyMusicInfo = this.notifyMusicInfo_;
            return notifyMusicInfo == null ? NotifyMusicInfo.getDefaultInstance() : notifyMusicInfo;
        }

        @Override
        public NotifyNavigationInfo getNotifyNavigationInfo() {
            NotifyNavigationInfo notifyNavigationInfo = this.notifyNavigationInfo_;
            return notifyNavigationInfo == null ? NotifyNavigationInfo.getDefaultInstance() : notifyNavigationInfo;
        }

        @Override
        public NotifyPhoneState getNotifyPhoneState() {
            NotifyPhoneState notifyPhoneState = this.notifyPhoneState_;
            return notifyPhoneState == null ? NotifyPhoneState.getDefaultInstance() : notifyPhoneState;
        }

        @Override
        public NotifyRemoveCamera getNotifyRemoveCamera() {
            NotifyRemoveCamera notifyRemoveCamera = this.notifyRemoveCamera_;
            return notifyRemoveCamera == null ? NotifyRemoveCamera.getDefaultInstance() : notifyRemoveCamera;
        }

        @Override
        public NotifySwitchDayOrNight getNotifySwitchDayOrNight() {
            NotifySwitchDayOrNight notifySwitchDayOrNight = this.notifySwitchDayOrNight_;
            return notifySwitchDayOrNight == null ? NotifySwitchDayOrNight.getDefaultInstance() : notifySwitchDayOrNight;
        }

        @Override
        public SetCameraState getSetCameraState() {
            SetCameraState setCameraState = this.setCameraState_;
            return setCameraState == null ? SetCameraState.getDefaultInstance() : setCameraState;
        }

        @Override
        public VRCmdToPhone getVrCmdToPhone() {
            VRCmdToPhone vRCmdToPhone = this.vrCmdToPhone_;
            return vRCmdToPhone == null ? VRCmdToPhone.getDefaultInstance() : vRCmdToPhone;
        }

        @Override
        public boolean hasAudioPlayerControl() {
            return this.audioPlayerControl_ != null;
        }

        @Override
        public boolean hasAwakenVoiceAssistant() {
            return this.awakenVoiceAssistant_ != null;
        }

        @Override
        public boolean hasBluetoothMac() {
            return this.bluetoothMac_ != null;
        }

        @Override
        public boolean hasCameraState() {
            return this.cameraState_ != null;
        }

        @Override
        public boolean hasCustomKeyEvent() {
            return this.customKeyEvent_ != null;
        }

        @Override
        public boolean hasDisconnect() {
            return this.disconnect_ != null;
        }

        @Override
        public boolean hasGetPortRequest() {
            return this.getPortRequest_ != null;
        }

        @Override
        public boolean hasGetPortResponse() {
            return this.getPortResponse_ != null;
        }

        @Override
        public boolean hasGetUcarConfigRequest() {
            return this.getUcarConfigRequest_ != null;
        }

        @Override
        public boolean hasGetUcarConfigResponse() {
            return this.getUcarConfigResponse_ != null;
        }

        @Override
        public boolean hasHeartbeat() {
            return this.heartbeat_ != null;
        }

        @Override
        public boolean hasNotifyAddCamera() {
            return this.notifyAddCamera_ != null;
        }

        @Override
        public boolean hasNotifyAudioPlayerState() {
            return this.notifyAudioPlayerState_ != null;
        }

        @Override
        public boolean hasNotifyCallHungUp() {
            return this.notifyCallHungUp_ != null;
        }

        @Override
        public boolean hasNotifyCarToBackground() {
            return this.notifyCarToBackground_ != null;
        }

        @Override
        public boolean hasNotifyCarToForeground() {
            return this.notifyCarToForeground_ != null;
        }

        @Override
        public boolean hasNotifyMicrophoneState() {
            return this.notifyMicrophoneState_ != null;
        }

        @Override
        public boolean hasNotifyMirrorState() {
            return this.notifyMirrorState_ != null;
        }

        @Override
        public boolean hasNotifyMusicInfo() {
            return this.notifyMusicInfo_ != null;
        }

        @Override
        public boolean hasNotifyNavigationInfo() {
            return this.notifyNavigationInfo_ != null;
        }

        @Override
        public boolean hasNotifyPhoneState() {
            return this.notifyPhoneState_ != null;
        }

        @Override
        public boolean hasNotifyRemoveCamera() {
            return this.notifyRemoveCamera_ != null;
        }

        @Override
        public boolean hasNotifySwitchDayOrNight() {
            return this.notifySwitchDayOrNight_ != null;
        }

        @Override
        public boolean hasSetCameraState() {
            return this.setCameraState_ != null;
        }

        @Override
        public boolean hasVrCmdToPhone() {
            return this.vrCmdToPhone_ != null;
        }
    }

    public interface ControlIndexOrBuilder extends MessageLiteOrBuilder {
        AudioPlayerControl getAudioPlayerControl();

        AwakenVoiceAssistant getAwakenVoiceAssistant();

        BluetoothMacInfo getBluetoothMac();

        NotifyCameraStateChanged getCameraState();

        CustomKeyEvent getCustomKeyEvent();

        Disconnect getDisconnect();

        GetPortRequest getGetPortRequest();

        GetPortResponse getGetPortResponse();

        GetUCarConfigRequest getGetUcarConfigRequest();

        GetUCarConfigResponse getGetUcarConfigResponse();

        Heartbeat getHeartbeat();

        NotifyAddCamera getNotifyAddCamera();

        NotifyAudioPlayerState getNotifyAudioPlayerState();

        NotifyCallHungUp getNotifyCallHungUp();

        NotifyCarToBackground getNotifyCarToBackground();

        NotifyCarToForeground getNotifyCarToForeground();

        NotifyMicrophoneState getNotifyMicrophoneState();

        NotifyMirrorState getNotifyMirrorState();

        NotifyMusicInfo getNotifyMusicInfo();

        NotifyNavigationInfo getNotifyNavigationInfo();

        NotifyPhoneState getNotifyPhoneState();

        NotifyRemoveCamera getNotifyRemoveCamera();

        NotifySwitchDayOrNight getNotifySwitchDayOrNight();

        SetCameraState getSetCameraState();

        VRCmdToPhone getVrCmdToPhone();

        boolean hasAudioPlayerControl();

        boolean hasAwakenVoiceAssistant();

        boolean hasBluetoothMac();

        boolean hasCameraState();

        boolean hasCustomKeyEvent();

        boolean hasDisconnect();

        boolean hasGetPortRequest();

        boolean hasGetPortResponse();

        boolean hasGetUcarConfigRequest();

        boolean hasGetUcarConfigResponse();

        boolean hasHeartbeat();

        boolean hasNotifyAddCamera();

        boolean hasNotifyAudioPlayerState();

        boolean hasNotifyCallHungUp();

        boolean hasNotifyCarToBackground();

        boolean hasNotifyCarToForeground();

        boolean hasNotifyMicrophoneState();

        boolean hasNotifyMirrorState();

        boolean hasNotifyMusicInfo();

        boolean hasNotifyNavigationInfo();

        boolean hasNotifyPhoneState();

        boolean hasNotifyRemoveCamera();

        boolean hasNotifySwitchDayOrNight();

        boolean hasSetCameraState();

        boolean hasVrCmdToPhone();
    }

    public static final class CustomKeyEvent extends GeneratedMessageLite<CustomKeyEvent, CustomKeyEvent.Builder> implements CustomKeyEventOrBuilder {
        public static final int ACTION_FIELD_NUMBER = 1;
        private static final CustomKeyEvent DEFAULT_INSTANCE;
        public static final int KEYCODE_FIELD_NUMBER = 2;
        public static final int META_STATE_FIELD_NUMBER = 3;
        private static volatile Parser<CustomKeyEvent> PARSER = null;
        public static final int TIMESTAMP_FIELD_NUMBER = 4;
        private int action_;
        private int keycode_;
        private int metaState_;
        private long timestamp_;

        public static final class Builder extends GeneratedMessageLite.Builder<CustomKeyEvent, Builder> implements CustomKeyEventOrBuilder {
            private Builder() {
                super(CustomKeyEvent.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearAction() {
                copyOnWrite();
                ((CustomKeyEvent) this.instance).clearAction();
                return this;
            }

            public Builder clearKeycode() {
                copyOnWrite();
                ((CustomKeyEvent) this.instance).clearKeycode();
                return this;
            }

            public Builder clearMetaState() {
                copyOnWrite();
                ((CustomKeyEvent) this.instance).clearMetaState();
                return this;
            }

            public Builder clearTimestamp() {
                copyOnWrite();
                ((CustomKeyEvent) this.instance).clearTimestamp();
                return this;
            }

            @Override
            public int getAction() {
                return ((CustomKeyEvent) this.instance).getAction();
            }

            @Override
            public KeyCode getKeycode() {
                return ((CustomKeyEvent) this.instance).getKeycode();
            }

            @Override
            public int getKeycodeValue() {
                return ((CustomKeyEvent) this.instance).getKeycodeValue();
            }

            @Override
            public int getMetaState() {
                return ((CustomKeyEvent) this.instance).getMetaState();
            }

            @Override
            public long getTimestamp() {
                return ((CustomKeyEvent) this.instance).getTimestamp();
            }

            public Builder setAction(int i) {
                copyOnWrite();
                ((CustomKeyEvent) this.instance).setAction(i);
                return this;
            }

            public Builder setKeycode(KeyCode keyCode) {
                copyOnWrite();
                ((CustomKeyEvent) this.instance).setKeycode(keyCode);
                return this;
            }

            public Builder setKeycodeValue(int i) {
                copyOnWrite();
                ((CustomKeyEvent) this.instance).setKeycodeValue(i);
                return this;
            }

            public Builder setMetaState(int i) {
                copyOnWrite();
                ((CustomKeyEvent) this.instance).setMetaState(i);
                return this;
            }

            public Builder setTimestamp(long j) {
                copyOnWrite();
                ((CustomKeyEvent) this.instance).setTimestamp(j);
                return this;
            }
        }

        public enum KeyCode implements Internal.EnumLite {
            KEY_CODE_UNDEFINED(0),
            KEY_CODE_MAIN(8103),
            KEY_CODE_TEL(8104),
            KEY_CODE_NAVI(8105),
            KEY_CODE_MEDIA(8106),
            KEY_CODE_VR_START(8107),
            KEY_CODE_VR_STOP(8108),
            KEY_CODE_NAVI_QUIT(8113),
            KEY_CODE_NEXT_FOCUS(8114),
            KEY_CODE_PRE_FOCUS(8115),
            UNRECOGNIZED(-1);

            public static final int KEY_CODE_MAIN_VALUE = 8103;
            public static final int KEY_CODE_MEDIA_VALUE = 8106;
            public static final int KEY_CODE_NAVI_QUIT_VALUE = 8113;
            public static final int KEY_CODE_NAVI_VALUE = 8105;
            public static final int KEY_CODE_NEXT_FOCUS_VALUE = 8114;
            public static final int KEY_CODE_PRE_FOCUS_VALUE = 8115;
            public static final int KEY_CODE_TEL_VALUE = 8104;
            public static final int KEY_CODE_UNDEFINED_VALUE = 0;
            public static final int KEY_CODE_VR_START_VALUE = 8107;
            public static final int KEY_CODE_VR_STOP_VALUE = 8108;
            private static final Internal.EnumLiteMap<KeyCode> internalValueMap = new Internal.EnumLiteMap<KeyCode>() {
                @Override
                public KeyCode findValueByNumber(int i) {
                    return KeyCode.forNumber(i);
                }
            };
            private final int value;

            public static final class KeyCodeVerifier implements Internal.EnumVerifier {
                public static final Internal.EnumVerifier INSTANCE = new KeyCodeVerifier();

                private KeyCodeVerifier() {
                }

                @Override
                public boolean isInRange(int i) {
                    return KeyCode.forNumber(i) != null;
                }
            }

            KeyCode(int i) {
                this.value = i;
            }

            public static KeyCode forNumber(int i) {
                if (i == 0) {
                    return KEY_CODE_UNDEFINED;
                }
                switch (i) {
                    case KEY_CODE_MAIN_VALUE:
                        return KEY_CODE_MAIN;
                    case KEY_CODE_TEL_VALUE:
                        return KEY_CODE_TEL;
                    case KEY_CODE_NAVI_VALUE:
                        return KEY_CODE_NAVI;
                    case KEY_CODE_MEDIA_VALUE:
                        return KEY_CODE_MEDIA;
                    case KEY_CODE_VR_START_VALUE:
                        return KEY_CODE_VR_START;
                    case KEY_CODE_VR_STOP_VALUE:
                        return KEY_CODE_VR_STOP;
                    default:
                        switch (i) {
                            case KEY_CODE_NAVI_QUIT_VALUE:
                                return KEY_CODE_NAVI_QUIT;
                            case KEY_CODE_NEXT_FOCUS_VALUE:
                                return KEY_CODE_NEXT_FOCUS;
                            case KEY_CODE_PRE_FOCUS_VALUE:
                                return KEY_CODE_PRE_FOCUS;
                            default:
                                return null;
                        }
                }
            }

            public static Internal.EnumLiteMap<KeyCode> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return KeyCodeVerifier.INSTANCE;
            }

            @Deprecated
            public static KeyCode valueOf(int i) {
                return forNumber(i);
            }

            @Override
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
        }

        static {
            CustomKeyEvent customKeyEvent = new CustomKeyEvent();
            DEFAULT_INSTANCE = customKeyEvent;
            GeneratedMessageLite.registerDefaultInstance(CustomKeyEvent.class, customKeyEvent);
        }

        private CustomKeyEvent() {
        }

        public void clearAction() {
            this.action_ = 0;
        }

        public void clearKeycode() {
            this.keycode_ = 0;
        }

        public void clearMetaState() {
            this.metaState_ = 0;
        }

        public void clearTimestamp() {
            this.timestamp_ = 0L;
        }

        public static CustomKeyEvent getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(CustomKeyEvent customKeyEvent) {
            return DEFAULT_INSTANCE.createBuilder(customKeyEvent);
        }

        public static CustomKeyEvent parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CustomKeyEvent) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CustomKeyEvent parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (CustomKeyEvent) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static CustomKeyEvent parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (CustomKeyEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static CustomKeyEvent parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (CustomKeyEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static CustomKeyEvent parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (CustomKeyEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static CustomKeyEvent parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (CustomKeyEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static CustomKeyEvent parseFrom(InputStream inputStream) throws IOException {
            return (CustomKeyEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CustomKeyEvent parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (CustomKeyEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static CustomKeyEvent parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CustomKeyEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static CustomKeyEvent parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (CustomKeyEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static CustomKeyEvent parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CustomKeyEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CustomKeyEvent parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (CustomKeyEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<CustomKeyEvent> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setAction(int i) {
            this.action_ = i;
        }

        public void setKeycode(KeyCode keyCode) {
            this.keycode_ = keyCode.getNumber();
        }

        public void setKeycodeValue(int i) {
            this.keycode_ = i;
        }

        public void setMetaState(int i) {
            this.metaState_ = i;
        }

        public void setTimestamp(long j) {
            this.timestamp_ = j;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new CustomKeyEvent();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0004\u0002\f\u0003\u0004\u0004\u0003", new Object[]{"action_", "keycode_", "metaState_", "timestamp_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<CustomKeyEvent> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (CustomKeyEvent.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public int getAction() {
            return this.action_;
        }

        @Override
        public KeyCode getKeycode() {
            KeyCode keyCodeForNumber = KeyCode.forNumber(this.keycode_);
            return keyCodeForNumber == null ? KeyCode.UNRECOGNIZED : keyCodeForNumber;
        }

        @Override
        public int getKeycodeValue() {
            return this.keycode_;
        }

        @Override
        public int getMetaState() {
            return this.metaState_;
        }

        @Override
        public long getTimestamp() {
            return this.timestamp_;
        }
    }

    public interface CustomKeyEventOrBuilder extends MessageLiteOrBuilder {
        int getAction();

        CustomKeyEvent.KeyCode getKeycode();

        int getKeycodeValue();

        int getMetaState();

        long getTimestamp();
    }

    public static final class Disconnect extends GeneratedMessageLite<Disconnect, Disconnect.Builder> implements DisconnectOrBuilder {
        private static final Disconnect DEFAULT_INSTANCE;
        private static volatile Parser<Disconnect> PARSER;

        public static final class Builder extends GeneratedMessageLite.Builder<Disconnect, Builder> implements DisconnectOrBuilder {
            private Builder() {
                super(Disconnect.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }
        }

        static {
            Disconnect disconnect = new Disconnect();
            DEFAULT_INSTANCE = disconnect;
            GeneratedMessageLite.registerDefaultInstance(Disconnect.class, disconnect);
        }

        private Disconnect() {
        }

        public static Disconnect getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Disconnect disconnect) {
            return DEFAULT_INSTANCE.createBuilder(disconnect);
        }

        public static Disconnect parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Disconnect) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Disconnect parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (Disconnect) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static Disconnect parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (Disconnect) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static Disconnect parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Disconnect) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static Disconnect parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (Disconnect) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static Disconnect parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (Disconnect) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static Disconnect parseFrom(InputStream inputStream) throws IOException {
            return (Disconnect) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Disconnect parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (Disconnect) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static Disconnect parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Disconnect) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Disconnect parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Disconnect) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static Disconnect parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Disconnect) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Disconnect parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Disconnect) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<Disconnect> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new Disconnect();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0000", null);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Disconnect> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (Disconnect.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public interface DisconnectOrBuilder extends MessageLiteOrBuilder {
    }

    public enum EncodingFormat implements Internal.EnumLite {
        UNKNOWN_FORMAT(0),
        ENCODING_PCM_8BIT(1),
        ENCODING_PCM_16BIT(2),
        ENCODING_PCM_24BIT_PACKED(3),
        ENCODING_PCM_32BIT(4),
        ENCODING_PCM_FLOAT(5),
        UNRECOGNIZED(-1);

        public static final int ENCODING_PCM_16BIT_VALUE = 2;
        public static final int ENCODING_PCM_24BIT_PACKED_VALUE = 3;
        public static final int ENCODING_PCM_32BIT_VALUE = 4;
        public static final int ENCODING_PCM_8BIT_VALUE = 1;
        public static final int ENCODING_PCM_FLOAT_VALUE = 5;
        public static final int UNKNOWN_FORMAT_VALUE = 0;
        private static final Internal.EnumLiteMap<EncodingFormat> internalValueMap = new Internal.EnumLiteMap<EncodingFormat>() {
            @Override
            public EncodingFormat findValueByNumber(int i) {
                return EncodingFormat.forNumber(i);
            }
        };
        private final int value;

        public static final class EncodingFormatVerifier implements Internal.EnumVerifier {
            public static final Internal.EnumVerifier INSTANCE = new EncodingFormatVerifier();

            private EncodingFormatVerifier() {
            }

            @Override
            public boolean isInRange(int i) {
                return EncodingFormat.forNumber(i) != null;
            }
        }

        EncodingFormat(int i) {
            this.value = i;
        }

        public static EncodingFormat forNumber(int i) {
            if (i == 0) {
                return UNKNOWN_FORMAT;
            }
            if (i == 1) {
                return ENCODING_PCM_8BIT;
            }
            if (i == 2) {
                return ENCODING_PCM_16BIT;
            }
            if (i == 3) {
                return ENCODING_PCM_24BIT_PACKED;
            }
            if (i == 4) {
                return ENCODING_PCM_32BIT;
            }
            if (i != 5) {
                return null;
            }
            return ENCODING_PCM_FLOAT;
        }

        public static Internal.EnumLiteMap<EncodingFormat> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return EncodingFormatVerifier.INSTANCE;
        }

        @Deprecated
        public static EncodingFormat valueOf(int i) {
            return forNumber(i);
        }

        @Override
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
    }

    public static final class FpsRange extends GeneratedMessageLite<FpsRange, FpsRange.Builder> implements FpsRangeOrBuilder {
        private static final FpsRange DEFAULT_INSTANCE;
        public static final int MAX_FIELD_NUMBER = 2;
        public static final int MIN_FIELD_NUMBER = 1;
        private static volatile Parser<FpsRange> PARSER;
        private int max_;
        private int min_;

        public static final class Builder extends GeneratedMessageLite.Builder<FpsRange, Builder> implements FpsRangeOrBuilder {
            private Builder() {
                super(FpsRange.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearMax() {
                copyOnWrite();
                ((FpsRange) this.instance).clearMax();
                return this;
            }

            public Builder clearMin() {
                copyOnWrite();
                ((FpsRange) this.instance).clearMin();
                return this;
            }

            @Override
            public int getMax() {
                return ((FpsRange) this.instance).getMax();
            }

            @Override
            public int getMin() {
                return ((FpsRange) this.instance).getMin();
            }

            public Builder setMax(int i) {
                copyOnWrite();
                ((FpsRange) this.instance).setMax(i);
                return this;
            }

            public Builder setMin(int i) {
                copyOnWrite();
                ((FpsRange) this.instance).setMin(i);
                return this;
            }
        }

        static {
            FpsRange fpsRange = new FpsRange();
            DEFAULT_INSTANCE = fpsRange;
            GeneratedMessageLite.registerDefaultInstance(FpsRange.class, fpsRange);
        }

        private FpsRange() {
        }

        public void clearMax() {
            this.max_ = 0;
        }

        public void clearMin() {
            this.min_ = 0;
        }

        public static FpsRange getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(FpsRange fpsRange) {
            return DEFAULT_INSTANCE.createBuilder(fpsRange);
        }

        public static FpsRange parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FpsRange) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FpsRange parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (FpsRange) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static FpsRange parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (FpsRange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static FpsRange parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (FpsRange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static FpsRange parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (FpsRange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static FpsRange parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (FpsRange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static FpsRange parseFrom(InputStream inputStream) throws IOException {
            return (FpsRange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FpsRange parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (FpsRange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static FpsRange parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FpsRange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static FpsRange parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (FpsRange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static FpsRange parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FpsRange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FpsRange parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (FpsRange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<FpsRange> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setMax(int i) {
            this.max_ = i;
        }

        public void setMin(int i) {
            this.min_ = i;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new FpsRange();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0002\u0004", new Object[]{"min_", "max_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<FpsRange> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (FpsRange.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public int getMax() {
            return this.max_;
        }

        @Override
        public int getMin() {
            return this.min_;
        }
    }

    public interface FpsRangeOrBuilder extends MessageLiteOrBuilder {
        int getMax();

        int getMin();
    }

    public static final class GearInfo extends GeneratedMessageLite<GearInfo, GearInfo.Builder> implements GearInfoOrBuilder {
        private static final GearInfo DEFAULT_INSTANCE;
        public static final int GEAR_FIELD_NUMBER = 1;
        private static volatile Parser<GearInfo> PARSER = null;
        public static final int SPEED_FIELD_NUMBER = 2;
        private int gear_;
        private int speed_;

        public static final class Builder extends GeneratedMessageLite.Builder<GearInfo, Builder> implements GearInfoOrBuilder {
            private Builder() {
                super(GearInfo.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearGear() {
                copyOnWrite();
                ((GearInfo) this.instance).clearGear();
                return this;
            }

            public Builder clearSpeed() {
                copyOnWrite();
                ((GearInfo) this.instance).clearSpeed();
                return this;
            }

            @Override
            public GearState getGear() {
                return ((GearInfo) this.instance).getGear();
            }

            @Override
            public int getGearValue() {
                return ((GearInfo) this.instance).getGearValue();
            }

            @Override
            public int getSpeed() {
                return ((GearInfo) this.instance).getSpeed();
            }

            public Builder setGear(GearState gearState) {
                copyOnWrite();
                ((GearInfo) this.instance).setGear(gearState);
                return this;
            }

            public Builder setGearValue(int i) {
                copyOnWrite();
                ((GearInfo) this.instance).setGearValue(i);
                return this;
            }

            public Builder setSpeed(int i) {
                copyOnWrite();
                ((GearInfo) this.instance).setSpeed(i);
                return this;
            }
        }

        public enum GearState implements Internal.EnumLite {
            GEAR_UNKNOWN(0),
            GEAR_PARK(1),
            GEAR_DRIVE(2),
            GEAR_REVERSE(3),
            GEAR_NEUTRAL(4),
            UNRECOGNIZED(-1);

            public static final int GEAR_DRIVE_VALUE = 2;
            public static final int GEAR_NEUTRAL_VALUE = 4;
            public static final int GEAR_PARK_VALUE = 1;
            public static final int GEAR_REVERSE_VALUE = 3;
            public static final int GEAR_UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<GearState> internalValueMap = new Internal.EnumLiteMap<GearState>() {
                @Override
                public GearState findValueByNumber(int i) {
                    return GearState.forNumber(i);
                }
            };
            private final int value;

            public static final class GearStateVerifier implements Internal.EnumVerifier {
                public static final Internal.EnumVerifier INSTANCE = new GearStateVerifier();

                private GearStateVerifier() {
                }

                @Override
                public boolean isInRange(int i) {
                    return GearState.forNumber(i) != null;
                }
            }

            GearState(int i) {
                this.value = i;
            }

            public static GearState forNumber(int i) {
                if (i == 0) {
                    return GEAR_UNKNOWN;
                }
                if (i == 1) {
                    return GEAR_PARK;
                }
                if (i == 2) {
                    return GEAR_DRIVE;
                }
                if (i == 3) {
                    return GEAR_REVERSE;
                }
                if (i != 4) {
                    return null;
                }
                return GEAR_NEUTRAL;
            }

            public static Internal.EnumLiteMap<GearState> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return GearStateVerifier.INSTANCE;
            }

            @Deprecated
            public static GearState valueOf(int i) {
                return forNumber(i);
            }

            @Override
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
        }

        static {
            GearInfo gearInfo = new GearInfo();
            DEFAULT_INSTANCE = gearInfo;
            GeneratedMessageLite.registerDefaultInstance(GearInfo.class, gearInfo);
        }

        private GearInfo() {
        }

        public void clearGear() {
            this.gear_ = 0;
        }

        public void clearSpeed() {
            this.speed_ = 0;
        }

        public static GearInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(GearInfo gearInfo) {
            return DEFAULT_INSTANCE.createBuilder(gearInfo);
        }

        public static GearInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GearInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GearInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (GearInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static GearInfo parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (GearInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static GearInfo parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GearInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static GearInfo parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (GearInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static GearInfo parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (GearInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static GearInfo parseFrom(InputStream inputStream) throws IOException {
            return (GearInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GearInfo parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (GearInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static GearInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GearInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static GearInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GearInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static GearInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GearInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GearInfo parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GearInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<GearInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setGear(GearState gearState) {
            this.gear_ = gearState.getNumber();
        }

        public void setGearValue(int i) {
            this.gear_ = i;
        }

        public void setSpeed(int i) {
            this.speed_ = i;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new GearInfo();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000b", new Object[]{"gear_", "speed_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<GearInfo> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (GearInfo.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public GearState getGear() {
            GearState gearStateForNumber = GearState.forNumber(this.gear_);
            return gearStateForNumber == null ? GearState.UNRECOGNIZED : gearStateForNumber;
        }

        @Override
        public int getGearValue() {
            return this.gear_;
        }

        @Override
        public int getSpeed() {
            return this.speed_;
        }
    }

    public interface GearInfoOrBuilder extends MessageLiteOrBuilder {
        GearInfo.GearState getGear();

        int getGearValue();

        int getSpeed();
    }

    public static final class GetPortRequest extends GeneratedMessageLite<GetPortRequest, GetPortRequest.Builder> implements GetPortRequestOrBuilder {
        private static final GetPortRequest DEFAULT_INSTANCE;
        private static volatile Parser<GetPortRequest> PARSER = null;
        public static final int TYPE_FIELD_NUMBER = 1;
        private int type_;

        public static final class Builder extends GeneratedMessageLite.Builder<GetPortRequest, Builder> implements GetPortRequestOrBuilder {
            private Builder() {
                super(GetPortRequest.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearType() {
                copyOnWrite();
                ((GetPortRequest) this.instance).clearType();
                return this;
            }

            @Override
            public ServerType getType() {
                return ((GetPortRequest) this.instance).getType();
            }

            @Override
            public int getTypeValue() {
                return ((GetPortRequest) this.instance).getTypeValue();
            }

            public Builder setType(ServerType serverType) {
                copyOnWrite();
                ((GetPortRequest) this.instance).setType(serverType);
                return this;
            }

            public Builder setTypeValue(int i) {
                copyOnWrite();
                ((GetPortRequest) this.instance).setTypeValue(i);
                return this;
            }
        }

        public enum ServerType implements Internal.EnumLite {
            UNKNOWN(0),
            SENSOR(1),
            AUDIO(2),
            UIBC(3),
            UNRECOGNIZED(-1);

            public static final int AUDIO_VALUE = 2;
            public static final int SENSOR_VALUE = 1;
            public static final int UIBC_VALUE = 3;
            public static final int UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<ServerType> internalValueMap = new Internal.EnumLiteMap<ServerType>() {
                @Override
                public ServerType findValueByNumber(int i) {
                    return ServerType.forNumber(i);
                }
            };
            private final int value;

            public static final class ServerTypeVerifier implements Internal.EnumVerifier {
                public static final Internal.EnumVerifier INSTANCE = new ServerTypeVerifier();

                private ServerTypeVerifier() {
                }

                @Override
                public boolean isInRange(int i) {
                    return ServerType.forNumber(i) != null;
                }
            }

            ServerType(int i) {
                this.value = i;
            }

            public static ServerType forNumber(int i) {
                if (i == 0) {
                    return UNKNOWN;
                }
                if (i == 1) {
                    return SENSOR;
                }
                if (i == 2) {
                    return AUDIO;
                }
                if (i != 3) {
                    return null;
                }
                return UIBC;
            }

            public static Internal.EnumLiteMap<ServerType> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return ServerTypeVerifier.INSTANCE;
            }

            @Deprecated
            public static ServerType valueOf(int i) {
                return forNumber(i);
            }

            @Override
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
        }

        static {
            GetPortRequest getPortRequest = new GetPortRequest();
            DEFAULT_INSTANCE = getPortRequest;
            GeneratedMessageLite.registerDefaultInstance(GetPortRequest.class, getPortRequest);
        }

        private GetPortRequest() {
        }

        public void clearType() {
            this.type_ = 0;
        }

        public static GetPortRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(GetPortRequest getPortRequest) {
            return DEFAULT_INSTANCE.createBuilder(getPortRequest);
        }

        public static GetPortRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetPortRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetPortRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (GetPortRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static GetPortRequest parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (GetPortRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static GetPortRequest parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GetPortRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static GetPortRequest parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (GetPortRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static GetPortRequest parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (GetPortRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static GetPortRequest parseFrom(InputStream inputStream) throws IOException {
            return (GetPortRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetPortRequest parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (GetPortRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static GetPortRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetPortRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static GetPortRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GetPortRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static GetPortRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetPortRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetPortRequest parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GetPortRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<GetPortRequest> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setType(ServerType serverType) {
            this.type_ = serverType.getNumber();
        }

        public void setTypeValue(int i) {
            this.type_ = i;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new GetPortRequest();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", new Object[]{"type_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<GetPortRequest> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (GetPortRequest.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public ServerType getType() {
            ServerType serverTypeForNumber = ServerType.forNumber(this.type_);
            return serverTypeForNumber == null ? ServerType.UNRECOGNIZED : serverTypeForNumber;
        }

        @Override
        public int getTypeValue() {
            return this.type_;
        }
    }

    public interface GetPortRequestOrBuilder extends MessageLiteOrBuilder {
        GetPortRequest.ServerType getType();

        int getTypeValue();
    }

    public static final class GetPortResponse extends GeneratedMessageLite<GetPortResponse, GetPortResponse.Builder> implements GetPortResponseOrBuilder {
        private static final GetPortResponse DEFAULT_INSTANCE;
        private static volatile Parser<GetPortResponse> PARSER = null;
        public static final int PORT_FIELD_NUMBER = 2;
        public static final int RESULT_FIELD_NUMBER = 1;
        private int port_;
        private int result_;

        public static final class Builder extends GeneratedMessageLite.Builder<GetPortResponse, Builder> implements GetPortResponseOrBuilder {
            private Builder() {
                super(GetPortResponse.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearPort() {
                copyOnWrite();
                ((GetPortResponse) this.instance).clearPort();
                return this;
            }

            public Builder clearResult() {
                copyOnWrite();
                ((GetPortResponse) this.instance).clearResult();
                return this;
            }

            @Override
            public int getPort() {
                return ((GetPortResponse) this.instance).getPort();
            }

            @Override
            public Result getResult() {
                return ((GetPortResponse) this.instance).getResult();
            }

            @Override
            public int getResultValue() {
                return ((GetPortResponse) this.instance).getResultValue();
            }

            public Builder setPort(int i) {
                copyOnWrite();
                ((GetPortResponse) this.instance).setPort(i);
                return this;
            }

            public Builder setResult(Result result) {
                copyOnWrite();
                ((GetPortResponse) this.instance).setResult(result);
                return this;
            }

            public Builder setResultValue(int i) {
                copyOnWrite();
                ((GetPortResponse) this.instance).setResultValue(i);
                return this;
            }
        }

        public enum Result implements Internal.EnumLite {
            UNKNOWN(0),
            SUCCESS(1),
            FAILURE(2),
            UNRECOGNIZED(-1);

            public static final int FAILURE_VALUE = 2;
            public static final int SUCCESS_VALUE = 1;
            public static final int UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<Result> internalValueMap = new Internal.EnumLiteMap<Result>() {
                @Override
                public Result findValueByNumber(int i) {
                    return Result.forNumber(i);
                }
            };
            private final int value;

            public static final class ResultVerifier implements Internal.EnumVerifier {
                public static final Internal.EnumVerifier INSTANCE = new ResultVerifier();

                private ResultVerifier() {
                }

                @Override
                public boolean isInRange(int i) {
                    return Result.forNumber(i) != null;
                }
            }

            Result(int i) {
                this.value = i;
            }

            public static Result forNumber(int i) {
                if (i == 0) {
                    return UNKNOWN;
                }
                if (i == 1) {
                    return SUCCESS;
                }
                if (i != 2) {
                    return null;
                }
                return FAILURE;
            }

            public static Internal.EnumLiteMap<Result> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return ResultVerifier.INSTANCE;
            }

            @Deprecated
            public static Result valueOf(int i) {
                return forNumber(i);
            }

            @Override
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
        }

        static {
            GetPortResponse getPortResponse = new GetPortResponse();
            DEFAULT_INSTANCE = getPortResponse;
            GeneratedMessageLite.registerDefaultInstance(GetPortResponse.class, getPortResponse);
        }

        private GetPortResponse() {
        }

        public void clearPort() {
            this.port_ = 0;
        }

        public void clearResult() {
            this.result_ = 0;
        }

        public static GetPortResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(GetPortResponse getPortResponse) {
            return DEFAULT_INSTANCE.createBuilder(getPortResponse);
        }

        public static GetPortResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetPortResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetPortResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (GetPortResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static GetPortResponse parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (GetPortResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static GetPortResponse parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GetPortResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static GetPortResponse parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (GetPortResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static GetPortResponse parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (GetPortResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static GetPortResponse parseFrom(InputStream inputStream) throws IOException {
            return (GetPortResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetPortResponse parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (GetPortResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static GetPortResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetPortResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static GetPortResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GetPortResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static GetPortResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetPortResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetPortResponse parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GetPortResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<GetPortResponse> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setPort(int i) {
            this.port_ = i;
        }

        public void setResult(Result result) {
            this.result_ = result.getNumber();
        }

        public void setResultValue(int i) {
            this.result_ = i;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new GetPortResponse();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u0004", new Object[]{"result_", "port_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<GetPortResponse> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (GetPortResponse.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public int getPort() {
            return this.port_;
        }

        @Override
        public Result getResult() {
            Result resultForNumber = Result.forNumber(this.result_);
            return resultForNumber == null ? Result.UNRECOGNIZED : resultForNumber;
        }

        @Override
        public int getResultValue() {
            return this.result_;
        }
    }

    public interface GetPortResponseOrBuilder extends MessageLiteOrBuilder {
        int getPort();

        GetPortResponse.Result getResult();

        int getResultValue();
    }

    public static final class GetUCarConfigRequest extends GeneratedMessageLite<GetUCarConfigRequest, GetUCarConfigRequest.Builder> implements GetUCarConfigRequestOrBuilder {
        private static final GetUCarConfigRequest DEFAULT_INSTANCE;
        private static volatile Parser<GetUCarConfigRequest> PARSER;

        public static final class Builder extends GeneratedMessageLite.Builder<GetUCarConfigRequest, Builder> implements GetUCarConfigRequestOrBuilder {
            private Builder() {
                super(GetUCarConfigRequest.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }
        }

        static {
            GetUCarConfigRequest getUCarConfigRequest = new GetUCarConfigRequest();
            DEFAULT_INSTANCE = getUCarConfigRequest;
            GeneratedMessageLite.registerDefaultInstance(GetUCarConfigRequest.class, getUCarConfigRequest);
        }

        private GetUCarConfigRequest() {
        }

        public static GetUCarConfigRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(GetUCarConfigRequest getUCarConfigRequest) {
            return DEFAULT_INSTANCE.createBuilder(getUCarConfigRequest);
        }

        public static GetUCarConfigRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetUCarConfigRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetUCarConfigRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (GetUCarConfigRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static GetUCarConfigRequest parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (GetUCarConfigRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static GetUCarConfigRequest parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GetUCarConfigRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static GetUCarConfigRequest parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (GetUCarConfigRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static GetUCarConfigRequest parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (GetUCarConfigRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static GetUCarConfigRequest parseFrom(InputStream inputStream) throws IOException {
            return (GetUCarConfigRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetUCarConfigRequest parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (GetUCarConfigRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static GetUCarConfigRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetUCarConfigRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static GetUCarConfigRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GetUCarConfigRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static GetUCarConfigRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetUCarConfigRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetUCarConfigRequest parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GetUCarConfigRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<GetUCarConfigRequest> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new GetUCarConfigRequest();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0000", null);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<GetUCarConfigRequest> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (GetUCarConfigRequest.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public interface GetUCarConfigRequestOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class GetUCarConfigResponse extends GeneratedMessageLite<GetUCarConfigResponse, GetUCarConfigResponse.Builder> implements GetUCarConfigResponseOrBuilder {
        public static final int CARBRMAC_FIELD_NUMBER = 1;
        public static final int CARCUSTOMFIELD_FIELD_NUMBER = 16;
        public static final int DEFAULT5GCHANNEL_FIELD_NUMBER = 11;
        private static final GetUCarConfigResponse DEFAULT_INSTANCE;
        public static final int DPI_FIELD_NUMBER = 4;
        public static final int FPS_FIELD_NUMBER = 7;
        public static final int ISDATATRANSMODE_FIELD_NUMBER = 10;
        public static final int ISSUPPORTCAMERA_FIELD_NUMBER = 12;
        public static final int ISSUPPORTLOWLATENCYDECODINGMODE_FIELD_NUMBER = 14;
        public static final int ISSUPPORTMIC_FIELD_NUMBER = 13;
        public static final int ISSUPPORTP2P_FIELD_NUMBER = 8;
        public static final int ISSUPPORTSOFTAP_FIELD_NUMBER = 9;
        public static final int ISSUPPORTVOICEWAKEN_FIELD_NUMBER = 15;
        private static volatile Parser<GetUCarConfigResponse> PARSER = null;
        public static final int SCREENHEIGHT_FIELD_NUMBER = 3;
        public static final int SCREENWIDTH_FIELD_NUMBER = 2;
        public static final int SDKVERSION_FIELD_NUMBER = 17;
        public static final int VIDEODISPLAYHEIGHT_FIELD_NUMBER = 6;
        public static final int VIDEODISPLAYWIDTH_FIELD_NUMBER = 5;
        private ByteString carBrMac_;
        private ByteString carCustomField_;
        private int default5GChannel_;
        private int dpi_;
        private int fps_;
        private boolean isDataTransMode_;
        private boolean isSupportCamera_;
        private boolean isSupportLowLatencyDecodingMode_;
        private boolean isSupportMic_;
        private boolean isSupportP2P_;
        private boolean isSupportSoftAP_;
        private boolean isSupportVoiceWaken_;
        private int screenHeight_;
        private int screenWidth_;
        private String sdkVersion_;
        private int videoDisplayHeight_;
        private int videoDisplayWidth_;

        public static final class Builder extends GeneratedMessageLite.Builder<GetUCarConfigResponse, Builder> implements GetUCarConfigResponseOrBuilder {
            private Builder() {
                super(GetUCarConfigResponse.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearCarBrMac() {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).clearCarBrMac();
                return this;
            }

            public Builder clearCarCustomField() {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).clearCarCustomField();
                return this;
            }

            public Builder clearDefault5GChannel() {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).clearDefault5GChannel();
                return this;
            }

            public Builder clearDpi() {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).clearDpi();
                return this;
            }

            public Builder clearFps() {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).clearFps();
                return this;
            }

            public Builder clearIsDataTransMode() {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).clearIsDataTransMode();
                return this;
            }

            public Builder clearIsSupportCamera() {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).clearIsSupportCamera();
                return this;
            }

            public Builder clearIsSupportLowLatencyDecodingMode() {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).clearIsSupportLowLatencyDecodingMode();
                return this;
            }

            public Builder clearIsSupportMic() {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).clearIsSupportMic();
                return this;
            }

            public Builder clearIsSupportP2P() {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).clearIsSupportP2P();
                return this;
            }

            public Builder clearIsSupportSoftAP() {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).clearIsSupportSoftAP();
                return this;
            }

            public Builder clearIsSupportVoiceWaken() {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).clearIsSupportVoiceWaken();
                return this;
            }

            public Builder clearScreenHeight() {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).clearScreenHeight();
                return this;
            }

            public Builder clearScreenWidth() {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).clearScreenWidth();
                return this;
            }

            public Builder clearSdkVersion() {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).clearSdkVersion();
                return this;
            }

            public Builder clearVideoDisplayHeight() {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).clearVideoDisplayHeight();
                return this;
            }

            public Builder clearVideoDisplayWidth() {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).clearVideoDisplayWidth();
                return this;
            }

            @Override
            public ByteString getCarBrMac() {
                return ((GetUCarConfigResponse) this.instance).getCarBrMac();
            }

            @Override
            public ByteString getCarCustomField() {
                return ((GetUCarConfigResponse) this.instance).getCarCustomField();
            }

            @Override
            public int getDefault5GChannel() {
                return ((GetUCarConfigResponse) this.instance).getDefault5GChannel();
            }

            @Override
            public int getDpi() {
                return ((GetUCarConfigResponse) this.instance).getDpi();
            }

            @Override
            public int getFps() {
                return ((GetUCarConfigResponse) this.instance).getFps();
            }

            @Override
            public boolean getIsDataTransMode() {
                return ((GetUCarConfigResponse) this.instance).getIsDataTransMode();
            }

            @Override
            public boolean getIsSupportCamera() {
                return ((GetUCarConfigResponse) this.instance).getIsSupportCamera();
            }

            @Override
            public boolean getIsSupportLowLatencyDecodingMode() {
                return ((GetUCarConfigResponse) this.instance).getIsSupportLowLatencyDecodingMode();
            }

            @Override
            public boolean getIsSupportMic() {
                return ((GetUCarConfigResponse) this.instance).getIsSupportMic();
            }

            @Override
            public boolean getIsSupportP2P() {
                return ((GetUCarConfigResponse) this.instance).getIsSupportP2P();
            }

            @Override
            public boolean getIsSupportSoftAP() {
                return ((GetUCarConfigResponse) this.instance).getIsSupportSoftAP();
            }

            @Override
            public boolean getIsSupportVoiceWaken() {
                return ((GetUCarConfigResponse) this.instance).getIsSupportVoiceWaken();
            }

            @Override
            public int getScreenHeight() {
                return ((GetUCarConfigResponse) this.instance).getScreenHeight();
            }

            @Override
            public int getScreenWidth() {
                return ((GetUCarConfigResponse) this.instance).getScreenWidth();
            }

            @Override
            public String getSdkVersion() {
                return ((GetUCarConfigResponse) this.instance).getSdkVersion();
            }

            @Override
            public ByteString getSdkVersionBytes() {
                return ((GetUCarConfigResponse) this.instance).getSdkVersionBytes();
            }

            @Override
            public int getVideoDisplayHeight() {
                return ((GetUCarConfigResponse) this.instance).getVideoDisplayHeight();
            }

            @Override
            public int getVideoDisplayWidth() {
                return ((GetUCarConfigResponse) this.instance).getVideoDisplayWidth();
            }

            public Builder setCarBrMac(ByteString abstractC2534u) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setCarBrMac(abstractC2534u);
                return this;
            }

            public Builder setCarCustomField(ByteString abstractC2534u) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setCarCustomField(abstractC2534u);
                return this;
            }

            public Builder setDefault5GChannel(int i) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setDefault5GChannel(i);
                return this;
            }

            public Builder setDpi(int i) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setDpi(i);
                return this;
            }

            public Builder setFps(int i) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setFps(i);
                return this;
            }

            public Builder setIsDataTransMode(boolean z) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setIsDataTransMode(z);
                return this;
            }

            public Builder setIsSupportCamera(boolean z) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setIsSupportCamera(z);
                return this;
            }

            public Builder setIsSupportLowLatencyDecodingMode(boolean z) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setIsSupportLowLatencyDecodingMode(z);
                return this;
            }

            public Builder setIsSupportMic(boolean z) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setIsSupportMic(z);
                return this;
            }

            public Builder setIsSupportP2P(boolean z) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setIsSupportP2P(z);
                return this;
            }

            public Builder setIsSupportSoftAP(boolean z) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setIsSupportSoftAP(z);
                return this;
            }

            public Builder setIsSupportVoiceWaken(boolean z) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setIsSupportVoiceWaken(z);
                return this;
            }

            public Builder setScreenHeight(int i) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setScreenHeight(i);
                return this;
            }

            public Builder setScreenWidth(int i) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setScreenWidth(i);
                return this;
            }

            public Builder setSdkVersion(String str) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setSdkVersion(str);
                return this;
            }

            public Builder setSdkVersionBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setSdkVersionBytes(abstractC2534u);
                return this;
            }

            public Builder setVideoDisplayHeight(int i) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setVideoDisplayHeight(i);
                return this;
            }

            public Builder setVideoDisplayWidth(int i) {
                copyOnWrite();
                ((GetUCarConfigResponse) this.instance).setVideoDisplayWidth(i);
                return this;
            }
        }

        static {
            GetUCarConfigResponse getUCarConfigResponse = new GetUCarConfigResponse();
            DEFAULT_INSTANCE = getUCarConfigResponse;
            GeneratedMessageLite.registerDefaultInstance(GetUCarConfigResponse.class, getUCarConfigResponse);
        }

        private GetUCarConfigResponse() {
            ByteString abstractC2534u = ByteString.EMPTY;
            this.carBrMac_ = abstractC2534u;
            this.carCustomField_ = abstractC2534u;
            this.sdkVersion_ = "";
        }

        public void clearCarBrMac() {
            this.carBrMac_ = getDefaultInstance().getCarBrMac();
        }

        public void clearCarCustomField() {
            this.carCustomField_ = getDefaultInstance().getCarCustomField();
        }

        public void clearDefault5GChannel() {
            this.default5GChannel_ = 0;
        }

        public void clearDpi() {
            this.dpi_ = 0;
        }

        public void clearFps() {
            this.fps_ = 0;
        }

        public void clearIsDataTransMode() {
            this.isDataTransMode_ = false;
        }

        public void clearIsSupportCamera() {
            this.isSupportCamera_ = false;
        }

        public void clearIsSupportLowLatencyDecodingMode() {
            this.isSupportLowLatencyDecodingMode_ = false;
        }

        public void clearIsSupportMic() {
            this.isSupportMic_ = false;
        }

        public void clearIsSupportP2P() {
            this.isSupportP2P_ = false;
        }

        public void clearIsSupportSoftAP() {
            this.isSupportSoftAP_ = false;
        }

        public void clearIsSupportVoiceWaken() {
            this.isSupportVoiceWaken_ = false;
        }

        public void clearScreenHeight() {
            this.screenHeight_ = 0;
        }

        public void clearScreenWidth() {
            this.screenWidth_ = 0;
        }

        public void clearSdkVersion() {
            this.sdkVersion_ = getDefaultInstance().getSdkVersion();
        }

        public void clearVideoDisplayHeight() {
            this.videoDisplayHeight_ = 0;
        }

        public void clearVideoDisplayWidth() {
            this.videoDisplayWidth_ = 0;
        }

        public static GetUCarConfigResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(GetUCarConfigResponse getUCarConfigResponse) {
            return DEFAULT_INSTANCE.createBuilder(getUCarConfigResponse);
        }

        public static GetUCarConfigResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GetUCarConfigResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetUCarConfigResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (GetUCarConfigResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static GetUCarConfigResponse parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (GetUCarConfigResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static GetUCarConfigResponse parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GetUCarConfigResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static GetUCarConfigResponse parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (GetUCarConfigResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static GetUCarConfigResponse parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (GetUCarConfigResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static GetUCarConfigResponse parseFrom(InputStream inputStream) throws IOException {
            return (GetUCarConfigResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetUCarConfigResponse parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (GetUCarConfigResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static GetUCarConfigResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GetUCarConfigResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static GetUCarConfigResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GetUCarConfigResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static GetUCarConfigResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GetUCarConfigResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetUCarConfigResponse parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GetUCarConfigResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<GetUCarConfigResponse> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setCarBrMac(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.carBrMac_ = abstractC2534u;
        }

        public void setCarCustomField(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.carCustomField_ = abstractC2534u;
        }

        public void setDefault5GChannel(int i) {
            this.default5GChannel_ = i;
        }

        public void setDpi(int i) {
            this.dpi_ = i;
        }

        public void setFps(int i) {
            this.fps_ = i;
        }

        public void setIsDataTransMode(boolean z) {
            this.isDataTransMode_ = z;
        }

        public void setIsSupportCamera(boolean z) {
            this.isSupportCamera_ = z;
        }

        public void setIsSupportLowLatencyDecodingMode(boolean z) {
            this.isSupportLowLatencyDecodingMode_ = z;
        }

        public void setIsSupportMic(boolean z) {
            this.isSupportMic_ = z;
        }

        public void setIsSupportP2P(boolean z) {
            this.isSupportP2P_ = z;
        }

        public void setIsSupportSoftAP(boolean z) {
            this.isSupportSoftAP_ = z;
        }

        public void setIsSupportVoiceWaken(boolean z) {
            this.isSupportVoiceWaken_ = z;
        }

        public void setScreenHeight(int i) {
            this.screenHeight_ = i;
        }

        public void setScreenWidth(int i) {
            this.screenWidth_ = i;
        }

        public void setSdkVersion(String str) {
            str.getClass();
            this.sdkVersion_ = str;
        }

        public void setSdkVersionBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.sdkVersion_ = abstractC2534u.toStringUtf8();
        }

        public void setVideoDisplayHeight(int i) {
            this.videoDisplayHeight_ = i;
        }

        public void setVideoDisplayWidth(int i) {
            this.videoDisplayWidth_ = i;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new GetUCarConfigResponse();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0011\u0000\u0000\u0001\u0011\u0011\u0000\u0000\u0000\u0001\n\u0002\u0004\u0003\u0004\u0004\u0004\u0005\u0004\u0006\u0004\u0007\u0004\b\u0007\t\u0007\n\u0007\u000b\u0004\f\u0007\r\u0007\u000e\u0007\u000f\u0007\u0010\n\u0011Ȉ", new Object[]{"carBrMac_", "screenWidth_", "screenHeight_", "dpi_", "videoDisplayWidth_", "videoDisplayHeight_", "fps_", "isSupportP2P_", "isSupportSoftAP_", "isDataTransMode_", "default5GChannel_", "isSupportCamera_", "isSupportMic_", "isSupportLowLatencyDecodingMode_", "isSupportVoiceWaken_", "carCustomField_", "sdkVersion_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<GetUCarConfigResponse> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (GetUCarConfigResponse.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public ByteString getCarBrMac() {
            return this.carBrMac_;
        }

        @Override
        public ByteString getCarCustomField() {
            return this.carCustomField_;
        }

        @Override
        public int getDefault5GChannel() {
            return this.default5GChannel_;
        }

        @Override
        public int getDpi() {
            return this.dpi_;
        }

        @Override
        public int getFps() {
            return this.fps_;
        }

        @Override
        public boolean getIsDataTransMode() {
            return this.isDataTransMode_;
        }

        @Override
        public boolean getIsSupportCamera() {
            return this.isSupportCamera_;
        }

        @Override
        public boolean getIsSupportLowLatencyDecodingMode() {
            return this.isSupportLowLatencyDecodingMode_;
        }

        @Override
        public boolean getIsSupportMic() {
            return this.isSupportMic_;
        }

        @Override
        public boolean getIsSupportP2P() {
            return this.isSupportP2P_;
        }

        @Override
        public boolean getIsSupportSoftAP() {
            return this.isSupportSoftAP_;
        }

        @Override
        public boolean getIsSupportVoiceWaken() {
            return this.isSupportVoiceWaken_;
        }

        @Override
        public int getScreenHeight() {
            return this.screenHeight_;
        }

        @Override
        public int getScreenWidth() {
            return this.screenWidth_;
        }

        @Override
        public String getSdkVersion() {
            return this.sdkVersion_;
        }

        @Override
        public ByteString getSdkVersionBytes() {
            return ByteString.copyFromUtf8(this.sdkVersion_);
        }

        @Override
        public int getVideoDisplayHeight() {
            return this.videoDisplayHeight_;
        }

        @Override
        public int getVideoDisplayWidth() {
            return this.videoDisplayWidth_;
        }
    }

    public interface GetUCarConfigResponseOrBuilder extends MessageLiteOrBuilder {
        ByteString getCarBrMac();

        ByteString getCarCustomField();

        int getDefault5GChannel();

        int getDpi();

        int getFps();

        boolean getIsDataTransMode();

        boolean getIsSupportCamera();

        boolean getIsSupportLowLatencyDecodingMode();

        boolean getIsSupportMic();

        boolean getIsSupportP2P();

        boolean getIsSupportSoftAP();

        boolean getIsSupportVoiceWaken();

        int getScreenHeight();

        int getScreenWidth();

        String getSdkVersion();

        ByteString getSdkVersionBytes();

        int getVideoDisplayHeight();

        int getVideoDisplayWidth();
    }

    public static final class Gps extends GeneratedMessageLite<Gps, Gps.Builder> implements GpsOrBuilder {
        public static final int ALTITUDE_FIELD_NUMBER = 1;
        public static final int ANTENNA_STATE_FIELD_NUMBER = 4;
        private static final Gps DEFAULT_INSTANCE;
        public static final int HEADING_FIELD_NUMBER = 7;
        public static final int LATITUDE_FIELD_NUMBER = 2;
        public static final int LONGITUDE_FIELD_NUMBER = 3;
        private static volatile Parser<Gps> PARSER = null;
        public static final int PDOP_FIELD_NUMBER = 5;
        public static final int SATS_USED_FIELD_NUMBER = 8;
        public static final int SATS_VISIBLE_FIELD_NUMBER = 9;
        public static final int SPEED_FIELD_NUMBER = 6;
        public static final int TIMESTAMP_FIELD_NUMBER = 10;
        private double altitude_;
        private int antennaState_;
        private int heading_;
        private double latitude_;
        private double longitude_;
        private int pdop_;
        private int satsUsed_;
        private int satsVisible_;
        private int speed_;
        private long timestamp_;

        public static final class Builder extends GeneratedMessageLite.Builder<Gps, Builder> implements GpsOrBuilder {
            private Builder() {
                super(Gps.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearAltitude() {
                copyOnWrite();
                ((Gps) this.instance).clearAltitude();
                return this;
            }

            public Builder clearAntennaState() {
                copyOnWrite();
                ((Gps) this.instance).clearAntennaState();
                return this;
            }

            public Builder clearHeading() {
                copyOnWrite();
                ((Gps) this.instance).clearHeading();
                return this;
            }

            public Builder clearLatitude() {
                copyOnWrite();
                ((Gps) this.instance).clearLatitude();
                return this;
            }

            public Builder clearLongitude() {
                copyOnWrite();
                ((Gps) this.instance).clearLongitude();
                return this;
            }

            public Builder clearPdop() {
                copyOnWrite();
                ((Gps) this.instance).clearPdop();
                return this;
            }

            public Builder clearSatsUsed() {
                copyOnWrite();
                ((Gps) this.instance).clearSatsUsed();
                return this;
            }

            public Builder clearSatsVisible() {
                copyOnWrite();
                ((Gps) this.instance).clearSatsVisible();
                return this;
            }

            public Builder clearSpeed() {
                copyOnWrite();
                ((Gps) this.instance).clearSpeed();
                return this;
            }

            public Builder clearTimestamp() {
                copyOnWrite();
                ((Gps) this.instance).clearTimestamp();
                return this;
            }

            @Override
            public double getAltitude() {
                return ((Gps) this.instance).getAltitude();
            }

            @Override
            public int getAntennaState() {
                return ((Gps) this.instance).getAntennaState();
            }

            @Override
            public int getHeading() {
                return ((Gps) this.instance).getHeading();
            }

            @Override
            public double getLatitude() {
                return ((Gps) this.instance).getLatitude();
            }

            @Override
            public double getLongitude() {
                return ((Gps) this.instance).getLongitude();
            }

            @Override
            public int getPdop() {
                return ((Gps) this.instance).getPdop();
            }

            @Override
            public int getSatsUsed() {
                return ((Gps) this.instance).getSatsUsed();
            }

            @Override
            public int getSatsVisible() {
                return ((Gps) this.instance).getSatsVisible();
            }

            @Override
            public int getSpeed() {
                return ((Gps) this.instance).getSpeed();
            }

            @Override
            public long getTimestamp() {
                return ((Gps) this.instance).getTimestamp();
            }

            public Builder setAltitude(double d2) {
                copyOnWrite();
                ((Gps) this.instance).setAltitude(d2);
                return this;
            }

            public Builder setAntennaState(int i) {
                copyOnWrite();
                ((Gps) this.instance).setAntennaState(i);
                return this;
            }

            public Builder setHeading(int i) {
                copyOnWrite();
                ((Gps) this.instance).setHeading(i);
                return this;
            }

            public Builder setLatitude(double d2) {
                copyOnWrite();
                ((Gps) this.instance).setLatitude(d2);
                return this;
            }

            public Builder setLongitude(double d2) {
                copyOnWrite();
                ((Gps) this.instance).setLongitude(d2);
                return this;
            }

            public Builder setPdop(int i) {
                copyOnWrite();
                ((Gps) this.instance).setPdop(i);
                return this;
            }

            public Builder setSatsUsed(int i) {
                copyOnWrite();
                ((Gps) this.instance).setSatsUsed(i);
                return this;
            }

            public Builder setSatsVisible(int i) {
                copyOnWrite();
                ((Gps) this.instance).setSatsVisible(i);
                return this;
            }

            public Builder setSpeed(int i) {
                copyOnWrite();
                ((Gps) this.instance).setSpeed(i);
                return this;
            }

            public Builder setTimestamp(long j) {
                copyOnWrite();
                ((Gps) this.instance).setTimestamp(j);
                return this;
            }
        }

        static {
            Gps gps = new Gps();
            DEFAULT_INSTANCE = gps;
            GeneratedMessageLite.registerDefaultInstance(Gps.class, gps);
        }

        private Gps() {
        }

        public void clearAltitude() {
            this.altitude_ = 0.0d;
        }

        public void clearAntennaState() {
            this.antennaState_ = 0;
        }

        public void clearHeading() {
            this.heading_ = 0;
        }

        public void clearLatitude() {
            this.latitude_ = 0.0d;
        }

        public void clearLongitude() {
            this.longitude_ = 0.0d;
        }

        public void clearPdop() {
            this.pdop_ = 0;
        }

        public void clearSatsUsed() {
            this.satsUsed_ = 0;
        }

        public void clearSatsVisible() {
            this.satsVisible_ = 0;
        }

        public void clearSpeed() {
            this.speed_ = 0;
        }

        public void clearTimestamp() {
            this.timestamp_ = 0L;
        }

        public static Gps getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Gps gps) {
            return DEFAULT_INSTANCE.createBuilder(gps);
        }

        public static Gps parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Gps) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Gps parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (Gps) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static Gps parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (Gps) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static Gps parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Gps) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static Gps parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (Gps) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static Gps parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (Gps) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static Gps parseFrom(InputStream inputStream) throws IOException {
            return (Gps) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Gps parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (Gps) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static Gps parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Gps) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Gps parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Gps) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static Gps parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Gps) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Gps parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Gps) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<Gps> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setAltitude(double d2) {
            this.altitude_ = d2;
        }

        public void setAntennaState(int i) {
            this.antennaState_ = i;
        }

        public void setHeading(int i) {
            this.heading_ = i;
        }

        public void setLatitude(double d2) {
            this.latitude_ = d2;
        }

        public void setLongitude(double d2) {
            this.longitude_ = d2;
        }

        public void setPdop(int i) {
            this.pdop_ = i;
        }

        public void setSatsUsed(int i) {
            this.satsUsed_ = i;
        }

        public void setSatsVisible(int i) {
            this.satsVisible_ = i;
        }

        public void setSpeed(int i) {
            this.speed_ = i;
        }

        public void setTimestamp(long j) {
            this.timestamp_ = j;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new Gps();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\n\u0000\u0000\u0001\n\n\u0000\u0000\u0000\u0001\u0000\u0002\u0000\u0003\u0000\u0004\u000b\u0005\u000b\u0006\u000b\u0007\u000b\b\u000b\t\u000b\n\u0003", new Object[]{"altitude_", "latitude_", "longitude_", "antennaState_", "pdop_", "speed_", "heading_", "satsUsed_", "satsVisible_", "timestamp_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Gps> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (Gps.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public double getAltitude() {
            return this.altitude_;
        }

        @Override
        public int getAntennaState() {
            return this.antennaState_;
        }

        @Override
        public int getHeading() {
            return this.heading_;
        }

        @Override
        public double getLatitude() {
            return this.latitude_;
        }

        @Override
        public double getLongitude() {
            return this.longitude_;
        }

        @Override
        public int getPdop() {
            return this.pdop_;
        }

        @Override
        public int getSatsUsed() {
            return this.satsUsed_;
        }

        @Override
        public int getSatsVisible() {
            return this.satsVisible_;
        }

        @Override
        public int getSpeed() {
            return this.speed_;
        }

        @Override
        public long getTimestamp() {
            return this.timestamp_;
        }
    }

    public interface GpsOrBuilder extends MessageLiteOrBuilder {
        double getAltitude();

        int getAntennaState();

        int getHeading();

        double getLatitude();

        double getLongitude();

        int getPdop();

        int getSatsUsed();

        int getSatsVisible();

        int getSpeed();

        long getTimestamp();
    }

    public static final class GyroScope extends GeneratedMessageLite<GyroScope, GyroScope.Builder> implements GyroScopeOrBuilder {
        private static final GyroScope DEFAULT_INSTANCE;
        public static final int GYRO_TYPE_FIELD_NUMBER = 1;
        public static final int LEGYRO_X_FIELD_NUMBER = 2;
        public static final int LEGYRO_Y_FIELD_NUMBER = 3;
        public static final int LEGYRO_Z_FIELD_NUMBER = 4;
        private static volatile Parser<GyroScope> PARSER = null;
        public static final int TIMESTAMP_FIELD_NUMBER = 5;
        private int gyroType_;
        private double legyroX_;
        private double legyroY_;
        private double legyroZ_;
        private long timestamp_;

        public static final class Builder extends GeneratedMessageLite.Builder<GyroScope, Builder> implements GyroScopeOrBuilder {
            private Builder() {
                super(GyroScope.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearGyroType() {
                copyOnWrite();
                ((GyroScope) this.instance).clearGyroType();
                return this;
            }

            public Builder clearLegyroX() {
                copyOnWrite();
                ((GyroScope) this.instance).clearLegyroX();
                return this;
            }

            public Builder clearLegyroY() {
                copyOnWrite();
                ((GyroScope) this.instance).clearLegyroY();
                return this;
            }

            public Builder clearLegyroZ() {
                copyOnWrite();
                ((GyroScope) this.instance).clearLegyroZ();
                return this;
            }

            public Builder clearTimestamp() {
                copyOnWrite();
                ((GyroScope) this.instance).clearTimestamp();
                return this;
            }

            @Override
            public int getGyroType() {
                return ((GyroScope) this.instance).getGyroType();
            }

            @Override
            public double getLegyroX() {
                return ((GyroScope) this.instance).getLegyroX();
            }

            @Override
            public double getLegyroY() {
                return ((GyroScope) this.instance).getLegyroY();
            }

            @Override
            public double getLegyroZ() {
                return ((GyroScope) this.instance).getLegyroZ();
            }

            @Override
            public long getTimestamp() {
                return ((GyroScope) this.instance).getTimestamp();
            }

            public Builder setGyroType(int i) {
                copyOnWrite();
                ((GyroScope) this.instance).setGyroType(i);
                return this;
            }

            public Builder setLegyroX(double d2) {
                copyOnWrite();
                ((GyroScope) this.instance).setLegyroX(d2);
                return this;
            }

            public Builder setLegyroY(double d2) {
                copyOnWrite();
                ((GyroScope) this.instance).setLegyroY(d2);
                return this;
            }

            public Builder setLegyroZ(double d2) {
                copyOnWrite();
                ((GyroScope) this.instance).setLegyroZ(d2);
                return this;
            }

            public Builder setTimestamp(long j) {
                copyOnWrite();
                ((GyroScope) this.instance).setTimestamp(j);
                return this;
            }
        }

        public enum GyroType implements Internal.EnumLite {
            GYRO_UNKNOWN(0),
            GYRO_SINGLE(1),
            GYRO_THREE(2),
            UNRECOGNIZED(-1);

            public static final int GYRO_SINGLE_VALUE = 1;
            public static final int GYRO_THREE_VALUE = 2;
            public static final int GYRO_UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<GyroType> internalValueMap = new Internal.EnumLiteMap<GyroType>() {
                @Override
                public GyroType findValueByNumber(int i) {
                    return GyroType.forNumber(i);
                }
            };
            private final int value;

            public static final class GyroTypeVerifier implements Internal.EnumVerifier {
                public static final Internal.EnumVerifier INSTANCE = new GyroTypeVerifier();

                private GyroTypeVerifier() {
                }

                @Override
                public boolean isInRange(int i) {
                    return GyroType.forNumber(i) != null;
                }
            }

            GyroType(int i) {
                this.value = i;
            }

            public static GyroType forNumber(int i) {
                if (i == 0) {
                    return GYRO_UNKNOWN;
                }
                if (i == 1) {
                    return GYRO_SINGLE;
                }
                if (i != 2) {
                    return null;
                }
                return GYRO_THREE;
            }

            public static Internal.EnumLiteMap<GyroType> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return GyroTypeVerifier.INSTANCE;
            }

            @Deprecated
            public static GyroType valueOf(int i) {
                return forNumber(i);
            }

            @Override
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
        }

        static {
            GyroScope gyroScope = new GyroScope();
            DEFAULT_INSTANCE = gyroScope;
            GeneratedMessageLite.registerDefaultInstance(GyroScope.class, gyroScope);
        }

        private GyroScope() {
        }

        public void clearGyroType() {
            this.gyroType_ = 0;
        }

        public void clearLegyroX() {
            this.legyroX_ = 0.0d;
        }

        public void clearLegyroY() {
            this.legyroY_ = 0.0d;
        }

        public void clearLegyroZ() {
            this.legyroZ_ = 0.0d;
        }

        public void clearTimestamp() {
            this.timestamp_ = 0L;
        }

        public static GyroScope getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(GyroScope gyroScope) {
            return DEFAULT_INSTANCE.createBuilder(gyroScope);
        }

        public static GyroScope parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GyroScope) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GyroScope parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (GyroScope) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static GyroScope parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (GyroScope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static GyroScope parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GyroScope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static GyroScope parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (GyroScope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static GyroScope parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (GyroScope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static GyroScope parseFrom(InputStream inputStream) throws IOException {
            return (GyroScope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GyroScope parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (GyroScope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static GyroScope parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GyroScope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static GyroScope parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GyroScope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static GyroScope parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GyroScope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GyroScope parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (GyroScope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<GyroScope> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setGyroType(int i) {
            this.gyroType_ = i;
        }

        public void setLegyroX(double d2) {
            this.legyroX_ = d2;
        }

        public void setLegyroY(double d2) {
            this.legyroY_ = d2;
        }

        public void setLegyroZ(double d2) {
            this.legyroZ_ = d2;
        }

        public void setTimestamp(long j) {
            this.timestamp_ = j;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new GyroScope();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001\u0004\u0002\u0000\u0003\u0000\u0004\u0000\u0005\u0003", new Object[]{"gyroType_", "legyroX_", "legyroY_", "legyroZ_", "timestamp_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<GyroScope> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (GyroScope.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public int getGyroType() {
            return this.gyroType_;
        }

        @Override
        public double getLegyroX() {
            return this.legyroX_;
        }

        @Override
        public double getLegyroY() {
            return this.legyroY_;
        }

        @Override
        public double getLegyroZ() {
            return this.legyroZ_;
        }

        @Override
        public long getTimestamp() {
            return this.timestamp_;
        }
    }

    public interface GyroScopeOrBuilder extends MessageLiteOrBuilder {
        int getGyroType();

        double getLegyroX();

        double getLegyroY();

        double getLegyroZ();

        long getTimestamp();
    }

    public static final class Heartbeat extends GeneratedMessageLite<Heartbeat, Heartbeat.Builder> implements HeartbeatOrBuilder {
        private static final Heartbeat DEFAULT_INSTANCE;
        private static volatile Parser<Heartbeat> PARSER = null;
        public static final int TIMESTAMP_FIELD_NUMBER = 1;
        private long timestamp_;

        public static final class Builder extends GeneratedMessageLite.Builder<Heartbeat, Builder> implements HeartbeatOrBuilder {
            private Builder() {
                super(Heartbeat.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearTimestamp() {
                copyOnWrite();
                ((Heartbeat) this.instance).clearTimestamp();
                return this;
            }

            @Override
            public long getTimestamp() {
                return ((Heartbeat) this.instance).getTimestamp();
            }

            public Builder setTimestamp(long j) {
                copyOnWrite();
                ((Heartbeat) this.instance).setTimestamp(j);
                return this;
            }
        }

        static {
            Heartbeat heartbeat = new Heartbeat();
            DEFAULT_INSTANCE = heartbeat;
            GeneratedMessageLite.registerDefaultInstance(Heartbeat.class, heartbeat);
        }

        private Heartbeat() {
        }

        public void clearTimestamp() {
            this.timestamp_ = 0L;
        }

        public static Heartbeat getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Heartbeat heartbeat) {
            return DEFAULT_INSTANCE.createBuilder(heartbeat);
        }

        public static Heartbeat parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Heartbeat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Heartbeat parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (Heartbeat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static Heartbeat parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (Heartbeat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static Heartbeat parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Heartbeat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static Heartbeat parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (Heartbeat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static Heartbeat parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (Heartbeat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static Heartbeat parseFrom(InputStream inputStream) throws IOException {
            return (Heartbeat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Heartbeat parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (Heartbeat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static Heartbeat parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Heartbeat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Heartbeat parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Heartbeat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static Heartbeat parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Heartbeat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Heartbeat parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Heartbeat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<Heartbeat> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setTimestamp(long j) {
            this.timestamp_ = j;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new Heartbeat();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0003", new Object[]{"timestamp_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Heartbeat> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (Heartbeat.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public long getTimestamp() {
            return this.timestamp_;
        }
    }

    public interface HeartbeatOrBuilder extends MessageLiteOrBuilder {
        long getTimestamp();
    }

    public enum LensFacing implements Internal.EnumLite {
        LENS_FACING_FRONT(0),
        LENS_FACING_BACK(1),
        LENS_FACING_EXTERNAL(2),
        UNRECOGNIZED(-1);

        public static final int LENS_FACING_BACK_VALUE = 1;
        public static final int LENS_FACING_EXTERNAL_VALUE = 2;
        public static final int LENS_FACING_FRONT_VALUE = 0;
        private static final Internal.EnumLiteMap<LensFacing> internalValueMap = new Internal.EnumLiteMap<LensFacing>() {
            @Override
            public LensFacing findValueByNumber(int i) {
                return LensFacing.forNumber(i);
            }
        };
        private final int value;

        public static final class LensFacingVerifier implements Internal.EnumVerifier {
            public static final Internal.EnumVerifier INSTANCE = new LensFacingVerifier();

            private LensFacingVerifier() {
            }

            @Override
            public boolean isInRange(int i) {
                return LensFacing.forNumber(i) != null;
            }
        }

        LensFacing(int i) {
            this.value = i;
        }

        public static LensFacing forNumber(int i) {
            if (i == 0) {
                return LENS_FACING_FRONT;
            }
            if (i == 1) {
                return LENS_FACING_BACK;
            }
            if (i != 2) {
                return null;
            }
            return LENS_FACING_EXTERNAL;
        }

        public static Internal.EnumLiteMap<LensFacing> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return LensFacingVerifier.INSTANCE;
        }

        @Deprecated
        public static LensFacing valueOf(int i) {
            return forNumber(i);
        }

        @Override
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
    }

    public static final class LightSensorInfo extends GeneratedMessageLite<LightSensorInfo, LightSensorInfo.Builder> implements LightSensorInfoOrBuilder {
        public static final int CURRENT_LUX_FIELD_NUMBER = 3;
        private static final LightSensorInfo DEFAULT_INSTANCE;
        public static final int MAX_LUX_FIELD_NUMBER = 1;
        public static final int MIN_LUX_FIELD_NUMBER = 2;
        private static volatile Parser<LightSensorInfo> PARSER;
        private double currentLux_;
        private double maxLux_;
        private double minLux_;

        public static final class Builder extends GeneratedMessageLite.Builder<LightSensorInfo, Builder> implements LightSensorInfoOrBuilder {
            private Builder() {
                super(LightSensorInfo.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearCurrentLux() {
                copyOnWrite();
                ((LightSensorInfo) this.instance).clearCurrentLux();
                return this;
            }

            public Builder clearMaxLux() {
                copyOnWrite();
                ((LightSensorInfo) this.instance).clearMaxLux();
                return this;
            }

            public Builder clearMinLux() {
                copyOnWrite();
                ((LightSensorInfo) this.instance).clearMinLux();
                return this;
            }

            @Override
            public double getCurrentLux() {
                return ((LightSensorInfo) this.instance).getCurrentLux();
            }

            @Override
            public double getMaxLux() {
                return ((LightSensorInfo) this.instance).getMaxLux();
            }

            @Override
            public double getMinLux() {
                return ((LightSensorInfo) this.instance).getMinLux();
            }

            public Builder setCurrentLux(double d2) {
                copyOnWrite();
                ((LightSensorInfo) this.instance).setCurrentLux(d2);
                return this;
            }

            public Builder setMaxLux(double d2) {
                copyOnWrite();
                ((LightSensorInfo) this.instance).setMaxLux(d2);
                return this;
            }

            public Builder setMinLux(double d2) {
                copyOnWrite();
                ((LightSensorInfo) this.instance).setMinLux(d2);
                return this;
            }
        }

        static {
            LightSensorInfo lightSensorInfo = new LightSensorInfo();
            DEFAULT_INSTANCE = lightSensorInfo;
            GeneratedMessageLite.registerDefaultInstance(LightSensorInfo.class, lightSensorInfo);
        }

        private LightSensorInfo() {
        }

        public void clearCurrentLux() {
            this.currentLux_ = 0.0d;
        }

        public void clearMaxLux() {
            this.maxLux_ = 0.0d;
        }

        public void clearMinLux() {
            this.minLux_ = 0.0d;
        }

        public static LightSensorInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(LightSensorInfo lightSensorInfo) {
            return DEFAULT_INSTANCE.createBuilder(lightSensorInfo);
        }

        public static LightSensorInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (LightSensorInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static LightSensorInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (LightSensorInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static LightSensorInfo parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (LightSensorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static LightSensorInfo parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (LightSensorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static LightSensorInfo parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (LightSensorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static LightSensorInfo parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (LightSensorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static LightSensorInfo parseFrom(InputStream inputStream) throws IOException {
            return (LightSensorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static LightSensorInfo parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (LightSensorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static LightSensorInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (LightSensorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static LightSensorInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (LightSensorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static LightSensorInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (LightSensorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static LightSensorInfo parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (LightSensorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<LightSensorInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setCurrentLux(double d2) {
            this.currentLux_ = d2;
        }

        public void setMaxLux(double d2) {
            this.maxLux_ = d2;
        }

        public void setMinLux(double d2) {
            this.minLux_ = d2;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new LightSensorInfo();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0000\u0002\u0000\u0003\u0000", new Object[]{"maxLux_", "minLux_", "currentLux_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<LightSensorInfo> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (LightSensorInfo.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public double getCurrentLux() {
            return this.currentLux_;
        }

        @Override
        public double getMaxLux() {
            return this.maxLux_;
        }

        @Override
        public double getMinLux() {
            return this.minLux_;
        }
    }

    public interface LightSensorInfoOrBuilder extends MessageLiteOrBuilder {
        double getCurrentLux();

        double getMaxLux();

        double getMinLux();
    }

    public static final class Lights extends GeneratedMessageLite<Lights, Lights.Builder> implements LightsOrBuilder {
        public static final int BACKUP_LAMP_ON_FIELD_NUMBER = 4;
        public static final int CLEARANCE_LAMP_ON_FIELD_NUMBER = 3;
        private static final Lights DEFAULT_INSTANCE;
        public static final int HIGH_BEAM_ON_FIELD_NUMBER = 2;
        public static final int LOW_BEAM_ON_FIELD_NUMBER = 1;
        private static volatile Parser<Lights> PARSER = null;
        public static final int STOP_LAMP_ON_FIELD_NUMBER = 5;
        private boolean backupLampOn_;
        private boolean clearanceLampOn_;
        private boolean highBeamOn_;
        private boolean lowBeamOn_;
        private boolean stopLampOn_;

        public static final class Builder extends GeneratedMessageLite.Builder<Lights, Builder> implements LightsOrBuilder {
            private Builder() {
                super(Lights.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearBackupLampOn() {
                copyOnWrite();
                ((Lights) this.instance).clearBackupLampOn();
                return this;
            }

            public Builder clearClearanceLampOn() {
                copyOnWrite();
                ((Lights) this.instance).clearClearanceLampOn();
                return this;
            }

            public Builder clearHighBeamOn() {
                copyOnWrite();
                ((Lights) this.instance).clearHighBeamOn();
                return this;
            }

            public Builder clearLowBeamOn() {
                copyOnWrite();
                ((Lights) this.instance).clearLowBeamOn();
                return this;
            }

            public Builder clearStopLampOn() {
                copyOnWrite();
                ((Lights) this.instance).clearStopLampOn();
                return this;
            }

            @Override
            public boolean getBackupLampOn() {
                return ((Lights) this.instance).getBackupLampOn();
            }

            @Override
            public boolean getClearanceLampOn() {
                return ((Lights) this.instance).getClearanceLampOn();
            }

            @Override
            public boolean getHighBeamOn() {
                return ((Lights) this.instance).getHighBeamOn();
            }

            @Override
            public boolean getLowBeamOn() {
                return ((Lights) this.instance).getLowBeamOn();
            }

            @Override
            public boolean getStopLampOn() {
                return ((Lights) this.instance).getStopLampOn();
            }

            public Builder setBackupLampOn(boolean z) {
                copyOnWrite();
                ((Lights) this.instance).setBackupLampOn(z);
                return this;
            }

            public Builder setClearanceLampOn(boolean z) {
                copyOnWrite();
                ((Lights) this.instance).setClearanceLampOn(z);
                return this;
            }

            public Builder setHighBeamOn(boolean z) {
                copyOnWrite();
                ((Lights) this.instance).setHighBeamOn(z);
                return this;
            }

            public Builder setLowBeamOn(boolean z) {
                copyOnWrite();
                ((Lights) this.instance).setLowBeamOn(z);
                return this;
            }

            public Builder setStopLampOn(boolean z) {
                copyOnWrite();
                ((Lights) this.instance).setStopLampOn(z);
                return this;
            }
        }

        static {
            Lights lights = new Lights();
            DEFAULT_INSTANCE = lights;
            GeneratedMessageLite.registerDefaultInstance(Lights.class, lights);
        }

        private Lights() {
        }

        public void clearBackupLampOn() {
            this.backupLampOn_ = false;
        }

        public void clearClearanceLampOn() {
            this.clearanceLampOn_ = false;
        }

        public void clearHighBeamOn() {
            this.highBeamOn_ = false;
        }

        public void clearLowBeamOn() {
            this.lowBeamOn_ = false;
        }

        public void clearStopLampOn() {
            this.stopLampOn_ = false;
        }

        public static Lights getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Lights lights) {
            return DEFAULT_INSTANCE.createBuilder(lights);
        }

        public static Lights parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Lights) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Lights parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (Lights) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static Lights parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (Lights) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static Lights parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Lights) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static Lights parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (Lights) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static Lights parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (Lights) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static Lights parseFrom(InputStream inputStream) throws IOException {
            return (Lights) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Lights parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (Lights) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static Lights parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Lights) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Lights parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Lights) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static Lights parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Lights) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Lights parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Lights) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<Lights> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setBackupLampOn(boolean z) {
            this.backupLampOn_ = z;
        }

        public void setClearanceLampOn(boolean z) {
            this.clearanceLampOn_ = z;
        }

        public void setHighBeamOn(boolean z) {
            this.highBeamOn_ = z;
        }

        public void setLowBeamOn(boolean z) {
            this.lowBeamOn_ = z;
        }

        public void setStopLampOn(boolean z) {
            this.stopLampOn_ = z;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new Lights();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001\u0007\u0002\u0007\u0003\u0007\u0004\u0007\u0005\u0007", new Object[]{"lowBeamOn_", "highBeamOn_", "clearanceLampOn_", "backupLampOn_", "stopLampOn_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Lights> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (Lights.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public boolean getBackupLampOn() {
            return this.backupLampOn_;
        }

        @Override
        public boolean getClearanceLampOn() {
            return this.clearanceLampOn_;
        }

        @Override
        public boolean getHighBeamOn() {
            return this.highBeamOn_;
        }

        @Override
        public boolean getLowBeamOn() {
            return this.lowBeamOn_;
        }

        @Override
        public boolean getStopLampOn() {
            return this.stopLampOn_;
        }
    }

    public interface LightsOrBuilder extends MessageLiteOrBuilder {
        boolean getBackupLampOn();

        boolean getClearanceLampOn();

        boolean getHighBeamOn();

        boolean getLowBeamOn();

        boolean getStopLampOn();
    }

    public static final class NotifyAddCamera extends GeneratedMessageLite<NotifyAddCamera, NotifyAddCamera.Builder> implements NotifyAddCameraOrBuilder {
        public static final int CAMERA_ID_FIELD_NUMBER = 1;
        private static final NotifyAddCamera DEFAULT_INSTANCE;
        public static final int FPS_RANGES_FIELD_NUMBER = 6;
        public static final int LENS_FACING_FIELD_NUMBER = 3;
        public static final int MAX_SIZE_FIELD_NUMBER = 7;
        public static final int NAME_FIELD_NUMBER = 2;
        public static final int ORIENTATION_FIELD_NUMBER = 4;
        private static volatile Parser<NotifyAddCamera> PARSER = null;
        public static final int SUPPORTED_SIZES_FIELD_NUMBER = 5;
        private int lensFacing_;
        private PictureSize maxSize_;
        private int orientation_;
        private String cameraId_ = "";
        private String name_ = "";
        private Internal.ProtobufList<PictureSize> supportedSizes_ = GeneratedMessageLite.emptyProtobufList();
        private Internal.ProtobufList<FpsRange> fpsRanges_ = GeneratedMessageLite.emptyProtobufList();

        public static final class Builder extends GeneratedMessageLite.Builder<NotifyAddCamera, Builder> implements NotifyAddCameraOrBuilder {
            private Builder() {
                super(NotifyAddCamera.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder addAllFpsRanges(Iterable<? extends FpsRange> iterable) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).addAllFpsRanges(iterable);
                return this;
            }

            public Builder addAllSupportedSizes(Iterable<? extends PictureSize> iterable) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).addAllSupportedSizes(iterable);
                return this;
            }

            public Builder addFpsRanges(int i, FpsRange.Builder builder) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).addFpsRanges(i, builder.build());
                return this;
            }

            public Builder addFpsRanges(int i, FpsRange fpsRange) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).addFpsRanges(i, fpsRange);
                return this;
            }

            public Builder addFpsRanges(FpsRange.Builder builder) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).addFpsRanges(builder.build());
                return this;
            }

            public Builder addFpsRanges(FpsRange fpsRange) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).addFpsRanges(fpsRange);
                return this;
            }

            public Builder addSupportedSizes(int i, PictureSize.Builder builder) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).addSupportedSizes(i, builder.build());
                return this;
            }

            public Builder addSupportedSizes(int i, PictureSize pictureSize) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).addSupportedSizes(i, pictureSize);
                return this;
            }

            public Builder addSupportedSizes(PictureSize.Builder builder) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).addSupportedSizes(builder.build());
                return this;
            }

            public Builder addSupportedSizes(PictureSize pictureSize) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).addSupportedSizes(pictureSize);
                return this;
            }

            public Builder clearCameraId() {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).clearCameraId();
                return this;
            }

            public Builder clearFpsRanges() {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).clearFpsRanges();
                return this;
            }

            public Builder clearLensFacing() {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).clearLensFacing();
                return this;
            }

            public Builder clearMaxSize() {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).clearMaxSize();
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).clearName();
                return this;
            }

            public Builder clearOrientation() {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).clearOrientation();
                return this;
            }

            public Builder clearSupportedSizes() {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).clearSupportedSizes();
                return this;
            }

            @Override
            public String getCameraId() {
                return ((NotifyAddCamera) this.instance).getCameraId();
            }

            @Override
            public ByteString getCameraIdBytes() {
                return ((NotifyAddCamera) this.instance).getCameraIdBytes();
            }

            @Override
            public FpsRange getFpsRanges(int i) {
                return ((NotifyAddCamera) this.instance).getFpsRanges(i);
            }

            @Override
            public int getFpsRangesCount() {
                return ((NotifyAddCamera) this.instance).getFpsRangesCount();
            }

            @Override
            public List<FpsRange> getFpsRangesList() {
                return Collections.unmodifiableList(((NotifyAddCamera) this.instance).getFpsRangesList());
            }

            @Override
            public LensFacing getLensFacing() {
                return ((NotifyAddCamera) this.instance).getLensFacing();
            }

            @Override
            public int getLensFacingValue() {
                return ((NotifyAddCamera) this.instance).getLensFacingValue();
            }

            @Override
            public PictureSize getMaxSize() {
                return ((NotifyAddCamera) this.instance).getMaxSize();
            }

            @Override
            public String getName() {
                return ((NotifyAddCamera) this.instance).getName();
            }

            @Override
            public ByteString getNameBytes() {
                return ((NotifyAddCamera) this.instance).getNameBytes();
            }

            @Override
            public Orientation getOrientation() {
                return ((NotifyAddCamera) this.instance).getOrientation();
            }

            @Override
            public int getOrientationValue() {
                return ((NotifyAddCamera) this.instance).getOrientationValue();
            }

            @Override
            public PictureSize getSupportedSizes(int i) {
                return ((NotifyAddCamera) this.instance).getSupportedSizes(i);
            }

            @Override
            public int getSupportedSizesCount() {
                return ((NotifyAddCamera) this.instance).getSupportedSizesCount();
            }

            @Override
            public List<PictureSize> getSupportedSizesList() {
                return Collections.unmodifiableList(((NotifyAddCamera) this.instance).getSupportedSizesList());
            }

            @Override
            public boolean hasMaxSize() {
                return ((NotifyAddCamera) this.instance).hasMaxSize();
            }

            public Builder mergeMaxSize(PictureSize pictureSize) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).mergeMaxSize(pictureSize);
                return this;
            }

            public Builder removeFpsRanges(int i) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).removeFpsRanges(i);
                return this;
            }

            public Builder removeSupportedSizes(int i) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).removeSupportedSizes(i);
                return this;
            }

            public Builder setCameraId(String str) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).setCameraId(str);
                return this;
            }

            public Builder setCameraIdBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).setCameraIdBytes(abstractC2534u);
                return this;
            }

            public Builder setFpsRanges(int i, FpsRange.Builder builder) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).setFpsRanges(i, builder.build());
                return this;
            }

            public Builder setFpsRanges(int i, FpsRange fpsRange) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).setFpsRanges(i, fpsRange);
                return this;
            }

            public Builder setLensFacing(LensFacing lensFacing) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).setLensFacing(lensFacing);
                return this;
            }

            public Builder setLensFacingValue(int i) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).setLensFacingValue(i);
                return this;
            }

            public Builder setMaxSize(PictureSize.Builder builder) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).setMaxSize(builder.build());
                return this;
            }

            public Builder setMaxSize(PictureSize pictureSize) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).setMaxSize(pictureSize);
                return this;
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).setNameBytes(abstractC2534u);
                return this;
            }

            public Builder setOrientation(Orientation orientation) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).setOrientation(orientation);
                return this;
            }

            public Builder setOrientationValue(int i) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).setOrientationValue(i);
                return this;
            }

            public Builder setSupportedSizes(int i, PictureSize.Builder builder) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).setSupportedSizes(i, builder.build());
                return this;
            }

            public Builder setSupportedSizes(int i, PictureSize pictureSize) {
                copyOnWrite();
                ((NotifyAddCamera) this.instance).setSupportedSizes(i, pictureSize);
                return this;
            }
        }

        static {
            NotifyAddCamera notifyAddCamera = new NotifyAddCamera();
            DEFAULT_INSTANCE = notifyAddCamera;
            GeneratedMessageLite.registerDefaultInstance(NotifyAddCamera.class, notifyAddCamera);
        }

        private NotifyAddCamera() {
        }

        public void addAllFpsRanges(Iterable<? extends FpsRange> iterable) {
            ensureFpsRangesIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.fpsRanges_);
        }

        public void addAllSupportedSizes(Iterable<? extends PictureSize> iterable) {
            ensureSupportedSizesIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.supportedSizes_);
        }

        public void addFpsRanges(int i, FpsRange fpsRange) {
            fpsRange.getClass();
            ensureFpsRangesIsMutable();
            this.fpsRanges_.add(i, fpsRange);
        }

        public void addFpsRanges(FpsRange fpsRange) {
            fpsRange.getClass();
            ensureFpsRangesIsMutable();
            this.fpsRanges_.add(fpsRange);
        }

        public void addSupportedSizes(int i, PictureSize pictureSize) {
            pictureSize.getClass();
            ensureSupportedSizesIsMutable();
            this.supportedSizes_.add(i, pictureSize);
        }

        public void addSupportedSizes(PictureSize pictureSize) {
            pictureSize.getClass();
            ensureSupportedSizesIsMutable();
            this.supportedSizes_.add(pictureSize);
        }

        public void clearCameraId() {
            this.cameraId_ = getDefaultInstance().getCameraId();
        }

        public void clearFpsRanges() {
            this.fpsRanges_ = GeneratedMessageLite.emptyProtobufList();
        }

        public void clearLensFacing() {
            this.lensFacing_ = 0;
        }

        public void clearMaxSize() {
            this.maxSize_ = null;
        }

        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        public void clearOrientation() {
            this.orientation_ = 0;
        }

        public void clearSupportedSizes() {
            this.supportedSizes_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureFpsRangesIsMutable() {
            Internal.ProtobufList<FpsRange> kVar = this.fpsRanges_;
            if (kVar.isModifiable()) {
                return;
            }
            this.fpsRanges_ = GeneratedMessageLite.mutableCopy(kVar);
        }

        private void ensureSupportedSizesIsMutable() {
            Internal.ProtobufList<PictureSize> kVar = this.supportedSizes_;
            if (kVar.isModifiable()) {
                return;
            }
            this.supportedSizes_ = GeneratedMessageLite.mutableCopy(kVar);
        }

        public static NotifyAddCamera getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public void mergeMaxSize(PictureSize pictureSize) {
            pictureSize.getClass();
            PictureSize pictureSize2 = this.maxSize_;
            if (pictureSize2 != null && pictureSize2 != PictureSize.getDefaultInstance()) {
                pictureSize = PictureSize.newBuilder(this.maxSize_).mergeFrom(pictureSize).buildPartial();
            }
            this.maxSize_ = pictureSize;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(NotifyAddCamera notifyAddCamera) {
            return DEFAULT_INSTANCE.createBuilder(notifyAddCamera);
        }

        public static NotifyAddCamera parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyAddCamera) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyAddCamera parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyAddCamera) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyAddCamera parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (NotifyAddCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static NotifyAddCamera parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyAddCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static NotifyAddCamera parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (NotifyAddCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static NotifyAddCamera parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyAddCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static NotifyAddCamera parseFrom(InputStream inputStream) throws IOException {
            return (NotifyAddCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyAddCamera parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyAddCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyAddCamera parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyAddCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static NotifyAddCamera parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyAddCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static NotifyAddCamera parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyAddCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyAddCamera parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyAddCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<NotifyAddCamera> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void removeFpsRanges(int i) {
            ensureFpsRangesIsMutable();
            this.fpsRanges_.remove(i);
        }

        public void removeSupportedSizes(int i) {
            ensureSupportedSizesIsMutable();
            this.supportedSizes_.remove(i);
        }

        public void setCameraId(String str) {
            str.getClass();
            this.cameraId_ = str;
        }

        public void setCameraIdBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.cameraId_ = abstractC2534u.toStringUtf8();
        }

        public void setFpsRanges(int i, FpsRange fpsRange) {
            fpsRange.getClass();
            ensureFpsRangesIsMutable();
            this.fpsRanges_.set(i, fpsRange);
        }

        public void setLensFacing(LensFacing lensFacing) {
            this.lensFacing_ = lensFacing.getNumber();
        }

        public void setLensFacingValue(int i) {
            this.lensFacing_ = i;
        }

        public void setMaxSize(PictureSize pictureSize) {
            pictureSize.getClass();
            this.maxSize_ = pictureSize;
        }

        public void setName(String str) {
            str.getClass();
            this.name_ = str;
        }

        public void setNameBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.name_ = abstractC2534u.toStringUtf8();
        }

        public void setOrientation(Orientation orientation) {
            this.orientation_ = orientation.getNumber();
        }

        public void setOrientationValue(int i) {
            this.orientation_ = i;
        }

        public void setSupportedSizes(int i, PictureSize pictureSize) {
            pictureSize.getClass();
            ensureSupportedSizesIsMutable();
            this.supportedSizes_.set(i, pictureSize);
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new NotifyAddCamera();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0002\u0000\u0001Ȉ\u0002Ȉ\u0003\f\u0004\f\u0005\u001b\u0006\u001b\u0007\t", new Object[]{"cameraId_", "name_", "lensFacing_", "orientation_", "supportedSizes_", PictureSize.class, "fpsRanges_", FpsRange.class, "maxSize_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<NotifyAddCamera> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (NotifyAddCamera.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public String getCameraId() {
            return this.cameraId_;
        }

        @Override
        public ByteString getCameraIdBytes() {
            return ByteString.copyFromUtf8(this.cameraId_);
        }

        @Override
        public FpsRange getFpsRanges(int i) {
            return this.fpsRanges_.get(i);
        }

        @Override
        public int getFpsRangesCount() {
            return this.fpsRanges_.size();
        }

        @Override
        public List<FpsRange> getFpsRangesList() {
            return this.fpsRanges_;
        }

        public FpsRangeOrBuilder getFpsRangesOrBuilder(int i) {
            return this.fpsRanges_.get(i);
        }

        public List<? extends FpsRangeOrBuilder> getFpsRangesOrBuilderList() {
            return this.fpsRanges_;
        }

        @Override
        public LensFacing getLensFacing() {
            LensFacing lensFacingForNumber = LensFacing.forNumber(this.lensFacing_);
            return lensFacingForNumber == null ? LensFacing.UNRECOGNIZED : lensFacingForNumber;
        }

        @Override
        public int getLensFacingValue() {
            return this.lensFacing_;
        }

        @Override
        public PictureSize getMaxSize() {
            PictureSize pictureSize = this.maxSize_;
            return pictureSize == null ? PictureSize.getDefaultInstance() : pictureSize;
        }

        @Override
        public String getName() {
            return this.name_;
        }

        @Override
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        @Override
        public Orientation getOrientation() {
            Orientation orientationForNumber = Orientation.forNumber(this.orientation_);
            return orientationForNumber == null ? Orientation.UNRECOGNIZED : orientationForNumber;
        }

        @Override
        public int getOrientationValue() {
            return this.orientation_;
        }

        @Override
        public PictureSize getSupportedSizes(int i) {
            return this.supportedSizes_.get(i);
        }

        @Override
        public int getSupportedSizesCount() {
            return this.supportedSizes_.size();
        }

        @Override
        public List<PictureSize> getSupportedSizesList() {
            return this.supportedSizes_;
        }

        public PictureSizeOrBuilder getSupportedSizesOrBuilder(int i) {
            return this.supportedSizes_.get(i);
        }

        public List<? extends PictureSizeOrBuilder> getSupportedSizesOrBuilderList() {
            return this.supportedSizes_;
        }

        @Override
        public boolean hasMaxSize() {
            return this.maxSize_ != null;
        }
    }

    public interface NotifyAddCameraOrBuilder extends MessageLiteOrBuilder {
        String getCameraId();

        ByteString getCameraIdBytes();

        FpsRange getFpsRanges(int i);

        int getFpsRangesCount();

        List<FpsRange> getFpsRangesList();

        LensFacing getLensFacing();

        int getLensFacingValue();

        PictureSize getMaxSize();

        String getName();

        ByteString getNameBytes();

        Orientation getOrientation();

        int getOrientationValue();

        PictureSize getSupportedSizes(int i);

        int getSupportedSizesCount();

        List<PictureSize> getSupportedSizesList();

        boolean hasMaxSize();
    }

    public static final class NotifyAudioPlayerState extends GeneratedMessageLite<NotifyAudioPlayerState, NotifyAudioPlayerState.Builder> implements NotifyAudioPlayerStateOrBuilder {
        public static final int CHANNEL_MASK_FIELD_NUMBER = 4;
        private static final NotifyAudioPlayerState DEFAULT_INSTANCE;
        public static final int ENCODING_FORMAT_FIELD_NUMBER = 5;
        private static volatile Parser<NotifyAudioPlayerState> PARSER = null;
        public static final int SAMPLE_RATE_FIELD_NUMBER = 3;
        public static final int STATE_FIELD_NUMBER = 2;
        public static final int TYPE_FIELD_NUMBER = 1;
        private int channelMask_;
        private int encodingFormat_;
        private int sampleRate_;
        private int state_;
        private int type_;

        public enum AudioPlayerState implements Internal.EnumLite {
            START_PLAYER(0),
            STOP_PLAYER(1),
            PAUSE_PLAYER(2),
            RESUME_PLAYER(3),
            UNRECOGNIZED(-1);

            public static final int PAUSE_PLAYER_VALUE = 2;
            public static final int RESUME_PLAYER_VALUE = 3;
            public static final int START_PLAYER_VALUE = 0;
            public static final int STOP_PLAYER_VALUE = 1;
            private static final Internal.EnumLiteMap<AudioPlayerState> internalValueMap = new Internal.EnumLiteMap<AudioPlayerState>() {
                @Override
                public AudioPlayerState findValueByNumber(int i) {
                    return AudioPlayerState.forNumber(i);
                }
            };
            private final int value;

            public static final class AudioPlayerStateVerifier implements Internal.EnumVerifier {
                public static final Internal.EnumVerifier INSTANCE = new AudioPlayerStateVerifier();

                private AudioPlayerStateVerifier() {
                }

                @Override
                public boolean isInRange(int i) {
                    return AudioPlayerState.forNumber(i) != null;
                }
            }

            AudioPlayerState(int i) {
                this.value = i;
            }

            public static AudioPlayerState forNumber(int i) {
                if (i == 0) {
                    return START_PLAYER;
                }
                if (i == 1) {
                    return STOP_PLAYER;
                }
                if (i == 2) {
                    return PAUSE_PLAYER;
                }
                if (i != 3) {
                    return null;
                }
                return RESUME_PLAYER;
            }

            public static Internal.EnumLiteMap<AudioPlayerState> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return AudioPlayerStateVerifier.INSTANCE;
            }

            @Deprecated
            public static AudioPlayerState valueOf(int i) {
                return forNumber(i);
            }

            @Override
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
        }

        public static final class Builder extends GeneratedMessageLite.Builder<NotifyAudioPlayerState, Builder> implements NotifyAudioPlayerStateOrBuilder {
            private Builder() {
                super(NotifyAudioPlayerState.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearChannelMask() {
                copyOnWrite();
                ((NotifyAudioPlayerState) this.instance).clearChannelMask();
                return this;
            }

            public Builder clearEncodingFormat() {
                copyOnWrite();
                ((NotifyAudioPlayerState) this.instance).clearEncodingFormat();
                return this;
            }

            public Builder clearSampleRate() {
                copyOnWrite();
                ((NotifyAudioPlayerState) this.instance).clearSampleRate();
                return this;
            }

            public Builder clearState() {
                copyOnWrite();
                ((NotifyAudioPlayerState) this.instance).clearState();
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((NotifyAudioPlayerState) this.instance).clearType();
                return this;
            }

            @Override
            public ChannelMask getChannelMask() {
                return ((NotifyAudioPlayerState) this.instance).getChannelMask();
            }

            @Override
            public int getChannelMaskValue() {
                return ((NotifyAudioPlayerState) this.instance).getChannelMaskValue();
            }

            @Override
            public EncodingFormat getEncodingFormat() {
                return ((NotifyAudioPlayerState) this.instance).getEncodingFormat();
            }

            @Override
            public int getEncodingFormatValue() {
                return ((NotifyAudioPlayerState) this.instance).getEncodingFormatValue();
            }

            @Override
            public SampleRate getSampleRate() {
                return ((NotifyAudioPlayerState) this.instance).getSampleRate();
            }

            @Override
            public int getSampleRateValue() {
                return ((NotifyAudioPlayerState) this.instance).getSampleRateValue();
            }

            @Override
            public AudioPlayerState getState() {
                return ((NotifyAudioPlayerState) this.instance).getState();
            }

            @Override
            public int getStateValue() {
                return ((NotifyAudioPlayerState) this.instance).getStateValue();
            }

            @Override
            public AudioType getType() {
                return ((NotifyAudioPlayerState) this.instance).getType();
            }

            @Override
            public int getTypeValue() {
                return ((NotifyAudioPlayerState) this.instance).getTypeValue();
            }

            public Builder setChannelMask(ChannelMask channelMask) {
                copyOnWrite();
                ((NotifyAudioPlayerState) this.instance).setChannelMask(channelMask);
                return this;
            }

            public Builder setChannelMaskValue(int i) {
                copyOnWrite();
                ((NotifyAudioPlayerState) this.instance).setChannelMaskValue(i);
                return this;
            }

            public Builder setEncodingFormat(EncodingFormat encodingFormat) {
                copyOnWrite();
                ((NotifyAudioPlayerState) this.instance).setEncodingFormat(encodingFormat);
                return this;
            }

            public Builder setEncodingFormatValue(int i) {
                copyOnWrite();
                ((NotifyAudioPlayerState) this.instance).setEncodingFormatValue(i);
                return this;
            }

            public Builder setSampleRate(SampleRate sampleRate) {
                copyOnWrite();
                ((NotifyAudioPlayerState) this.instance).setSampleRate(sampleRate);
                return this;
            }

            public Builder setSampleRateValue(int i) {
                copyOnWrite();
                ((NotifyAudioPlayerState) this.instance).setSampleRateValue(i);
                return this;
            }

            public Builder setState(AudioPlayerState audioPlayerState) {
                copyOnWrite();
                ((NotifyAudioPlayerState) this.instance).setState(audioPlayerState);
                return this;
            }

            public Builder setStateValue(int i) {
                copyOnWrite();
                ((NotifyAudioPlayerState) this.instance).setStateValue(i);
                return this;
            }

            public Builder setType(AudioType audioType) {
                copyOnWrite();
                ((NotifyAudioPlayerState) this.instance).setType(audioType);
                return this;
            }

            public Builder setTypeValue(int i) {
                copyOnWrite();
                ((NotifyAudioPlayerState) this.instance).setTypeValue(i);
                return this;
            }
        }

        static {
            NotifyAudioPlayerState notifyAudioPlayerState = new NotifyAudioPlayerState();
            DEFAULT_INSTANCE = notifyAudioPlayerState;
            GeneratedMessageLite.registerDefaultInstance(NotifyAudioPlayerState.class, notifyAudioPlayerState);
        }

        private NotifyAudioPlayerState() {
        }

        public void clearChannelMask() {
            this.channelMask_ = 0;
        }

        public void clearEncodingFormat() {
            this.encodingFormat_ = 0;
        }

        public void clearSampleRate() {
            this.sampleRate_ = 0;
        }

        public void clearState() {
            this.state_ = 0;
        }

        public void clearType() {
            this.type_ = 0;
        }

        public static NotifyAudioPlayerState getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(NotifyAudioPlayerState notifyAudioPlayerState) {
            return DEFAULT_INSTANCE.createBuilder(notifyAudioPlayerState);
        }

        public static NotifyAudioPlayerState parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyAudioPlayerState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyAudioPlayerState parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyAudioPlayerState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyAudioPlayerState parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (NotifyAudioPlayerState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static NotifyAudioPlayerState parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyAudioPlayerState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static NotifyAudioPlayerState parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (NotifyAudioPlayerState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static NotifyAudioPlayerState parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyAudioPlayerState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static NotifyAudioPlayerState parseFrom(InputStream inputStream) throws IOException {
            return (NotifyAudioPlayerState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyAudioPlayerState parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyAudioPlayerState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyAudioPlayerState parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyAudioPlayerState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static NotifyAudioPlayerState parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyAudioPlayerState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static NotifyAudioPlayerState parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyAudioPlayerState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyAudioPlayerState parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyAudioPlayerState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<NotifyAudioPlayerState> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setChannelMask(ChannelMask channelMask) {
            this.channelMask_ = channelMask.getNumber();
        }

        public void setChannelMaskValue(int i) {
            this.channelMask_ = i;
        }

        public void setEncodingFormat(EncodingFormat encodingFormat) {
            this.encodingFormat_ = encodingFormat.getNumber();
        }

        public void setEncodingFormatValue(int i) {
            this.encodingFormat_ = i;
        }

        public void setSampleRate(SampleRate sampleRate) {
            this.sampleRate_ = sampleRate.getNumber();
        }

        public void setSampleRateValue(int i) {
            this.sampleRate_ = i;
        }

        public void setState(AudioPlayerState audioPlayerState) {
            this.state_ = audioPlayerState.getNumber();
        }

        public void setStateValue(int i) {
            this.state_ = i;
        }

        public void setType(AudioType audioType) {
            this.type_ = audioType.getNumber();
        }

        public void setTypeValue(int i) {
            this.type_ = i;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new NotifyAudioPlayerState();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001\f\u0002\f\u0003\f\u0004\f\u0005\f", new Object[]{"type_", "state_", "sampleRate_", "channelMask_", "encodingFormat_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<NotifyAudioPlayerState> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (NotifyAudioPlayerState.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public ChannelMask getChannelMask() {
            ChannelMask channelMaskForNumber = ChannelMask.forNumber(this.channelMask_);
            return channelMaskForNumber == null ? ChannelMask.UNRECOGNIZED : channelMaskForNumber;
        }

        @Override
        public int getChannelMaskValue() {
            return this.channelMask_;
        }

        @Override
        public EncodingFormat getEncodingFormat() {
            EncodingFormat encodingFormatForNumber = EncodingFormat.forNumber(this.encodingFormat_);
            return encodingFormatForNumber == null ? EncodingFormat.UNRECOGNIZED : encodingFormatForNumber;
        }

        @Override
        public int getEncodingFormatValue() {
            return this.encodingFormat_;
        }

        @Override
        public SampleRate getSampleRate() {
            SampleRate sampleRateForNumber = SampleRate.forNumber(this.sampleRate_);
            return sampleRateForNumber == null ? SampleRate.UNRECOGNIZED : sampleRateForNumber;
        }

        @Override
        public int getSampleRateValue() {
            return this.sampleRate_;
        }

        @Override
        public AudioPlayerState getState() {
            AudioPlayerState audioPlayerStateForNumber = AudioPlayerState.forNumber(this.state_);
            return audioPlayerStateForNumber == null ? AudioPlayerState.UNRECOGNIZED : audioPlayerStateForNumber;
        }

        @Override
        public int getStateValue() {
            return this.state_;
        }

        @Override
        public AudioType getType() {
            AudioType audioTypeForNumber = AudioType.forNumber(this.type_);
            return audioTypeForNumber == null ? AudioType.UNRECOGNIZED : audioTypeForNumber;
        }

        @Override
        public int getTypeValue() {
            return this.type_;
        }
    }

    public interface NotifyAudioPlayerStateOrBuilder extends MessageLiteOrBuilder {
        ChannelMask getChannelMask();

        int getChannelMaskValue();

        EncodingFormat getEncodingFormat();

        int getEncodingFormatValue();

        SampleRate getSampleRate();

        int getSampleRateValue();

        NotifyAudioPlayerState.AudioPlayerState getState();

        int getStateValue();

        AudioType getType();

        int getTypeValue();
    }

    public static final class NotifyCallHungUp extends GeneratedMessageLite<NotifyCallHungUp, NotifyCallHungUp.Builder> implements NotifyCallHungUpOrBuilder {
        private static final NotifyCallHungUp DEFAULT_INSTANCE;
        private static volatile Parser<NotifyCallHungUp> PARSER = null;
        public static final int TIMESTAMP_FIELD_NUMBER = 1;
        private long timestamp_;

        public static final class Builder extends GeneratedMessageLite.Builder<NotifyCallHungUp, Builder> implements NotifyCallHungUpOrBuilder {
            private Builder() {
                super(NotifyCallHungUp.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearTimestamp() {
                copyOnWrite();
                ((NotifyCallHungUp) this.instance).clearTimestamp();
                return this;
            }

            @Override
            public long getTimestamp() {
                return ((NotifyCallHungUp) this.instance).getTimestamp();
            }

            public Builder setTimestamp(long j) {
                copyOnWrite();
                ((NotifyCallHungUp) this.instance).setTimestamp(j);
                return this;
            }
        }

        static {
            NotifyCallHungUp notifyCallHungUp = new NotifyCallHungUp();
            DEFAULT_INSTANCE = notifyCallHungUp;
            GeneratedMessageLite.registerDefaultInstance(NotifyCallHungUp.class, notifyCallHungUp);
        }

        private NotifyCallHungUp() {
        }

        public void clearTimestamp() {
            this.timestamp_ = 0L;
        }

        public static NotifyCallHungUp getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(NotifyCallHungUp notifyCallHungUp) {
            return DEFAULT_INSTANCE.createBuilder(notifyCallHungUp);
        }

        public static NotifyCallHungUp parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyCallHungUp) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyCallHungUp parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyCallHungUp) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyCallHungUp parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (NotifyCallHungUp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static NotifyCallHungUp parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyCallHungUp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static NotifyCallHungUp parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (NotifyCallHungUp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static NotifyCallHungUp parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyCallHungUp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static NotifyCallHungUp parseFrom(InputStream inputStream) throws IOException {
            return (NotifyCallHungUp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyCallHungUp parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyCallHungUp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyCallHungUp parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyCallHungUp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static NotifyCallHungUp parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyCallHungUp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static NotifyCallHungUp parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyCallHungUp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyCallHungUp parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyCallHungUp) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<NotifyCallHungUp> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setTimestamp(long j) {
            this.timestamp_ = j;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new NotifyCallHungUp();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0003", new Object[]{"timestamp_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<NotifyCallHungUp> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (NotifyCallHungUp.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public long getTimestamp() {
            return this.timestamp_;
        }
    }

    public interface NotifyCallHungUpOrBuilder extends MessageLiteOrBuilder {
        long getTimestamp();
    }

    public static final class NotifyCameraStateChanged extends GeneratedMessageLite<NotifyCameraStateChanged, NotifyCameraStateChanged.Builder> implements NotifyCameraStateChangedOrBuilder {
        public static final int CAMERA_ID_FIELD_NUMBER = 1;
        private static final NotifyCameraStateChanged DEFAULT_INSTANCE;
        private static volatile Parser<NotifyCameraStateChanged> PARSER = null;
        public static final int STATE_FIELD_NUMBER = 2;
        private String cameraId_ = "";
        private int state_;

        public static final class Builder extends GeneratedMessageLite.Builder<NotifyCameraStateChanged, Builder> implements NotifyCameraStateChangedOrBuilder {
            private Builder() {
                super(NotifyCameraStateChanged.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearCameraId() {
                copyOnWrite();
                ((NotifyCameraStateChanged) this.instance).clearCameraId();
                return this;
            }

            public Builder clearState() {
                copyOnWrite();
                ((NotifyCameraStateChanged) this.instance).clearState();
                return this;
            }

            @Override
            public String getCameraId() {
                return ((NotifyCameraStateChanged) this.instance).getCameraId();
            }

            @Override
            public ByteString getCameraIdBytes() {
                return ((NotifyCameraStateChanged) this.instance).getCameraIdBytes();
            }

            @Override
            public State getState() {
                return ((NotifyCameraStateChanged) this.instance).getState();
            }

            @Override
            public int getStateValue() {
                return ((NotifyCameraStateChanged) this.instance).getStateValue();
            }

            public Builder setCameraId(String str) {
                copyOnWrite();
                ((NotifyCameraStateChanged) this.instance).setCameraId(str);
                return this;
            }

            public Builder setCameraIdBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyCameraStateChanged) this.instance).setCameraIdBytes(abstractC2534u);
                return this;
            }

            public Builder setState(State state) {
                copyOnWrite();
                ((NotifyCameraStateChanged) this.instance).setState(state);
                return this;
            }

            public Builder setStateValue(int i) {
                copyOnWrite();
                ((NotifyCameraStateChanged) this.instance).setStateValue(i);
                return this;
            }
        }

        public enum State implements Internal.EnumLite {
            OPENED(0),
            CLOSED(1),
            ERROR(2),
            PREEMPTED(3),
            NO_PERMISSION(4),
            BUSY(5),
            UNRECOGNIZED(-1);

            public static final int BUSY_VALUE = 5;
            public static final int CLOSED_VALUE = 1;
            public static final int ERROR_VALUE = 2;
            public static final int NO_PERMISSION_VALUE = 4;
            public static final int OPENED_VALUE = 0;
            public static final int PREEMPTED_VALUE = 3;
            private static final Internal.EnumLiteMap<State> internalValueMap = new Internal.EnumLiteMap<State>() {
                @Override
                public State findValueByNumber(int i) {
                    return State.forNumber(i);
                }
            };
            private final int value;

            public static final class StateVerifier implements Internal.EnumVerifier {
                public static final Internal.EnumVerifier INSTANCE = new StateVerifier();

                private StateVerifier() {
                }

                @Override
                public boolean isInRange(int i) {
                    return State.forNumber(i) != null;
                }
            }

            State(int i) {
                this.value = i;
            }

            public static State forNumber(int i) {
                if (i == 0) {
                    return OPENED;
                }
                if (i == 1) {
                    return CLOSED;
                }
                if (i == 2) {
                    return ERROR;
                }
                if (i == 3) {
                    return PREEMPTED;
                }
                if (i == 4) {
                    return NO_PERMISSION;
                }
                if (i != 5) {
                    return null;
                }
                return BUSY;
            }

            public static Internal.EnumLiteMap<State> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return StateVerifier.INSTANCE;
            }

            @Deprecated
            public static State valueOf(int i) {
                return forNumber(i);
            }

            @Override
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
        }

        static {
            NotifyCameraStateChanged notifyCameraStateChanged = new NotifyCameraStateChanged();
            DEFAULT_INSTANCE = notifyCameraStateChanged;
            GeneratedMessageLite.registerDefaultInstance(NotifyCameraStateChanged.class, notifyCameraStateChanged);
        }

        private NotifyCameraStateChanged() {
        }

        public void clearCameraId() {
            this.cameraId_ = getDefaultInstance().getCameraId();
        }

        public void clearState() {
            this.state_ = 0;
        }

        public static NotifyCameraStateChanged getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(NotifyCameraStateChanged notifyCameraStateChanged) {
            return DEFAULT_INSTANCE.createBuilder(notifyCameraStateChanged);
        }

        public static NotifyCameraStateChanged parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyCameraStateChanged) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyCameraStateChanged parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyCameraStateChanged) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyCameraStateChanged parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (NotifyCameraStateChanged) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static NotifyCameraStateChanged parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyCameraStateChanged) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static NotifyCameraStateChanged parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (NotifyCameraStateChanged) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static NotifyCameraStateChanged parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyCameraStateChanged) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static NotifyCameraStateChanged parseFrom(InputStream inputStream) throws IOException {
            return (NotifyCameraStateChanged) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyCameraStateChanged parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyCameraStateChanged) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyCameraStateChanged parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyCameraStateChanged) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static NotifyCameraStateChanged parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyCameraStateChanged) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static NotifyCameraStateChanged parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyCameraStateChanged) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyCameraStateChanged parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyCameraStateChanged) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<NotifyCameraStateChanged> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setCameraId(String str) {
            str.getClass();
            this.cameraId_ = str;
        }

        public void setCameraIdBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.cameraId_ = abstractC2534u.toStringUtf8();
        }

        public void setState(State state) {
            this.state_ = state.getNumber();
        }

        public void setStateValue(int i) {
            this.state_ = i;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new NotifyCameraStateChanged();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\f", new Object[]{"cameraId_", "state_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<NotifyCameraStateChanged> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (NotifyCameraStateChanged.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public String getCameraId() {
            return this.cameraId_;
        }

        @Override
        public ByteString getCameraIdBytes() {
            return ByteString.copyFromUtf8(this.cameraId_);
        }

        @Override
        public State getState() {
            State stateForNumber = State.forNumber(this.state_);
            return stateForNumber == null ? State.UNRECOGNIZED : stateForNumber;
        }

        @Override
        public int getStateValue() {
            return this.state_;
        }
    }

    public interface NotifyCameraStateChangedOrBuilder extends MessageLiteOrBuilder {
        String getCameraId();

        ByteString getCameraIdBytes();

        NotifyCameraStateChanged.State getState();

        int getStateValue();
    }

    public static final class NotifyCarToBackground extends GeneratedMessageLite<NotifyCarToBackground, NotifyCarToBackground.Builder> implements NotifyCarToBackgroundOrBuilder {
        private static final NotifyCarToBackground DEFAULT_INSTANCE;
        private static volatile Parser<NotifyCarToBackground> PARSER = null;
        public static final int TIMESTAMP_FIELD_NUMBER = 1;
        private long timestamp_;

        public static final class Builder extends GeneratedMessageLite.Builder<NotifyCarToBackground, Builder> implements NotifyCarToBackgroundOrBuilder {
            private Builder() {
                super(NotifyCarToBackground.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearTimestamp() {
                copyOnWrite();
                ((NotifyCarToBackground) this.instance).clearTimestamp();
                return this;
            }

            @Override
            public long getTimestamp() {
                return ((NotifyCarToBackground) this.instance).getTimestamp();
            }

            public Builder setTimestamp(long j) {
                copyOnWrite();
                ((NotifyCarToBackground) this.instance).setTimestamp(j);
                return this;
            }
        }

        static {
            NotifyCarToBackground notifyCarToBackground = new NotifyCarToBackground();
            DEFAULT_INSTANCE = notifyCarToBackground;
            GeneratedMessageLite.registerDefaultInstance(NotifyCarToBackground.class, notifyCarToBackground);
        }

        private NotifyCarToBackground() {
        }

        public void clearTimestamp() {
            this.timestamp_ = 0L;
        }

        public static NotifyCarToBackground getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(NotifyCarToBackground notifyCarToBackground) {
            return DEFAULT_INSTANCE.createBuilder(notifyCarToBackground);
        }

        public static NotifyCarToBackground parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyCarToBackground) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyCarToBackground parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyCarToBackground) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyCarToBackground parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (NotifyCarToBackground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static NotifyCarToBackground parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyCarToBackground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static NotifyCarToBackground parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (NotifyCarToBackground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static NotifyCarToBackground parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyCarToBackground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static NotifyCarToBackground parseFrom(InputStream inputStream) throws IOException {
            return (NotifyCarToBackground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyCarToBackground parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyCarToBackground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyCarToBackground parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyCarToBackground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static NotifyCarToBackground parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyCarToBackground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static NotifyCarToBackground parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyCarToBackground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyCarToBackground parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyCarToBackground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<NotifyCarToBackground> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setTimestamp(long j) {
            this.timestamp_ = j;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new NotifyCarToBackground();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0003", new Object[]{"timestamp_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<NotifyCarToBackground> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (NotifyCarToBackground.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public long getTimestamp() {
            return this.timestamp_;
        }
    }

    public interface NotifyCarToBackgroundOrBuilder extends MessageLiteOrBuilder {
        long getTimestamp();
    }

    public static final class NotifyCarToForeground extends GeneratedMessageLite<NotifyCarToForeground, NotifyCarToForeground.Builder> implements NotifyCarToForegroundOrBuilder {
        private static final NotifyCarToForeground DEFAULT_INSTANCE;
        private static volatile Parser<NotifyCarToForeground> PARSER = null;
        public static final int TIMESTAMP_FIELD_NUMBER = 1;
        private long timestamp_;

        public static final class Builder extends GeneratedMessageLite.Builder<NotifyCarToForeground, Builder> implements NotifyCarToForegroundOrBuilder {
            private Builder() {
                super(NotifyCarToForeground.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearTimestamp() {
                copyOnWrite();
                ((NotifyCarToForeground) this.instance).clearTimestamp();
                return this;
            }

            @Override
            public long getTimestamp() {
                return ((NotifyCarToForeground) this.instance).getTimestamp();
            }

            public Builder setTimestamp(long j) {
                copyOnWrite();
                ((NotifyCarToForeground) this.instance).setTimestamp(j);
                return this;
            }
        }

        static {
            NotifyCarToForeground notifyCarToForeground = new NotifyCarToForeground();
            DEFAULT_INSTANCE = notifyCarToForeground;
            GeneratedMessageLite.registerDefaultInstance(NotifyCarToForeground.class, notifyCarToForeground);
        }

        private NotifyCarToForeground() {
        }

        public void clearTimestamp() {
            this.timestamp_ = 0L;
        }

        public static NotifyCarToForeground getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(NotifyCarToForeground notifyCarToForeground) {
            return DEFAULT_INSTANCE.createBuilder(notifyCarToForeground);
        }

        public static NotifyCarToForeground parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyCarToForeground) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyCarToForeground parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyCarToForeground) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyCarToForeground parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (NotifyCarToForeground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static NotifyCarToForeground parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyCarToForeground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static NotifyCarToForeground parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (NotifyCarToForeground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static NotifyCarToForeground parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyCarToForeground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static NotifyCarToForeground parseFrom(InputStream inputStream) throws IOException {
            return (NotifyCarToForeground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyCarToForeground parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyCarToForeground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyCarToForeground parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyCarToForeground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static NotifyCarToForeground parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyCarToForeground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static NotifyCarToForeground parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyCarToForeground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyCarToForeground parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyCarToForeground) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<NotifyCarToForeground> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setTimestamp(long j) {
            this.timestamp_ = j;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new NotifyCarToForeground();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0003", new Object[]{"timestamp_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<NotifyCarToForeground> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (NotifyCarToForeground.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public long getTimestamp() {
            return this.timestamp_;
        }
    }

    public interface NotifyCarToForegroundOrBuilder extends MessageLiteOrBuilder {
        long getTimestamp();
    }

    public static final class NotifyMicrophoneState extends GeneratedMessageLite<NotifyMicrophoneState, NotifyMicrophoneState.Builder> implements NotifyMicrophoneStateOrBuilder {
        public static final int CHANNEL_MASK_FIELD_NUMBER = 3;
        private static final NotifyMicrophoneState DEFAULT_INSTANCE;
        public static final int ENCODING_FORMAT_FIELD_NUMBER = 4;
        private static volatile Parser<NotifyMicrophoneState> PARSER = null;
        public static final int SAMPLE_RATE_FIELD_NUMBER = 2;
        public static final int STATE_FIELD_NUMBER = 1;
        private int channelMask_;
        private int encodingFormat_;
        private int sampleRate_;
        private boolean state_;

        public static final class Builder extends GeneratedMessageLite.Builder<NotifyMicrophoneState, Builder> implements NotifyMicrophoneStateOrBuilder {
            private Builder() {
                super(NotifyMicrophoneState.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearChannelMask() {
                copyOnWrite();
                ((NotifyMicrophoneState) this.instance).clearChannelMask();
                return this;
            }

            public Builder clearEncodingFormat() {
                copyOnWrite();
                ((NotifyMicrophoneState) this.instance).clearEncodingFormat();
                return this;
            }

            public Builder clearSampleRate() {
                copyOnWrite();
                ((NotifyMicrophoneState) this.instance).clearSampleRate();
                return this;
            }

            public Builder clearState() {
                copyOnWrite();
                ((NotifyMicrophoneState) this.instance).clearState();
                return this;
            }

            @Override
            public ChannelMask getChannelMask() {
                return ((NotifyMicrophoneState) this.instance).getChannelMask();
            }

            @Override
            public int getChannelMaskValue() {
                return ((NotifyMicrophoneState) this.instance).getChannelMaskValue();
            }

            @Override
            public EncodingFormat getEncodingFormat() {
                return ((NotifyMicrophoneState) this.instance).getEncodingFormat();
            }

            @Override
            public int getEncodingFormatValue() {
                return ((NotifyMicrophoneState) this.instance).getEncodingFormatValue();
            }

            @Override
            public SampleRate getSampleRate() {
                return ((NotifyMicrophoneState) this.instance).getSampleRate();
            }

            @Override
            public int getSampleRateValue() {
                return ((NotifyMicrophoneState) this.instance).getSampleRateValue();
            }

            @Override
            public boolean getState() {
                return ((NotifyMicrophoneState) this.instance).getState();
            }

            public Builder setChannelMask(ChannelMask channelMask) {
                copyOnWrite();
                ((NotifyMicrophoneState) this.instance).setChannelMask(channelMask);
                return this;
            }

            public Builder setChannelMaskValue(int i) {
                copyOnWrite();
                ((NotifyMicrophoneState) this.instance).setChannelMaskValue(i);
                return this;
            }

            public Builder setEncodingFormat(EncodingFormat encodingFormat) {
                copyOnWrite();
                ((NotifyMicrophoneState) this.instance).setEncodingFormat(encodingFormat);
                return this;
            }

            public Builder setEncodingFormatValue(int i) {
                copyOnWrite();
                ((NotifyMicrophoneState) this.instance).setEncodingFormatValue(i);
                return this;
            }

            public Builder setSampleRate(SampleRate sampleRate) {
                copyOnWrite();
                ((NotifyMicrophoneState) this.instance).setSampleRate(sampleRate);
                return this;
            }

            public Builder setSampleRateValue(int i) {
                copyOnWrite();
                ((NotifyMicrophoneState) this.instance).setSampleRateValue(i);
                return this;
            }

            public Builder setState(boolean z) {
                copyOnWrite();
                ((NotifyMicrophoneState) this.instance).setState(z);
                return this;
            }
        }

        static {
            NotifyMicrophoneState notifyMicrophoneState = new NotifyMicrophoneState();
            DEFAULT_INSTANCE = notifyMicrophoneState;
            GeneratedMessageLite.registerDefaultInstance(NotifyMicrophoneState.class, notifyMicrophoneState);
        }

        private NotifyMicrophoneState() {
        }

        public void clearChannelMask() {
            this.channelMask_ = 0;
        }

        public void clearEncodingFormat() {
            this.encodingFormat_ = 0;
        }

        public void clearSampleRate() {
            this.sampleRate_ = 0;
        }

        public void clearState() {
            this.state_ = false;
        }

        public static NotifyMicrophoneState getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(NotifyMicrophoneState notifyMicrophoneState) {
            return DEFAULT_INSTANCE.createBuilder(notifyMicrophoneState);
        }

        public static NotifyMicrophoneState parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyMicrophoneState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyMicrophoneState parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyMicrophoneState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyMicrophoneState parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (NotifyMicrophoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static NotifyMicrophoneState parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyMicrophoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static NotifyMicrophoneState parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (NotifyMicrophoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static NotifyMicrophoneState parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyMicrophoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static NotifyMicrophoneState parseFrom(InputStream inputStream) throws IOException {
            return (NotifyMicrophoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyMicrophoneState parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyMicrophoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyMicrophoneState parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyMicrophoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static NotifyMicrophoneState parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyMicrophoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static NotifyMicrophoneState parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyMicrophoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyMicrophoneState parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyMicrophoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<NotifyMicrophoneState> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setChannelMask(ChannelMask channelMask) {
            this.channelMask_ = channelMask.getNumber();
        }

        public void setChannelMaskValue(int i) {
            this.channelMask_ = i;
        }

        public void setEncodingFormat(EncodingFormat encodingFormat) {
            this.encodingFormat_ = encodingFormat.getNumber();
        }

        public void setEncodingFormatValue(int i) {
            this.encodingFormat_ = i;
        }

        public void setSampleRate(SampleRate sampleRate) {
            this.sampleRate_ = sampleRate.getNumber();
        }

        public void setSampleRateValue(int i) {
            this.sampleRate_ = i;
        }

        public void setState(boolean z) {
            this.state_ = z;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new NotifyMicrophoneState();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0007\u0002\f\u0003\f\u0004\f", new Object[]{"state_", "sampleRate_", "channelMask_", "encodingFormat_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<NotifyMicrophoneState> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (NotifyMicrophoneState.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public ChannelMask getChannelMask() {
            ChannelMask channelMaskForNumber = ChannelMask.forNumber(this.channelMask_);
            return channelMaskForNumber == null ? ChannelMask.UNRECOGNIZED : channelMaskForNumber;
        }

        @Override
        public int getChannelMaskValue() {
            return this.channelMask_;
        }

        @Override
        public EncodingFormat getEncodingFormat() {
            EncodingFormat encodingFormatForNumber = EncodingFormat.forNumber(this.encodingFormat_);
            return encodingFormatForNumber == null ? EncodingFormat.UNRECOGNIZED : encodingFormatForNumber;
        }

        @Override
        public int getEncodingFormatValue() {
            return this.encodingFormat_;
        }

        @Override
        public SampleRate getSampleRate() {
            SampleRate sampleRateForNumber = SampleRate.forNumber(this.sampleRate_);
            return sampleRateForNumber == null ? SampleRate.UNRECOGNIZED : sampleRateForNumber;
        }

        @Override
        public int getSampleRateValue() {
            return this.sampleRate_;
        }

        @Override
        public boolean getState() {
            return this.state_;
        }
    }

    public interface NotifyMicrophoneStateOrBuilder extends MessageLiteOrBuilder {
        ChannelMask getChannelMask();

        int getChannelMaskValue();

        EncodingFormat getEncodingFormat();

        int getEncodingFormatValue();

        SampleRate getSampleRate();

        int getSampleRateValue();

        boolean getState();
    }

    public static final class NotifyMirrorState extends GeneratedMessageLite<NotifyMirrorState, NotifyMirrorState.Builder> implements NotifyMirrorStateOrBuilder {
        private static final NotifyMirrorState DEFAULT_INSTANCE;
        public static final int MIRROR_STATE_FIELD_NUMBER = 1;
        private static volatile Parser<NotifyMirrorState> PARSER;
        private int mirrorState_;

        public static final class Builder extends GeneratedMessageLite.Builder<NotifyMirrorState, Builder> implements NotifyMirrorStateOrBuilder {
            private Builder() {
                super(NotifyMirrorState.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearMirrorState() {
                copyOnWrite();
                ((NotifyMirrorState) this.instance).clearMirrorState();
                return this;
            }

            @Override
            public MirrorState getMirrorState() {
                return ((NotifyMirrorState) this.instance).getMirrorState();
            }

            @Override
            public int getMirrorStateValue() {
                return ((NotifyMirrorState) this.instance).getMirrorStateValue();
            }

            public Builder setMirrorState(MirrorState mirrorState) {
                copyOnWrite();
                ((NotifyMirrorState) this.instance).setMirrorState(mirrorState);
                return this;
            }

            public Builder setMirrorStateValue(int i) {
                copyOnWrite();
                ((NotifyMirrorState) this.instance).setMirrorStateValue(i);
                return this;
            }
        }

        public enum MirrorState implements Internal.EnumLite {
            STATE_STOP_MIRROR(0),
            STATE_DISCONNECT_LINK(1),
            STATE_GO_TO_DESKTOP(2),
            UNRECOGNIZED(-1);

            public static final int STATE_DISCONNECT_LINK_VALUE = 1;
            public static final int STATE_GO_TO_DESKTOP_VALUE = 2;
            public static final int STATE_STOP_MIRROR_VALUE = 0;
            private static final Internal.EnumLiteMap<MirrorState> internalValueMap = new Internal.EnumLiteMap<MirrorState>() {
                @Override
                public MirrorState findValueByNumber(int i) {
                    return MirrorState.forNumber(i);
                }
            };
            private final int value;

            public static final class MirrorStateVerifier implements Internal.EnumVerifier {
                public static final Internal.EnumVerifier INSTANCE = new MirrorStateVerifier();

                private MirrorStateVerifier() {
                }

                @Override
                public boolean isInRange(int i) {
                    return MirrorState.forNumber(i) != null;
                }
            }

            MirrorState(int i) {
                this.value = i;
            }

            public static MirrorState forNumber(int i) {
                if (i == 0) {
                    return STATE_STOP_MIRROR;
                }
                if (i == 1) {
                    return STATE_DISCONNECT_LINK;
                }
                if (i != 2) {
                    return null;
                }
                return STATE_GO_TO_DESKTOP;
            }

            public static Internal.EnumLiteMap<MirrorState> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return MirrorStateVerifier.INSTANCE;
            }

            @Deprecated
            public static MirrorState valueOf(int i) {
                return forNumber(i);
            }

            @Override
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
        }

        static {
            NotifyMirrorState notifyMirrorState = new NotifyMirrorState();
            DEFAULT_INSTANCE = notifyMirrorState;
            GeneratedMessageLite.registerDefaultInstance(NotifyMirrorState.class, notifyMirrorState);
        }

        private NotifyMirrorState() {
        }

        public void clearMirrorState() {
            this.mirrorState_ = 0;
        }

        public static NotifyMirrorState getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(NotifyMirrorState notifyMirrorState) {
            return DEFAULT_INSTANCE.createBuilder(notifyMirrorState);
        }

        public static NotifyMirrorState parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyMirrorState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyMirrorState parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyMirrorState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyMirrorState parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (NotifyMirrorState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static NotifyMirrorState parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyMirrorState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static NotifyMirrorState parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (NotifyMirrorState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static NotifyMirrorState parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyMirrorState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static NotifyMirrorState parseFrom(InputStream inputStream) throws IOException {
            return (NotifyMirrorState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyMirrorState parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyMirrorState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyMirrorState parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyMirrorState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static NotifyMirrorState parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyMirrorState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static NotifyMirrorState parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyMirrorState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyMirrorState parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyMirrorState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<NotifyMirrorState> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setMirrorState(MirrorState mirrorState) {
            this.mirrorState_ = mirrorState.getNumber();
        }

        public void setMirrorStateValue(int i) {
            this.mirrorState_ = i;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new NotifyMirrorState();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", new Object[]{"mirrorState_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<NotifyMirrorState> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (NotifyMirrorState.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public MirrorState getMirrorState() {
            MirrorState mirrorStateForNumber = MirrorState.forNumber(this.mirrorState_);
            return mirrorStateForNumber == null ? MirrorState.UNRECOGNIZED : mirrorStateForNumber;
        }

        @Override
        public int getMirrorStateValue() {
            return this.mirrorState_;
        }
    }

    public interface NotifyMirrorStateOrBuilder extends MessageLiteOrBuilder {
        NotifyMirrorState.MirrorState getMirrorState();

        int getMirrorStateValue();
    }

    public static final class NotifyMusicInfo extends GeneratedMessageLite<NotifyMusicInfo, NotifyMusicInfo.Builder> implements NotifyMusicInfoOrBuilder {
        public static final int ALBUM_NAME_FIELD_NUMBER = 2;
        public static final int ARTIST_NAME_FIELD_NUMBER = 1;
        public static final int AUTHOR_NAME_FIELD_NUMBER = 7;
        public static final int COMPOSER_NAME_FIELD_NUMBER = 9;
        public static final int COVER_ART_BITMAP_FIELD_NUMBER = 13;
        public static final int COVER_ART_FIELD_NUMBER = 3;
        private static final NotifyMusicInfo DEFAULT_INSTANCE;
        public static final int IS_FAVORITE_FIELD_NUMBER = 11;
        public static final int IS_PLAYING_FIELD_NUMBER = 12;
        public static final int LYRICS_FIELD_NUMBER = 4;
        private static volatile Parser<NotifyMusicInfo> PARSER = null;
        public static final int PLAYING_CURRENT_TIME_MS_FIELD_NUMBER = 10;
        public static final int PLAYING_TIMES_MS_FIELD_NUMBER = 5;
        public static final int TITLE_FIELD_NUMBER = 6;
        public static final int WRITER_NAME_FIELD_NUMBER = 8;
        private int bitField0_;
        private boolean isFavorite_;
        private boolean isPlaying_;
        private int playingCurrentTimeMs_;
        private long playingTimesMs_;
        private String artistName_ = "";
        private String albumName_ = "";
        private String coverArt_ = "";
        private String lyrics_ = "";
        private String title_ = "";
        private String authorName_ = "";
        private String writerName_ = "";
        private String composerName_ = "";
        private ByteString coverArtBitmap_ = ByteString.EMPTY;

        public static final class Builder extends GeneratedMessageLite.Builder<NotifyMusicInfo, Builder> implements NotifyMusicInfoOrBuilder {
            private Builder() {
                super(NotifyMusicInfo.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearAlbumName() {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).clearAlbumName();
                return this;
            }

            public Builder clearArtistName() {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).clearArtistName();
                return this;
            }

            public Builder clearAuthorName() {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).clearAuthorName();
                return this;
            }

            public Builder clearComposerName() {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).clearComposerName();
                return this;
            }

            public Builder clearCoverArt() {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).clearCoverArt();
                return this;
            }

            public Builder clearCoverArtBitmap() {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).clearCoverArtBitmap();
                return this;
            }

            public Builder clearIsFavorite() {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).clearIsFavorite();
                return this;
            }

            public Builder clearIsPlaying() {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).clearIsPlaying();
                return this;
            }

            public Builder clearLyrics() {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).clearLyrics();
                return this;
            }

            public Builder clearPlayingCurrentTimeMs() {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).clearPlayingCurrentTimeMs();
                return this;
            }

            public Builder clearPlayingTimesMs() {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).clearPlayingTimesMs();
                return this;
            }

            public Builder clearTitle() {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).clearTitle();
                return this;
            }

            public Builder clearWriterName() {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).clearWriterName();
                return this;
            }

            @Override
            public String getAlbumName() {
                return ((NotifyMusicInfo) this.instance).getAlbumName();
            }

            @Override
            public ByteString getAlbumNameBytes() {
                return ((NotifyMusicInfo) this.instance).getAlbumNameBytes();
            }

            @Override
            public String getArtistName() {
                return ((NotifyMusicInfo) this.instance).getArtistName();
            }

            @Override
            public ByteString getArtistNameBytes() {
                return ((NotifyMusicInfo) this.instance).getArtistNameBytes();
            }

            @Override
            public String getAuthorName() {
                return ((NotifyMusicInfo) this.instance).getAuthorName();
            }

            @Override
            public ByteString getAuthorNameBytes() {
                return ((NotifyMusicInfo) this.instance).getAuthorNameBytes();
            }

            @Override
            public String getComposerName() {
                return ((NotifyMusicInfo) this.instance).getComposerName();
            }

            @Override
            public ByteString getComposerNameBytes() {
                return ((NotifyMusicInfo) this.instance).getComposerNameBytes();
            }

            @Override
            public String getCoverArt() {
                return ((NotifyMusicInfo) this.instance).getCoverArt();
            }

            @Override
            public ByteString getCoverArtBitmap() {
                return ((NotifyMusicInfo) this.instance).getCoverArtBitmap();
            }

            @Override
            public ByteString getCoverArtBytes() {
                return ((NotifyMusicInfo) this.instance).getCoverArtBytes();
            }

            @Override
            public boolean getIsFavorite() {
                return ((NotifyMusicInfo) this.instance).getIsFavorite();
            }

            @Override
            public boolean getIsPlaying() {
                return ((NotifyMusicInfo) this.instance).getIsPlaying();
            }

            @Override
            public String getLyrics() {
                return ((NotifyMusicInfo) this.instance).getLyrics();
            }

            @Override
            public ByteString getLyricsBytes() {
                return ((NotifyMusicInfo) this.instance).getLyricsBytes();
            }

            @Override
            public int getPlayingCurrentTimeMs() {
                return ((NotifyMusicInfo) this.instance).getPlayingCurrentTimeMs();
            }

            @Override
            public long getPlayingTimesMs() {
                return ((NotifyMusicInfo) this.instance).getPlayingTimesMs();
            }

            @Override
            public String getTitle() {
                return ((NotifyMusicInfo) this.instance).getTitle();
            }

            @Override
            public ByteString getTitleBytes() {
                return ((NotifyMusicInfo) this.instance).getTitleBytes();
            }

            @Override
            public String getWriterName() {
                return ((NotifyMusicInfo) this.instance).getWriterName();
            }

            @Override
            public ByteString getWriterNameBytes() {
                return ((NotifyMusicInfo) this.instance).getWriterNameBytes();
            }

            @Override
            public boolean hasCoverArtBitmap() {
                return ((NotifyMusicInfo) this.instance).hasCoverArtBitmap();
            }

            public Builder setAlbumName(String str) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setAlbumName(str);
                return this;
            }

            public Builder setAlbumNameBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setAlbumNameBytes(abstractC2534u);
                return this;
            }

            public Builder setArtistName(String str) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setArtistName(str);
                return this;
            }

            public Builder setArtistNameBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setArtistNameBytes(abstractC2534u);
                return this;
            }

            public Builder setAuthorName(String str) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setAuthorName(str);
                return this;
            }

            public Builder setAuthorNameBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setAuthorNameBytes(abstractC2534u);
                return this;
            }

            public Builder setComposerName(String str) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setComposerName(str);
                return this;
            }

            public Builder setComposerNameBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setComposerNameBytes(abstractC2534u);
                return this;
            }

            public Builder setCoverArt(String str) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setCoverArt(str);
                return this;
            }

            public Builder setCoverArtBitmap(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setCoverArtBitmap(abstractC2534u);
                return this;
            }

            public Builder setCoverArtBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setCoverArtBytes(abstractC2534u);
                return this;
            }

            public Builder setIsFavorite(boolean z) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setIsFavorite(z);
                return this;
            }

            public Builder setIsPlaying(boolean z) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setIsPlaying(z);
                return this;
            }

            public Builder setLyrics(String str) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setLyrics(str);
                return this;
            }

            public Builder setLyricsBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setLyricsBytes(abstractC2534u);
                return this;
            }

            public Builder setPlayingCurrentTimeMs(int i) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setPlayingCurrentTimeMs(i);
                return this;
            }

            public Builder setPlayingTimesMs(long j) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setPlayingTimesMs(j);
                return this;
            }

            public Builder setTitle(String str) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setTitle(str);
                return this;
            }

            public Builder setTitleBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setTitleBytes(abstractC2534u);
                return this;
            }

            public Builder setWriterName(String str) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setWriterName(str);
                return this;
            }

            public Builder setWriterNameBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyMusicInfo) this.instance).setWriterNameBytes(abstractC2534u);
                return this;
            }
        }

        static {
            NotifyMusicInfo notifyMusicInfo = new NotifyMusicInfo();
            DEFAULT_INSTANCE = notifyMusicInfo;
            GeneratedMessageLite.registerDefaultInstance(NotifyMusicInfo.class, notifyMusicInfo);
        }

        private NotifyMusicInfo() {
        }

        public void clearAlbumName() {
            this.albumName_ = getDefaultInstance().getAlbumName();
        }

        public void clearArtistName() {
            this.artistName_ = getDefaultInstance().getArtistName();
        }

        public void clearAuthorName() {
            this.authorName_ = getDefaultInstance().getAuthorName();
        }

        public void clearComposerName() {
            this.composerName_ = getDefaultInstance().getComposerName();
        }

        public void clearCoverArt() {
            this.coverArt_ = getDefaultInstance().getCoverArt();
        }

        public void clearCoverArtBitmap() {
            this.bitField0_ &= -2;
            this.coverArtBitmap_ = getDefaultInstance().getCoverArtBitmap();
        }

        public void clearIsFavorite() {
            this.isFavorite_ = false;
        }

        public void clearIsPlaying() {
            this.isPlaying_ = false;
        }

        public void clearLyrics() {
            this.lyrics_ = getDefaultInstance().getLyrics();
        }

        public void clearPlayingCurrentTimeMs() {
            this.playingCurrentTimeMs_ = 0;
        }

        public void clearPlayingTimesMs() {
            this.playingTimesMs_ = 0L;
        }

        public void clearTitle() {
            this.title_ = getDefaultInstance().getTitle();
        }

        public void clearWriterName() {
            this.writerName_ = getDefaultInstance().getWriterName();
        }

        public static NotifyMusicInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(NotifyMusicInfo notifyMusicInfo) {
            return DEFAULT_INSTANCE.createBuilder(notifyMusicInfo);
        }

        public static NotifyMusicInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyMusicInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyMusicInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyMusicInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyMusicInfo parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (NotifyMusicInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static NotifyMusicInfo parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyMusicInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static NotifyMusicInfo parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (NotifyMusicInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static NotifyMusicInfo parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyMusicInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static NotifyMusicInfo parseFrom(InputStream inputStream) throws IOException {
            return (NotifyMusicInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyMusicInfo parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyMusicInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyMusicInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyMusicInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static NotifyMusicInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyMusicInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static NotifyMusicInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyMusicInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyMusicInfo parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyMusicInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<NotifyMusicInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setAlbumName(String str) {
            str.getClass();
            this.albumName_ = str;
        }

        public void setAlbumNameBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.albumName_ = abstractC2534u.toStringUtf8();
        }

        public void setArtistName(String str) {
            str.getClass();
            this.artistName_ = str;
        }

        public void setArtistNameBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.artistName_ = abstractC2534u.toStringUtf8();
        }

        public void setAuthorName(String str) {
            str.getClass();
            this.authorName_ = str;
        }

        public void setAuthorNameBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.authorName_ = abstractC2534u.toStringUtf8();
        }

        public void setComposerName(String str) {
            str.getClass();
            this.composerName_ = str;
        }

        public void setComposerNameBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.composerName_ = abstractC2534u.toStringUtf8();
        }

        public void setCoverArt(String str) {
            str.getClass();
            this.coverArt_ = str;
        }

        public void setCoverArtBitmap(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.bitField0_ |= 1;
            this.coverArtBitmap_ = abstractC2534u;
        }

        public void setCoverArtBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.coverArt_ = abstractC2534u.toStringUtf8();
        }

        public void setIsFavorite(boolean z) {
            this.isFavorite_ = z;
        }

        public void setIsPlaying(boolean z) {
            this.isPlaying_ = z;
        }

        public void setLyrics(String str) {
            str.getClass();
            this.lyrics_ = str;
        }

        public void setLyricsBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.lyrics_ = abstractC2534u.toStringUtf8();
        }

        public void setPlayingCurrentTimeMs(int i) {
            this.playingCurrentTimeMs_ = i;
        }

        public void setPlayingTimesMs(long j) {
            this.playingTimesMs_ = j;
        }

        public void setTitle(String str) {
            str.getClass();
            this.title_ = str;
        }

        public void setTitleBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.title_ = abstractC2534u.toStringUtf8();
        }

        public void setWriterName(String str) {
            str.getClass();
            this.writerName_ = str;
        }

        public void setWriterNameBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.writerName_ = abstractC2534u.toStringUtf8();
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new NotifyMusicInfo();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\r\u0000\u0001\u0001\r\r\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004Ȉ\u0005\u0003\u0006Ȉ\u0007Ȉ\bȈ\tȈ\n\u0004\u000b\u0007\f\u0007\rည\u0000", new Object[]{"bitField0_", "artistName_", "albumName_", "coverArt_", "lyrics_", "playingTimesMs_", "title_", "authorName_", "writerName_", "composerName_", "playingCurrentTimeMs_", "isFavorite_", "isPlaying_", "coverArtBitmap_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<NotifyMusicInfo> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (NotifyMusicInfo.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public String getAlbumName() {
            return this.albumName_;
        }

        @Override
        public ByteString getAlbumNameBytes() {
            return ByteString.copyFromUtf8(this.albumName_);
        }

        @Override
        public String getArtistName() {
            return this.artistName_;
        }

        @Override
        public ByteString getArtistNameBytes() {
            return ByteString.copyFromUtf8(this.artistName_);
        }

        @Override
        public String getAuthorName() {
            return this.authorName_;
        }

        @Override
        public ByteString getAuthorNameBytes() {
            return ByteString.copyFromUtf8(this.authorName_);
        }

        @Override
        public String getComposerName() {
            return this.composerName_;
        }

        @Override
        public ByteString getComposerNameBytes() {
            return ByteString.copyFromUtf8(this.composerName_);
        }

        @Override
        public String getCoverArt() {
            return this.coverArt_;
        }

        @Override
        public ByteString getCoverArtBitmap() {
            return this.coverArtBitmap_;
        }

        @Override
        public ByteString getCoverArtBytes() {
            return ByteString.copyFromUtf8(this.coverArt_);
        }

        @Override
        public boolean getIsFavorite() {
            return this.isFavorite_;
        }

        @Override
        public boolean getIsPlaying() {
            return this.isPlaying_;
        }

        @Override
        public String getLyrics() {
            return this.lyrics_;
        }

        @Override
        public ByteString getLyricsBytes() {
            return ByteString.copyFromUtf8(this.lyrics_);
        }

        @Override
        public int getPlayingCurrentTimeMs() {
            return this.playingCurrentTimeMs_;
        }

        @Override
        public long getPlayingTimesMs() {
            return this.playingTimesMs_;
        }

        @Override
        public String getTitle() {
            return this.title_;
        }

        @Override
        public ByteString getTitleBytes() {
            return ByteString.copyFromUtf8(this.title_);
        }

        @Override
        public String getWriterName() {
            return this.writerName_;
        }

        @Override
        public ByteString getWriterNameBytes() {
            return ByteString.copyFromUtf8(this.writerName_);
        }

        @Override
        public boolean hasCoverArtBitmap() {
            return (this.bitField0_ & 1) != 0;
        }
    }

    public interface NotifyMusicInfoOrBuilder extends MessageLiteOrBuilder {
        String getAlbumName();

        ByteString getAlbumNameBytes();

        String getArtistName();

        ByteString getArtistNameBytes();

        String getAuthorName();

        ByteString getAuthorNameBytes();

        String getComposerName();

        ByteString getComposerNameBytes();

        String getCoverArt();

        ByteString getCoverArtBitmap();

        ByteString getCoverArtBytes();

        boolean getIsFavorite();

        boolean getIsPlaying();

        String getLyrics();

        ByteString getLyricsBytes();

        int getPlayingCurrentTimeMs();

        long getPlayingTimesMs();

        String getTitle();

        ByteString getTitleBytes();

        String getWriterName();

        ByteString getWriterNameBytes();

        boolean hasCoverArtBitmap();
    }

    public static final class NotifyNavigationInfo extends GeneratedMessageLite<NotifyNavigationInfo, NotifyNavigationInfo.Builder> implements NotifyNavigationInfoOrBuilder {
        private static final NotifyNavigationInfo DEFAULT_INSTANCE;
        public static final int DIRECTIONICON_FIELD_NUMBER = 2;
        public static final int DISTANCEUNIT_FIELD_NUMBER = 4;
        public static final int DISTANCE_FIELD_NUMBER = 3;
        public static final int ISNAVIGATING_FIELD_NUMBER = 1;
        public static final int OPERATION_FIELD_NUMBER = 5;
        private static volatile Parser<NotifyNavigationInfo> PARSER = null;
        public static final int TITLE1_FIELD_NUMBER = 7;
        public static final int TITLE2_FIELD_NUMBER = 8;
        public static final int WHERE_FIELD_NUMBER = 6;
        private boolean isNavigating_;
        private ByteString directionIcon_ = ByteString.EMPTY;
        private String distance_ = "";
        private String distanceUnit_ = "";
        private String operation_ = "";
        private String where_ = "";
        private String title1_ = "";
        private String title2_ = "";

        public static final class Builder extends GeneratedMessageLite.Builder<NotifyNavigationInfo, Builder> implements NotifyNavigationInfoOrBuilder {
            private Builder() {
                super(NotifyNavigationInfo.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearDirectionIcon() {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).clearDirectionIcon();
                return this;
            }

            public Builder clearDistance() {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).clearDistance();
                return this;
            }

            public Builder clearDistanceUnit() {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).clearDistanceUnit();
                return this;
            }

            public Builder clearIsNavigating() {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).clearIsNavigating();
                return this;
            }

            public Builder clearOperation() {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).clearOperation();
                return this;
            }

            public Builder clearTitle1() {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).clearTitle1();
                return this;
            }

            public Builder clearTitle2() {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).clearTitle2();
                return this;
            }

            public Builder clearWhere() {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).clearWhere();
                return this;
            }

            @Override
            public ByteString getDirectionIcon() {
                return ((NotifyNavigationInfo) this.instance).getDirectionIcon();
            }

            @Override
            public String getDistance() {
                return ((NotifyNavigationInfo) this.instance).getDistance();
            }

            @Override
            public ByteString getDistanceBytes() {
                return ((NotifyNavigationInfo) this.instance).getDistanceBytes();
            }

            @Override
            public String getDistanceUnit() {
                return ((NotifyNavigationInfo) this.instance).getDistanceUnit();
            }

            @Override
            public ByteString getDistanceUnitBytes() {
                return ((NotifyNavigationInfo) this.instance).getDistanceUnitBytes();
            }

            @Override
            public boolean getIsNavigating() {
                return ((NotifyNavigationInfo) this.instance).getIsNavigating();
            }

            @Override
            public String getOperation() {
                return ((NotifyNavigationInfo) this.instance).getOperation();
            }

            @Override
            public ByteString getOperationBytes() {
                return ((NotifyNavigationInfo) this.instance).getOperationBytes();
            }

            @Override
            public String getTitle1() {
                return ((NotifyNavigationInfo) this.instance).getTitle1();
            }

            @Override
            public ByteString getTitle1Bytes() {
                return ((NotifyNavigationInfo) this.instance).getTitle1Bytes();
            }

            @Override
            public String getTitle2() {
                return ((NotifyNavigationInfo) this.instance).getTitle2();
            }

            @Override
            public ByteString getTitle2Bytes() {
                return ((NotifyNavigationInfo) this.instance).getTitle2Bytes();
            }

            @Override
            public String getWhere() {
                return ((NotifyNavigationInfo) this.instance).getWhere();
            }

            @Override
            public ByteString getWhereBytes() {
                return ((NotifyNavigationInfo) this.instance).getWhereBytes();
            }

            public Builder setDirectionIcon(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).setDirectionIcon(abstractC2534u);
                return this;
            }

            public Builder setDistance(String str) {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).setDistance(str);
                return this;
            }

            public Builder setDistanceBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).setDistanceBytes(abstractC2534u);
                return this;
            }

            public Builder setDistanceUnit(String str) {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).setDistanceUnit(str);
                return this;
            }

            public Builder setDistanceUnitBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).setDistanceUnitBytes(abstractC2534u);
                return this;
            }

            public Builder setIsNavigating(boolean z) {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).setIsNavigating(z);
                return this;
            }

            public Builder setOperation(String str) {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).setOperation(str);
                return this;
            }

            public Builder setOperationBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).setOperationBytes(abstractC2534u);
                return this;
            }

            public Builder setTitle1(String str) {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).setTitle1(str);
                return this;
            }

            public Builder setTitle1Bytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).setTitle1Bytes(abstractC2534u);
                return this;
            }

            public Builder setTitle2(String str) {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).setTitle2(str);
                return this;
            }

            public Builder setTitle2Bytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).setTitle2Bytes(abstractC2534u);
                return this;
            }

            public Builder setWhere(String str) {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).setWhere(str);
                return this;
            }

            public Builder setWhereBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyNavigationInfo) this.instance).setWhereBytes(abstractC2534u);
                return this;
            }
        }

        static {
            NotifyNavigationInfo notifyNavigationInfo = new NotifyNavigationInfo();
            DEFAULT_INSTANCE = notifyNavigationInfo;
            GeneratedMessageLite.registerDefaultInstance(NotifyNavigationInfo.class, notifyNavigationInfo);
        }

        private NotifyNavigationInfo() {
        }

        public void clearDirectionIcon() {
            this.directionIcon_ = getDefaultInstance().getDirectionIcon();
        }

        public void clearDistance() {
            this.distance_ = getDefaultInstance().getDistance();
        }

        public void clearDistanceUnit() {
            this.distanceUnit_ = getDefaultInstance().getDistanceUnit();
        }

        public void clearIsNavigating() {
            this.isNavigating_ = false;
        }

        public void clearOperation() {
            this.operation_ = getDefaultInstance().getOperation();
        }

        public void clearTitle1() {
            this.title1_ = getDefaultInstance().getTitle1();
        }

        public void clearTitle2() {
            this.title2_ = getDefaultInstance().getTitle2();
        }

        public void clearWhere() {
            this.where_ = getDefaultInstance().getWhere();
        }

        public static NotifyNavigationInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(NotifyNavigationInfo notifyNavigationInfo) {
            return DEFAULT_INSTANCE.createBuilder(notifyNavigationInfo);
        }

        public static NotifyNavigationInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyNavigationInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyNavigationInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyNavigationInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyNavigationInfo parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (NotifyNavigationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static NotifyNavigationInfo parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyNavigationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static NotifyNavigationInfo parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (NotifyNavigationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static NotifyNavigationInfo parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyNavigationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static NotifyNavigationInfo parseFrom(InputStream inputStream) throws IOException {
            return (NotifyNavigationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyNavigationInfo parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyNavigationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyNavigationInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyNavigationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static NotifyNavigationInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyNavigationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static NotifyNavigationInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyNavigationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyNavigationInfo parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyNavigationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<NotifyNavigationInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setDirectionIcon(ByteString abstractC2534u) {
            abstractC2534u.getClass();
            this.directionIcon_ = abstractC2534u;
        }

        public void setDistance(String str) {
            str.getClass();
            this.distance_ = str;
        }

        public void setDistanceBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.distance_ = abstractC2534u.toStringUtf8();
        }

        public void setDistanceUnit(String str) {
            str.getClass();
            this.distanceUnit_ = str;
        }

        public void setDistanceUnitBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.distanceUnit_ = abstractC2534u.toStringUtf8();
        }

        public void setIsNavigating(boolean z) {
            this.isNavigating_ = z;
        }

        public void setOperation(String str) {
            str.getClass();
            this.operation_ = str;
        }

        public void setOperationBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.operation_ = abstractC2534u.toStringUtf8();
        }

        public void setTitle1(String str) {
            str.getClass();
            this.title1_ = str;
        }

        public void setTitle1Bytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.title1_ = abstractC2534u.toStringUtf8();
        }

        public void setTitle2(String str) {
            str.getClass();
            this.title2_ = str;
        }

        public void setTitle2Bytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.title2_ = abstractC2534u.toStringUtf8();
        }

        public void setWhere(String str) {
            str.getClass();
            this.where_ = str;
        }

        public void setWhereBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.where_ = abstractC2534u.toStringUtf8();
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new NotifyNavigationInfo();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\b\u0000\u0000\u0001\b\b\u0000\u0000\u0000\u0001\u0007\u0002\n\u0003Ȉ\u0004Ȉ\u0005Ȉ\u0006Ȉ\u0007Ȉ\bȈ", new Object[]{"isNavigating_", "directionIcon_", "distance_", "distanceUnit_", "operation_", "where_", "title1_", "title2_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<NotifyNavigationInfo> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (NotifyNavigationInfo.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public ByteString getDirectionIcon() {
            return this.directionIcon_;
        }

        @Override
        public String getDistance() {
            return this.distance_;
        }

        @Override
        public ByteString getDistanceBytes() {
            return ByteString.copyFromUtf8(this.distance_);
        }

        @Override
        public String getDistanceUnit() {
            return this.distanceUnit_;
        }

        @Override
        public ByteString getDistanceUnitBytes() {
            return ByteString.copyFromUtf8(this.distanceUnit_);
        }

        @Override
        public boolean getIsNavigating() {
            return this.isNavigating_;
        }

        @Override
        public String getOperation() {
            return this.operation_;
        }

        @Override
        public ByteString getOperationBytes() {
            return ByteString.copyFromUtf8(this.operation_);
        }

        @Override
        public String getTitle1() {
            return this.title1_;
        }

        @Override
        public ByteString getTitle1Bytes() {
            return ByteString.copyFromUtf8(this.title1_);
        }

        @Override
        public String getTitle2() {
            return this.title2_;
        }

        @Override
        public ByteString getTitle2Bytes() {
            return ByteString.copyFromUtf8(this.title2_);
        }

        @Override
        public String getWhere() {
            return this.where_;
        }

        @Override
        public ByteString getWhereBytes() {
            return ByteString.copyFromUtf8(this.where_);
        }
    }

    public interface NotifyNavigationInfoOrBuilder extends MessageLiteOrBuilder {
        ByteString getDirectionIcon();

        String getDistance();

        ByteString getDistanceBytes();

        String getDistanceUnit();

        ByteString getDistanceUnitBytes();

        boolean getIsNavigating();

        String getOperation();

        ByteString getOperationBytes();

        String getTitle1();

        ByteString getTitle1Bytes();

        String getTitle2();

        ByteString getTitle2Bytes();

        String getWhere();

        ByteString getWhereBytes();
    }

    public static final class NotifyPhoneState extends GeneratedMessageLite<NotifyPhoneState, NotifyPhoneState.Builder> implements NotifyPhoneStateOrBuilder {
        public static final int CS_FIELD_NUMBER = 1;
        private static final NotifyPhoneState DEFAULT_INSTANCE;
        public static final int IS_SCREEN_LOCKED_FIELD_NUMBER = 2;
        public static final int IS_VOICE_ASSISTANT_ACTIVE_FIELD_NUMBER = 4;
        public static final int IS_WECHAT_QQ_CALL_FIELD_NUMBER = 3;
        private static volatile Parser<NotifyPhoneState> PARSER;
        private int cs_;
        private boolean isScreenLocked_;
        private boolean isVoiceAssistantActive_;
        private boolean isWechatQqCall_;

        public static final class Builder extends GeneratedMessageLite.Builder<NotifyPhoneState, Builder> implements NotifyPhoneStateOrBuilder {
            private Builder() {
                super(NotifyPhoneState.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearCs() {
                copyOnWrite();
                ((NotifyPhoneState) this.instance).clearCs();
                return this;
            }

            public Builder clearIsScreenLocked() {
                copyOnWrite();
                ((NotifyPhoneState) this.instance).clearIsScreenLocked();
                return this;
            }

            public Builder clearIsVoiceAssistantActive() {
                copyOnWrite();
                ((NotifyPhoneState) this.instance).clearIsVoiceAssistantActive();
                return this;
            }

            public Builder clearIsWechatQqCall() {
                copyOnWrite();
                ((NotifyPhoneState) this.instance).clearIsWechatQqCall();
                return this;
            }

            @Override
            public CALL_STATE getCs() {
                return ((NotifyPhoneState) this.instance).getCs();
            }

            @Override
            public int getCsValue() {
                return ((NotifyPhoneState) this.instance).getCsValue();
            }

            @Override
            public boolean getIsScreenLocked() {
                return ((NotifyPhoneState) this.instance).getIsScreenLocked();
            }

            @Override
            public boolean getIsVoiceAssistantActive() {
                return ((NotifyPhoneState) this.instance).getIsVoiceAssistantActive();
            }

            @Override
            public boolean getIsWechatQqCall() {
                return ((NotifyPhoneState) this.instance).getIsWechatQqCall();
            }

            public Builder setCs(CALL_STATE call_state) {
                copyOnWrite();
                ((NotifyPhoneState) this.instance).setCs(call_state);
                return this;
            }

            public Builder setCsValue(int i) {
                copyOnWrite();
                ((NotifyPhoneState) this.instance).setCsValue(i);
                return this;
            }

            public Builder setIsScreenLocked(boolean z) {
                copyOnWrite();
                ((NotifyPhoneState) this.instance).setIsScreenLocked(z);
                return this;
            }

            public Builder setIsVoiceAssistantActive(boolean z) {
                copyOnWrite();
                ((NotifyPhoneState) this.instance).setIsVoiceAssistantActive(z);
                return this;
            }

            public Builder setIsWechatQqCall(boolean z) {
                copyOnWrite();
                ((NotifyPhoneState) this.instance).setIsWechatQqCall(z);
                return this;
            }
        }

        public enum CALL_STATE implements Internal.EnumLite {
            UNKNOWN_STATE(0),
            IDLE(1),
            RINGING(2),
            OFFHOOK(3),
            UNRECOGNIZED(-1);

            public static final int IDLE_VALUE = 1;
            public static final int OFFHOOK_VALUE = 3;
            public static final int RINGING_VALUE = 2;
            public static final int UNKNOWN_STATE_VALUE = 0;
            private static final Internal.EnumLiteMap<CALL_STATE> internalValueMap = new Internal.EnumLiteMap<CALL_STATE>() {
                @Override
                public CALL_STATE findValueByNumber(int i) {
                    return CALL_STATE.forNumber(i);
                }
            };
            private final int value;

            public static final class CALL_STATEVerifier implements Internal.EnumVerifier {
                public static final Internal.EnumVerifier INSTANCE = new CALL_STATEVerifier();

                private CALL_STATEVerifier() {
                }

                @Override
                public boolean isInRange(int i) {
                    return CALL_STATE.forNumber(i) != null;
                }
            }

            CALL_STATE(int i) {
                this.value = i;
            }

            public static CALL_STATE forNumber(int i) {
                if (i == 0) {
                    return UNKNOWN_STATE;
                }
                if (i == 1) {
                    return IDLE;
                }
                if (i == 2) {
                    return RINGING;
                }
                if (i != 3) {
                    return null;
                }
                return OFFHOOK;
            }

            public static Internal.EnumLiteMap<CALL_STATE> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return CALL_STATEVerifier.INSTANCE;
            }

            @Deprecated
            public static CALL_STATE valueOf(int i) {
                return forNumber(i);
            }

            @Override
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
        }

        static {
            NotifyPhoneState notifyPhoneState = new NotifyPhoneState();
            DEFAULT_INSTANCE = notifyPhoneState;
            GeneratedMessageLite.registerDefaultInstance(NotifyPhoneState.class, notifyPhoneState);
        }

        private NotifyPhoneState() {
        }

        public void clearCs() {
            this.cs_ = 0;
        }

        public void clearIsScreenLocked() {
            this.isScreenLocked_ = false;
        }

        public void clearIsVoiceAssistantActive() {
            this.isVoiceAssistantActive_ = false;
        }

        public void clearIsWechatQqCall() {
            this.isWechatQqCall_ = false;
        }

        public static NotifyPhoneState getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(NotifyPhoneState notifyPhoneState) {
            return DEFAULT_INSTANCE.createBuilder(notifyPhoneState);
        }

        public static NotifyPhoneState parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyPhoneState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyPhoneState parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyPhoneState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyPhoneState parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (NotifyPhoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static NotifyPhoneState parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyPhoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static NotifyPhoneState parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (NotifyPhoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static NotifyPhoneState parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyPhoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static NotifyPhoneState parseFrom(InputStream inputStream) throws IOException {
            return (NotifyPhoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyPhoneState parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyPhoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyPhoneState parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyPhoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static NotifyPhoneState parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyPhoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static NotifyPhoneState parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyPhoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyPhoneState parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyPhoneState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<NotifyPhoneState> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setCs(CALL_STATE call_state) {
            this.cs_ = call_state.getNumber();
        }

        public void setCsValue(int i) {
            this.cs_ = i;
        }

        public void setIsScreenLocked(boolean z) {
            this.isScreenLocked_ = z;
        }

        public void setIsVoiceAssistantActive(boolean z) {
            this.isVoiceAssistantActive_ = z;
        }

        public void setIsWechatQqCall(boolean z) {
            this.isWechatQqCall_ = z;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new NotifyPhoneState();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\f\u0002\u0007\u0003\u0007\u0004\u0007", new Object[]{"cs_", "isScreenLocked_", "isWechatQqCall_", "isVoiceAssistantActive_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<NotifyPhoneState> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (NotifyPhoneState.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public CALL_STATE getCs() {
            CALL_STATE call_stateForNumber = CALL_STATE.forNumber(this.cs_);
            return call_stateForNumber == null ? CALL_STATE.UNRECOGNIZED : call_stateForNumber;
        }

        @Override
        public int getCsValue() {
            return this.cs_;
        }

        @Override
        public boolean getIsScreenLocked() {
            return this.isScreenLocked_;
        }

        @Override
        public boolean getIsVoiceAssistantActive() {
            return this.isVoiceAssistantActive_;
        }

        @Override
        public boolean getIsWechatQqCall() {
            return this.isWechatQqCall_;
        }
    }

    public interface NotifyPhoneStateOrBuilder extends MessageLiteOrBuilder {
        NotifyPhoneState.CALL_STATE getCs();

        int getCsValue();

        boolean getIsScreenLocked();

        boolean getIsVoiceAssistantActive();

        boolean getIsWechatQqCall();
    }

    public static final class NotifyRemoveCamera extends GeneratedMessageLite<NotifyRemoveCamera, NotifyRemoveCamera.Builder> implements NotifyRemoveCameraOrBuilder {
        private static final NotifyRemoveCamera DEFAULT_INSTANCE;
        public static final int IDS_FIELD_NUMBER = 1;
        private static volatile Parser<NotifyRemoveCamera> PARSER;
        private Internal.ProtobufList<String> ids_ = GeneratedMessageLite.emptyProtobufList();

        public static final class Builder extends GeneratedMessageLite.Builder<NotifyRemoveCamera, Builder> implements NotifyRemoveCameraOrBuilder {
            private Builder() {
                super(NotifyRemoveCamera.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder addAllIds(Iterable<String> iterable) {
                copyOnWrite();
                ((NotifyRemoveCamera) this.instance).addAllIds(iterable);
                return this;
            }

            public Builder addIds(String str) {
                copyOnWrite();
                ((NotifyRemoveCamera) this.instance).addIds(str);
                return this;
            }

            public Builder addIdsBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((NotifyRemoveCamera) this.instance).addIdsBytes(abstractC2534u);
                return this;
            }

            public Builder clearIds() {
                copyOnWrite();
                ((NotifyRemoveCamera) this.instance).clearIds();
                return this;
            }

            @Override
            public String getIds(int i) {
                return ((NotifyRemoveCamera) this.instance).getIds(i);
            }

            @Override
            public ByteString getIdsBytes(int i) {
                return ((NotifyRemoveCamera) this.instance).getIdsBytes(i);
            }

            @Override
            public int getIdsCount() {
                return ((NotifyRemoveCamera) this.instance).getIdsCount();
            }

            @Override
            public List<String> getIdsList() {
                return Collections.unmodifiableList(((NotifyRemoveCamera) this.instance).getIdsList());
            }

            public Builder setIds(int i, String str) {
                copyOnWrite();
                ((NotifyRemoveCamera) this.instance).setIds(i, str);
                return this;
            }
        }

        static {
            NotifyRemoveCamera notifyRemoveCamera = new NotifyRemoveCamera();
            DEFAULT_INSTANCE = notifyRemoveCamera;
            GeneratedMessageLite.registerDefaultInstance(NotifyRemoveCamera.class, notifyRemoveCamera);
        }

        private NotifyRemoveCamera() {
        }

        public void addAllIds(Iterable<String> iterable) {
            ensureIdsIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.ids_);
        }

        public void addIds(String str) {
            str.getClass();
            ensureIdsIsMutable();
            this.ids_.add(str);
        }

        public void addIdsBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            ensureIdsIsMutable();
            this.ids_.add(abstractC2534u.toStringUtf8());
        }

        public void clearIds() {
            this.ids_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureIdsIsMutable() {
            Internal.ProtobufList<String> kVar = this.ids_;
            if (kVar.isModifiable()) {
                return;
            }
            this.ids_ = GeneratedMessageLite.mutableCopy(kVar);
        }

        public static NotifyRemoveCamera getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(NotifyRemoveCamera notifyRemoveCamera) {
            return DEFAULT_INSTANCE.createBuilder(notifyRemoveCamera);
        }

        public static NotifyRemoveCamera parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifyRemoveCamera) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyRemoveCamera parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyRemoveCamera) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyRemoveCamera parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (NotifyRemoveCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static NotifyRemoveCamera parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyRemoveCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static NotifyRemoveCamera parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (NotifyRemoveCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static NotifyRemoveCamera parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyRemoveCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static NotifyRemoveCamera parseFrom(InputStream inputStream) throws IOException {
            return (NotifyRemoveCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifyRemoveCamera parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifyRemoveCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifyRemoveCamera parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifyRemoveCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static NotifyRemoveCamera parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyRemoveCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static NotifyRemoveCamera parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifyRemoveCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifyRemoveCamera parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifyRemoveCamera) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<NotifyRemoveCamera> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setIds(int i, String str) {
            str.getClass();
            ensureIdsIsMutable();
            this.ids_.set(i, str);
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new NotifyRemoveCamera();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001Ț", new Object[]{"ids_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<NotifyRemoveCamera> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (NotifyRemoveCamera.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public String getIds(int i) {
            return this.ids_.get(i);
        }

        @Override
        public ByteString getIdsBytes(int i) {
            return ByteString.copyFromUtf8(this.ids_.get(i));
        }

        @Override
        public int getIdsCount() {
            return this.ids_.size();
        }

        @Override
        public List<String> getIdsList() {
            return this.ids_;
        }
    }

    public interface NotifyRemoveCameraOrBuilder extends MessageLiteOrBuilder {
        String getIds(int i);

        ByteString getIdsBytes(int i);

        int getIdsCount();

        List<String> getIdsList();
    }

    public static final class NotifySwitchDayOrNight extends GeneratedMessageLite<NotifySwitchDayOrNight, NotifySwitchDayOrNight.Builder> implements NotifySwitchDayOrNightOrBuilder {
        private static final NotifySwitchDayOrNight DEFAULT_INSTANCE;
        public static final int MODE_FIELD_NUMBER = 1;
        private static volatile Parser<NotifySwitchDayOrNight> PARSER;
        private int mode_;

        public static final class Builder extends GeneratedMessageLite.Builder<NotifySwitchDayOrNight, Builder> implements NotifySwitchDayOrNightOrBuilder {
            private Builder() {
                super(NotifySwitchDayOrNight.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearMode() {
                copyOnWrite();
                ((NotifySwitchDayOrNight) this.instance).clearMode();
                return this;
            }

            @Override
            public Mode getMode() {
                return ((NotifySwitchDayOrNight) this.instance).getMode();
            }

            @Override
            public int getModeValue() {
                return ((NotifySwitchDayOrNight) this.instance).getModeValue();
            }

            public Builder setMode(Mode mode) {
                copyOnWrite();
                ((NotifySwitchDayOrNight) this.instance).setMode(mode);
                return this;
            }

            public Builder setModeValue(int i) {
                copyOnWrite();
                ((NotifySwitchDayOrNight) this.instance).setModeValue(i);
                return this;
            }
        }

        public enum Mode implements Internal.EnumLite {
            UNKNOWN(0),
            DAY(1),
            NIGHT(2),
            UNRECOGNIZED(-1);

            public static final int DAY_VALUE = 1;
            public static final int NIGHT_VALUE = 2;
            public static final int UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<Mode> internalValueMap = new Internal.EnumLiteMap<Mode>() {
                @Override
                public Mode findValueByNumber(int i) {
                    return Mode.forNumber(i);
                }
            };
            private final int value;

            public static final class ModeVerifier implements Internal.EnumVerifier {
                public static final Internal.EnumVerifier INSTANCE = new ModeVerifier();

                private ModeVerifier() {
                }

                @Override
                public boolean isInRange(int i) {
                    return Mode.forNumber(i) != null;
                }
            }

            Mode(int i) {
                this.value = i;
            }

            public static Mode forNumber(int i) {
                if (i == 0) {
                    return UNKNOWN;
                }
                if (i == 1) {
                    return DAY;
                }
                if (i != 2) {
                    return null;
                }
                return NIGHT;
            }

            public static Internal.EnumLiteMap<Mode> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return ModeVerifier.INSTANCE;
            }

            @Deprecated
            public static Mode valueOf(int i) {
                return forNumber(i);
            }

            @Override
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
        }

        static {
            NotifySwitchDayOrNight notifySwitchDayOrNight = new NotifySwitchDayOrNight();
            DEFAULT_INSTANCE = notifySwitchDayOrNight;
            GeneratedMessageLite.registerDefaultInstance(NotifySwitchDayOrNight.class, notifySwitchDayOrNight);
        }

        private NotifySwitchDayOrNight() {
        }

        public void clearMode() {
            this.mode_ = 0;
        }

        public static NotifySwitchDayOrNight getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(NotifySwitchDayOrNight notifySwitchDayOrNight) {
            return DEFAULT_INSTANCE.createBuilder(notifySwitchDayOrNight);
        }

        public static NotifySwitchDayOrNight parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NotifySwitchDayOrNight) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifySwitchDayOrNight parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifySwitchDayOrNight) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifySwitchDayOrNight parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (NotifySwitchDayOrNight) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static NotifySwitchDayOrNight parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifySwitchDayOrNight) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static NotifySwitchDayOrNight parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (NotifySwitchDayOrNight) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static NotifySwitchDayOrNight parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifySwitchDayOrNight) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static NotifySwitchDayOrNight parseFrom(InputStream inputStream) throws IOException {
            return (NotifySwitchDayOrNight) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NotifySwitchDayOrNight parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (NotifySwitchDayOrNight) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static NotifySwitchDayOrNight parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (NotifySwitchDayOrNight) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static NotifySwitchDayOrNight parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifySwitchDayOrNight) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static NotifySwitchDayOrNight parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NotifySwitchDayOrNight) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NotifySwitchDayOrNight parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (NotifySwitchDayOrNight) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<NotifySwitchDayOrNight> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setMode(Mode mode) {
            this.mode_ = mode.getNumber();
        }

        public void setModeValue(int i) {
            this.mode_ = i;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new NotifySwitchDayOrNight();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", new Object[]{"mode_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<NotifySwitchDayOrNight> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (NotifySwitchDayOrNight.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public Mode getMode() {
            Mode modeForNumber = Mode.forNumber(this.mode_);
            return modeForNumber == null ? Mode.UNRECOGNIZED : modeForNumber;
        }

        @Override
        public int getModeValue() {
            return this.mode_;
        }
    }

    public interface NotifySwitchDayOrNightOrBuilder extends MessageLiteOrBuilder {
        NotifySwitchDayOrNight.Mode getMode();

        int getModeValue();
    }

    public static final class Oil extends GeneratedMessageLite<Oil, Oil.Builder> implements OilOrBuilder {
        public static final int CURRENT_FUEL_FIELD_NUMBER = 2;
        private static final Oil DEFAULT_INSTANCE;
        public static final int MAX_FUEL_FIELD_NUMBER = 1;
        private static volatile Parser<Oil> PARSER;
        private int currentFuel_;
        private int maxFuel_;

        public static final class Builder extends GeneratedMessageLite.Builder<Oil, Builder> implements OilOrBuilder {
            private Builder() {
                super(Oil.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearCurrentFuel() {
                copyOnWrite();
                ((Oil) this.instance).clearCurrentFuel();
                return this;
            }

            public Builder clearMaxFuel() {
                copyOnWrite();
                ((Oil) this.instance).clearMaxFuel();
                return this;
            }

            @Override
            public int getCurrentFuel() {
                return ((Oil) this.instance).getCurrentFuel();
            }

            @Override
            public int getMaxFuel() {
                return ((Oil) this.instance).getMaxFuel();
            }

            public Builder setCurrentFuel(int i) {
                copyOnWrite();
                ((Oil) this.instance).setCurrentFuel(i);
                return this;
            }

            public Builder setMaxFuel(int i) {
                copyOnWrite();
                ((Oil) this.instance).setMaxFuel(i);
                return this;
            }
        }

        static {
            Oil oil = new Oil();
            DEFAULT_INSTANCE = oil;
            GeneratedMessageLite.registerDefaultInstance(Oil.class, oil);
        }

        private Oil() {
        }

        public void clearCurrentFuel() {
            this.currentFuel_ = 0;
        }

        public void clearMaxFuel() {
            this.maxFuel_ = 0;
        }

        public static Oil getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Oil oil) {
            return DEFAULT_INSTANCE.createBuilder(oil);
        }

        public static Oil parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Oil) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Oil parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (Oil) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static Oil parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (Oil) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static Oil parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Oil) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static Oil parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (Oil) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static Oil parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (Oil) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static Oil parseFrom(InputStream inputStream) throws IOException {
            return (Oil) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Oil parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (Oil) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static Oil parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Oil) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Oil parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Oil) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static Oil parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Oil) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Oil parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (Oil) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<Oil> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setCurrentFuel(int i) {
            this.currentFuel_ = i;
        }

        public void setMaxFuel(int i) {
            this.maxFuel_ = i;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new Oil();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\u000b", new Object[]{"maxFuel_", "currentFuel_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Oil> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (Oil.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public int getCurrentFuel() {
            return this.currentFuel_;
        }

        @Override
        public int getMaxFuel() {
            return this.maxFuel_;
        }
    }

    public interface OilOrBuilder extends MessageLiteOrBuilder {
        int getCurrentFuel();

        int getMaxFuel();
    }

    public enum Orientation implements Internal.EnumLite {
        ORIENTATION_0(0),
        ORIENTATION_90(90),
        ORIENTATION_180(180),
        ORIENTATION_270(270),
        UNRECOGNIZED(-1);

        public static final int ORIENTATION_0_VALUE = 0;
        public static final int ORIENTATION_180_VALUE = 180;
        public static final int ORIENTATION_270_VALUE = 270;
        public static final int ORIENTATION_90_VALUE = 90;
        private static final Internal.EnumLiteMap<Orientation> internalValueMap = new Internal.EnumLiteMap<Orientation>() {
            @Override
            public Orientation findValueByNumber(int i) {
                return Orientation.forNumber(i);
            }
        };
        private final int value;

        public static final class OrientationVerifier implements Internal.EnumVerifier {
            public static final Internal.EnumVerifier INSTANCE = new OrientationVerifier();

            private OrientationVerifier() {
            }

            @Override
            public boolean isInRange(int i) {
                return Orientation.forNumber(i) != null;
            }
        }

        Orientation(int i) {
            this.value = i;
        }

        public static Orientation forNumber(int i) {
            if (i == 0) {
                return ORIENTATION_0;
            }
            if (i == 90) {
                return ORIENTATION_90;
            }
            if (i == 180) {
                return ORIENTATION_180;
            }
            if (i != 270) {
                return null;
            }
            return ORIENTATION_270;
        }

        public static Internal.EnumLiteMap<Orientation> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return OrientationVerifier.INSTANCE;
        }

        @Deprecated
        public static Orientation valueOf(int i) {
            return forNumber(i);
        }

        @Override
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
    }

    public static final class PictureSize extends GeneratedMessageLite<PictureSize, PictureSize.Builder> implements PictureSizeOrBuilder {
        private static final PictureSize DEFAULT_INSTANCE;
        public static final int HEIGHT_FIELD_NUMBER = 2;
        private static volatile Parser<PictureSize> PARSER = null;
        public static final int WIDTH_FIELD_NUMBER = 1;
        private int height_;
        private int width_;

        public static final class Builder extends GeneratedMessageLite.Builder<PictureSize, Builder> implements PictureSizeOrBuilder {
            private Builder() {
                super(PictureSize.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearHeight() {
                copyOnWrite();
                ((PictureSize) this.instance).clearHeight();
                return this;
            }

            public Builder clearWidth() {
                copyOnWrite();
                ((PictureSize) this.instance).clearWidth();
                return this;
            }

            @Override
            public int getHeight() {
                return ((PictureSize) this.instance).getHeight();
            }

            @Override
            public int getWidth() {
                return ((PictureSize) this.instance).getWidth();
            }

            public Builder setHeight(int i) {
                copyOnWrite();
                ((PictureSize) this.instance).setHeight(i);
                return this;
            }

            public Builder setWidth(int i) {
                copyOnWrite();
                ((PictureSize) this.instance).setWidth(i);
                return this;
            }
        }

        static {
            PictureSize pictureSize = new PictureSize();
            DEFAULT_INSTANCE = pictureSize;
            GeneratedMessageLite.registerDefaultInstance(PictureSize.class, pictureSize);
        }

        private PictureSize() {
        }

        public void clearHeight() {
            this.height_ = 0;
        }

        public void clearWidth() {
            this.width_ = 0;
        }

        public static PictureSize getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(PictureSize pictureSize) {
            return DEFAULT_INSTANCE.createBuilder(pictureSize);
        }

        public static PictureSize parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PictureSize) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PictureSize parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (PictureSize) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static PictureSize parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (PictureSize) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static PictureSize parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (PictureSize) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static PictureSize parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (PictureSize) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static PictureSize parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (PictureSize) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static PictureSize parseFrom(InputStream inputStream) throws IOException {
            return (PictureSize) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PictureSize parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (PictureSize) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static PictureSize parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (PictureSize) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static PictureSize parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (PictureSize) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static PictureSize parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PictureSize) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static PictureSize parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (PictureSize) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<PictureSize> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setHeight(int i) {
            this.height_ = i;
        }

        public void setWidth(int i) {
            this.width_ = i;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new PictureSize();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0002\u0004", new Object[]{"width_", "height_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<PictureSize> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (PictureSize.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public int getHeight() {
            return this.height_;
        }

        @Override
        public int getWidth() {
            return this.width_;
        }
    }

    public interface PictureSizeOrBuilder extends MessageLiteOrBuilder {
        int getHeight();

        int getWidth();
    }

    public enum SampleRate implements Internal.EnumLite {
        UNKNOWN_RATE(0),
        SAMPLE_RATE_8000(8000),
        SAMPLE_RATE_11025(11025),
        SAMPLE_RATE_12000(12000),
        SAMPLE_RATE_16000(16000),
        SAMPLE_RATE_22050(22050),
        SAMPLE_RATE_24000(24000),
        SAMPLE_RATE_32000(32000),
        SAMPLE_RATE_44100(44100),
        SAMPLE_RATE_48000(48000),
        SAMPLE_RATE_96000(96000),
        SAMPLE_RATE_192000(192000),
        UNRECOGNIZED(-1);

        public static final int SAMPLE_RATE_11025_VALUE = 11025;
        public static final int SAMPLE_RATE_12000_VALUE = 12000;
        public static final int SAMPLE_RATE_16000_VALUE = 16000;
        public static final int SAMPLE_RATE_192000_VALUE = 192000;
        public static final int SAMPLE_RATE_22050_VALUE = 22050;
        public static final int SAMPLE_RATE_24000_VALUE = 24000;
        public static final int SAMPLE_RATE_32000_VALUE = 32000;
        public static final int SAMPLE_RATE_44100_VALUE = 44100;
        public static final int SAMPLE_RATE_48000_VALUE = 48000;
        public static final int SAMPLE_RATE_8000_VALUE = 8000;
        public static final int SAMPLE_RATE_96000_VALUE = 96000;
        public static final int UNKNOWN_RATE_VALUE = 0;
        private static final Internal.EnumLiteMap<SampleRate> internalValueMap = new Internal.EnumLiteMap<SampleRate>() {
            @Override
            public SampleRate findValueByNumber(int i) {
                return SampleRate.forNumber(i);
            }
        };
        private final int value;

        public static final class SampleRateVerifier implements Internal.EnumVerifier {
            public static final Internal.EnumVerifier INSTANCE = new SampleRateVerifier();

            private SampleRateVerifier() {
            }

            @Override
            public boolean isInRange(int i) {
                return SampleRate.forNumber(i) != null;
            }
        }

        SampleRate(int i) {
            this.value = i;
        }

        public static SampleRate forNumber(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN_RATE;
                case SAMPLE_RATE_8000_VALUE:
                    return SAMPLE_RATE_8000;
                case SAMPLE_RATE_11025_VALUE:
                    return SAMPLE_RATE_11025;
                case SAMPLE_RATE_12000_VALUE:
                    return SAMPLE_RATE_12000;
                case SAMPLE_RATE_16000_VALUE:
                    return SAMPLE_RATE_16000;
                case SAMPLE_RATE_22050_VALUE:
                    return SAMPLE_RATE_22050;
                case SAMPLE_RATE_24000_VALUE:
                    return SAMPLE_RATE_24000;
                case SAMPLE_RATE_32000_VALUE:
                    return SAMPLE_RATE_32000;
                case SAMPLE_RATE_44100_VALUE:
                    return SAMPLE_RATE_44100;
                case SAMPLE_RATE_48000_VALUE:
                    return SAMPLE_RATE_48000;
                case SAMPLE_RATE_96000_VALUE:
                    return SAMPLE_RATE_96000;
                case SAMPLE_RATE_192000_VALUE:
                    return SAMPLE_RATE_192000;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<SampleRate> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return SampleRateVerifier.INSTANCE;
        }

        @Deprecated
        public static SampleRate valueOf(int i) {
            return forNumber(i);
        }

        @Override
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
    }

    public static final class SensorIndex extends GeneratedMessageLite<SensorIndex, SensorIndex.Builder> implements SensorIndexOrBuilder {
        public static final int ACCELERATION_FIELD_NUMBER = 4;
        private static final SensorIndex DEFAULT_INSTANCE;
        public static final int GEAR_INFO_FIELD_NUMBER = 6;
        public static final int GPS_FIELD_NUMBER = 1;
        public static final int GYRO_SCOPE_FIELD_NUMBER = 3;
        public static final int LIGHTS_FIELD_NUMBER = 2;
        public static final int LIGHT_SENSOR_INFO_FIELD_NUMBER = 7;
        public static final int OIL_FIELD_NUMBER = 5;
        private static volatile Parser<SensorIndex> PARSER;
        private Acceleration acceleration_;
        private GearInfo gearInfo_;
        private Gps gps_;
        private GyroScope gyroScope_;
        private LightSensorInfo lightSensorInfo_;
        private Lights lights_;
        private Oil oil_;

        public static final class Builder extends GeneratedMessageLite.Builder<SensorIndex, Builder> implements SensorIndexOrBuilder {
            private Builder() {
                super(SensorIndex.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearAcceleration() {
                copyOnWrite();
                ((SensorIndex) this.instance).clearAcceleration();
                return this;
            }

            public Builder clearGearInfo() {
                copyOnWrite();
                ((SensorIndex) this.instance).clearGearInfo();
                return this;
            }

            public Builder clearGps() {
                copyOnWrite();
                ((SensorIndex) this.instance).clearGps();
                return this;
            }

            public Builder clearGyroScope() {
                copyOnWrite();
                ((SensorIndex) this.instance).clearGyroScope();
                return this;
            }

            public Builder clearLightSensorInfo() {
                copyOnWrite();
                ((SensorIndex) this.instance).clearLightSensorInfo();
                return this;
            }

            public Builder clearLights() {
                copyOnWrite();
                ((SensorIndex) this.instance).clearLights();
                return this;
            }

            public Builder clearOil() {
                copyOnWrite();
                ((SensorIndex) this.instance).clearOil();
                return this;
            }

            @Override
            public Acceleration getAcceleration() {
                return ((SensorIndex) this.instance).getAcceleration();
            }

            @Override
            public GearInfo getGearInfo() {
                return ((SensorIndex) this.instance).getGearInfo();
            }

            @Override
            public Gps getGps() {
                return ((SensorIndex) this.instance).getGps();
            }

            @Override
            public GyroScope getGyroScope() {
                return ((SensorIndex) this.instance).getGyroScope();
            }

            @Override
            public LightSensorInfo getLightSensorInfo() {
                return ((SensorIndex) this.instance).getLightSensorInfo();
            }

            @Override
            public Lights getLights() {
                return ((SensorIndex) this.instance).getLights();
            }

            @Override
            public Oil getOil() {
                return ((SensorIndex) this.instance).getOil();
            }

            @Override
            public boolean hasAcceleration() {
                return ((SensorIndex) this.instance).hasAcceleration();
            }

            @Override
            public boolean hasGearInfo() {
                return ((SensorIndex) this.instance).hasGearInfo();
            }

            @Override
            public boolean hasGps() {
                return ((SensorIndex) this.instance).hasGps();
            }

            @Override
            public boolean hasGyroScope() {
                return ((SensorIndex) this.instance).hasGyroScope();
            }

            @Override
            public boolean hasLightSensorInfo() {
                return ((SensorIndex) this.instance).hasLightSensorInfo();
            }

            @Override
            public boolean hasLights() {
                return ((SensorIndex) this.instance).hasLights();
            }

            @Override
            public boolean hasOil() {
                return ((SensorIndex) this.instance).hasOil();
            }

            public Builder mergeAcceleration(Acceleration acceleration) {
                copyOnWrite();
                ((SensorIndex) this.instance).mergeAcceleration(acceleration);
                return this;
            }

            public Builder mergeGearInfo(GearInfo gearInfo) {
                copyOnWrite();
                ((SensorIndex) this.instance).mergeGearInfo(gearInfo);
                return this;
            }

            public Builder mergeGps(Gps gps) {
                copyOnWrite();
                ((SensorIndex) this.instance).mergeGps(gps);
                return this;
            }

            public Builder mergeGyroScope(GyroScope gyroScope) {
                copyOnWrite();
                ((SensorIndex) this.instance).mergeGyroScope(gyroScope);
                return this;
            }

            public Builder mergeLightSensorInfo(LightSensorInfo lightSensorInfo) {
                copyOnWrite();
                ((SensorIndex) this.instance).mergeLightSensorInfo(lightSensorInfo);
                return this;
            }

            public Builder mergeLights(Lights lights) {
                copyOnWrite();
                ((SensorIndex) this.instance).mergeLights(lights);
                return this;
            }

            public Builder mergeOil(Oil oil) {
                copyOnWrite();
                ((SensorIndex) this.instance).mergeOil(oil);
                return this;
            }

            public Builder setAcceleration(Acceleration.Builder builder) {
                copyOnWrite();
                ((SensorIndex) this.instance).setAcceleration(builder.build());
                return this;
            }

            public Builder setAcceleration(Acceleration acceleration) {
                copyOnWrite();
                ((SensorIndex) this.instance).setAcceleration(acceleration);
                return this;
            }

            public Builder setGearInfo(GearInfo.Builder builder) {
                copyOnWrite();
                ((SensorIndex) this.instance).setGearInfo(builder.build());
                return this;
            }

            public Builder setGearInfo(GearInfo gearInfo) {
                copyOnWrite();
                ((SensorIndex) this.instance).setGearInfo(gearInfo);
                return this;
            }

            public Builder setGps(Gps.Builder builder) {
                copyOnWrite();
                ((SensorIndex) this.instance).setGps(builder.build());
                return this;
            }

            public Builder setGps(Gps gps) {
                copyOnWrite();
                ((SensorIndex) this.instance).setGps(gps);
                return this;
            }

            public Builder setGyroScope(GyroScope.Builder builder) {
                copyOnWrite();
                ((SensorIndex) this.instance).setGyroScope(builder.build());
                return this;
            }

            public Builder setGyroScope(GyroScope gyroScope) {
                copyOnWrite();
                ((SensorIndex) this.instance).setGyroScope(gyroScope);
                return this;
            }

            public Builder setLightSensorInfo(LightSensorInfo.Builder builder) {
                copyOnWrite();
                ((SensorIndex) this.instance).setLightSensorInfo(builder.build());
                return this;
            }

            public Builder setLightSensorInfo(LightSensorInfo lightSensorInfo) {
                copyOnWrite();
                ((SensorIndex) this.instance).setLightSensorInfo(lightSensorInfo);
                return this;
            }

            public Builder setLights(Lights.Builder builder) {
                copyOnWrite();
                ((SensorIndex) this.instance).setLights(builder.build());
                return this;
            }

            public Builder setLights(Lights lights) {
                copyOnWrite();
                ((SensorIndex) this.instance).setLights(lights);
                return this;
            }

            public Builder setOil(Oil.Builder builder) {
                copyOnWrite();
                ((SensorIndex) this.instance).setOil(builder.build());
                return this;
            }

            public Builder setOil(Oil oil) {
                copyOnWrite();
                ((SensorIndex) this.instance).setOil(oil);
                return this;
            }
        }

        static {
            SensorIndex sensorIndex = new SensorIndex();
            DEFAULT_INSTANCE = sensorIndex;
            GeneratedMessageLite.registerDefaultInstance(SensorIndex.class, sensorIndex);
        }

        private SensorIndex() {
        }

        public void clearAcceleration() {
            this.acceleration_ = null;
        }

        public void clearGearInfo() {
            this.gearInfo_ = null;
        }

        public void clearGps() {
            this.gps_ = null;
        }

        public void clearGyroScope() {
            this.gyroScope_ = null;
        }

        public void clearLightSensorInfo() {
            this.lightSensorInfo_ = null;
        }

        public void clearLights() {
            this.lights_ = null;
        }

        public void clearOil() {
            this.oil_ = null;
        }

        public static SensorIndex getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public void mergeAcceleration(Acceleration acceleration) {
            acceleration.getClass();
            Acceleration acceleration2 = this.acceleration_;
            if (acceleration2 != null && acceleration2 != Acceleration.getDefaultInstance()) {
                acceleration = Acceleration.newBuilder(this.acceleration_).mergeFrom(acceleration).buildPartial();
            }
            this.acceleration_ = acceleration;
        }

        public void mergeGearInfo(GearInfo gearInfo) {
            gearInfo.getClass();
            GearInfo gearInfo2 = this.gearInfo_;
            if (gearInfo2 != null && gearInfo2 != GearInfo.getDefaultInstance()) {
                gearInfo = GearInfo.newBuilder(this.gearInfo_).mergeFrom(gearInfo).buildPartial();
            }
            this.gearInfo_ = gearInfo;
        }

        public void mergeGps(Gps gps) {
            gps.getClass();
            Gps gps2 = this.gps_;
            if (gps2 != null && gps2 != Gps.getDefaultInstance()) {
                gps = Gps.newBuilder(this.gps_).mergeFrom(gps).buildPartial();
            }
            this.gps_ = gps;
        }

        public void mergeGyroScope(GyroScope gyroScope) {
            gyroScope.getClass();
            GyroScope gyroScope2 = this.gyroScope_;
            if (gyroScope2 != null && gyroScope2 != GyroScope.getDefaultInstance()) {
                gyroScope = GyroScope.newBuilder(this.gyroScope_).mergeFrom(gyroScope).buildPartial();
            }
            this.gyroScope_ = gyroScope;
        }

        public void mergeLightSensorInfo(LightSensorInfo lightSensorInfo) {
            lightSensorInfo.getClass();
            LightSensorInfo lightSensorInfo2 = this.lightSensorInfo_;
            if (lightSensorInfo2 != null && lightSensorInfo2 != LightSensorInfo.getDefaultInstance()) {
                lightSensorInfo = LightSensorInfo.newBuilder(this.lightSensorInfo_).mergeFrom(lightSensorInfo).buildPartial();
            }
            this.lightSensorInfo_ = lightSensorInfo;
        }

        public void mergeLights(Lights lights) {
            lights.getClass();
            Lights lights2 = this.lights_;
            if (lights2 != null && lights2 != Lights.getDefaultInstance()) {
                lights = Lights.newBuilder(this.lights_).mergeFrom(lights).buildPartial();
            }
            this.lights_ = lights;
        }

        public void mergeOil(Oil oil) {
            oil.getClass();
            Oil oil2 = this.oil_;
            if (oil2 != null && oil2 != Oil.getDefaultInstance()) {
                oil = Oil.newBuilder(this.oil_).mergeFrom(oil).buildPartial();
            }
            this.oil_ = oil;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(SensorIndex sensorIndex) {
            return DEFAULT_INSTANCE.createBuilder(sensorIndex);
        }

        public static SensorIndex parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SensorIndex) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SensorIndex parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (SensorIndex) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static SensorIndex parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (SensorIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static SensorIndex parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (SensorIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static SensorIndex parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (SensorIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static SensorIndex parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (SensorIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static SensorIndex parseFrom(InputStream inputStream) throws IOException {
            return (SensorIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SensorIndex parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (SensorIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static SensorIndex parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SensorIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static SensorIndex parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (SensorIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static SensorIndex parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SensorIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SensorIndex parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (SensorIndex) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<SensorIndex> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setAcceleration(Acceleration acceleration) {
            acceleration.getClass();
            this.acceleration_ = acceleration;
        }

        public void setGearInfo(GearInfo gearInfo) {
            gearInfo.getClass();
            this.gearInfo_ = gearInfo;
        }

        public void setGps(Gps gps) {
            gps.getClass();
            this.gps_ = gps;
        }

        public void setGyroScope(GyroScope gyroScope) {
            gyroScope.getClass();
            this.gyroScope_ = gyroScope;
        }

        public void setLightSensorInfo(LightSensorInfo lightSensorInfo) {
            lightSensorInfo.getClass();
            this.lightSensorInfo_ = lightSensorInfo;
        }

        public void setLights(Lights lights) {
            lights.getClass();
            this.lights_ = lights;
        }

        public void setOil(Oil oil) {
            oil.getClass();
            this.oil_ = oil;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new SensorIndex();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0000\u0000\u0001\t\u0002\t\u0003\t\u0004\t\u0005\t\u0006\t\u0007\t", new Object[]{"gps_", "lights_", "gyroScope_", "acceleration_", "oil_", "gearInfo_", "lightSensorInfo_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<SensorIndex> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (SensorIndex.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public Acceleration getAcceleration() {
            Acceleration acceleration = this.acceleration_;
            return acceleration == null ? Acceleration.getDefaultInstance() : acceleration;
        }

        @Override
        public GearInfo getGearInfo() {
            GearInfo gearInfo = this.gearInfo_;
            return gearInfo == null ? GearInfo.getDefaultInstance() : gearInfo;
        }

        @Override
        public Gps getGps() {
            Gps gps = this.gps_;
            return gps == null ? Gps.getDefaultInstance() : gps;
        }

        @Override
        public GyroScope getGyroScope() {
            GyroScope gyroScope = this.gyroScope_;
            return gyroScope == null ? GyroScope.getDefaultInstance() : gyroScope;
        }

        @Override
        public LightSensorInfo getLightSensorInfo() {
            LightSensorInfo lightSensorInfo = this.lightSensorInfo_;
            return lightSensorInfo == null ? LightSensorInfo.getDefaultInstance() : lightSensorInfo;
        }

        @Override
        public Lights getLights() {
            Lights lights = this.lights_;
            return lights == null ? Lights.getDefaultInstance() : lights;
        }

        @Override
        public Oil getOil() {
            Oil oil = this.oil_;
            return oil == null ? Oil.getDefaultInstance() : oil;
        }

        @Override
        public boolean hasAcceleration() {
            return this.acceleration_ != null;
        }

        @Override
        public boolean hasGearInfo() {
            return this.gearInfo_ != null;
        }

        @Override
        public boolean hasGps() {
            return this.gps_ != null;
        }

        @Override
        public boolean hasGyroScope() {
            return this.gyroScope_ != null;
        }

        @Override
        public boolean hasLightSensorInfo() {
            return this.lightSensorInfo_ != null;
        }

        @Override
        public boolean hasLights() {
            return this.lights_ != null;
        }

        @Override
        public boolean hasOil() {
            return this.oil_ != null;
        }
    }

    public interface SensorIndexOrBuilder extends MessageLiteOrBuilder {
        Acceleration getAcceleration();

        GearInfo getGearInfo();

        Gps getGps();

        GyroScope getGyroScope();

        LightSensorInfo getLightSensorInfo();

        Lights getLights();

        Oil getOil();

        boolean hasAcceleration();

        boolean hasGearInfo();

        boolean hasGps();

        boolean hasGyroScope();

        boolean hasLightSensorInfo();

        boolean hasLights();

        boolean hasOil();
    }

    public static final class SetCameraState extends GeneratedMessageLite<SetCameraState, SetCameraState.Builder> implements SetCameraStateOrBuilder {
        public static final int ACTION_FIELD_NUMBER = 3;
        public static final int CAMERA_ID_FIELD_NUMBER = 1;
        private static final SetCameraState DEFAULT_INSTANCE;
        public static final int FPS_RANGE_FIELD_NUMBER = 2;
        private static volatile Parser<SetCameraState> PARSER = null;
        public static final int PICTURE_SIZE_FIELD_NUMBER = 4;
        private int action_;
        private String cameraId_ = "";
        private FpsRange fpsRange_;
        private PictureSize pictureSize_;

        public static final class Builder extends GeneratedMessageLite.Builder<SetCameraState, Builder> implements SetCameraStateOrBuilder {
            private Builder() {
                super(SetCameraState.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearAction() {
                copyOnWrite();
                ((SetCameraState) this.instance).clearAction();
                return this;
            }

            public Builder clearCameraId() {
                copyOnWrite();
                ((SetCameraState) this.instance).clearCameraId();
                return this;
            }

            public Builder clearFpsRange() {
                copyOnWrite();
                ((SetCameraState) this.instance).clearFpsRange();
                return this;
            }

            public Builder clearPictureSize() {
                copyOnWrite();
                ((SetCameraState) this.instance).clearPictureSize();
                return this;
            }

            @Override
            public CameraAction getAction() {
                return ((SetCameraState) this.instance).getAction();
            }

            @Override
            public int getActionValue() {
                return ((SetCameraState) this.instance).getActionValue();
            }

            @Override
            public String getCameraId() {
                return ((SetCameraState) this.instance).getCameraId();
            }

            @Override
            public ByteString getCameraIdBytes() {
                return ((SetCameraState) this.instance).getCameraIdBytes();
            }

            @Override
            public FpsRange getFpsRange() {
                return ((SetCameraState) this.instance).getFpsRange();
            }

            @Override
            public PictureSize getPictureSize() {
                return ((SetCameraState) this.instance).getPictureSize();
            }

            @Override
            public boolean hasFpsRange() {
                return ((SetCameraState) this.instance).hasFpsRange();
            }

            @Override
            public boolean hasPictureSize() {
                return ((SetCameraState) this.instance).hasPictureSize();
            }

            public Builder mergeFpsRange(FpsRange fpsRange) {
                copyOnWrite();
                ((SetCameraState) this.instance).mergeFpsRange(fpsRange);
                return this;
            }

            public Builder mergePictureSize(PictureSize pictureSize) {
                copyOnWrite();
                ((SetCameraState) this.instance).mergePictureSize(pictureSize);
                return this;
            }

            public Builder setAction(CameraAction cameraAction) {
                copyOnWrite();
                ((SetCameraState) this.instance).setAction(cameraAction);
                return this;
            }

            public Builder setActionValue(int i) {
                copyOnWrite();
                ((SetCameraState) this.instance).setActionValue(i);
                return this;
            }

            public Builder setCameraId(String str) {
                copyOnWrite();
                ((SetCameraState) this.instance).setCameraId(str);
                return this;
            }

            public Builder setCameraIdBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((SetCameraState) this.instance).setCameraIdBytes(abstractC2534u);
                return this;
            }

            public Builder setFpsRange(FpsRange.Builder builder) {
                copyOnWrite();
                ((SetCameraState) this.instance).setFpsRange(builder.build());
                return this;
            }

            public Builder setFpsRange(FpsRange fpsRange) {
                copyOnWrite();
                ((SetCameraState) this.instance).setFpsRange(fpsRange);
                return this;
            }

            public Builder setPictureSize(PictureSize.Builder builder) {
                copyOnWrite();
                ((SetCameraState) this.instance).setPictureSize(builder.build());
                return this;
            }

            public Builder setPictureSize(PictureSize pictureSize) {
                copyOnWrite();
                ((SetCameraState) this.instance).setPictureSize(pictureSize);
                return this;
            }
        }

        static {
            SetCameraState setCameraState = new SetCameraState();
            DEFAULT_INSTANCE = setCameraState;
            GeneratedMessageLite.registerDefaultInstance(SetCameraState.class, setCameraState);
        }

        private SetCameraState() {
        }

        public void clearAction() {
            this.action_ = 0;
        }

        public void clearCameraId() {
            this.cameraId_ = getDefaultInstance().getCameraId();
        }

        public void clearFpsRange() {
            this.fpsRange_ = null;
        }

        public void clearPictureSize() {
            this.pictureSize_ = null;
        }

        public static SetCameraState getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public void mergeFpsRange(FpsRange fpsRange) {
            fpsRange.getClass();
            FpsRange fpsRange2 = this.fpsRange_;
            if (fpsRange2 != null && fpsRange2 != FpsRange.getDefaultInstance()) {
                fpsRange = FpsRange.newBuilder(this.fpsRange_).mergeFrom(fpsRange).buildPartial();
            }
            this.fpsRange_ = fpsRange;
        }

        public void mergePictureSize(PictureSize pictureSize) {
            pictureSize.getClass();
            PictureSize pictureSize2 = this.pictureSize_;
            if (pictureSize2 != null && pictureSize2 != PictureSize.getDefaultInstance()) {
                pictureSize = PictureSize.newBuilder(this.pictureSize_).mergeFrom(pictureSize).buildPartial();
            }
            this.pictureSize_ = pictureSize;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(SetCameraState setCameraState) {
            return DEFAULT_INSTANCE.createBuilder(setCameraState);
        }

        public static SetCameraState parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (SetCameraState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SetCameraState parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (SetCameraState) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static SetCameraState parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (SetCameraState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static SetCameraState parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (SetCameraState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static SetCameraState parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (SetCameraState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static SetCameraState parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (SetCameraState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static SetCameraState parseFrom(InputStream inputStream) throws IOException {
            return (SetCameraState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SetCameraState parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (SetCameraState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static SetCameraState parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (SetCameraState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static SetCameraState parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (SetCameraState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static SetCameraState parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (SetCameraState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SetCameraState parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (SetCameraState) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<SetCameraState> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setAction(CameraAction cameraAction) {
            this.action_ = cameraAction.getNumber();
        }

        public void setActionValue(int i) {
            this.action_ = i;
        }

        public void setCameraId(String str) {
            str.getClass();
            this.cameraId_ = str;
        }

        public void setCameraIdBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.cameraId_ = abstractC2534u.toStringUtf8();
        }

        public void setFpsRange(FpsRange fpsRange) {
            fpsRange.getClass();
            this.fpsRange_ = fpsRange;
        }

        public void setPictureSize(PictureSize pictureSize) {
            pictureSize.getClass();
            this.pictureSize_ = pictureSize;
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new SetCameraState();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\t\u0003\f\u0004\t", new Object[]{"cameraId_", "fpsRange_", "action_", "pictureSize_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<SetCameraState> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (SetCameraState.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public CameraAction getAction() {
            CameraAction cameraActionForNumber = CameraAction.forNumber(this.action_);
            return cameraActionForNumber == null ? CameraAction.UNRECOGNIZED : cameraActionForNumber;
        }

        @Override
        public int getActionValue() {
            return this.action_;
        }

        @Override
        public String getCameraId() {
            return this.cameraId_;
        }

        @Override
        public ByteString getCameraIdBytes() {
            return ByteString.copyFromUtf8(this.cameraId_);
        }

        @Override
        public FpsRange getFpsRange() {
            FpsRange fpsRange = this.fpsRange_;
            return fpsRange == null ? FpsRange.getDefaultInstance() : fpsRange;
        }

        @Override
        public PictureSize getPictureSize() {
            PictureSize pictureSize = this.pictureSize_;
            return pictureSize == null ? PictureSize.getDefaultInstance() : pictureSize;
        }

        @Override
        public boolean hasFpsRange() {
            return this.fpsRange_ != null;
        }

        @Override
        public boolean hasPictureSize() {
            return this.pictureSize_ != null;
        }
    }

    public interface SetCameraStateOrBuilder extends MessageLiteOrBuilder {
        CameraAction getAction();

        int getActionValue();

        String getCameraId();

        ByteString getCameraIdBytes();

        FpsRange getFpsRange();

        PictureSize getPictureSize();

        boolean hasFpsRange();

        boolean hasPictureSize();
    }

    public static final class VRCmdToPhone extends GeneratedMessageLite<VRCmdToPhone, VRCmdToPhone.Builder> implements VRCmdToPhoneOrBuilder {
        public static final int CMD_FIELD_NUMBER = 1;
        private static final VRCmdToPhone DEFAULT_INSTANCE;
        private static volatile Parser<VRCmdToPhone> PARSER = null;
        public static final int SOURCE_FIELD_NUMBER = 2;
        private int cmd_;
        private String source_ = "";

        public static final class Builder extends GeneratedMessageLite.Builder<VRCmdToPhone, Builder> implements VRCmdToPhoneOrBuilder {
            private Builder() {
                super(VRCmdToPhone.DEFAULT_INSTANCE);
            }

            public Builder(C10991 c10991) {
                this();
            }

            public Builder clearCmd() {
                copyOnWrite();
                ((VRCmdToPhone) this.instance).clearCmd();
                return this;
            }

            public Builder clearSource() {
                copyOnWrite();
                ((VRCmdToPhone) this.instance).clearSource();
                return this;
            }

            @Override
            public VRCmd getCmd() {
                return ((VRCmdToPhone) this.instance).getCmd();
            }

            @Override
            public int getCmdValue() {
                return ((VRCmdToPhone) this.instance).getCmdValue();
            }

            @Override
            public String getSource() {
                return ((VRCmdToPhone) this.instance).getSource();
            }

            @Override
            public ByteString getSourceBytes() {
                return ((VRCmdToPhone) this.instance).getSourceBytes();
            }

            public Builder setCmd(VRCmd vRCmd) {
                copyOnWrite();
                ((VRCmdToPhone) this.instance).setCmd(vRCmd);
                return this;
            }

            public Builder setCmdValue(int i) {
                copyOnWrite();
                ((VRCmdToPhone) this.instance).setCmdValue(i);
                return this;
            }

            public Builder setSource(String str) {
                copyOnWrite();
                ((VRCmdToPhone) this.instance).setSource(str);
                return this;
            }

            public Builder setSourceBytes(ByteString abstractC2534u) {
                copyOnWrite();
                ((VRCmdToPhone) this.instance).setSourceBytes(abstractC2534u);
                return this;
            }
        }

        public enum VRCmd implements Internal.EnumLite {
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
            UNRECOGNIZED(-1);

            public static final int VR_CMD_CALL_VALUE = 5;
            public static final int VR_CMD_ENDCALL_VALUE = 6;
            public static final int VR_CMD_MAP_VALUE = 8;
            public static final int VR_CMD_MUSIC_NEXT_VALUE = 2;
            public static final int VR_CMD_MUSIC_PAUSE_VALUE = 3;
            public static final int VR_CMD_MUSIC_PLAY_VALUE = 4;
            public static final int VR_CMD_MUSIC_PRE_VALUE = 1;
            public static final int VR_CMD_MUSIC_VALUE = 9;
            public static final int VR_CMD_NAVI_COMPANY_VALUE = 14;
            public static final int VR_CMD_NAVI_HOME_VALUE = 13;
            public static final int VR_CMD_NAVI_QUIT_VALUE = 12;
            public static final int VR_CMD_TEL_VALUE = 7;
            public static final int VR_CMD_UNDEFINED_VALUE = 0;
            public static final int VR_CMD_VR_START_VALUE = 10;
            public static final int VR_CMD_VR_STOP_VALUE = 11;
            private static final Internal.EnumLiteMap<VRCmd> internalValueMap = new Internal.EnumLiteMap<VRCmd>() {
                @Override
                public VRCmd findValueByNumber(int i) {
                    return VRCmd.forNumber(i);
                }
            };
            private final int value;

            public static final class VRCmdVerifier implements Internal.EnumVerifier {
                public static final Internal.EnumVerifier INSTANCE = new VRCmdVerifier();

                private VRCmdVerifier() {
                }

                @Override
                public boolean isInRange(int i) {
                    return VRCmd.forNumber(i) != null;
                }
            }

            VRCmd(int i) {
                this.value = i;
            }

            public static VRCmd forNumber(int i) {
                switch (i) {
                    case 0:
                        return VR_CMD_UNDEFINED;
                    case 1:
                        return VR_CMD_MUSIC_PRE;
                    case 2:
                        return VR_CMD_MUSIC_NEXT;
                    case 3:
                        return VR_CMD_MUSIC_PAUSE;
                    case 4:
                        return VR_CMD_MUSIC_PLAY;
                    case 5:
                        return VR_CMD_CALL;
                    case 6:
                        return VR_CMD_ENDCALL;
                    case 7:
                        return VR_CMD_TEL;
                    case 8:
                        return VR_CMD_MAP;
                    case 9:
                        return VR_CMD_MUSIC;
                    case 10:
                        return VR_CMD_VR_START;
                    case 11:
                        return VR_CMD_VR_STOP;
                    case 12:
                        return VR_CMD_NAVI_QUIT;
                    case 13:
                        return VR_CMD_NAVI_HOME;
                    case 14:
                        return VR_CMD_NAVI_COMPANY;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<VRCmd> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return VRCmdVerifier.INSTANCE;
            }

            @Deprecated
            public static VRCmd valueOf(int i) {
                return forNumber(i);
            }

            @Override
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
        }

        static {
            VRCmdToPhone vRCmdToPhone = new VRCmdToPhone();
            DEFAULT_INSTANCE = vRCmdToPhone;
            GeneratedMessageLite.registerDefaultInstance(VRCmdToPhone.class, vRCmdToPhone);
        }

        private VRCmdToPhone() {
        }

        public void clearCmd() {
            this.cmd_ = 0;
        }

        public void clearSource() {
            this.source_ = getDefaultInstance().getSource();
        }

        public static VRCmdToPhone getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(VRCmdToPhone vRCmdToPhone) {
            return DEFAULT_INSTANCE.createBuilder(vRCmdToPhone);
        }

        public static VRCmdToPhone parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (VRCmdToPhone) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static VRCmdToPhone parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (VRCmdToPhone) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static VRCmdToPhone parseFrom(ByteString abstractC2534u) throws InvalidProtocolBufferException {
            return (VRCmdToPhone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u);
        }

        public static VRCmdToPhone parseFrom(ByteString abstractC2534u, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (VRCmdToPhone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2534u, c2517r0);
        }

        public static VRCmdToPhone parseFrom(CodedInputStream abstractC2549x) throws IOException {
            return (VRCmdToPhone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x);
        }

        public static VRCmdToPhone parseFrom(CodedInputStream abstractC2549x, ExtensionRegistryLite c2517r0) throws IOException {
            return (VRCmdToPhone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, abstractC2549x, c2517r0);
        }

        public static VRCmdToPhone parseFrom(InputStream inputStream) throws IOException {
            return (VRCmdToPhone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static VRCmdToPhone parseFrom(InputStream inputStream, ExtensionRegistryLite c2517r0) throws IOException {
            return (VRCmdToPhone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, c2517r0);
        }

        public static VRCmdToPhone parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (VRCmdToPhone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static VRCmdToPhone parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (VRCmdToPhone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, c2517r0);
        }

        public static VRCmdToPhone parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (VRCmdToPhone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static VRCmdToPhone parseFrom(byte[] bArr, ExtensionRegistryLite c2517r0) throws InvalidProtocolBufferException {
            return (VRCmdToPhone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, c2517r0);
        }

        public static Parser<VRCmdToPhone> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        public void setCmd(VRCmd vRCmd) {
            this.cmd_ = vRCmd.getNumber();
        }

        public void setCmdValue(int i) {
            this.cmd_ = i;
        }

        public void setSource(String str) {
            str.getClass();
            this.source_ = str;
        }

        public void setSourceBytes(ByteString abstractC2534u) {
            checkByteStringIsUtf8(abstractC2534u);
            this.source_ = abstractC2534u.toStringUtf8();
        }

        @Override
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke iVar, Object obj, Object obj2) {
            C10991 c10991 = null;
            switch (C10991.f9470xa1dLEVEL_VERBOSE61[iVar.ordinal()]) {
                case 1:
                    return new VRCmdToPhone();
                case 2:
                    return new Builder(c10991);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002Ȉ", new Object[]{"cmd_", "source_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<VRCmdToPhone> cVar = PARSER;
                    if (cVar == null) {
                        synchronized (VRCmdToPhone.class) {
                            cVar = PARSER;
                            if (cVar == null) {
                                cVar = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = cVar;
                            }
                            return cVar;
                        }
                    }
                    return cVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override
        public VRCmd getCmd() {
            VRCmd vRCmdForNumber = VRCmd.forNumber(this.cmd_);
            return vRCmdForNumber == null ? VRCmd.UNRECOGNIZED : vRCmdForNumber;
        }

        @Override
        public int getCmdValue() {
            return this.cmd_;
        }

        @Override
        public String getSource() {
            return this.source_;
        }

        @Override
        public ByteString getSourceBytes() {
            return ByteString.copyFromUtf8(this.source_);
        }
    }

    public interface VRCmdToPhoneOrBuilder extends MessageLiteOrBuilder {
        VRCmdToPhone.VRCmd getCmd();

        int getCmdValue();

        String getSource();

        ByteString getSourceBytes();
    }

    public enum VideoType implements Internal.EnumLite {
        VIDEO_UNDEFINED(0),
        VIDEO_CAST(1),
        VIDEO_COMMUNICATION(2),
        VIDEO_CAMERA_PREVIEW(3),
        VIDEO_CAMERA_PICTURE(4),
        UNRECOGNIZED(-1);

        public static final int VIDEO_CAMERA_PICTURE_VALUE = 4;
        public static final int VIDEO_CAMERA_PREVIEW_VALUE = 3;
        public static final int VIDEO_CAST_VALUE = 1;
        public static final int VIDEO_COMMUNICATION_VALUE = 2;
        public static final int VIDEO_UNDEFINED_VALUE = 0;
        private static final Internal.EnumLiteMap<VideoType> internalValueMap = new Internal.EnumLiteMap<VideoType>() {
            @Override
            public VideoType findValueByNumber(int i) {
                return VideoType.forNumber(i);
            }
        };
        private final int value;

        public static final class VideoTypeVerifier implements Internal.EnumVerifier {
            public static final Internal.EnumVerifier INSTANCE = new VideoTypeVerifier();

            private VideoTypeVerifier() {
            }

            @Override
            public boolean isInRange(int i) {
                return VideoType.forNumber(i) != null;
            }
        }

        VideoType(int i) {
            this.value = i;
        }

        public static VideoType forNumber(int i) {
            if (i == 0) {
                return VIDEO_UNDEFINED;
            }
            if (i == 1) {
                return VIDEO_CAST;
            }
            if (i == 2) {
                return VIDEO_COMMUNICATION;
            }
            if (i == 3) {
                return VIDEO_CAMERA_PREVIEW;
            }
            if (i != 4) {
                return null;
            }
            return VIDEO_CAMERA_PICTURE;
        }

        public static Internal.EnumLiteMap<VideoType> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return VideoTypeVerifier.INSTANCE;
        }

        @Deprecated
        public static VideoType valueOf(int i) {
            return forNumber(i);
        }

        @Override
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
    }

    private UCarProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite c2517r0) {
    }
}
