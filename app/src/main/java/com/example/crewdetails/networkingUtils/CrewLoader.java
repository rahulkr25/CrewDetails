package com.example.crewdetails.networkingUtils;

import android.content.Context;

import com.example.crewdetails.database.CrewEntry;

import java.util.ArrayList;

public class CrewLoader extends  androidx.loader.content.AsyncTaskLoader<ArrayList<CrewEntry>> {

    private String mUrl;

    public CrewLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {

        forceLoad();
    }


    @Override
    public ArrayList<CrewEntry> loadInBackground() {

        if (mUrl == null) {
            return null;
        }


        ArrayList<CrewEntry>crew = QueryUtils.extractCrew(mUrl);
        return crew;
    }
}
