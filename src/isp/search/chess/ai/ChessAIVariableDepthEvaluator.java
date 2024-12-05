package isp.search.chess.ai;

import isp.search.chess.ChessGame;
import isp.search.chess.GameState;
import isp.search.chess.enums.PieceColor;
import isp.search.chess.util.FenLoader;
import isp.search.chess.util.Move;
import isp.search.chess.util.MoveCalculator;

import java.util.List;

public class ChessAIVariableDepthEvaluator extends ChessAI{

    public ChessAIVariableDepthEvaluator(ChessGame chessGame, PieceColor pieceColor) {
        super(chessGame, pieceColor);
    }


    @Override
    public void move() {


    }
}
