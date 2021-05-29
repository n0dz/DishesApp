package com.nodz.dishapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nodz.dishapp.Model.DishModel;
import com.nodz.dishapp.R;

import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.MyViewHolder> {

    Context context;
    List<DishModel> dishData;

    public DishAdapter(Context context, List<DishModel> dishData) {
        this.context = context;
        this.dishData = dishData;
    }

    public DishAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(context);
        v = inflater.inflate(R.layout.dish_layout, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DishAdapter.MyViewHolder holder, int position) {

        //holder.dishdetails.setText(dishData.get(position).getDetails());
        holder.dishname.setText(dishData.get(position).getName());

        Glide.with(context)
                .load(dishData.get(position).getImg()).override(120,120)
                .into(holder.dishimage);


    }

    @Override
    public int getItemCount() {return dishData.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView dishname;
        TextView dishdetails;
        ImageView dishimage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            dishname = itemView.findViewById(R.id.dishname);
            //dishdetails = itemView.findViewById(R.id.dish_about);
            dishimage = itemView.findViewById(R.id.dish_image);

        }
    }

}
