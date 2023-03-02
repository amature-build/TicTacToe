import java.util.List;

public interface IPlayer {
    /**
     * @return Outputs player's name
     */
    String getName();

    /**
     * set player's name
     */
    void setName(String name);
    void setPosition(int position);
    /**
     * @return Outputs player's current position
     */
    int getCurrentPos();

    /**
     * @return Outputs player's history of positions
     */
    List<Integer> getPosHistory();
}
