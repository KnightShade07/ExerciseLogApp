package com.example.exerciselogapp.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.exerciselogapp.Exercise;
import com.example.exerciselogapp.ExerciseRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private ExerciseRepository repository;
    private LiveData<List<Exercise>> allExercises;
    private MutableLiveData<List<Exercise>> searchResults;

    public MainViewModel(Application application){
        super(application);
        repository = new ExerciseRepository(application);
        allExercises = repository.getAllExercises();
        searchResults = repository.getSearchResults();
    }

    MutableLiveData<List<Exercise>> getSearchResults(){
        return searchResults;
    }

    LiveData<List<Exercise>> getAllExercises(){
        return allExercises;
    }

    public void insertExercise(Exercise exercise){
        repository.insertExercise(exercise);
    }

    public void findExercise(String name){
        repository.findExercise(name);
    }

    public void deleteExercise(String name){
        repository.deleteExercise(name);
    }


}