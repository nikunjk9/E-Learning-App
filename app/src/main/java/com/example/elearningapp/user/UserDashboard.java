package com.example.elearningapp.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningapp.CourseListAdapter;
import com.example.elearningapp.ModelCourse;
import com.example.elearningapp.R;
import com.example.elearningapp.payment.AddCourse;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity {

    private ArrayList<ModelCourse> videoArrayList;
    private CourseListAdapter adapterVideo;
    private RecyclerView videosRv;

    String category = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page_twelve);

        videosRv = findViewById(R.id.courseRv);

        Intent intent = getIntent();
        category = intent.getStringExtra(UserRealDashboard.CATEGORY);

        loadCourseList();

    }

    private void loadCourseList() {
        videoArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(AddCourse.COURSES);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    for (DataSnapshot dataSnapshot : ds.getChildren()) {
                        ModelCourse modelVideo = dataSnapshot.getValue(ModelCourse.class);
                        if (modelVideo.getCategory().equals(category)) {
                            videoArrayList.add(modelVideo);
                        }
                    }
                }
                adapterVideo = new CourseListAdapter(UserDashboard.this, videoArrayList);
                videosRv.setAdapter(adapterVideo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}