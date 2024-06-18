package com.example.quizapp;

import java.util.ArrayList;
import java.util.List;

public class QuestionModel {
    private String question;
    private ArrayList<String> options;
    private String correct;

    public QuestionModel(String question, ArrayList<String> options, String correct) {
        this.question = question;
        this.options = options;
        this.correct = correct;
    }

    public QuestionModel() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }
}
