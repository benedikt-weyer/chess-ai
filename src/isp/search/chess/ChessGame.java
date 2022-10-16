package isp.search.chess;

import isp.search.chess.ai.ChessAI;
import isp.search.chess.ai.ChessAIFirstMove;
import isp.search.chess.ai.ChessAIRandom;
import isp.search.chess.enums.PieceColor;
import isp.search.chess.gui.SingleplayerFrame;
import isp.search.chess.util.BoardPosition;
import isp.search.chess.util.FenLoader;

public class ChessGame implements UserInputListener {
    private GameState gameState;
    private ChessAI chessAI;
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

        //init ai
        this.chessAI = new ChessAIFirstMove(gameState, PieceColor.WHITE); //Hier AI tauschen
        chessAI.move(); // first move
        rerender();
    }

    @Override
    public void onTilePressed(BoardPosition pressedTile) {

        if (selectedTile != null && selectedTile.equals(pressedTile)) {
            selectedTile = null;
        } else { //if new tile pressed
            Piece pieceAtSelectedTile = gameState.getPieceAtPosition(selectedTile);
            if (pieceAtSelectedTile != null) {
                //move player
                if (!gameState.isGameFinished()) {
                    boolean moveSuccess = gameState.movePieceWithLegalCheck(pieceAtSelectedTile, pressedTile);

                    if (moveSuccess) {
                        //move ai
                        if (!gameState.isGameFinished()) {
                            chessAI.move();
                        }

                        //deselect move indicator
                        selectedTile = null;
                    } else {
                        selectedTile = pressedTile;
                    }
                } else {
                    selectedTile = pressedTile;
                }
            } else {
                selectedTile = pressedTile;
            }
        }

        //rerender
        rerender();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void rerender() {
        singleplayerFrame.renderBoard(gameState, userBoardListener, selectedTile);
    }
}
