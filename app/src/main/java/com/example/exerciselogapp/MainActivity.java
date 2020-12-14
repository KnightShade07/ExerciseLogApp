package com.example.exerciselogapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.exerciselogapp.ui.main.ExerciseListAdapter;
import com.example.exerciselogapp.ui.main.MainFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }

        //recyclerView = findViewById(R.id.exercise_recycler);
        /**The book, in chapter 49, tells us to have this setup code in OnCreate,
           but why do we need to do it here when the recyclerSetup() method in the
           MainFragment.java class basically does the same thing?
         **/

        /**layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ExerciseListAdapter();
        recyclerView.setAdapter(adapter);
         **/


    }
}