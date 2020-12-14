package com.example.exerciselogapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class ExerciseRepository {
    private MutableLiveData<List<Exercise>> searchResults =
            new MutableLiveData<>();
    private LiveData<List<Exercise>> allExercises;

    private ExerciseDao exerciseDao;

    /**
     * Class responsible for handling all database actions.
     * @param application
     */

    public ExerciseRepository(Application application){
        ExerciseRoomDatabase db;
        db = ExerciseRoomDatabase.getDatabase(application);
        exerciseDao = db.exerciseDao();
        allExercises = exerciseDao.getAllExercises();
    }

    public LiveData<List<Exercise>> getAllExercises(){
        return allExercises;
    }

    public MutableLiveData<List<Exercise>> getSearchResults(){
        return searchResults;
    }

    private void asyncFinished(List<Exercise> results){
        searchResults.setValue(results);
    }

    private static class QueryAsyncTask extends
            AsyncTask<String, Void, List<Exercise>>{
        private ExerciseDao asyncTaskDao;
        private ExerciseRepository delegate = null;

        QueryAsyncTask(ExerciseDao dao){
            asyncTaskDao = dao;
        }

        @Override
        protected List<Exercise> doInBackground(final String... params){
            return asyncTaskDao.findExercise(params[0]);
        }
        @Override
        protected void onPostExecute(List<Exercise> result){
            delegate.asyncFinished(result);
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Exercise, Void, Void>{
        private ExerciseDao asyncTaskDao;
        InsertAsyncTask(ExerciseDao dao){
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Exercise...params){
            asyncTaskDao.insertExercise(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<String, Void, Void>{
        private ExerciseDao asyncTaskDao;
        DeleteAsyncTask(ExerciseDao dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected  Void doInBackground(final String...params) {
            asyncTaskDao.deleteExercise(params[0]);
            return null;
        }
    }

    public void insertExercise(Exercise newExercise){
        InsertAsyncTask task = new InsertAsyncTask(exerciseDao);
        task.execute(newExercise);
    }

    public void deleteExercise(String name){
        DeleteAsyncTask task = new DeleteAsyncTask(exerciseDao);
        task.execute(name);
    }

    public void findExercise(String name){
        QueryAsyncTask task = new QueryAsyncTask(exerciseDao);
        task.delegate = this;
        task.execute(name);
    }
}
