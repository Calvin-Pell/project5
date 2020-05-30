package mod;

public class Computer {

    //INSTANCE VARIABLES
    private int _curScore;
    private int _curMove;

    //GETTERS
    public int get_curScore() { return _curScore; }
    public int get_curMove() { return _curMove; }

    //SETTERS
    public void set_curMove(int _curMove) { this._curMove = _curMove; }

    //CONSTRUCTOR
    public Computer() {
        _curScore = 0;
        _curMove = -1;
    }

    /*
     *Sets curMove equal to a random int from 0 to 2.
     */
    public void genMove() {
        _curMove = (int)(Math.random() * 3);
    }

    /*
     *Increases curScore by 1 when the computer wins.
     */
    public void inc_curScore() {
        _curScore++;
    }

    /*
     *Resets the score to 0.
     */
    public void resetScore() {
        _curScore = 0;
    }
}
