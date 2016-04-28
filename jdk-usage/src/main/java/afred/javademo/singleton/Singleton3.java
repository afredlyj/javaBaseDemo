package afred.javademo.singleton;

/**
 * Created by winnie on 2016-04-06 .
 */
public class Singleton3 {


    private static class Holder {
        private static Singleton3 singleton3 = new Singleton3();
    }

    private Singleton3() {

    }

    public Singleton3 getSingleton() {
        return Holder.singleton3;
    }


}
