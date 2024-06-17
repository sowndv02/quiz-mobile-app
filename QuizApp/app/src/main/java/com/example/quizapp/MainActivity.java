package com.example.quizapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private QuizListAdapter quizListAdapter;
    private List<QuizModel> quizModelList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BindingView();
        BindingAction();
        GetDataFromFirebase();
    }


    private void SetUpRecyclerView(){
        quizListAdapter = new QuizListAdapter(quizModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(quizListAdapter);
    }
    private void GetDataFromFirebase(){
        quizModelList.add(new QuizModel("1", "Programming", "All the basic programming", "10"));
        quizModelList.add(new QuizModel("2", "Computer", "All the computer question", "20"));
        quizModelList.add(new QuizModel("3", "Geography", "Boost your geographic knowledge", "15"));
        SetUpRecyclerView();
    }

    private void BindingView() {
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void BindingAction() {
        
    }


}