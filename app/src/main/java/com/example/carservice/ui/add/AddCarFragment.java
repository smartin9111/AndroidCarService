package com.example.carservice.ui.add;

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
import com.example.carservice.ui.home.HomeFragment;

public class AddCarFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_car, container, false);

        Button addCar = rootView.findViewById(R.id.buttonAddCar);
        EditText editPlate = rootView.findViewById(R.id.editPlate);
        EditText editManufacturer = rootView.findViewById(R.id.editManufacturer);
        EditText editType = rootView.findViewById(R.id.editType);
        EditText editYearOfManufacture = rootView.findViewById(R.id.editYearOfManufacture);
        EditText editDescription = rootView.findViewById(R.id.editDescription);

        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Car car = new Car(editPlate.getText().toString(), editManufacturer.getText().toString(), editType.getText().toString(), editYearOfManufacture.getText().toString(),editDescription.getText().toString());
                saveNewCar(car);
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

    private void saveNewCar(Car car) {
        AppDatabase db  = AppDatabase.getDBInstance(this.getActivity().getApplicationContext());

        db.carDao().insertCar(car);

        ((MainActivity) this.getActivity()).loadFragment(new HomeFragment(),"Home");
    }
}