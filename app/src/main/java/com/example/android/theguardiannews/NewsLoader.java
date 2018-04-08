package com.example.android.theguardiannews;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by Thanassis on 7/4/2018.
 * Loads a list of {@link News}  by using an AsyncTask to perform the
 * network request in a background thread.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private static final String TAG = NewsLoader.class.getSimpleName();

    /** Query URL */
    private String url;

    /**
     * Constructor for a new {@link NewsLoader} object.
     */
    public NewsLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    /**
     * Perfrom this task in a background thread.
     */
    @Override
    public List<News> loadInBackground() {
        if (url == null){
            return null;
        }
        // Perform the network request, parse the response, and extract a list of News.
        List<News> news = Utils.fetchNewsData(url);
        return news;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        // Force an asynchronous load.
        forceLoad();
    }
}
