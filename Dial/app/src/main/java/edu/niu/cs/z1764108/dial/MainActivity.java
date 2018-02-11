package edu.niu.cs.z1764108.dial;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Thread animationThread;
    private DialView dialView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialView = new DialView(this);

        setContentView(dialView);

        animationThread = new Thread(animationRunnable);

        animationThread.start();

    }//onCreate

    private Runnable animationRunnable = new Runnable() {
        private static final int DELAY = 20;
        @Override
        public void run() {
            while(true)
            {
                try
                {
                    Thread.sleep(DELAY);
                    threadHandler.sendEmptyMessage(0);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    };

    public Handler threadHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            dialView.update();
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        threadHandler.removeCallbacks(animationRunnable);
    }
}//MainActivity
