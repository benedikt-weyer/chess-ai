package isp.search.chess.ai;

import isp.search.chess.GameState;
import isp.search.chess.enums.PieceColor;

public abstract class ChessAI {
    public GameState gameState;
    public PieceColor pieceColor;

    public ChessAI(GameState gameState, PieceColor pieceColor) {
            this.gameState = gameState;
            this.pieceColor = pieceColor;
    }
    public void move() {}
}
