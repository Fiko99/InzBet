package com.example.inzbet.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inzbet.Match;
import com.example.inzbet.MatchRecyclerViewAdapter;
import com.example.inzbet.Odds;
import com.example.inzbet.R;
import com.example.inzbet.RandomOddsGenerator;
import com.example.inzbet.Root;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MatchesFragment extends Fragment {

    TextView textView;
    Spinner s;
    List<String> competition = new ArrayList<>();
    RecyclerView recyclerView;
    MatchRecyclerViewAdapter matchAdapter;
    private MatchRecyclerViewAdapter.RecyclerViewClickListener listener;
    Root rMatches;
    RandomOddsGenerator randomOddsGenerator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);
//        competition.add("Premier League");
//        competition.add("Erdesive");
//        textView = view.findViewById(R.id.textView);
//        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, competition);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        s = view.findViewById(R.id.spinner1);
//        s.setAdapter(adapter);
//        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                //while (match == null);
//                if(item.equals("Premier League")) {
//                    HttpGetRequest hgr = new HttpGetRequest();
//                    hgr.execute("PL");
//                    try {
//                        hgr.get(2, TimeUnit.SECONDS);
//                        textView.setText((hgr.all_stuff.matches.get(0).getHomeTeam().getName()));
//                    } catch(TimeoutException te) {
//                        Log.e("timeout", "Za długo oczekiwano na dane.");
//                    } catch(Exception e) {}
//                }
//                if(item.equals("Erdesive"))
//                {
//                    HttpGetRequest hgr = new HttpGetRequest();
//                    hgr.execute("DED");
//                    try {
//                        hgr.get(2, TimeUnit.SECONDS);
//                        textView.setText((hgr.all_stuff.matches.get(0).getHomeTeam().getName()));
//                    } catch(TimeoutException te) {
//                        Log.e("timeout", "Za długo oczekiwano na dane.");
//                    } catch(Exception e) {}
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                new HttpGetRequest().execute();
//            }
//
//        });

        if(rMatches == null) {
            HttpGetRequest hgr = new HttpGetRequest();
            hgr.execute("PL");
            try {
                hgr.get(2, TimeUnit.SECONDS);
                rMatches = hgr.all_stuff;
                // TODO: Wygenerować oddsy
                randomOddsGenerator = new RandomOddsGenerator();
                for (Match match : rMatches.matches) {
                    randomOddsGenerator.generateAllOdds();
                    match.setOdds(new Odds(randomOddsGenerator.getHomeOdd(),
                            randomOddsGenerator.getAwayOdd(),
                            randomOddsGenerator.getDrawOdd()));
                }
            }catch(TimeoutException e) {
                Log.e("timeout", "Za długo oczekiwano na dane.");
            }catch(Exception e) { }
        }
        matchAdapter = new MatchRecyclerViewAdapter(rMatches.matches, listener);

        this.recyclerView = view.findViewById(R.id.matchRV);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.recyclerView.setAdapter(matchAdapter);

        return view;
    }
}

class HttpGetRequest extends AsyncTask<String, Void, Root> {

    public Root all_stuff;
    public static String apiToken = "f6765dcdd1024189a9d257a09fe451c8";

    @Override
    protected Root doInBackground(String... params) {
        String param = "";
        HttpURLConnection con;
        try {
            for(String s : params) {
                param = s;
                break;
            }
            URL url = new URL("https://api.football-data.org/v2/competitions/"+ param +"/matches?status=SCHEDULED");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("X-Auth-Token", apiToken);
            if (con.getResponseCode() == 200) {
                BufferedReader is = new BufferedReader(new InputStreamReader(con.getInputStream()));
                Gson gson = new Gson();
                all_stuff = gson.fromJson(is, Root.class);
                con.disconnect();
            }
            Log.e("Json results matches", all_stuff.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return all_stuff;
    }
    @NonNull
    private String toStringAllStuff() {
        StringBuilder sb = new StringBuilder();
        for (Match m : all_stuff.matches) {
            sb.append(m.toString());
            sb.append('\n');
        }
        return sb.toString();
    }

    protected void onPostExecute(Root result) {
        super.onPostExecute(result);
    }
}
