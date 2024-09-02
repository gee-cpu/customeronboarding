package com.gonchaba.customeronboarding.consumer;

public interface Receiver <T> {

    void consume(T message);
}
