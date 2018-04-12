package com.example.android.theguardiannews;

import android.support.v7.widget.RecyclerView;

import com.example.android.theguardiannews.databinding.RecyclerNewsBinding;

/**
 * Created by Thanassis on 3/4/2018.
 * The base class for all generated bindings
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private  final RecyclerNewsBinding binding;

    /**
     */
    public NewsViewHolder(RecyclerNewsBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    /**
     * executePendingBindings() forces the bindings to run immediately instead of delaying them until the next frame.
     * If the wrong data is in the views because the binding is waiting until the next frame, it will be measured improperly.
     * @param object is the convention model object, for instance {@link News} object.
     */
    public void bind(Object object){
        /**
         * Using setVariable() instead of the type-safe, but class-specific, setObj() method
         * so that I can assign whatever view model object type that I need.
         */
        binding.setVariable(BR.newsView, object);
        binding.executePendingBindings();
    }

    public RecyclerNewsBinding getBinding() {
        return binding;
    }

}
