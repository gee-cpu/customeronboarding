package com.gonchaba.customeronboarding.consumer;

import com.gonchaba.customeronboarding.dto.CheckDTO;
import com.gonchaba.customeronboarding.model.CustomerChecks;
import com.gonchaba.customeronboarding.service.CheckRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IPRSReceiver implements Receiver<CheckDTO> {
    private final CheckRunner iprsCheckService;

    @Override
    @RabbitListener(queues = "${rabbitmq.queue.iprs.name}")
    public void consume(CheckDTO message) {
       log.info("receiver IPRS request {}", message.getType());
       iprsCheckService.runCheck(message);
    }
}
