package afred.demo.interrupt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2015-08-26 
 * Time: 10:25
 */
public class PrimeGenerator implements Runnable {

    private final List<BigInteger> primes = new ArrayList<BigInteger>();

    private volatile boolean cancelled;

    @Override public void run() {

        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<BigInteger>(primes);
    }

    private static List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } finally {
            generator.cancel();
        }

        return generator.get();
    }

    public static void main(String[] args) {
        try {
            System.out.println("primes : " + aSecondOfPrimes());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
