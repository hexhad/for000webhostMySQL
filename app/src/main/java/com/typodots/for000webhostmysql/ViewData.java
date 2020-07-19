package com.typodots.for000webhostmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class ViewData extends AppCompatActivity {
    private TextView show_name,show_email;
    private String ExtractionDataUrl = "http://frozenice.000webhostapp.com/appCon/ExtractData.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        show_name = findViewById(R.id.name);
        show_email = findViewById(R.id.email);

        ExtractionData();
    }

    private void ExtractionData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ExtractionDataUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0 ; i<array.length();i++){
                        JSONObject data = array.getJSONObject(i);
                        String username = data.getString("username");
                        String email = data.getString("email");
                        show_name.setText(username);
                        show_email.setText(email);
                    }
                } catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}