package edu.niu.cs.z1764108.scenetransitions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    ViewGroup painting;
    Transition transition;

    Scene activeScene, passiveScene1, passiveScene2, passiveScene3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        painting = (ViewGroup)findViewById(R.id.paintingContainer);

        //Get the transition from the xml file
        transition = TransitionInflater.from(this).inflateTransition(R.transition.transition);

        //Connect
        activeScene = Scene.getSceneForLayout(painting, R.layout.scene1, this);
        passiveScene1 = Scene.getSceneForLayout(painting,R.layout.scene2,this);
        passiveScene2 = Scene.getSceneForLayout(painting,R.layout.scene3,this);
        passiveScene3 = Scene.getSceneForLayout(painting,R.layout.scene4,this);

        //Specify the starting scene
        activeScene.enter();
    }

    //Handle Button clicks
    public void changeScenes(View view){
        //Rotate the scenes
        Scene temp = activeScene;
        activeScene = passiveScene1;
        passiveScene1 = passiveScene2;
        passiveScene2 = passiveScene3;
        passiveScene3 = temp;

        TransitionManager.go(activeScene, transition);
    }

}
