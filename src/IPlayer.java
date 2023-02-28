public interface IPlayer {
    enum State {
        PLAYING,
        WIN,
        LOSS,
        DRAW
    }
    State getPlayerState();
    void setStatePlay();
    void setStateWin();
    void setStateLoss();
    void setStateDraw();
}
