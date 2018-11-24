package com.ford.qiye.web.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fute.common.util.DateUtils;

import java.io.IOException;
import java.util.Date;

/**
 * Created by wanglijun on 16/8/5.
 */
public class DateTimeSerializer extends JsonSerializer<Date> {


    @Override
    public void serialize(Date value, JsonGenerator generator,
                          SerializerProvider provider) throws IOException, JsonProcessingException {
        generator.writeString(DateUtils.formatDate(value, DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS));

    }

}