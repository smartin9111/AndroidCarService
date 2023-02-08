package com.example.carservice.ui.update;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carservice.MainActivity;
import com.example.carservice.R;
import com.example.carservice.db.AppDatabase;
import com.example.carservice.db.car.Car;
import com.example.carservice.db.car.CarWithServices;
import com.example.carservice.ui.home.HomeFragment;


public class UpdateCarFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_update_car, container, false);

        Button updateCar = rootView.findViewById(R.id.buttonAddCar);
        EditText editPlate = rootView.findViewById(R.id.editPlate);
        EditText editManufacturer = rootView.findViewById(R.id.editManufacturer);
        EditText editType = rootView.findViewById(R.id.editType);
        EditText editYearOfManufacture = rootView.findViewById(R.id.editYearOfManufacture);
        EditText editDescription = rootView.findViewById(R.id.editDescription);

        int carId = getArguments().getInt("someInt", 0);
        CarWithServices car = AppDatabase.getDBInstance(this.getActivity().getApplicationContext()).carDao().getCarById(carId);

        editPlate.setText(car.getCar().getPlateNumber());
        editManufacturer.setText(car.getCar().getManufacturer());
        editType.setText(car.getCar().getManufacturer());
        editYearOfManufacture.setText(car.getCar().getYearOfManufacture());
        editDescription.setText(car.getCar().getDescription());

        updateCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int carId = getArguments().getInt("someInt", 0);
                CarWithServices car = AppDatabase.getDBInstance(getActivity().getApplicationContext()).carDao().getCarById(carId);

                car.getCar().setPlateNumber(editPlate.getText().toString());
                car.getCar().setManufacturer(editManufacturer.getText().toString());
                car.getCar().setType(editType.getText().toString());
                car.getCar().setYearOfManufacture(editYearOfManufacture.getText().toString());
                car.getCar().setDescription(editDescription.getText().toString());
                updateCar(car.getCar());
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.only_home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public static UpdateCarFragment newInstance(int someInt) {
        UpdateCarFragment myFragment = new UpdateCarFragment();

        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        myFragment.setArguments(args);

        return myFragment;
    }

    private void updateCar(Car car) {
        AppDatabase db  = AppDatabase.getDBInstance(this.getActivity().getApplicationContext());

        db.carDao().updateCar(car);

        ((MainActivity) this.getActivity()).loadFragment(new HomeFragment(),"Home");
    }

}