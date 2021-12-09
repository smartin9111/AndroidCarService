package com.example.carservice;

import java.util.ArrayList;
import java.util.List;

public class CarManager {

    public static List<Car> loadMockData() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("ABC-123", "VW", "nem", "2015", "GTI", null));
        cars.add(new Car("ABC-125", "VW", "Golf", "2015", "GTI", null));
        cars.add(new Car("ABC-127", "VW", "Golf", "2015", "GTI", null));
        cars.add(new Car("ABC-128", "VW", "Golf", "2015", "GTI", null));
        return cars;
    }

}
