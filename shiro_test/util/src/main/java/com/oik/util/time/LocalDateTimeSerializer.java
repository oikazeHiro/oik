package com.oik.util.time;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author 15093
 * @description TODO
 * @date 2023/2/16 9:20
 */
//时间序列化时变为时间戳
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        if (value != null) {
            long timestamp = LocalDateTimeUtil.toEpochMilli(value);
            gen.writeNumber(timestamp);
        }
    }
}
