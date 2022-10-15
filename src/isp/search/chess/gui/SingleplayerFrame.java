package isp.search.chess.gui;


import javax.swing.JFrame;

import java.awt.Dimension;


import isp.search.chess.GameState;
import isp.search.chess.UserBoardListener;

public class SingleplayerFrame extends JFrame {
    private SinglePlayerPanel singlePlayerPanel;

    public SingleplayerFrame(){


        setTitle("Singleplayer Frame");
        setSize(1000, 1000);
        setPreferredSize(new Dimension(1000, 1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        singlePlayerPanel = new SinglePlayerPanel();
        

        getContentPane().add(singlePlayerPanel);

        

        
        //show frame
        setVisible(true);
    }


    public void renderBoard(GameState gameState, UserBoardListener boardListener){
        singlePlayerPanel.renderBoard(gameState, boardListener);
    }

    public void addBoardListener(UserBoardListener boardListener){
        //this.boardListener = boardListener;
        singlePlayerPanel.addMouseListener(boardListener);
    }

}
