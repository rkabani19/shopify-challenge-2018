package com.rohailkabani.shopifyinventory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ClickActivity extends AppCompatActivity {
    private static final String TAG = "ClickActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);

        String title = getIntent().getExtras().getString("title");
        String description = getIntent().getExtras().getString("description");
        String id = getIntent().getExtras().getString("productID");
        String colours = getIntent().getExtras().getString("colours");
        String type = getIntent().getExtras().getString("productType");
        String imgURL = getIntent().getExtras().getString("imgURL");

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle2);
        TextView tvDescription = (TextView) findViewById(R.id.tvDescription2);
        TextView tvID = (TextView) findViewById(R.id.tvID);
        TextView tvColours = (TextView) findViewById(R.id.tvColours);
        TextView tvType = (TextView) findViewById(R.id.tvType);
        ImageView imgProduct = (ImageView) findViewById(R.id.imgProduct);

        tvTitle.setText(title);
        tvDescription.setText(description);
        tvID.setText(id);
        tvColours.setText(colours);
        tvType.setText(type);

        Picasso.with(getApplicationContext()).load(imgURL).into(imgProduct);

    }
}
