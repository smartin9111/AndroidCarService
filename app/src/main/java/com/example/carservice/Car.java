package com.example.carservice;

import java.util.List;

public class Car {

    private String plateNumber;
    private String manufacturer;
    private String type;
    private String yearOfManufacture;
    private String description;
    private List<Service> services;

    public Car(String plateNumber, String manufacturer, String type, String yearOfManufacture, String description, List<Service> services) {
        this.plateNumber = plateNumber;
        this.manufacturer = manufacturer;
        this.type = type;
        this.yearOfManufacture = yearOfManufacture;
        this.description = description;
        this.services = services;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
