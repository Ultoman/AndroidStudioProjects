package edu.niu.cs.z1764108.mp3player;

import android.Manifest;
import android.app.ListActivity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    private final static String TAG = "MP3";

    //private final static String PATH = new String(Environment.getExternalStorageDirectory().toString() + "/");
    private final static String PATH = "/sdcard/";
    //private final static String PATH = "/VG Dubstep/";

    private Button pausePlayBtn, stopBtn;
    private TextView songNameTV;

    private List<String> songs;
    private MediaPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        //Create list for songs
        songs = new ArrayList<>();

        //Create mediaplayer
        player = new MediaPlayer();

        //Get info into list
        updatePlayList();

        //Connect variables
        songNameTV = (TextView)findViewById(R.id.songPlayingTextView);

        //Handle button
        pausePlayBtn = (Button)findViewById(R.id.pauseButton);
        pausePlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If media is playing pause song and change button
                if (player.isPlaying())
                {
                    player.pause();
                    pausePlayBtn.setBackgroundResource(R.drawable.play);
                }
                else //media not playing
                {
                    player.start();
                    pausePlayBtn.setBackgroundResource(R.drawable.pause);
                }
            }
        });

        stopBtn = (Button)findViewById(R.id.stopButton);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    player.stop();
                    //Make textview invisible
                    songNameTV.setVisibility(View.INVISIBLE);

                    //change background for the pause/play button
                    pausePlayBtn.setBackgroundResource(R.drawable.pause);

                    //Change visibility
                    pausePlayBtn.setVisibility(View.INVISIBLE);
                    stopBtn.setVisibility(View.INVISIBLE);
            }
        });

    }//Create

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        try
        {
            //reset media
            player.reset();
            //load song
            player.setDataSource(PATH + songs.get(position));
            //prepare media
            player.prepare();
            player.start();
            //get song name into textview
            songNameTV.setText("Song: " + songs.get(position));
            songNameTV.setVisibility(View.VISIBLE);

            pausePlayBtn.setBackgroundResource(R.drawable.pause);
            pausePlayBtn.setVisibility(View.VISIBLE);

            stopBtn.setVisibility(View.VISIBLE);

        }
        catch(IOException e)
        {
            Log.d(TAG, e.getMessage());
        }
    }//onListItemClick

    public void updatePlayList()
    {
        File [] mp3Files = (new File(PATH)).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".mp3");
            }
        });
        //If files were found
        if (mp3Files != null) {
            if (mp3Files.length > 0) {
                //populate songs enhanced for loop
                for (File file : mp3Files) {
                    songs.add(file.getName());
                }
            }
        }
        //set up adapter
        ArrayAdapter<String> songList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songs);
        setListAdapter(songList);
    }

    /*
    class MP3Filter implements FilenameFilter
    {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".mp3");
        }
    }
    */
}
