package isp.search.chess.util;

import java.util.ArrayList;
import java.util.List;

import isp.search.chess.GameState;
import isp.search.chess.Piece;
import isp.search.chess.enums.PieceColor;
import isp.search.chess.enums.PieceType;

public class FenLoader {
    private static int rowCount = 8;
    
    public static GameState loadGameStateFromFenString(String fenString){

        //eg rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1

        String positionString = fenString.split(" ")[0];
        String turnColorChar = fenString.split(" ")[1];
        String castleRightSring = fenString.split(" ")[2];
        String turn1 = fenString.split(" ")[3];
        String turn2 = fenString.split(" ")[4];

        List<Piece> pieces = new ArrayList<>();

        //load position
        int positionPointer = 0;
        for(int i=0; i<positionString.length(); i++){

            //get current char from position string
            char positionChar = positionString.charAt(i);

            //if not new row
            if(positionChar != '/'){

                if(positionChar >= '0' && positionChar <= '9'){
                    //move pointer if position string has number

                    positionPointer += positionChar - '0';

                }else{
                    //add piece to position

                    PieceType pieceType = PieceType.getByFenChar(positionChar);
                    PieceColor pieceColor = PieceColor.getByFenChar(positionChar);

                    int boardX = positionPointer % rowCount;
                    int boardY = (int) Math.floor((double) positionPointer / rowCount);

                    Piece piece = new Piece(boardX, boardY, pieceType, pieceColor);

                    //add piece
                    pieces.add(piece);

                    //increment pointer
                    positionPointer++;
                }
            }
        }


        //determine turn color
        PieceColor turnColor = turnColorChar.charAt(0) == 'w' ? PieceColor.WHITE : PieceColor.BLACK;


        //create and return new game state
        GameState gameState = new GameState(pieces, turnColor);

        return gameState;
    }

}
