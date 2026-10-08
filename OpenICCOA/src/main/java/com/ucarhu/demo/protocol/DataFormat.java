package com.ucarhu.demo.protocol;

public enum DataFormat {
    RAW(0),
    PB3(1);


    private final int f569b;

    DataFormat(int i) {
        this.f569b = i;
    }

    public final int getCode() {
        return this.f569b;
    }
}
