package one.scarecrow.games.OPMT.Game;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Pieces {

    String[] pieces = new String[10];
    public int piecesLength = 10;

    /**
     * Sets all pieces to the default starting position.
     *
     * @return void
     */
    public void resetPieces() {
        // 1 2 3 8 = Black
        pieces[1] = "black";
        pieces[2] = "black";
        pieces[3] = "black";
        pieces[8] = "black";
        // 4 5 6 7 = White
        pieces[4] = "white";
        pieces[5] = "white";
        pieces[6] = "white";
        pieces[7] = "white";
        // 9 = Empty
        pieces[9] = "empty";
    }


    /**
     * Sets a piece, only to be used in this class.
     *
     * @param id id of the button to change values
     * @param type the value to be changed, can only be black, black-selected, white, white-selected, or empty
     */
    private void setPieceType(int id, String type){
        pieces[id] = type.toLowerCase();
    }

    /**
     * Gets the piece type.
     *
     * @param n The id of the piece
     * @return The type of piece it is. Will only return black, black-selected, white, white-selected, or empty.
     */
    public String getPieceType(int n) {

        return pieces[n];
    }

    /**
     * Finds where the empty space is.
     *
     * @return the id of the empty space.
     */
    public int findEmptySpace() {
        for (int i = 1; i < pieces.length; i++) {
            if (getPieceType(i).contains("empty")) {
                return i;
            }
        }
        //Should never get here, there should always be an empty space
        return 0;
    }

    /**
     * Finds all the black pieces on the board.
     *
     * @return a list of all black pieces id that is on the board.
     */
    public List<Integer> findAllBlackPieces(){
        List<Integer> found = new ArrayList<Integer>();
        for (int i = 1; i < pieces.length; i++) {
            if (getPieceType(i).contains("black")) {
                found.add(i);
            }
        }
        return found;
    }

    /**
     * Finds all the white pieces on the board.
     *
     * @return a list of all white pieces id that is on the board.
     */
    public List<Integer> findAllWhitePieces(){
        List<Integer> found = new ArrayList<Integer>();
        for (int i = 1; i < pieces.length; i++) {
            if (getPieceType(i).contains("white")) {
                found.add(i);
            }
        }
        return found;
    }




    /**
     * Selects a piece
     *
     * @param buttonName name of button and piece (1-9)
     */
    public void select(int buttonName) {
        String type = getPieceType(buttonName);
        //Do not select the empty space.
        if(!type.contains("empty")){
            type = type + "-selected";
            setPieceType(buttonName, type);
        }
    }

    /**
     * Checks if there are any selected pieces
     *
     * @return true if there is a piece selected
     */
    public boolean isAnySelected() {
        if (getSelectedPiece() == -1){
            return false;
        }
        return true;
    }

    /**
     * Returns the selected piece
     *
     * @return the button name of the selected piece, if there is no selected piece it will return -1.
     */
    public int getSelectedPiece(){
        for (int i = 1; i < pieces.length; i++){
            String type = getPieceType(i);
            if(type.contains("selected")) {
                return i;
            }
        }
        return -1;
    }

    /**
     *  Unselects a piece
     *
     * @param selectedPiece a piece that is already selected, to be unselected.
     */
    public void unselect(int selectedPiece) {
        String type = getPieceType(selectedPiece);
        if (type.contains("white")){
            setPieceType(selectedPiece, "white");
        }else if(type.contains("black")){
            setPieceType(selectedPiece, "black");
        }
    }

    /**
     *  This will move the selected piece to buttonName (should be the empty space)
     *
     * @param buttonName should always be the empty space
     */
    public void movePiece(int buttonName, int selectedButton) {
        String buttonNameType = getPieceType(buttonName);
        int selectedPieceId = getSelectedPiece();
        String selectedPieceType = getPieceType(selectedPieceId);

        // Basically just switch them both.
        setPieceType(buttonName, selectedPieceType);
        setPieceType(selectedPieceId, buttonNameType);
    }
}