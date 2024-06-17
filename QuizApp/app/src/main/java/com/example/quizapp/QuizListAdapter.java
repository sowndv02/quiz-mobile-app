package com.example.quizapp;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.MyViewHolder> {
    private List<QuizModel> quizList;

    public QuizListAdapter(List<QuizModel> quizList) {
        this.quizList = quizList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quiz_item_recycler_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        QuizModel quiz = quizList.get(position);
        holder.titleTextView.setText(quiz.getTitle());
        holder.subTitleTextView.setText(quiz.getSubTitle());
        holder.timeTextView.setText(quiz.getTime() + " min");
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView subTitleTextView;
        public TextView timeTextView;

        public MyViewHolder(View view) {
            super(view);
            titleTextView = view.findViewById(R.id.quiz_title_text);
            subTitleTextView = view.findViewById(R.id.quiz_subtitle_text);
            timeTextView = view.findViewById(R.id.quiz_time_text);
        }
    }
}
