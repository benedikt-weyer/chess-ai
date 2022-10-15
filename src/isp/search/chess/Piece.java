package isp.search.chess;


import isp.search.chess.enums.PieceColor;
import isp.search.chess.enums.PieceType;
import isp.search.chess.util.BoardPosition;

public class Piece {
    private int boardX, boardY;
    private PieceType pieceType;
    private PieceColor pieceColor;


    public Piece(int boardX, int boardY, PieceType pieceType, PieceColor pieceColor) {
        this.boardX = boardX;
        this.boardY = boardY;
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }



    public int getBoardX() {
        return this.boardX;
    }

    public void setBoardX(int boardX) {
        this.boardX = boardX;
    }

    public int getBoardY() {
        return this.boardY;
    }

    public void setBoardY(int boardY) {
        this.boardY = boardY;
    }

    public void setBoardPosition(int boardX, int boardY){
        this.boardX = boardX;
        this.boardY = boardY;
    }

    public void setBoardPosition(BoardPosition newBoardPosition) {
        setBoardPosition(newBoardPosition.getBoardX(), newBoardPosition.getBoardY());
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }
    
}
