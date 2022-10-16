package isp.search.chess.ai;

import isp.search.chess.GameState;
import isp.search.chess.enums.PieceColor;
import isp.search.chess.util.BoardPosition;
import isp.search.chess.util.FenLoader;
import isp.search.chess.util.Move;
import isp.search.chess.util.MoveCalculator;

import java.util.List;
import java.util.function.Function;

public class AlphaBetaPruning {

    private Function<GameState, Double> evaluationFunction;

    //private GameState initGameState;

    public AlphaBetaPruning(Function<GameState, Double> evaluationFunction){
        this.evaluationFunction = evaluationFunction;
        //this.initGameState = initGameState;
    }

    // Aufgerufen durch pruning_max(jetzigerSpielstand, wieTief?,Farbe, -unendlich,unendlich)
    public double pruning_max(GameState currentGameState, PieceColor pieceColor, int depth, double alpha, double beta) {
        if(depth == 0) return pieceColor == PieceColor.WHITE ? evaluationFunction.apply(currentGameState) : -evaluationFunction.apply(currentGameState);


        List<Move> allLegalMoves = MoveCalculator.getAllLegalMoves(currentGameState, currentGameState.getTurnColor());


        for(Move legalMove : allLegalMoves) {

            //clone gameState and move
            String currentGameFenString = FenLoader.generateFenStringFromGameState(currentGameState);
            GameState clonedGameState = FenLoader.loadGameStateFromFenString(currentGameFenString);

            clonedGameState.movePieceWithLegalCheck(clonedGameState.getPieceAtPosition(legalMove.getOldBoardPosition()), legalMove.getNewBoardPosition());
            alpha = Math.max(alpha, pruning_min(clonedGameState, pieceColor, depth - 1, alpha, beta));

            if(alpha >= beta) {
                return alpha;
            }
        }

        return alpha;
    }

    public double pruning_min(GameState currentGameState, PieceColor pieceColor, int depth, double alpha, double beta) {
        if(depth == 0) return pieceColor == PieceColor.WHITE ? -evaluationFunction.apply(currentGameState) : evaluationFunction.apply(currentGameState);

        List<Move> allLegalMoves = MoveCalculator.getAllLegalMoves(currentGameState, currentGameState.getTurnColor()); // TODO PieceColor.WHITE
        for(Move legalMove : allLegalMoves) {

            //clone gameState and move
            String currentGameFenString = FenLoader.generateFenStringFromGameState(currentGameState);
            GameState clonedGameState = FenLoader.loadGameStateFromFenString(currentGameFenString);

            clonedGameState.movePieceWithLegalCheck(clonedGameState.getPieceAtPosition(legalMove.getOldBoardPosition()), legalMove.getNewBoardPosition());

            beta = Math.min(beta, pruning_max(clonedGameState, pieceColor, depth - 1, alpha, beta));

            if(alpha >= beta) {
                return beta;
            }
        }

        return beta;
    }


}
