package com.ucarhu.demo.protocol.channel;

public interface FutureCallback<T> {
    T mapResponse(com.ucarhu.demo.protocol.UCarMessage c0102w);

    void onSuccess(T t);

    void onFailure(java.lang.Exception exc);
}
