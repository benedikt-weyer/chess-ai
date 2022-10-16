package isp.search.chess.ai;

import isp.search.chess.GameState;
import isp.search.chess.enums.PieceColor;

public class Evaluator {

    //positive means white is better
    public static double evaluatePieceCount(GameState currentGameState) {

        double blackPieceCount = currentGameState.getPieces().stream()
                .filter(p -> p.getPieceColor() == PieceColor.BLACK)
                .count();

        double whitePieceCount = currentGameState.getPieces().stream()
                .filter(p -> p.getPieceColor() == PieceColor.WHITE)
                .count();

        return whitePieceCount - blackPieceCount;
    }

}
