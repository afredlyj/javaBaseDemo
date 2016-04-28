package afred.javademo.rtti;

import afred.javademo.proxy.rpc.ObjectEchoClient;

/**
 * Created by winnie on 2016-04-06 .
 */

interface HasBatteries {

}

interface Waterproof {

}

interface Shoots {

}

class Toy {
    Toy() {

    }

    Toy(int i) {

    }
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots {
    FancyToy() {
        super(1);
    }
}

public class ToyTest {


    static void print(Class clz) {
        System.out.println("class name : " + clz.getName() + ", interface ? " + clz.isInterface());
        System.out.println("simple name : " + clz.getSimpleName());
        System.out.println("canonical name : " + clz.getCanonicalName());
    }

    public static void main(String[] args) {
        Class clz = null;

        try {
            clz = Class.forName("afred.javademo.rtti.FancyToy");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

        print(clz);

        for (Class c : clz.getInterfaces()) {
            print(c);
        }

        Class up = clz.getSuperclass();

        try {
            Object obj = up.newInstance();
            print(obj.getClass());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
