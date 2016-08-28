package afred.javademo.hibernate.constraintcomposition;

/**
 * Created by winnie on 2016-02-27 .
 */
public class Car {

    @ValidLicensePlate
    private String licensePlate;

    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
