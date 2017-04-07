package com.android.prateek.demo.Models;

/**
 * Created by prate on 3/9/2017.
 */

public class phone_data {

    private String home;

    private String mobile;

    public phone_data(String home, String mobile) {
        this.home = home;
        this.mobile = mobile;
    }

    public String getHome ()
    {
        return home;
    }

    public void setHome (String home)
    {
        this.home = home;
    }

    public String getMobile ()
    {
        return mobile;
    }

    public void setMobile (String mobile)
    {
        this.mobile = mobile;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [home = "+home+", mobile = "+mobile+"]";
    }
}

