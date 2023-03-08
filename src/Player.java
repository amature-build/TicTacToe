import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Integer> posList = new ArrayList<>();
    private EPlayerType userType;
    private EPlayerState state = EPlayerState.INACTIVE;
    private String placementValue;

    public Player(){}
    public Player(String name) {
        this.name = name;
    }

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
        String msg = "Congratulation! " +
                this.name +
                ".\nYou have won the game.";

        return msg;
    }
    public String promptLose(){
        String msg = "Too bad, " +
                this.name +
                ".\nYou have lost the game.";

        return msg;
    }
    public String promptDraw(){
        String msg = "It's a draw.\nTry harder next time.";

        return msg;
    }
}
