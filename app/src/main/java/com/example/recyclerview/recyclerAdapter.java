package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private final recyclerViewInterface recyclerViewInterface;
    Context context;
    LayoutInflater inflater;
    String [] listtext;

    public recyclerAdapter (Context context, String[]listtext, recyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.listtext = listtext;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvLv;

        public MyViewHolder(@NonNull View v, recyclerViewInterface recyclerViewInterface) {
            super(v);
            tvLv = (TextView) v.findViewById(R.id.tvLV);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_lv, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        holder.tvLv.setText(listtext[position]);
    }

    @Override
    public int getItemCount() {
        return listtext.length;
    }
}
