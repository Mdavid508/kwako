package com.example.kwako;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.kwako.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class VerifyPropertyDetails extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;
    ImageView backbutton;

    public static final int TOTAL_TABS  = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_property_details);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager);


        viewPagerAdapter  = new ViewPagerAdapter(this, TOTAL_TABS, getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(viewPagerAdapter);

        // When a tab is selected
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // set current tab as selected tab
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // change fragments on swipe
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // select the tab at this scroll position
                tabLayout.getTabAt(position).select();
            }
        });
//previous button
        backbutton = findViewById(R.id.btnBack);
        backbutton.setOnClickListener(view -> {
            finish();
        });

    }
}