package com.example.drestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.drestaurant.Fragments.CartFragment;
import com.example.drestaurant.Fragments.HomeFragment;
import com.example.drestaurant.Fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new HomeFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.cart_nav:
                    fragment = new CartFragment();
                    loadFragment(fragment);

                    return true;
                case R.id.home_nav:
                    fragment = new HomeFragment();
                    loadFragment(fragment);

                    return true;
                case R.id.profile_nav:
                    fragment = new ProfileFragment();
                    loadFragment(fragment);

                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}
