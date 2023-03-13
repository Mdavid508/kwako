package com.example.kwako.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.kwako.fragments.UnverifiedPropertyDetails;
import com.example.kwako.fragments.VerifiedPropertyDetails;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private Context context;
    private int totalTabs;
    public ViewPagerAdapter(Context context, int totalTabs, @NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        /**
         * Load the fragment based on the current scroll position
         */
        switch (position){
            case 0 : {
                // first tab (Verified Property)
                VerifiedPropertyDetails verifiedP = new VerifiedPropertyDetails();
                return verifiedP;
            }
            case 1 : {
                // second tab (Unverified Property Details)
                UnverifiedPropertyDetails unverifiedP =  new UnverifiedPropertyDetails();
                return  unverifiedP;
            }
            default:{
                // no fragment matched
                return  null;
            }
        }
    }

    @Override
    public int getItemCount() {
        return totalTabs;
    }
}
