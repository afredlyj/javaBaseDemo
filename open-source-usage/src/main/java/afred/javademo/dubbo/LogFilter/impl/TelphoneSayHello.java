package afred.javademo.dubbo.logfilter.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import afred.javademo.dubbo.logfilter.HelloService;

/**
 * Created by afred on 16/10/6.
 */
public class TelphoneSayHello implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(TelphoneSayHello.class);

    @Override
    public String sayHello(String name) {

        return "telphone : hello " + name;
    }
}
