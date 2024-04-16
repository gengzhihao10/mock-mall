package com.gzh.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

public interface ResendMapper {

    @Insert("insert into resend (id,message,exchange,routing_key,send_time) values (#{id},#{message},#{exchange},#{routingKey},#{sendTime})")
    void save(Map map);


}
