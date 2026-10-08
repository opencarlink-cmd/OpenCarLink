package com.ucar.vehiclesdk.recorder;

import android.os.Parcel;
import android.os.Parcelable;
import com.ucar.databus.proto.UCarProto;
import com.ucar.vehiclesdk.UCarCommon;

public class AudioConfig implements Parcelable {
    public static final Parcelable.Creator<AudioConfig> CREATOR = new AudioConfigCreator();
    private int mChannel;
    private int mFormat;
    private int mSampleRate;
    private int mSource;

    public static class AudioConfigCreator implements Parcelable.Creator<AudioConfig> {
        @Override
        public AudioConfig createFromParcel(Parcel parcel) {
            return new AudioConfig(parcel);
        }

        @Override
        public AudioConfig[] newArray(int i) {
            return new AudioConfig[i];
        }
    }

    public AudioConfig(int i, int i2, int i3, int i4) {
        this.mSampleRate = UCarProto.SampleRate.SAMPLE_RATE_8000_VALUE;
        this.mFormat = 2;
        this.mSource = 3;
        this.mChannel = 3;
        this.mSampleRate = i;
        this.mFormat = i2;
        this.mSource = i3;
        this.mChannel = i4;
    }

    public AudioConfig(Parcel parcel) {
        this.mSampleRate = UCarProto.SampleRate.SAMPLE_RATE_8000_VALUE;
        this.mFormat = 2;
        this.mSource = 3;
        this.mChannel = 3;
        this.mSampleRate = parcel.readInt();
        this.mFormat = parcel.readInt();
        this.mSource = parcel.readInt();
        this.mChannel = parcel.readInt();
    }

    public static AudioConfig getCarConfig(UCarCommon.AudioFormat audioFormat) {
        return new AudioConfig(audioFormat.getSampleRate(), audioFormat.getEncodingFormat(), 1, audioFormat.getChannelConfig());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getChannel() {
        return this.mChannel;
    }

    public int getFormat() {
        return this.mFormat;
    }

    public int getSampleRate() {
        return this.mSampleRate;
    }

    public int getSource() {
        return this.mSource;
    }

    public void setChannel(int i) {
        this.mChannel = i;
    }

    public void setFormat(int i) {
        this.mFormat = i;
    }

    public void setSampleRate(int i) {
        this.mSampleRate = i;
    }

    public void setSource(int i) {
        this.mSource = i;
    }

    public String toString() {
        return "AudioConfig{sample rate=" + this.mSampleRate + ", format=" + this.mFormat + ", source=" + this.mSource + ", channel=" + this.mChannel + '}';
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mSampleRate);
        parcel.writeInt(this.mFormat);
        parcel.writeInt(this.mSource);
        parcel.writeInt(this.mChannel);
    }
}
