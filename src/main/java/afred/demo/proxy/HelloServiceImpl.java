package afred.demo.proxy;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2016-03-02 
 * Time: 19:48
 */
public class HelloServiceImpl implements IHelloService {

    @Override public String sayHello() {
        return "afred";
    }

    @Override public String sayHi() {
        return "hi, afred";
    }
}
