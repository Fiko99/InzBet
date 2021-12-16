package com.example.inzbet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MatchRecyclerViewAdapter extends RecyclerView.Adapter<MatchRecyclerViewAdapter.ViewHolder> {
    List<Match> MatchList;
    private RecyclerViewClickListener listener;

    public MatchRecyclerViewAdapter(List<Match> matchesList, RecyclerViewClickListener listener) {
        this.MatchList = matchesList;
        this.listener = listener;
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
        // TODO: generator liczb losowych i połączneie go z tym 1, X, 2
        holder.homeTeamBet.setText(String.format("1\n%.2f",
                match.getOdds().getHomeTeamOdd()));
        holder.drawBet.setText(String.format("X\n%.2f",
                match.getOdds().getDrawOdd()));
        holder.awayTeamBet.setText(String.format("2\n%.2f",
                match.getOdds().getAwayTeamOdd()));

    }

    @Override
    public int getItemCount() {
        return MatchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView homeTeam;
        TextView time;
        TextView awayTeam;
        Button homeTeamBet;
        Button drawBet;
        Button awayTeamBet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            homeTeam = itemView.findViewById(R.id.home_team);
            time = itemView.findViewById(R.id.time);
            awayTeam = itemView.findViewById(R.id.away_team);
            homeTeamBet = itemView.findViewById(R.id.home_team_bet);
            drawBet = itemView.findViewById(R.id.draw_bet);
            awayTeamBet = itemView.findViewById(R.id.away_team_bet);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View itemView) {
            listener.onClick(itemView, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener
    {
        void onClick(View v, int position);
    }
}


