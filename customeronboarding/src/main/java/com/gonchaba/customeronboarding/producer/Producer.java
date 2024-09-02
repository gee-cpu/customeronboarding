package com.gonchaba.customeronboarding.producer;

public interface Producer<T> {

    void send(T message);
}
