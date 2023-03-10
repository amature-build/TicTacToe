import java.util.*;
import java.util.stream.Stream;

public class GameUI {
    private String input;
    private Player currentPlayer;
    private PossibleWin data;

    private final Player[] players = {
        new Player(), new Player()
    };

    private GameBoard gameboard;
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
    boolean isSameName(){
        return players[0].getName().equals(this.input);
    }
    void promptSameNameRetry(){
        if (isSameName()){
            System.out.println("The name already exist. Try again.");
        }
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
            String msg = "Player " +
                    this.currentPlayer.getName() +
                    " " +
                    this.currentPlayer.getPlacementValue() +
                    "\nPlease enter a value to place on board.";
            System.out.println(msg);
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
            boolean notMatch = !word.toLowerCase().matches(charList);
            if (notMatch){
                return false;
            }
        }
        return true;
    }
    boolean validateStartGame(String word){
        if (word.length() > 1){
            return false;
        }

        String charList = "[sq]";
        return matchAgainstList(charList, word);
    }
    boolean validateUserName(String word){
        if (word.equalsIgnoreCase("cpu")){
            return false;
        }
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
    void setPositionOnBoard(String position){
        int iPos = Integer.parseInt(position);
        String placement = this.currentPlayer.getPlacementValue();
        switch (iPos){
            case 0 -> this.gameboard.setGameBoard(0,0, placement);
            case 1 -> this.gameboard.setGameBoard(0,2, placement);
            case 2 -> this.gameboard.setGameBoard(0,4, placement);
            case 3 -> this.gameboard.setGameBoard(2,0, placement);
            case 4 -> this.gameboard.setGameBoard(2,2, placement);
            case 5 -> this.gameboard.setGameBoard(2,4, placement);
            case 6 -> this.gameboard.setGameBoard(4,0, placement);
            case 7 -> this.gameboard.setGameBoard(4,2, placement);
            case 8 -> this.gameboard.setGameBoard(4,4, placement);
        }
        this.currentPlayer.setPosition(iPos);
    }
    void gameBoard(){
        for (String[] row: this.gameboard.getGameBoard()) {
            for (String item: row) {
                System.out.print(item);
            }
            System.out.println();
        }
    }
    private List<Integer> combinePosHistory(){
        List<Integer> combinedList = new ArrayList<>();
        for (Player player : players) {
            combinedList.addAll(player.getPosHistory());
        }
        Collections.sort(combinedList);
        return combinedList;
    }
    private List<Integer> posWithoutCombined(){
        List<Integer> combinedList = combinePosHistory();
        return Stream.of(0,1,2,3,4,5,6,7,8)
                .filter(value -> !combinedList.contains(value))
                .toList();
    }
    private List<Integer[]> nextPossibleLists(List<Integer> posList, List<Integer[]> listToLoop){
        List<Integer[]> possibleLists = new ArrayList<>();
        List<Integer> countList = nextCountList(posList, listToLoop);

        int highest = countList.stream().max(Comparator.naturalOrder()).get();
        if (highest > 2){
            highest = 2;
        }

        for (int i = 0; i < countList.size(); i++) {
            if (countList.get(i).equals(highest)){
                possibleLists.add(listToLoop.get(i));
            }
        }
        return possibleLists;
    }
    private List<Integer> nextCountList(List<Integer> posList, List<Integer[]> listToLoop){
        List<Integer> countList = new ArrayList<>();
        for (Integer[] eachList: listToLoop) {
            int count = (int) posList.stream()
                    .filter(value -> List.of(eachList).contains(value))
                    .count();
            countList.add(count);
        }
        return countList;
    }
    private void removePossibleWinThatMatched(){
        List<Integer> combinedList = combinePosHistory();
        List<Integer[]> winList = this.data.getWinMoveList();
        for (int i = 0; i < winList.size(); i++) {
            List<Integer> win = List.of(winList.get(i));
            if (combinedList.containsAll(win)){
                this.data.removeRow(i);
            }
        }
    }
    private String blockPlayer(List<Integer[]> playerPossiblePos){
        List<Integer> playerPosList = players[0].getPosHistory();
        Random rand = new Random();
        String output = "";
        List<Integer[]> playerPossiblePosFiltered = new ArrayList<>();
        for (Integer[] eachList : playerPossiblePos) {
            int count = (int) playerPosList.stream()
                    .filter(value -> List.of(eachList).contains(value))
                    .count();
            if (count > 1) {
                playerPossiblePosFiltered.add(eachList);
            }
        }
        if (!playerPossiblePosFiltered.isEmpty()){
            int randNum = rand.nextInt(playerPossiblePosFiltered.size());
            Integer[] possibleList = playerPossiblePosFiltered.get(randNum);
            for (int value: possibleList) {
                if (!playerPosList.contains(value)){
                    output = String.valueOf(value);
                }
            }
        }

        return output;
    }
    private String remainderCpuMove(){
        List<Integer> combinedList = combinePosHistory();
        List<Integer> remainedList = posWithoutCombined();
        List<Integer[]> remainderMoveList = nextPossibleLists(remainedList, this.data.getWinMoveList());
        Random rand = new Random();
        boolean nonUsableMove;
        String output = "";
        List<Integer[]> remainderPossiblePosFiltered = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        for (Integer[] eachList : remainderMoveList) {
            int count = (int) combinedList.stream()
                    .filter(value -> List.of(eachList).contains(value))
                    .count();
            countList.add(count);
        }

        int lowest = countList.stream().min(Comparator.naturalOrder()).get();

        for (int i = 0; i < countList.size(); i++) {
            if (countList.get(i).equals(lowest)){
                remainderPossiblePosFiltered.add(remainderMoveList.get(i));
            }
        }

        int randNum = rand.nextInt(remainderPossiblePosFiltered.size());
        Integer[] possibleList = remainderPossiblePosFiltered.get(randNum);
        do {
            randNum = rand.nextInt(possibleList.length);
            int possibleMove = possibleList[randNum];
            nonUsableMove = combinedList.contains(possibleMove);
            if (!nonUsableMove){
                output = String.valueOf(possibleMove);
            }
        } while (nonUsableMove);

        return output;
    }
    private String nextMoveCPU(){
        String nextMove = "";
        Random rand = new Random();
        removePossibleWinThatMatched();
        List<Integer> cpuPosList = players[1].getPosHistory();
        List<Integer> playerPosList = players[0].getPosHistory();
        List<Integer[]> winLists = this.data.getWinMoveList();


        List<Integer[]> playerPossiblePos = nextPossibleLists(playerPosList, winLists);

//      first move before player
        if (combinePosHistory().isEmpty()){
            nextMove = "4";
            return nextMove;
        }
//      first move after player
        if (cpuPosList.isEmpty() && !playerPosList.isEmpty()){
            int randNum;
            boolean exist;
            int playerPos = playerPosList.iterator().next();
            do {
                randNum = rand.nextInt(9);
                exist = randNum == playerPos;
            } while (exist);
            nextMove = String.valueOf(randNum);
            return nextMove;
        }
//      if player has winning chance
        String blockMove = blockPlayer(playerPossiblePos);
        if (!blockMove.equalsIgnoreCase("")){
            nextMove = blockMove;
            return nextMove;
        }

//      outstanding options
        nextMove = remainderCpuMove();
        return nextMove;
    }

    boolean isGameWon(Player player){
        List<Integer> posList = player.getPosHistory();
        List<Integer[]> winList = this.data.getWinMoveList();
        Collections.sort(posList);

        for (Integer[] list : winList) {
            if (posList.containsAll(List.of(list))){
                return true;
            }
        }
        return false;
    }
    boolean isGameDraw(){
        List<Integer> posList = combinePosHistory();

        int total = posList.stream()
                .mapToInt(value -> value)
                .sum();

        return total == 36 && posList.size() == 9;
    }
    private void promptEndMsg(Player player){
        String msg;
        if (isGameWon(player)){
            msg = player.promptWon();
        } else if (isGameDraw()) {
            msg = player.promptDraw();
        } else {
            msg = player.promptLose();
        }
        System.out.println(msg);
    }

    void gameEnding(){

        for (Player player : players) {
            if (!player.getUserType().equals(EPlayerType.CPU)){
                promptEndMsg(player);
            }
        }
    }

    void gameInProcess() {
        boolean toContinue;
        this.data = new PossibleWin();
        this.gameboard = new GameBoard();
        do {
            gameBoard();
            if (this.currentPlayer.getUserType().equals(EPlayerType.CPU)){
                this.input = nextMoveCPU();
                setPositionOnBoard(this.input);
                System.out.println("CPU made a move.");
            } else {
                askPosInput();
                setPositionOnBoard(this.input);
            }
            toContinue = !isGameWon(this.currentPlayer);
            if (toContinue){
                switchCurrentPlayer();
            }
            if (isGameDraw()){
                toContinue = false;
            }
        } while (toContinue);
    }
    void gamePlayerCPU(){
        for (Player player: players) {
            player.resetHistory();
        }

        askPlayersOrCpu();
        switch (this.input) {
            case "1" -> setCPU();
            case "2" -> {
                do {
                    askUserName();
                    promptSameNameRetry();
                } while (isSameName());
                setPlayer2();
            }
        }
    }
    boolean validateRestartGame(String word){
        if (word.length() > 1){
            return false;
        }

        String charList = "[cq]";
        return matchAgainstList(charList, word);
    }
    private boolean playGameAgain(){
        boolean output;
        boolean invalidInput;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("Press c/C to Play Again or q/Q to Quit");
            this.input = scan.nextLine();
            invalidInput = !validateRestartGame(this.input);
            if (invalidInput){
                System.out.println("Invalid Input.");
            }
        } while (invalidInput);

        output = this.input.equalsIgnoreCase("c");

        return output;
    }
    void runGame(){
        askToStart();
        askUserName();
        setPlayer1();

        do {
            gamePlayerCPU();
            playerRightOfFirst();
            gameInProcess();
            gameBoard();
            gameEnding();
        } while (playGameAgain());
    }
}