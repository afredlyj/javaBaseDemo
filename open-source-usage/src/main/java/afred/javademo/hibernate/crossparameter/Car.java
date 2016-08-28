package afred.javademo.hibernate.crossparameter;

import afred.javademo.hibernate.Person;
import afred.javademo.hibernate.custom.CaseMode;
import afred.javademo.hibernate.custom.CheckCase;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by winnie on 2016-02-26 .
 */
public class Car {

    @NotNull
    private String manufacturer;

    @NotNull
    @Size(min = 3, max = 10)
    @CheckCase(CaseMode.UPPER)
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

    @LuggageCountMatchesPassengerCount(piecesOfLuggagePerPassenger = 2)
    public void load(List<Person> passengers, List<PieceOfLuggage> luggage) {
        //...
    }
}
