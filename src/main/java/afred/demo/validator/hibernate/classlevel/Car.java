package afred.demo.validator.hibernate.classlevel;

import java.util.List;

/**
 * Created by winnie on 2016-02-26 .
 */
@ValidPassengerCount(message = "There must be not more passengers than seats.")
public class Car {

    private int seatCount;

    private List<Person> passengers;

    public Car(int seatCount, List<Person> passengers) {
        this.seatCount = seatCount;
        this.passengers = passengers;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public List<Person> getPassengers() {
        return passengers;
    }
}
