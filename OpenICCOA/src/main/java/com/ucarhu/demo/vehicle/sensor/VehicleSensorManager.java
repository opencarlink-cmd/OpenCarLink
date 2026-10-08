package com.ucarhu.demo.vehicle.sensor;

/**
 * 车辆传感器管理器，负责把 GPS、灯光、陀螺仪、加速度、档位、油量等车况数据发往手机端。
 * 每类传感器数据都通过对应协议消息构造后发送到 SENSOR 通道。
 */


public class VehicleSensorManager {

    private static final java.lang.String f806c = "SensorManager";

    private static final int f807d = 90;

    private static final int f808e = -90;

    private static final int f809f = 180;

    private static final int f810g = -180;

    private static final int f811h = 0;

    private android.content.Context f812a;

    private com.ucarhu.demo.sharelink.channel.ShareLinkChannel f813b = new com.ucarhu.demo.sharelink.channel.ShareLinkChannel(com.ucarhu.demo.protocol.channel.ChannelType.SENSOR, false, true);

    public class a implements com.ucarhu.demo.protocol.channel.SendCallback {
        public a() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.f806c, "sendGpsData failed: ", exc);
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.f806c, "sendGpsDatao succeeded");
        }
    }

    public class b implements com.ucarhu.demo.protocol.channel.SendCallback {
        public b() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.f806c, "sendLightsData failed.", exc);
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.f806c, "sendLightsData succeeded");
        }
    }

    public class c implements com.ucarhu.demo.protocol.channel.SendCallback {
        public c() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.f806c, "sendGyroScopeData failed", exc);
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.f806c, "sendGyroScopeData succeeded");
        }
    }

    public class d implements com.ucarhu.demo.protocol.channel.SendCallback {
        public d() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.f806c, "sendAccelerationData failed.", exc);
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.f806c, "sendAccelerationData succeeded");
        }
    }

    public class e implements com.ucarhu.demo.protocol.channel.SendCallback {
        public e() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.f806c, "sendOilData failed.", exc);
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.f806c, "sendOilData succeeded");
        }
    }

    public class f implements com.ucarhu.demo.protocol.channel.SendCallback {
        public f() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.f806c, "sendGearData failed.", exc);
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.f806c, "sendGearData succeeded");
        }
    }

    public class g implements com.ucarhu.demo.protocol.channel.SendCallback {
        public g() {
        }

        @Override
        public void onFailure(java.lang.Exception exc) {
            com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.f806c, "sendLightSensorInfoData failed.", exc);
        }

        @Override
        public void onSuccess(java.lang.Boolean bool) {
            com.ucarhu.demo.logging.EasyLogger.info(com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.f806c, "sendLightSensorInfoData succeeded");
        }
    }

    public VehicleSensorManager(android.content.Context context) {
        this.f812a = context;
    }

    private boolean m955b() {
        com.ucarhu.demo.sharelink.channel.ShareLinkChannel c0016a = this.f813b;
        return c0016a != null && c0016a.mo353b();
    }

    public void startSensorChannel(java.lang.String str) {
        com.ucarhu.demo.logging.EasyLogger.info(f806c, "SensorChannel start address:" + str);
        com.ucarhu.demo.sharelink.channel.ShareLinkChannel c0016a = this.f813b;
        if (c0016a == null) {
            com.ucarhu.demo.logging.EasyLogger.error(f806c, "sensor channel is null");
            return;
        }
        if (c0016a.mo353b()) {
            com.ucarhu.demo.logging.EasyLogger.info(f806c, "sensor channel server has opened");
            return;
        }
        try {
            this.f813b.m417a0(0, str);
        } catch (java.io.IOException e2) {
            com.ucarhu.demo.logging.EasyLogger.infoWithThrowable(f806c, "Start sensor channel error.", e2);
        }
    }

    public boolean sendAccelerationInfo(com.ucar.vehiclesdk.UCarCommon.AccelerationInfo accelerationInfo) {
        java.lang.String str;
        if (accelerationInfo == null) {
            str = " sendAccelerationData() error";
        } else {
            if (m955b()) {
                this.f813b.mo355c(com.ucarhu.demo.protocol.SensorMessages.m327j(com.ucar.databus.proto.UCarProto.Acceleration.newBuilder().setAccX(accelerationInfo.getAccX()).setAccY(accelerationInfo.getAccY()).setAccZ(accelerationInfo.getAccZ()).setTimestamp(accelerationInfo.getTimeStamp()).build()), new com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.d());
                return true;
            }
            str = " sendAccelerationData() channel not ready, please check!";
        }
        com.ucarhu.demo.logging.EasyLogger.error(f806c, str);
        return false;
    }

    public boolean sendGpsInfo(com.ucar.vehiclesdk.UCarCommon.GPSInfo gPSInfo) {
        java.lang.String str;
        if (gPSInfo == null || gPSInfo.getLatitude() < -90.0d || gPSInfo.getLatitude() > 90.0d || gPSInfo.getLongitude() < -180.0d || gPSInfo.getLongitude() > 180.0d || gPSInfo.getSpeed() < 0) {
            str = " sendGpsData() args error";
        } else {
            if (m955b()) {
                this.f813b.mo355c(com.ucarhu.demo.protocol.SensorMessages.m329l(com.ucar.databus.proto.UCarProto.Gps.newBuilder().setAltitude(gPSInfo.getAltitude()).setLatitude(gPSInfo.getLatitude()).setLongitude(gPSInfo.getLongitude()).setAntennaState(gPSInfo.getAntennaState()).setPdop(gPSInfo.getpDop()).setSpeed(gPSInfo.getSpeed()).setHeading(gPSInfo.getHeading()).setSatsUsed(gPSInfo.getSatsUsed()).setSatsVisible(gPSInfo.getSatsVisible()).setTimestamp(gPSInfo.getTimeStamp()).build()), new com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.a());
                return true;
            }
            str = " sendGpsData() channel not ready, please check!";
        }
        com.ucarhu.demo.logging.EasyLogger.error(f806c, str);
        return false;
    }

    public boolean sendGearStateInfo(com.ucar.vehiclesdk.UCarCommon.GearStateInfo gearStateInfo) {
        java.lang.String str;
        if (gearStateInfo == null || gearStateInfo.getGearState() == null) {
            str = "sendGearData() args error";
        } else {
            if (m955b()) {
                this.f813b.mo355c(com.ucarhu.demo.protocol.SensorMessages.m328k(com.ucar.databus.proto.UCarProto.GearInfo.newBuilder().setSpeed(gearStateInfo.getCurrentSpeed()).setGear(com.ucar.databus.proto.UCarProto.GearInfo.GearState.forNumber(gearStateInfo.getGearState().getValue())).build()), new com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.f());
                return true;
            }
            str = " sendGearData() channel not ready, please check!";
        }
        com.ucarhu.demo.logging.EasyLogger.error(f806c, str);
        return false;
    }

    public boolean sendGyroscopeInfo(com.ucar.vehiclesdk.UCarCommon.GyroscopeInfo gyroscopeInfo) {
        java.lang.String str;
        if (gyroscopeInfo == null || gyroscopeInfo.getLegyroX() < -180.0d || gyroscopeInfo.getLegyroX() > 180.0d || gyroscopeInfo.getLegyroY() < -90.0d || gyroscopeInfo.getLegyroY() > 90.0d || gyroscopeInfo.getLegyroZ() < 0.0d || gyroscopeInfo.getLegyroZ() > 360.0d) {
            str = "sendGyroScopeData args error";
        } else {
            if (m955b()) {
                this.f813b.mo355c(com.ucarhu.demo.protocol.SensorMessages.m330m(com.ucar.databus.proto.UCarProto.GyroScope.newBuilder().setGyroType(gyroscopeInfo.getGyroType()).setLegyroX(gyroscopeInfo.getLegyroX()).setLegyroY(gyroscopeInfo.getLegyroY()).setLegyroZ(gyroscopeInfo.getLegyroZ()).setTimestamp(gyroscopeInfo.getTimeStamp()).build()), new com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.c());
                return true;
            }
            str = " sendGyroScopeData() channel not ready, please check!";
        }
        com.ucarhu.demo.logging.EasyLogger.error(f806c, str);
        return false;
    }

    public boolean sendLightSensorInfo(com.ucar.vehiclesdk.UCarCommon.LightSensorInfo lightSensorInfo) {
        java.lang.String str;
        if (lightSensorInfo == null || lightSensorInfo.getMinLux() < 0.0d || lightSensorInfo.getMaxLux() < 0.0d || lightSensorInfo.getCurrentLux() < 0.0d || lightSensorInfo.getCurrentLux() < lightSensorInfo.getMinLux() || lightSensorInfo.getCurrentLux() > lightSensorInfo.getMaxLux()) {
            str = "sendLightSensorInfoData() args error";
        } else {
            if (m955b()) {
                this.f813b.mo355c(com.ucarhu.demo.protocol.SensorMessages.m331n(com.ucar.databus.proto.UCarProto.LightSensorInfo.newBuilder().setMaxLux(lightSensorInfo.getMaxLux()).setMinLux(lightSensorInfo.getMinLux()).setCurrentLux(lightSensorInfo.getCurrentLux()).build()), new com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.g());
                return true;
            }
            str = " sendLightSensorInfoData() channel not ready, please check!";
        }
        com.ucarhu.demo.logging.EasyLogger.error(f806c, str);
        return false;
    }

    public boolean sendLightsInfo(com.ucar.vehiclesdk.UCarCommon.LightsInfo lightsInfo) {
        java.lang.String str;
        if (lightsInfo == null) {
            str = " sendLightsData() args error";
        } else {
            if (m955b()) {
                this.f813b.mo355c(com.ucarhu.demo.protocol.SensorMessages.m332o(com.ucar.databus.proto.UCarProto.Lights.newBuilder().setBackupLampOn(lightsInfo.isBackupLampOn()).setClearanceLampOn(lightsInfo.isClearanceLampOn()).setHighBeamOn(lightsInfo.isHighBeamOn()).setLowBeamOn(lightsInfo.isLowBeamOn()).setStopLampOn(lightsInfo.isStopLampOn()).build()), new com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.b());
                return true;
            }
            str = " sendLightsData() channel not ready, please check!";
        }
        com.ucarhu.demo.logging.EasyLogger.error(f806c, str);
        return false;
    }

    public boolean sendOilInfo(com.ucar.vehiclesdk.UCarCommon.OilInfo oilInfo) {
        if (oilInfo == null || oilInfo.getCurrentFuel() < 0 || oilInfo.getMaxFuel() < 0 || oilInfo.getMinFuel() < 0 || oilInfo.getCurrentFuel() > oilInfo.getMaxFuel() || oilInfo.getCurrentFuel() < oilInfo.getMinFuel()) {
            com.ucarhu.demo.logging.EasyLogger.error(f806c, "sendOilData() args error");
            return false;
        }
        if (!m955b()) {
            com.ucarhu.demo.logging.EasyLogger.error(f806c, " sendOilData() channel not ready, please check!");
            return true;
        }
        this.f813b.mo355c(com.ucarhu.demo.protocol.SensorMessages.m333p(com.ucar.databus.proto.UCarProto.Oil.newBuilder().setMaxFuel(oilInfo.getMaxFuel()).setCurrentFuel(oilInfo.getCurrentFuel()).build()), new com.ucarhu.demo.vehicle.sensor.VehicleSensorManager.e());
        return true;
    }

    public void closeSensorChannel() throws java.io.IOException {
        if (this.f813b != null) {
            com.ucarhu.demo.logging.EasyLogger.info(f806c, "close sensor channel");
            this.f813b.mo359q0();
        }
    }
}
