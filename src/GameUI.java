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
}
