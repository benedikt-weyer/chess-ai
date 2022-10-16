package isp.search.chess.ai;

import isp.search.chess.ChessGame;
import isp.search.chess.GameState;
import isp.search.chess.enums.PieceColor;

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
    public long evaluate() {
        GameState currentGameState = chessGame.getGameState();

        long ownPieces = currentGameState.getPieces().stream()
                .filter(p -> p.getPieceColor() == pieceColor)
                .count();

        long enemyPieces = currentGameState.getPieces().stream()
                .filter(p -> p.getPieceColor() != pieceColor)
                .count();

        return ownPieces - enemyPieces;
    }

    /*
     * Diese Methode bewertet die Spielsituation und gibt die Bewertung zurück.
     */
    public void printEvaluation() {
        if (evaluate() > 0) {
            System.out.println(pieceColor + "has an advantage by" + evaluate() + " pieces.");
        } else if (evaluate() < 0) {
            System.out.println(pieceColor + "is at a disadvantage by" + evaluate() + " pieces.");
        } else {
            System.out.println("The game is balanced.");
        }
    }

    @Override
    public void move() {


        printEvaluation();
    }
}
