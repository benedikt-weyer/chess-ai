package isp.search.chess.ai;

import isp.search.chess.GameState;
import isp.search.chess.Piece;
import isp.search.chess.enums.PieceColor;
import isp.search.chess.enums.PieceType;
import isp.search.chess.util.MoveCalculator;

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


    public static double evaluatePiecesByStaticValue(GameState currentGameState) {

        double blackPieceValueSum = currentGameState.getPieces().stream()
                .filter(p -> p.getPieceColor() == PieceColor.BLACK)
                .mapToDouble(Evaluator::mapToPieceValueStatic)
                .sum();

        double whitePieceValueSum = currentGameState.getPieces().stream()
                .filter(p -> p.getPieceColor() == PieceColor.WHITE)
                .mapToDouble(Evaluator::mapToPieceValueStatic)
                .sum();

        return whitePieceValueSum - blackPieceValueSum;
    }

    public static double relativeMovePossibilitiesEvaluator(GameState currentGameState){

        //limit opponents movement & maximize own movement
        double whiteMoveCount = MoveCalculator.getAllLegalMoves(currentGameState, PieceColor.WHITE).size();
        double blackMoveCount = MoveCalculator.getAllLegalMoves(currentGameState, PieceColor.BLACK).size();

        double whiteMoveCountRelativeToPieceCount = whiteMoveCount / currentGameState.getPieces().stream().filter(p -> p.getPieceColor() == PieceColor.WHITE).count();
        double blackMoveCountRelativeToPieceCount = blackMoveCount / currentGameState.getPieces().stream().filter(p -> p.getPieceColor() == PieceColor.BLACK).count();

        return whiteMoveCountRelativeToPieceCount - blackMoveCountRelativeToPieceCount;

    }

    public static double checkmateEvaluator(GameState currentGameState){

        //limit opponents movement & maximize own movement
        double whiteMoveCount = MoveCalculator.getAllLegalMoves(currentGameState, PieceColor.WHITE).size();
        double blackMoveCount = MoveCalculator.getAllLegalMoves(currentGameState, PieceColor.BLACK).size();


        if(whiteMoveCount == 0) return -Double.MAX_VALUE;
        if(blackMoveCount == 0) return Double.MAX_VALUE;

        return 0;
    }


    public static double benediktsEvaluator(GameState currentGameState){

        double staticEvaluation = evaluatePiecesByStaticValue(currentGameState);
        double relativeMovePossibilitiesEvaluation = relativeMovePossibilitiesEvaluator(currentGameState);
        double checkmateEvaluation = checkmateEvaluator(currentGameState);

        return 1 * staticEvaluation + 0.1 * relativeMovePossibilitiesEvaluation + checkmateEvaluation;

    }


    private static double mapToPieceValueStatic(Piece piece){
        if(piece.getPieceType() == PieceType.PAWN){
            return 1;
        }
        if(piece.getPieceType() == PieceType.KNIGHT){
            return 3;
        }
        if(piece.getPieceType() == PieceType.BISHOP){
            return 3;
        }
        if(piece.getPieceType() == PieceType.ROOK){
            return 5;
        }
        if(piece.getPieceType() == PieceType.QUEEN){
            return 9;
        }

        return 0;
    }

}
