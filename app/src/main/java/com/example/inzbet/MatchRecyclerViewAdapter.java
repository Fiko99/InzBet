package com.example.inzbet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inzbet.pojo.Match;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MatchRecyclerViewAdapter extends RecyclerView.Adapter<MatchRecyclerViewAdapter.ViewHolder> {
    List<Match> MatchList;

    public MatchRecyclerViewAdapter(List<Match> matchesList) {
        this.MatchList = matchesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_component, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Match match = MatchList.get(position);
        holder.homeTeam.setText(match.getHomeTeam().getName());
        holder.time.setText(new SimpleDateFormat("dd.MM\nHH:mm", Locale.getDefault()).format(match.getUtcDate()));
        holder.awayTeam.setText(match.getAwayTeam().getName());
        holder.homeTeamBet.setText(String.format("1\n%.2f",
                match.getOdds().getHomeTeamOdd()));
        holder.drawBet.setText(String.format("X\n%.2f",
                match.getOdds().getDrawOdd()));
        holder.awayTeamBet.setText(String.format("2\n%.2f",
                match.getOdds().getAwayTeamOdd()));
        holder.homeTeamBet.setMaxLines(2);
        holder.drawBet.setMaxLines(2);
        holder.awayTeamBet.setMaxLines(2);

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
        TextView homeTeam;
        TextView time;
        TextView awayTeam;
        MaterialButtonToggleGroup materialButtonToggleGroup;
        MaterialButton homeTeamBet;
        MaterialButton drawBet;
        MaterialButton awayTeamBet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            homeTeam = itemView.findViewById(R.id.home_team);
            time = itemView.findViewById(R.id.time);
            awayTeam = itemView.findViewById(R.id.away_team);
            homeTeamBet = itemView.findViewById(R.id.home_team_bet);
            drawBet = itemView.findViewById(R.id.draw_bet);
            awayTeamBet = itemView.findViewById(R.id.away_team_bet);
            materialButtonToggleGroup = itemView.findViewById(R.id.material_group);
        }
    }
}


