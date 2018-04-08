package com.example.android.theguardiannews;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.theguardiannews.databinding.FragmentLayoutBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link NewsFragment} subclass.
 */
public class NewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>> {

    private final String TAG = NewsFragment.class.getName();
    private FragmentLayoutBinding fragmentBinding;
    private int LOADER_ID;
    private NewsAdapter adapter;
    public static List<News> listNews;
    private static final String API_BASE_URL = "http://content.guardianapis.com/search?";
    private static final String ENDPOINT_SECTION = "section=";
    private static final String ENDPOINT_SEARCH = "q=";
    private static final String API_PAGE_SIZE_PARAMETER = "&page-size=100";
    private static final String API_MAIN_PARAMETERS = "&show-tags=contributor&show-fields=thumbnail,trailText&api-key=test";
    private static final String  TEST_URL = "https://content.guardianapis.com/search?q=deVil%20AND%20may%20AND%20Cry&show-tags=contributor&show-fields=thumbnail,trailText&api-key=test";
    // url.replaceAll(" " ,"%20");

    /**
     * Method to return a new instance of the news fragment.
     */
    public static NewsFragment newInstance(String newsSection, int fragmentPosition) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(Constants.STATE_NEWS_SECTION, newsSection);
        args.putInt(Constants.STATE_FRAGMENT_POSITION, fragmentPosition);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    /**
     * Method to get News Section Name.
     */
    public String getSection() {
        return getArguments().getString(Constants.STATE_NEWS_SECTION, "");
    }

    /**
     * Method to get News Section Name.
     */
    public int getFragmentPosition() {
        return getArguments().getInt(Constants.STATE_FRAGMENT_POSITION, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (inflater == null) {
            inflater = LayoutInflater.from(container.getContext());
        }
        fragmentBinding = FragmentLayoutBinding.inflate(inflater, container, false);
        View rootView = fragmentBinding.getRoot();
        //fragmentBinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_layout, container, false));

        // Create a new adapter that takes the list as input
        listNews = new ArrayList<>();
        adapter = new NewsAdapter(getContext(), listNews);
        // Set RecyclerView
        fragmentBinding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentBinding.recyclerview.addItemDecoration(
                new DividerItemDecoration(fragmentBinding.recyclerview.getContext(), LinearLayoutManager.VERTICAL));
        fragmentBinding.recyclerview.setAdapter(adapter);


        // If there is a network connection, fetch data
        if (checkConnectivity()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getActivity().getSupportLoaderManager();
            // Get loaderId from fragmentPosition, so that a different loaderId is assigned for each section
            LOADER_ID = getFragmentPosition();
            loaderManager.initLoader(LOADER_ID, null, this);
        } else {
            // First, hide loading indicator so error message will be visible
            fragmentBinding.loadingIndicator.setVisibility(View.GONE);
            // Update empty state with no connection error message
            fragmentBinding.emptyTextview.setText(getString(R.string.no_internet_connection));
            fragmentBinding.emptyTextview.setVisibility(View.VISIBLE);
        }

        return rootView;
    }

    private boolean checkConnectivity() {
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean hasConnection = networkInfo != null && networkInfo.isConnected();
        return hasConnection;
    }

    /**
     * onCreateLoader(), is used when the LoaderManager has determined that the loader
     * with the specified ID isn't running, So it creates a new one.
     */
    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {

        String section = getSection();
        String requestURL = "";
        String strinGAKI = "https://content.guardianapis.com/search?q=Lara Croft&show-tags=contributor&show-fields=thumbnail,trailText&api-key=test";
        if (section.equals(getString(R.string.fragment_home))) {
            requestURL = API_BASE_URL + API_MAIN_PARAMETERS;
        } else if(section.equals(getString(R.string.fragment_music))){
            requestURL = API_BASE_URL + ENDPOINT_SECTION + section.toLowerCase() + API_MAIN_PARAMETERS;
        }else if(section.equals(getString(R.string.fragment_business))){
            requestURL = API_BASE_URL + ENDPOINT_SECTION + section.toLowerCase() + API_MAIN_PARAMETERS;
        }else if(section.equals(getString(R.string.fragment_technology))){
            requestURL = API_BASE_URL + ENDPOINT_SECTION + section.toLowerCase() + API_MAIN_PARAMETERS;
        } else if(section.equals(getString(R.string.fragment_politics))){
            requestURL = API_BASE_URL + ENDPOINT_SECTION + section.toLowerCase() + API_MAIN_PARAMETERS;
        }else if(section.equals(getString(R.string.fragment_sport))){
            requestURL = API_BASE_URL + ENDPOINT_SECTION + section.toLowerCase() + API_MAIN_PARAMETERS;
        } else if(section.equals(getString(R.string.fragment_weather))){
            requestURL = API_BASE_URL + ENDPOINT_SECTION + section.toLowerCase() + API_MAIN_PARAMETERS;
        } else if(section.equals(getString(R.string.fragment_film))){
            requestURL = API_BASE_URL + ENDPOINT_SECTION + section.toLowerCase() + API_MAIN_PARAMETERS;
        } else if(section.equals(getString(R.string.fragment_money))){
            requestURL = API_BASE_URL + ENDPOINT_SECTION + section.toLowerCase() + API_MAIN_PARAMETERS;
        } else if(section.equals(getString(R.string.fragment_education))){
            requestURL = API_BASE_URL + ENDPOINT_SECTION + section.toLowerCase() + API_MAIN_PARAMETERS;
        } else if(section.equals(getString(R.string.fragment_environment))){
            requestURL = API_BASE_URL + ENDPOINT_SECTION + section.toLowerCase() + API_MAIN_PARAMETERS;
        } else if(section.equals(getString(R.string.fragment_fashion))){
            requestURL = API_BASE_URL + ENDPOINT_SECTION + section.toLowerCase() + API_MAIN_PARAMETERS;
        }


        // Create a new loader for the given URL
        return new NewsLoader(getContext(), requestURL);
    }

    /**
     * This method runs on the main UI thread after the background work has been completed.
     * This method receives as input, the return value from the loadInBackground() method.
     */
    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> news) {
        // Hide loading indicator because the data has been loaded
        fragmentBinding.loadingIndicator.setVisibility(View.GONE);
        // Set empty state text to display "No news found."
        if (news == null || news.size() == 0) {
            fragmentBinding.emptyTextview.setText(getString(R.string.no_news_found));
            fragmentBinding.emptyTextview.setVisibility(View.VISIBLE);
        } else {
            // Set empty state text when no news found
            fragmentBinding.emptyTextview.setVisibility(View.GONE);
        }
        // Clear the adapter of previous news data
        //adapter.clearAll();
        listNews.clear();
        // If there is a valid list of {@link News}s, then add them to the adapter's
        // data set. This will trigger the RecyclerView to update.
        if (news != null && !news.isEmpty()) {
            //adapter.addAll(news);
            listNews.addAll(news);
            adapter.notifyDataSetChanged();
        }
        // Bind changes
        fragmentBinding.notifyChange();
    }

    /**
     * onLoaderReset(), informs that the data from the loader is no longer valid.
     * This isn't actually a case that's going to come up with a simple loader, but the correct thing to do
     * is to remove all the news data from UI by clearing out the adapter’s data set.
     */
    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        adapter.clearAll();
        // Bind changes
        fragmentBinding.notifyChange();
    }
}
