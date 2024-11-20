import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame implements TicTacToeView {

    private JButton[][] buttons;

    public TicTacToeFrame(){
        super("Tic Tac Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(TicTacToeModel.SIZE, TicTacToeModel.SIZE));

        TicTacToeModel model = new TicTacToeModel();
        model.addTicTacToeView(this);

        buttons = new JButton[TicTacToeModel.SIZE][TicTacToeModel.SIZE];

        for (int i = 0; i < TicTacToeModel.SIZE; i++) {
            for (int j = 0; j < TicTacToeModel.SIZE; j++) {
                JButton b = new JButton(" ");
                b.setActionCommand(i + " " + j);
                buttons[i][j] = b;
                this.add(b);
                int x = i;
                int y = j;
                b.addActionListener(e -> model.play(x, y));
            }
        }
        this.setSize(500,500);
        this.setVisible(true);
    }
    @Override
    public void update(TicTacToeEvent event) {
        String label = event.isTurn()? "X":"O";
        buttons[event.getX()][event.getY()].setText(label);

        TicTacToeModel.Status status = event.getStatus();

        if(status==TicTacToeModel.Status.X_WON || status==TicTacToeModel.Status.O_WON || status==TicTacToeModel.Status.TIE) {
            String finalMessage = null;
            switch (status) {
                case TIE -> finalMessage = "Tie Game";
                case X_WON -> finalMessage = "X won";
                case O_WON -> finalMessage = "O won";
            }
            for (int i = 0; i<3; i++) {
                for (int j = 0; j< 3; j++) {
                    buttons[i][j].setEnabled(false);
                }
            }
            JOptionPane.showMessageDialog(this, finalMessage, "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new TicTacToeFrame();
    }
}
