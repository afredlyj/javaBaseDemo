package afred.demo.spring_schema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Created by winnie on 15/12/5.
 */
public class SimpleDateFormatBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    private static final Logger logger = LoggerFactory.getLogger(SimpleDateFormatBeanDefinitionParser.class);

    @Override
    protected Class<?> getBeanClass(Element element) {
        return DateFormat.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {

        String pattern = element.getAttribute("pattern");
        if (StringUtils.hasText(pattern)) {
            builder.addPropertyValue("pattern", pattern);
        }

        String lenient = element.getAttribute("lenient");
        if (StringUtils.hasText(lenient)) {
            builder.addPropertyValue("lenient", Boolean.valueOf(lenient));
        }
    }
}
