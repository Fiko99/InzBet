package com.example.inzbet.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatchesFragment extends Fragment {

    TextView textView;
    Match match /*= new Match()*/;
    Spinner s;
    List<String> competition = new ArrayList<>();
    private MatchesFragment fragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);
        new HttpGetRequest().execute();
        competition.add("Premier League");
        competition.add("Erdesive");
        textView = view.findViewById(R.id.textView);
        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, competition);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s = view.findViewById(R.id.spinner1);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                while (match == null);
                if(item.equals("Premier League")) {
                    textView.setText((match.getCompetition().getName()));
                    Log.e("Info", match.getCompetition().getName());
                }
                if(item.equals("Erdesive"))
                {
                    textView.setText((match.getCompetition().getName()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                new HttpGetRequest().execute();
            }

        });
        //textView.setText(match.getMatchday().toString());

        return view;
    }
}

class HttpGetRequest extends AsyncTask<Void, Void, Void> {

    private MatchesFragment fragment;
    Match match;
    public static String apiToken = "f6765dcdd1024189a9d257a09fe451c8";

//    public HttpGetRequest(MatchesFragment fragment) {
//        this.fragment = fragment;
//    }

    @Override
    protected Void doInBackground(Void... params) {
        HttpURLConnection con;
        try {
            URL url = new URL("https://api.football-data.org/v2/competitions/PL/matches");
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
