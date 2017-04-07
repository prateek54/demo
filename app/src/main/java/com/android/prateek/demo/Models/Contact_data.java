package com.android.prateek.demo.Models;

/**
 * Created by prate on 1/25/2017.
 */

public class Contact_data {

    private phone_data phone;

    private String email;

    private String name;

    public Contact_data(phone_data phone, String email, String name) {
        this.phone = phone;
        this.email = email;
        this.name = name;
    }

    public phone_data getPhone ()
    {
        return phone;
    }

    public void setPhone (phone_data phone)
    {
        this.phone = phone;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [phone = "+phone+", email = "+email+", name = "+name+"]";
    }
}



