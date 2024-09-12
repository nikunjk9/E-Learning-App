package com.example.elearningapp.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningapp.AdapterCourseListAdapter;
import com.example.elearningapp.ModelCourse;
import com.example.elearningapp.R;
import com.example.elearningapp.payment.AddCourse;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminDashboard extends AppCompatActivity {

    private ArrayList<ModelCourse> videoArrayList;
    private AdapterCourseListAdapter adapterVideo;
    private RecyclerView videosRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_page);

        videosRv = findViewById(R.id.courseRv);

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
                        videoArrayList.add(modelVideo);
                    }
                }
                adapterVideo = new AdapterCourseListAdapter(AdminDashboard.this, videoArrayList);
                videosRv.setAdapter(adapterVideo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}