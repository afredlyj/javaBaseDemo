package afred.demo.validator.hibernate;

import javax.validation.constraints.NotNull;

/**
 * Created by winnie on 2016-02-26 .
 */
public class RentalCar extends Car {

    private String rentalStation;

    public void setRentalStation(String rentalStation) {
        this.rentalStation = rentalStation;
    }

    @NotNull
    public String getRentalStation() {
        return rentalStation;
    }

    public RentalCar(String manufacturer, String licencePlate, int seatCount) {
        super(manufacturer, licencePlate, seatCount);
    }


}
