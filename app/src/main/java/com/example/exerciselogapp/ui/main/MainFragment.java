package com.example.exerciselogapp.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exerciselogapp.Exercise;
import com.example.exerciselogapp.MainActivity;
import com.example.exerciselogapp.R;

import java.util.List;
import java.util.Locale;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private ExerciseListAdapter adapter;
    private TextView exerciseID;
    private EditText exerciseName;
    private EditText numberOfReps;
    private EditText timeSpent;
    public EditText exerciseDay;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        exerciseID = getView().findViewById(R.id.userID);
        exerciseName = getView().findViewById(R.id.exerciseName);
        numberOfReps = getView().findViewById(R.id.numberOfReps);
        timeSpent = getView().findViewById(R.id.timeSpent);
        exerciseDay = getView().findViewById(R.id.exerciseDay);

        listenerSetup();
        observerSetup();
        recyclerSetup();
    }

    private void clearFields() {
        exerciseID.setText("");
        exerciseName.setText("");
        numberOfReps.setText("");
        timeSpent.setText("");
        exerciseDay.setText("");
    }

    private void listenerSetup(){
        Button addButton = getView().findViewById(R.id.addButton);
        Button findButton = getView().findViewById(R.id.findButton);
        Button deleteButton = getView().findViewById(R.id.findButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //NOTE: variable names in here cannot match the fields up above,
                //or else java will NOT recognize the text boxes.
                String name = exerciseName.getText().toString();
                String reps = numberOfReps.getText().toString();
                String time = timeSpent.getText().toString();
                String day = exerciseDay.getText().toString();

                if (!name.equals("") && !reps.equals("") && !time.equals("") && !day.equals("")){
                    //Why is it expecting 5 arguments? exerciseID should NOT register as an argument here.
                    //The example in the book does not put id in its class instance, just name and quantity.
                    Exercise exercise = new Exercise(0,name,
                            Integer.parseInt(reps),
                            time, Integer.parseInt(day));
                    mViewModel.insertExercise(exercise);
                    clearFields();
                } else {
                    exerciseID.setText("Incomplete Information.");
                }


            }
        });

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.findExercise(exerciseName.getText().toString());
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.deleteExercise(exerciseName.getText().toString());
                clearFields();
            }
        });
    }

    private void observerSetup() {
        mViewModel.getAllExercises().observe(getViewLifecycleOwner(), new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                adapter.setExerciseList(exercises);
            }
        });
        mViewModel.getSearchResults().observe(getViewLifecycleOwner(),
                new Observer<List<Exercise>>() {
                    @Override
                    public void onChanged(List<Exercise> exercises) {
                        if (exercises.size() > 0) {
                            exerciseID.setText(String.format(Locale.US, "%d",
                                    exercises.get(0).getExerciseName()));
                            exerciseName.setText(exercises.get(0).getExerciseName());
                            numberOfReps.setText(String.format(Locale.US, "%d",
                                    exercises.get(0).getNumberOfReps()));
                            timeSpent.setText(exercises.get(0).getTimeSpent());
                            exerciseDay.setText(String.format(Locale.US,"%d",
                                    exercises.get(0).getExerciseDay()));
                        } else {
                            exerciseID.setText("No match was found.");
                        }
                    }
                });
    }

    private void recyclerSetup() {
        RecyclerView recyclerView;
        adapter = new ExerciseListAdapter(R.layout.exercise_list_item);
        recyclerView = getView().findViewById(R.id.exercise_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }



}
