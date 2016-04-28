package afred.javademo.singleton;

/**
 * Created by winnie on 2016-04-06 .
 */
public class Singleton1 {

    // 饿汉
    // 在第一次引用Singleton1时就创建对象，而不管实际是否需要
    private static Singleton1 singleton = new Singleton1();

    private Singleton1() {
        System.out.println("init");
    }

    public Singleton1 getSingleton() {
        return singleton;
    }

}
