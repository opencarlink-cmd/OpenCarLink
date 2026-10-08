package com.ucarhu.demo.protocol.channel.socket;

public class DefaultFutureCallback implements com.ucarhu.demo.protocol.channel.FutureCallback<com.ucarhu.demo.protocol.UCarMessage> {

    private static final java.lang.String f461a = "DefaultFutureCallback";

    private static final com.ucarhu.demo.protocol.channel.socket.DefaultFutureCallback f462b = new com.ucarhu.demo.protocol.channel.socket.DefaultFutureCallback();

    private DefaultFutureCallback() {
    }

    public static com.ucarhu.demo.protocol.channel.socket.DefaultFutureCallback m361d() {
        return f462b;
    }

    @Override
    public void onFailure(java.lang.Exception exc) {
        com.ucarhu.demo.protocol.ProtocolConfig.getLogger().errorWithThrowable(f461a, "request error: " + exc.getMessage(), exc);
    }

    @Override
    public void onSuccess(com.ucarhu.demo.protocol.UCarMessage c0102w) {
    }

    @Override
    public com.ucarhu.demo.protocol.UCarMessage mapResponse(com.ucarhu.demo.protocol.UCarMessage c0102w) {
        return c0102w;
    }
}
