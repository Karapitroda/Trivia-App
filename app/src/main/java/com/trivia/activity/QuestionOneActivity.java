package com.trivia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.trivia.MyApp;
import com.trivia.adapter.OptionsOneAdapter;
import com.trivia.database.DatabaseClient;
import com.trivia.databinding.ActivityQuestionOneBinding;
import com.trivia.model.Option;
import com.trivia.model.Question;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuestionOneActivity extends AppCompatActivity {

    public static QuestionOneActivity instance;
    View view;
    public String TAG = "TAG";
    public static ActivityQuestionOneBinding binding;

    public ArrayList<Question> questionArrayList = new ArrayList<>();
    public OptionsOneAdapter optionsOneAdapter;
    public ArrayList<Option> optionList = new ArrayList<>();
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionOneBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);
        instance = this;
        getData();
    }

    public void onBack(View view) {
        onBackPressed();
    }

    public void getData() {
        databaseWriteExecutor.execute(() -> {
            questionArrayList = (ArrayList<Question>) DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                    .questionDeo().getAll();
            optionList = (ArrayList<Option>) DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                    .optionDeo().getCount(questionArrayList.get(0).getId());
            seData();
        });

    }

    public void seData() {
        binding.txtQuestion.setText(questionArrayList.get(0).getQustion());

        binding.rvOptions.setLayoutManager(new LinearLayoutManager(instance));
        optionsOneAdapter = new OptionsOneAdapter(instance, optionList);
        binding.rvOptions.setAdapter(optionsOneAdapter);
        optionsOneAdapter.setOnItemClickListener(new OptionsOneAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int type, int position) {

            }
        });
    }

    public void onNext(View view) {
        if (optionsOneAdapter.row_index > -1) {
            databaseWriteExecutor.execute(() -> {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .tempDeo().updateQueOne(questionArrayList.get(0).getQustion(), optionList.get(optionsOneAdapter.row_index).getOption(), MyApp.quizId);
                startActivity(new Intent(instance, QuestionTwoActivity.class));
            });
        } else {
            Toast.makeText(instance, "please select one ans", Toast.LENGTH_LONG).show();
        }
    }
}