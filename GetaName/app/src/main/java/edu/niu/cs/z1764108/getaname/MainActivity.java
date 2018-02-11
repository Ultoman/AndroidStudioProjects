package edu.niu.cs.z1764108.getaname;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView nameTV;

    static final int REQUEST_CODE = 1;

    static final String SAVED_NAME_KEY = "oldName",
                        DEFAULT_NAME = "none";

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        nameTV = (TextView)findViewById(R.id.nameTextView);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        //get last name entered
        String savedName = preferences.getString(SAVED_NAME_KEY, DEFAULT_NAME);

        if (!savedName.equals(DEFAULT_NAME))
        {
            //put name into textview
            nameTV.setText("The last name that was entered: " + savedName);
        }

    }//onCreate

    public void getName(View view)
    {
        Intent nameIntent = new Intent(MainActivity.this, NameActivity.class);
        startActivityForResult(nameIntent, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String returnedName;

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            returnedName = data.getStringExtra("nameID");
            nameTV.setText("Your name is " + returnedName);

            SharedPreferences.Editor editor = preferences.edit();

            editor.putString(SAVED_NAME_KEY, returnedName);

            //Commit changes
            editor.commit();

        }
    }
}
