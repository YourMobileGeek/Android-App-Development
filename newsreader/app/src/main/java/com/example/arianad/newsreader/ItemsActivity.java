package com.example.arianad.newsreader;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

//implement interface for listener
public class ItemsActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener

{
    //declare RSSFeed and FileIO variables
    private RSSFeed feed;
    private FileIO io;

    private TextView titleTextView;
    private ListView itemsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        //show app icon in emulator
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        //create FileIO object
        io = new FileIO(getApplicationContext());

        titleTextView = (TextView) findViewById(R.id.titleTextView);
        itemsListView = (ListView) findViewById(R.id.itemsListView);

        itemsListView.setOnItemClickListener(this);

        new DownloadFeed().execute();

    }

    //DownloadFeed (inner class): uses background thread to download XML for RSS feed
    class DownloadFeed extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            io.downloadFile();
            return null;
        }

        //displays message in LogCat view
        @Override
        protected void onPostExecute(Void result) {
            Log.d("News reader", "Feed downloaded");
            new ReadFeed().execute();

        }

    }

    //ReadFeed (inner class): background thread to read XML for RSS feed and parse into RSSFeed object (containing multiple RSSItem objects)
    class ReadFeed extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            feed = io.readFile();
            return null;
        }

        //displays message in LogCat view
        @Override
        protected void onPostExecute(Void result) {
            Log.d("News reader", "Feed read");

            //update the display for the activity
            ItemsActivity.this.updateDisplay();
        }

    }

    //updates user interfaces(UI)
    public void updateDisplay()
    {
        if (feed == null) {
            titleTextView.setText("Unable to get RSS feed");
            return;
        }

        //set title for feed
        titleTextView.setText(feed.getTitle());

        //get items for feed
        ArrayList<RSSItem> items = feed.getAllItems();

        //create List of Map<String, ?> objects
        ArrayList<HashMap<String, String>> data = new ArrayList<>(); //~ sign?
        for (RSSItem item : items) {
            HashMap<String, String> map = new HashMap<>(); //~ sign?
            map.put("date", item.getPubDateFormatted());
            map.put("title", item.getTitle());
            data.add(map);
        }

        //create resource, from and to variables
        int resource = R.layout.listview_item;
        String[] from = {"date", "title"};
        int[] to = {R.id.pubDateTextView, R.id.titleTextView};


        //create and set adapter
        SimpleAdapter adapter = new SimpleAdapter(this, data, resource, from, to);
        itemsListView.setAdapter(adapter);

        //displays message in LogCat view
        Log.d("News reader", "Feed displayed");

    }

        //Parameters:
        // 1) provides AdapterView object for list of items,
        // 2) provides View object for selected item,
        // 3) provides position of selected item
        // 4) provides ID of selected item
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id)
        {
            //get item at specified position
            RSSItem item = feed.getItem(position);

            //create an intent (to pass all data to Item activity)
            Intent intent = new Intent(this, ItemActivity.class);

            intent.putExtra("pubdate", item.getPubDate());
            intent.putExtra("title", item.getTitle());
            intent.putExtra("description", item.getDescription());
            intent.putExtra("link", item.getLink());

            this.startActivity(intent);

        }


}
