import java.util.ArrayList;
import java.util.List;

public class PossibleWin {
    List<int[]> moveList = new ArrayList<>();

    public PossibleWin(){
        this.moveList.add(new int[]{0, 1, 2});
        this.moveList.add(new int[]{3, 4, 5});
        this.moveList.add(new int[]{6, 7, 8});
        this.moveList.add(new int[]{0, 3, 6});
        this.moveList.add(new int[]{1, 4, 7});
        this.moveList.add(new int[]{2, 5, 8});
        this.moveList.add(new int[]{0, 4, 8});
        this.moveList.add(new int[]{2, 4, 6});
    }

    public List<int[]> getWinMoveList(){
        return this.moveList;
    }

}
