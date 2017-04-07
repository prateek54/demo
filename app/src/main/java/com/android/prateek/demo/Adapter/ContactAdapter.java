package com.android.prateek.demo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.android.prateek.demo.Models.Contact_data;
import com.android.prateek.demo.Models.phone_data;
import com.android.prateek.demo.R;

import java.util.ArrayList;



/**
 * Created by prate on 1/25/2017.
 */

public class ContactAdapter extends BaseAdapter {

    Activity activity;
    private LayoutInflater inflater;
    private ArrayList<Contact_data> contactItems;

    public ContactAdapter(Activity activity,ArrayList<Contact_data> contactItems) {
        this.activity=activity;
        this.contactItems = contactItems;
    }

    TextView name;
    TextView email;
    TextView home;
    TextView mobile;



    @Override
    public int getCount() {
        return contactItems.size();
    }

    @Override
    public Object getItem(int location) {
        return contactItems.get(location) ;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //final View view = activity.getLayoutInflater().inflate(R.layout.list_row, null);

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);


        name = (TextView) convertView.findViewById(R.id.name_lay);
        email = (TextView) convertView.findViewById(R.id.email_lay);
        home = (TextView) convertView.findViewById(R.id.home_lay);
        mobile = (TextView) convertView.findViewById(R.id.mobile_lay);

        Contact_data contact_data = contactItems.get(position);


        //name
        name.setText(contact_data.getName());
        //email
        email.setText(contact_data.getEmail());
        //home
        home.setText(contact_data.getPhone().getHome());
        //mobile
        mobile.setText(contact_data.getPhone().getMobile());

        return convertView;

    }
}
