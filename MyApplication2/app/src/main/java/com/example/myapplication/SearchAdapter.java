package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SearchAdapter extends BaseAdapter {
    private List<String> mSearchResults;

    public SearchAdapter(List<String> searchResults) {
        mSearchResults = searchResults;
    }

    @Override
    public int getCount() {
        return mSearchResults.size();
    }

    @Override
    public String getItem(int position) {
        return mSearchResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_search, parent, false);
        }

        TextView resultTextView = convertView.findViewById(R.id.search_frag);
        resultTextView.setText(mSearchResults.get(position));

        return convertView;
    }
}
