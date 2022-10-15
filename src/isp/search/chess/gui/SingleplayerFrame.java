package isp.search.chess.gui;


import javax.swing.JFrame;


import isp.search.chess.GameState;
import isp.search.chess.UserBoardListener;
import isp.search.chess.util.BoardPosition;

public class SingleplayerFrame extends JFrame {
    private SinglePlayerPanel singlePlayerPanel;

    public SingleplayerFrame(){


        setTitle("Singleplayer Frame");
        setSize(1300, 1300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        singlePlayerPanel = new SinglePlayerPanel();
        

        getContentPane().add(singlePlayerPanel);

        

        
        //show frame
        setVisible(true);
    }


    public void renderBoard(GameState gameState, UserBoardListener boardListener, BoardPosition selectedTile){
        singlePlayerPanel.renderBoard(gameState, boardListener, selectedTile);
    }

    public void addBoardListener(UserBoardListener boardListener){
        //this.boardListener = boardListener;
        singlePlayerPanel.addMouseListener(boardListener);
    }

}
