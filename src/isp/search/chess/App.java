package isp.search.chess;


import isp.search.chess.ai.ChessAI;
import isp.search.chess.ai.ChessAIRandom;
import isp.search.chess.enums.PieceColor;

public class App {
    public static void main(String[] args) {

        String fenStringStartingPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

        ChessGame chessGame = new ChessGame(fenStringStartingPosition);

        ChessAI randomChessAI = new ChessAIRandom(chessGame, PieceColor.WHITE);

        ChessAI randomChessAI2 = new ChessAIRandom(chessGame, PieceColor.BLACK);

        //LocalPlayer localPlayer = new LocalPlayer(chessGame, PieceColor.BLACK);

        //set players
        chessGame.setPlayerWhite(randomChessAI);
        chessGame.setPlayerBlack(randomChessAI2);

        chessGame.start();
    }

}
