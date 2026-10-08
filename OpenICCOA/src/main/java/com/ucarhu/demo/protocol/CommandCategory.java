package com.ucarhu.demo.protocol;

public enum CommandCategory {
    NONE(0),
    CONTROL(1),
    SENSOR(2),
    AUTH(3),
    AUDIO(4),
    VIDEO(5),
    CERT(6),
    ACK(7);


    private final int f556b;

    CommandCategory(int i) {
        this.f556b = i;
    }

    public final int getCode() {
        return this.f556b;
    }
}
