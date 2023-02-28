public class Player implements IPlayer {
    private String name;
    private State gameState;

    public Player(){
        this.gameState = State.PLAYING;
    }
    public Player(String name){
        this.name = name;
        this.gameState = State.PLAYING;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public State getPlayerState() {
        return gameState;
    }

    @Override
    public void setStatePlay() {
        this.gameState = State.PLAYING;
    }

    @Override
    public void setStateWin() {
        this.gameState = State.WIN;
    }

    @Override
    public void setStateLoss() {
        this.gameState = State.LOSS;
    }

    @Override
    public void setStateDraw() {
        this.gameState = State.DRAW;
    }
}
