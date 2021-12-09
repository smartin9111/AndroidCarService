package com.example.carservice.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carservice.Car;
import com.example.carservice.CarAdapter;
import com.example.carservice.CarManager;
import com.example.carservice.R;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        List<Car> carList = CarManager.loadMockData();
        recyclerView = rootView.findViewById(R.id.carRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView.setAdapter(new CarAdapter(carList));

        return rootView;
    }
}