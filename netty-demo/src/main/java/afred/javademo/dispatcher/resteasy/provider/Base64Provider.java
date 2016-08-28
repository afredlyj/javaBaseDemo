package afred.javademo.dispatcher.resteasy.provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

/**
 * Created by afred on 16/8/20.
 */
@Provider
@Consumes("application/base64")
public class Base64Provider implements MessageBodyReader<Serializable> {
    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {

        // TODO: 16/8/20
        return false;
    }

    @Override
    public Serializable readFrom(Class<Serializable> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {

        // TODO: 16/8/20
        return null;
    }
}
