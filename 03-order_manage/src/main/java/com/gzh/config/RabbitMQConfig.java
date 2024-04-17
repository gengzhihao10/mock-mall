package com.gzh.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ORDER_EXCHANGE = "order_exchange";
    public static final String ORDER_QUEUE = "order_queue";

    public static final String ORDER_DEAD_EXCHANGE = "order_dead_exchange";
    public static final String ORDER_DEAD_QUEUE = "order_dead_queue";

    @Bean
    public Exchange orderExchange(){
        return ExchangeBuilder.fanoutExchange(ORDER_EXCHANGE).build();
    }

    @Bean
    public Queue orderQueue(){
        return QueueBuilder.durable(ORDER_QUEUE).deadLetterExchange(ORDER_DEAD_EXCHANGE).build();
    }

    @Bean
    public Exchange orderDeadExchange(){
        return ExchangeBuilder.fanoutExchange(ORDER_DEAD_EXCHANGE).build();
    }

    @Bean
    public Queue orderDeadQueue(){
        return QueueBuilder.durable(ORDER_DEAD_QUEUE).build();
    }

    @Bean
    public Binding orderBinding(Exchange orderExchange, Queue orderQueue){
        return BindingBuilder.bind(orderQueue).to(orderExchange).with("").noargs();
    }

    @Bean
    public Binding orderDeadBinding(Exchange orderDeadExchange,Queue orderDeadQueue){
        return BindingBuilder.bind(orderDeadQueue).to(orderDeadExchange).with("").noargs();
    }
}
