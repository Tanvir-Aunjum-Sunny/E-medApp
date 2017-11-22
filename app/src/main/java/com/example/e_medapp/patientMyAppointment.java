package com.example.e_medapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class patientMyAppointment extends AppCompatActivity {
    Spinner DoctorSelectSpinner;
    ArrayAdapter<CharSequence> adapter;
    String DoctorSelect;
    final Calendar  myCalendar = Calendar.getInstance();
     EditText ettxtDate;
    EditText editTextFromTime;

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        ettxtDate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_my_appointment);
       final EditText etDoctorID = (EditText)findViewById(R.id.etPatientDoctorID);
        editTextFromTime = (EditText) findViewById(R.id.editText3);


        Button buttonMakeAppointment = (Button) findViewById(R.id.buttonMakeAppointment);
        ettxtDate =(EditText)findViewById(R.id.txtdate);

      final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        ettxtDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(patientMyAppointment.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editTextFromTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(patientMyAppointment.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editTextFromTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }

        });

        /*
       DoctorSelectSpinner = (Spinner)findViewById(R.id.DoctorSelectSpinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.doctorType_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DoctorSelectSpinner.setAdapter(adapter);
        DoctorSelectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                DoctorSelect = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?>parent){

            }
        });

        ettxtDate.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View view, boolean hasfocus){
                if(hasfocus){
                    DateDialog dialog=new DateDialog(view);
                    FragmentTransaction ft =getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");

                }
            }

        }); */

        buttonMakeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PatientIntent = getIntent();
                final String PatientID = PatientIntent.getStringExtra("PatientID");
                final String PatientName = PatientIntent.getStringExtra("PatientName");
                final String PatientPassword = PatientIntent.getStringExtra("PatientPassword");
                final String PatientEmail = PatientIntent.getStringExtra("PatientEmail");
                final int PatientPhone = PatientIntent.getIntExtra("PatientPhone", -1);
                final String PatientType = PatientIntent.getStringExtra("PatientType");
                final String DoctorID = etDoctorID.getText().toString();
                final String Date = ettxtDate.getText().toString();
                final String Time = editTextFromTime.getText().toString();
                if (DoctorID !=null && Date !=null && !DoctorID.isEmpty() && !Date.isEmpty()){
                    Toast.makeText(patientMyAppointment.this, "Appointment Added Successfully",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(patientMyAppointment.this, "Please Try Again",
                            Toast.LENGTH_SHORT).show();
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(patientMyAppointment.this, "Appointment Added Successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(patientMyAppointment.this, patientPanel.class);
                                patientMyAppointment.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(patientMyAppointment.this);
                                builder.setMessage("Appointment Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                PatientAppointmentRequest patientAppointmentRequest = new PatientAppointmentRequest(PatientID, PatientName, PatientPassword, PatientEmail, PatientPhone, PatientType, Date, Time, DoctorID,  responseListener);
                RequestQueue queue = Volley.newRequestQueue(patientMyAppointment.this);
                queue.add(patientAppointmentRequest);


            }
        });



    }

}

