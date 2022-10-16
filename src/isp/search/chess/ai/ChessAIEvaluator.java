package isp.search.chess.ai;

import isp.search.chess.ChessGame;
import isp.search.chess.GameState;
import isp.search.chess.enums.PieceColor;
import isp.search.chess.util.FenLoader;
import isp.search.chess.util.Move;
import isp.search.chess.util.MoveCalculator;

import java.util.List;

/*
 * Diese Klasse erweitert die Klasse ChessAI und implementiert die Bewertung der Spielsituation.
 */
public class ChessAIEvaluator extends ChessAI{
    public ChessAIEvaluator(ChessGame chessGame, PieceColor pieceColor) {
        super(chessGame, pieceColor);
    }

    /*
     * Diese Methode errechnet die Bewertung der Spielsituation.
     * Die Berechnung basiert auf der einfachsten Heuristik, die nur die Anzahl der Figuren auf dem Brett berücksichtigt.
     */



    /*
     * Diese Methode bewertet die Spielsituation und gibt die Bewertung zurück.
     */
    public void printEvaluation() {
        /*if (evaluate() > 0) {
            System.out.println(pieceColor + "has an advantage by" + evaluate() + " pieces.");
        } else if (evaluate() < 0) {
            System.out.println(pieceColor + "is at a disadvantage by" + evaluate() + " pieces.");
        } else {
            System.out.println("The game is balanced.");
        }*/
    }

    @Override
    public void move() {

        GameState currentGameState = chessGame.getGameState();

        //new Alpha Beta Pruning with eval method
        AlphaBetaPruning alphaBetaPruning = new AlphaBetaPruning(Evaluator::evaluatePieceCount);

        List<Move> allLegalMoves = MoveCalculator.getAllLegalMoves(currentGameState, this.pieceColor); // TODO PieceColor.WHITE
        Move bestMove = null;
        double bestMoveEval = -Double.MAX_VALUE;

        //for every move: select best
        for(Move legalMove : allLegalMoves) {
                        //clone gameState and move
            String currentGameFenString = FenLoader.generateFenStringFromGameState(currentGameState);
            GameState clonedGameState = FenLoader.loadGameStateFromFenString(currentGameFenString);

            clonedGameState.movePieceWithLegalCheck(clonedGameState.getPieceAtPosition(legalMove.getOldBoardPosition()), legalMove.getNewBoardPosition());

            double evalOfMove = alphaBetaPruning.pruning_max(clonedGameState, this.pieceColor, 0, -Double.MAX_VALUE, Double.MAX_VALUE);

            if(evalOfMove >= bestMoveEval){
                bestMove = legalMove;
                bestMoveEval = evalOfMove;
                System.out.println(String.format("Best Move: %s with eval of %s", legalMove, evalOfMove));
            }

            System.out.println(String.format("Move: %s with eval of %s", legalMove, evalOfMove));
        }


        //move best move
        currentGameState.movePieceWithLegalCheck(currentGameState.getPieceAtPosition(bestMove.getOldBoardPosition()), bestMove.getNewBoardPosition());

    }
}
