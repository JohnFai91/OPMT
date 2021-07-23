package one.scarecrow.games.OPMT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

import one.scarecrow.games.OPMT.Game.Board;
import one.scarecrow.games.OPMT.Game.Pieces;

public class GameActivity extends AppCompatActivity {


    // Init vars
    Button B1, B2, B3, B4, B5, B6, B7, B8, B9;

    // When pressed for the first time, the button will become activated and the second press of an another button will move
    int currentButtonActiveId;



    TextView currentActiveTextBox;

    // Grabs all values from the values class, changed from the main menu
    Boolean isWhiteTurn = values.isWhiteTurn;
    // Play on one screen with someone else!
    Boolean localMultiplayer = values.localMultiplayer;
    Boolean isWhiteComputer = values.isWhiteComputer;

    Board board = new Board();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        //Change bottom nav bar to transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        // Defining vars
        currentActiveTextBox = findViewById(R.id.currentActiveTextbox);
        B1 = findViewById(R.id.B1);
        B2 = findViewById(R.id.B2);
        B3 = findViewById(R.id.B3);
        B4 = findViewById(R.id.B4);
        B5 = findViewById(R.id.B5);
        B6 = findViewById(R.id.B6);
        B7 = findViewById(R.id.B7);
        B8 = findViewById(R.id.B8);
        B9 = findViewById(R.id.B9);

        B1.setOnClickListener(view -> buttonOnClickMethod(1));
        B2.setOnClickListener(view -> buttonOnClickMethod(2));
        B3.setOnClickListener(view -> buttonOnClickMethod(3));
        B4.setOnClickListener(view -> buttonOnClickMethod(4));
        B5.setOnClickListener(view -> buttonOnClickMethod(5));
        B6.setOnClickListener(view -> buttonOnClickMethod(6));
        B7.setOnClickListener(view -> buttonOnClickMethod(7));
        B8.setOnClickListener(view -> buttonOnClickMethod(8));
        B9.setOnClickListener(view -> buttonOnClickMethod(9));
    }


    // This is the function that gets called when a button is pressed
    private void buttonOnClickMethod(int n){
       Pieces pieces = board.runTurn(n);
       //Sets the background resource of all pieces ever button press... could lead to lag if try hard enough maybe?
        setAllBackgroundResourceValue(pieces);

    }


    /**
     * Sets the text of the TextBox in game.
     *
     * @param text Can be anything, should be who's turn it is.
     */
    private void setTextBox(String text){
        currentActiveTextBox.setText(text);
    }

    private void setAllBackgroundResourceValue(Pieces pieces){
        for (int i = 1; i < pieces.piecesLength; i++){
            setBackgroundResourceValue(i, pieces.getPieceType(i));
        }
    }


    /**
     * Sets the background resource value (Color) of a button.
     *
     * @param buttonName Button name is the number for the button (1-9)
     * @param type The type can be either black, black-selected, white, white-selected, or empty.
     */
    public void setBackgroundResourceValue(int buttonName, String type){
        Button B = findViewById(getButtonNameToId(buttonName));
        switch (type.toLowerCase()){
            case "black":
                B.setBackgroundResource(R.drawable.blackbutton);
                break;
            case "black-selected":
                B.setBackgroundResource(R.drawable.blackselected);
                break;
            case "white":
                B.setBackgroundResource(R.drawable.whitebutton);
                break;
            case "white-selected":
                B.setBackgroundResource(R.drawable.whiteselected);
                break;
            case "empty":
                B.setBackgroundResource(R.drawable.emptybutton);
                break;
        }
    }

    /**
     * Takes the button name and gives you the button id. Useful for findViewById()
     *
     * @param buttonName Button name is the number for the button (1-9)
     * @return the true button id of a given name
     */
    private int getButtonNameToId(int buttonName){
        switch (buttonName){
            case 1:
                return B1.getId();
            case 2:
                return B2.getId();
            case 3:
                return B3.getId();
            case 4:
                return B4.getId();
            case 5:
                return B5.getId();
            case 6:
                return B6.getId();
            case 7:
                return B7.getId();
            case 8:
                return B8.getId();
            case 9:
                return B9.getId();
        }
        // should never get here
        return 0;
    }


}