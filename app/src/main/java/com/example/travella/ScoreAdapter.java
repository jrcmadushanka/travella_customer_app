package com.example.travella;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

public class ScoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<String> items1;
    private ArrayList<String> score;


    ScoreAdapter(Context context, ArrayList<String> items1, ArrayList<String> score) {
        this.context = context;
        this.items1 = items1;
        this.score = score;
        }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.activity_custom_row_old, parent, false);
        return new Item(row);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        ((Item) holder).title1.setText(items1.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( context,  TokenGenerator.class);
                intent.putExtra("token", score.get(position));
                startActivity(context, intent, null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items1.size();
    }

    public class Item extends RecyclerView.ViewHolder {

        TextView title1;

        Item(View itemView) {
            super(itemView);
            title1 = itemView.findViewById(R.id.title1);
        }
    }

}
