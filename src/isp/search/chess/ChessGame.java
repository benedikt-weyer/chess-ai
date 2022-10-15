package isp.search.chess;

import isp.search.chess.gui.SingleplayerFrame;
import isp.search.chess.util.BoardPosition;
import isp.search.chess.util.FenLoader;

public class ChessGame implements UserInputListener{
    private GameState gameState;
    private UserBoardListener userBoardListener;
    private SingleplayerFrame singleplayerFrame;

    public ChessGame(String fenString) {
        
        //load game state
        this.gameState = FenLoader.loadGameStateFromFenString(fenString);

        //init user controlls
        this.userBoardListener = new UserBoardListener(this);


        singleplayerFrame = new SingleplayerFrame();
        singleplayerFrame.renderBoard(gameState, userBoardListener);
        singleplayerFrame.addBoardListener(userBoardListener);
    }

    @Override
    public void onTileSelectionChange(BoardPosition boardPosition) {

        singleplayerFrame.renderBoard(gameState, userBoardListener);
    }

}
