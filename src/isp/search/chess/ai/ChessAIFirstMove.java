package isp.search.chess.ai;

import isp.search.chess.GameState;
import isp.search.chess.Piece;
import isp.search.chess.enums.PieceColor;
import isp.search.chess.util.BoardPosition;
import isp.search.chess.util.MoveCalculator;

import java.util.List;

/**
 * Macht immer den erstmöglichen Zug, einfach um Dinge zu testen, da Spielzüge des Gegners einfach vorausschaubar sind.
 */
public class ChessAIFirstMove implements ChessAI {
    private GameState gameState;
    private PieceColor pieceColor;

    public ChessAIFirstMove(GameState gameState, PieceColor pieceColor) {
        this.gameState = gameState;
        this.pieceColor = pieceColor;
    }

    public void move() {
        Piece FirstPieceThatCanMove = gameState.getPieces().stream()
                .filter(p -> p.getPieceColor() == pieceColor)
                .filter(p -> MoveCalculator.getLegalMoves(gameState, p).size() > 0)
                .findFirst()
                .get();


        List<BoardPosition> moves = MoveCalculator.getLegalMoves(gameState, FirstPieceThatCanMove);
        BoardPosition firstMove = moves.get(0);

        gameState.movePieceWithLegalCheck(FirstPieceThatCanMove, firstMove);
    }
}
