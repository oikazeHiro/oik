package com.oik.util.time;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author 15093
 * @description TODO
 * @date 2023/2/16 9:21
 */
//时间戳反序列化时间
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext deserializationContext)
            throws IOException {
        long timestamp = p.getValueAsLong();
        if (timestamp > 0) {
            return LocalDateTimeUtil.of(timestamp, ZoneOffset.of("+8"));
        } else {
            return null;
        }
    }
}
