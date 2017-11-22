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

public class patientRegistration extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
     String PatientType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);
        final EditText etPatientID  = (EditText) findViewById(R.id.etPatientID);
        final EditText etPatientName = (EditText) findViewById(R.id.etPatientName);
        final EditText etPatientPassword = (EditText) findViewById(R.id.etDoctorPassword);
        final EditText etPatientEmail = (EditText) findViewById(R.id.etPatientEmail);
        final EditText etPatientPhone = (EditText) findViewById(R.id.etPatientPhone);
        final Button buttonPatientReg = (Button) findViewById(R.id.buttonPatientReg);

        spinner = (Spinner)findViewById(R.id.PatientSpinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.patientType_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                 PatientType = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?>parent){

            }
        });

        buttonPatientReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String PatientID = etPatientID.getText().toString();
                final String PatientName = etPatientName.getText().toString();
                final String PatientPassword = etPatientPassword.getText().toString();
                final String PatientEmail = etPatientEmail.getText().toString();
                final int PatientPhone =Integer.parseInt (etPatientPhone.getText().toString());


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(patientRegistration.this, "Registration Successfull",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(patientRegistration.this, adminPanel.class);
                                patientRegistration.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(patientRegistration.this);
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

                PatientRegisterRequest patientRegisterRequest = new PatientRegisterRequest(PatientID, PatientName, PatientPassword, PatientEmail, PatientPhone, PatientType,  responseListener);
                RequestQueue queue = Volley.newRequestQueue(patientRegistration.this);
                queue.add(patientRegisterRequest);
            }
        });


    }
}

