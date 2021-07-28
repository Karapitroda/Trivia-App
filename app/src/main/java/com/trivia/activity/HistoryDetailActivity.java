package com.trivia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.trivia.databinding.ActivityHistoryDetailBinding;
import com.trivia.model.History;

public class HistoryDetailActivity extends AppCompatActivity {

    public static HistoryDetailActivity instance;
    View view;
    public String TAG = "TAG";
    public static ActivityHistoryDetailBinding binding;

    public History history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryDetailBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);
        instance = this;

        history = (History) getIntent().getSerializableExtra("obj");

        binding.txtName.setText(history.getName());
        binding.txtOptionOne.setText(history.getOptioneOne());
        binding.txtQuestionOne.setText(history.getQustionOne());
        binding.txtQuestionTwo.setText(history.getQustionTwo());
        binding.txtOptionTwo.setText(history.getOptioneTwo());

        binding.txtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(instance, WelcomeActivity.class));
                finishAffinity();
            }
        });
        binding.txtHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(instance, HistoryListActivity.class));
                finishAffinity();
            }
        });
    }

    public void onBack(View view) {
        onBackPressed();
    }
}