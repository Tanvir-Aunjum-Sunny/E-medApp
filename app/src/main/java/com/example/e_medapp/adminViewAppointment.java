package com.example.e_medapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.Serializable;
import java.util.ArrayList;

public class adminViewAppointment extends AppCompatActivity implements AsyncResponse, AdapterView.OnItemClickListener {
    final String LOG = "ListActivity";

    private ArrayList<adminPatient> userList;
    private ListView lvPatientList;
    private FunDapter<adminPatient>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_appointment);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); */

        //ImageLoader.getInstance().init(UILConfig.config(ListActivity.this));

        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(adminViewAppointment.this, this);
        taskRead.execute("http://minissan.comlu.com/adminPatient.php");

        lvPatientList = (ListView)findViewById(R.id.lvPatientList);

        registerForContextMenu(lvPatientList);
    }
    /*
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final adminPatient selectedPatient = adapter.getItem(info.position);
        Log.d(LOG, selectedPatient.PatientName);

        if(item.getItemId() == R.id.menuUpdate){
            Intent in = new Intent(adminViewAppointment.this, adminPatientDetailsActivity.class);
            in.putExtra("item", selectedPatient);
            startActivity(in);
        }
        else if (item.getItemId() == R.id.menuAppointment){
            Intent in = new Intent(adminPatientViewActivity.this, TimeSelect.class);
            in.putExtra("item", selectedPatient);
            startActivity(in);
        }

        else if(item.getItemId() == R.id.menuRemove){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Do you want to remove " + selectedPatient.PatientName + "?");
            alert.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    userList.remove(selectedPatient);
                    adapter.notifyDataSetChanged();

                    HashMap postData = new HashMap();
                    postData.put("patientNo", "" + selectedPatient.patientNo);
                    //postData.put("mobile", "android");


                    PostResponseAsyncTask taskUpdate = new PostResponseAsyncTask(adminViewAppointment.this,
                            postData, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            Log.d(LOG, s);
                            if(s.contains("success")){
                                Toast.makeText(adminViewAppointment.this, "Delete Successfully", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    taskUpdate.execute("http://minissan.comlu.com/patientRemove.php");
                }
            });
            alert.setNegativeButton("Cancel", null);
            alert.show();
        }

        return super.onContextItemSelected(item);
    }
    */

    @Override
    public void processFinish(String s) {
        userList = new JsonConverter<adminPatient>().toArrayList(s, adminPatient.class);

        BindDictionary<adminPatient> dict = new BindDictionary<adminPatient>();
        dict.addStringField(R.id.tvAdminDoctorName, new StringExtractor<adminPatient>() {
            @Override
            public String getStringValue(adminPatient item, int position) {
                return item.PatientName;
            }
        });
        dict.addStringField(R.id.tvAdminPatientDate, new StringExtractor<adminPatient>() {
            @Override
            public String getStringValue(adminPatient item, int position) {
                return item.Date;
            }
        });
        dict.addStringField(R.id.tvAdminPatientTime, new StringExtractor<adminPatient>() {
            @Override
            public String getStringValue(adminPatient item, int position) {
                return item.Time;
            }
        });

        adapter = new FunDapter<>(
                adminViewAppointment.this, userList, R.layout.layout_admin_patient_appointment_view, dict);

        lvPatientList = (ListView) findViewById(R.id.lvPatientList);
        lvPatientList.setAdapter(adapter);


         lvPatientList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        adminPatient selectedPatient = userList.get(position);
        Intent in = new Intent(adminViewAppointment.this, TimeSelect.class);
        in.putExtra("item", (Serializable) selectedPatient);
        startActivity(in);
    }

}