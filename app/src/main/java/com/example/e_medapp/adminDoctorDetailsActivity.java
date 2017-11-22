package com.example.e_medapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.Calendar;
import java.util.HashMap;

public class adminDoctorDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    final String LOG = "DetailActivity";
    EditText etID, etName, etEmail, etPhone, etType;
    Button btnUpdate;
    private adminDoctor AdminDoctor;
    Calendar  myCalendar;
    //ImageView ivImage;
  /*  private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etDate.setText(sdf.format(myCalendar.getTime()));
    }
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail_doctor);
       // myCalendar= Calendar.getInstance();
      //  etDate = (EditText)findViewById(R.id.etAdminPatientDate);
      //  etTime = (EditText)findViewById(R.id.etAdminPatientTime);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); */

        //ImageLoader.getInstance().init(UILConfig.config(DetailActivity.this));

      /*  etTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(adminPatientDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        etTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }

        });

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

        etDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(adminPatientDetailsActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
     */

        AdminDoctor = (adminDoctor) getIntent().getSerializableExtra("item");

        etID = (EditText)findViewById(R.id.etAdminPatientID);
        etName = (EditText)findViewById(R.id.etAdminPatientName);
        etEmail = (EditText)findViewById(R.id.etAdminPatientEmail);
        etPhone = (EditText)findViewById(R.id.etAdminPatientPhone);
        etType = (EditText)findViewById(R.id.etAdminPatientType);

       // etID = (EditText)findViewById(R.id.etAdminPatientDoctor);

        if(AdminDoctor != null){
            etID.setText(AdminDoctor.DoctorID);
            etName.setText(AdminDoctor.DoctorName);
            etEmail.setText(AdminDoctor.DoctorEmail);
            etPhone.setText(""+AdminDoctor.DoctorPhone);
            etType.setText(AdminDoctor.DoctorType);
            //etDate.setText(AdminPatient.Date);
            //etTime.setText(AdminPatient.Time);
            //etDoctorID.setText(AdminPatient.DoctorID);
            Log.d(LOG, "DetailActivity");
            // ImageLoader.getInstance().displayImage(product.image_url, ivImage);
        }

        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        HashMap postData = new HashMap();
        postData.put("DoctorNo", "" + AdminDoctor.DoctorNo);
        postData.put("DoctorID", etID.getText().toString());
        postData.put("DoctorName", etName.getText().toString());
        postData.put("DoctorPassword", AdminDoctor.DoctorPassword);
        postData.put("DoctorEmail", etEmail.getText().toString());
        postData.put("DoctorPhone", etPhone.getText().toString());
        postData.put("DoctorType", etType.getText().toString());
      //  postData.put("Date", etDate.getText().toString());
      //  postData.put("Time", etTime.getText().toString());
      //  postData.put("DoctorID", etDoctorID.getText().toString());
        postData.put("mobile", "android");


        PostResponseAsyncTask taskUpdate = new PostResponseAsyncTask(adminDoctorDetailsActivity.this,
                postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d(LOG, s);
                if(s.contains("success")){
                    Toast.makeText(adminDoctorDetailsActivity.this, "Update Successfully", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(adminDoctorDetailsActivity.this, adminDoctorViewActivity.class);
                    startActivity(in);
                }
            }
        });
        taskUpdate.execute("http://minissan.comlu.com/doctorUpdate.php");
    }

}