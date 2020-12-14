package com.example.exerciselogapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Exercise.class}, version = 1, exportSchema = false)
public abstract class ExerciseRoomDatabase extends RoomDatabase {
    public abstract ExerciseDao exerciseDao();
    private static ExerciseRoomDatabase INSTANCE;

    /**
     * Creates a single instance of the database
     * @param context
     * @return INSTANCE the instance of the database
     */
    static ExerciseRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (ExerciseRoomDatabase.class) {
                if (INSTANCE == null){
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    ExerciseRoomDatabase.class,
                                    "exercise_database").build();
                }
            }
        }

        return INSTANCE;
    }
}
