package com.example.inzbet.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.inzbet.DecimalDigitsInputFilter;
import com.example.inzbet.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountFragment extends Fragment {

    private EditText depositValue;
    private Button deposit, withdraw;
    private TextView accountBalance;
    private SharedPreferences sharedPreferences;
    private float price, previous, subtraction, sum, amount, number;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        depositValue = (EditText) view.findViewById(R.id.deposit_value);
        deposit = view.findViewById(R.id.deposit);
        accountBalance = view.findViewById(R.id.account_balance_value);
        withdraw = view.findViewById(R.id.withdraw);
        depositValue.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});

        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (depositValue.length() <= 0) {
                    depositValue.setError("To pole jest wymagane");
                    return;
                }

                price = Float.parseFloat(depositValue.getText().toString());
                previous = Float.parseFloat(accountBalance.getText().toString());
                sum = 0;
                sum = sum + previous;

                if (price > 1000000) {
                    depositValue.setError("Kwota spoza zakresu");
                    return;
                }

                accountBalance.setText(Float.toString(price + sum));
                sharedPreferences = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putFloat("number", Float.parseFloat((accountBalance.getText().toString())));
                editor.apply();

                depositValue.getText().clear();
            }
        });

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (depositValue.length() <= 0) {
                    depositValue.setError("To pole jest wymagane");
                    return;
                }

                amount = Float.parseFloat(accountBalance.getText().toString());
                price = Float.parseFloat(depositValue.getText().toString());
                subtraction = amount - price;

                if (subtraction < 0) {
                    depositValue.setError("Kwota, którą próbujesz wypłacić przekracza kwotę środków na Twoim koncie");
                    return;
                }

                accountBalance.setText(Float.toString(Float.parseFloat(String.format("%.2f", subtraction))));
                sharedPreferences = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putFloat("number", Float.parseFloat((accountBalance.getText().toString())));
                editor.apply();

                depositValue.getText().clear();
            }
        });

        update();

        return view;
    }

    private void update() {
        sharedPreferences = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        number = sharedPreferences.getFloat("number", 0);
        accountBalance.setText(String.valueOf(number));
    }
}
