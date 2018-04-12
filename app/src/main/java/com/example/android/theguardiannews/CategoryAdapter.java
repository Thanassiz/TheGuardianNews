package com.example.android.theguardiannews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Thanassis on 5/4/2018.
 * Fragment to display list of news items with more dynamic ViewPager items.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    private static  String TAG = CategoryAdapter.class.getSimpleName();
    private static int NUM_PAGES = 12;
    private Context context;


    public CategoryAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    /**
     * Returns the fragment to display for that page.
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case Constants.HOME:
                return NewsFragment.newInstance(context.getString(R.string.fragment_home), Constants.HOME);
            case Constants.MUSIC:
                return NewsFragment.newInstance(context.getString(R.string.fragment_music), Constants.MUSIC);
            case Constants.BUSINESS:
                return NewsFragment.newInstance(context.getString(R.string.fragment_business), Constants.BUSINESS);
            case Constants.TECHNOLOGY:
                return NewsFragment.newInstance(context.getString(R.string.fragment_technology), Constants.TECHNOLOGY);
            case Constants.POLITICS:
                return NewsFragment.newInstance(context.getString(R.string.fragment_politics), Constants.POLITICS);
            case Constants.SPORTS:
                return NewsFragment.newInstance(context.getString(R.string.fragment_sport), Constants.SPORTS);
            case Constants.WEATHER:
                return NewsFragment.newInstance(context.getString(R.string.fragment_weather), Constants.WEATHER);
            case Constants.FILM:
                return NewsFragment.newInstance(context.getString(R.string.fragment_film), Constants.FILM);
            case Constants.MONEY:
                return NewsFragment.newInstance(context.getString(R.string.fragment_money), Constants.MONEY);
            case Constants.EDUCATION:
                return NewsFragment.newInstance(context.getString(R.string.fragment_education), Constants.EDUCATION);
            case Constants.ENVIRONMENT:
                return NewsFragment.newInstance(context.getString(R.string.fragment_environment), Constants.ENVIRONMENT);
            case Constants.FASHION:
                return NewsFragment.newInstance(context.getString(R.string.fragment_fashion), Constants.FASHION);
            case 13:
                return NewsFragment.newInstance("Search", 13);
            default:
                return null;
        }
    }

    /**
     * Returns total number of pages.
     */
    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    /**
     *  Returns the page title for the top indicator.
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case Constants.HOME:
                return context.getString(R.string.fragment_home);
            case Constants.MUSIC:
                return context.getString(R.string.fragment_music);
            case Constants.BUSINESS:
                return context.getString(R.string.fragment_business);
            case Constants.TECHNOLOGY:
                return context.getString(R.string.fragment_technology);
            case Constants.POLITICS:
                return context.getString(R.string.fragment_politics);
            case Constants.SPORTS:
                return context.getString(R.string.fragment_sport)+"s";
            case Constants.WEATHER:
                return context.getString(R.string.fragment_weather);
            case Constants.FILM:
                return context.getString(R.string.fragment_film)+"s";
            case Constants.MONEY:
                return context.getString(R.string.fragment_money);
            case Constants.EDUCATION:
                return context.getString(R.string.fragment_education);
            case Constants.ENVIRONMENT:
                return context.getString(R.string.fragment_environment);
            case Constants.FASHION:
                return context.getString(R.string.fragment_fashion);
            case 13:
                return "Search Results";
            default:
                return null;
        }
    }

}
