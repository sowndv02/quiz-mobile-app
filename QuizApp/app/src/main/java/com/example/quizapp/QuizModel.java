package com.example.quizapp;

import java.util.ArrayList;
import java.util.List;

public class QuizModel {
    private String id;
    private String title;
    private String subTitle;
    private String time;

    private ArrayList<QuestionModel> questionList;

    public QuizModel() {
    }

    public QuizModel(String id, String title, String subTitle, String time, ArrayList<QuestionModel> questionList) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.time = time;
        this.questionList = questionList;
    }

    public ArrayList<QuestionModel> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<QuestionModel> questionList) {
        this.questionList = questionList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
