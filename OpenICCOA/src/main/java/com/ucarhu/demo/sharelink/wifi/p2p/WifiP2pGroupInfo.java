package com.ucarhu.demo.sharelink.wifi.p2p;

public class WifiP2pGroupInfo {

    private java.lang.String networkName;

    private java.lang.String passphrase;

    private int frequency;

    private boolean groupOwner;

    private java.lang.String groupOwnerMac;

    public int getFrequency() {
        return this.frequency;
    }

    public com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pGroupInfo setFrequency(int frequency) {
        this.frequency = frequency;
        return this;
    }

    public com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pGroupInfo setGroupOwnerMac(java.lang.String groupOwnerMac) {
        this.groupOwnerMac = groupOwnerMac;
        return this;
    }

    public com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pGroupInfo setGroupOwner(boolean groupOwner) {
        this.groupOwner = groupOwner;
        return this;
    }

    public com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pGroupInfo setNetworkName(java.lang.String networkName) {
        this.networkName = networkName;
        return this;
    }

    public java.lang.String getNetworkName() {
        return this.networkName;
    }

    public com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pGroupInfo setPassphrase(java.lang.String passphrase) {
        this.passphrase = passphrase;
        return this;
    }

    public java.lang.String getGroupOwnerMac() {
        return this.groupOwnerMac;
    }

    public java.lang.String getPassphrase() {
        return this.passphrase;
    }

    public boolean isGroupOwner() {
        return this.groupOwner;
    }

    public java.lang.String toString() {
        return "GroupInfo{networkName='" + this.networkName + "', passphrase='" + this.passphrase + "', frequency=" + this.frequency + ", groupOwner=" + this.groupOwner + ", groupOwnerMac='" + this.groupOwnerMac + "'}";
    }
}
