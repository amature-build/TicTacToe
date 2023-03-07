import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameUI {
    private String input;
    private Player player1, player2, currentPlayer;
    private Player[] players = new Player[2];

    private String[][] gameboard = {
            {"0", "|", "1", "|", "2"},
            {"-", "+", "-", "+", "-"},
            {"3", "|", "4", "|", "5"},
            {"-", "+", "-", "+", "-"},
            {"6", "|", "7", "|", "8"}
    };
    public GameUI(){
        players[0] = new Player();
        this.player1 = new Player("abc");
        this.player2 = new Player("def");
    }

    private void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }

    private void playerRightOfFirst(){
        Random rand = new Random();
        if (rand.nextBoolean()) {
            setCurrentPlayer(player1);
        } else {
            setCurrentPlayer(player2);
        }
    }

    public void askToStart(){
        boolean invalidInput;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe.");
        do {
            System.out.println("Please press s/S to Start. q/Q to Quit.");
            this.input = scan.nextLine();
            invalidInput = !validateStartGame(this.input);
            if (invalidInput){
                System.out.println("Invalid Input.");
            }
        } while (invalidInput);

        if (this.input.equalsIgnoreCase("q")){
            System.exit(0);
        }

    }

    public void askUserName(){
        boolean invalidInput;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("Please enter a name. Only use Alpha characters.");
            this.input = scan.nextLine();
            invalidInput = !validateUserName(this.input);
            if (invalidInput){
                System.out.println("Invalid Input. Please try again.");
            }
        } while (invalidInput);
    }

    public void askPosInput(){
        boolean invalidInput;
        boolean posExist = false;
        Scanner scan = new Scanner(System.in);

        do{
            System.out.println("Please enter value to place on board.");
            this.input = scan.nextLine();
            invalidInput = !validatePosition();
            if (invalidInput){
                System.out.println("Invalid input. Please try again.");
            } else {
                posExist = positionExist();
                if (posExist) {
                    System.out.println("Position already exists. Please try again.");
                }
            }
        } while (invalidInput || posExist);
    }

    /**
     * Compare given input against given list of given char.
     * If any matches with given list of char, regardless of Cap or not, True else False
     * @param charList list of char ex. [a-z], [^a-z], [abc]
     * @param charToTest given input
     * @return true or false
     */
    private boolean matchAgainstList(String charList, String charToTest){
        for (String word : charToTest.split("")) {
            boolean match = word.toLowerCase().matches(charList);
            if (match){
                return true;
            }
        }
        return false;
    }
    boolean validateStartGame(String word){
        if (word.length() > 1){
            return false;
        }

        String charList = "[sq]";
        return matchAgainstList(charList, word);
    }
    boolean validateUserName(String word){
        String charList = "[a-z]";
        return matchAgainstList(charList, word);
    }

    boolean validatePosition(){
        String charList = "[0-8]";
        return matchAgainstList(charList, this.input);
    }

    boolean positionExist(){
        int iPos = Integer.parseInt(this.input);
        boolean output;
        if (this.player1.getPosHistory().isEmpty()){
            output = false;
        } else {
            List<Integer> posList = this.player1.getPosHistory();
            output = posList.contains(iPos);
        }
        return output;
    }

    void setPositionOnBoard(){
        int iPos = Integer.parseInt(this.input);
        String placement = "x";
        switch (iPos){
            case 0 -> this.gameboard[0][0] = placement;
            case 1 -> this.gameboard[0][2] = placement;
            case 2 -> this.gameboard[0][4] = placement;
            case 3 -> this.gameboard[2][0] = placement;
            case 4 -> this.gameboard[2][2] = placement;
            case 5 -> this.gameboard[2][4] = placement;
            case 6 -> this.gameboard[4][0] = placement;
            case 7 -> this.gameboard[4][2] = placement;
            case 8 -> this.gameboard[4][4] = placement;
        }
        this.player1.setPosition(iPos);
    }
    void gameBoard(){
        for (String[] row: this.gameboard) {
            for (String item: row) {
                System.out.print(item);
            }
            System.out.println();
        }
    }
}
