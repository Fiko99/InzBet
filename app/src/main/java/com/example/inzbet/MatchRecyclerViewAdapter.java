package com.example.inzbet;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inzbet.pojo.Match;

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

//        holder.homeTeamBet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onPressed(holder.homeTeamBet);
//            }
//        });
//
//        holder.drawBet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onPressed(holder.drawBet);
//            }
//        });
//
//        holder.awayTeamBet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onPressed(holder.awayTeamBet);
//            }
//        });

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void onPressed(Button button) {
        button.setSelected(!button.isSelected());
    }

    @Override
    public int getItemCount() {
        return MatchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView homeTeam;
        TextView time;
        TextView awayTeam;
        Button homeTeamBet;
        Button drawBet;
        Button awayTeamBet;
        Button[] btnGroup = new Button[3];
        Button btnUnfocus;
        int[] btnId = {R.id.home_team_bet, R.id.draw_bet, R.id.away_team_bet};

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            homeTeam = itemView.findViewById(R.id.home_team);
            time = itemView.findViewById(R.id.time);
            awayTeam = itemView.findViewById(R.id.away_team);
            homeTeamBet = itemView.findViewById(R.id.home_team_bet);
            drawBet = itemView.findViewById(R.id.draw_bet);
            awayTeamBet = itemView.findViewById(R.id.away_team_bet);
            for(int i = 0; i < btnGroup.length; i++){
                btnGroup[i] = itemView.findViewById(btnId[i]);
                btnGroup[i].setBackgroundColor(Color.parseColor("#ffe800"));
                btnGroup[i].setOnClickListener(this);
            }
            btnUnfocus = btnGroup[0];
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.home_team_bet:
                    setFocus(btnUnfocus, btnGroup[0]);
                    break;

                case R.id.draw_bet:
                    setFocus(btnUnfocus, btnGroup[1]);
                    break;

                case R.id.away_team_bet:
                    setFocus(btnUnfocus, btnGroup[2]);
                    break;
            }
        }

        public void setFocus(Button btnUnfocus, Button btnFocus){
            btnUnfocus.setBackgroundColor(Color.parseColor("#ffe800"));
            btnFocus.setBackgroundColor(Color.parseColor("#000000"));
            this.btnUnfocus = btnFocus;
        }
    }
}


