package com.example.inzbet;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BetsRecyclerViewAdapter extends RecyclerView.Adapter<BetsRecyclerViewAdapter.ViewHolder> {
    private SharedPreferences sharedPreferences;
    private int size;

    public BetsRecyclerViewAdapter(SharedPreferences sharedPreferences, int size) {
        this.sharedPreferences = sharedPreferences;
        this.size = size;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bet_summary, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        float a = sharedPreferences.getFloat("odd" + position, 0);
        float b = sharedPreferences.getFloat("win" + position, 0);
        float c = sharedPreferences.getFloat("rate" + position, 0);
        holder.totalOdd.setText(String.valueOf(a));
        holder.win.setText(String.valueOf(b));
        holder.contribution.setText(String.valueOf(c));
    }

    @Override
    public int getItemCount() {
        return size;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView totalOdd;
        TextView contribution;
        TextView win;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            totalOdd = itemView.findViewById(R.id.odd_h);
            contribution = itemView.findViewById(R.id.contribution);
            win = itemView.findViewById(R.id.b_win);
        }
    }
}
