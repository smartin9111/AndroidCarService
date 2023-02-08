package com.example.carservice.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carservice.MainActivity;
import com.example.carservice.R;
import com.example.carservice.adapters.CarAdapter;
import com.example.carservice.db.AppDatabase;
import com.example.carservice.db.car.CarWithServices;
import com.example.carservice.ui.car.CarFragment;

import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment implements CarClickListener{

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        List<CarWithServices> carList = AppDatabase.getDBInstance(this.getActivity().getApplicationContext()).carDao().getAllCar();
        Collections.reverse(carList);
        recyclerView = rootView.findViewById(R.id.carRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        CarAdapter adapter = new CarAdapter(carList, this.getActivity(), this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onClick(int id) {
        ((MainActivity) this.getActivity()).loadFragment(CarFragment.newInstance(id),"Car");
    }
}