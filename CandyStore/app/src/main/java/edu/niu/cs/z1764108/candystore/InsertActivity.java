package edu.niu.cs.z1764108.candystore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private DatabaseManager database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        database = new DatabaseManager(this);
    }//onCreate

    public void insert(View view)
    {
        EditText nameET = (EditText)findViewById(R.id.candyNameEditText);
        EditText priceET = (EditText)findViewById(R.id.candyPriceEditText);

        String nameStr = nameET.getText().toString();
        String priceStr = priceET.getText().toString();

        try
        {
            double price = Double.parseDouble(priceStr);
            Candy candy = new Candy(0, nameStr, price);
            database.insert(candy);
            Toast.makeText(this, "Candy: " + nameStr + " added", Toast.LENGTH_SHORT).show();
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(this, "Price error", Toast.LENGTH_SHORT).show();
        }

        nameET.setText("");
        priceET.setText("");
    }//insert

    public void goBack(View view)
    {
        finish();
    }

}//insertActivity
