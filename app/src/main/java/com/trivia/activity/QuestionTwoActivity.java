package com.trivia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.trivia.MyApp;
import com.trivia.adapter.OptionsTwoAdapter;
import com.trivia.database.DatabaseClient;
import com.trivia.databinding.ActivityQuestionTwoBinding;
import com.trivia.model.History;
import com.trivia.model.Option;
import com.trivia.model.Question;
import com.trivia.model.TempExam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuestionTwoActivity extends AppCompatActivity {
    public static QuestionTwoActivity instance;
    View view;
    public String TAG = "TAG";
    public static ActivityQuestionTwoBinding binding;
    public OptionsTwoAdapter optionsTwoAdapter;

    public ArrayList<Question> questionArrayList = new ArrayList<>();
    public OptionsTwoAdapter optionsOneAdapter;
    public ArrayList<Option> optionList = new ArrayList<>();
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionTwoBinding.inflate(getLayoutInflater());
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
                    .optionDeo().getCount(questionArrayList.get(1).getId());
            seData();
        });

    }

    public void seData() {
        binding.txtQuestion.setText(questionArrayList.get(1).getQustion());

        binding.rvOptions.setLayoutManager(new LinearLayoutManager(instance));
        optionsOneAdapter = new OptionsTwoAdapter(instance, optionList);
        binding.rvOptions.setAdapter(optionsOneAdapter);
        optionsOneAdapter.setOnItemClickListener(new OptionsTwoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int type, int position) {

            }
        });
    }

    public void onNext(View view) {
        if (optionsOneAdapter.selctedArray.size() < 1) {
            Toast.makeText(instance, "please select morethaan 1", Toast.LENGTH_LONG).show();
        } else {
            ArrayList<String> selectedAns = new ArrayList<>();
            for (Option obj : optionsOneAdapter.selctedArray) {
                selectedAns.add(obj.getOption());
            }
            databaseWriteExecutor.execute(() -> {
                  TempExam tempExam =
                        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                                .tempDeo().getTour(MyApp.quizId);

                History history =new History();
                history.setDate(getTodayDateFormeted());
                history.setName(tempExam.getName());
                history.setOptioneOne(tempExam.getOptioneOne());
                history.setQustionOne(tempExam.getQustionOne());
                history.setQustionTwo(questionArrayList.get(1).getQustion());
                history.setOptioneTwo(TextUtils.join(",", selectedAns));

                         DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                                .cartDeo().insert(history);


                startActivity(new Intent(instance, HistoryDetailActivity.class)
                .putExtra("obj",history));
            });
        }
    }
    public static String getTodayDateFormeted() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm");
        String formattedDate = df.format(c);
        return formattedDate;
    }
}