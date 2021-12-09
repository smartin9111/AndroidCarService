package com.example.carservice.ui.car;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carservice.Car;
import com.example.carservice.MainActivity;
import com.example.carservice.R;

public class CarFragment extends Fragment {

    private Car car;
    private TextView plateNumber;
    private TextView manufacturer;
    private TextView type;
    private TextView yearOfManufacture;
    private TextView description;

    public CarFragment(Car car) {
        this.car = car;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_car, container, false);

        plateNumber = rootView.findViewById(R.id.textViewPlateNumer);
        manufacturer = rootView.findViewById(R.id.textViewManufacturer);
        type = rootView.findViewById(R.id.textViewType);
        yearOfManufacture = rootView.findViewById(R.id.textViewYear);
        description = rootView.findViewById(R.id.textViewDescription);

        plateNumber.setText(car.getPlateNumber());
        manufacturer.setText(car.getManufacturer());
        type.setText(car.getType());
        yearOfManufacture.setText(car.getYearOfManufacture());
        description.setText(car.getDescription());

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.car_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


}