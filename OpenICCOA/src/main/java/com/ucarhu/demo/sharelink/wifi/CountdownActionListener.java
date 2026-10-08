package com.ucarhu.demo.sharelink.wifi;

public class CountdownActionListener extends com.ucarhu.demo.sharelink.wifi.LoggingActionListener {

    private java.util.concurrent.CountDownLatch f224c;

    public CountdownActionListener(java.lang.String str, java.lang.String str2, java.util.concurrent.CountDownLatch countDownLatch) {
        super(str, str2);
        java.util.Objects.requireNonNull(countDownLatch, "Parameter latch should not be null.");
        this.f224c = countDownLatch;
    }

    @Override
    public void onFailure(int i) {
        super.onFailure(i);
        this.f224c.countDown();
    }

    @Override
    public void onSuccess() {
        super.onSuccess();
        this.f224c.countDown();
    }
}
