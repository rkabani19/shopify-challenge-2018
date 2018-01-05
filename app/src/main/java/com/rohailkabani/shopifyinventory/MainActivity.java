package com.rohailkabani.shopifyinventory;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ListView listView;
    private static String url = "https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
    private ArrayList<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listItems = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);

        new DownloadData().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ClickActivity.class);
//                intent.putExtra("stuff", listView.getItemAtPosition(position).toString());

                intent.putExtra("title", listItems.get(position).getTitle());
                intent.putExtra("description", listItems.get(position).getDescription());
                intent.putExtra("productID", listItems.get(position).getProductId());
                intent.putExtra("productType", listItems.get(position).getTypeProduct());
                intent.putExtra("colours", listItems.get(position).getColours());
                intent.putExtra("imgURL", listItems.get(position).getImgURL());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        MenuItem search = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) search.getActionView();
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<ListItem> temp = new ArrayList<>();

                for (ListItem temp2 : listItems) {
                    if(temp2.getTitle().toLowerCase().startsWith(newText.toLowerCase())) {
                        temp.add(temp2);
                    }
                }

                FeedAdapter<ListItem> feedAdapter = new FeedAdapter<>(MainActivity.this, R.layout.list_item, temp);
                listView.setAdapter(feedAdapter);

                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private class DownloadData extends AsyncTask<Void, Void, Void> {
         protected Void doInBackground(Void... arg0) {
            HTTPHandler sh = new HTTPHandler();
            String jsonString = sh.makeServiceCall(url);

            Log.d(TAG, "Response from url: " + jsonString);

            if (jsonString != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonString);

                    JSONArray products = jsonObject.getJSONArray("products");

                    for (int i = 0; i < products.length(); i++) {
                        JSONObject p = products.getJSONObject(i);

                        String title = p.getString("title");
                        String description = p.getString("body_html");
                        String imgURL = p.getJSONObject("image").getString("src");
                        String idProduct = p.getString("id");
                        String typeProduct = p.getString("product_type");

                        JSONArray coloursArray = p.getJSONArray("options").getJSONObject(0).getJSONArray("values");
                        String colours = "";
                        for (int k = 0; k < coloursArray.length(); k++) {
                            if ((k + 1) != coloursArray.length()) {
                                colours += coloursArray.getString(k) + ", ";
                            } else {
                                colours += coloursArray.getString(k);
                            }
                        }

                        ListItem listItem = new ListItem(title, description, imgURL, idProduct, colours, typeProduct);

                        listItems.add(listItem);
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            FeedAdapter<ListItem> feedAdapter = new FeedAdapter<>(MainActivity.this, R.layout.list_item, listItems);
            listView.setAdapter(feedAdapter);
        }
    }
}
