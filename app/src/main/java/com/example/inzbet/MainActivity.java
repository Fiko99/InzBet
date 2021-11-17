package com.example.inzbet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.inzbet.fragments.AccountFragment;
import com.example.inzbet.fragments.BetsFragment;
import com.example.inzbet.fragments.HomeFragment;
import com.example.inzbet.fragments.MatchesFragment;
import com.example.inzbet.fragments.NewBetFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public FloatingActionButton floatingActionButton;
    public BottomNavigationView bottomNavigationView;
    public ImageButton person;
    public ImageButton cancel;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.fab_1);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        person = findViewById(R.id.person_icon);
        textView = findViewById(R.id.text_view_title);
        cancel = findViewById(R.id.cancel_icon);

        cancel.setVisibility(View.GONE);

        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_wrapper, new AccountFragment())
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
//                Fragment f = new NewBetFragment();
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
//                        .replace(R.id.fl_wrapper, f, "tag")
//                        .addToBackStack(null)
//                        .commit();
                openNewBetActivity();

//                bottomNavigationView.setVisibility(View.GONE);
//                floatingActionButton.setVisibility(View.GONE);
//                textView.setVisibility(View.GONE);
//                cancel.setVisibility(View.VISIBLE);
            }
        });

//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .setCustomAnimations( R.anim.slide_in_right, R.anim.slide_out_right)
//                        .replace(R.id.fl_wrapper, new MatchesFragment())
//                        .commit();
//                //getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                cancel.setVisibility(View.GONE);
//                textView.setVisibility(View.VISIBLE);
//                bottomNavigationView.setVisibility(View.VISIBLE);
//                floatingActionButton.setVisibility(View.VISIBLE);
//            }
//        });

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, new HomeFragment()).commit();
    }

    // obsługa BottomNavigationView
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            selectedFragment = new HomeFragment();
                            setComponentsAttributes();
                            break;
                        case R.id.navigation_matches:
                            selectedFragment = new MatchesFragment();
                            setComponentsAttributes();
                            break;
                        case R.id.navigation_bets:
                            selectedFragment = new BetsFragment();
                            setComponentsAttributes();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, selectedFragment).commit();

                    return true;
                }
            };

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
        if(getSupportFragmentManager().findFragmentByTag("tag") != null) {
            return;
        }

        int count = getSupportFragmentManager().getBackStackEntryCount();
        if(count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            setComponentsAttributes();
        }
    }

    public void openNewBetActivity()
    {
        Intent intent = new Intent(this, NewBetActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}