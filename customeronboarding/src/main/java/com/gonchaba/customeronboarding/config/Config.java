package com.gonchaba.customeronboarding.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Config {

    @Value("rabbitmq.exchange")
    private String exchange;

    @Value("${rabbitmq.queue.iprs.name}")
    private String iprsQueueName;
    @Value("${rabbitmq.queue.iprs.key}")
    private String iprsQueueKey;

    @Value("${rabbitmq.queue.crb.name}")
    private String crbQueueName;
    @Value("${rabbitmq.queue.crb.key}")
    private String crbQueueKey;

    @Value("${rabbitmq.queue.kra.name}")
    private String kraQueueName;
    @Value("${rabbitmq.queue.kra.key}")
    private String kraQueueKey;
}
