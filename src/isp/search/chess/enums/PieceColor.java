package isp.search.chess.enums;

public enum PieceColor {
    WHITE,
    BLACK;

    public static PieceColor getByFenChar(char fenChar) {
        return Character.isUpperCase(fenChar) ? WHITE : BLACK;
    }
}
