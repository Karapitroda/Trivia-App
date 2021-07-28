package com.trivia.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.trivia.MyApp;
import com.trivia.R;


/**
 * Created by Karan-Pitroda on 13-07-2021.
 */

public class MySharedPref {
    public MyApp app;
    SharedPreferences shared;
    SharedPreferences.Editor et;
    private String MY_PREF_NAME;
    private Context context;

    public final String isLoggedIn = "isLoggedIn";

    public MySharedPref(Context ct) {
        this.context = ct;
        MY_PREF_NAME = context.getResources().getString(R.string.app_name);
        shared = ct.getSharedPreferences(MY_PREF_NAME, 0);
        et = shared.edit();
    }


    public String getString(final String key, final String value) {

        return shared.contains(key) ? shared.getString(key, "") : "";

    }

    public void sharedPrefClear() {

        et.clear();
        et.apply();
        et.commit();

    }



    public boolean getIsLoggedIn() {
        return shared.contains(isLoggedIn) ? shared.getBoolean(isLoggedIn, false) : false;
    }

    public void setIsLoggedIn(boolean isfirst) {
        et.putBoolean(isLoggedIn, isfirst);
        et.commit();
    }
    public void clearApp() {
        et.clear();
        et.apply();
        et.commit();
    }
}
