package com.ucarhu.demo.sharelink.util;

public class IntRange {

    private int f205a;

    private int f206b;

    private IntRange(int i, int i2) {
        this.f205a = i;
        this.f206b = i2;
    }

    public static com.ucarhu.demo.sharelink.util.IntRange of(int i, int i2) {
        if (i2 > i) {
            return new com.ucarhu.demo.sharelink.util.IntRange(i, i2);
        }
        throw new java.lang.IllegalArgumentException("The end integer must greater than the start integer.");
    }

    public boolean contains(int i) {
        return this.f205a <= i && i < this.f206b;
    }
}
