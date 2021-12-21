package com.example.inzbet.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inzbet.MainActivity;
import com.example.inzbet.MatchRecyclerViewAdapter;
import com.example.inzbet.R;
import com.example.inzbet.RandomOddsGenerator;
import com.example.inzbet.pojo.Match;
import com.example.inzbet.pojo.Odds;
import com.example.inzbet.pojo.Root;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MatchesFragment extends Fragment {

    RecyclerView recyclerView;
    MatchRecyclerViewAdapter matchAdapter;
    Root rMatches;
    RandomOddsGenerator randomOddsGenerator;
    MainActivity ref;

    public MatchesFragment(MainActivity mainActivity) {
        this.ref = mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        if (rMatches == null) {
            HttpGetRequest hgr = new HttpGetRequest();
            hgr.execute("PL");
            try {
                hgr.get(4, TimeUnit.SECONDS);
                rMatches = hgr.allStuff;
                randomOddsGenerator = new RandomOddsGenerator();
                for (Match match : rMatches.matches) {
                    randomOddsGenerator.generateAllOdds();
                    match.setOdds(new Odds(randomOddsGenerator.getHomeOdd(),
                            randomOddsGenerator.getAwayOdd(),
                            randomOddsGenerator.getDrawOdd()));
                }
            } catch (TimeoutException e) {
                Log.e("timeout", "Za długo oczekiwano na dane.");
            } catch (Exception e) {
            }
        }
        matchAdapter = new MatchRecyclerViewAdapter(rMatches.matches);
        ref.matches = rMatches.matches;
        this.recyclerView = view.findViewById(R.id.matchRV);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.recyclerView.setAdapter(matchAdapter);

        return view;
    }
}

class HttpGetRequest extends AsyncTask<String, Void, Root> {

    public Root allStuff;
    public static String apiToken = "f6765dcdd1024189a9d257a09fe451c8";

    @Override
    protected Root doInBackground(String... params) {
        String param = "";
        HttpURLConnection con;
        try {
            for (String s : params) {
                param = s;
                break;
            }
            URL url = new URL("https://api.football-data.org/v2/competitions/" + param + "/matches?status=SCHEDULED");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("X-Auth-Token", apiToken);
            if (con.getResponseCode() == 200) {
                BufferedReader is = new BufferedReader(new InputStreamReader(con.getInputStream()));
                Gson gson = new Gson();
                allStuff = gson.fromJson(is, Root.class);
                con.disconnect();
            }
            Log.e("Json results matches", toStringAllStuff());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allStuff;
    }

    @NonNull
    private String toStringAllStuff() {
        StringBuilder sb = new StringBuilder();
        for (Match m : allStuff.matches) {
            sb.append(m.toString());
            sb.append('\n');
        }
        return sb.toString();
    }

    protected void onPostExecute(Root result) {
        super.onPostExecute(result);
    }
}
