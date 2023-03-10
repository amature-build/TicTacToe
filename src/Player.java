import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private final List<Integer> posList = new ArrayList<>();
    private EPlayerType userType;
    private EPlayerState state = EPlayerState.INACTIVE;
    private String placementValue;

    public Player(){}

    /**
     * @return Output player's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Clear out past history
     */
    public void resetHistory() {
        this.posList.clear();
    }

    /**
     * set argument as player's name
     * @param name player's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param position set player's latest position
     */
    public void setPosition(int position) {
        this.posList.add(position);
    }

    /**
     * @return Outputs player's latest position
     */
    public int getCurrentPos() {
        int curLen = this.posList.size();
        int curIndex = curLen - 1;
        return this.posList.get(curIndex);
    }

    /**
     * @return Outputs player's entire position
     */
    public List<Integer> getPosHistory() {
        return this.posList;
    }

    public void setPlayerActive(){
        this.state = EPlayerState.ACTIVE;
    }
    public void setPlayerInactive(){
        this.state = EPlayerState.INACTIVE;
    }
    public EPlayerState getPlayerState(){
        return this.state;
    }
    public void setPlacementValue(String placementValue){
        this.placementValue = placementValue;
    }
    public String getPlacementValue(){
        return this.placementValue;
    }
    public void setUserTypeUser(){
        this.userType = EPlayerType.USER;
    }
    public void setUserTypeCPU(){
        this.userType = EPlayerType.CPU;
    }
    public EPlayerType getUserType(){
        return this.userType;
    }
    public String promptWon(){

        return "Congratulation! %s.\nYou have won the game.".formatted(this.name);
    }
    public String promptLose(){

        return "Too bad, %s.\nYou have lost the game.".formatted(this.name);
    }
    public String promptDraw(){

        return "It's a draw.\nTry harder next time %s.".formatted(this.name);
    }
}
