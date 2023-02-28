import java.util.Scanner;

public class GameUI {
    private String input;
    public GameUI(){}

    public void askToStart(){
        boolean invalidInput;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe.");
        do {
            System.out.println("Please press s/S to Start. q/Q to Quit.");
            this.input = scan.nextLine();
            invalidInput = !validateStartGame();
            if (invalidInput){
                System.out.println("Invalid Input.");
            }
        } while (invalidInput);

        if (this.input.equalsIgnoreCase("q")){
            System.exit(0);
        }

    }

    /**
     * Compare given input against given list of given char.
     * If any matches with given list of char, True else False
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
    private boolean validateStartGame(){
        if (this.input.length() > 1){
            return false;
        }

        String charList = "[sq]";
        return matchAgainstList(charList, this.input);
    }
}
