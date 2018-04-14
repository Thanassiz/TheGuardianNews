package com.example.android.theguardiannews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Thanassis on 5/4/2018.
 * Fragment to display list of news items with more dynamic ViewPager items.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    private final String TAG = CategoryAdapter.class.getSimpleName();
    private List<NewsFragment> fragmentList;
    private List<String> tabTitleList;
    private Context context;


    public CategoryAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
        this.fragmentList = new LinkedList<>();
        this.fragmentList = new LinkedList<NewsFragment>(Arrays.asList(
                NewsFragment.newInstance(context.getString(R.string.fragment_home), Constants.HOME),
                NewsFragment.newInstance(context.getString(R.string.fragment_music), Constants.MUSIC),
                NewsFragment.newInstance(context.getString(R.string.fragment_business), Constants.BUSINESS),
                NewsFragment.newInstance(context.getString(R.string.fragment_technology), Constants.TECHNOLOGY),
                NewsFragment.newInstance(context.getString(R.string.fragment_politics), Constants.POLITICS),
                NewsFragment.newInstance(context.getString(R.string.fragment_sport), Constants.SPORTS),
                NewsFragment.newInstance(context.getString(R.string.fragment_weather), Constants.WEATHER),
                NewsFragment.newInstance(context.getString(R.string.fragment_film), Constants.FILM),
                NewsFragment.newInstance(context.getString(R.string.fragment_money), Constants.MONEY),
                NewsFragment.newInstance(context.getString(R.string.fragment_education), Constants.EDUCATION),
                NewsFragment.newInstance(context.getString(R.string.fragment_environment), Constants.ENVIRONMENT),
                NewsFragment.newInstance(context.getString(R.string.fragment_fashion), Constants.FASHION)));
        this.tabTitleList = new ArrayList<>();
        tabTitleList.add(context.getString(R.string.fragment_home));
        tabTitleList.add(context.getString(R.string.fragment_music));
        tabTitleList.add(context.getString(R.string.fragment_business));
        tabTitleList.add(context.getString(R.string.fragment_technology));
        tabTitleList.add(context.getString(R.string.fragment_politics));
        tabTitleList.add(context.getString(R.string.fragment_sport) + "s");
        tabTitleList.add(context.getString(R.string.fragment_weather));
        tabTitleList.add(context.getString(R.string.fragment_film) + "s");
        tabTitleList.add(context.getString(R.string.fragment_money));
        tabTitleList.add(context.getString(R.string.fragment_education));
        tabTitleList.add(context.getString(R.string.fragment_environment));
        tabTitleList.add(context.getString(R.string.fragment_fashion));
    }

    /**
     * Returns the fragment to display for that page.
     */
    @Override
    public Fragment getItem(int position) {

        if (position >= 0 && position < fragmentList.size()) {
            NewsFragment newsFragment = fragmentList.get(position);
            return newsFragment;
        } else {
            return null;
        }
    }

    @Override
    public int getItemPosition(Object object) {
    /*    if( !fragmentList.contains( object) ) {
            Log.e(TAG, "NO IDEA WHY POSITION IS NONE: " + POSITION_NONE);
            return POSITION_NONE;
        } else {
            Log.e(TAG, "POSITION UNCHANGED: " + POSITION_NONE);
            return POSITION_UNCHANGED;
        }*/

        int index = fragmentList.indexOf (object);
        if (index == -1) {
            Log.e(TAG, "index is:  " + POSITION_NONE);
            return POSITION_NONE;
        } else {
            Log.e(TAG, "POSITION UNCHANGED: " + index);
            return index;
        }
    }

    /**
     * Returns total number of pages.
     */
    @Override
    public int getCount() {
        return fragmentList.size();
    }

    /**
     * Returns the page title for the top indicator.
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (position >= 0 && position < tabTitleList.size()) {
            String tabTitle = tabTitleList.get(position);
            return tabTitle;
        } else {
            return null;
        }
    }

    public void addFragment(NewsFragment fragment) {
        int position = fragment.getFragmentPosition();
        Log.e(TAG, "addedFragment position = " + position);
        if( fragment != null ) {
            fragmentList.add(fragment);
            tabTitleList.add(context.getString(R.string.fragment_search));
            notifyDataSetChanged();
        }
        else {
            Log.e(TAG, "Failed to add fragment.");
        }
    }

    public void removeFragment(NewsFragment fragment){

        int position = fragment.getFragmentPosition();
        Log.e(TAG, "Fragment will be removed from position: " + position);
        if( position >= 0 && position < fragmentList.size() && fragmentList.size() > 1 ){
            fragmentList.remove(position);
            tabTitleList.remove(position);
            notifyDataSetChanged();
        } else {
            Log.e(TAG, "Failed to remove fragment");
        }
    }

    public void replaceFragment(NewsFragment fragment){
        int position = fragment.getFragmentPosition();
        Log.e(TAG, " THIS NEW SEARCH FRAGMENT POSITION IS: " + position + " FRAGMENTLIST SIZE = " + fragmentList.size());
        if( position >= 0 && position < fragmentList.size())  {
            fragmentList.remove(position);
            tabTitleList.remove(position);
            fragmentList.add(fragment);
            tabTitleList.add(context.getString(R.string.fragment_search));
            notifyDataSetChanged();
        }
        else {
            Log.e(TAG, "Failed to replace fragment.");
        }
    }

    public List<String> getTabTitleList() {
        return tabTitleList;
    }

    public List<NewsFragment> getFragmentList() {
        return fragmentList;
    }
}
