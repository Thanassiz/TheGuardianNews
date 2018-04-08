package com.example.android.theguardiannews;

import android.view.View;

/**
 * Created by Thanassis on 4/4/2018.
 */

public interface RecyclerViewItemClickListener {

    void onClick(View view, int position);

    void onLongClick(View view, int position);

}
