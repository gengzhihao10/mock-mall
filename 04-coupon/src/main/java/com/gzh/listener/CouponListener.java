package com.gzh.listener;


import com.gzh.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CouponListener {

    @RabbitListener(queues = {RabbitMQConfig.COUPON_QUEUE})
    public void consume(String msg, Channel channel, Message message) throws InterruptedException, IOException {
        //预扣除优惠券
        Thread.sleep(400);
        System.out.println("优惠券预扣除成功！" + msg);
        //手动ack
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

}
