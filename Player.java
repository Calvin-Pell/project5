package mod;

public class Player {

    //INSTANCE VARIABLE
    private int _hiScore;
    private int _curScore;
    private int _curMove;
    private int _wins;
    private int _curStreak;

    //GETTERS
    public int get_curScore() { return _curScore; }
    public int get_hiScore() { return _hiScore; }
    public int get_curMove() { return _curMove; }
    public int get_wins() { return _wins; }
    public int get_curStreak() { return _curStreak; }

    //SETTERS
    public void set_hiScore(int _hiScore) { this._hiScore = _hiScore; }
    public void set_curMove(int _curMove) { this._curMove = _curMove; }
    public void set_curStreak(int _curStreak) { this._curStreak = _curStreak; }
    public void set_wins(int _wins) { this._wins = _wins; }

    //CONSTRUCTOR
    public Player() {
        _hiScore = 0;
        _curScore = 0;
        _curMove = -1;
        _curStreak = 0;
        _wins = 0;
    }

    /*
     *Increases curScore by 1.
     */
    public void inc_curScore() {
        _curScore++;
    }

    /*
     *Increases wins by 1.
     */
    public void incWins() {
        _wins++;
    }

    /*
     *Resets curScore to 0.
     */
    public void resetScore() {_curScore = 0;}

    /*
     *Increases curStreak by 1. Also checks if curStreak is greater than hiScore. If it is,
     * then hiScore is set to be equal to curStreak.
     */
    public void incCurStreak() {
        _curStreak++;
        if(_curStreak > _hiScore)
            _hiScore = _curStreak;
    }

}

