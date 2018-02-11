package edu.niu.cs.z1764108.tictactoe;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import static edu.niu.cs.z1764108.tictactoe.TicTacToe.SIDE;

public class MainActivity extends AppCompatActivity {

    private Button [][]buttons;

    private TicTacToe game;

    private TextView gameStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        game = new TicTacToe();

        buildGUI();
    }

    public void buildGUI()
    {
        //Get width of screen
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenWidth = size.x / SIDE;

        //Create a grid layout
        GridLayout gridLayout = new GridLayout(this);

        gridLayout.setColumnCount(SIDE);
        //add 1 to hold TextView
        gridLayout.setRowCount(SIDE+1);

        //Create button array
        buttons = new Button[SIDE][SIDE];

        //Create ButtonHandler
        ButtonHandler buttonHandler = new ButtonHandler();


        //add buttons to gridlayout
        for (int row = 0; row < SIDE; row++)
            for (int col = 0; col < SIDE; col++)
            {
                buttons[row][col] = new Button(this);

                buttons[row][col].setTextSize((int)(screenWidth * 0.2));
                buttons[row][col].setOnClickListener(buttonHandler);

                gridLayout.addView(buttons[row][col], screenWidth, screenWidth);
            }

        gameStatus = new TextView(this);

        GridLayout.Spec rowSpec = GridLayout.spec(SIDE, 1),
                        colSpec = GridLayout.spec(0, SIDE);

        GridLayout.LayoutParams LayoutParams = new GridLayout.LayoutParams(rowSpec, colSpec);

        gameStatus.setLayoutParams(LayoutParams);
        gameStatus.setWidth(SIDE * screenWidth);
        gameStatus.setHeight(screenWidth);

        gameStatus.setGravity(Gravity.CENTER);
        gameStatus.setBackgroundColor(Color.CYAN);

        gameStatus.setTextSize((int)(screenWidth*0.15));
        gameStatus.setText(game.result());

        gridLayout.addView(gameStatus);

        //set view to gridlayout
        setContentView(gridLayout);
    }//buildGUI

    public void update(int row, int col)
    {
        //Determine who is playing
        int currentPlayer = game.play(row,col);

        if (currentPlayer == 1)
            buttons[row][col].setText("X");
        else if(currentPlayer == 2)
            buttons[row][col].setText("O");

        if(game.isGameOver())
        {
            gameStatus.setBackgroundColor(Color.BLUE);
            enableButtons(false);
            gameStatus.setText(game.result());
            showNewDialog();
        }
    }//update

    public void enableButtons(boolean enabled)
    {
        for (int row = 0; row < SIDE; row++)
            for (int col = 0; col < SIDE; col++)
                buttons[row][col].setEnabled(enabled);
    }

    private class ButtonHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            //Toast.makeText(MainActivity.this, "ButtonHandler: " + v, Toast.LENGTH_SHORT).show();

            for (int row = 0; row < SIDE; row++)
                for (int col = 0; col < SIDE; col++)
                {
                  if (v == buttons[row][col])
                  {
                      update(row, col);
                  }
                }
        }//onClick
    }//ButtonHandler

    public void resetButtons()
    {
        for (int row = 0; row < SIDE; row++)
            for (int col = 0; col < SIDE; col++)
                buttons[row][col].setText("");
    }

    public void showNewDialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("TicTacToe");
        alert.setMessage("Play again?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                game.resetGame();
                enableButtons(true);
                resetButtons();
                gameStatus.setBackgroundColor(Color.CYAN);
                gameStatus.setText(game.result());
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        });

        alert.show();

    }//showNewDialog



}//MainActivity
