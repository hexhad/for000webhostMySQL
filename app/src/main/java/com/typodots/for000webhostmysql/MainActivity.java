package com.typodots.for000webhostmysql;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText name, email;
    private Button alter,show;

    private String sendUrl = "http://frozenice.000webhostapp.com/appCon/getData.php";
    private RequestQueue requestQueue;
    private  static  final  String TAG = MainActivity.class.getSimpleName();
    int sucess;
    private String TAG_SUCESS="sucess";
    private String TAG_MESSAGE="message";
    private String tag_json_obj="json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        alter = findViewById(R.id.button);
        show = findViewById(R.id.button2);

        requestQueue=Volley.newRequestQueue(getApplicationContext());
        alter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewData.class);
                startActivity(intent);
            }
        });

    }

    private  void sendData(){
        StringRequest request=new StringRequest(Request.Method.POST, sendUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    sucess = jobj.getInt(TAG_SUCESS);
                    if (sucess == 1) {
                        Toast.makeText(MainActivity.this, jobj.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, jobj.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "Error OC", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                Log.wtf("TAG",""+error.getMessage().toString());
            }
        }){
            public Map<String,String> getParams(){
                Map<String, String> params=new HashMap<String, String>();
                params.put("username",name.getText().toString());
                params.put("email",email.getText().toString());
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(10000,1,1.0f));
        requestQueue.add(request);

    }
}