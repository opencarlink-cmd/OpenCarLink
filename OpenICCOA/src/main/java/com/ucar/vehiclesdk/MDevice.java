package com.ucar.vehiclesdk;

import android.os.Parcel;
import android.os.Parcelable;

public class MDevice implements Parcelable {
    public static final int CONNECT_TYPE_DEFAULT = 1;
    public static final int CONNECT_TYPE_SOFTAP = 3;
    public static final int CONNECT_TYPE_USB = 2;
    public static final int CONNECT_TYPE_WIFIP2P = 1;
    public static final Parcelable.Creator<MDevice> CREATOR = new MDeviceCreator();
    private static final int DEFAULT_PORT = 7236;
    private String mAddress;
    private int mConnectType;
    private String mId;
    private String mModel;
    private String mName;
    private int mPort;

    public static class MDeviceCreator implements Parcelable.Creator<MDevice> {
        @Override
        public MDevice createFromParcel(Parcel parcel) {
            return new MDevice(parcel);
        }

        @Override
        public MDevice[] newArray(int i) {
            return new MDevice[i];
        }
    }

    public MDevice() {
        this.mId = "-1";
        this.mModel = "GENERIC PHONE";
        this.mName = "UNKNOWN";
        this.mAddress = "";
        this.mPort = DEFAULT_PORT;
        this.mConnectType = 1;
    }

    public MDevice(Parcel parcel) {
        this.mId = "-1";
        this.mModel = "GENERIC PHONE";
        this.mName = "UNKNOWN";
        this.mAddress = "";
        this.mPort = DEFAULT_PORT;
        this.mConnectType = 1;
        this.mId = parcel.readString();
        this.mModel = parcel.readString();
        this.mName = parcel.readString();
        this.mAddress = parcel.readString();
        this.mPort = parcel.readInt();
        this.mConnectType = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public int getConnectType() {
        return this.mConnectType;
    }

    public String getId() {
        return this.mId;
    }

    public String getModel() {
        return this.mModel;
    }

    public String getName() {
        return this.mName;
    }

    public int getPort() {
        return this.mPort;
    }

    public boolean isWired() {
        return 2 == this.mConnectType;
    }

    public boolean isWireless() {
        int i = this.mConnectType;
        return 1 == i || 3 == i;
    }

    public void setAddress(String str) {
        this.mAddress = str;
    }

    public void setConnectType(int i) {
        this.mConnectType = i;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public void setModel(String str) {
        this.mModel = str;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setPort(int i) {
        this.mPort = i;
    }

    public String toString() {
        return "CarDevice{mId='" + this.mId + "', mModel='" + this.mModel + "', mName='" + this.mName + "', mAddress='" + this.mAddress + "', mPort='" + this.mPort + "', mConnectType=" + this.mConnectType + '}';
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mId);
        parcel.writeString(this.mModel);
        parcel.writeString(this.mName);
        parcel.writeString(this.mAddress);
        parcel.writeInt(this.mPort);
        parcel.writeInt(this.mConnectType);
    }
}
