package com.example.e_medapp;

/**
 * Created by LENOVO on 10/30/2016.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class patientLoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://minissan.comlu.com/patientLogin.php";
    private Map<String, String> params;

    public patientLoginRequest(String PatientID, String PatientPassword, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("PatientID", PatientID);
        params.put("PatientPassword", PatientPassword);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}