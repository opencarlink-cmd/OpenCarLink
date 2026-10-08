package com.ucarhu.demo.sharelink.util;

public class ValidationMessage {

    private java.lang.String f211a;

    private java.lang.String f212b;

    private long f213c;

    public java.lang.String m166a() {
        return this.f212b;
    }

    public void m167b(long j) {
        this.f213c = j;
    }

    public void m168c(java.lang.String str) {
        this.f212b = str;
    }

    public long m169d() {
        return this.f213c;
    }

    public void m170e(java.lang.String str) {
        this.f211a = str;
    }

    public java.lang.String m171f() {
        return this.f211a;
    }

    public java.lang.String toString() {
        return "ValidationMessage{deviceId='" + this.f211a + "', authentication='" + this.f212b + "', deadline=" + this.f213c + '}';
    }
}
