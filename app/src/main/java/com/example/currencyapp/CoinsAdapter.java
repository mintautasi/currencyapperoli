package com.example.currencyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class CoinsAdapter extends ArrayAdapter<String> {
    public CoinsAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = convertView.findViewById(R.id.currency_row_textView);
        // Populate the data into the template view using the data object
        tvName.setText(item);
        // Return the completed view to render on screen
        return convertView;
    }
}
