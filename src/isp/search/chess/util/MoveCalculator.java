package isp.search.chess.util;

import isp.search.chess.GameState;
import isp.search.chess.Piece;
import isp.search.chess.enums.PieceColor;
import isp.search.chess.enums.PieceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class MoveCalculator {
    private static final int ROW_COUNT = 8;
    
    public static List<BoardPosition> getLegalMoves(GameState gameState, BoardPosition piecePosition){

        List<BoardPosition> legalMoves = new ArrayList<>();
        
        Piece piece = gameState.getPieceAtPosition(piecePosition);


        if(piece != null){
            if(piece.getPieceType() == PieceType.PAWN){
                if(piece.getPieceColor() == PieceColor.WHITE){
                    //WHITE PAWNS
                    //if not at end of board
                    if(piece.getBoardY() < ROW_COUNT-1){                 

                        //move 2 forward
                        if(piece.getBoardY() == 1){
                            if(isSquareEmpty(gameState, piece.getBoardX(), piece.getBoardY()+1) && isSquareEmpty(gameState, piece.getBoardX(), piece.getBoardY()+2)){
                                legalMoves.add(new BoardPosition(piece.getBoardX(), piece.getBoardY() + 2));
                            }
                        }

                        //move 1
                        //move forward
                        if(isSquareEmpty(gameState, piece.getBoardX(), piece.getBoardY()+1)){
                            legalMoves.add(new BoardPosition(piece.getBoardX(), piece.getBoardY() + 1));
                        }

                        if(piece.getBoardX() - 1 > 0){
                            //capture left

                            if(!isSquareEmpty(gameState, piece.getBoardX() - 1, piece.getBoardY() + 1) && !isSquareFriendly(gameState, piece.getBoardX() - 1, piece.getBoardY() + 1, PieceColor.WHITE)){
                                legalMoves.add(new BoardPosition(piece.getBoardX() -1, piece.getBoardY() + 1));
                            }

                        }else if(piece.getBoardX() + 1 <= ROW_COUNT-1){
                            //capture right

                            if(!isSquareEmpty(gameState, piece.getBoardX() + 1, piece.getBoardY() + 1) && !isSquareFriendly(gameState, piece.getBoardX() + 1, piece.getBoardY() + 1, PieceColor.WHITE)){
                                legalMoves.add(new BoardPosition(piece.getBoardX() + 1, piece.getBoardY() + 1));
                            }
                        }

                    }

                }else{
                    //BLACK PAWNS
                    //if not at end of board
                    if(piece.getBoardY() > 0){                 

                        //move 2 forward
                        if(piece.getBoardY() == ROW_COUNT-1-1){
                            if(isSquareEmpty(gameState, piece.getBoardX(), piece.getBoardY()-1) && isSquareEmpty(gameState, piece.getBoardX(), piece.getBoardY()-2)){
                                legalMoves.add(new BoardPosition(piece.getBoardX(), piece.getBoardY() - 2));
                            }
                        }

                        //move 1
                        //move forward
                        if(isSquareEmpty(gameState, piece.getBoardX(), piece.getBoardY()-1)){
                            legalMoves.add(new BoardPosition(piece.getBoardX(), piece.getBoardY() - 1));
                        }

                        if(piece.getBoardX() - 1 > 0){
                            //capture left

                            if(!isSquareEmpty(gameState, piece.getBoardX() - 1, piece.getBoardY() - 1) && !isSquareFriendly(gameState, piece.getBoardX() - 1, piece.getBoardY() - 1, PieceColor.BLACK)){
                                legalMoves.add(new BoardPosition(piece.getBoardX() - 1, piece.getBoardY() - 1));
                            }

                        }else if(piece.getBoardX() + 1 <= ROW_COUNT-1){
                            //capture right

                            if(!isSquareEmpty(gameState, piece.getBoardX() + 1, piece.getBoardY() - 1) && !isSquareFriendly(gameState, piece.getBoardX() + 1, piece.getBoardY() - 1, PieceColor.BLACK)){
                                legalMoves.add(new BoardPosition(piece.getBoardX() + 1, piece.getBoardY() - 2));
                            }
                        }

                    }
                }
            }else if(piece.getPieceType() == PieceType.KNIGHT){
                BoardPosition[] theoritacalKnightMoves = {
                    new BoardPosition(piece.getBoardX() - 1, piece.getBoardY() + 2),
                    new BoardPosition(piece.getBoardX() + 1, piece.getBoardY() + 2),
                    new BoardPosition(piece.getBoardX() + 2, piece.getBoardY() + 1),
                    new BoardPosition(piece.getBoardX() + 2, piece.getBoardY() - 1),
                    new BoardPosition(piece.getBoardX() + 1, piece.getBoardY() - 2),
                    new BoardPosition(piece.getBoardX() - 1, piece.getBoardY() - 2),
                    new BoardPosition(piece.getBoardX() - 2, piece.getBoardY() - 1),
                    new BoardPosition(piece.getBoardX() - 2, piece.getBoardY() + 1),
                };

                List<BoardPosition> possibleKnightMoves = Arrays.stream(theoritacalKnightMoves)
                    .filter(tkm -> isSquareOnBoard(tkm.getBoardX(), tkm.getBoardY()))
                    .filter(tkm -> isSquareEmpty(gameState, tkm.getBoardX(), tkm.getBoardY()) || !isSquareFriendly(gameState, tkm.getBoardX(), tkm.getBoardY(), piece.getPieceColor()))
                    .collect(Collectors.toList());

                //add all moves to legal moves
                legalMoves.addAll(possibleKnightMoves);


            }else if(piece.getPieceType() == PieceType.ROOK){

                legalMoves.addAll(scanLineForLegalMoves(gameState, piece, 1, 0));
                legalMoves.addAll(scanLineForLegalMoves(gameState, piece, -1, 0));
                legalMoves.addAll(scanLineForLegalMoves(gameState, piece, 0, 1));
                legalMoves.addAll(scanLineForLegalMoves(gameState, piece, 0, -1));

            }else if(piece.getPieceType() == PieceType.BISHOP){

                legalMoves.addAll(scanLineForLegalMoves(gameState, piece, 1, 1));
                legalMoves.addAll(scanLineForLegalMoves(gameState, piece, -1, 1));
                legalMoves.addAll(scanLineForLegalMoves(gameState, piece, 1, -1));
                legalMoves.addAll(scanLineForLegalMoves(gameState, piece, -1, -1));

            }else if(piece.getPieceType() == PieceType.KING){

                BoardPosition[] theoritacalKingMoves = {
                    new BoardPosition(piece.getBoardX(), piece.getBoardY() + 1),
                    new BoardPosition(piece.getBoardX() - 1, piece.getBoardY() + 1),
                    new BoardPosition(piece.getBoardX() - 1, piece.getBoardY()),
                    new BoardPosition(piece.getBoardX() - 1, piece.getBoardY() - 1),
                    new BoardPosition(piece.getBoardX(), piece.getBoardY() - 1),
                    new BoardPosition(piece.getBoardX() + 1, piece.getBoardY() - 1),
                    new BoardPosition(piece.getBoardX() + 1, piece.getBoardY()),
                    new BoardPosition(piece.getBoardX() + 1, piece.getBoardY() + 1),
                };


                List<BoardPosition> possibleKingMoves = Arrays.stream(theoritacalKingMoves)
                    .filter(tkm -> isSquareOnBoard(tkm.getBoardX(), tkm.getBoardY()))
                    .filter(tkm -> isSquareEmpty(gameState, tkm.getBoardX(), tkm.getBoardY()) || !isSquareFriendly(gameState, tkm.getBoardX(), tkm.getBoardY(), piece.getPieceColor()))
                    .collect(Collectors.toList());

                legalMoves.addAll(possibleKingMoves);

            }else if(piece.getPieceType() == PieceType.QUEEN){

                legalMoves.addAll(scanLineForLegalMoves(gameState, piece, 1, 0));
                legalMoves.addAll(scanLineForLegalMoves(gameState, piece, -1, 0));
                legalMoves.addAll(scanLineForLegalMoves(gameState, piece, 0, 1));
                legalMoves.addAll(scanLineForLegalMoves(gameState, piece, 0, -1));

                legalMoves.addAll(scanLineForLegalMoves(gameState, piece, 1, 1));
                legalMoves.addAll(scanLineForLegalMoves(gameState, piece, -1, 1));
                legalMoves.addAll(scanLineForLegalMoves(gameState, piece, 1, -1));
                legalMoves.addAll(scanLineForLegalMoves(gameState, piece, -1, -1));

            }

            /*if(withCheckCheck){
                
                //filter legal moves where king is in check
                return legalMoves.filter(move => {

                    const theoreticalBoardState = pieces.map(p => {return {...p}});

                    const toMovePiece = theoreticalBoardState.find(p => p.boardX == piece.getBoardX() && p.boardY == p.boardY);
                    toMovepiece.getBoardX() = move.newBoardX;
                    toMovepiece.getBoardY() = move.newBoardY;

                    return !isKingInCheck(theoreticalBoardState, toMovePiece.isWhite);

                });
            }else{
                console.log(legalMoves)

                return legalMoves;
            }*/
        }

        return legalMoves;
    }



    //--position heper--

    //check if square is not occupied by other piece
    private static boolean isSquareEmpty(GameState gameState, int boardX, int boardY){
        return gameState.getPieces().stream()
            .anyMatch(piece -> (piece.getBoardX() == boardX && piece.getBoardY() == boardY));
    }

    //check if square has a piece with the same color as given
    private static boolean isSquareFriendly(GameState gameState, int boardX, int boardY, PieceColor ownPieceColor){
        Piece piece = gameState.getPieceAtPosition(new BoardPosition(boardX, boardY));

        if(piece == null) return false;

        return piece.getPieceColor() == ownPieceColor;
    }

    //check board bounds
    private static boolean isSquareOnBoard(int boardX, int boardY){
        return boardX >= 0 && boardX <= ROW_COUNT-1 && boardY >= 0 && boardY <= ROW_COUNT-1;
    }

    /*const isKingInCheck = (gameState, isWhite) => {
        const kingToTestInCheck =  gameState.pieces.find(piece => piece.isWhite == isWhite && piece.type == PieceType.King);
        const attackingPieces = gameState.pieces.filter(piece => piece.isWhite == !isWhite);

        return attackingPieces.some(piece => {
            const pieceMoves = getLegalMoves(gameState, piece, false);

            return pieceMoves.some(move => move.newBoardX == kingToTestInCheck.boardX && move.newBoardY == kingToTestInCheck.boardY);
        });
    }*/

    //scans line for legal moves from piece, line direction is determined by x- and yIncrement
    private static List<BoardPosition> scanLineForLegalMoves(GameState gameState, Piece piece, int xIncrement, int yIncrement){

        List<BoardPosition> legalMoves = new ArrayList<>();

        int x = piece.getBoardX() + xIncrement;
        int y = piece.getBoardY() + yIncrement;

        while(isSquareOnBoard(x, y)){

            if(isSquareEmpty(gameState, x, y)){
                legalMoves.add(new BoardPosition(x, y));
            }else{
                if(!isSquareFriendly(gameState, x, y, piece.getPieceColor())){
                    legalMoves.add(new BoardPosition(x, y));
                }
                break;
            }
            
            x += xIncrement;
            y += yIncrement;
        }

        return legalMoves;
    }

}
