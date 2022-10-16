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


    @Override
    public void move() {

        GameState currentGameState = chessGame.getGameState();

        //new Alpha Beta Pruning with eval method
        AlphaBetaPruning alphaBetaPruning = new AlphaBetaPruning(Evaluator::benediktsEvaluator);

        List<Move> allLegalMoves = MoveCalculator.getAllLegalMoves(currentGameState, this.pieceColor);
        Move bestMove = null;
        double bestMoveEval = -Double.MAX_VALUE;

        //for every move: select best
        for(Move legalMove : allLegalMoves) {
                        //clone gameState and move
            String currentGameFenString = FenLoader.generateFenStringFromGameState(currentGameState);
            GameState clonedGameState = FenLoader.loadGameStateFromFenString(currentGameFenString);

            clonedGameState.movePieceWithLegalCheck(clonedGameState.getPieceAtPosition(legalMove.getOldBoardPosition()), legalMove.getNewBoardPosition());

            //System.out.println(-Double.MAX_VALUE >= 0);
            int depth = 1;
            double evalOfMove = alphaBetaPruning.pruning_min(clonedGameState, this.pieceColor, depth, -Double.MAX_VALUE, Double.MAX_VALUE);

            //System.out.println(String.format("Move for %s: %s with eval of %s", this.pieceColor, legalMove, evalOfMove));

            if(evalOfMove >= bestMoveEval){
                bestMove = legalMove;
                bestMoveEval = evalOfMove;

            }

        }

        System.out.println(String.format("Best Move for %s: %s with eval of %s", this.pieceColor, bestMove, bestMoveEval));
        System.out.println("------------------------------------");

        //move best move
        currentGameState.movePieceWithLegalCheck(currentGameState.getPieceAtPosition(bestMove.getOldBoardPosition()), bestMove.getNewBoardPosition());

    }
}
