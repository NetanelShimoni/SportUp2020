package com.example.sportup;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * this class imports all the images and all mucels that the trainer can upload
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    MyMuscles[] MyMuscles;
    Context context;
    Tranier t;

    public MyAdapter(MyMuscles[] MyMuscles,trainer_Home activity,Tranier t) {
        this.MyMuscles = MyMuscles;
        this.context = activity;
        this.t=t;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.masuels_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MyMuscles MyMusclesDataList = MyMuscles[position];
        holder.textViewName.setText(MyMusclesDataList.getMusclesName());
        holder.movieImage.setImageResource(MyMusclesDataList.getMusclesImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, MyMusclesDataList.getMusclesName(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, trainer_add_Exersice.class);
                i.putExtra("trainer", t);
                i.putExtra("id_m",position);
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return MyMuscles.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView movieImage;
        TextView textViewName;
        TextView textViewDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.imageview);
            textViewName = itemView.findViewById(R.id.textName);

        }
    }

}