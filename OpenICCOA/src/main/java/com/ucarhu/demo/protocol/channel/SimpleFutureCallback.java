package com.ucarhu.demo.protocol.channel;

public interface SimpleFutureCallback<T> extends com.ucarhu.demo.protocol.channel.FutureCallback<T> {
    @Override
    default void onSuccess(T t) {
    }

    @Override
    default void onFailure(java.lang.Exception exc) {
    }
}
