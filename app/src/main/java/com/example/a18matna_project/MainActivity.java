package com.example.a18matna_project;

import android.os.AsyncTask;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


// Create a new class, Mountain, that can hold your JSON data

// Create a ListView as in "Assignment 1 - Toast and ListView"

// Retrieve data from Internet service using AsyncTask and the included networking code

// Parse the retrieved JSON and update the ListView adapter

// Implement a "refresh" functionality using Android's menu system


public class MainActivity extends AppCompatActivity {

    public ArrayList<kopp>mountains = new ArrayList<kopp>();
    public ArrayAdapter<kopp> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FetchData().execute();

        //skapa en ArrayAdapter som kopplar samman
        // list_item_textview, my_item_textview, och listan med rådata
        //ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.list_item_textview,R.id.my_item_textview,listData);
        adapter= new ArrayAdapter<kopp>(this,R.layout.list_item_textview,R.id.my_item_textview);
        //Koppla samman ListView med ArrayAdapter
        ListView my_ListView = (ListView)findViewById(R.id.my_listview);
        my_ListView.setAdapter(adapter);

        my_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),adapter.getItem(position).info() ,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), KoppdetailsActivity.class);
                //String message = mMessageEditText.getText().toString();

                intent.putExtra("Name", adapter.getItem(position).getName());
                intent.putExtra("Category", adapter.getItem(position).getCategory());
                intent.putExtra("Size", adapter.getItem(position).getSize());
                intent.putExtra("Price", adapter.getItem(position).getPrice());
                intent.putExtra("Color", adapter.getItem(position).getColor());


                startActivity(intent);

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void activitystarter(){
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(intent);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.action_settings){
            new FetchData();
            return true;
        }
        if(id==R.id.about){
            activitystarter();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private class FetchData extends AsyncTask<Void,Void,String>{
        @Override
        protected String doInBackground(Void... params) {
            // These two variables need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a Java string.
            String jsonStr = null;

            try {
                // Construct the URL for the Internet service
                URL url = new URL("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=a18matna");

                // Create the request to the PHP-service, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                jsonStr = buffer.toString();
                return jsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in
                // attempting to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("Network error", "Error closing stream", e);
                    }
                }
            }
        }
        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            Log.d("Walla","FETCH:"+o);
            // This code executes after we have received our data. The String object o holds
            // the un-parsed JSON string or is null if we had an IOException during the fetch.

            // Implement a parsing code that loops through the entire JSON and creates objects
            // of our newly created Mountain class.

            try {
                JSONArray Jarray = new JSONArray(o);


                for(int i = 0; i < Jarray.length(); i++){
                    JSONObject a = Jarray.getJSONObject(i);

                    kopp m = new kopp(a.getString("name"));
                    adapter.add(m);
                    m.setSize(a.getInt("size"));
                    m.setPrice(a.getInt( "cost"));
                    m.setColor(a.getString("location"));
                    m.setCategory(a.getString("category"));
                    mountains.add(m);
                }


            } catch (JSONException e) {
                Log.e("kungligt","E: "+e.getMessage());
            }
            for(int b = 0; b < mountains.size(); b++) {
                Log.e("kungligt",mountains.get(b).toString());
            }
        }
    }
}

