package com.example.carservice.db.car;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CarDao {

    @Query("SELECT * FROM cars")
    List<CarWithServices> getAllCar();

    @Query("SELECT * FROM cars WHERE carId = :carId")
    CarWithServices getCarById(int carId);

    @Insert
    void insertCar(Car car);

    @Delete
    void deleteCar(Car car);

    @Update
    void updateCar(Car car);
}
