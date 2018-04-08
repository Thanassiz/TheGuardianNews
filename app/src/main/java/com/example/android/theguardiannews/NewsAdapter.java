package com.example.android.theguardiannews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.theguardiannews.databinding.RecyclerNewsBinding;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Thanassis on 3/4/2018.
 * In this Adapter, the layout ID is being used as the view type so that it is easier to inflate the right binding.
 * This lets the Adapter handle any number of layouts.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private final String TAG = NewsAdapter.class.getSimpleName();
    private LayoutInflater inflater;
    private Context context;
    private List<News> newsList;

    /**
     * Constructs a new {@link NewsAdapter}
     *
     * @param context  of the app
     * @param newsList is the list of news, which is the data source of the adapter
     */
    public NewsAdapter(Context context, List<News> newsList) {
        this.newsList = newsList;
        this.context = context;
    }

    /**
     * Views are created and the ViewHolder contains references to them so that the data can be set quickly.
     */
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        RecyclerNewsBinding binding = RecyclerNewsBinding.inflate(inflater, parent, false);
        return new NewsViewHolder(binding);
    }

    /**
     * The specific data is assigned (bound) to the Views.
     */
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);

        final RecyclerNewsBinding binding = holder.getBinding();
        validateViews(news, binding);

        holder.bind(news);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return newsList == null ? 0 : newsList.size();
    }


    private void validateViews(News news, RecyclerNewsBinding binding) {

        String title = news.getTitle();
        news.setTitle(title);
        binding.titleView.setText(title);

        String section = news.getSection();
        news.setSection(section);
        binding.sectionView.setText(section);

        if (news.getThumbnail().contentEquals("")) {
            hideViews(binding.thumbnail);
        } else {
            showViews(binding.thumbnail);
        }

        String author = news.getAuthor();
        news.setAuthor(author);
        binding.sectionView.setText(section);
        if (news.getAuthor().contentEquals("")) {
            hideViews(binding.authorView);
        } else {
            binding.authorView.setText(author);
            showViews(binding.authorView);
        }

        // Convert Html text to plain text
        String trail = Html.fromHtml(news.getTrail()).toString();
        news.setTrail(trail);
        binding.trailView.setText(trail);
        binding.trailView.setTextColor(context.getResources().getColor(R.color.colorAccent));

        // Convert date from timestamp to the desired date.
        String date = formatDate(news.getDate());
        news.setDate(date);
        binding.dateView.setText(date);

        //binding.notifyChange();
    }

    /** Show Views if they exist. */
    private void showViews(View view) {
        view.setVisibility(View.VISIBLE);
    }

    /** Hide Views if they don't exist. */
    private void hideViews(View view) {
        view.setVisibility(View.GONE);
    }


    /**
     * Return the formatted date string (i.e. "3-Mar-1984") from a Date object.
     */
    private String formatDate(String stringTimeStamp) {
        try {
            // Checks if the timestamp date matches the old format pattern
            if (stringTimeStamp.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}Z")) //(regex example 2016-10-24T10:52:10Z)
            {
                SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss'Z'", Locale.getDefault());
                SimpleDateFormat newFormat = new SimpleDateFormat("dd / MM / yyyy", Locale.getDefault());
                return newFormat.format(oldFormat.parse(stringTimeStamp));
            }
        } catch (Exception e) {
            Log.e(TAG,"Failed formatting the date.", e);
        }
        return stringTimeStamp;
    }

    /**
     *  Clear all data (a list of {@link News} objects)
     */
    public void clearAll() {
        newsList.clear();
        notifyDataSetChanged();
    }

    /**
     * Add  a list of {@link News}
     * @param news is the list of news, which is the data source of the adapter
     */
    public void addAll(List<News> news) {
        newsList.clear();
        newsList.addAll(news);
        notifyDataSetChanged();
    }

}
