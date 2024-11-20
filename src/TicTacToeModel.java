import java.util.ArrayList;
import java.util.List;

public class TicTacToeModel {

    public static final int SIZE = 3;
    public static final boolean X = true;
    public static final boolean O = false;

    public enum Status {X_WON, O_WON, TIE, UNDECIDED};

    private char[][] grid;
    private boolean turn;
    private Status status;

    private List<TicTacToeView> views;

    public TicTacToeModel() {
        grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = ' ';
            }
        }
        turn = X;
        status = Status.UNDECIDED;
        views = new ArrayList<>();
    }

    public void addTicTacToeView(TicTacToeView v){
        views.add(v);
    }

    private void changeTurn() {
        turn = !turn;
    }

    private int getNumFreeSquares() {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == ' ') {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean detectWinner(char player, int row, int col) {
        boolean winner = false;
        // check the row and column the user just played in
        if ((grid[row][0] == grid[row][1] && grid[row][0] == grid[row][2] && grid[row][0] == player)
                || (grid[0][col] == grid[1][col] && grid[0][col] == grid[2][col] && grid[0][col] == player)) {
            winner = true;
        } else if (row == col && grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2] && grid[0][0] == player) { // checks diagonal
            winner = true;
        } else if (row == 2 - col && grid[0][2] == grid[1][1] && grid[0][2] == grid[2][0] && grid[0][2] == player) { // checks other diagonal
            winner = true;
        }
        return winner;
    }

    public Status getStatus() { return status; }

    private void updateStatus(int row, int col) {
        if (detectWinner('X', row, col)) {
            status = Status.X_WON;
        } else if (detectWinner('O', row, col)) {
            status = Status.O_WON;
        } else if (getNumFreeSquares() == 0) {
            status = Status.TIE;
        }
    }

    public boolean getTurn() {return turn;}

    public void play(int x, int y) {
        if (grid[x][y] != ' ') return;
        grid[x][y] = turn? 'X' : 'O';
        updateStatus(x, y);

        for (TicTacToeView v: views){
            v.update(new TicTacToeEvent(this, x, y, turn, status));
        }
        changeTurn();
    }
}

