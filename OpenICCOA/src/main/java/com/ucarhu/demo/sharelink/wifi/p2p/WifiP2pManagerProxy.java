package com.ucarhu.demo.sharelink.wifi.p2p;

@android.annotation.SuppressLint({"PrivateApi", "DiscouragedPrivateApi"})
public class WifiP2pManagerProxy {

    private static final java.lang.String f248a = "WifiP2pManagerProxy";

    public static class a implements java.lang.reflect.InvocationHandler {

        public final java.lang.reflect.Method f249a;

        public final com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pManagerProxy.c f250b;

        public a(java.lang.reflect.Method method, com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pManagerProxy.c cVar) {
            this.f249a = method;
            this.f250b = cVar;
        }

        @Override
        public java.lang.Object invoke(java.lang.Object obj, java.lang.reflect.Method method, java.lang.Object[] objArr) throws java.lang.Throwable {
            if (!android.text.TextUtils.equals("onPersistentGroupInfoAvailable", method.getName())) {
                return null;
            }
            java.util.Collection<android.net.wifi.p2p.WifiP2pGroup> collection = (java.util.Collection) this.f249a.invoke(objArr[0], new java.lang.Object[0]);
            com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pManagerProxy.c cVar = this.f250b;
            if (cVar == null) {
                return null;
            }
            cVar.mo209a(collection);
            return null;
        }
    }

    public static class b implements com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pManagerProxy.c {

        public final java.lang.reflect.Method f251a;

        public final android.net.wifi.p2p.WifiP2pManager f252b;

        public final android.net.wifi.p2p.WifiP2pManager.Channel f253c;

        public b(java.lang.reflect.Method method, android.net.wifi.p2p.WifiP2pManager wifiP2pManager, android.net.wifi.p2p.WifiP2pManager.Channel channel) {
            this.f251a = method;
            this.f252b = wifiP2pManager;
            this.f253c = channel;
        }

        @Override
        public void mo209a(java.util.Collection<android.net.wifi.p2p.WifiP2pGroup> collection){
            java.util.Iterator<android.net.wifi.p2p.WifiP2pGroup> it = collection.iterator();
            while (it.hasNext()) {
                int iIntValue = 0;
                try {
                    iIntValue = ((java.lang.Integer) this.f251a.invoke(it.next(), new java.lang.Object[0])).intValue();
                } catch (java.lang.IllegalAccessException | java.lang.reflect.InvocationTargetException e2) {
                    com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pManagerProxy.f248a, "getNetworkId exception ", e2);
                }
                com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pManagerProxy.deletePersistentGroup(this.f252b, this.f253c, iIntValue, new com.ucarhu.demo.sharelink.wifi.LoggingActionListener(com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pManagerProxy.f248a, "Delete " + iIntValue));
            }
        }
    }

    public interface c {
        void mo209a(java.util.Collection<android.net.wifi.p2p.WifiP2pGroup> collection);
    }

    public static void deleteAllPersistentGroups(android.net.wifi.p2p.WifiP2pManager wifiP2pManager, android.net.wifi.p2p.WifiP2pManager.Channel channel) {
        com.ucarhu.demo.logging.EasyLogger.info(f248a, "Delete all persistent groups.");
        try {
            requestPersistentGroupInfo(wifiP2pManager, channel, new com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pManagerProxy.b(android.net.wifi.p2p.WifiP2pGroup.class.getDeclaredMethod("getNetworkId", new java.lang.Class[0]), wifiP2pManager, channel));
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.warnWithThrowable(f248a, "Delete all persistent group failed.", e2);
        }
    }

    public static void deletePersistentGroup(android.net.wifi.p2p.WifiP2pManager wifiP2pManager, android.net.wifi.p2p.WifiP2pManager.Channel channel, int i, android.net.wifi.p2p.WifiP2pManager.ActionListener actionListener) {
        com.ucarhu.demo.logging.EasyLogger.info(f248a, "Delete persistent group.");
        try {
            android.net.wifi.p2p.WifiP2pManager.class.getDeclaredMethod("deletePersistentGroup", android.net.wifi.p2p.WifiP2pManager.Channel.class, java.lang.Integer.TYPE, android.net.wifi.p2p.WifiP2pManager.ActionListener.class).invoke(wifiP2pManager, channel, java.lang.Integer.valueOf(i), actionListener);
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.infoWithThrowable(f248a, "Delete persistent group failed.", e2);
        }
    }

    public static boolean requestPersistentGroupInfo(android.net.wifi.p2p.WifiP2pManager wifiP2pManager, android.net.wifi.p2p.WifiP2pManager.Channel channel, com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pManagerProxy.c cVar) {
        try {
            com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pManagerProxy.a aVar = new com.ucarhu.demo.sharelink.wifi.p2p.WifiP2pManagerProxy.a(java.lang.Class.forName("android.net.wifi.p2p.WifiP2pGroupList").getDeclaredMethod("getGroupList", new java.lang.Class[0]), cVar);
            java.lang.Class<?> cls = java.lang.Class.forName("android.net.wifi.p2p.WifiP2pManager$PersistentGroupInfoListener");
            java.lang.reflect.Method declaredMethod = android.net.wifi.p2p.WifiP2pManager.class.getDeclaredMethod("requestPersistentGroupInfo", android.net.wifi.p2p.WifiP2pManager.Channel.class, cls);
            java.lang.ClassLoader classLoader = cls.getClassLoader();
            if (classLoader != null) {
                declaredMethod.invoke(wifiP2pManager, channel, java.lang.reflect.Proxy.newProxyInstance(classLoader, new java.lang.Class[]{cls}, aVar));
            }
            return true;
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.warnWithThrowable(f248a, "Request persistent group info failed.", e2);
            return false;
        }
    }

    public static boolean setWifiP2pChannel(android.net.wifi.p2p.WifiP2pManager wifiP2pManager, android.net.wifi.p2p.WifiP2pManager.Channel channel, int i, android.net.wifi.p2p.WifiP2pManager.ActionListener actionListener) {
        com.ucarhu.demo.logging.EasyLogger.info(f248a, "Try to set channel to " + i);
        try {
            java.lang.Class cls = java.lang.Integer.TYPE;
            android.net.wifi.p2p.WifiP2pManager.class.getDeclaredMethod("setWifiP2pChannels", android.net.wifi.p2p.WifiP2pManager.Channel.class, cls, cls, android.net.wifi.p2p.WifiP2pManager.ActionListener.class).invoke(wifiP2pManager, channel, 0, java.lang.Integer.valueOf(i), actionListener);
            return true;
        } catch (java.lang.Exception e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f248a, "setWifiP2pChannels invoke failed", e2);
            return false;
        }
    }
}
