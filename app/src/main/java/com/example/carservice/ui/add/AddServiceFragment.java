package com.example.carservice.ui.add;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carservice.MainActivity;
import com.example.carservice.R;
import com.example.carservice.db.AppDatabase;
import com.example.carservice.db.service.Service;
import com.example.carservice.ui.car.CarFragment;

import java.time.LocalDate;
import java.util.Calendar;

public class AddServiceFragment extends Fragment {

    private LocalDate serviceDate;
    String serviceTypeString;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_service, container, false);

        Button setDateButton = rootView.findViewById(R.id.setDateButton);
        Button saveServiceButton = rootView.findViewById(R.id.addServiceButton);

        RadioGroup serviceType = rootView.findViewById(R.id.radioGroupAddService);

        serviceType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonMandatory:
                        serviceTypeString = "Mandatory Service";
                        break;
                    case R.id.radioButtonTechnikal:
                        serviceTypeString = "Technical Exam";
                        break;
                }
            }
        });

        EditText editDescription = rootView.findViewById(R.id.editTextServiceDescription);

        saveServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int carId = getArguments().getInt("someInt", 0);
                Service service = new Service(serviceTypeString, editDescription.getText().toString(), serviceDate, carId);
                AppDatabase.getDBInstance(getActivity().getApplicationContext()).serviceDao().insertService(service);
                ((MainActivity) getActivity()).loadFragment(CarFragment.newInstance(carId),"Car");
            }
        });

        setDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialog(0).show();
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

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            serviceDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay);
        }
    };

    protected Dialog onCreateDialog(int id) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(this.getActivity(), datePickerListener, year, month, day);
    }

    public static AddServiceFragment newInstance(int someInt) {
        AddServiceFragment myFragment = new AddServiceFragment();

        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        myFragment.setArguments(args);

        return myFragment;
    }
}