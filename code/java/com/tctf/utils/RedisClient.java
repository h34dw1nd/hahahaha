package com.tctf.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

@Component("redisClient")
public class RedisClient{

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;


    private RedisConnection getConnection() {

        return redisTemplate.getConnectionFactory().getConnection();
    }


    private <T> byte[] getKey(T id) {
        //RedisSerializer serializer = redisTemplate.getKeySerializer();
        //byte[] idBytes = serializer.serialize(id);
        byte[] idBytes = String.valueOf(id).getBytes();
        byte[] prefixBytes = "post".getBytes();
        byte[] key = new byte[prefixBytes.length + idBytes.length];
        System.arraycopy(prefixBytes, 0, key, 0, prefixBytes.length);
        System.arraycopy(idBytes, 0, key, prefixBytes.length, idBytes.length);
        return key;
    }

    public <T> void delete(T t) {
        getConnection().del(getKey(t));
    }

    public <T> void set(Integer id, T t) {
        byte[] key = getKey(id);
        RedisSerializer serializer = redisTemplate.getValueSerializer();
        byte[] val = serializer.serialize(t);
        getConnection().set(key, val);

    }

    public <T> T getObject(Integer id) {
        byte[] key = getKey(id);
        byte[] result = getConnection().get(key);
        return (T) redisTemplate.getValueSerializer().deserialize(result);
    }


}