package com.example.cliff.moviesfromthedb.Util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/* This class will use a List<Fragment> and List<String> to manage our fragments and their titles

 ----------------FragmentPagerAdapter
 saves fragments in memory for speed
 Navigating back to fragment will retrieve that fragments state in memory
 USAGE: Few fragments with not alot of heavy data
 EXAMPLE: 3 tabs with bitmaps

 --------------FragmentStatePagerAdapter
 best for paging across a collection of objects for which the number of pages is undetermined.
 Destroy fragments as they are switched
 USAGE: Many fragments
 EXAMPLE: book reader app will not load all the pages at once
*/

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
