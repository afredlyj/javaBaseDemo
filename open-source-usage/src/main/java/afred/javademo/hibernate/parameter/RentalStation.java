package afred.javademo.hibernate.parameter;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by winnie on 2016-02-27 .
 */
public class RentalStation {

    public RentalStation(@NotNull String name) {
        //...
    }

    public void rentCar(
//            @NotNull Customer customer,
            @NotNull @Future Date startDate,
            @Min(1) int durationInDays) {
        //...
    }

}
