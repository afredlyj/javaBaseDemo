package afred.demo.interview;

/**
 * Created by winnie on 15/9/26.
 */
public class Base {

    private String baseName = "base";

    public Base() {
        callName();
    }

    public void callName() {
        System.out.println(baseName);
    }

    static class Sub extends Base {
        private String baseName = "sub";

        @Override
        public void callName() {
            System.out.println(baseName);
        }
    }

    class Sub2 extends Base {
        private String baseName = "sub";

        @Override
        public void callName() {
            System.out.println(baseName);
        }
    }



    public static void main(String[] args) {

        /**
         * 构造器的初始化顺序大概是 父类静态块 子类静态块 父类初始化语句 父类构造函器 子类初始化语句 子类构造器。
         * 父类构造器执行的时候，调用了子类的重载方法，然而子类的类字段还在刚初始化的阶段，只能输出null。
         */

        // null
        Base b = new Sub();

        // base
        Base b2 = new Base();

        // null
        b2 = b2.new Sub2();
    }

}
