package com.example.quizapp;

public class QuizModel {
    private String id;
    private String title;
    private String subTitle;
    private String time;

    public QuizModel() {
    }

    public QuizModel(String id, String title, String subTitle, String time) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.time = time;
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
