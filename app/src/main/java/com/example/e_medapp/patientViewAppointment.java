package com.example.e_medapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class patientViewAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_appointment);

        Intent PatientIntent = getIntent();

      /*  String PatientID = PatientIntent.getStringExtra("PatientID");
        String PatientName = PatientIntent.getStringExtra("PatientName");
        String PatientEmail = PatientIntent.getStringExtra("PatientEmail");
        int PatientPhone = PatientIntent.getIntExtra("PatientPhone", -1);
        String PatientType = PatientIntent.getStringExtra("PatientType"); */

        String Date = PatientIntent.getStringExtra("Date");
        String Time = PatientIntent.getStringExtra("Time");
        String DoctorID = PatientIntent.getStringExtra("DoctorID");

       /* TextView tvPatientID = (TextView) findViewById(R.id.tvPatientID);
        TextView tvPatientName = (TextView) findViewById(R.id.tvPatientName);
        TextView tvPatientEmail = (TextView) findViewById(R.id.tvPatientEmail);
        TextView tvPatientPhone = (TextView) findViewById(R.id.tvPatientPhone);
        TextView tvPatientType = (TextView) findViewById(R.id.tvPatientType); */
        TextView tvDate = (TextView) findViewById(R.id.tvPatientViewAppointmentDate);
        TextView tvTime = (TextView) findViewById(R.id.tvPatientViewAppointmentTime);
        TextView tvDoctorID = (TextView) findViewById(R.id.tvPatientViewAppointmentDoctor);

        // Display user details
        /*tvPatientID.setText(PatientID);
        tvPatientName.setText(PatientName);
        tvPatientEmail.setText(PatientEmail);
        tvPatientPhone.setText(PatientPhone + "");
        tvPatientType.setText(PatientType); */

        tvDate.setText(Date);
        tvTime.setText(Time);
        tvDoctorID.setText(DoctorID);

    }
}
