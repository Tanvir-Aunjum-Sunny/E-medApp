package com.example.e_medapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;
import java.util.HashMap;

public class adminDoctorViewActivity extends AppCompatActivity implements AsyncResponse/*, AdapterView.OnItemClickListener */{
    final String LOG = "ListActivity";

    private ArrayList<adminDoctor> userList;
    private ListView lvDoctorList;
    private FunDapter<adminDoctor>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_doctor_view);
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

        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(adminDoctorViewActivity.this, this);
        taskRead.execute("http://minissan.comlu.com/adminDoctor.php");

        lvDoctorList = (ListView)findViewById(R.id.lvDoctorList);

        registerForContextMenu(lvDoctorList);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final adminDoctor selectedDoctor = adapter.getItem(info.position);
        Log.d(LOG, selectedDoctor.DoctorID);

        if(item.getItemId() == R.id.menuUpdate){
            Intent in = new Intent(adminDoctorViewActivity.this, adminDoctorDetailsActivity.class);
            in.putExtra("item", selectedDoctor);
            startActivity(in);
        }
      /*  else if (item.getItemId() == R.id.menuAppointment){
            Intent in = new Intent(adminPatientViewActivity.this, TimeSelect.class);
            in.putExtra("item", selectedPatient);
            startActivity(in);
        } */

        else if(item.getItemId() == R.id.menuRemove){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Do you want to remove " + selectedDoctor.DoctorID + "?");
            alert.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    userList.remove(selectedDoctor);
                    adapter.notifyDataSetChanged();

                    HashMap postData = new HashMap();
                    postData.put("DoctorNo", "" + selectedDoctor.DoctorNo);
                    //postData.put("mobile", "android");


                    PostResponseAsyncTask taskUpdate = new PostResponseAsyncTask(adminDoctorViewActivity.this,
                            postData, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            Log.d(LOG, s);
                            if(s.contains("success")){
                                Toast.makeText(adminDoctorViewActivity.this, "Delete Successfully", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    taskUpdate.execute("http://minissan.comlu.com/doctorRemove.php");
                }
            });
            alert.setNegativeButton("Cancel", null);
            alert.show();
        }

        return super.onContextItemSelected(item);
    }


    @Override
    public void processFinish(String s) {
        userList = new JsonConverter<adminDoctor>().toArrayList(s, adminDoctor.class);

        BindDictionary<adminDoctor> dict = new BindDictionary<adminDoctor>();
        dict.addStringField(R.id.tvAdminDoctorName, new StringExtractor<adminDoctor>() {
            @Override
            public String getStringValue(adminDoctor item, int position) {
                return item.DoctorID
                        ;
            }
        });
        dict.addStringField(R.id.tvAdminDoctorID, new StringExtractor<adminDoctor>() {
            @Override
            public String getStringValue(adminDoctor item, int position) {
                return item.DoctorType;
            }
        });
      /*  dict.addStringField(R.id.tvAdminPatientTime, new StringExtractor<adminDoctor>() {
            @Override
            public String getStringValue(adminDoctor item, int position) {
                return item.Time;
            }
        }); */

        adapter = new FunDapter<>(
                adminDoctorViewActivity.this, userList, R.layout.layout_admin_doctor_view, dict);

        lvDoctorList = (ListView) findViewById(R.id.lvDoctorList);
        lvDoctorList.setAdapter(adapter);


        // lvPatientList.setOnItemClickListener(this);
    }
 /*
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        adminDoctor selectedPatient = userList.get(position);
        Intent in = new Intent(adminPatientViewActivity.this, adminPatientDetailsActivity.class);
        in.putExtra("item", (Serializable) selectedPatient);
        startActivity(in);
    } */

}



