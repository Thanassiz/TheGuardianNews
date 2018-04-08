package com.example.android.theguardiannews;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanassis on 5/4/2018.
 * Helper methods related to requesting and receiving news data.
 */
public class Utils {

    private static final String TAG = Utils.class.getName();
    private static Context mContext;

    /**
     * Query the URL and return a list of {@link News} objects.
     */
    public static List<News> fetchNewsData(String requestUrl) {
        // Create URL
        URL url = createUrl(requestUrl);
        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request.", e);
        }
        // Extract relevant fields from the JSON response and create a list of {@link NewsItem}s
        List<News> newsList = extractFeatureFromJson(jsonResponse);

//        // Force app to sleep, Just to test the loading indicator
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // Return the list of {@link News}
        return newsList;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == Constants.SUCCESS_RESPONSE_CODE) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(TAG, "Problem retrieving the News JSON results.", e);
        } finally {

            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static List<News> extractFeatureFromJson(String newsJSON) {
        /** If the JSON string is empty or null, then return early. */
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }
        /** Create an empty ArrayList used to add news items */
        List<News> newsList = new ArrayList<>();
        /**
         * Try to parse the JSON response string. If there's a problem with the way the JSON
         * is formatted, a JSONException exception object will be thrown.
         */
        try {
            // JSON Object for the data retrieved from API request
            JSONObject baseJsonResponse;
            // JSON results fetched
            JSONObject jsonResults;
            // Array of News Items
            JSONArray newsArray;
            //JSON object for current news item in the newsArray
            JSONObject currentNews;
            // News Title
            String newsTitle = "";
            // News Section
            String newsSection = "";
            // Published Date
            String newsDate = "";
            // Web URL of the news item
            String newsUrl = "";
            // Array of tags
            JSONArray tagsArray;
            // JSON Object for news tags
            JSONObject newsTag;
            // JSON Object for news fields
            JSONObject newsField;

            baseJsonResponse = new JSONObject(newsJSON);
            jsonResults = baseJsonResponse.getJSONObject(Constants.JSON_KEY_RESPONSE);
            if (jsonResults.has(Constants.JSON_KEY_RESULTS)) {
                newsArray = jsonResults.getJSONArray(Constants.JSON_KEY_RESULTS);
                for (int i = 0; i < newsArray.length(); i++) {
                    currentNews = newsArray.getJSONObject(i);
                    if (currentNews.has(Constants.JSON_KEY_WEB_TITLE)) {
                        newsTitle = currentNews.getString(Constants.JSON_KEY_WEB_TITLE);
                    }
                    if (currentNews.has(Constants.JSON_KEY_SECTION_NAME)) {
                        newsSection = currentNews.getString(Constants.JSON_KEY_SECTION_NAME);
                    }
                    if (currentNews.has(Constants.JSON_KEY_WEB_PUBLICATION_DATE)) {
                        newsDate = currentNews.getString(Constants.JSON_KEY_WEB_PUBLICATION_DATE);
                    }
                    if (currentNews.has(Constants.JSON_KEY_WEB_URL)) {
                        newsUrl = currentNews.getString(Constants.JSON_KEY_WEB_URL);
                    }
                    // Author of the news item - obtained from newsTags
                    String newsAuthor = "";
                    if (currentNews.has(Constants.JSON_KEY_TAGS)) {
                        tagsArray = currentNews.getJSONArray(Constants.JSON_KEY_TAGS);
                        if (tagsArray.length() > 0) {
                            for (int j = 0; j < tagsArray.length(); j++) {
                                newsTag = tagsArray.getJSONObject(j);
                                if (newsTag.has(Constants.JSON_KEY_WEB_TITLE)) {
                                    newsAuthor = newsTag.getString(Constants.JSON_KEY_WEB_TITLE);
                                }
                            }
                        }
                    }
                    // News Thumbnail (image) associated with the key "fields"
                    String newsThumbnail = "";
                    // News trail text associated with the key "fields"
                    String newsTrail = "";
                    if (currentNews.has(Constants.JSON_KEY_FIELDS)) {
                        newsField = currentNews.getJSONObject(Constants.JSON_KEY_FIELDS);
                        if (newsField.has(Constants.JSON_KEY_THUMBNAIL)) {
                            newsThumbnail = newsField.getString(Constants.JSON_KEY_THUMBNAIL);
                        }
                        if (newsField.has(Constants.JSON_KEY_TRAIL_TEXT)) {
                            newsTrail = newsField.getString(Constants.JSON_KEY_TRAIL_TEXT);
                        }
                    }
                    News news = new News(newsTitle, newsSection, newsAuthor, newsDate, newsUrl, newsThumbnail, newsTrail);
                    newsList.add(news);
                }
            }

        } catch (JSONException e) {
            Log.e(TAG, "Problem parsing the news JSON results", e);
        }
        return newsList;
    }

}
