import java.util.*;

public class GameUI {
    private String input;
    private Player currentPlayer;

    private Player[] players = {
        new Player(), new Player()
    };

    private String[][] gameboard = {
            {"0", "|", "1", "|", "2"},
            {"-", "+", "-", "+", "-"},
            {"3", "|", "4", "|", "5"},
            {"-", "+", "-", "+", "-"},
            {"6", "|", "7", "|", "8"}
    };
    public GameUI(){
    }

    public void askPlayersOrCpu(){
        boolean invalidInput;
        Scanner scan = new Scanner(System.in);
        System.out.println("Play as Single or Multi Player.");

        do {
            System.out.println("Press number as shown below.\n1 Single Player\n2 Multi Player");
            this.input = scan.nextLine();
            invalidInput = !validPlayerOption();
            if (invalidInput) {
                System.out.println("Invalid input. Please try again.");
            }
        } while (invalidInput);

    }

    private void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }

    private void switchState(){
        for (Player player: players) {
            if (player.getPlayerState().equals(EPlayerState.ACTIVE)){
                player.setPlayerInactive();
            } else {
                player.setPlayerActive();
            }
        }
    }
    public void switchCurrentPlayer(){
        switchState();
        for (Player player: players) {
            if (player.getPlayerState().equals(EPlayerState.ACTIVE)){
                this.currentPlayer = player;
            }
        }
    }
    public void playerRightOfFirst(){
        Random rand = new Random();
        if (rand.nextBoolean()) {
            setCurrentPlayer(players[0]);
            players[0].setPlayerActive();
            players[0].setPlacementValue("X");
            players[1].setPlayerInactive();
            players[1].setPlacementValue("O");
        } else {
            setCurrentPlayer(players[1]);
            players[1].setPlayerActive();
            players[1].setPlacementValue("X");
            players[0].setPlayerInactive();
            players[0].setPlacementValue("O");
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

    public void setPlayer1(){
        players[0].setName(this.input);
        players[0].setUserTypeUser();
    }
    public void setPlayer2(){
        players[1].setName(this.input);
        players[1].setUserTypeUser();
    }
    private void setCPU(){
        players[1].setName("CPU");
        players[1].setUserTypeCPU();
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

    private boolean validPlayerOption(){
        String charList = "[12]";
        if (this.input.length() > 1) {
            return false;
        }
        return matchAgainstList(charList, this.input);
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
        List<Integer> posList = new ArrayList<>();
        boolean output;

        for (Player p : players) {
            if (!p.getPosHistory().isEmpty()){
                posList.addAll(p.getPosHistory());
            }
        }
        output = posList.contains(iPos);
        return output;
    }

    void setPositionOnBoard(){
        int iPos = Integer.parseInt(this.input);
        String placement = this.currentPlayer.getPlacementValue();
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
        this.currentPlayer.setPosition(iPos);
    }
    void gameBoard(){
        for (String[] row: this.gameboard) {
            for (String item: row) {
                System.out.print(item);
            }
            System.out.println();
        }
    }
    boolean isGameWon(){
        List<Integer> posList = this.currentPlayer.getPosHistory();
        List<Integer[]> winList = new PossibleWin().getWinMoveList();
        Collections.sort(posList);

        for (Integer[] list : winList) {
            if (posList.containsAll(List.of(list))){
                return true;
            }
        }
        return false;
    }
    boolean isGameDraw(){
        List<Integer> posList = new ArrayList<>();

        for (Player player : players) {
            posList.addAll(player.getPosHistory());
        }

        int total = posList.stream()
                .mapToInt(value -> value)
                .sum();

        return total == 36 && posList.size() == 9;
    }
    void promptWon(){
        System.out.println("Congratulation Player " + this.currentPlayer.getName());
        System.out.println("You have won the game.");
    }

    void gameEnding(){
        boolean isCPU = players[1].getUserType().equals(EPlayerType.CPU);
        String msg = "";


        if (isGameWon()){
            msg = this.currentPlayer.promptWon();
        } else if (isGameDraw()) {
            msg = this.currentPlayer.promptDraw();
        }

        System.out.println(msg);
    }

    void gameInProcess() {
        boolean toContinue;

        do {
            gameBoard();
            askPosInput();
            setPositionOnBoard();
            toContinue = !isGameWon();
            if (toContinue){
                switchCurrentPlayer();
            }
            if (isGameDraw()){
                toContinue = false;
            }
        } while (toContinue);
    }
    void gamePlayerSelection(){
        askUserName();
        setPlayer1();
        askPlayersOrCpu();

        switch (this.input) {
            case "1" -> {
                setCPU();
            }
            case "2" -> {
                askUserName();
                setPlayer2();
            }
        }

    }
}