package com.example.bloodbank.ambulance;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.bloodbank.R;
import com.example.bloodbank.ambulance.Ambulance;

import java.util.List;

public class CustomAdapterAmbulance extends ArrayAdapter<Ambulance> {
    private Activity context;
    private List<Ambulance> ambulanceList;

    public CustomAdapterAmbulance(Activity context, List<Ambulance> ambulanceList) {
        super(context, R.layout.sample_ambulance_layout, ambulanceList);
        this.context = context;
        this.ambulanceList = ambulanceList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_ambulance_layout, null, true);

        Ambulance ambulance = ambulanceList.get(position);

        TextView t1 = view.findViewById(R.id.ambulanceNameSampleId);
        TextView t2 = view.findViewById(R.id.ambulanceAreaSampleId);
        TextView t3 = view.findViewById(R.id.ambulancePhoneNoSampleId);

        t1.setText("Name: "+ambulance.getAmbulanceName());
        t2.setText("Area: "+ambulance.getAmbulanceArea());
        t3.setText("Phone: "+ambulance.getAmbulancePhone());

        return view;
    }
}
