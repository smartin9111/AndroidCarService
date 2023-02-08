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
import com.example.carservice.db.service.Service;
import com.example.carservice.ui.car.CarFragment;
import com.example.carservice.ui.car.ServiceClickListener;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceHolder> {

    private List<Service> serviceList;
    private Context context;
    private ServiceClickListener serviceClickListener;

    public ServiceAdapter(List<Service> serviceList, Context context, ServiceClickListener serviceClickListener) {
        this.serviceList = serviceList;
        this.context = context;
        this.serviceClickListener = serviceClickListener;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ServiceClickListener getServiceClickListener() {
        return serviceClickListener;
    }

    public void setServiceClickListener(ServiceClickListener serviceClickListener) {
        this.serviceClickListener = serviceClickListener;
    }

    @NonNull
    @Override
    public ServiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_list_row, parent, false);

        return new ServiceAdapter.ServiceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceHolder holder, int position) {


        Service service = serviceList.get(position);
        holder.dateTextView.setText(service.getDate().toString());
        holder.typeTextView.setText(service.getServiceType());
        int carId = service.getCarId();

        holder.itemView.setOnClickListener(v -> {
            serviceClickListener.onClick(service);
        });

        holder.removeCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase.getDBInstance((MainActivity) context).serviceDao().deleteService(service);
                ((MainActivity)context).loadFragment(CarFragment.newInstance(carId), "Home");
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public class ServiceHolder extends RecyclerView.ViewHolder {

        public TextView dateTextView;
        public TextView typeTextView;
        public Button removeCarButton;

        public ServiceHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            removeCarButton = itemView.findViewById(R.id.deleteServiceButton);
        }
    }



}
