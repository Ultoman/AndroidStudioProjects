package edu.niu.cs.z1764108.counter;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView countTV;

    private Integer counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countTV = (TextView)findViewById(R.id.counterTextView);

        counter = 0;

        //Create thread and associate with a Runnable
        Thread counterThread = new Thread(counterRunnable);
        counterThread.start();
    }//onCreate

    @Override
    protected void onStart() {
        super.onStart();
        counter = 0;
    }

    private Runnable counterRunnable = new Runnable() {
        private static final int DELAY = 1000;
        @Override
        public void run() {
            while(true)
            {
                //increment counter
                counter++;

                //sleep
                try
                {
                    Thread.sleep(DELAY);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                //send message to thread handler
                threadHandler.sendEmptyMessage(0);
            }
        }
    };

    public Handler threadHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            countTV.setText(counter.toString());
        }
    };

}
