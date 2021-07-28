package com.trivia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.trivia.MyApp;
import com.trivia.database.DatabaseClient;
import com.trivia.databinding.ActivityWelcomeBinding;
import com.trivia.model.Option;
import com.trivia.model.Question;
import com.trivia.model.TempExam;

import org.json.JSONArray;
import org.json.JSONException;

public class WelcomeActivity extends AppCompatActivity {

    public static WelcomeActivity instance;
    View view;
    public String TAG = "TAG";
    public static ActivityWelcomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);
        instance = this;
        if (!MyApp.mySharedPref.getIsLoggedIn()) {
            insertGame st = new insertGame();
            st.execute();
        }


        binding.txtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!binding.edtName.getText().toString().isEmpty()){
                    startGame st = new startGame();
                    st.execute();
                }else{
                    Toast.makeText(instance, "please select enter name", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public class startGame extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... voids) {

            //adding to database
            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                    .tempDeo().deleteAll();

            TempExam tempExam = new TempExam();
            tempExam.setName(binding.edtName.getText().toString());
            MyApp.quizId = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                    .tempDeo().insert(tempExam);

            return "";

        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            startActivity(new Intent(instance, QuestionOneActivity.class));
        }
    }


    public class insertGame extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... voids) {

            try {
                JSONArray jsonArray = new JSONArray("[{\"question\":\"Who is the best cricketer in the world?\",\"optionType\":0,\"option\":[\"Sachin Tendulkar\",\"Virat Kolli\",\"Adam Gilchirst\",\"Jacques Kallis\"]},{\"question\":\"What are the colors in the Indian national flag? (multiple selection)\",\"optionType\":1,\"option\":[\"White\",\"Yellow\",\"Orange\",\"Green\"]}]");

                Question question = new Question();
                question.setOptionType(jsonArray.getJSONObject(0).getInt("optionType"));
                question.setQustion(jsonArray.getJSONObject(0).getString("question"));

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .questionDeo().insert(question);
                question = new Question();
                question = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .questionDeo().getLastId();
                JSONArray optionArray = jsonArray.getJSONObject(0).getJSONArray("option");
                for (int i = 0; i < optionArray.length(); i++) {
                    Option option = new Option();
                    option.setOption(optionArray.getString(i));
                    option.setQuestionID(question.getId());
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                            .optionDeo().insert(option);
                }


                question = new Question();
                question.setOptionType(jsonArray.getJSONObject(1).getInt("optionType"));
                question.setQustion(jsonArray.getJSONObject(1).getString("question"));

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .questionDeo().insert(question);
                question = new Question();
                question = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .questionDeo().getLastId();
                optionArray = jsonArray.getJSONObject(1).getJSONArray("option");
                for (int i = 0; i < optionArray.length(); i++) {
                    Option option = new Option();
                    option.setOption(optionArray.getString(i));
                    option.setQuestionID(question.getId());
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                            .optionDeo().insert(option);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "";

        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            MyApp.mySharedPref.setIsLoggedIn(true);
        }
    }
}