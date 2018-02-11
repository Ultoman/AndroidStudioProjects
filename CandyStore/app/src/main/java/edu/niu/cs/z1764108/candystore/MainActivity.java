package edu.niu.cs.z1764108.candystore;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseManager databaseManager;
    private double total;
    private ScrollView scrollView;
    private int buttonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseManager = new DatabaseManager(this);
        total = 0;
        scrollView = (ScrollView)findViewById(R.id.candyScrollView);

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        buttonWidth = size.x / 2;

        updateView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    public void updateView()
    {
        ArrayList<Candy> candies = databaseManager.selectAll();
        int numCandies = candies.size();

        if (numCandies > 0)
        {
            scrollView.removeAllViewsInLayout();
            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setRowCount((numCandies + 1) / 2);
            gridLayout.setColumnCount(2);

            CandyButton [] buttons = new CandyButton[numCandies];
            int sub = 0;

            for (Candy candy : candies)
            {
                buttons[sub] = new CandyButton(this,candy);
                buttons[sub].setText(candy.getName() + "\n" + candy.getPrice());
                buttons[sub].setOnClickListener(buttonHandler);

                gridLayout.addView(buttons[sub], buttonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
                sub++;
            }//for
            scrollView.addView(gridLayout);
        }//if
    }//updateView

    public View.OnClickListener buttonHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            total += ((CandyButton)v).getPrice();
            String payStr = NumberFormat.getCurrencyInstance().format(total);
            Toast.makeText(MainActivity.this, payStr, Toast.LENGTH_SHORT).show();
        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Toast.makeText(this, "Add was selected", Toast.LENGTH_SHORT).show();
            Log.d("onOptionsItemSelected", "ADD was selected");

            Intent insertIntent = new Intent(MainActivity.this, InsertActivity.class);
            startActivity(insertIntent);

            return true;
        }
        else if (id == R.id.action_delete)
        {
            Toast.makeText(this, "Delete was selected", Toast.LENGTH_SHORT).show();
            Log.d("onOptionsItemSelected", "DELETE was selected");

            Intent deleteIntent = new Intent(MainActivity.this, DeleteActivity.class);
            startActivity(deleteIntent);

            return true;
        }
        else if (id == R.id.action_update)
        {
            Toast.makeText(this, "Update was selected", Toast.LENGTH_SHORT).show();
            Log.d("onOptionsItemSelected", "UPDATE was selected");

            Intent updateIntent = new Intent(MainActivity.this, UpdateActivity.class);
            startActivity(updateIntent);
            return true;
        }
        else if (id == R.id.action_reset)
        {
            Toast.makeText(this, "Reset was selected", Toast.LENGTH_SHORT).show();
            Log.d("onOptionsItemSelected", "RESET was selected");

            total = 0;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
