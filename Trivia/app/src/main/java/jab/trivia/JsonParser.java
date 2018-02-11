package jab.trivia;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * From webServicesExample.odt
 *
 */

public class JsonParser {
    private static InputStream is = null;
    private static JSONObject jObj = null;
    private static JSONArray jArr = null;
    private URL urlin;
    private String json = "json string";

    public JsonParser(){
        //empty constructor
    }

    public JSONObject getJSONFromUrl (String url)
    {
        HttpURLConnection urlConnection;
        try
        {
            urlin = new URL(url);
            urlConnection = (HttpURLConnection)urlin.openConnection();
            is = new BufferedInputStream((urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while((line = reader.readLine()) != null)
            {
                sb.append(line+"\n");
            }
            is.close();
            urlConnection.disconnect();
            json = sb.toString();
            Log.d("pars1",json);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            Log.d("pars1","malformed " + url);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Log.d("pars1","ioexception " + url + e.toString());
        }

        finally
        {
            try
            {
                jObj = new JSONObject(json);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Log.d("pars1", "json exception " + json);
            }
        }
        return jObj;
    }//getJSONFromUrl


}
