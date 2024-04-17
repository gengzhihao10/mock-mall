package com.gzh.service;

import org.springframework.amqp.core.Message;

public interface UserPointsConsumeService {

    void consume(Message message);
}
