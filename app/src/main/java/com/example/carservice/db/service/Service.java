package com.example.carservice.db.service;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "services")
public class Service {

    @PrimaryKey(autoGenerate = true)
    private int serviceId;
    private String serviceType;
    private String description;
    private LocalDate date;
    private int carId;

    public Service(String serviceType, String description, LocalDate date, int carId) {
        this.serviceType = serviceType;
        this.description = description;
        this.date = date;
        this.carId = carId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
