package com.example.e_medapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class doctorRegistration extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String DoctorType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);

        final EditText etDoctorID  = (EditText) findViewById(R.id.etPatientID);
        final EditText etDoctorName = (EditText) findViewById(R.id.etPatientName);
        final EditText etDoctorPassword = (EditText) findViewById(R.id.etDoctorPassword);
        final EditText etDoctorEmail = (EditText) findViewById(R.id.etPatientEmail);
        final EditText etDoctorPhone = (EditText) findViewById(R.id.etPatientPhone);
        final Button buttonDoctorReg = (Button) findViewById(R.id.buttonDoctorReg);

        spinner = (Spinner)findViewById(R.id.PatientSpinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.doctorType_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                DoctorType = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?>parent){

            }
        });

        buttonDoctorReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String DoctorID = etDoctorID.getText().toString();
                final String DoctorName = etDoctorName.getText().toString();
                final String DoctorPassword = etDoctorPassword.getText().toString();
                final String DoctorEmail = etDoctorEmail.getText().toString();
                final String DoctorPhone = etDoctorPhone.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(doctorRegistration.this, "Registration Successfull",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(doctorRegistration.this, adminPanel.class);
                                doctorRegistration.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(doctorRegistration.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                DoctorRegisterRequest doctorRegisterRequest = new DoctorRegisterRequest(DoctorID, DoctorName, DoctorPassword, DoctorEmail, DoctorPhone, DoctorType, responseListener);
                RequestQueue queue = Volley.newRequestQueue(doctorRegistration.this);
                queue.add(doctorRegisterRequest);
            }
        });


    }
}
