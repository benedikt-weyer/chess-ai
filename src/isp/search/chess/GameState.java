package isp.search.chess;

import java.util.List;

import isp.search.chess.enums.PieceColor;
import isp.search.chess.enums.PieceType;
import isp.search.chess.util.BoardPosition;
import isp.search.chess.util.MoveCalculator;

public class GameState {
    private List<Piece> pieces;
    private PieceColor turnColor;
    private boolean castleRightsWhiteK, castleRightsWhiteQ, castleRightsBlackK, castleRightsBlackQ;
    private PieceColor winnerColor;
    private boolean gameFinished;

    private int totalMoveCount;

    private static final short ROW_COUNT = 8;


    public GameState(List<Piece> pieces, PieceColor turnColor,
                     boolean castleRightsWhiteK, boolean castleRightsWhiteQ, boolean castleRightsBlackK, boolean castleRightsBlackQ, int totalMoveCount) {
        this.pieces = pieces;
        this.turnColor = turnColor;
        this.castleRightsWhiteK = castleRightsWhiteK;
        this.castleRightsWhiteQ = castleRightsWhiteQ;
        this.castleRightsBlackK = castleRightsBlackK;
        this.castleRightsBlackQ = castleRightsBlackQ;
        this.totalMoveCount = totalMoveCount;
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


    public boolean movePieceWithLegalCheck(Piece piece, BoardPosition newBoardPosition){

        //if no piece at piece position return false
        if(piece == null) return false;

        //return false if not at turn
        if(piece.getPieceColor() != getTurnColor()) return false;

        //return false if game ended
        if(this.gameFinished) return false;

        //get legal moves
        List<BoardPosition> legalMoves = MoveCalculator.getLegalMoves(this, piece);
        // check if move is legal
        boolean moveIsLegal = legalMoves.stream()
            .anyMatch(legalMove -> newBoardPosition.equals(legalMove));

        if(moveIsLegal){
            //move
            hardMovePiece(piece, newBoardPosition);

            //increase move count
            totalMoveCount += 1;


            //test for check mate
            int sumOfMoves = getPieces().stream()
                    .filter(p -> p.getPieceColor() == getTurnColor()) //filter opponents
                    .map(p -> MoveCalculator.getLegalMoves(this, p).size())
                    .mapToInt(Integer::intValue)
                    .sum();

            if(sumOfMoves == 0){
                //check mate
                this.winnerColor = (getTurnColor() == PieceColor.BLACK) ? PieceColor.WHITE : PieceColor.BLACK;
                this.gameFinished = true;

                System.out.println(this.winnerColor + " Won!");
            }

            //check for max move count
            if(totalMoveCount > 500){
                this.gameFinished = true;

                System.out.println("Remi because max move count exceeded!");
            }

            return true;

        }else{
            return false;
        }      
        
    }

    public void hardMovePiece(Piece piece, BoardPosition newBoardPosition){

        //handle takes piece
        Piece pieceAtNewPosition = getPieceAtPosition(newBoardPosition);
        if(pieceAtNewPosition != null){
            pieces.remove(pieceAtNewPosition);
        }

        //handle pawn at end
        if(piece.getPieceType() == PieceType.PAWN){
            if((piece.getPieceColor() == PieceColor.BLACK && newBoardPosition.getBoardY() == 0)
                    || (piece.getPieceColor() == PieceColor.WHITE && newBoardPosition.getBoardY() == ROW_COUNT-1)){
                piece.setPieceType(PieceType.QUEEN);
            }
        }

        //handle castling
        if(piece.getPieceType() == PieceType.KING){
            if(Math.abs(piece.getBoardX() - newBoardPosition.getBoardX()) == 2){
                if(piece.getBoardX() - newBoardPosition.getBoardX() > 0){
                    Piece rookPiece = getPieceAtPosition(new BoardPosition(0, newBoardPosition.getBoardY()));
                    rookPiece.setBoardPosition(new BoardPosition(3, newBoardPosition.getBoardY()));
                }else{
                    Piece rookPiece = getPieceAtPosition(new BoardPosition(7, newBoardPosition.getBoardY()));
                    rookPiece.setBoardPosition(new BoardPosition(5, newBoardPosition.getBoardY()));
                }
            }
        }

        //handle setting castling rights
        if(piece.getPieceType() == PieceType.KING){
            if(piece.getPieceColor() == PieceColor.BLACK){
                castleRightsBlackK = false;
                castleRightsBlackQ = false;
            }else if(piece.getPieceColor() == PieceColor.WHITE){
                castleRightsWhiteK = false;
                castleRightsWhiteQ = false;
            }
        }

        if(piece.getPieceType() == PieceType.ROOK){
            if(piece.getPieceColor() == PieceColor.BLACK){
                if(piece.getBoardX() == 0) castleRightsBlackQ = false;
                if(piece.getBoardX() == 7) castleRightsBlackK = false;
            }else if(piece.getPieceColor() == PieceColor.WHITE){
                if(piece.getBoardX() == 0) castleRightsWhiteQ = false;
                if(piece.getBoardX() == 7) castleRightsWhiteK = false;
            }
        }


        //set new position of piece
        piece.setBoardPosition(newBoardPosition);




        //change turn
        setTurnColor(getTurnColor() == PieceColor.BLACK ? PieceColor.WHITE : PieceColor.BLACK);
    }

    public boolean isCastleRightsWhiteK() {
        return castleRightsWhiteK;
    }

    public boolean isCastleRightsWhiteQ() {
        return castleRightsWhiteQ;
    }

    public boolean isCastleRightsBlackK() {
        return castleRightsBlackK;
    }

    public boolean isCastleRightsBlackQ() {
        return castleRightsBlackQ;
    }

    public PieceColor getWinnerColor() {
        return winnerColor;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public int getTotalMoveCount() {
        return totalMoveCount;
    }
}
