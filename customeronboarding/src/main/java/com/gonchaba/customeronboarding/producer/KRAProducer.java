package com.gonchaba.customeronboarding.producer;

import com.gonchaba.customeronboarding.dto.CheckDTO;
import com.gonchaba.customeronboarding.config.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service("kraProducer")
@RequiredArgsConstructor
public class KRAProducer implements Producer<CheckDTO> {
    private final RabbitTemplate rabbitTemplate;
    private final Config config;
    @Override
    public void send(CheckDTO message) {
        rabbitTemplate.convertAndSend(config.getExchange(),config.getKraQueueKey(), message);
    }
}
