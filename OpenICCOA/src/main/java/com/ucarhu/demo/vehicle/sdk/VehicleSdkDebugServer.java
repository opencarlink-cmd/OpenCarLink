package com.ucarhu.demo.vehicle.sdk;

public class VehicleSdkDebugServer extends java.lang.Thread {

    private static final int f955i = 54321;

    private final java.lang.String f956b = com.ucarhu.demo.vehicle.sdk.VehicleSdkDebugServer.class.getSimpleName();

    private final android.content.Context f957c;

    private java.net.ServerSocket f958d;

    private android.media.AudioManager f959e;

    private boolean f960f;

    private volatile boolean f961g;

    private boolean f962h;

    public VehicleSdkDebugServer(android.content.Context context) {
        this.f957c = context;
    }

    public static boolean m1069b(java.lang.String str) {
        return !str.equals("");
    }

    public static java.lang.Short m1070c(java.lang.String str) {
        return java.lang.Short.valueOf(java.lang.Short.parseShort(str.toString()));
    }

    private void m1071d() {
        if (this.f962h) {
            return;
        }
        this.f959e.setMicrophoneMute(false);
    }

    private void m1072e() {
        android.media.AudioManager audioManager = (android.media.AudioManager) this.f957c.getSystemService("audio");
        this.f959e = audioManager;
        this.f962h = audioManager.isMicrophoneMute();
        this.f959e.setMicrophoneMute(true);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void m1073f(java.lang.String str) {
        java.util.List list;
        int i;
        java.lang.String str2;
        java.lang.StringBuilder sb;
        java.lang.String str3;
        list = (java.util.List) java.util.Arrays.stream(str.split(" ")).filter(new java.util.function.Predicate() {
            @Override
            public final boolean test(java.lang.Object obj) {
                return com.ucarhu.demo.vehicle.sdk.VehicleSdkDebugServer.m1069b((java.lang.String) obj);
            }
        }).collect(java.util.stream.Collectors.toList());
        i = 0;
        str2 = (java.lang.String) list.get(0);
        str2.hashCode();
        switch (str2) {
            case "sendGyroscopeInfo":
                com.ucar.vehiclesdk.UCarAdapter.getInstance().sendGyroscopeInfo(new com.ucar.vehiclesdk.UCarCommon.GyroscopeInfo(java.lang.Integer.parseInt((java.lang.String) list.get(1)), java.lang.Double.parseDouble((java.lang.String) list.get(2)), java.lang.Double.parseDouble((java.lang.String) list.get(3)), java.lang.Double.parseDouble((java.lang.String) list.get(4)), java.lang.System.currentTimeMillis()));
                i = 1;
                break;
            case "sendOilInfo":
                com.ucar.vehiclesdk.UCarAdapter.getInstance().sendOilInfo(new com.ucar.vehiclesdk.UCarCommon.OilInfo(java.lang.Integer.parseInt((java.lang.String) list.get(1)), 0, java.lang.Integer.parseInt((java.lang.String) list.get(2))));
                i = 1;
                break;
            case "sendGPSInfo":
                com.ucar.vehiclesdk.UCarAdapter.getInstance().sendGPSInfo(new com.ucar.vehiclesdk.UCarCommon.GPSInfo(java.lang.Double.parseDouble((java.lang.String) list.get(1)), java.lang.Double.parseDouble((java.lang.String) list.get(2)), java.lang.Double.parseDouble((java.lang.String) list.get(3)), java.lang.Integer.parseInt((java.lang.String) list.get(4)), java.lang.Integer.parseInt((java.lang.String) list.get(5)), java.lang.Integer.parseInt((java.lang.String) list.get(6)), java.lang.Integer.parseInt((java.lang.String) list.get(7)), java.lang.Integer.parseInt((java.lang.String) list.get(8)), java.lang.Integer.parseInt((java.lang.String) list.get(9)), java.lang.System.currentTimeMillis()));
                i = 1;
                break;
            case "sendBatteryInfo":
                com.ucar.vehiclesdk.UCarAdapter.getInstance().sendBatteryInfo(null);
                i = 1;
                break;
            case "deletePhoneById":
                com.ucar.vehiclesdk.UCarAdapter.deletePhoneById(this.f957c, (java.lang.String) list.get(1));
                i = 1;
                break;
            case "sendVRCMD":
                com.ucar.vehiclesdk.UCarAdapter.getInstance().sendVRCMD(com.ucar.vehiclesdk.UCarCommon.VRCmdType.fromInt(java.lang.Integer.parseInt((java.lang.String) list.get(1))), (java.lang.String) list.get(2));
                i = 1;
                break;
            case "notifyIsCallHungUp":
                com.ucar.vehiclesdk.UCarAdapter.getInstance().notifyHungUpCall();
                i = 1;
                break;
            case "sendAccelerationInfo":
                com.ucar.vehiclesdk.UCarAdapter.getInstance().sendAccelerationInfo(new com.ucar.vehiclesdk.UCarCommon.AccelerationInfo(java.lang.Double.parseDouble((java.lang.String) list.get(1)), java.lang.Double.parseDouble((java.lang.String) list.get(2)), java.lang.Double.parseDouble((java.lang.String) list.get(3)), java.lang.System.currentTimeMillis()));
                i = 1;
                break;
            case "disconnect":
                com.ucar.vehiclesdk.UCarAdapter.getInstance().disconnect();
                i = 1;
                break;
            case "sendMicRecordData":
                java.util.List list2 = (java.util.List) list.subList(2, list.size()).stream().map(new java.util.function.Function() {
                    @Override
                    public final java.lang.Object apply(java.lang.Object obj) {
                        return com.ucarhu.demo.vehicle.sdk.VehicleSdkDebugServer.m1070c((java.lang.String) obj);
                    }
                }).collect(java.util.stream.Collectors.toList());
                short[] sArr = new short[list2.size()];
                while (i < list2.size()) {
                    sArr[i] = ((java.lang.Short) list2.get(i)).shortValue();
                    i++;
                }
                m1072e();
                com.ucar.vehiclesdk.UCarAdapter.getInstance().sendMicRecordData(java.lang.Integer.parseInt((java.lang.String) list.get(1)), sArr, java.lang.System.currentTimeMillis());
                m1071d();
                i = 1;
                break;
            case "sendKeyEvent":
                com.ucar.vehiclesdk.UCarAdapter.getInstance().sendKeyEvent(com.ucar.vehiclesdk.UCarCommon.KeyEventActionType.fromInt(java.lang.Integer.parseInt((java.lang.String) list.get(1))), com.ucar.vehiclesdk.UCarCommon.KeyCodeType.fromInt(java.lang.Integer.parseInt((java.lang.String) list.get(2))), 0);
                i = 1;
                break;
            case "notifySwitchDayOrNight":
                com.ucar.vehiclesdk.UCarAdapter.getInstance().notifySwitchDayOrNight(com.ucar.vehiclesdk.UCarCommon.DayNightMode.fromInt(java.lang.Integer.parseInt((java.lang.String) list.get(1))));
                i = 1;
                break;
            case "awakenVoiceAssistant":
                com.ucar.vehiclesdk.UCarAdapter.getInstance().awakenVoiceAssistant(((java.lang.String) list.get(5)).getBytes(java.nio.charset.StandardCharsets.UTF_8), new com.ucar.vehiclesdk.UCarCommon.AudioFormat(java.lang.String.valueOf(list.get(1)), java.lang.Integer.parseInt((java.lang.String) list.get(2)), java.lang.Integer.parseInt((java.lang.String) list.get(3)), java.lang.Integer.parseInt((java.lang.String) list.get(4))), (java.lang.String) list.get(6));
                i = 1;
                break;
            case "sendGotoBackground":
                com.ucar.vehiclesdk.UCarAdapter.getInstance().pauseCast();
                i = 1;
                break;
            case "sendGearStateInfo":
                com.ucar.vehiclesdk.UCarAdapter.getInstance().sendGearStateInfo(new com.ucar.vehiclesdk.UCarCommon.GearStateInfo(com.ucar.vehiclesdk.UCarCommon.GearState.fromInt(java.lang.Integer.parseInt((java.lang.String) list.get(1))), java.lang.Integer.parseInt((java.lang.String) list.get(2))));
                i = 1;
                break;
            case "sendLightsInfo":
                com.ucar.vehiclesdk.UCarAdapter.getInstance().sendLightsInfo(new com.ucar.vehiclesdk.UCarCommon.LightsInfo(java.lang.Boolean.parseBoolean((java.lang.String) list.get(1)), java.lang.Boolean.parseBoolean((java.lang.String) list.get(2)), java.lang.Boolean.parseBoolean((java.lang.String) list.get(3)), java.lang.Boolean.parseBoolean((java.lang.String) list.get(4)), java.lang.Boolean.parseBoolean((java.lang.String) list.get(5))));
                i = 1;
                break;
            case "sendLightSensorInfo":
                com.ucar.vehiclesdk.UCarAdapter.getInstance().sendLightSensorInfo(new com.ucar.vehiclesdk.UCarCommon.LightSensorInfo(java.lang.Double.parseDouble((java.lang.String) list.get(1)), java.lang.Double.parseDouble((java.lang.String) list.get(2)), java.lang.Double.parseDouble((java.lang.String) list.get(3))));
                i = 1;
                break;
            default:
                android.util.Log.d(this.f956b, "parse API failed, please check sender, api:" + str2);
                break;
        }
        java.lang.String str4 = this.f956b;
        if (i != 0) {
            sb = new java.lang.StringBuilder();
            sb.append(str2);
            str3 = " executed";
        } else {
            sb = new java.lang.StringBuilder();
            sb.append(str2);
            str3 = " execute failed";
        }
        sb.append(str3);
        android.util.Log.d(str4, sb.toString());
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void m1074g() throws java.lang.Throwable {
        java.net.Socket socket;
        java.io.InputStream inputStream;
        java.io.BufferedReader bufferedReader;
        java.lang.String str;
        java.lang.Exception e;
        try {
            this.f958d = new java.net.ServerSocket(f955i);
            this.f961g = true;
            while (this.f961g) {
                java.io.InputStreamReader inputStreamReader = null;
                try {
                    android.util.Log.d(this.f956b, "waiting for in socket");
                    java.net.Socket socketAccept = this.f958d.accept();
                    try {
                        java.io.InputStream inputStream2 = socketAccept.getInputStream();
                        try {
                            java.io.InputStreamReader inputStreamReader2 = new java.io.InputStreamReader(inputStream2);
                            try {
                                bufferedReader = new java.io.BufferedReader(inputStreamReader2);
                                java.lang.String str2 = "";
                                while (true) {
                                    try {
                                        java.lang.String line = bufferedReader.readLine();
                                        if (line == null) {
                                            break;
                                        }
                                        android.util.Log.d(this.f956b, "received: " + line);
                                        str2 = line;
                                    } catch (java.lang.Exception e2) {
                                        e = e2;
                                        inputStreamReader = inputStreamReader2;
                                        inputStream = inputStream2;
                                        socket = socketAccept;
                                        try {
                                            android.util.Log.e(this.f956b, e.getMessage(), e);
                                            if (inputStreamReader == null) {
                                                try {
                                                    inputStreamReader.close();
                                                } catch (java.io.IOException e3) {
                                                    e = e3;
                                                    str = this.f956b;
                                                    android.util.Log.e(str, e.getMessage(), e);
                                                }
                                            }
                                            if (inputStream != null) {
                                                inputStream.close();
                                            }
                                            if (socket != null) {
                                                socket.close();
                                            }
                                            if (bufferedReader == null) {
                                                bufferedReader.close();
                                            }
                                        } catch (java.lang.Throwable th) {
                                            th = th;
                                            if (inputStreamReader != null) {
                                                try {
                                                    inputStreamReader.close();
                                                } catch (java.io.IOException e4) {
                                                    android.util.Log.e(this.f956b, e4.getMessage(), e4);
                                                    throw new RuntimeException();
                                                }
                                            }
                                            if (inputStream != null) {
                                                inputStream.close();
                                            }
                                            if (socket != null) {
                                                socket.close();
                                            }
                                            if (bufferedReader != null) {
                                                bufferedReader.close();
                                            }
                                            throw new RuntimeException();
                                        }
                                    } catch (java.lang.Throwable th2) {
                                                            inputStreamReader = inputStreamReader2;
                                        inputStream = inputStream2;
                                        socket = socketAccept;
                                        if (inputStreamReader != null) {
                                        }
                                        if (inputStream != null) {
                                        }
                                        if (socket != null) {
                                        }
                                        if (bufferedReader != null) {
                                        }
                                        throw new RuntimeException();
                                    }
                                }
                                bufferedReader.close();
                                inputStreamReader2.close();
                                inputStream2.close();
                                socketAccept.close();
                                m1073f(str2);
                                try {
                                    inputStreamReader2.close();
                                    inputStream2.close();
                                    socketAccept.close();
                                    bufferedReader.close();
                                } catch (java.io.IOException e5) {
                                    e = e5;
                                    str = this.f956b;
                                    android.util.Log.e(str, e.getMessage(), e);
                                }
                            } catch (java.lang.Exception e6) {
                                bufferedReader = null;
                                inputStreamReader = inputStreamReader2;
                                inputStream = inputStream2;
                                socket = socketAccept;
                                e = e6;
                            } catch (java.lang.Throwable th3) {
                                bufferedReader = null;
                                inputStreamReader = inputStreamReader2;
                                inputStream = inputStream2;
                                socket = socketAccept;
                                java.lang.Throwable th = th3;
                            }
                        } catch (java.lang.Exception e7) {
                            socket = socketAccept;
                            e = e7;
                            inputStream = inputStream2;
                            bufferedReader = null;
                            android.util.Log.e(this.f956b, e.getMessage(), e);
                            if (inputStreamReader == null) {
                            }
                            if (inputStream != null) {
                            }
                            if (socket != null) {
                            }
                            if (bufferedReader == null) {
                            }
                        } catch (java.lang.Throwable th4) {
                            socket = socketAccept;
                            java.lang.Throwable th = th4;
                            inputStream = inputStream2;
                            bufferedReader = null;
                            if (inputStreamReader != null) {
                            }
                            if (inputStream != null) {
                            }
                            if (socket != null) {
                            }
                            if (bufferedReader != null) {
                            }
                            throw new RuntimeException();
                        }
                    } catch (java.lang.Exception e8) {
                        inputStream = null;
                        socket = socketAccept;
                        e = e8;
                    } catch (java.lang.Throwable th5) {
                        inputStream = null;
                        socket = socketAccept;
                        java.lang.Throwable th = th5;
                    }
                } catch (java.lang.Exception e9) {
                    e = e9;
                    socket = null;
                    inputStream = null;
                } catch (java.lang.Throwable th6) {
                    java.lang.Throwable th = th6;
                    socket = null;
                    inputStream = null;
                }
            }
            android.util.Log.d(this.f956b, "service stoped");
        } catch (java.lang.Exception e10) {
            android.util.Log.e(this.f956b, e10.getMessage(), e10);
        }
    }

    public void closeServer() {
        try {
            android.util.Log.d(this.f956b, "close");
            this.f961g = false;
            this.f958d.close();
        } catch (java.io.IOException e2) {
            android.util.Log.d(this.f956b, e2.getMessage(), e2);
        }
    }

    @Override
    public void run() {
        if (this.f960f) {
            android.util.Log.d(this.f956b, "is Running, ignore");
        } else {
            this.f960f = true;
            try {
                m1074g();
            } catch (java.lang.Throwable th) {
                android.util.Log.e(this.f956b, th.getMessage(), th);
            }
        }
    }
}
