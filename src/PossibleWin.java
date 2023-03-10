import java.util.ArrayList;
import java.util.List;

public class PossibleWin {
    private final List<Integer[]> moveList = new ArrayList<>();

    public PossibleWin(){
        this.moveList.add(new Integer[]{0, 1, 2});
        this.moveList.add(new Integer[]{3, 4, 5});
        this.moveList.add(new Integer[]{6, 7, 8});
        this.moveList.add(new Integer[]{0, 3, 6});
        this.moveList.add(new Integer[]{1, 4, 7});
        this.moveList.add(new Integer[]{2, 5, 8});
        this.moveList.add(new Integer[]{0, 4, 8});
        this.moveList.add(new Integer[]{2, 4, 6});
    }

    public List<Integer[]> getWinMoveList(){
        return this.moveList;
    }
    public void removeRow(int index){
        this.moveList.remove(index);
    }

}
