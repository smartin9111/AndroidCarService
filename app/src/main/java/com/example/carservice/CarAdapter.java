package com.example.carservice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarHolder> {

    private List<Car> carList;

    public CarAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_list_row, parent, false);

        return new CarHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarHolder holder, int position) {
        Car car = carList.get(position);
        holder.plateTextView.setText(car.getPlateNumber());
        holder.typeTextView.setText(car.getType());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class CarHolder extends RecyclerView.ViewHolder {

        public TextView plateTextView;
        public TextView typeTextView;

        public CarHolder(@NonNull View itemView) {
            super(itemView);
            plateTextView = itemView.findViewById(R.id.plateTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
        }
    }

}
