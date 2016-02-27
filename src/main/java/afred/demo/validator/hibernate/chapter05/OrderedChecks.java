package afred.demo.validator.hibernate.chapter05;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;
/**
 * Created by winnie on 2016-02-27 .
 */
@GroupSequence({ Default.class, CarChecks.class, DriverChecks.class })
public interface OrderedChecks {
}
