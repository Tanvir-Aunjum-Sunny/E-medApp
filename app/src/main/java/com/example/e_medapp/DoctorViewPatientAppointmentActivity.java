package com.example.e_medapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;

public class DoctorViewPatientAppointmentActivity extends AppCompatActivity implements AsyncResponse/*, AdapterView.OnItemClickListener */{
    final String LOG = "ListActivity";

    private ArrayList<adminPatient> userList;
    private ListView lvPatientList;
    private FunDapter<adminPatient>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_patient_appointment);

        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(DoctorViewPatientAppointmentActivity.this, this);
        taskRead.execute("http://minissan.comlu.com/adminPatient.php");

        lvPatientList = (ListView)findViewById(R.id.lvPatientList);

        registerForContextMenu(lvPatientList);
    }

    @Override
    public void processFinish(String s) {
        userList = new JsonConverter<adminPatient>().toArrayList(s, adminPatient.class);

        BindDictionary<adminPatient> dict = new BindDictionary<adminPatient>();
        dict.addStringField(R.id.tvDoctorPatientID, new StringExtractor<adminPatient>() {
            @Override
            public String getStringValue(adminPatient item, int position) {
                return item.DoctorID;
            }
        });
        dict.addStringField(R.id.tvDoctorPatientDate, new StringExtractor<adminPatient>() {
            @Override
            public String getStringValue(adminPatient item, int position) {
                return item.Date;
            }
        });
        dict.addStringField(R.id.tvDoctorPatientTime, new StringExtractor<adminPatient>() {
            @Override
            public String getStringValue(adminPatient item, int position) {
                return item.Time;
            }
        });
      /*  dict.addStringField(R.id.tvAdminPatientTime, new StringExtractor<adminPatient>() {
            @Override
            public String getStringValue(adminPatient item, int position) {
                return item.Time;
            }
        }); */

        adapter = new FunDapter<>(
                DoctorViewPatientAppointmentActivity.this, userList, R.layout.layout_doctor_patient_view, dict);

        lvPatientList = (ListView) findViewById(R.id.lvPatientList);
        lvPatientList.setAdapter(adapter);


        // lvPatientList.setOnItemClickListener(this);
    }
 /*
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        adminPatient selectedPatient = userList.get(position);
        Intent in = new Intent(adminPatientViewActivity.this, adminPatientDetailsActivity.class);
        in.putExtra("item", (Serializable) selectedPatient);
        startActivity(in);
    } */

}



