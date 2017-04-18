package com.android.prateek.demo;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.prateek.demo.Adapter.ContactAdapter;
import com.android.prateek.demo.Adapter.PhoneAdapter;
import com.android.prateek.demo.Models.Contact_data;
import com.android.prateek.demo.Models.phone_data;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.android.prateek.demo.R.string.error;

public class popup_verify extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<Contact_data> cList = new ArrayList<>();


    private ListView listView;
    private ContactAdapter adapter;

    LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_verify);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .8));
        listView = (ListView) findViewById(R.id.list_pop);
        adapter = new ContactAdapter(popup_verify.this,cList);


        // Creating volley request obj
        JsonArrayRequest cReq = new JsonArrayRequest("http://api.androidhive.info/volley/person_array.json", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject obj = response.getJSONObject(i);
                        phone_data pho_data = new phone_data("home","mobile");


                        Contact_data contact_data = new Contact_data(pho_data,"name","email");
                        //pho_data.setHome(obj.getString("home"));
                        //pho_data.setMobile(obj.getString("mobile"));
                        //contact_data.setPhone(pho_data);

                        contact_data.setName(obj.getString("name"));
                        contact_data.setEmail(obj.getString("email"));




                        cList.add(contact_data);

                        listView.setAdapter(adapter);




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // notifying list adapter about data changes
                    // so that it renders the list view with updated data
                    adapter.notifyDataSetChanged();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(cReq);

    }


}
