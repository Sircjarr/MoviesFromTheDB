package com.example.cliff.moviesfromthedb;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cliff.moviesfromthedb.Util.SectionsPagerAdapter;

// This class will hold our fragments
// Fragments are managed with a ViewPager, TabLayout, and our SectionsPagerAdapter class

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new UpcomingFragment(), "UPCOMING");
        adapter.addFragment(new PopularFragment(), "POPULAR");
        adapter.addFragment(new NowPlayingFragment(), "NOW PLAYING");

        viewPager.setAdapter(adapter);
    }
}
