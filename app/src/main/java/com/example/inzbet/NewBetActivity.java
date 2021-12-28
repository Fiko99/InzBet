package com.example.inzbet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inzbet.pojo.Match;

import java.util.ArrayList;

public class NewBetActivity extends AppCompatActivity {

    private ImageButton cancel;
    private TextView oddsSum, win;
    private EditText rate;
    private Button placeBet;
    private RecyclerView recyclerView;
    private ArrayList<Match> rMatches;
    private CouponRecyclerViewAdapter couponAdapter;
    private float totalOdd, odd, rateValue, accountBalance;
    private SharedPreferences sharedPreferences, prefsValues;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rMatches = getIntent().getParcelableArrayListExtra("matches");
        setContentView(R.layout.activity_new_bet);

        cancel = findViewById(R.id.cancel_icon);
        oddsSum = findViewById(R.id.odd);
        rate = findViewById(R.id.rate);
        win = findViewById(R.id.win);
        placeBet = findViewById(R.id.place_bet);
        rate.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});
        placeBet.setEnabled(false);

        rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (rate.length() <= 0) {
                    rate.setError("Minimalna stawka to 1 zł");
                    placeBet.setEnabled(false);
                    return;
                }

                rateValue = Float.parseFloat(rate.getText().toString());

                if (rateValue < 1.0f) {
                    rate.setError("Minimalna stawka to 1 zł");
                } else {
                    win.setText(String.valueOf(Math.round((odd * rateValue * 0.88f) * 100) / 100f));
                    placeBet.setEnabled(!rMatches.isEmpty());
                }

                if (s.toString().length() == 1 && s.toString().startsWith("0")) {
                    s.clear();
                }

            }
        });

        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        accountBalance = sharedPreferences.getFloat("number", 0);

        prefsValues = getSharedPreferences("coupons", Context.MODE_PRIVATE);

        placeBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rateValue > Float.parseFloat(String.valueOf(accountBalance)))
                    Toast.makeText(getApplicationContext(), "Brak środków na koncie", Toast.LENGTH_LONG).show();
                else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putFloat("number", accountBalance - rateValue);
                    editor.apply();
                    rMatches.clear();
                    couponAdapter.notifyDataSetChanged();
                    if (rMatches.isEmpty())
                        placeBet.setEnabled(false);

                    int lastCouponId = prefsValues.getInt("lastCouponId", -1);
                    SharedPreferences.Editor editor1 = prefsValues.edit();
                    editor1.putFloat("odd" + lastCouponId, odd);
                    editor1.putFloat("win" + lastCouponId, Float.parseFloat(win.getText().toString()));
                    editor1.putFloat("rate" + lastCouponId, rateValue);
                    editor1.putInt("lastCouponId", lastCouponId + 1);
                    editor1.apply();
                }
            }
        });

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

        if (rMatches.isEmpty())
            totalOdd = 0;
        else
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
        oddsSum.setText(String.valueOf(Math.round(totalOdd * 100) / 100f));
        odd = Float.parseFloat(oddsSum.getText().toString());
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