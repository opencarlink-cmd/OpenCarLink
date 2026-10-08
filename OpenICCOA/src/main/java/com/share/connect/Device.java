package com.share.connect;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.ucar.vehiclesdk.connect.UCarConnectProxy;

public class Device implements Parcelable {
    public static final Parcelable.Creator<Device> CREATOR = new DeviceCreator();
    public static final Device UNKNOWN_CAR = new Device().setVin("101010101010").setVid(UCarConnectProxy.f9576v).setVName("未知车企").setPName("未知车型").setPid(UCarConnectProxy.f9576v).setShortName("未知车型");
    private String mPName;
    private String mPid;
    private String mShortName;
    private String mVName;
    private String mVid;
    private String mVin;

    public static class DeviceCreator implements Parcelable.Creator<Device> {
        @Override
        public Device createFromParcel(Parcel parcel) {
            return new Device(parcel);
        }

        @Override
        public Device[] newArray(int i) {
            return new Device[i];
        }
    }

    public Device() {
    }

    public Device(Parcel parcel) {
        this.mVin = parcel.readString();
        this.mPName = parcel.readString();
        this.mVName = parcel.readString();
        this.mVid = parcel.readString();
        this.mPid = parcel.readString();
        this.mShortName = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }
        if (obj instanceof Device) {
            return TextUtils.equals(this.mVin, ((Device) obj).mVin);
        }
        return false;
    }

    public String getCertServerId() {
        return this.mVid + this.mPid;
    }

    public String getPName() {
        return this.mPName;
    }

    public String getPid() {
        return this.mPid;
    }

    public String getShortName() {
        return this.mShortName;
    }

    public String getVName() {
        return this.mVName;
    }

    public String getVid() {
        return this.mVid;
    }

    public String getVin() {
        return this.mVin;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Device setPName(String str) {
        this.mPName = str;
        return this;
    }

    public Device setPid(String str) {
        this.mPid = str;
        return this;
    }

    public Device setShortName(String str) {
        this.mShortName = str;
        return this;
    }

    public Device setVName(String str) {
        this.mVName = str;
        return this;
    }

    public Device setVid(String str) {
        this.mVid = str;
        return this;
    }

    public Device setVin(String str) {
        this.mVin = str;
        return this;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mVin);
        parcel.writeString(this.mPName);
        parcel.writeString(this.mVName);
        parcel.writeString(this.mVid);
        parcel.writeString(this.mPid);
        parcel.writeString(this.mShortName);
    }
}
