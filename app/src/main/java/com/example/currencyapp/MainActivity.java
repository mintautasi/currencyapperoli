package com.example.currencyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mCurrencies;
    private RequestQueue mQueue;
    private android.widget.ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCurrencies = findViewById(R.id.currencies);
        Button button = findViewById(R.id.button);
        adapter = new CoinsAdapter(this);
        mCurrencies.setAdapter(adapter);
        mQueue = Volley.newRequestQueue(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();

            }
        });

    }

    private void jsonParse() {
        String url = "https://api.exchangeratesapi.io/latest";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    java.util.List<String> dataList = parser(response);
                    adapter.clear();
                    adapter.addAll(dataList);
                    adapter.notifyDataSetChanged();

                }, error -> error.printStackTrace());
        mQueue.add(request);
    }

    List<String> parser(JSONObject response) {
        HashMap<String, String> currencies = new HashMap<>();
        JSONObject exchangeRates = response.optJSONObject("rates");
        Iterator<String> keys = exchangeRates.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = exchangeRates.optString(key);
            currencies.put(key, value);
        }
        return new ArrayList<>(currencies.values());
    }
}