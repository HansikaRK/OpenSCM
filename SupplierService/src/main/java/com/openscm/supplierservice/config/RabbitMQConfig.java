package com.openscm.supplierservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Exchange names (must match AuthService)
    public static final String USER_EXCHANGE = "user.exchange";
    
    // Queue names
    public static final String SUPPLIER_QUEUE = "supplier.registration.queue";
    
    // Routing keys
    public static final String SUPPLIER_ROUTING_KEY = "user.registered.supplier";

    // Exchanges
    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange(USER_EXCHANGE);
    }

    // Queues
    @Bean
    public Queue supplierQueue() {
        return QueueBuilder.durable(SUPPLIER_QUEUE).build();
    }

    // Bindings
    @Bean
    public Binding supplierBinding() {
        return BindingBuilder
                .bind(supplierQueue())
                .to(userExchange())
                .with(SUPPLIER_ROUTING_KEY);
    }

    // JSON converter
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // RabbitTemplate with JSON converter
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
