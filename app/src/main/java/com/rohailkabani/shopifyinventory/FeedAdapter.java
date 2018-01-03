package com.rohailkabani.shopifyinventory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohailkabani on 2018-01-03.
 */

public class FeedAdapter extends ArrayAdapter<FeedEntry> {
    private ArrayList<FeedEntry> feedEntries;
    private LayoutInflater layoutInflater;
    private int resource;
    private ViewHolder holder;
    View view;
    int gen = 0;

    private static class ViewHolder {
        TextView tvTitle;
        TextView tvDescription;
        ImageView imgProduct;
    }

    public FeedAdapter(Context context, int resource, ArrayList<FeedEntry> feedEntries) {
        super(context, resource, feedEntries);
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
        this.feedEntries = feedEntries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            holder = new ViewHolder();
            v = layoutInflater.inflate(resource, null);

            holder.tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            holder.tvDescription = (TextView) v.findViewById(R.id.tvDescription);
            holder.imgProduct = (ImageView) v.findViewById(R.id.imgProduct);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.tvTitle.setText(feedEntries.get(position).getTitle());
        holder.tvDescription.setText(feedEntries.get(position).getTitle());
//        holder.imgProduct.setText(feedEntries.get(position).getImage());


        return super.getView(position, convertView, parent);
    }
}
