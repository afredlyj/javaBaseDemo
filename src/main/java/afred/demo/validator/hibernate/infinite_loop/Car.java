package afred.demo.validator.hibernate.infinite_loop;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by winnie on 2016-02-26 .
 */
public class Car {

    @NotNull
    private String manufacturer;

    @NotNull
    @Size(min = 3, max = 10)
    private String licensePlate;

    @Min(2)
    private int seatCount;

    @NotNull
    // 级联
    @Valid
    private Person driver;

    public Car(String manufacturer, String licencePlate, int seatCount) {
        this.manufacturer = manufacturer;
        this.licensePlate = licencePlate;
        this.seatCount = seatCount;
    }

    public Person getDriver() {
        return driver;
    }

    public void setDriver(Person driver) {
        this.driver = driver;
    }
}
