package com.ucarhu.demo.protocol;

public class ProtocolException extends java.lang.RuntimeException {
    public ProtocolException(java.lang.String str) {
        super(str);
    }

    public ProtocolException(java.lang.String str, java.lang.Throwable th) {
        super(str, th);
    }
}
