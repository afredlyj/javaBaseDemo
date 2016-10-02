package afred.javademo.dispatcher.resteasy;

import com.google.common.base.Preconditions;

import org.jboss.resteasy.spi.ResteasyDeployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

import javax.ws.rs.ext.Provider;

import afred.javademo.dispatcher.resteasy.annotation.HttpHandler;

/**
 * Created by afred on 16/8/17.
 */
public class SpringContainer {

    private static final Logger logger = LoggerFactory.getLogger(SpringContainer.class);

    private int port;

    private int ioThreads;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getIoThreads() {
        return ioThreads;
    }

    public void setIoThreads(int ioThreads) {
        this.ioThreads = ioThreads;
    }

    public SpringContainer(int port, int ioThreads) {

        Preconditions.checkArgument(port > 0, "port illegal");
        Preconditions.checkArgument(ioThreads > 0, "io thread num illegal");

        this.port = port;
        this.ioThreads = ioThreads;
    }

    public ConfigurableNettyJaxrsServer start(ApplicationContext context) throws Exception {

        ResteasyDeployment deployment = new ResteasyDeployment();

        Collection<Object> beansWithAnnotation = context.getBeansWithAnnotation(HttpHandler.class).values();

        if (beansWithAnnotation != null && !beansWithAnnotation.isEmpty()) {
            deployment.getResources().addAll(beansWithAnnotation);
        }

        Collection<Object> providers = context.getBeansWithAnnotation(Provider.class).values();
        logger.debug("providers : {}", providers);
        if (providers != null) {
            deployment.getProviders().addAll(providers);
        }

        ConfigurableNettyJaxrsServer netty = new ConfigurableNettyJaxrsServer(ioThreads);
        netty.setDeployment(deployment);
        netty.setPort(port);
        netty.setRootResourcePath("");
        netty.setSecurityDomain(null);
        netty.start();
        return netty;
    }

}
