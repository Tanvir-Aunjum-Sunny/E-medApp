package com.example.e_medapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DoctorDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        Intent DoctorIntent = getIntent();
        String DoctorID = DoctorIntent.getStringExtra("DoctorID");
        String DoctorName = DoctorIntent.getStringExtra("DoctorName");
        String DoctorEmail = DoctorIntent.getStringExtra("DoctorEmail");
        int DoctorPhone = DoctorIntent.getIntExtra("DoctorPhone", -1);
        String DoctorType = DoctorIntent.getStringExtra("DoctorType");

        TextView tvDoctorID = (TextView) findViewById(R.id.tvDoctorID);
        TextView tvDoctorName = (TextView) findViewById(R.id.tvDoctorName);
        TextView tvDoctorEmail = (TextView) findViewById(R.id.tvDoctorEmail);
        TextView tvDoctorPhone = (TextView) findViewById(R.id.tvDoctorPhone);
        TextView tvDoctorType = (TextView) findViewById(R.id.tvDoctorType);


        // Display user details
        tvDoctorID.setText(DoctorID);
        tvDoctorName.setText(DoctorName);
        tvDoctorEmail.setText(DoctorEmail);
        tvDoctorPhone.setText(DoctorPhone + "");
        tvDoctorType.setText(DoctorType);

    }
}
