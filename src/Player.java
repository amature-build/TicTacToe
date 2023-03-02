import java.util.List;

public class Player implements IPlayer {
    private String name;
    private List<Integer> posList;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPosition(int position) {
        this.posList.add(position);
    }

    @Override
    public int getCurrentPos() {
        int curLen = this.posList.size();
        int curIndex = curLen - 1;
        return this.posList.get(curIndex);
    }

    @Override
    public List<Integer> getPosHistory() {
        return this.posList;
    }
}
