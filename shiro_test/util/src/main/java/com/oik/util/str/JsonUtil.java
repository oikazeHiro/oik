package com.oik.util.str;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.oik.util.time.LocalDateTimeDeserializer;
import com.oik.util.time.LocalDateTimeSerializer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author 15093
 * @description TODO
 * @date 2023/2/16 9:30
 */
@Component
public class JsonUtil {
    public String serializer(Object data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        mapper.registerModule(timeModule);
        String json = mapper.writeValueAsString(data);
        System.out.println(json);
        return json;
    }

    public <T> T deserializer(String json, Class<T> type) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        mapper.registerModule(timeModule);
        return mapper.readValue(json, type);
    }

    public String fastJsonSerializer(Object data){
        return JSON.toJSONString(data);
    }

    public <T> T fastJsonDeserializer(String json, Class<T> type){
        return JSON.parseObject(json,type);
    }
}
