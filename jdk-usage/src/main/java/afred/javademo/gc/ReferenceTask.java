package afred.javademo.gc;

import java.util.List;

/**
 * Created by winnie on 15/12/2.
 */
public class ReferenceTask implements Runnable {

    private volatile byte[] arr = null;


    public void run() {

        arr = new byte[2 * 1024 * 1024];

    }
}
