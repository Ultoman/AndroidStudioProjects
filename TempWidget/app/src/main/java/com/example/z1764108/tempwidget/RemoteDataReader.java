package com.example.z1764108.tempwidget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by z1764108 on 2/28/2018.
 */

public class RemoteDataReader {
    private String urlString;

    public RemoteDataReader(String baseUrl, String cityStr, String keyName, String key)
    {
        try
        {
            urlString = baseUrl + URLEncoder.encode(cityStr, "UTF-8");
            if (keyName != null && key != null)
            {
                urlString += URLEncoder.encode("&","UTF-8") + keyName + "=" + key;
            }
        }
        catch (UnsupportedEncodingException e)
        {
            urlString = "";
            e.printStackTrace();
        }
    }

    public String getData()
    {
        try
        {
            //establish connection
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();
            //get input stream and prepare to read
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            //read data
            String dataRead = new String();
            String line = br.readLine();
            while (line != null)
            {
                dataRead += line;
                line = br.readLine();
            }

            is.close();
            connection.disconnect();
            return dataRead;
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            return "";
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "";
        }
    }
}
