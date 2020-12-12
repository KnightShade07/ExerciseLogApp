package com.example.exerciselogapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercises")
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "exerciseID")
    private int exerciseID;

    @ColumnInfo(name = "exerciseName")
    private String exerciseName;
    private int numberOfReps;
    private String timeSpent; //This variable is a string instead of an int because of time quantity.
    private int exerciseDay;

    public Exercise(int exerciseID, String exerciseName, int numberOfReps, String timeSpent, int exerciseDay){
        this.exerciseID = exerciseID;
        this.exerciseName = exerciseName;
        this.numberOfReps = numberOfReps;
        this.timeSpent = timeSpent;
        this.exerciseDay = exerciseDay;
    }

    //getters

    public int getExerciseID(){
        return this.exerciseID;
    }

    public String getExerciseName(){
        return this.exerciseName;
    }

    public int getNumberOfReps(){
        return this.numberOfReps;
    }

    public String getTimeSpent(){
        return timeSpent;
    }

    public int getExerciseDay(){
        return this.exerciseDay;
    }

    //setters

    public void setExerciseID(int exerciseID){
        this.exerciseID = exerciseID;
    }

    public void setExerciseName(String exerciseName){
        this.exerciseName = exerciseName;
    }

    public void setNumberOfReps(int numberOfReps){
        this.numberOfReps = numberOfReps;
    }

    public void setTimeSpent(String timeSpent){
        this.timeSpent = timeSpent;
    }

    public void setExerciseDay(int exerciseDay){
        this.exerciseDay = exerciseDay;
    }
}
