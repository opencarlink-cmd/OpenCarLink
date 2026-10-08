package com.ucarhu.demo.protocol.channel;

public interface SendCallback extends com.ucarhu.demo.protocol.channel.FutureCallback<java.lang.Boolean> {
    @Override
    @java.lang.Deprecated
    default java.lang.Boolean mapResponse(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        if (c0102w != null) {
            c0102w.recycle();
        }
        return java.lang.Boolean.valueOf(c0102w != null);
    }

    @Override
    default void onSuccess(java.lang.Boolean bool) {
    }
}
