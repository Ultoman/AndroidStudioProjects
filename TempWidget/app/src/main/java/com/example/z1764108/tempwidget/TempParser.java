package com.example.z1764108.tempwidget;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by z1764108 on 2/28/2018.
 */

public class TempParser {
    public static final double ZEROK = -273.15;
    private final String MAIN_KEY = "main";
    private final String TEMP_KEY = "temp";
    private JSONObject jObj;

    public TempParser (String json)
    {
        try
        {
            jObj = new JSONObject(json);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public double getTempK()
    {
        try
        {
            JSONObject jsonMain = jObj.getJSONObject(MAIN_KEY);
            return jsonMain.getDouble(TEMP_KEY);
        }
        catch (JSONException e)
        {
            return 25 - ZEROK;
        }
    }

    public int getTempC()
    {
        return (int) (getTempK() + ZEROK + 0.5);
    }

    public int getTempF()
    {
        return (int) (((getTempK()) + ZEROK) * 9/5 + 32 + 0.5);
    }
}
