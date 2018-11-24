package com.ford.qiye.web.converter;

import org.apache.commons.codec.CharEncoding;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by wanglijun on 16/8/5.
 */
public class StringEscapeHttpMessageConverter extends StringHttpMessageConverter {
    /**编写定义*/
    private static final Charset DEFAULT_CHARSET = Charset.forName(CharEncoding.UTF_8);

    protected void writeInternal(String s, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        s = HtmlUtils.htmlEscape(s);
        StreamUtils.copy(s, DEFAULT_CHARSET, outputMessage.getBody());
    }
}
