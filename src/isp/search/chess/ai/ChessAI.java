package isp.search.chess.ai;

import isp.search.chess.ChessGame;
import isp.search.chess.GameState;
import isp.search.chess.Piece;
import isp.search.chess.enums.PieceColor;
import isp.search.chess.util.BoardPosition;
import isp.search.chess.util.MoveCalculator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ChessAI {
    private GameState gameState;
    private PieceColor pieceColor;

    public ChessAI(GameState gameState, PieceColor pieceColor) {
        this.gameState = gameState;
        this.pieceColor = pieceColor;
    }


    public void move() {

        List<Piece> randomPiecesThatCanMove = gameState.getPieces().stream()
                .filter(p -> p.getPieceColor() == pieceColor)
                .filter(p -> MoveCalculator.getLegalMoves(gameState, p).size() > 0)
                .toList();

        Piece randomPiece = randomPiecesThatCanMove.get(ThreadLocalRandom.current().nextInt(randomPiecesThatCanMove.size()));

        List<BoardPosition> randomMoves = MoveCalculator.getLegalMoves(gameState, randomPiece);
        BoardPosition randomMove = randomMoves.get(ThreadLocalRandom.current().nextInt(randomMoves.size()));

        gameState.movePieceWithLegalCheck(randomPiece, randomMove);
    }
}
