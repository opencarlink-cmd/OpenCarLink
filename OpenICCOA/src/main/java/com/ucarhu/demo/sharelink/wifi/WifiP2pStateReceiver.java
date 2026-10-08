package com.ucarhu.demo.sharelink.wifi;

public class WifiP2pStateReceiver extends android.content.BroadcastReceiver {

    private static final java.lang.String TAG = "WifiP2pStateReceiver";

    private boolean registered = false;

    private com.ucarhu.demo.sharelink.wifi.WifiP2pStateReceiver.b peersChangedListener;

    private com.ucarhu.demo.sharelink.wifi.WifiP2pStateReceiver.a connectionStateListener;

    private com.ucarhu.demo.sharelink.wifi.WifiP2pStateReceiver.c thisDeviceChangedListener;

    public interface a {
        void onConnectionStateChanged(android.net.wifi.p2p.WifiP2pInfo wifiP2pInfo, android.net.wifi.p2p.WifiP2pGroup wifiP2pGroup, android.net.NetworkInfo networkInfo);
    }

    public interface b {
        void onPeersChanged(android.net.wifi.p2p.WifiP2pDeviceList wifiP2pDeviceList);
    }

    public interface c {
        void onThisDeviceChanged(android.net.wifi.p2p.WifiP2pDevice wifiP2pDevice);
    }

    private android.content.IntentFilter m177a() {
        android.content.IntentFilter intentFilter = new android.content.IntentFilter();
        intentFilter.addAction("android.net.wifi.p2p.STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.p2p.PEERS_CHANGED");
        intentFilter.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.p2p.THIS_DEVICE_CHANGED");
        return intentFilter;
    }

    private java.lang.String m178b(java.util.Collection<android.net.wifi.p2p.WifiP2pDevice> collection) {
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        if (collection != null && !collection.isEmpty()) {
            for (android.net.wifi.p2p.WifiP2pDevice wifiP2pDevice : collection) {
                sb.append(wifiP2pDevice.deviceName);
                sb.append(", ");
                sb.append(wifiP2pDevice.deviceAddress);
                sb.append(", ");
                sb.append(wifiP2pDevice.status);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public void setConnectionStateListener(com.ucarhu.demo.sharelink.wifi.WifiP2pStateReceiver.a aVar) {
        this.connectionStateListener = aVar;
    }

    public void setPeersChangedListener(com.ucarhu.demo.sharelink.wifi.WifiP2pStateReceiver.b bVar) {
        this.peersChangedListener = bVar;
    }

    public void setThisDeviceChangedListener(com.ucarhu.demo.sharelink.wifi.WifiP2pStateReceiver.c cVar) {
        this.thisDeviceChangedListener = cVar;
    }

    public synchronized void setRegistered(android.content.Context context, boolean shouldRegister) {
        android.content.Context applicationContext = context.getApplicationContext();
        if (shouldRegister) {
            if (this.registered) {
                return;
            }
            applicationContext.registerReceiver(this, m177a());
            this.registered = true;
        } else {
            if (!this.registered) {
                return;
            }
            try {
                applicationContext.unregisterReceiver(this);
                this.registered = false;
            } catch (java.lang.IllegalArgumentException e3) {
                this.registered = false;
                com.ucarhu.demo.logging.EasyLogger.warn(TAG, "Receiver was already unregistered: " + e3.getMessage());
            }
        }
    }

    @Override
    public void onReceive(android.content.Context context, android.content.Intent intent) {
        java.lang.String str;
        java.lang.String action = intent.getAction();
        com.ucarhu.demo.logging.EasyLogger.debug(TAG, "WifiP2pStateReceiver: action:" + action + ", isInitialStickyBroadcast=" + isInitialStickyBroadcast());
        if ("android.net.wifi.p2p.STATE_CHANGED".equals(action)) {
            str = "onReceive: STATE_CHANGED " + intent.getIntExtra("wifi_p2p_state", -1);
        } else {
            if (!"android.net.wifi.p2p.PEERS_CHANGED".equals(action)) {
                if (!"android.net.wifi.p2p.CONNECTION_STATE_CHANGE".equals(action)) {
                    if ("android.net.wifi.p2p.THIS_DEVICE_CHANGED".equals(action)) {
                        android.net.wifi.p2p.WifiP2pDevice wifiP2pDevice = (android.net.wifi.p2p.WifiP2pDevice) intent.getParcelableExtra("wifiP2pDevice");
                        if (wifiP2pDevice == null) {
                            com.ucarhu.demo.logging.EasyLogger.info(TAG, "wifiP2pDevice is null");
                            return;
                        }
                        com.ucarhu.demo.sharelink.wifi.WifiP2pStateReceiver.c cVar = this.thisDeviceChangedListener;
                        if (cVar != null) {
                            cVar.onThisDeviceChanged(wifiP2pDevice);
                            return;
                        }
                        return;
                    }
                    return;
                }
                android.net.wifi.p2p.WifiP2pInfo wifiP2pInfo = (android.net.wifi.p2p.WifiP2pInfo) intent.getParcelableExtra("wifiP2pInfo");
                android.net.wifi.p2p.WifiP2pGroup wifiP2pGroup = (android.net.wifi.p2p.WifiP2pGroup) intent.getParcelableExtra("p2pGroupInfo");
                android.net.NetworkInfo networkInfo = (android.net.NetworkInfo) intent.getParcelableExtra("networkInfo");
                java.lang.StringBuilder sb = new java.lang.StringBuilder();
                sb.append("Null check: p2pInfo: ");
                sb.append(wifiP2pInfo != null);
                sb.append(", wifiP2pGroup: ");
                sb.append(wifiP2pGroup != null);
                sb.append(", networkInfo: ");
                sb.append(networkInfo != null);
                com.ucarhu.demo.logging.EasyLogger.debug(TAG, sb.toString());
                if (wifiP2pInfo != null) {
                    com.ucarhu.demo.logging.EasyLogger.debug(TAG, "p2pInfo: groupFormed: " + wifiP2pInfo.groupFormed + ", isGroupOwner: " + wifiP2pInfo.isGroupOwner);
                }
                if (wifiP2pGroup != null) {
                    java.lang.StringBuilder sb2 = new java.lang.StringBuilder();
                    sb2.append("networkInfo: ");
                    sb2.append(networkInfo);
                    sb2.append("\nwifiP2pGroup: ");
                    sb2.append(wifiP2pGroup.getNetworkName());
                    sb2.append(", ");
                    sb2.append(wifiP2pGroup.getOwner() != null ? wifiP2pGroup.getOwner().deviceAddress : "null");
                    sb2.append("\nClient: ");
                    sb2.append(m178b(wifiP2pGroup.getClientList()));
                    com.ucarhu.demo.logging.EasyLogger.debug(TAG, sb2.toString());
                }
                com.ucarhu.demo.sharelink.wifi.WifiP2pStateReceiver.a aVar = this.connectionStateListener;
                if (aVar != null) {
                    aVar.onConnectionStateChanged(wifiP2pInfo, wifiP2pGroup, networkInfo);
                    return;
                }
                return;
            }
            android.net.wifi.p2p.WifiP2pDeviceList wifiP2pDeviceList = (android.net.wifi.p2p.WifiP2pDeviceList) intent.getParcelableExtra("wifiP2pDeviceList");
            if (wifiP2pDeviceList != null) {
                java.lang.StringBuilder sb3 = new java.lang.StringBuilder("Peers changed: ");
                for (android.net.wifi.p2p.WifiP2pDevice wifiP2pDevice2 : wifiP2pDeviceList.getDeviceList()) {
                    sb3.append("\n");
                    sb3.append(wifiP2pDevice2.deviceAddress);
                    sb3.append(" ");
                    sb3.append(wifiP2pDevice2.deviceName);
                }
                com.ucarhu.demo.logging.EasyLogger.debug(TAG, sb3.toString());
                com.ucarhu.demo.sharelink.wifi.WifiP2pStateReceiver.b bVar = this.peersChangedListener;
                if (bVar != null) {
                    bVar.onPeersChanged(wifiP2pDeviceList);
                    return;
                }
                return;
            }
            str = "onReceive: WIFI_P2P_PEERS_CHANGED_ACTION deviceList is null ";
        }
        com.ucarhu.demo.logging.EasyLogger.debug(TAG, str);
    }
}
