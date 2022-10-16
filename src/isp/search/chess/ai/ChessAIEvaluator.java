package isp.search.chess.ai;

import isp.search.chess.GameState;
import isp.search.chess.enums.PieceColor;

/*
 * Diese Klasse erweitert die Klasse ChessAI und implementiert die Bewertung der Spielsituation.
 */
public class ChessAIEvaluator extends ChessAI{
    public ChessAIEvaluator(GameState gameState, PieceColor pieceColor) {
        super(gameState, pieceColor);
    }

    /*
     * Diese Methode errechnet die Bewertung der Spielsituation.
     * Die Berechnung basiert auf der einfachsten Heuristik, die nur die Anzahl der Figuren auf dem Brett berücksichtigt.
     */
    public long calculateEvaluation() {
        long ownPieces = gameState.getPieces().stream()
                .filter(p -> p.getPieceColor() == pieceColor)
                .count();
        long enemyPieces = gameState.getPieces().stream()
                .filter(p -> p.getPieceColor() != pieceColor)
                .count();
        return ownPieces - enemyPieces;
    }

    /*
     * Diese Methode bewertet die Spielsituation und gibt die Bewertung zurück.
     */
    public String evaluate() {
        if (calculateEvaluation() > 0) {
            return pieceColor + "has an advantage by" + calculateEvaluation() + " pieces.";
        } else if (calculateEvaluation() < 0) {
            return pieceColor + "is at a disadvantage by" + calculateEvaluation() + " pieces.";
        } else {
            return "The game is balanced.";
        }
    }
}
