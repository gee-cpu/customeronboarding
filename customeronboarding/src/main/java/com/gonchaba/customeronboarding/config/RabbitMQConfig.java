package com.gonchaba.customeronboarding.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {
    private final Config config;

    @Bean
    public TopicExchange customerValidationExchange() {
        return new TopicExchange(config.getExchange());
    }

    @Bean
    public Queue iprsQueue() {
        return new Queue(config.getIprsQueueName());
    }

    @Bean
    public Binding iprsValidationBinding() {
        return BindingBuilder.bind(iprsQueue())
                .to(customerValidationExchange())
                .with(config.getIprsQueueKey());
    }

    @Bean
    public Queue kraQueue() {
        return new Queue(config.getKraQueueName());
    }

    @Bean
    public Binding kraValidationBinding() {
        return BindingBuilder.bind(kraQueue())
                .to(customerValidationExchange())
                .with(config.getKraQueueKey());
    }

    @Bean
    public Queue crbQueue() {
        return new Queue(config.getCrbQueueName());
    }

    @Bean
    public Binding crbValidationBinding() {
        return BindingBuilder.bind(crbQueue())
                .to(customerValidationExchange())
                .with(config.getCrbQueueKey());
    }


    @Bean
    MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter(objectMapper));
        return rabbitTemplate;
    }
}
