package isp.search.chess.ai;

import isp.search.chess.GameState;
import isp.search.chess.Piece;
import isp.search.chess.enums.PieceColor;
import isp.search.chess.enums.PieceType;
import isp.search.chess.util.BoardPosition;
import isp.search.chess.util.Move;
import isp.search.chess.util.MoveCalculator;

import java.util.List;

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

        //normalize
        return (whiteMoveCount - blackMoveCount) / (whiteMoveCount + blackMoveCount);

    }

    public static double checkmateEvaluator(GameState currentGameState){

        //limit opponents movement & maximize own movement
        List<Move> whiteMoves = MoveCalculator.getAllLegalMoves(currentGameState, PieceColor.WHITE);
        List<Move> blackMoves = MoveCalculator.getAllLegalMoves(currentGameState, PieceColor.BLACK);

        if(whiteMoves.size() == 0){

            //get king
            Piece king = currentGameState.getPieces().stream()
                    .filter(p -> p.getPieceColor() == PieceColor.WHITE)
                    .filter(p -> p.getPieceType() == PieceType.KING)
                    .findFirst()
                    .get();

            //check for stalemate
            boolean isInCheck = blackMoves.stream()
                    .anyMatch(move -> move.getNewBoardPosition().equals(king.getBoardPosition()));

            if(currentGameState.getTurnColor() == PieceColor.WHITE){
                if(isInCheck){
                    //check mate
                    return Double.NEGATIVE_INFINITY;
                }else{
                    //stalemate
                    return Double.POSITIVE_INFINITY;
                }
            }else{
                System.out.println("never called");
                if(isInCheck){
                    //check mate
                    return Double.POSITIVE_INFINITY;
                }else{
                    //stalemate
                    return Double.NEGATIVE_INFINITY;
                }
            }

        }

        if(blackMoves.size() == 0){

            //get king
            Piece king = currentGameState.getPieces().stream()
                    .filter(p -> p.getPieceColor() == PieceColor.BLACK)
                    .filter(p -> p.getPieceType() == PieceType.KING)
                    .findFirst()
                    .get();

            //check for stalemate
            boolean isInCheck = whiteMoves.stream()
                    .anyMatch(move -> move.getNewBoardPosition().equals(king.getBoardPosition()));

            if(currentGameState.getTurnColor() == PieceColor.BLACK){
                if(isInCheck){
                    //check mate
                    return Double.POSITIVE_INFINITY;
                }else{
                    //stalemate
                    return Double.NEGATIVE_INFINITY;
                }
            }else{
                System.out.println("never called");
                if(isInCheck){
                    //check mate
                    return Double.NEGATIVE_INFINITY;
                }else{
                    //stalemate
                    return Double.POSITIVE_INFINITY;
                }
            }


        }

        return 0;
    }


    public static double castleEvaluator(GameState currentGameState){

        //limit opponents movement & maximize own movement
        boolean whiteCastled = false, blackCastled = false;

        //white
        if(currentGameState.getPieceAtPosition(new BoardPosition(3, 0)) != null && currentGameState.getPieceAtPosition(new BoardPosition(3, 0)).getPieceType() == PieceType.ROOK
                && currentGameState.getPieceAtPosition(new BoardPosition(3, 0)).getPieceColor() == PieceColor.WHITE){
            if((currentGameState.getPieceAtPosition(new BoardPosition(2, 0)) != null && currentGameState.getPieceAtPosition(new BoardPosition(2, 0)).getPieceType() == PieceType.KING
                    && currentGameState.getPieceAtPosition(new BoardPosition(2, 0)).getPieceColor() == PieceColor.WHITE)
                || (currentGameState.getPieceAtPosition(new BoardPosition(1, 0)) != null && currentGameState.getPieceAtPosition(new BoardPosition(1, 0)).getPieceType() == PieceType.KING
                    && currentGameState.getPieceAtPosition(new BoardPosition(1, 0)).getPieceColor() == PieceColor.WHITE)
                ||(currentGameState.getPieceAtPosition(new BoardPosition(0, 0)) != null && currentGameState.getPieceAtPosition(new BoardPosition(0, 0)).getPieceType() == PieceType.KING
                    && currentGameState.getPieceAtPosition(new BoardPosition(0, 0)).getPieceColor() == PieceColor.WHITE)){
                whiteCastled = true;
            }
        }
        if(currentGameState.getPieceAtPosition(new BoardPosition(5, 0)) != null && currentGameState.getPieceAtPosition(new BoardPosition(5, 0)).getPieceType() == PieceType.ROOK
                && currentGameState.getPieceAtPosition(new BoardPosition(5, 0)).getPieceColor() == PieceColor.WHITE){
            if((currentGameState.getPieceAtPosition(new BoardPosition(6, 0)) != null && currentGameState.getPieceAtPosition(new BoardPosition(6, 0)).getPieceType() == PieceType.KING
                    && currentGameState.getPieceAtPosition(new BoardPosition(6, 0)).getPieceColor() == PieceColor.WHITE)
                    || (currentGameState.getPieceAtPosition(new BoardPosition(7, 0)) != null && currentGameState.getPieceAtPosition(new BoardPosition(7, 0)).getPieceType() == PieceType.KING
                    && currentGameState.getPieceAtPosition(new BoardPosition(7, 0)).getPieceColor() == PieceColor.WHITE)){
                whiteCastled = true;
            }
        }


        //black
        if(currentGameState.getPieceAtPosition(new BoardPosition(3, 7)) != null && currentGameState.getPieceAtPosition(new BoardPosition(3, 7)).getPieceType() == PieceType.ROOK
                && currentGameState.getPieceAtPosition(new BoardPosition(3, 7)).getPieceColor() == PieceColor.WHITE){
            if((currentGameState.getPieceAtPosition(new BoardPosition(2, 7)) != null && currentGameState.getPieceAtPosition(new BoardPosition(2, 7)).getPieceType() == PieceType.KING
                    && currentGameState.getPieceAtPosition(new BoardPosition(2, 7)).getPieceColor() == PieceColor.WHITE)
                    || (currentGameState.getPieceAtPosition(new BoardPosition(1, 7)) != null && currentGameState.getPieceAtPosition(new BoardPosition(1, 7)).getPieceType() == PieceType.KING
                    && currentGameState.getPieceAtPosition(new BoardPosition(1, 7)).getPieceColor() == PieceColor.WHITE)
                    ||(currentGameState.getPieceAtPosition(new BoardPosition(0, 7)) != null && currentGameState.getPieceAtPosition(new BoardPosition(0, 7)).getPieceType() == PieceType.KING
                    && currentGameState.getPieceAtPosition(new BoardPosition(0, 7)).getPieceColor() == PieceColor.WHITE)){
                blackCastled = true;
            }
        }
        if(currentGameState.getPieceAtPosition(new BoardPosition(5, 7)) != null && currentGameState.getPieceAtPosition(new BoardPosition(5, 7)).getPieceType() == PieceType.ROOK
                && currentGameState.getPieceAtPosition(new BoardPosition(5, 7)).getPieceColor() == PieceColor.WHITE){
            if((currentGameState.getPieceAtPosition(new BoardPosition(6, 7)) != null && currentGameState.getPieceAtPosition(new BoardPosition(6, 7)).getPieceType() == PieceType.KING
                    && currentGameState.getPieceAtPosition(new BoardPosition(6, 7)).getPieceColor() == PieceColor.WHITE)
                    || (currentGameState.getPieceAtPosition(new BoardPosition(7, 7)) != null && currentGameState.getPieceAtPosition(new BoardPosition(7, 7)).getPieceType() == PieceType.KING
                    && currentGameState.getPieceAtPosition(new BoardPosition(7, 7)).getPieceColor() == PieceColor.WHITE)){
                blackCastled = true;
            }
        }

        double whiteSum = (whiteCastled ? 3 : 0);
        whiteSum += (currentGameState.isCastleRightsWhiteQ() ? 0.9 : 0);
        whiteSum += (currentGameState.isCastleRightsWhiteK() ? 1 : 0);

        double blackSum = (blackCastled ? 3 : 0);
        blackSum += (currentGameState.isCastleRightsWhiteQ() ? 0.9 : 0);
        blackSum += (currentGameState.isCastleRightsBlackK() ? 1 : 0);

        return (whiteSum - blackSum);
    }


    public static double evaluatorV1(GameState currentGameState){

        double staticEvaluation = evaluatePiecesByStaticValue(currentGameState);
        double checkmateEvaluation = checkmateEvaluator(currentGameState);


        return 1 * staticEvaluation + checkmateEvaluation + 0.1 * Math.random();

    }


    public static double evaluatorV2(GameState currentGameState){

        double staticEvaluation = evaluatePiecesByStaticValue(currentGameState);
        double relativeMovePossibilitiesEvaluation = relativeMovePossibilitiesEvaluator(currentGameState);
        double checkmateEvaluation = checkmateEvaluator(currentGameState);

        return 1 * staticEvaluation + 0.3 * relativeMovePossibilitiesEvaluation + checkmateEvaluation + 0.1 * Math.random();

    }

    public static double evaluatorV3(GameState currentGameState){

        double staticEvaluation = evaluatePiecesByStaticValue(currentGameState);
        double relativeMovePossibilitiesEvaluation = relativeMovePossibilitiesEvaluator(currentGameState);
        double checkmateEvaluation = checkmateEvaluator(currentGameState);
        double castleEvaluation = castleEvaluator(currentGameState);

        return 1 * staticEvaluation + 0.5 * castleEvaluation + 0.3 * relativeMovePossibilitiesEvaluation + checkmateEvaluation + 0.1 * Math.random();

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
