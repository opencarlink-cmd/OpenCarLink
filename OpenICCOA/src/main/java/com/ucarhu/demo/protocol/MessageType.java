package com.ucarhu.demo.protocol;

public enum MessageType {
    SEND(0),
    REQ(1),
    RES(2),
    SEND_SYNC(3);


    private final int f599b;

    MessageType(int i) {
        this.f599b = i;
    }

    public final int getCode() {
        return this.f599b;
    }
}
