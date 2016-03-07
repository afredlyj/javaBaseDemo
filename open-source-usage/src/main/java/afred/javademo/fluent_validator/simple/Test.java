package afred.javademo.fluent_validator.simple;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.Result;

/**
 * Created by winnie on 2016-02-28 .
 */
public class Test {

    public static void main(String[] args) {
        Car car = new Car();

//        Result ret = FluentValidator.checkAll()
////                .on(car.getLicensePlate(), new CarLicensePlateValidator())
////                .on(car.getManufacturer(), new CarManufacturerValidator())
//                .on(car.getSeatCount(), new CarSeatCountValidator())
//                .doValidate()
//                .result(toSimple());

//        System.out.println(ret);
    }

}
