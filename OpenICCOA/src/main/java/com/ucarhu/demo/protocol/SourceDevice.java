package com.ucarhu.demo.protocol;

public enum SourceDevice {
    CAR(0),
    PHONE(1);


    private final int code;

    SourceDevice(int code) {
        this.code = code;
    }

    public final int getCode() {
        return this.code;
    }
}
