package com.example.z1764108.tempwidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by z1764108 on 3/5/2018.
 */

public class TempTask extends AsyncTask<String, Void, String> {

    private MainActivity mainActivity;
    private Context context;
    private AppWidgetManager appWidgetManager;
    private int[] appWidgetIds;
    public TempTask (MainActivity fromProvider, Context fromCtx,
                        AppWidgetManager fromAppWidgetManager, int[] fromAppWidgetIds)
    {
        mainActivity = fromProvider;
        context = fromCtx;
        appWidgetManager = fromAppWidgetManager;
        appWidgetIds = fromAppWidgetIds;
    }

    @Override
    protected String doInBackground(String... urlParts) {

        String baseUrl = "",cityString = "",keyName = "",key = "";
        if (urlParts != null)
        {
            baseUrl = urlParts[0];
            cityString = urlParts[1];
            keyName = urlParts[2];
            key = urlParts[3];
        }
        RemoteDataReader remoteDataReader = new RemoteDataReader(baseUrl, cityString, keyName, key);
        String json = remoteDataReader.getData();

        return json;
    }

    @Override
    protected void onPostExecute(String returnedJson) {
        TempParser parser = new TempParser(returnedJson);
        mainActivity.updateWidget(parser.getTempF(),context,appWidgetManager,appWidgetIds);
    }
}
