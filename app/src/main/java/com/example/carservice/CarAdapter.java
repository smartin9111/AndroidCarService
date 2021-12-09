package com.example.carservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carservice.ui.car.CarFragment;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarHolder> {

    private List<Car> carList;
    private Context context;


    public CarAdapter(List<Car> carList, Context context) {
        this.carList = carList;
        this.context = context;
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


        holder.itemView.setOnClickListener(v -> {
        MainActivity activity = (MainActivity) context;
        Fragment fragment = new CarFragment(car);
        activity.loadFragment(fragment, "Car");

        });
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
