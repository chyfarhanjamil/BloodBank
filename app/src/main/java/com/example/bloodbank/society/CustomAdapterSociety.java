package com.example.bloodbank.society;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.bloodbank.R;

import java.util.List;

public class CustomAdapterSociety extends ArrayAdapter<VoluntarySociety> {
    private Activity context;
    private List<VoluntarySociety> voluntarySocietyList;

    public CustomAdapterSociety(Activity context, List<VoluntarySociety> voluntarySocietyList) {
        super(context, R.layout.sample_society_layout, voluntarySocietyList);
        this.context = context;
        this.voluntarySocietyList = voluntarySocietyList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_society_layout, null, true);

        VoluntarySociety voluntarySociety = voluntarySocietyList.get(position);

        TextView t1 = view.findViewById(R.id.societyNameSampleId);
        TextView t2 = view.findViewById(R.id.societyAreaSampleId);
        TextView t3 = view.findViewById(R.id.societyPhoneNoSampleId);

        t1.setText(voluntarySociety.getSocietyName());
        t2.setText(voluntarySociety.getSocietyArea());
        t3.setText(voluntarySociety.getSocietyPhone());

        return view;
    }
}
