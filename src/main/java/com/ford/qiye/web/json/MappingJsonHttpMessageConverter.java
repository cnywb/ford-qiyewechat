package com.ford.qiye.web.json;

import com.fasterxml.jackson.core.JsonEncoding;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by wanglijun on 16/8/5.
 */
public class MappingJsonHttpMessageConverter extends MappingJackson2HttpMessageConverter {

	/* (non-Javadoc)
	 * @see org.springframework.http.converter.json.MappingJacksonHttpMessageConverter#writeInternal(java.lang.Object, org.springframework.http.HttpOutputMessage)
	 */

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        if(object instanceof String){
            JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
            byte[] bytes = ((String) object).getBytes(encoding.getJavaName());
            OutputStream out=outputMessage.getBody();
            out.write(bytes);
            out.flush();
        }else{
            super.writeInternal(object, outputMessage);
        }
    }
}