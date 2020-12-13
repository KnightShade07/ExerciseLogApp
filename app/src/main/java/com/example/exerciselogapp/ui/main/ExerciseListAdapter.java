package com.example.exerciselogapp.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exerciselogapp.Exercise;
import com.example.exerciselogapp.R;

import java.util.List;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ViewHolder> {
   private int exerciseItemLayout;
   private List<Exercise> exerciseList;

   public ExerciseListAdapter(int layoutID) {
       exerciseItemLayout = layoutID;
   }

   public void setExerciseList(List<Exercise> exercises) {
       exerciseList = exercises;
       notifyDataSetChanged();
   }

   @Override
    public int getItemCount(){
       return exerciseList == null ? 0 : exerciseList.size();
   }

   @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(
               parent.getContext()).inflate(exerciseItemLayout, parent, false);
       return new ViewHolder(view);
   }

   @NonNull
   @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
       TextView item = holder.item;
       item.setText(exerciseList.get(listPosition).getExerciseName());
   }

   static class ViewHolder extends RecyclerView.ViewHolder {
       TextView item;
       ViewHolder(View itemView){
           super(itemView);
           item = itemView.findViewById(R.id.exercise_row);
       }
   }
}
