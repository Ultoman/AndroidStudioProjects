package edu.niu.cs.z1764108.cartooncharacter;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LinearLayout gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gallery = (LinearLayout)findViewById(R.id.characterLayout);

        fillGallery();
    }//onCreate

    private void fillGallery(){
        ImageButton characterItem;

        for(int i = 0; i < CharacterInfo.description.length; i++)
        {
            characterItem = new ImageButton(this);

            Character character = new Character(CharacterInfo.description[i], CharacterInfo.id[i]);

            characterItem.setContentDescription(character.getCharacterDescription());

            characterItem.setImageDrawable(ResourcesCompat.getDrawable(getResources(), character.getCharacterID(), null));

            characterItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String descrip = (String)v.getContentDescription();
                    Toast.makeText(v.getContext(), descrip, Toast.LENGTH_SHORT).show();
                }
            });

            gallery.addView(characterItem);
        }// end of for
    }
}
