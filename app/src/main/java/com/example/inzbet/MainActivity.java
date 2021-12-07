package com.example.inzbet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.inzbet.fragments.AccountFragment;
import com.example.inzbet.fragments.BetsFragment;
import com.example.inzbet.fragments.HomeFragment;
import com.example.inzbet.fragments.MatchesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public FloatingActionButton floatingActionButton;
    public BottomNavigationView bottomNavigationView;
    public ImageButton person;
    public ImageButton cancel;
    public TextView textView;
    public AccountFragment accountFragment = new AccountFragment();
    public HomeFragment homeFragment = new HomeFragment();
    public MatchesFragment matchesFragment = new MatchesFragment();
    public BetsFragment betsFragment = new BetsFragment();

//    private Match match;
//    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //downloadData();

        floatingActionButton = findViewById(R.id.fab_1);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        person = findViewById(R.id.person_icon);
        textView = findViewById(R.id.text_view_title);
        cancel = findViewById(R.id.cancel_icon);
        //textView2 = findViewById(R.id.textView);

        cancel.setVisibility(View.GONE);

        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_wrapper, accountFragment)
                        .addToBackStack(null)
                        .commit();
                floatingActionButton.setVisibility(View.GONE);
                bottomNavigationView.setVisibility(View.VISIBLE);
                person.setBackgroundResource(R.drawable.ic_baseline_person_24);
                cancel.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewBetActivity();
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, homeFragment).commit();
    }

    // obsługa BottomNavigationView
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            selectedFragment = homeFragment;
                            setComponentsAttributes();
                            break;
                        case R.id.navigation_matches:
                            selectedFragment = matchesFragment;
                            setComponentsAttributes();
//                            downloadData();
//                            textView2.setText((match.getHomeTeam().toString()));
                            break;
                        case R.id.navigation_bets:
                            selectedFragment = betsFragment;
                            setComponentsAttributes();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, selectedFragment).commit();

                    return true;
                }
            };

//    private void downloadData() {
//        HttpURLConnection con = null;
//        try {
//            URL url = new URL("https://api.football-data.org/v2/matches");
//            con = (HttpURLConnection) url.openConnection();
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

    // ustawienie widoczności floatingActionButton oraz tła ikony "person"
    public void setComponentsAttributes() {
        floatingActionButton.setVisibility(View.VISIBLE);
        bottomNavigationView.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        person.setBackgroundResource(R.drawable.ic_baseline_person_outline_24);
        cancel.setVisibility(View.GONE);
    }

    //obsługa przycisku powrotu
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag("tag") != null) {
            return;
        }

        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            setComponentsAttributes();
        }
    }

    public void openNewBetActivity() {
        Intent intent = new Intent(this, NewBetActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}

