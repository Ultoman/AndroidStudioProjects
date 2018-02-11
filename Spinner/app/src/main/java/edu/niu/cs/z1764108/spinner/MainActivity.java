package edu.niu.cs.z1764108.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner xmlSpin, classSpin, listSpin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Spinner populated from the strings.xml
        xmlSpin = (Spinner)findViewById(R.id.xmlSpinner);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource( getApplicationContext(),
                                                                                    R.array.spinnerValues,
                                                                                    R.layout.spinner_view);

        xmlSpin.setAdapter(adapter1);

        xmlSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection1;
                selection1 = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "The first selection is " + selection1, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner populated from a class
        classSpin = (Spinner)findViewById(R.id.classSpinner);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>( getApplicationContext(),
                                                                    R.layout.spinner_view,
                                                                    SpinnerInfo.valueArray);
        classSpin.setAdapter(adapter2);

        classSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection2;
                selection2 = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "The second selection is " + selection2, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner populated by list object
        List<String> values = new ArrayList<>();
        values.add("Chicago Blackhawks");
        values.add("Chicago Cubs");
        values.add("Chicago Bears");
        values.add("Chicago White Sox");

        listSpin = (Spinner)findViewById(R.id.listSpinner);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getApplicationContext(),
                                                                    R.layout.spinner_view,
                                                                    values);
        listSpin.setAdapter(adapter3);
        listSpin.setOnItemSelectedListener(spinnerListener);
    }
    AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selection3;
            selection3 = parent.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(),"The third selection is " + selection3, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

}
