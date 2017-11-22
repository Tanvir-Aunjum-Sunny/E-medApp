package com.example.e_medapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 10/1/2016.
 */

public class adminLoginRequest extends StringRequest {
    private static final String adminLogin_Request_URL = "http://minissan.comlu.com/adminLogin.php";
    private Map<String, String> params;

    public adminLoginRequest (String username, String password, Response.Listener<String> listener){
        super(Method.POST, adminLogin_Request_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
