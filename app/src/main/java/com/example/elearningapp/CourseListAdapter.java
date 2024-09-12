package com.example.elearningapp;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.viewHolder> {

    private Context context;
    private ArrayList<ModelCourse> videoArrayList;

    public CourseListAdapter(Context context, ArrayList<ModelCourse> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_user_page_fourteen, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ModelCourse modelVideo = videoArrayList.get(position);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(modelVideo.getTimestamp()));
        String formattedDate = DateFormat.format("dd/MM/yyyy", calendar).toString();

        holder.tv_title.setText(modelVideo.getTitle());
        holder.tv_time.setText(formattedDate);
        holder.tv_category.setText(modelVideo.getCategory());
        holder.tv_totalLessons.setText(modelVideo.getTotalLessons() + " Lessons");
        holder.tv_tutor.setText(modelVideo.getTutor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayCourse.class);
                intent.putExtra("tutor", modelVideo.getTutor());
                intent.putExtra("courseTitle", modelVideo.getTitle());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoArrayList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {

        TextView tv_title, tv_time, tv_totalLessons, tv_category, tv_tutor;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.titleTv);
            tv_time = itemView.findViewById(R.id.timeTv);
            tv_totalLessons = itemView.findViewById(R.id.totlaLessonsTv);
            tv_category = itemView.findViewById(R.id.categoryTv);
            tv_tutor = itemView.findViewById(R.id.tutorTv);

        }
    }
}
