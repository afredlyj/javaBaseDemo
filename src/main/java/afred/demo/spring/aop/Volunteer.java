package afred.demo.spring.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2015-12-29 
 * Time: 16:53
 */
public class Volunteer implements Thinker {

    private static final Logger logger = LoggerFactory.getLogger(Volunteer.class);

    private String thoughts;

    @Override public void thinkOfSomething(String thoughts) {

        this.thoughts = thoughts;
    }

    public String getThoughts() {
        return thoughts;
    }

    public void setThoughts(String thoughts) {
        this.thoughts = thoughts;
    }
}
