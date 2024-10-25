import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth=600;
    int boardHeight=700; //50px extra for text panel on top

    JFrame frame = new JFrame("Tic-Tac_Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currPlayer = playerX;

    boolean gameOver=false;
    int turns=0;

    JButton restart = new JButton();
    JPanel restartPanel = new JPanel();
    JLabel scoreLabel = new JLabel();
    JPanel ScorePanel = new JPanel();
    JPanel test = new JPanel();
    int scoreX=0;
    int scoreY=0;

    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    


        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);
        test.setLayout(new BorderLayout());
        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray);
        test.add(boardPanel, BorderLayout.CENTER);
        frame.add(test);
        // frame.add(boardPanel);

        ScorePanel.setLayout(new BorderLayout());
        ScorePanel.setBackground(Color.CYAN);
        scoreLabel.setLayout(new BorderLayout());
        scoreLabel.setFont(new Font("Arial", Font.BOLD,20));
        scoreLabel.setText("Player 1(X): "+scoreX + " Player 2(O): " +scoreY);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        ScorePanel.add(scoreLabel);
        frame.add(ScorePanel, BorderLayout.SOUTH);

        restart.setText("Restart");
        restart.setPreferredSize(new Dimension(100, 40));
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gameOver=false;
                textLabel.setText("Tic-Tac-Toe");
                for(int r=0;r<3;r++){
                    for(int c=0;c<3;c++){
                        board[r][c].setBackground(Color.DARK_GRAY);
                        board[r][c].setForeground(Color.WHITE);
                        turns=0;
                        board[r][c].setText("");
                    }   
                }
            }
        });
        restartPanel.setLayout(new BorderLayout());
        test.add(restart, BorderLayout.SOUTH);
        // frame.add(restartPanel,BorderLayout);
        


        for(int r=0; r<3;r++){
            for(int c=0;c<3;c++){
                JButton tile= new JButton();
                board[r][c]=tile;
                boardPanel.add(tile);


                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD,120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if(gameOver) return;
                        JButton tile = (JButton)e.getSource();
                        if(tile.getText()==""){
                            tile.setText(currPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                                currPlayer=currPlayer==playerX?playerO:playerX;
                                textLabel.setText(currPlayer+"'s turn");
                            }
                            
                        }
                        

                    }
                });
            }
        }
    }
    void checkWinner(){
        //horizontal
        for(int r=0;r<3;r++){
            if(board[r][0].getText()=="") continue;
            
            if(board[r][0].getText()==board[r][1].getText() && board[r][1].getText()==board[r][2].getText()){
                if(board[r][0].getText()=="X"){
                    scoreX++;
                }
                if(board[r][0].getText()=="O"){
                    scoreY++;
                }
                for(int i=0;i<3;i++){
                    setWinner(board[r][i]);
                }
                gameOver=true;
                return;
            }
        }
        //vertical
        for(int c=0;c<3;c++){
            if(board[0][c].getText()=="") continue;
            
            if(board[0][c].getText()==board[1][c].getText() && board[1][c].getText()==board[2][c].getText()){
                if(board[0][c].getText()=="X"){
                    scoreX++;
                }
                if(board[0][c].getText()=="O"){
                    scoreY++;
                }
                for(int i=0;i<3;i++){
                    setWinner(board[i][c]);
                }
                
                gameOver=true;
                return;
            }
        }

        //diagonally
        if(board[0][0].getText()==board[1][1].getText() && board[1][1].getText()==board[2][2].getText() && board[0][0].getText()!=""){
            if(board[0][0].getText()=="X"){
                scoreX++;
            }
            if(board[0][0].getText()=="O"){
                scoreY++;
            }
            for(int i=0;i<3;i++){
                setWinner(board[i][i]);

            }
            
            gameOver=true;
            return;
        }

        //anti-diagonally
        if(board[0][2].getText()==board[1][1].getText() && board[1][1].getText()==board[2][0].getText() && board[0][2].getText()!=""){
            if(board[0][2].getText()=="X"){
                scoreX++;
            }
            if(board[0][2].getText()=="O"){
                scoreY++;
            }
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver=true;
            return;
        }

        //tie
        if(turns==9){
            for(int r=0; r<3;r++){
                for(int c=0;c<3;c++){
                    setTie(board[r][c]);
                }
            }   
            gameOver=true; 
        }

    }
    void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currPlayer+ " is the winner!");
        scoreLabel.setText("Player 1(X): "+scoreX + " Player 2(O): " +scoreY);
    }
    
    void setTie(JButton tile){
        tile.setBackground(Color.gray);
        tile.setForeground(Color.yellow);
        textLabel.setText("It is a Tie!");
    }
}
