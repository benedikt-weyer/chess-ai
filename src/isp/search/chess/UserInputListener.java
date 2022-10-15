package isp.search.chess;

import isp.search.chess.util.BoardPosition;

public interface UserInputListener {

    void onTileSelectionChange(BoardPosition boardPosition);
}
