package edu.niu.cs.z1764108.draw;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends Activity implements View.OnClickListener{

    private DrawingView drawingView;

    private ImageButton currentColor;

    private Button brushBtn, eraseBtn, newBtn, saveBtn;

    private float smallBrush, mediumBrush, largeBrush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        drawingView = (DrawingView)findViewById(R.id.drawing);

        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.colors);
        currentColor = (ImageButton)paintLayout.getChildAt(0);
        //currentColor.setImageDrawable(ResourcesCompat.getDrawable( getResources(), R.drawable.paint_selected, null));
        currentColor.setImageResource(R.drawable.paint_selected);

        //Found in dimens
        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);

        drawingView.setBrushSize(mediumBrush);
        drawingView.setPaintColor(currentColor.getTag().toString());

        brushBtn = (Button)findViewById(R.id.brushButton);
        brushBtn.setOnClickListener(this);

        eraseBtn = (Button)findViewById(R.id.eraseButton);
        eraseBtn.setOnClickListener(this);

        newBtn = (Button)findViewById(R.id.newButton);
        newBtn.setOnClickListener(this);

        saveBtn = (Button)findViewById(R.id.saveButton);
        saveBtn.setOnClickListener(this);
    }//onCreate

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.brushButton)
        {
            //create pop up dialog with 3 buttons
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Choose a Brush Size:");
            brushDialog.setContentView(R.layout.brush_choice);

            brushDialog.show();

            Button smallBrushBtn = (Button)brushDialog.findViewById(R.id.smallBrushButton);
            smallBrushBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //change brush size
                    drawingView.setBrushSize(smallBrush);

                    //set the last brush size
                    drawingView.setLastBrushSize(smallBrush);

                    drawingView.setErase(false);

                    brushDialog.dismiss();
                }
            });

            Button mediumBrushBtn = (Button)brushDialog.findViewById(R.id.mediumBrushButton);
            mediumBrushBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //change brush size
                    drawingView.setBrushSize(mediumBrush);

                    //set the last brush size
                    drawingView.setLastBrushSize(mediumBrush);

                    drawingView.setErase(false);

                    brushDialog.dismiss();
                }
            });

            Button largeBrushBtn = (Button)brushDialog.findViewById(R.id.largeBrushButton);
            largeBrushBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //change brush size
                    drawingView.setBrushSize(largeBrush);

                    //set the last brush size
                    drawingView.setLastBrushSize(largeBrush);

                    drawingView.setErase(false);

                    brushDialog.dismiss();
                }
            });
        }
        else if (v.getId() == R.id.eraseButton)
        {
            final Dialog eraseDialog = new Dialog(this);
            eraseDialog.setTitle("Choose an Eraser Size:");
            eraseDialog.setContentView(R.layout.eraser_choice);

            eraseDialog.show();

            Button smallEraseBtn = (Button)eraseDialog.findViewById(R.id.smallEraserButton);
            smallEraseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //change brush size
                    drawingView.setBrushSize(smallBrush);

                    drawingView.setErase(true);

                    eraseDialog.dismiss();
                }
            });

            Button mediumEraseBtn = (Button)eraseDialog.findViewById(R.id.mediumEraserButton);
            mediumEraseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //change brush size
                    drawingView.setBrushSize(mediumBrush);

                    drawingView.setErase(true);

                    eraseDialog.dismiss();
                }
            });

            Button largeEraseBtn = (Button)eraseDialog.findViewById(R.id.largeEraserButton);
            largeEraseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //change brush size
                    drawingView.setBrushSize(largeBrush);

                    drawingView.setErase(true);

                    eraseDialog.dismiss();
                }
            });
        }
        else if (v.getId() == R.id.newButton)
        {
            final AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
            newDialog.setTitle("New Drawing");
            newDialog.setMessage("Start a new drawing (you will lose the current drawing)?");

            newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    drawingView.newDrawing();
                    dialog.dismiss();
                }
            });

            newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            newDialog.show();
        }
        else if (v.getId() == R.id.saveButton)
        {
            final AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
            saveDialog.setTitle("Save Drawing");
            saveDialog.setMessage("Save the drawing to gallery?");

            saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    drawingView.setDrawingCacheEnabled(true);
                    String savedImageURI = MediaStore.Images.Media.insertImage(MainActivity.this.getContentResolver(),
                            drawingView.getDrawingCache(), UUID.randomUUID().toString() + ".png", "drawing");
                    if (savedImageURI != null)
                    {
                        Toast.makeText(getApplicationContext(), "Drawing was saved to gallery" + savedImageURI, Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Drawing was not saved", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            saveDialog.show();
            drawingView.destroyDrawingCache();
        }
    }//onClick

    //Change Colors
    public void paintClicked(View view)
    {
        drawingView.setErase(false);
        drawingView.setBrushSize(drawingView.getLastBrushSize());

        if (view != currentColor)
        {
            ImageButton imageButton = (ImageButton)view;
            String color = view.getTag().toString();
            drawingView.setPaintColor(color);

            imageButton.setImageResource(R.drawable.paint_selected);
            currentColor.setImageResource(R.drawable.paint_color);
            currentColor = imageButton;
        }
    }

}
