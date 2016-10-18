package afred.javademo.joda;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Minutes;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by afred on 16/10/18.
 */
public class JodaTest {

    @Test
    public void dateDifferent() throws ParseException {
        String dateStart = "2013-02-20 11:29:58";
        String dateStop = "2013-02-20 11:31:58";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = null;
        Date d2 = null;
        d1 = format.parse(dateStart);
        d2 = format.parse(dateStop);

        DateTime dt1 = new DateTime(d1);
        DateTime dt2 = new DateTime(d2);

        System.out.println(Days.daysBetween(dt1, dt2).getDays());

        System.out.println(Minutes.minutesBetween(dt1, dt2).getMinutes());
    }

}
