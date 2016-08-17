package afred.javademo.dispatcher.resteasy;

import org.jboss.resteasy.spi.ResteasyDeployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by afred on 16/8/17.
 */
public class SpringContainer {

    private static final Logger logger = LoggerFactory.getLogger(SpringContainer.class);

    public static ApplicationContext start() throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("rest-easy.xml");

        logger.debug("all beans : {}", Arrays.asList(context.getBeanDefinitionNames()));


        ResteasyDeployment deployment = new ResteasyDeployment();


        Collection<Object> beansWithAnnotation = context.getBeansWithAnnotation(Controller.class).values();

        logger.debug("beans : {}", beansWithAnnotation);
        deployment.getResources().addAll(beansWithAnnotation);

        ConfigurableNettyContainer.start(deployment);
        return context;

    }

}
