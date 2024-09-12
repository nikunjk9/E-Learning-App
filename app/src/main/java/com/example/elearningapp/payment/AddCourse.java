package com.example.elearningapp.payment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.elearningapp.AddVideoActivity;
import com.example.elearningapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddCourse extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String COURSES = "courses";
    String[] courses = {"Web", "Frontend", "Backend", "Database", "Android", "Machine Learning"};

    Spinner spinner;
    EditText et_courseTitle, et_totalLessons;
    CardView cv_proceed;

    String courseCategory = "";
    String username = "";
    FirebaseAuth mAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        spinner = findViewById(R.id.courseSpinner);
        spinner.setOnItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Uploading Video");
        progressDialog.setCanceledOnTouchOutside(false);


        et_courseTitle = findViewById(R.id.et_courseTitle);
        et_totalLessons = findViewById(R.id.et_totalVideos);
        cv_proceed = findViewById(R.id.cv_proceed);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(ad);

        cv_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String title = et_courseTitle.getText().toString();
                String totalLessons = et_totalLessons.getText().toString();
                String timestamp = String.valueOf(System.currentTimeMillis());

                HashMap<String, String> hashMap2 = new HashMap<>();
                hashMap2.put("id", mAuth.getUid());
                hashMap2.put("title", title);
                hashMap2.put("timestamp", timestamp);
                hashMap2.put("tutor", username);
                hashMap2.put("totalLessons", totalLessons);
                hashMap2.put("category", courseCategory);

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference(AddCourse.COURSES).child(username);
                reference.child(title).setValue(hashMap2).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Intent intent = new Intent(AddCourse.this, AddVideoActivity.class);
                        intent.putExtra("title", title);
                        intent.putExtra("totalLessons", totalLessons);
                        intent.putExtra("category", courseCategory);
                        intent.putExtra("username", username);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(AddCourse.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), courses[position], Toast.LENGTH_LONG).show();
        courseCategory = courses[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}