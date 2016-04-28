package afred.javademo.singleton;

/**
 * Created by winnie on 2016-04-06 .
 */
public class Singleton2 {

    private volatile Singleton2 singleton;

    // jdk1.5 之后, volatile才保证可见性，阻止重排序
    public Singleton2 getSingleton() {
        if (null == singleton) {
            synchronized (Singleton2.class) {
                if (null == singleton) {
                    singleton = new Singleton2();
                }
            }
        }

        return singleton;
    }

}
