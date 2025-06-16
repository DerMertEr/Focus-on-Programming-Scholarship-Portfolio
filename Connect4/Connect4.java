import java.util.Scanner;
import java.util.Random;

public class Connect4
{

    enum Direction
    {
        UP_LEFT,
        UP,
        UP_RIGHT,
        RIGHT,
        DOWN_RIGHT,
        DOWN,
        DOWN_LEFT,
        LEFT
    }

    enum Gamemode
    {
        PVP,
        PVC,
        NULL
    }

    enum Color
    {
        RED,
        BLUE
    }

    public static Random rd = new Random();
    public static Scanner sc = new Scanner(System.in);

    static final Direction[] allDirections = {Direction.UP_LEFT, Direction.UP, Direction.UP_RIGHT, Direction.RIGHT, Direction.DOWN_RIGHT, Direction.DOWN, Direction.DOWN_LEFT, Direction.LEFT};


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    static Gamemode gamemode = Gamemode.NULL;

    static boolean doesPlayer1StartsFirst = rd.nextInt(0, 2) == 0 ? true : false;
    static boolean isPlayer1Turn = doesPlayer1StartsFirst;

    static int player1Score = 0;
    static int player2Score = 0;
    static int computerScore = 0;
    static int player1Color = 0;
    static int player2Color = 0;
    static int computerColor = 0;

    static final int RED = 1;
    static final int BLUE = 2;
    static final String RED_STRING = ANSI_RED + "R" + ANSI_RESET;
    static final String BLUE_STRING = ANSI_CYAN + "B" + ANSI_RESET;

    static int[][] BOARD = {
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
    };

    //static int[][] BOARD = {
        //{2, 0, 0, 0, 0, 0, 0},
        //{2, 0, 0, 0, 0, 0, 0},
        //{1, 0, 2, 1, 0, 0, 0},
        //{1, 2, 1, 2, 0, 0, 0},
        //{2, 2, 1, 2, 0, 0, 0},
        //{1, 1, 1, 2, 0, 0, 0},
    //};


    private static String displayBoard()
    {
        // char[] boardCharValues = new char[42];
        String[] boardCharValues = new String[42];
        int count = 0;

        for (int i = 0; i < BOARD.length; i++) {
            for (int j = 0; j < BOARD[i].length; j++) {
                if (BOARD[i][j] == RED) {
                    //boardCharValues[count] = "R";
                    boardCharValues[count] = RED_STRING;
                } else if (BOARD[i][j] == BLUE) {
                    //boardCharValues[count] = "B";
                    boardCharValues[count] = BLUE_STRING;
                } else {
                    boardCharValues[count] = " ";
                }
                count++;
            }
        }

        String displayedBoard = String.format("+===+===+===+===+===+===+===+\n"
          //+ "| %c | %c | %c | %c | %c | %c | %c |\n"
          //+ "+---+---+---+---+---+---+---+\n"
          //+ "| %c | %c | %c | %c | %c | %c | %c |\n"
          //+ "+---+---+---+---+---+---+---+\n"
          //+ "| %c | %c | %c | %c | %c | %c | %c |\n"
          //+ "+---+---+---+---+---+---+---+\n"
          //+ "| %c | %c | %c | %c | %c | %c | %c |\n"
          //+ "+---+---+---+---+---+---+---+\n"
          //+ "| %c | %c | %c | %c | %c | %c | %c |\n"
          //+ "+---+---+---+---+---+---+---+\n"
          //+ "| %c | %c | %c | %c | %c | %c | %c |\n"
          //+ "+===+===+===+===+===+===+===+\n",
            + "| %s | %s | %s | %s | %s | %s | %s |\n"
            + "+---+---+---+---+---+---+---+\n"
            + "| %s | %s | %s | %s | %s | %s | %s |\n"
            + "+---+---+---+---+---+---+---+\n"
            + "| %s | %s | %s | %s | %s | %s | %s |\n"
            + "+---+---+---+---+---+---+---+\n"
            + "| %s | %s | %s | %s | %s | %s | %s |\n"
            + "+---+---+---+---+---+---+---+\n"
            + "| %s | %s | %s | %s | %s | %s | %s |\n"
            + "+---+---+---+---+---+---+---+\n"
            + "| %s | %s | %s | %s | %s | %s | %s |\n"
            + "+===+===+===+===+===+===+===+\n"
            + "  1   2   3   4   5   6   7  \n",
            boardCharValues[0], boardCharValues[1], boardCharValues[2], boardCharValues[3], boardCharValues[4], boardCharValues[5], boardCharValues[6],
            boardCharValues[7], boardCharValues[8], boardCharValues[9], boardCharValues[10], boardCharValues[11], boardCharValues[12], boardCharValues[13],
            boardCharValues[14], boardCharValues[15], boardCharValues[16], boardCharValues[17], boardCharValues[18], boardCharValues[19], boardCharValues[20],
            boardCharValues[21], boardCharValues[22], boardCharValues[23], boardCharValues[24], boardCharValues[25], boardCharValues[26], boardCharValues[27],
            boardCharValues[28], boardCharValues[29], boardCharValues[30], boardCharValues[31], boardCharValues[32], boardCharValues[33], boardCharValues[34],
            boardCharValues[35], boardCharValues[36], boardCharValues[37], boardCharValues[38], boardCharValues[39], boardCharValues[40], boardCharValues[41]
        );

        return displayedBoard;
    }


    // accepts 1 for the color RED, 2 for the color BLUE
    // returns true if the piece was placed, and returns false if you try to place a piece out of bounds
    public static boolean placePiece(int unformattedIndex, int color)
    {
        int index0;
        int index1 = unformattedIndex - 1;

        if (color != 1 && color != 2) {
            throw new IllegalArgumentException("IllegalArgumentException: Incorrect color value to place");
        } else if (index1 < 0 || index1 > 6) {
            return false;
        }

        if (BOARD[5][index1] == 0) {
            index0 = 5;
        } else if (BOARD[4][index1] == 0) {
            index0 = 4;
        } else if (BOARD[3][index1] == 0) {
            index0 = 3;
        } else if (BOARD[2][index1] == 0) {
            index0 = 2;
        } else if (BOARD[1][index1] == 0) {
            index0 = 1;
        } else if (BOARD[0][index1] == 0) {
            index0 = 0;
        } else {
            return false;
        }

        BOARD[index0][index1] = color;
        return true;
    }


    public static void computerAi()
    {
        int numberToTry;

        boolean boardIsFull = true;
        for (int i = 0; i < BOARD.length; i++) {
            for (int j = 0; j < BOARD[i].length; j++) {
                if (BOARD[i][j] == 0) boardIsFull = false;
            }
        }
        if (boardIsFull) {
            throw new IllegalArgumentException("IllegalArgumentException: The board is full!! The computer ai can't place anything");
        }

        while (true) {
            numberToTry = rd.nextInt(1, 8);
            boolean success = placePiece(numberToTry, computerColor);
            if (success) break;
        }
    }


    private static int arrayBoundsDetection(int pos0, int pos1)
    {
        int minIndex = 0;
        int maxIndex0 = 5;
        int maxIndex1 = 6;

        if ((pos0 < minIndex || pos0 > maxIndex0) || (pos1 < minIndex || pos1 > maxIndex1)) {
            return 1;
        }

        return 0;
    }


    // returns 3 items
    // [x, y, z]
    // x = index position 1
    // y = index position 2
    // z = error detection
    // if the found direction is out of bounds of the array, it will return:
    // [0, 0, 1], where z=1 means that there was an error
    // if there was no error, z=0
    // if the found direction was a valid one at [0][0], it would return:
    // [0, 0, 0]
    private static int[] findIndex(int ogIndexPos0, int ogIndexPos1, Direction direction)
    {
        int[] resultArray = {0, 0, 0};

        if (direction == Direction.UP_LEFT) {
            resultArray[0] = ogIndexPos0 - 1;
            resultArray[1] = ogIndexPos1 - 1;
        } else if (direction == Direction.UP) {
            resultArray[0] = ogIndexPos0 - 1;
            resultArray[1] = ogIndexPos1;
        } else if (direction == Direction.UP_RIGHT) {
            resultArray[0] = ogIndexPos0 - 1;
            resultArray[1] = ogIndexPos1 + 1;
        } else if (direction == Direction.RIGHT) {
            resultArray[0] = ogIndexPos0;
            resultArray[1] = ogIndexPos1 + 1;
        } else if (direction == Direction.DOWN_RIGHT) {
            resultArray[0] = ogIndexPos0 + 1;
            resultArray[1] = ogIndexPos1 + 1;
        } else if (direction == Direction.DOWN) {
            resultArray[0] = ogIndexPos0 + 1;
            resultArray[1] = ogIndexPos1;
        } else if (direction == Direction.DOWN_LEFT) {
            resultArray[0] = ogIndexPos0 + 1;
            resultArray[1] = ogIndexPos1 - 1;
        } else if (direction == Direction.LEFT) {
            resultArray[0] = ogIndexPos0;
            resultArray[1] = ogIndexPos1 - 1;
        }
        resultArray[2] = arrayBoundsDetection(resultArray[0], resultArray[1]);

        return resultArray;
    }



    // returns 0 if round still going
    // returns -1 if round ties
    // returns 1 if RED won
    // returns 2 if BLUE won
    public static int detectWin()
    {
        // int result = 0;
        int timesFoundInARow = 0;
        // Direction currentDirection;  // we iterate over each direction, this variable stores which direction we're looking in currently
        int currentLookingElement;  // when we get the correct index error free (not out of bounds), we store the number that it finds in the array here
        int[] nextIndexPosition = new int[3];  // this stores the coordinates of the next position we look for

        for (int i = 0; i < BOARD.length; i++) {
            for (int j = 0; j < BOARD[i].length; j++) {
                timesFoundInARow = 0;
                if (BOARD[i][j] == 0) continue;
                timesFoundInARow = 1;
                for (Direction currentDirection : allDirections) {
                    nextIndexPosition = findIndex(i, j, currentDirection);
                    if (nextIndexPosition[2] != 0) {
                        continue;
                    }
                    currentLookingElement = BOARD[nextIndexPosition[0]][nextIndexPosition[1]];

                    if (currentLookingElement == BOARD[i][j]) {
                        timesFoundInARow++;
                        // 2 in a row for now
                        nextIndexPosition = findIndex(nextIndexPosition[0], nextIndexPosition[1], currentDirection);
                        if (nextIndexPosition[2] != 0) {
                            continue;
                        }
                        currentLookingElement = BOARD[nextIndexPosition[0]][nextIndexPosition[1]];

                        if (currentLookingElement == BOARD[i][j]) {
                            timesFoundInARow++;
                            // 3 in a row for now
                            nextIndexPosition = findIndex(nextIndexPosition[0], nextIndexPosition[1], currentDirection);
                            // nextIndexPosition = findIndex(i, j, currentDirection);
                            if (nextIndexPosition[2] != 0) {
                                continue;
                            }
                            currentLookingElement = BOARD[nextIndexPosition[0]][nextIndexPosition[1]];

                            if (currentLookingElement == BOARD[i][j]) {
                                timesFoundInARow++;
                                // 4 in a row we win!!
                                if (timesFoundInARow == 4) return BOARD[i][j];
                            }
                        }
                    }
                }
            }
        }

        boolean draw = true;
        for (int i = 0; i < BOARD.length; i++) {
            for (int j = 0; j < BOARD[i].length; j++) {
                if (BOARD[i][j] == 0) draw = false;
            }
        }

        if (draw) return -1;
        return 0;
    }


    private static void resetAll()
    {
        gamemode = Gamemode.NULL;
        player1Score = 0;
        player2Score = 0;
        computerColor = 0;
        player1Color = 0;
        player2Color = 0;
        computerColor = 0;
        doesPlayer1StartsFirst = rd.nextInt(0, 2) == 0 ? true : false;
        isPlayer1Turn = doesPlayer1StartsFirst;
    }

    private static void resetColors()
    {
        player1Color = 0;
        player2Color = 0;
        computerColor = 0;
    }

    private static void resetBoard()
    {
        for (int i = 0; i < BOARD.length; i++) {
            for (int j = 0; j < BOARD[i].length; j++) {
                BOARD[i][j] = 0;
            }
        }
    }


    private static int chooseGamemodeLoop()
    {
        String gamemodeChoice = "";
        while (true) {
            System.out.println("Would you like to go against a (p)layer or the (c)omputer?");
            System.out.print("?: ");
            gamemodeChoice = sc.nextLine();

            if (gamemodeChoice.equals("-1")) {
                return -1;
            } else if (gamemodeChoice.toUpperCase().equals("P")) {
                gamemode = Gamemode.PVP;
                return 0;
            } else if (gamemodeChoice.toUpperCase().equals("C")) {
                gamemode = Gamemode.PVC;
                return 0;
            } else {
                gamemode = Gamemode.NULL;
                System.out.println("Please enter (p) to player against another player or (c) to play against the computer.\n");
            }
        }
    }

    private static int chooseColorsLoop()
    {
        String playerColorChoice = "";
        while (true) {
            if (rd.nextInt(0, 2) == 0 || gamemode == Gamemode.PVC) {
                System.out.println("Player 1, would you like to be (r)ed or (b)lue?");
                System.out.print("?: ");
                playerColorChoice = sc.nextLine();
                if (playerColorChoice.equals("-1")) {
                    return -1;
                } else if (playerColorChoice.toUpperCase().equals("R")) {
                    player1Color = RED;

                    if (gamemode == Gamemode.PVP) {
                        player2Color = BLUE;
                    } else if (gamemode == Gamemode.PVC) {
                        computerColor = BLUE;
                    }

                    return 0;
                } else if (playerColorChoice.toUpperCase().equals("B")) {
                    player1Color = BLUE;

                    if (gamemode == Gamemode.PVP) {
                        player2Color = RED;
                    } else if (gamemode == Gamemode.PVC) {
                        computerColor = RED;
                    }

                    return 0;
                } else {
                    player1Color = 0;
                    player2Color = 0;
                    computerColor = 0;
                    System.out.println("Please enter (r) to play as red or (b) to play as blue.\n");
                }
            } else {
                System.out.println("Player 2, would you like to be (r)ed or (b)lue?");
                System.out.print("?: ");
                playerColorChoice = sc.nextLine();
                if (playerColorChoice.equals("-1")) {
                    return -1;
                } else if (playerColorChoice.toUpperCase().equals("R")) {
                    player2Color = RED;
                    player1Color = BLUE;
                    return 0;
                } else if (playerColorChoice.toUpperCase().equals("B")) {
                    player2Color = BLUE;
                    player1Color = RED;
                    return 0;
                } else {
                    player2Color = 0;
                    player1Color = 0;
                    computerColor = 0;
                    System.out.println("Please enter (r) to play as red or (b) to play as blue.\n");
                }
            }
        }
    }

    // returns -1 to exit the program immediately (during game)
    private static int gameplayLoop()
    {
        isPlayer1Turn = doesPlayer1StartsFirst;
        int position;
        // int roundResult;  // was supposed to be to return the round result but i don't think we need it
        int moveResult;  // current move's outcome (see exit codes of detectWin function)
        boolean result;  // result if the player specified a valid position in the board or not
        String player1ColorString = player1Color == 1 ? "Red" : "Blue";
        String player2ColorString = player2Color == 1 ? "Red" : "Blue";
        // String computerColorString = player2Color == 1 ? "Red" : "Blue";

        while (true) {
            System.out.println(displayBoard());

            // players perform their moves
            if (isPlayer1Turn) {
                try {
                    System.out.println("Player 1 " + player1ColorString + ": Type a number 1-7 to place your piece!\n");
                    System.out.print("?: ");
                    position = sc.nextInt();
                    if (position == -1) return -1;
                    result = placePiece(position, player1Color);
                    if (result) {
                        isPlayer1Turn = !isPlayer1Turn;
                    } else {
                        System.out.println("Please make sure that you place a piece inside of the board.\n");
                        continue;
                    }
                } catch (Exception e) {
                    sc.nextLine();
                    System.out.println("Please enter a valid number.\n");
                    continue;
                }
            } else if (!isPlayer1Turn && gamemode == Gamemode.PVP) {
                try {
                    System.out.println("Player 2 " + player2ColorString + ": Type a number 1-7 to place your piece!\n");
                    System.out.print("?: ");
                    position = sc.nextInt();
                    if (position == -1) return -1;
                    result = placePiece(position, player2Color);
                    if (result) {
                        isPlayer1Turn = !isPlayer1Turn;
                    } else {
                        System.out.println("Please make sure that you place a piece inside of the board.\n");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a valid number.\n");
                    continue;
                }
            } else if (!isPlayer1Turn && gamemode == Gamemode.PVC) {
                computerAi();
                isPlayer1Turn = !isPlayer1Turn;
            }

            System.out.println();


            // detect if game end and deal with outcome
            moveResult = detectWin();

            if (moveResult == -1) {
                sc.nextLine();
                System.out.println(displayBoard());
                doesPlayer1StartsFirst = !doesPlayer1StartsFirst;
                System.out.println("Tie! The gameboard is filled up!");
                resetBoard();
                return 0;
            } else if (moveResult == 1 && player1Color == 1) {
                sc.nextLine();
                System.out.println(displayBoard());
                doesPlayer1StartsFirst = !doesPlayer1StartsFirst;
                player1Score++;
                System.out.println("Player 1 Red WON!!");
                resetBoard();
                return 0;
            } else if (moveResult == 1 && player2Color == 1) {
                sc.nextLine();
                System.out.println(displayBoard());
                doesPlayer1StartsFirst = !doesPlayer1StartsFirst;
                player2Score++;
                System.out.println("Player 2 Red WON!!");
                resetBoard();
                return 0;
            } else if (moveResult == 2 && player1Color == 2) {
                sc.nextLine();
                System.out.println(displayBoard());
                doesPlayer1StartsFirst = !doesPlayer1StartsFirst;
                player1Score++;
                System.out.println("Player 1 Blue WON!!");
                resetBoard();
                return 0;
            } else if (moveResult == 2 && player2Color == 2) {
                sc.nextLine();
                System.out.println(displayBoard());
                doesPlayer1StartsFirst = !doesPlayer1StartsFirst;
                player2Score++;
                System.out.println("Player 2 Blue WON!!");
                resetBoard();
                return 0;
            }
        }
    }

    public static void displayScores()
    {
        String player1ColorString = player1Color == 1 ? "Red" : "Blue";
        String player2ColorString = player2Color == 1 ? "Red" : "Blue";
        String computerColorString = player2Color == 1 ? "Red" : "Blue";

        System.out.println(String.format("Player 1 %s: %d", player1ColorString, player1Score));
        if (gamemode == Gamemode.PVP) {
            System.out.println(String.format("Player 2 %s: %d", player2ColorString, player2Score));
        } else if (gamemode == Gamemode.PVC) {
            System.out.println(String.format("Computer %s: %d", computerColorString, computerScore));
        }
    }

    // this function prompts the user for which reset to do, and then performs the correct resetting of data to do so
    // it returns exit codes to specify what the user chose and what loops need to be replayed:
    // returns 0 to restart the round
    // returns 1 to normally exit the program (at the end of the game) (says final score and goodbye message)
    // returns 2 to restart ALMOST all the way from beginning of the program skipping all the welcome messages and just asking to switch colors
    // (does NOT reset player SCORES, it DOES reset COLORS) (only changes the colors of the players) basically just start from the choose gamemodeLoop function
    // returns 3 to restart from the beginning of program without welcome messages, just asks the gamemode and color
    // (this resets ALL scores and ALL colors)
    private static int promptReset()
    {
        String resetChoice = "";
        while (true) {
            System.out.println("Would you like to (0) do another round, (1) exit the program, (2) switch colors, or (3) switch opponents (go against computer or another person) (warning this also resets current scores)?");
            System.out.print("?: ");
            resetChoice = sc.nextLine();

            if (resetChoice.equals("-1")) {
                return -1;
            } else if (resetChoice.equals("0")) {
                return 0;
            } else if (resetChoice.equals("1")) {
                return 1;
            } else if (resetChoice.equals("2")) {
                resetColors();
                return 2;
            } else if (resetChoice.equals("3")) {
                resetAll();
                return 3;
            } else {
                System.out.println("Please enter (0) to go another round, (1) to exit the program, (2) to switch colors, or (3) to switch from going against the computer to going against a person and vice versa.\n");
            }
        }
    }

    public static void gameLoop()
    {
        int generalExitCode;  // exit code for chooseGamemodeLoop(), chooseColorsLoop(), and promptReset(). if any of them == -1, then exit in the middle of the game
        int gameplayLoopExitCode;  // exit code from gameplayLoop() function. only used if it returns -1, which means that user wants to exit the program in the middle of the game.
        int promptResetExitCode;  // exit code from promptReset() function. 0=(restart round), 1=(exit program), 2=(restart from chooseColorsLoop()), 3=(restart from chooseGamemodeLoop()), (ADD THIS IN THE FUTURE: -1=(break in the middle of gameplay loop))
        int trackGameStage = 3;  // this will be used to determine where we start when restarting the game. for example, if(trackGameState=3):chooseGamemodeLoop();

        System.out.println("Welcome to Connect 4!\n");

        while (true) {
            if (trackGameStage == -1) break;

            if (trackGameStage == 3) {
                generalExitCode = chooseGamemodeLoop();
                if (generalExitCode == -1) break;
                System.out.println("\n\n");
                trackGameStage = 2;
            }

            if (trackGameStage == 2) {
                generalExitCode = chooseColorsLoop();
                if (generalExitCode == -1) break;
                System.out.println("\n\n");
                trackGameStage = 1;
            }

            if (trackGameStage == 1) {
                gameplayLoopExitCode = gameplayLoop();  // deal with the -1 immediate exit code (exits entire program immediately)
                if (gameplayLoopExitCode == -1) break;
                System.out.println("\n\n");
                trackGameStage = 0;
            }

            if (trackGameStage == 0) {
                System.out.println("The current scores are:");
                displayScores();
                System.out.println("\n\n");
                trackGameStage = -2;
            }

            // 0 restart the round
            // 1 exit the program
            // 2 restart from chooseColorsLoop()
            // 3 restart from chooseGamemodeLoop()
            if (trackGameStage == -2) {
                generalExitCode = promptResetExitCode = promptReset();
                if (generalExitCode == -1) break;
                if (promptResetExitCode == 0) {
                    trackGameStage = 1;
                    System.out.println("\n\n");
                    continue;
                } else if (promptResetExitCode == 1) {
                    trackGameStage = -3;
                    System.out.println("\n\n");
                } else if (promptResetExitCode == 2) {
                    trackGameStage = 2;
                    System.out.println("\n\n");
                    continue;
                } else if (promptResetExitCode == 3) {
                    trackGameStage = 3;
                    System.out.println("\n\n");
                    continue;
                }
            }

            if (trackGameStage == -3) {
                System.out.println("Your final scores are:");
                displayScores();
                System.out.println("\n\nThanks for playing! Have a good day!");
                break;
            }
        }
    }




    public static void main(String[] args) throws Exception
    {
        System.out.println("==================================================\n\n");

        gameLoop();

        sc.close();

        System.out.println("\n\n==================================================");
    }
}
