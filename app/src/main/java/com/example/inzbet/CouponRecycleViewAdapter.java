package com.example.inzbet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inzbet.pojo.Match;

import org.w3c.dom.Text;

import java.util.List;

public class CouponRecycleViewAdapter extends RecyclerView.Adapter<CouponRecycleViewAdapter.ViewHolder> {
    List<Match> MatchList;

    public CouponRecycleViewAdapter(List<Match> matchList) {
        this.MatchList = matchList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponRecycleViewAdapter.ViewHolder holder, int position) {

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


