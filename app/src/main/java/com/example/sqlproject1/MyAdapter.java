package com.example.sqlproject1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlproject1.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    static Context context;
    static ArrayList<Integer> ids;
    static ArrayList<String> uname;
    static ArrayList<String> uphone;
    LayoutInflater layoutInflater;

    public MyAdapter(Context context, ArrayList<Integer> ids, ArrayList<String> uname, ArrayList<String> uphone){
        this.context = context;
        this.ids = ids;
        this.uname = uname;
        this.uphone = uphone;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_itme, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.names.setText(String.valueOf(uname.get(position)));
        holder.numbers.setText(String.valueOf(uphone.get(position)));
        holder.itemView.setTag(ids.get(position));
    }

    @Override
    public int getItemCount() {
        return uname.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView names, numbers;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            names = itemView.findViewById(R.id.textView);
            numbers = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MainActivity3.class);
                    intent.putExtra("id", (Integer) view.getTag());
                    intent.putExtra("names", uname.get(getAdapterPosition()));
                    intent.putExtra("numbers", uphone.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
