package com.example.inzbet.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.inzbet.Match;
import com.example.inzbet.R;
import com.google.gson.Gson;

import org.apache.http.params.HttpConnectionParams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatchesFragment extends Fragment {

    TextView textView;
    Match match;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);
        new HttpGetRequest(this).execute();
        textView = view.findViewById(R.id.textView);
        textView.setText(match.getMatchday().toString());

        return view;
    }
}

class HttpGetRequest extends AsyncTask<Void, Void, Void> {

    Fragment fragment;
    Match match;
    String apiToken = "f6765dcdd1024189a9d257a09fe451c8";

    public HttpGetRequest(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected Void doInBackground(Void... params) {
        HttpURLConnection con = null;
        try {
            URL url = new URL("https://api.football-data.org/v2/matches");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("X-Auth-Token", apiToken);
            if (con.getResponseCode() == 200) {
                BufferedReader is = new BufferedReader(new InputStreamReader(con.getInputStream()));
                Gson gson = new Gson();
                match = gson.fromJson(is, Match.class);
                con.disconnect();
            }
            Log.e("Info", con.getRequestProperty("X-Auth-Token"));
            Log.e("Info", match.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }
}
