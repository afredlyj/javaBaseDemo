package afred.demo.spring.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2015-12-29 
 * Time: 16:27
 */
public class Magician implements MindReader {

    private static final Logger logger = LoggerFactory.getLogger(Magician.class);

    private String thoughts;

    @Override public void interceptThoughts(String thoughts) {


    }

    @Override public String getThouhts() {
        return null;
    }
}
