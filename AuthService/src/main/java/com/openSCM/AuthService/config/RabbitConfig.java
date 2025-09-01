package com.openscm.authservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public DirectExchange supplierExchange() {
        return new DirectExchange("supplierExchange");
    }

    @Bean
    public Queue supplierQueue() {
        return new Queue("supplierQueue", true); // durable queue
    }

    @Bean
    public Binding binding(Queue supplierQueue, DirectExchange supplierExchange) {
        return BindingBuilder.bind(supplierQueue).to(supplierExchange).with("supplier.created");
    }
}
