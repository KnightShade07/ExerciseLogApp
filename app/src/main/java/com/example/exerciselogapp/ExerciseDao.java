package com.example.exerciselogapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
/**
 *  Class where database commands are created.
 */
public interface ExerciseDao {
    @Insert
    void insertExercise(Exercise exercise);

    @Query("SELECT * FROM exercises WHERE exerciseName = :exerciseName")
    List<Exercise> findExercise(String exerciseName);

    @Query("DELETE FROM exercises WHERE exerciseName = :exerciseName")
    void deleteExercise(String exerciseName);

    @Query("SELECT * FROM exercises")
    LiveData<List<Exercise>> getAllExercises();
}
