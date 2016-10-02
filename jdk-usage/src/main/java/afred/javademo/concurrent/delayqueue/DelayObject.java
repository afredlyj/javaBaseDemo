package afred.javademo.concurrent.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by afred on 16/9/1.
 */
public class DelayObject implements Delayed {

    private String data;


    private long startTime;

    public DelayObject(String data, long startTime) {
        this.data = data;
        this.startTime = startTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.startTime < ((DelayObject) o).startTime) {
            return -1;
        }
        if (this.startTime > ((DelayObject) o).startTime) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "{" +
                "data='" + data + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
