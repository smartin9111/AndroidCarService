package com.example.carservice.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.carservice.TimeConverter;
import com.example.carservice.db.car.Car;
import com.example.carservice.db.car.CarDao;
import com.example.carservice.db.service.Service;
import com.example.carservice.db.service.ServiceDao;

@Database(entities = {Car.class, Service.class}, version = 2)
@TypeConverters(TimeConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CarDao carDao();

    public abstract ServiceDao serviceDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDBInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "CarService")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }



}
