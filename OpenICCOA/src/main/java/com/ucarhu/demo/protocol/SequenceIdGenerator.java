package com.ucarhu.demo.protocol;

public class SequenceIdGenerator {

    private static final java.util.concurrent.atomic.AtomicInteger NEXT_SEQUENCE_ID = new java.util.concurrent.atomic.AtomicInteger(1);

    public static int nextSequenceId() {
        return NEXT_SEQUENCE_ID.getAndIncrement();
    }
}
