public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        GameUI test = new GameUI();
        test.gameBoard();
        test.askPosInput();
        test.setPositionOnBoard();
        test.gameBoard();
        test.askPosInput();
        test.setPositionOnBoard();
        test.gameBoard();
    }
}