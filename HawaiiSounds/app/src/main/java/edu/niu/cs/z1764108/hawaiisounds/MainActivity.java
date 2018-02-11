package edu.niu.cs.z1764108.hawaiisounds;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button ukuleleBtn, drumsBtn;
    private MediaPlayer ukuleleMP, drumsMP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup ukulele
        ukuleleBtn = (Button)findViewById(R.id.ukuleleButton);
        ukuleleMP = new MediaPlayer();
        ukuleleMP = MediaPlayer.create(this, R.raw.ukulele);

        //setup drums
        drumsBtn = (Button)findViewById(R.id.drumsButton);
        drumsMP = new MediaPlayer();
        drumsMP = MediaPlayer.create(this, R.raw.drums);
    }//onCreate

    public void playUkulele(View view)
    {
        if ( ukuleleMP.isPlaying() )
        {
            ukuleleMP.pause();
            ((Button)view).setText(R.string.playUkuleleString);
        }
        else
        {
            if (drumsMP.isPlaying())
            {
                drumsMP.pause();
                drumsBtn.setText(R.string.playDrumsString);
            }

            ukuleleMP.start();
            ukuleleBtn.setText(R.string.pauseUkuleleString);

        }
    }

    public void playDrums(View view)
    {
        if ( drumsMP.isPlaying())
        {
            drumsMP.pause();
            drumsBtn.setText(R.string.playUkuleleString);
        }
        else
        {
         if (ukuleleMP.isPlaying())
         {
             ukuleleMP.pause();
             ukuleleBtn.setText(R.string.playUkuleleString);
         }
            drumsMP.start();
            drumsBtn.setText(R.string.pauseDrumsString);
        }
    }

}//Main
