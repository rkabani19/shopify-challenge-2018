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

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rohailkabani on 2018-01-03.
 */

public class FeedAdapter<T extends ListItem> extends ArrayAdapter {
    private static final String TAG = "FeedAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<T> application;
    private Context context;

    public FeedAdapter(@NonNull Context context, int resource, List<T> application) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.application = application;
        this.context = context;
    }

    @Override
    public int getCount() {
        return application.size();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutResource, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        T currentApp = application.get(position);

        viewHolder.tvTitle.setText(currentApp.getTitle());
        viewHolder.tvDescription.setText(currentApp.getDescription());
//        viewHolder.img.setText(currentApp.getImgURL());

        Picasso.with(context).load(currentApp.getImgURL()).into(viewHolder.img);

        return convertView;
    }


    private class ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final ImageView img;

        ViewHolder(View v) {
            this.tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            this.tvDescription = (TextView) v.findViewById(R.id.tvDescription2);
            this.img = (ImageView) v.findViewById(R.id.imgProduct);
        }
    }



}
