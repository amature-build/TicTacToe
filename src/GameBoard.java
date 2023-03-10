public class GameBoard {
    private String[][] gameBoard;
    private final String[][] defaultBoard = {
            {"0", "|", "1", "|", "2"},
            {"-", "+", "-", "+", "-"},
            {"3", "|", "4", "|", "5"},
            {"-", "+", "-", "+", "-"},
            {"6", "|", "7", "|", "8"}
    };

    public GameBoard(){
        this.gameBoard = this.defaultBoard;
    }
    public void setGameBoard(int row, int col, String placement){
        this.gameBoard[row][col] = placement;
    }
    public String[][] getGameBoard(){
        return this.gameBoard;
    }
    public void resetBoard(){
        this.gameBoard = this.defaultBoard;
    }


}
