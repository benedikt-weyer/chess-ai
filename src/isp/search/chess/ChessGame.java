package isp.search.chess;

import isp.search.chess.gui.SingleplayerFrame;
import isp.search.chess.util.BoardPosition;
import isp.search.chess.util.FenLoader;

public class ChessGame implements UserInputListener{
    private GameState gameState;
    private UserBoardListener userBoardListener;
    private SingleplayerFrame singleplayerFrame;

    private BoardPosition selectedTile = null;

    public ChessGame(String fenString) {
        
        //load game state
        this.gameState = FenLoader.loadGameStateFromFenString(fenString);

        //init user controls
        this.userBoardListener = new UserBoardListener(this);


        singleplayerFrame = new SingleplayerFrame();
        singleplayerFrame.renderBoard(gameState, userBoardListener, selectedTile);
        singleplayerFrame.addBoardListener(userBoardListener);
    }

    @Override
    public void onTilePressed(BoardPosition pressedTile) {

        if(selectedTile != null && selectedTile.equals(pressedTile)){
            selectedTile = null;
        }else{ //if new tile pressed
            Piece pieceAtSelectedTile = gameState.getPieceAtPosition(selectedTile);
            if(pieceAtSelectedTile != null){
                //check if move is legal
                gameState.movePieceWithLegalCheck(pieceAtSelectedTile, pressedTile);
            }

            selectedTile = pressedTile;
        }

        //rerender
        singleplayerFrame.renderBoard(gameState, userBoardListener, selectedTile);
    }

}
