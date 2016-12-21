package afred.javademo.jvm;

/**
 * Created by afred on 16/12/10.
 */
public class StaticDispatch {

    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Woman extends Human {
    }


    public void sayHello(Human human) {
        System.out.println("hello human");

    }

    public void sayHello(Man man) {
        System.out.println("hello man");
    }

    public void sayHello(Woman woman) {
        System.out.println("hello woman");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();

        StaticDispatch dispatch = new StaticDispatch();
        // 编译器在重载时,是通过参数的静态类型而不是实际类型作为判断依据的
        dispatch.sayHello(man);
        dispatch.sayHello(woman);
    }
}
