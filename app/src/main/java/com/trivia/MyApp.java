package com.trivia;


import android.os.StrictMode;

import com.trivia.utils.MySharedPref;


public class MyApp extends DarkThemeApplication {
    public final static boolean RETROFIT_SHOW_LOG = true;
    public static MyApp instance;
    public static long quizId;
    public static MySharedPref mySharedPref;

    public void onCreate() {
        super.onCreate();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            instance = this;
            mySharedPref = new MySharedPref(instance);
            Class.forName("android.os.AsyncTask");

        } catch (ClassNotFoundException e) {
        }
    }


}