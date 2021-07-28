package com.trivia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;

import com.trivia.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    public static SplashActivity instance;
    View view;
    public String TAG = "TAG";
    public static ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window mWindow = getWindow();
        mWindow.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);
        instance = this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(instance, WelcomeActivity.class));
                finish();
            }
        }, 2000);


    }
}