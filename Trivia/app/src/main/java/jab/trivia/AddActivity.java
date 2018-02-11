package jab.trivia;

import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddActivity extends AppCompatActivity {

    //Widgets
    private RadioGroup diffRG;
    private EditText amountET;
    private Spinner categorySP;

    //TriviaAPI variables
    private TriviaAPI triviaAPI;

    //Database variables
    private DatabaseManager databaseManager;

    //JSON private variables;
    private JSONObject jsonQuestionObject, jsonCategoryObject;
    private JSONArray jsonQuestionArray, jsonCategoryArray;
    private JsonParser jsonParser = new JsonParser();

    private String [] categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        databaseManager = new DatabaseManager(this);
        triviaAPI = new TriviaAPI();

        //Connect widgets to variables
        diffRG = (RadioGroup)findViewById(R.id.diffRadioGroup);
        amountET = (EditText)findViewById(R.id.amountEditText);
        categorySP = (Spinner)findViewById(R.id.categorySpinner);

        //Set up listeners
        diffRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.easyRadioButton: triviaAPI.setDifficulty("easy");
                        break;
                    case R.id.mediumRadioButton: triviaAPI.setDifficulty("medium");
                        break;
                    case R.id.hardRadioButton: triviaAPI.setDifficulty("hard");
                }
            }
        });

        // Get categories
        /*
        jsonCategoryObject = jsonParser.getJSONFromUrl(triviaAPI.generateCategoryURL());
        try{
            jsonCategoryArray = jsonCategoryObject.getJSONArray("trivia_categories");
            JSONObject tempObj;
            for (int i = 0; i < jsonCategoryArray.length(); i++)
            {
                tempObj = jsonCategoryArray.getJSONObject(i);
                databaseManager.insertCategory(tempObj.getInt("id"), tempObj.getString("name"));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.d("pars1", "json exception " + e.toString());
        }
        */


        categories = databaseManager.selectAllCategories();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_view, categories);

        categorySP.setAdapter(adapter);
        categorySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // find category id
                int categoryID = databaseManager.selectCategory(parent.getItemAtPosition(position).toString());
                triviaAPI.setCategory(categoryID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }



    public void submitURL(View v)
    {
        int amount = Integer.parseInt(amountET.getText().toString());
        if (amount > 50 || amount < 1)
        {
            Toast.makeText(getApplicationContext(), "Invalid amount: At most 50", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            triviaAPI.setAmount(amount);
        }

        Toast.makeText(getApplicationContext(),
                triviaAPI.generateURL(),
                Toast.LENGTH_LONG).show();

        new DownLoadJson().execute();
    }

    private class DownLoadJson extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... params) {
            // generate url from TriviaAPI class and store into jsonObject
            jsonQuestionObject = jsonParser.getJSONFromUrl(triviaAPI.generateURL());
            return "End of do in background";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(), "Grabbing Questions", Toast.LENGTH_SHORT).show();
            // Get data from jsonObject
            try
            {
                jsonQuestionArray = jsonQuestionObject.getJSONArray("results");
                JSONObject tempObj;
                JSONArray tempArr;
                for (int i = 0; i < jsonQuestionArray.length(); i++)
                {
                    tempObj = jsonQuestionArray.getJSONObject(i);
                    tempArr = tempObj.getJSONArray("incorrect_answers");
                    // Create new trivia object with trivia parameters
                    Trivia trivia = new Trivia(i, tempObj.getString("question"), tempObj.getString("correct_answer"),
                            tempArr.getString(0), tempArr.getString(1), tempArr.getString(2));
                    // Add this trivia to database
                    databaseManager.insertTrivia(trivia);
                }
                Toast.makeText(getApplicationContext(), "Added to Database", Toast.LENGTH_SHORT).show();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Log.d("pars1", "json exception in post execute");
            }
        }
    }

    public void goBack(View view)
    {
        finish();
    }
}
