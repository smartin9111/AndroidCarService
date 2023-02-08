package com.example.carservice.db.service;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ServiceDao {

    @Query("SELECT * FROM services")
    List<Service> getAllService();

    @Query("SELECT * FROM services WHERE serviceId = :serviceId")
    Service getServiceById(int serviceId);

    @Insert
    void insertService(Service service);

    @Delete
    void deleteService(Service service);
}
