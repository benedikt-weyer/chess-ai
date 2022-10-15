package isp.search.chess;

import java.util.List;

import isp.search.chess.enums.PieceColor;
import isp.search.chess.util.BoardPosition;
import isp.search.chess.util.MoveCalculator;

public class GameState {
    private List<Piece> pieces;
    private PieceColor turnColor;


    public GameState(List<Piece> pieces, PieceColor turnColor) {
        this.pieces = pieces;
        this.turnColor = turnColor;
    }



    public List<Piece> getPieces() {
        return this.pieces;
    }

    public PieceColor getTurnColor() {
        return this.turnColor;
    }

    public void setTurnColor(PieceColor turnColor) {
        this.turnColor = turnColor;
    }


    public Piece getPieceAtPosition(BoardPosition boardPosition){
        return pieces.stream()
            .filter(piece -> piece.getBoardPosition().equals(boardPosition))
            .findFirst()
            .orElse(null);
    }


    public boolean movePiece(Piece piece, BoardPosition newBoardPosition){

        //if no piece at piece position return false
        if(piece == null) return false;

        //get legal moves
        List<BoardPosition> legalMoves = MoveCalculator.getLegalMoves(this, piece);
        // check if move is legal
        boolean moveIsLegal = legalMoves.stream()
            .anyMatch(legalMove -> newBoardPosition.equals(legalMove));

        if(moveIsLegal){
            //handle takes piece
            Piece pieceAtNewPosition = getPieceAtPosition(newBoardPosition);
            if(pieceAtNewPosition != null){
                pieces.remove(pieceAtNewPosition);
            }

            //set new position of piece
            piece.setBoardPosition(newBoardPosition);

            return true;

        }else{
            return false;
        }      
        
    }


}
