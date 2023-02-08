package com.example.carservice.db.car;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.carservice.db.service.Service;

import java.util.List;

public class CarWithServices {

    @Embedded
    public Car car;

    @Relation(parentColumn = "carId", entityColumn = "carId", entity = Service.class)
    public List<Service> services;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
