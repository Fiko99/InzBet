package com.example.inzbet;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inzbet.pojo.Match;

import java.util.ArrayList;

public class NewBetActivity extends AppCompatActivity {

    private ImageButton cancel;
    private TextView oddsSum;
    private RecyclerView recyclerView;
    private ArrayList<Match> rMatches;
    private CouponRecyclerViewAdapter couponAdapter;
    private float totalOdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rMatches = getIntent().getParcelableArrayListExtra("matches");
        setContentView(R.layout.activity_new_bet);

        cancel = findViewById(R.id.cancel_icon);
        oddsSum = findViewById(R.id.odd);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        couponAdapter = new CouponRecyclerViewAdapter(rMatches);

        this.recyclerView = findViewById(R.id.couponRV);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.recyclerView.setAdapter(couponAdapter);

        totalOdd = 1;
        for (Match m : rMatches) {
            if (m.getType().equals("1")) {
                totalOdd *= Math.round(m.getOdds().getHomeTeamOdd() * 100) / 100f;
            }
            if (m.getType().equals("X")) {
                totalOdd *= Math.round(m.getOdds().getDrawOdd() * 100) / 100f;
            }
            if (m.getType().equals("2")) {
                totalOdd *= Math.round(m.getOdds().getAwayTeamOdd() * 100) / 100f;
            }
        }
        oddsSum.setText(String.format("%.2f", totalOdd));
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
    }

}