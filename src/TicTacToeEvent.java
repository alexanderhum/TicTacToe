import java.util.EventObject;

public class TicTacToeEvent extends EventObject {
    private TicTacToeModel model;
    private int x;
    private int y;
    private boolean turn;
    private TicTacToeModel.Status status;

    public TicTacToeEvent(TicTacToeModel model, int x, int y, boolean turn, TicTacToeModel.Status status) {
        super(model);
        this.x = x;
        this.y = y;
        this.turn = turn;
        this.status = status;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isTurn() {
        return turn;
    }

    public TicTacToeModel.Status getStatus() { return status; }
}
