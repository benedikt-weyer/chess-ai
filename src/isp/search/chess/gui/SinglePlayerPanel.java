package isp.search.chess.gui;

import isp.search.chess.GameState;
import isp.search.chess.Piece;
import isp.search.chess.UserBoardListener;
import isp.search.chess.util.BoardPosition;
import isp.search.chess.util.ChessPieceImageHelper;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;


public class SinglePlayerPanel extends JPanel{
    private static final int BOARD_X = 0;
    private static final int BOARD_Y = 0;
    public static final int BOARD_SIZE = 800;
    public static final int ROW_COUNT = 8;

    private GameState gameState;
    private UserBoardListener boardListener;

    private BufferedImage chessPieceAtlasImage;


    public SinglePlayerPanel(){
        super();
        chessPieceAtlasImage = ChessPieceImageHelper.getTextureAtlas();
    }
    
    @Override
    protected void paintComponent(Graphics g){
        //setup
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(new RenderingHints(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        ));
        g2.setRenderingHints(new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        ));
        g2.setRenderingHints(new RenderingHints(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR
        ));

        //loop over every square
        for(int x=0; x<ROW_COUNT; x++){
            for(int y=0; y<ROW_COUNT; y++){
                BoardPosition currentBoardPosition = new BoardPosition(x, y);

                //draw squares
                final int tileSize = BOARD_SIZE/ROW_COUNT;

                g2.setColor((x + y ) % 2 == 0 ? new Color(50, 65, 70) : Color.WHITE);
                //if tile is selected change color
                if(boardListener != null && boardListener.getSelectedTile() != null && boardListener.getSelectedTile().equals(currentBoardPosition)){
                    //g2.setColor((x + y ) % 2 == 0 ? new Color(200, 65, 70) : new Color(50, 65, 70));
                    g2.setColor(new Color(150, 165, 170));
                }

                g2.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);


                //draw pieces
                Piece currentPiece = gameState.getPieceAtPosition(currentBoardPosition);

                if(currentPiece != null){
                    Rectangle pieceBounds = ChessPieceImageHelper.getBoundsByPiece(currentPiece.getPieceType(), currentPiece.getPieceColor());

                    g2.drawImage(chessPieceAtlasImage, x * tileSize, y * tileSize , x * tileSize + tileSize, y * tileSize + tileSize, 
                        pieceBounds.x, pieceBounds.y, pieceBounds.x + pieceBounds.width, pieceBounds.y + pieceBounds.height, null);
                }

            }
        }
        
    }

    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }


    public void renderBoard(GameState gameState, UserBoardListener boardListener){
        this.gameState = gameState;
        this.boardListener = boardListener;
        repaint();
        
    }

}
