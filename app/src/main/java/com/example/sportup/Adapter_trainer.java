package com.example.sportup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
//fdfdfdfsdfsdfdf
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * this class pulls out the traienrs from the database and shw them in recycle view
 */
public class Adapter_trainer extends RecyclerView.Adapter<Adapter_trainer.ViewHolder> {
    Context context;
    List<Tranier> MyTrainers;
    User user;

    public Adapter_trainer(List MyTrainers,Find_trainer activity,User name) {
        this.MyTrainers=new ArrayList<>();
        this.MyTrainers = MyTrainers;
        this.context = activity;
        this.user=name;

    }

    public Adapter_trainer(List MyTrainers,our_Trainer activity) {
        this.MyTrainers=new ArrayList<>();
        this.MyTrainers = MyTrainers;
        this.context = activity;
        this.user=null;
    }

    @NonNull
    @Override
    public Adapter_trainer.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.trianer_list,parent,false);
        Adapter_trainer.ViewHolder viewHolder = new Adapter_trainer.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Tranier MyexeDataList =MyTrainers.get(position);
        holder.textViewName.setText("Trainer Name: "+MyexeDataList.getName());
        if(user==null && MyTrainers.get(position).isIs_verify()){
            holder.textViewacept.setText("Accept!");
        }else if(user==null){
            holder.textViewacept.setText("No Accept!");

        }
        if(this.user!=null) {
            holder.textViewName0.setText("Trainer City: " + MyexeDataList.getCity());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null) {
                    Intent i = new Intent(context, send_message_for_trainer.class);
                    Tranier move = new Tranier();
                    for (int j = 0; j < MyTrainers.size(); j++) {
                        if (MyexeDataList.getId_system().equals(MyTrainers.get(j).getId_system())) {
                            move = MyTrainers.get(j);
                            break;
                        }
                    }

                    i.putExtra("trainer", move);
                    i.putExtra("user", user);
                    context.startActivity(i);
                }
                else{ // user==null -->our_trainer ---> Admin
                    Intent i = new Intent(context,ditaiels_trainer.class);
                    Tranier move = new Tranier();
                    for (int j = 0; j < MyTrainers.size(); j++) {
                        if (MyexeDataList.getId_system().equals(MyTrainers.get(j).getId_system())) {
                            move = MyTrainers.get(j);
                            i.putExtra("trainer",move);
                            break;
                        }
                    }
                    System.out.println(move.toString());

                    context.startActivity(i);
                }



            }
        });
    }



    @Override
    public int getItemCount() {
        return MyTrainers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        TextView textViewName0;
        TextView textViewacept;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.name_trainer_for_found);
            textViewName0 = itemView.findViewById(R.id.City_trainer_for_found);
            textViewacept = itemView.findViewById(R.id.textView_accept);
            imageView = itemView.findViewById(R.id.imageView5);





        }
    }


}
