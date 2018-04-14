package com.example.android.theguardiannews;

/**
 * Created by Thanassis on 5/4/2018.
 * Store Constants for the NewsFeed app.
 */

public final class Constants {

    private Constants() {
    /** Create a private constructor because no one should ever create a {@link Constants} object. */
    }

    /** Constants value for each fragment */
    static final int HOME = 0;
    static final int MUSIC = 1;
    static final int BUSINESS = 2;
    static final int TECHNOLOGY = 3;
    static final int POLITICS = 4;
    static final int SPORTS = 5;
    static final int WEATHER = 6;
    static final int FILM = 7;
    static final int MONEY = 8;
    static final int EDUCATION = 9;
    static final int ENVIRONMENT = 10;
    static final int FASHION = 11;
    static final int SEARCH = 12;

    /**  Extract the key associated with the JSONObject */
    static final String JSON_KEY_RESPONSE = "response";
    static final String JSON_KEY_RESULTS = "results";
    static final String JSON_KEY_WEB_TITLE = "webTitle"; // same key used for news title and author name
    static final String JSON_KEY_SECTION_NAME = "sectionName";
    static final String JSON_KEY_WEB_PUBLICATION_DATE = "webPublicationDate";
    static final String JSON_KEY_WEB_URL = "webUrl";
    static final String JSON_KEY_TAGS = "tags";
    static final String JSON_KEY_FIELDS = "fields";
    static final String JSON_KEY_THUMBNAIL = "thumbnail";
    static final String JSON_KEY_TRAIL_TEXT = "trailText";

    /** HTTP response code when getResponse is called */
    static final int SUCCESS_RESPONSE_CODE = 200;
    static final int BAD_REQUEST_RESPONSE_CODE = 400;
    static final int SERVICE_UNAVAILABLE_RESPONSE_CODE = 503;

    /** Constants value for Bundle Arguments */
    static final String STATE_NEWS_SECTION = "section";
    static final String STATE_FRAGMENT_POSITION = "fragmentPosition";

}
