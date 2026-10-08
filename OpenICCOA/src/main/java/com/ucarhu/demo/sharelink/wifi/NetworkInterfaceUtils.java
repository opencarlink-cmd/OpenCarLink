package com.ucarhu.demo.sharelink.wifi;

public class NetworkInterfaceUtils {

    private static final java.lang.String f227a = "NetworkInterfaceUtils";

    private static java.lang.String m173a(java.net.NetworkInterface networkInterface) {
        java.lang.String displayName = networkInterface.getDisplayName();
        return displayName == null ? "" : displayName;
    }

    public static java.lang.String getSelfHostAddress(java.util.List<java.lang.String> list) throws java.net.SocketException {
        java.net.NetworkInterface networkInterfaceM176d = m176d(list);
        if (networkInterfaceM176d != null) {
            return getHostAddressByInterface(networkInterfaceM176d);
        }
        com.ucarhu.demo.logging.EasyLogger.warn(f227a, "getSelfHostAddress: mostFitInterface is null");
        return null;
    }

    public static java.lang.String getHostAddressByInterface(java.net.NetworkInterface networkInterface) {
        java.util.Enumeration<java.net.InetAddress> inetAddresses = networkInterface.getInetAddresses();
        while (inetAddresses != null && inetAddresses.hasMoreElements()) {
            java.net.InetAddress inetAddressNextElement = inetAddresses.nextElement();
            if (!inetAddressNextElement.isLoopbackAddress() && (inetAddressNextElement instanceof java.net.Inet4Address)) {
                java.lang.String hostAddress = inetAddressNextElement.getHostAddress();
                com.ucarhu.demo.logging.EasyLogger.debug(f227a, "getHostAddressByInterface: networkInterface=" + networkInterface.getDisplayName() + ", hostAddress=" + hostAddress);
                return hostAddress;
            }
            com.ucarhu.demo.logging.EasyLogger.warn(f227a, "networkInterface=" + networkInterface.getDisplayName() + ",s InetAddress=" + inetAddressNextElement + " not matches.");
        }
        return null;
    }

    @androidx.annotation.Nullable
    private static java.net.NetworkInterface m176d(java.util.List<java.lang.String> list) throws java.net.SocketException {
        com.ucarhu.demo.logging.EasyLogger.debug(f227a, "getNetworkInterfaceByRegex: regex=" + list);
        java.util.ArrayList arrayList = new java.util.ArrayList();
        java.util.Iterator<java.lang.String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(java.util.regex.Pattern.compile(it.next()));
        }
        try {
            java.util.Enumeration<java.net.NetworkInterface> networkInterfaces = java.net.NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                return null;
            }
            java.util.ArrayList<java.net.NetworkInterface> list2 = java.util.Collections.list(networkInterfaces);
            java.util.Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                java.util.regex.Pattern pattern = (java.util.regex.Pattern) it2.next();
                for (java.net.NetworkInterface networkInterface : list2) {
                    if (pattern.matcher(m173a(networkInterface)).matches()) {
                        return networkInterface;
                    }
                }
            }
            return null;
        } catch (java.net.SocketException e2) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f227a, "Get NetworkInterfaces failed", e2);
            return null;
        }
    }
}
