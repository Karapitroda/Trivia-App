package com.trivia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.trivia.adapter.HistoryAdapter;
import com.trivia.database.DatabaseClient;
import com.trivia.databinding.ActivityHistoryListBinding;
import com.trivia.model.History;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HistoryListActivity extends AppCompatActivity {
    public static HistoryListActivity instance;
    View view;
    public String TAG = "TAG";
    public static ActivityHistoryListBinding binding;
    public HistoryAdapter historyAdapter;
    public ArrayList<History> historyArrayList = new ArrayList<>();
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryListBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);
        instance = this;
        getData();
    }

    public void getData() {
        databaseWriteExecutor.execute(() -> {
            historyArrayList = (ArrayList<History>) DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                    .cartDeo().getAll();

            setData();
        });

    }

    public void setData() {
        binding.rvHistory.setLayoutManager(new LinearLayoutManager(instance));
        historyAdapter = new HistoryAdapter(instance, historyArrayList);
        binding.rvHistory.setAdapter(historyAdapter);
        historyAdapter.setOnItemClickListener(new HistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick( int position) {
                startActivity(new Intent(instance, HistoryDetailActivity.class)
                        .putExtra("obj", historyArrayList.get(position)));
            }
        });
    }

    public void onBack(View view) {
        onBackPressed();
    }
}