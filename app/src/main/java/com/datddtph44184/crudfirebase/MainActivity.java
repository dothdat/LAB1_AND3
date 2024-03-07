package com.datddtph44184.crudfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}