public class GameBoard {
    private final String[][] gameBoard;

    public GameBoard(){
        String[][] defaultBoard = {
                {"0", "|", "1", "|", "2"},
                {"-", "+", "-", "+", "-"},
                {"3", "|", "4", "|", "5"},
                {"-", "+", "-", "+", "-"},
                {"6", "|", "7", "|", "8"}
        };
        this.gameBoard = defaultBoard;
    }
    public void setGameBoard(int row, int col, String placement){
        this.gameBoard[row][col] = placement;
    }
    public String[][] getGameBoard(){
        return this.gameBoard;
    }
}
