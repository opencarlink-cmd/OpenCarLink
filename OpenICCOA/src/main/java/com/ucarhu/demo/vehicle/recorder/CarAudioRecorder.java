package com.ucarhu.demo.vehicle.recorder;

public class CarAudioRecorder implements java.lang.Runnable {

    private static final java.lang.String f930i = "CarAudioRecorder";

    private static final long f931j = 10;

    public static final int f932k = 15;

    private com.ucar.vehiclesdk.recorder.AudioConfig f933b;

    private android.media.AudioRecord f934c;

    private com.ucarhu.demo.vehicle.recorder.CarAudioRecorder.a f935d;

    private android.media.audiofx.AcousticEchoCanceler f936e;

    private boolean f937f;

    private int f938g = 0;

    private volatile boolean f939h = false;

    public interface a {
        void mo770a(short[] sArr, int i);
    }

    public CarAudioRecorder(@androidx.annotation.NonNull com.ucar.vehiclesdk.recorder.AudioConfig audioConfig, boolean z, @androidx.annotation.NonNull com.ucarhu.demo.vehicle.recorder.CarAudioRecorder.a aVar) {
        this.f937f = true;
        this.f935d = aVar;
        this.f933b = audioConfig;
        this.f937f = z;
    }

    private android.media.AudioRecord.Builder m1058a() throws java.lang.IllegalArgumentException {
        android.media.AudioFormat.Builder sampleRate;
        int channel;
        android.media.AudioFormat.Builder builder = new android.media.AudioFormat.Builder();
        if (12 == this.f933b.getChannel()) {
            sampleRate = builder.setSampleRate(this.f937f ? this.f933b.getSampleRate() : this.f933b.getSampleRate() * 2);
            channel = this.f937f ? this.f933b.getChannel() : 16;
            sampleRate.setChannelMask(channel);
            return new android.media.AudioRecord.Builder().setAudioFormat(builder.setEncoding(this.f933b.getFormat()).build()).setAudioSource(this.f933b.getSource()).setBufferSizeInBytes(this.f938g);
        }
        sampleRate = builder.setSampleRate(this.f933b.getSampleRate());
        channel = this.f933b.getChannel();
        sampleRate.setChannelMask(channel);
        return new android.media.AudioRecord.Builder().setAudioFormat(builder.setEncoding(this.f933b.getFormat()).build()).setAudioSource(this.f933b.getSource()).setBufferSizeInBytes(this.f938g);
    }

    private void m1059b(@androidx.annotation.Nullable android.media.AudioRecord.Builder builder, int i) {
        com.ucarhu.demo.logging.EasyLogger.info(f930i, "setSessionId, id = " + i);
        try {
            android.media.AudioRecord.Builder.class.getDeclaredMethod("setSessionId", java.lang.Integer.TYPE).invoke(builder, java.lang.Integer.valueOf(i));
        } catch (java.lang.IllegalAccessException | java.lang.NoSuchMethodException | java.lang.reflect.InvocationTargetException e2) {
            com.ucarhu.demo.logging.EasyLogger.error(f930i, "set session id error, error msg = " + e2.getMessage());
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean m1060d() {
        int sampleRate;
        int channel;
        boolean z;
        if (12 == this.f933b.getChannel()) {
            sampleRate = this.f937f ? this.f933b.getSampleRate() : this.f933b.getSampleRate() * 2;
            channel = this.f937f ? this.f933b.getChannel() : 16;
            this.f938g = android.media.AudioRecord.getMinBufferSize(sampleRate, channel, this.f933b.getFormat());
            z = this.f938g > 0;
            if (!z) {
                com.ucarhu.demo.logging.EasyLogger.error(f930i, "initBuffer error!");
            }
            return z;
        }
        sampleRate = this.f933b.getSampleRate();
        channel = this.f933b.getChannel();
        this.f938g = android.media.AudioRecord.getMinBufferSize(sampleRate, channel, this.f933b.getFormat());
        z = this.f938g > 0;
        if (!z) {
            com.ucarhu.demo.logging.EasyLogger.error(f930i, "initBuffer error!");
        }
        return z;
    }

    private boolean m1061e() {
        boolean z = true;
        if (this.f934c == null) {
            android.media.AudioRecord.Builder builderM1058a = m1058a();
            m1059b(builderM1058a, com.ucarhu.demo.vehicle.media.AudioSessionProvider.nextAudioSessionId());
            try {
                this.f934c = builderM1058a.build();
            } catch (java.lang.UnsupportedOperationException unused) {
                try {
                    this.f934c = m1058a().build();
                } catch (java.lang.UnsupportedOperationException e2) {
                    com.ucarhu.demo.logging.EasyLogger.warnWithThrowable(f930i, "initRecorder failed", e2);
                    z = false;
                }
            }
        } else {
            z = true;
        }
        if (z && this.f934c != null) {
            m1062c(this.f934c.getAudioSessionId());
        }
        return z && this.f934c != null;
    }

    public boolean m1062c(int i) {
        java.lang.String str;
        if (this.f936e != null) {
            return false;
        }
        if (android.media.audiofx.AcousticEchoCanceler.isAvailable()) {
            android.media.audiofx.AcousticEchoCanceler acousticEchoCancelerCreate = android.media.audiofx.AcousticEchoCanceler.create(i);
            this.f936e = acousticEchoCancelerCreate;
            if (acousticEchoCancelerCreate != null) {
                acousticEchoCancelerCreate.setEnabled(true);
                return this.f936e.getEnabled();
            }
            str = "AcousticEchoCanceler create failed";
        } else {
            str = "AcousticEchoCanceler is not available";
        }
        com.ucarhu.demo.logging.EasyLogger.debug(f930i, str);
        return false;
    }

    public boolean m1063f() {
        return this.f939h;
    }

    public void startRecording() {
        com.ucarhu.demo.logging.EasyLogger.debug(f930i, "start recording, support stereo: " + this.f937f);
        if (m1060d() && m1061e()) {
            new java.lang.Thread(this).start();
        }
    }

    public void stopRecording() {
        this.f939h = false;
    }

    @Override
    public void run(){
        com.ucarhu.demo.vehicle.recorder.CarAudioRecorder.a aVar;
        if (this.f934c.getState() == 0) {
            return;
        }
        com.ucarhu.demo.logging.EasyLogger.debug(f930i, "car audio recorder thread start");
        int i = 15;
        this.f939h = false;
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                break;
            }
            this.f934c.startRecording();
            if (this.f934c.getRecordingState() == 3) {
                this.f939h = true;
                break;
            }
            try {
                java.lang.Thread.sleep(f931j);
            } catch (java.lang.IllegalArgumentException | java.lang.InterruptedException e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f930i, "sleep duration start recording error.", e2);
            }
            i = i2;
        }
        if (!this.f939h) {
            com.ucarhu.demo.logging.EasyLogger.error(f930i, "mic is occupied, exit audio record thread");
        }
        int i3 = this.f938g;
        short[] sArr = new short[i3];
        while (this.f939h) {
            int i4 = this.f934c.read(sArr, 0, i3);
            if (i4 > 0 && (aVar = this.f935d) != null) {
                aVar.mo770a(sArr, i4);
            }
        }
        this.f934c.stop();
        this.f934c.release();
        android.media.audiofx.AcousticEchoCanceler acousticEchoCanceler = this.f936e;
        if (acousticEchoCanceler != null) {
            acousticEchoCanceler.release();
            this.f936e = null;
        }
    }
}
