package com.example.inzbet.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inzbet.BetsRecyclerViewAdapter;
import com.example.inzbet.R;

public class BetsFragment extends Fragment {
    private BetsRecyclerViewAdapter betAdapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bets, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("coupons", Context.MODE_PRIVATE);
        int size = sharedPreferences.getInt("lastCouponId", 0);

        betAdapter = new BetsRecyclerViewAdapter(sharedPreferences, size);
        this.recyclerView = view.findViewById(R.id.betSummaryRV);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.recyclerView.setAdapter(betAdapter);

        return view;
    }
}
