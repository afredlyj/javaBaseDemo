package afred.javademo.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by winnie on 15/12/5.
 */
public class AfredNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        // Spring在解析XML过程中，如果遇到datef节点，则会把XML解析细节委托给SimpleDateFormatBeanDefinitionParser处理
        registerBeanDefinitionParser("datef", new SimpleDateFormatBeanDefinitionParser());

    }
}
