package com.rohailkabani.shopifyinventory;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<FeedEntry> feedEntries;
    private FeedAdapter feedAdapter;
    private String jsonURL = "https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        feedEntries = new ArrayList<FeedEntry>();
        new JSONAsyncTask().execute(jsonURL);

        final ListView listView = (ListView) findViewById(R.id.list_item);

        feedAdapter = new FeedAdapter(getApplicationContext(), R.layout.list_record, feedEntries);

        listView.setAdapter(feedAdapter);
    }

    private class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected String doInBackground(String... strings) {
            String jsonFeed = downloadJSON(strings[0]);

            return jsonFeed;
        }

        private String downloadJSON(String urlPath) {
            try {

            } catch ()

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }


}

}
