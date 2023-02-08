package com.example.carservice.db.car;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cars")
public class Car {

    @PrimaryKey(autoGenerate = true)
    private int carId;
    private String plateNumber;
    private String manufacturer;
    private String type;
    private String yearOfManufacture;
    private String description;

    public Car(String plateNumber, String manufacturer, String type, String yearOfManufacture, String description) {
        this.plateNumber = plateNumber;
        this.manufacturer = manufacturer;
        this.type = type;
        this.yearOfManufacture = yearOfManufacture;
        this.description = description;
    }

    public int getId() {
        return carId;
    }

    public void setId(int id) {
        this.carId = id;
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

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
