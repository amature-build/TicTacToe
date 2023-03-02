import java.util.List;

public class Player {
    private String name;
    private List<Integer> posList;

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
}
