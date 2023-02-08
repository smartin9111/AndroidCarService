package com.example.carservice.ui.car;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carservice.MainActivity;
import com.example.carservice.R;
import com.example.carservice.adapters.ServiceAdapter;
import com.example.carservice.db.AppDatabase;
import com.example.carservice.db.car.CarWithServices;
import com.example.carservice.db.service.Service;
import com.example.carservice.ui.add.AddCarFragment;
import com.example.carservice.ui.add.AddServiceFragment;
import com.example.carservice.ui.home.HomeFragment;

import java.util.Collections;

public class CarFragment extends Fragment implements Toolbar.OnMenuItemClickListener, ServiceClickListener{

    private TextView plateNumber;
    private TextView manufacturer;
    private TextView type;
    private TextView yearOfManufacture;
    private TextView description;
    private int carId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_car, container, false);

        Toolbar toolbar= (Toolbar) getActivity().findViewById(R.id.main_toolbar);
        toolbar.inflateMenu(R.menu.car_menu);
        toolbar.setOnMenuItemClickListener(this);

        plateNumber = rootView.findViewById(R.id.textViewPlateNumer);
        manufacturer = rootView.findViewById(R.id.textViewManufacturer);
        type = rootView.findViewById(R.id.textViewType);
        yearOfManufacture = rootView.findViewById(R.id.textViewYear);
        description = rootView.findViewById(R.id.textViewDescription);

        carId = getArguments().getInt("someInt", 0);
        CarWithServices car = AppDatabase.getDBInstance(this.getActivity().getApplicationContext()).carDao().getCarById(carId);
        plateNumber.setText(car.getCar().getPlateNumber());
        manufacturer.setText(car.getCar().getManufacturer());
        type.setText(car.getCar().getType());
        yearOfManufacture.setText(car.getCar().getYearOfManufacture());
        description.setText(car.getCar().getDescription());

        Collections.reverse(car.getServices());
        RecyclerView recyclerView = rootView.findViewById(R.id.serviceRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        ServiceAdapter adapter = new ServiceAdapter(car.getServices(), this.getActivity(), this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.car_menu, menu);

    }

    public static CarFragment newInstance(int someInt) {
        CarFragment myFragment = new CarFragment();

        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_service:
                ((MainActivity) this.getActivity()).loadFragment(AddServiceFragment.newInstance(carId), "Add Service");
                return true;
            case R.id.action_home:
                ((MainActivity) this.getActivity()).loadFragment(new HomeFragment(), "Home");
                return true;
            case R.id.action_add_car:
                ((MainActivity) this.getActivity()).loadFragment(new AddCarFragment(), "Add Car");
                return true;
        }
        return true;
    }

    @Override
    public void onClick(Service service) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Description");
        alertDialog.setMessage(service.getDescription());
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
}