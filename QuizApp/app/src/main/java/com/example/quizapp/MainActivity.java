package com.example.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private QuizListAdapter quizListAdapter;
    private List<QuizModel> quizModelList = new ArrayList<>();

    private ProgressBar progressBar;
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
        progressBar.setVisibility(View.GONE);
        quizListAdapter = new QuizListAdapter(quizModelList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(quizListAdapter);
    }
    private void GetDataFromFirebase(){

//        ArrayList<QuestionModel> listQuestionModel = new ArrayList<>();
//        listQuestionModel.add(new QuestionModel("What is android?", new ArrayList<>(Arrays.asList("OS", "Language", "Product" ,"None")), "OS"));
//        listQuestionModel.add(new QuestionModel("Who owns android?", new ArrayList<>(Arrays.asList("Apple", "Google", "Microsoft","Samsung")), "Google"));
//        listQuestionModel.add(new QuestionModel("Which assistant android uses?", new ArrayList<>(Arrays.asList("Siri", "Cortana", "Google Assistant","Alexa")), "Google Assistant"));
//        quizModelList.add(new QuizModel("1", "Programming", "All the basic programming", "10", listQuestionModel));
        progressBar.setVisibility(View.VISIBLE);
        FirebaseDatabase.getInstance().getReference().get().addOnSuccessListener(dataSnapshot -> {
            if(dataSnapshot.exists()){
                for (DataSnapshot childSnapshot :dataSnapshot.getChildren()) {
                    QuizModel quizModel = childSnapshot.getValue(QuizModel.class);
                    quizModelList.add(quizModel);
                }
            }
            SetUpRecyclerView();
        });
    }

    private void BindingView() {
        recyclerView = findViewById(R.id.recycler_view);
        progressBar= findViewById(R.id.progress_bar);
    }

    private void BindingAction() {
        
    }


}