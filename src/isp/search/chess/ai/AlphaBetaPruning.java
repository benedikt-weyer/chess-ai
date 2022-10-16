package isp.search.chess.ai;

import isp.search.chess.GameState;
import isp.search.chess.enums.PieceColor;
import isp.search.chess.util.BoardPosition;

public interface AlphaBetaPruning {
    // Aufgerufen durch pruning_max(jetzigerSpielstand, wieTief?,Farbe, -unendlich,unendlich)
    default int pruning_max(BoardPosition boardPosition, int depth, PieceColor pieceColor, int alpha, int beta) {
        if (depth == 0) {
            //return eval(); hier muss ne berechnungsmethode rein wonach wir bewerten
        }
        for(/* mal schauen*/) {
            alpha = Math.max(alpha,pruning_min(,alpha,beta));

            if(alpha >=beta) {
                return alpha;
            }
        }
        return alpha;
    }

    default int pruning_min(BoardPosition boardPosition, int depth, PieceColor pieceColor, int alpha, int beta) {
        if (depth == 0) {
            //return eval(); hier muss ne berechnungsmethode rein wonach wir bewerten
        }
        for(/* mal schauen*/) {
            beta = Math.min(beta,pruning_max(,alpha,beta));

            if(alpha >=beta) {
                return beta;
            }
        }

        return beta;
    }
}
