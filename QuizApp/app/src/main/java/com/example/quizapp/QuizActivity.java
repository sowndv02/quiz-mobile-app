package com.example.quizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewbinding.ViewBinding;

import com.example.quizapp.databinding.ScoreDialogBinding;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{
    public static ArrayList<QuestionModel> questionModelList;
    public static String time = "";
    private TextView questionIndicatorTextView;
    private LinearProgressIndicator questionProgressIndicator;
    private TextView questionTextView;
    private TextView timerIndicatorTextView;
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button nextBtn;
    private int currentQuestionIndex = 0;

    private String selectedAnswer = "";
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BindingView();
        BindingAction();
        LoadQuestions();
        StartTimer();
    }

    private void StartTimer() {
        long totalTimeInMilisecond = Integer.parseInt(time) * 60 * 1000L;
        new CountDownTimer(totalTimeInMilisecond, 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished/1000;
                long minutes = seconds/60;
                long remainingSeconds = seconds % 60;
                timerIndicatorTextView.setText(String.format("%02d:%02d", minutes, remainingSeconds));
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void LoadQuestions(){
        selectedAnswer = "";
        if(currentQuestionIndex == questionModelList.size()){
            FinishQuiz();
            return;
        }

        questionIndicatorTextView.setText("Question " + (currentQuestionIndex + 1) + "/" + questionModelList.size());
        questionProgressIndicator.setProgress((int)((float)currentQuestionIndex / questionModelList.size() * 100));
        questionTextView.setText(questionModelList.get(currentQuestionIndex).getQuestion());
        btn0.setText(questionModelList.get(currentQuestionIndex).getOptions().get(0));
        btn1.setText(questionModelList.get(currentQuestionIndex).getOptions().get(1));
        btn2.setText(questionModelList.get(currentQuestionIndex).getOptions().get(2));
        btn3.setText(questionModelList.get(currentQuestionIndex).getOptions().get(3));
    }

    private void BindingAction() {
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
    }

    private void BindingView() {
        questionIndicatorTextView = findViewById(R.id.question_indicator_textView);
        questionProgressIndicator = findViewById(R.id.quiz_question_progress_indicator);
        questionTextView = findViewById(R.id.question_textview);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        nextBtn = findViewById(R.id.next_btn);
        timerIndicatorTextView = findViewById(R.id.timer_indicator_textview);
    }

    @Override
    public void onClick(View v) {
        btn0.setBackgroundColor(getColor(R.color.gray));
        btn1.setBackgroundColor(getColor(R.color.gray));
        btn2.setBackgroundColor(getColor(R.color.gray));
        btn3.setBackgroundColor(getColor(R.color.gray));
        Button btnClicked = (Button)v;
        if(btnClicked.getId() == R.id.next_btn){
            if(selectedAnswer.isEmpty()){
                Toast.makeText(this, "Please answer to continue", Toast.LENGTH_SHORT).show();
                return;
            }
            if(selectedAnswer == questionModelList.get(currentQuestionIndex).getCorrect()){
                score ++;

            }
            currentQuestionIndex++;
            LoadQuestions();
        }else{
            selectedAnswer = btnClicked.getText().toString();
            btnClicked.setBackgroundColor(getColor(R.color.orange));
        }
    }

    private void FinishQuiz(){
        int totalQuestion = questionModelList.size();
        int percentage = (int)((float)score/(float)totalQuestion * 100);

        ScoreDialogBinding  dialogBinding =  ScoreDialogBinding.inflate(getLayoutInflater());

        dialogBinding.scoreProgressText.setText(percentage + " %");
        dialogBinding.scoreProgressIndicator.setProgress(percentage);
        if(percentage > 60){
            dialogBinding.scoreTitle.setText("Congrats! You have passed");
            dialogBinding.scoreTitle.setTextColor(Color.BLUE);
        }else{
            dialogBinding.scoreTitle.setText("Oops! You have failed");
            dialogBinding.scoreTitle.setTextColor(Color.RED);
        }
        dialogBinding.scoreSubtitle.setText(score + " out of " + totalQuestion + " are correct");
        dialogBinding.finishBtn.setOnClickListener(v -> finish());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogBinding.getRoot())
                .setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}