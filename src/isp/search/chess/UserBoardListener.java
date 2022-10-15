package isp.search.chess;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import isp.search.chess.gui.SinglePlayerPanel;
import isp.search.chess.util.BoardPosition;

public class UserBoardListener implements MouseInputListener{
    private BoardPosition selectedTile = null;
    private UserInputListener callback;
    
    public UserBoardListener(UserInputListener callback){
        this.callback = callback;
    }

    public void register(UserInputListener userInputListenerCallback) {
        //userInputListenerCallback.methodToCallBack();
    }


    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {

        int boardX = (int) Math.floor(1.0f * e.getX() / SinglePlayerPanel.BOARD_SIZE * SinglePlayerPanel.ROW_COUNT);
        int boardY = (int) Math.floor(1.0f * e.getY() / SinglePlayerPanel.BOARD_SIZE * SinglePlayerPanel.ROW_COUNT);

        BoardPosition boardPosition = new BoardPosition(boardX, boardY);

        if(selectedTile == boardPosition){
            selectedTile = null;
        }else{
            selectedTile = boardPosition;
        }

        callback.onTileSelectionChange(selectedTile);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    public BoardPosition getSelectedTile(){
        return selectedTile;
    }
}
