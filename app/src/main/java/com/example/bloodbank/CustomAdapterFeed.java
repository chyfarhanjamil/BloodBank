package com.example.bloodbank;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapterFeed extends ArrayAdapter<MakeRequest> {
    private Activity context;
    private List<MakeRequest> makeRequestList;

    public CustomAdapterFeed(Activity context, List<MakeRequest> makeRequestList) {
        super(context, R.layout.sample_feed_layout, makeRequestList);
        this.context = context;
        this.makeRequestList = makeRequestList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_feed_layout, null, true);
        MakeRequest makeRequest = makeRequestList.get(position);

        TextView t1 = view.findViewById(R.id.feedNameSampleId);
        TextView t2 = view.findViewById(R.id.feedBloodGrpSampleId);
        TextView t3 = view.findViewById(R.id.feedAreaSampleId);
        TextView t4 = view.findViewById(R.id.feedAmountSampleId);
        TextView t5 = view.findViewById(R.id.feedPhoneNoSampleId);
        TextView t6 = view.findViewById(R.id.feedDetailsSampleId);

        t1.setText("Name: "+makeRequest.getRecipientName());
        t2.setText("Blood Group: "+makeRequest.getRecipientBloodGrp());
        t3.setText("Location: "+makeRequest.getRecipientArea());
        t4.setText("Amount (Bag): "+makeRequest.getRecipientAmount());
        t5.setText("Phone: "+makeRequest.getRecipientPhone());
        t6.setText("Details: "+makeRequest.getRecipientDetails());

        return view;
    }
}
