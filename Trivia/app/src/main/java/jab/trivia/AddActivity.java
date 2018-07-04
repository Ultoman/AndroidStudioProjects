package jab.trivia;

import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private Button submitB;
    private Button backB;

    //TriviaAPI variables
    private TriviaAPI triviaAPI;

    //Database variables
    private DatabaseManager databaseManager;

    //JSON private variables;
    private JSONObject jsonQuestionObject;
    private JSONArray jsonQuestionArray;
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
        submitB = (Button)findViewById(R.id.submitButton);
        backB = (Button)findViewById(R.id.backButton);

        //Set up listeners for difficulty radio buttons

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





    }



    public void submitURL(View v)
    {

        //Check what amount was entered
        int amount = Integer.parseInt(amountET.getText().toString());
        // If no radio buttons are checked
        if (diffRG.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(getApplicationContext(), "Choose a difficulty!", Toast.LENGTH_SHORT).show();
            return;
        }
        //Cannot add more than 50 questions
        if (amount > 50 || amount < 1)
        {
            Toast.makeText(getApplicationContext(), "Invalid amount: At most 50", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            // set the amount if it is correct
            triviaAPI.setAmount(amount);
        }

        //Disable buttons while downloading
        v.setEnabled(false);
        backB.setEnabled(false);
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
                // Enable buttons after finishing the download into database
                submitB.setEnabled(true);
                backB.setEnabled(true);
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
