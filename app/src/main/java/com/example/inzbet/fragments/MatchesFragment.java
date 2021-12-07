package com.example.inzbet.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
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
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        new HttpGetRequest(this).execute();
        //downloadData();
        textView = view.findViewById(R.id.textView);
        textView.setText(match.getMatchday().toString());

//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                HttpURLConnection con = null;
//                try {
//                    URL url = new URL("https://api.football-data.org/v2/matches");
//                    con = (HttpURLConnection) url.openConnection();
//                    con.setRequestProperty("Accept", "application/json");
//                    con.setRequestProperty("X-Auth-Token", "f6765dcdd1024189a9d257a09fe451c8");
//                    con.getHeaderField("Accept");
//                    con.getHeaderField("X-Auth-Token");
//                    if (con.getResponseCode() == 200) {
//                        BufferedReader is = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                        Gson gson = new Gson();
//                        match = gson.fromJson(is, Match.class);
//                        con.disconnect();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }); {
//
//        }

        return view;
    }

//    private void downloadData() {
//        HttpURLConnection con = null;
//        try {
//            URL url = new URL("https://api.football-data.org/v2/matches");
//            con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("Accept", "application/json");
//            con.setRequestProperty("X-Auth-Token", "f6765dcdd1024189a9d257a09fe451c8");
//            if (con.getResponseCode() == 200) {
//                BufferedReader is = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                Gson gson = new Gson();
//                match = gson.fromJson(is, Match.class);
//                con.disconnect();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}

class HttpGetRequest extends AsyncTask<Void, Void, Void> {

    Fragment fragment;
    Match match;

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
            con.setRequestProperty("X-Auth-Token", "f6765dcdd1024189a9d257a09fe451c8");
            System.out.println(con.getRequestProperty("X-Auth-Token"));
//            con.getHeaderField("Accept");
//            con.getHeaderField("X-Auth-Token");
            if (con.getResponseCode() == 200) {
                BufferedReader is = new BufferedReader(new InputStreamReader(con.getInputStream()));
                Gson gson = new Gson();
                match = gson.fromJson(is, Match.class);
                con.disconnect();
            }
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
