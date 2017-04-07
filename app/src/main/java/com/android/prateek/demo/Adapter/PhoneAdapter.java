package com.android.prateek.demo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.prateek.demo.Models.phone_data;
import com.android.prateek.demo.R;

import java.util.ArrayList;

/**
 * Created by prate on 3/10/2017.
 */

public class PhoneAdapter extends BaseAdapter {

    Activity activity;
    private LayoutInflater inflater;
    private ArrayList<phone_data> phoneItems;


    public PhoneAdapter(Activity activity, ArrayList<phone_data> contactItems) {
        this.activity=activity;
        this.phoneItems = contactItems;
    }


    TextView home;
    TextView mobile;



    @Override
    public int getCount() {
        return phoneItems.size();
    }

    @Override
    public Object getItem(int location) {
        return phoneItems.get(location) ;
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



        home = (TextView) convertView.findViewById(R.id.home_lay);
        mobile = (TextView) convertView.findViewById(R.id.mobile_lay);

        phone_data pho_data = phoneItems.get(position);


        //home
        home.setText(pho_data.getHome());
        //mobile
        mobile.setText(pho_data.getMobile());

        return convertView;

    }
}
