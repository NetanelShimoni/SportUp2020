package com.example.sportup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdepter_exe extends RecyclerView.Adapter<MyAdepter_exe.ViewHolder> {
  //  Exersice[] Myexe;
    Context context;
    List<Exersice> Myexe;
    User user;

    public MyAdepter_exe(List Myexe,home_Workout activity,User name) {
        this.Myexe=new ArrayList<>();
        this.Myexe = Myexe;
        this.context = activity;
        this.user=name;
    }

    @NonNull
    @Override
    public MyAdepter_exe.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.exersice_list,parent,false);
        MyAdepter_exe.ViewHolder viewHolder = new MyAdepter_exe.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Exersice MyexeDataList = Myexe.get(position);
        holder.textViewName.setTextColor(R.color.green_dark );
        holder.textViewName.setText("Trainer Name: "+MyexeDataList.trainer_name);
        holder.textViewName1.setTextColor(R.color.green_dark );
        holder.textViewName1.setText("Trainer Phone: "+MyexeDataList.trainer_phone);
        holder.textViewName2.setText("Name of Exercise: "+MyexeDataList.name);
        holder.textViewName3.setText("Description: "+MyexeDataList.description);
        holder.textViewName0.setTextColor(R.color.green_dark );
        holder.textViewName0.setText(MyexeDataList.getName_muselce());
        String link = new String(MyexeDataList.link);
       // String s="<iframe width=\"100%\" height=\"100%\"src=\"https://www.youtube.com/embed/eWEF1Zrmdow\" frameborder=\"0\" allowfullscreen></iframe>";

        //link=link.substring(0,24)+"embed/"+link.substring(32,link.length());
       // int index=link.indexOf("/watch?v=");
       link= link.replaceFirst("watch?v=","embed/");
        String new_link="";
        for (int i = 0; i <link.length() ; i++) {
            if(link.charAt(i)=='w'&&link.charAt(i+1)=='a'){
                 new_link=link.substring(0,i);
                new_link+="embed/";
                for (int j = i+8; j <link.length() ; j++) {
                    char c = link.charAt(j);
                    new_link+=c;

                }
            }
        }

        //System.out.println("new_link="+new_link);
        String s="<iframe width=\"100%\" height=\"100%\"src=\""+new_link+"\" frameborder=\"0\" allowfullscreen></iframe>";

        holder.WebView.loadData(s,"text/html","utf-8");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });
    }



    @Override
    public int getItemCount() {
        return Myexe.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView movieImage;
        TextView textViewName;
        TextView textViewName0;
        TextView textViewName1;
        TextView textViewName2;
        TextView textViewName3;
        WebView WebView;
        Button back;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //movieImage = itemView.findViewById(R.id.imageview);
            textViewName = itemView.findViewById(R.id.name_trainer_for_found);
            textViewName1 = itemView.findViewById(R.id.City_trainer_for_found);
            textViewName2 = itemView.findViewById(R.id.exe_name);
            textViewName0 = itemView.findViewById(R.id.textName);
            textViewName3 = itemView.findViewById(R.id.text_discription);
          //  back = itemView.findViewById(R.id.button_back);
            WebView = itemView.findViewById(R.id.webView);
            WebView.getSettings().setJavaScriptEnabled(true);




        }
    }

}
