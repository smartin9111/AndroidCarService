package com.example.carservice.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carservice.MainActivity;
import com.example.carservice.R;
import com.example.carservice.db.AppDatabase;
import com.example.carservice.db.car.Car;
import com.example.carservice.db.car.CarWithServices;
import com.example.carservice.ui.home.CarClickListener;
import com.example.carservice.ui.home.HomeFragment;
import com.example.carservice.ui.update.UpdateCarFragment;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarHolder> {

    private List<CarWithServices> carList;
    private Context context;
    private CarClickListener carClickListener;

    public CarAdapter(List<CarWithServices> carList, Context context, CarClickListener carClickListener) {
        this.carList = carList;
        this.context = context;
        this.carClickListener = carClickListener;
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

        Car car = carList.get(position).car;
        holder.plateTextView.setText(car.getPlateNumber());
        holder.typeTextView.setText(car.getType());

        holder.itemView.setOnClickListener(v -> {
        carClickListener.onClick(car.getId());
        });

        holder.removeCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase.getDBInstance(context.getApplicationContext()).carDao().deleteCar(car);
                ((MainActivity) context).loadFragment(new HomeFragment(), "Home");
            }
        });

        holder.updateCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase.getDBInstance(context.getApplicationContext()).carDao().getCarById(car.getId());
                ((MainActivity) context).loadFragment(UpdateCarFragment.newInstance(car.getId()), "Car Update");
            }
        });

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }



    public class CarHolder extends RecyclerView.ViewHolder {

        public TextView plateTextView;
        public TextView typeTextView;
        public Button removeCarButton;
        public Button updateCarButton;

        public CarHolder(@NonNull View itemView) {
            super(itemView);
            plateTextView = itemView.findViewById(R.id.plateTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            removeCarButton = itemView.findViewById(R.id.carRemoveButton);
            updateCarButton = itemView.findViewById(R.id.carUpdateButton);
        }
    }
}
