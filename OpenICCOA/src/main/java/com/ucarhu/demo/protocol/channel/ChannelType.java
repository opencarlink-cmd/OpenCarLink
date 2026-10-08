package com.ucarhu.demo.protocol.channel;

public enum ChannelType {
    CUSTOM(0),
    UIBC(4321),
    RTSP(7236),
    RTP(15550),
    AUTH(57209),
    CONTROL(57219),
    MEDIA(57229),
    SENSOR(57239),
    CERT(57249),
    VIDEO(57259);


    private static com.ucarhu.demo.protocol.channel.ChannelType[] f432m = null;

    private final int f434b;

    ChannelType(int i) {
        this.f434b = i;
    }

    public static com.ucarhu.demo.protocol.channel.ChannelType fromPort(int i) {
        if (f432m == null) {
            f432m = values();
        }
        int i2 = 0;
        while (true) {
            com.ucarhu.demo.protocol.channel.ChannelType[] enumC0057bArr = f432m;
            if (i2 >= enumC0057bArr.length) {
                return CUSTOM;
            }
            if (enumC0057bArr[i2].f434b == i) {
                return enumC0057bArr[i2];
            }
            i2++;
        }
    }

    public int getPort() {
        return this.f434b;
    }
}
