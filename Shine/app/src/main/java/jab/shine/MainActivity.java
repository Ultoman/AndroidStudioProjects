package jab.shine;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView shineIV;
    private MediaPlayer shineMP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shineIV = (ImageView)findViewById(R.id.shineImage);
        shineMP = new MediaPlayer();
        shineMP = MediaPlayer.create(this, R.raw.shine_sound);
    }//onCreate

    public void yPressed(View view)
    {
        shineIV.setVisibility(view.INVISIBLE);
    }

    public void bPressed(View view)
    {
        shineMP.start();
        shineIV.setVisibility(view.VISIBLE);
    }
}//Main
