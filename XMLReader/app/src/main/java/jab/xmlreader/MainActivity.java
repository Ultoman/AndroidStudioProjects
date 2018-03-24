package jab.xmlreader;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**************
 * James Bonasera
 * Z1764108
 * Assignment 4
 *
 * Section
 *
 */

public class MainActivity extends AppCompatActivity {

    // UI widgets
    private LinearLayout linearLayout;

    // XmlPullParser
    private XmlPullParserFactory xmlFactoryObject;

    private HttpURLConnection connection;

    private String xmlURL = "https://rss.itunes.apple.com/api/v1/us/movies/top-movies/all/10/explicit.atom";

    private ArrayList<Movie> movies;

    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.display);
        movies = new ArrayList<>();

        XmlDownloader xmlDownloader = new XmlDownloader();
        xmlDownloader.execute();
    }


    private class XmlDownloader extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            //Create new XMLparser
            try
            {
                xmlFactoryObject = XmlPullParserFactory.newInstance();
                XmlPullParser pullParser = xmlFactoryObject.newPullParser();
                //Create url connection
                URL url = new URL(xmlURL);
                connection = (HttpURLConnection)url.openConnection();

                pullParser.setInput(connection.getInputStream(), null);

                //Handle events
                int event = pullParser.getEventType();
                Movie movie = new Movie();
                // Loop through all movies
                while(event != XmlPullParser.END_DOCUMENT) {
                    //Grab name of first XML event
                    String name = pullParser.getName();
                    switch (event){
                        // If event is a start tag -
                        case XmlPullParser.START_TAG:
                            // and the name is entry then there is a new movie info to be added
                            if (name.equals("entry"))
                            {
                                movies.add(new Movie());
                            }
                            // and the name is im:name then the next text holds the movie name
                            else if (name.equals("im:name"))
                            {
                                movies.get(movies.size() - 1).setName(pullParser.nextText());
                            }
                            // and the name is im:name then the next text holds the artist name
                            else if (name.equals("im:artist"))
                            {
                                movies.get(movies.size() - 1).setArtist(pullParser.nextText());
                            }
                            // and the name is im:name then the next text holds the image url
                            else if (name.equals("im:image"))
                            {
                                movies.get(movies.size() - 1).setImage(pullParser.nextText());
                            }
                            // and the name is im:name then the next text holds the release date
                            else if (name.equals("im:releaseDate"))
                            {
                                movies.get(movies.size() - 1).setDate(pullParser.nextText());
                            }
                            break;

                    }//switch
                    // Go to next event
                    event = pullParser.next();
                }//while


            }//try
            catch (XmlPullParserException e)
            {
                e.printStackTrace();
                Log.d("parse", e.toString());
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
                Log.d("url", e.toString());
            }
            catch (IOException e)
            {
                e.printStackTrace();
                Log.d("url", e.toString());
            }
            return null;
        }//doInBackground

        @Override
        protected void onPostExecute(Void v) {
            // Create runnable to effect UI while in AsyncTask
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Movie currentMovie;
                    for (int i = 0; i < movies.size(); i++)
                    {
                        currentMovie = movies.get(i);
                        // Create text view and image view to add to the linearlayout
                        textView = new TextView(getApplicationContext());
                        textView.setText((i + 1) + ". " + currentMovie.getName() +
                                        "\nArtist: " + currentMovie.getArtist() +
                                        "\nRelease Date: " + currentMovie.getDate() + "\n\n");
                        textView.setTextColor(Color.BLACK);
                        textView.setTextSize(16);

                        imageView = new ImageView(getApplicationContext());
                        imageView.setImageBitmap(currentMovie.getImage());
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(400,600);
                        layoutParams.setMargins(400, 0, 0, 100);

                        linearLayout.addView(textView);
                        //linearLayout.addView(imageView, 400, 600);
                        linearLayout.addView(imageView, layoutParams);
                    }//for
                }//run
            };//new Runnable
            runOnUiThread(runnable);
        }
    }//AsyncTask

}
