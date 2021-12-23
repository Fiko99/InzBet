package com.example.inzbet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inzbet.pojo.Match;

import java.util.List;

public class CouponRecyclerViewAdapter extends RecyclerView.Adapter<CouponRecyclerViewAdapter.ViewHolder> {
    List<Match> MatchList;

    public CouponRecyclerViewAdapter(List<Match> matchList) {
        this.MatchList = matchList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponRecyclerViewAdapter.ViewHolder holder, int position) {
        Match match = MatchList.get(position);
        holder.cHomeTeam.setText(match.getHomeTeam().getName());
        holder.cAwayTeam.setText(match.getAwayTeam().getName());
        holder.type.setText(match.getType());
        if (match.getType().equals("1"))
            holder.odd.setText(String.format("%.2f", match.getOdds().getHomeTeamOdd()));
        if (match.getType().equals("X"))
            holder.odd.setText(String.format("%.2f", match.getOdds().getDrawOdd()));
        if (match.getType().equals("2"))
            holder.odd.setText(String.format("%.2f", match.getOdds().getAwayTeamOdd()));

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return MatchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cHomeTeam;
        TextView cAwayTeam;
        TextView separator;
        TextView type;
        TextView odd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cHomeTeam = itemView.findViewById(R.id.c_home_team);
            cAwayTeam = itemView.findViewById(R.id.c_away_team);
            separator = itemView.findViewById(R.id.separator);
            type = itemView.findViewById(R.id.type);
            odd = itemView.findViewById(R.id.c_odd);
        }
    }
}


