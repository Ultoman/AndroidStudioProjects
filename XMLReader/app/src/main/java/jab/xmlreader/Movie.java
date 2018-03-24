package jab.xmlreader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by JAB on 3/14/2018.
 */

public class Movie {

    private String name;
    private String artist;
    private String date;
    private Bitmap image;

    public Movie(){}

    public Movie(String newName, String newArtist, String newDate, String imageURL)
    {
        name = newName;
        artist = newArtist;
        date = newDate;

        image = loadImage(imageURL);
    }

    public static Bitmap loadImage(String url)
    {
        // Get Drawable from URL
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            //Drawable d = Drawable.createFromStream(is, null);
            return bitmap;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.d("image", e.toString());
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(String imageURL) {
        this.image = loadImage(imageURL);
    }
}
