package afred.javademo.dispatcher.resteasy.provider;

import com.alibaba.fastjson.JSON;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import afred.javademo.dispatcher.resteasy.domain.RequestData;

/**
 * Created by afred on 16/8/20.
 */
@Provider
@Consumes({Base64Provider.CONTENT_TYPE})
@Controller
public class Base64Provider implements MessageBodyReader<RequestData> {

    private static Logger logger = LoggerFactory.getLogger(Base64Provider.class);

    public static final String CONTENT_TYPE = "application/base64";

    public static final MediaType MEDIA_TYPE = new MediaType("application", "base64");

    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {

        // TODO: 16/8/20

        boolean assignableFrom = RequestData.class.isAssignableFrom(aClass);
        boolean equals = MEDIA_TYPE.equals(mediaType);

        logger.debug("clz : {}, media type : {}", assignableFrom, equals);
        logger.debug("media type : {}", mediaType);


        return assignableFrom && equals;

    }

    @Override
    public RequestData readFrom(Class<RequestData> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        logger.debug("multivalued map : {}", httpHeaders);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entityStream));
        String line = null;

        StringBuilder stringBuilder = new StringBuilder(256);
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        if (Base64.isBase64(stringBuilder.toString())) {
            String decodeRequest = new String(Base64.decodeBase64(stringBuilder.toString()));
            return JSON.parseObject(decodeRequest, RequestData.class);
        }

        return null;
    }

}
