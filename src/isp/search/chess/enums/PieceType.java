package isp.search.chess.enums;

public enum PieceType {
    ROOK,
    BISHOP,
    KNIGHT,
    PAWN,
    KING,
    QUEEN;


    public static PieceType getByFenChar(char fenChar) {

        switch (Character.toLowerCase(fenChar)) {
            case 'r':
                return ROOK;
            case 'b':
                return BISHOP;
            case 'n':
                return KNIGHT;
            case 'p':
                return PAWN;
            case 'k':
                return KING;
            case 'q':
                return QUEEN;
            default:
                return null;
        }

    }
}
