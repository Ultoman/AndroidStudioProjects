package com.example.z1764108.tempwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppWidgetProvider {

    public static final char DEGREE = '\u00B0';
    public final static String STARTINGURL = "http://api.openweathermap.org/data/2.5/weather?q=";
    public static final String KEY_NAME = "&APPID";
    private String city = "Chicago";
    private String key = "878af01170772a0090b6ad378212283";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        TempTask task = new TempTask(this, context, appWidgetManager, appWidgetIds);
        task.execute(STARTINGURL, city, KEY_NAME, key);
    }

    public void updateWidget(int temp, Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        Date dateToday = new Date();
        String todayStr = DateFormat.getDateTimeInstance().format(dateToday);

        String displayStr = todayStr + city + "\n";
        displayStr += (temp + " " + DEGREE + "F");

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_main);
        remoteViews.setTextViewText(R.id.display, displayStr);
        for (int widgetId : appWidgetIds)
        {
            Intent intent = new Intent(context, MainActivity.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.display,pendingIntent);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
}
