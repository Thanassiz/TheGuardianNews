package com.example.android.theguardiannews;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Thanassis on 3/4/2018.
 * /**
 * An {@link News} object contains information related to a single news.
 */

public class News {
    /**
     * Title of the article
     */
    private String title;

    /**
     * Section name of the article
     */
    private String section;

    /**
     * Author name in the article
     */
    private String author;

    /**
     * Web publication date of the article
     */
    private String date;

    /**
     * Website URL of the article
     */
    private String url;

    /**
     * Thumbnail of the article
     */
    private String thumbnail;

    /**
     * TrailText of the article with string type (html)
     */
    private String trail;

    /**
     * Public constructor of {@link News} object.
     *
     * @param title     is the title of the article
     * @param section   is the section name of the article
     * @param author    is author name in article
     * @param date      is the web publication date of the article
     * @param url       is the website URL to find more details about the article
     * @param thumbnail is the thumbnail of the article
     * @param trail     is trail text of the article with string type (html)
     */
    public News(String title, String section, String author, String date, String url, String thumbnail, String trail) {
        this.title = title;
        this.section = section;
        this.author = author;
        this.date = date;
        this.url = url;
        this.thumbnail = thumbnail;
        this.trail = trail;
    }


    /**
     * Returns the title of the article
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the section of the article
     */
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    /**
     * Returns the author of the article
     */
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Returns the date of the article
     */
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns the URL of the article
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the thumbnail of the article
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * Returns the trail text of the article
     */
    public String getTrail() {
        return trail;
    }

    public void setTrail(String trail) {
        this.trail = trail;
    }

    /**
     * Sets the thumbnail to the imageView (Databinding) if thumbnail exists.
     */
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView view, String imageUrl) {
        if (!imageUrl.equals("")) {
            Picasso.get().load(imageUrl).into(view);
        }
        // Log.e("Thumbnail", "Empty string path (no thumbnail found)");
    }

    }

