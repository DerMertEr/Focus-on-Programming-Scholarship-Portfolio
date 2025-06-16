# Before: Material Theme Ocean - 2/28/2024
# Before 2: Ayu Tokyo Night - 2/28/2024
# Installed GlassIt-VSC for transparent background - 2/28/2024

from random import randint

DEBUGMODE: bool = False

player1_symbol: str = " "
player2_symbol: str = " "
computer_symbol: str = " "

gamemode: str = ""

player1_score: int = 0
player2_score: int = 0
computer_score: int = 0

computer_difficulty: str = ""

gamemode_break: bool = False
symbol_break: bool = False
gameplay_break: bool = False
instructions_not_printed_flag: bool = True

turn_is_player1: bool = True if randint(0, 1) == 0 else False
start_turn: bool = turn_is_player1

board_index: list = [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ']

instructions: str = "Type a number in the board to place your symbol there.\nYou cannot place a symbol in a spot that's already occupied.\nThe first to get three in a row wins!\n\n 1 | 2 | 3 \n===|===|===\n 4 | 5 | 6 \n===|===|===\n 7 | 8 | 9 \n\n"


def draw_board() -> str:
    l1: str = " " + board_index[0] + " | " + board_index[1] + " | " + board_index[2] + " \n"
    l2: str = "===|===|===\n"
    l3: str = " " + board_index[3] + " | " + board_index[4] + " | " + board_index[5] + " \n"
    l4: str = "===|===|===\n"
    l5: str = " " + board_index[6] + " | " + board_index[7] + " | " + board_index[8] + " \n"
    return l1 + l2 + l3 + l4 + l5


def place(symbol: str, spot: int) -> None:
    if spot < 1 or spot > 9:
        raise IndexError
    elif board_index[spot - 1] != " ":
        raise SyntaxError
    else:
        board_index[spot - 1] = symbol


def prompt_place(symbol_turn: str) -> None:
    while True:
        try:
            turn: str = "(Player 1: " + symbol_turn + ")" if turn_is_player1 else "(Player 2: " + symbol_turn + ")"
            _spot: int = int(input(turn + ": Choose a spot to place your symbol down.\n?: "))
            place(symbol_turn, _spot)
            break
        except ValueError:
            print("Please input a number.\n")
        except IndexError:
            print("Please input a number within the range of the board.\n")
        except SyntaxError:
            print("Please input a spot that isn't taken already.\n")


def reset_board() -> None:
    global board_index
    board_index[0] = " "
    board_index[1] = " "
    board_index[2] = " "
    board_index[3] = " "
    board_index[4] = " "
    board_index[5] = " "
    board_index[6] = " "
    board_index[7] = " "
    board_index[8] = " "


def reset_score() -> None:
    global player1_score
    global player2_score
    global computer_score
    player1_score = 0
    player2_score = 0
    computer_score = 0


def reset_symbols() -> None:
    global player1_symbol
    global player2_symbol
    global computer_symbol
    player1_symbol = " "
    player2_symbol = " "
    computer_symbol = " "


def reset_gamemode() -> None:
    global gamemode
    gamemode = ""


def swap_turns() -> None:
    global turn_is_player1
    global start_turn
    start_turn = not start_turn
    turn_is_player1 = start_turn


def prompt_retry() -> str:
    global gameplay_break
    global symbol_break
    global gamemode_break

    while True:
        retry: str = input("Would you like to play again (1), select symbols again (2), select gamemode again (3) (progress score gets erased!), or exit (4)?\n?: ")
        
        if retry == "1":
            swap_turns()
            print()
            break
        elif retry == "2":
            gameplay_break = True
            swap_turns()
            reset_symbols()
            print()
            break
        elif retry == "3":
            gameplay_break = True
            symbol_break = True
            swap_turns()
            reset_score()
            reset_symbols()
            reset_gamemode()
            print()
            break
        elif retry == "4":
            swap_turns()
            gameplay_break = True
            symbol_break = True
            gamemode_break = True
            reset_symbols()
            reset_gamemode()
            print()
            break
        else:
            print("Please select 1 to play again, 2 to reselect symbols, 3 to reselect gamemode, and 4 to exit.\n")


# returns "None" if inconclusive, "Draw" if its a draw, "X" if its x, "O" if its o
def find_win() -> str:
    # Makes sure the game hasn't ended
    #if board_index[0] == " " or board_index[1] == " " or board_index[2] == " " or board_index[3] == " " or board_index[4] == " " or board_index[5] == " " or board_index[6] == " " or board_index[7] == " " or board_index[8] == " ":
        #return "None"

    # Horizontal wins
    if board_index[0] == "X" and board_index[1] == "X" and board_index[2] == "X":
        return "X"
    elif board_index[0] == "O" and board_index[1] == "O" and board_index[2] == "O":
        return "O"
    elif board_index[3] == "X" and board_index[4] == "X" and board_index[5] == "X":
        return "X"
    elif board_index[3] == "O" and board_index[4] == "O" and board_index[5] == "O":
        return "O"
    elif board_index[6] == "X" and board_index[7] == "X" and board_index[8] == "X":
        return "X"
    elif board_index[6] == "O" and board_index[7] == "O" and board_index[8] == "O":
        return "O"

    # Vertical wins
    elif board_index[0] == "X" and board_index[3] == "X" and board_index[6] == "X":
        return "X"
    elif board_index[0] == "O" and board_index[3] == "O" and board_index[6] == "O":
        return "O"
    elif board_index[1] == "X" and board_index[4] == "X" and board_index[7] == "X":
        return "X"
    elif board_index[1] == "O" and board_index[4] == "O" and board_index[7] == "O":
        return "O"
    elif board_index[6] == "X" and board_index[5] == "X" and board_index[8] == "X":
        return "X"
    elif board_index[6] == "O" and board_index[5] == "O" and board_index[8] == "O":
        return "O"


    # Diagonal wins
    elif board_index[0] == "X" and board_index[4] == "X" and board_index[8] == "X":
        return "X"
    elif board_index[0] == "O" and board_index[4] == "O" and board_index[8] == "O":
        return "O"
    elif board_index[2] == "X" and board_index[4] == "X" and board_index[6] == "X":
        return "X"
    elif board_index[2] == "O" and board_index[4] == "O" and board_index[6] == "O":
        return "O"


    # Makes sure the game hasn't ended
    elif board_index[0] == " " or board_index[1] == " " or board_index[2] == " " or board_index[3] == " " or board_index[4] == " " or board_index[5] == " " or board_index[6] == " " or board_index[7] == " " or board_index[8] == " ":
        return "None"
    

    # If theres a draw
    return "Draw"


def easy_computer_ai() -> None:
    # Makes sure the game hasn't ended
    if board_index[0] != " " and board_index[1] != " " and board_index[2] != " " and board_index[3] != " " and board_index[4] != " " and board_index[5] != " " and board_index[6] != " " and board_index[7] != " " and board_index[8] != " ":
        return

    index: int = 0

    while True:
        index = randint(0, 8)
        try:
            place(computer_symbol, index)
        except:
            pass
        else:
            break


def medium_computer_ai() -> None:
    # Makes sure the game hasn't ended
    if board_index[0] == " " or board_index[1] == " " or board_index[2] == " " or board_index[3] == " " or board_index[4] == " " or board_index[5] == " " or board_index[6] == " " or board_index[7] == " " or board_index[8] == " ":
        return
    
    chance: bool = True if randint(1, 4) == 1 else False

    if chance:
        hard_computer_ai()
    else:
        easy_computer_ai()


def hard_computer_ai() -> None:
    global DEBUGMODE

    across_top_win: list = [0, 1, 2]
    across_middle_win: list = [3, 4, 5]
    across_bottom_win: list = [6, 7, 8]
    down_left_win: list = [0, 3, 6]
    down_middle_win: list = [1, 4, 7]
    down_right_win: list = [2, 5, 8]
    positive_diagonal_win: list = [6, 4, 2]
    negative_diagonal_win: list = [0, 4, 8]

    all_wins: list = [across_top_win, across_middle_win, across_bottom_win, down_left_win, down_middle_win, down_right_win, positive_diagonal_win, negative_diagonal_win]

    board_weights: list = [0, 0, 0, 0, 0, 0, 0, 0, 0]


    for win_array in all_wins:
        weight: int = 0

        current_win_index_0: int = win_array[0]
        current_win_index_1: int = win_array[1]
        current_win_index_2: int = win_array[2]

        current_win_position_0: str = board_index[current_win_index_0]
        current_win_position_1: str = board_index[current_win_index_1]
        current_win_position_2: str = board_index[current_win_index_2]

        # Weight List:
        # -1: If there's already a symbol there
        # 0: Placing randomly but sticking to a winScenario
        # 0: If there's a playerSymbol and a computerSymbol in the same winScenario (neither can gain from the index)
        # 1: Blocking a player's 1/3 winScenario
        # 2: Developing one of your winScenarios to a 2/3 winScenario
        # 5: Blocking a player's 2/3 winScenario
        # 60: Making a 3/3 winScenario (just winning by placing the 3rd symbol)

        # Setting all taken positions to -1 in the board_weights[]
        if current_win_position_0 != ' ':
            board_weights[current_win_index_0] = -1
        if current_win_position_1 != ' ':
            board_weights[current_win_index_1] = -1
        if current_win_position_2 != ' ':
            board_weights[current_win_index_2] = -1


        # Making a 3/3 winScenario (just winning by placing the 3rd symbol)
        if (current_win_position_0 == ' ' and current_win_position_1 == computer_symbol and current_win_position_2 == computer_symbol) or (current_win_position_0 == computer_symbol and current_win_position_1 == ' ' and current_win_position_2 == computer_symbol) or (current_win_position_0 == computer_symbol and current_win_position_1 == computer_symbol and current_win_position_2 == ' '):
            weight = 60
            board_weights[current_win_index_0] = -1 if (board_weights[current_win_index_0] == -1) else (board_weights[current_win_index_0] + weight)
            board_weights[current_win_index_1] = -1 if (board_weights[current_win_index_1] == -1) else (board_weights[current_win_index_1] + weight)
            board_weights[current_win_index_2] = -1 if (board_weights[current_win_index_2] == -1) else (board_weights[current_win_index_2] + weight)
        

        # Blocking a player's 2/3 winScenario
        if (current_win_position_0 == ' ' and current_win_position_1 == player1_symbol and current_win_position_2 == player1_symbol) or (current_win_position_0 == player1_symbol and current_win_position_1 == ' ' and current_win_position_2 == player1_symbol) or (current_win_position_0 == player1_symbol and current_win_position_1 == player1_symbol and current_win_position_2 == ' '):
            weight = 5
            board_weights[current_win_index_0] = -1 if (board_weights[current_win_index_0] == -1) else (board_weights[current_win_index_0] + weight)
            board_weights[current_win_index_1] = -1 if (board_weights[current_win_index_1] == -1) else (board_weights[current_win_index_1] + weight)
            board_weights[current_win_index_2] = -1 if (board_weights[current_win_index_2] == -1) else (board_weights[current_win_index_2] + weight)


        # Developing one of your winScenarios to a 2/3 winScenario
        if (current_win_position_0 == computer_symbol and current_win_position_1 == ' ' and current_win_position_2 == ' ') or (current_win_position_0 == ' ' and current_win_position_1 == computer_symbol and current_win_position_2 == ' ') or (current_win_position_0 == ' ' and current_win_position_1 == ' ' and current_win_position_2 == computer_symbol):
            weight = 2
            board_weights[current_win_index_0] = -1 if (board_weights[current_win_index_0] == -1) else (board_weights[current_win_index_0] + weight)
            board_weights[current_win_index_1] = -1 if (board_weights[current_win_index_1] == -1) else (board_weights[current_win_index_1] + weight)
            board_weights[current_win_index_2] = -1 if (board_weights[current_win_index_2] == -1) else (board_weights[current_win_index_2] + weight)


        # Blocking a player's 1/3 winScenario
        if (current_win_position_0 == player1_symbol and current_win_position_1 == ' ' and current_win_position_2 == ' ') or (current_win_position_0 == ' ' and current_win_position_1 == player1_symbol and current_win_position_2 == ' ') or (current_win_position_0 == ' ' and current_win_position_1 == ' ' and current_win_position_2 == player1_symbol):
            weight = 1
            board_weights[current_win_index_0] = -1 if (board_weights[current_win_index_0] == -1) else (board_weights[current_win_index_0] + weight)
            board_weights[current_win_index_1] = -1 if (board_weights[current_win_index_1] == -1) else (board_weights[current_win_index_1] + weight)
            board_weights[current_win_index_2] = -1 if (board_weights[current_win_index_2] == -1) else (board_weights[current_win_index_2] + weight)


    if DEBUGMODE:
        dl1: str = " " + str(board_weights[0]) + " | " + str(board_weights[1]) + " | " + str(board_weights[2]) + " \n"
        dl2: str = "===|===|===\n"
        dl3: str = " " + str(board_weights[3]) + " | " + str(board_weights[4]) + " | " + str(board_weights[5]) + " \n"
        dl4: str = "===|===|===\n"
        dl5: str = " " + str(board_weights[6]) + " | " + str(board_weights[7]) + " | " + str(board_weights[8]) + " \n"
        debug_board: str = dl1 + dl2 + dl3 + dl4 + dl5

        print(debug_board)


    # Find the largest weight index number and the indexes at which that number appears
    largest_weight: int = max(board_weights)

    ## Calculates how many times that value appears in the array
    count: int = 0
    for weight in board_weights:
        if weight == largest_weight:
            count += 1
    
    ## If the largest weight value only appears once it'll just place it at it's index
    if count == 1:
        weight_index: int = board_weights.index(largest_weight)
        place(computer_symbol, weight_index + 1)
        return


    indexes: list = []

    ## Finds all the indexes that the value appears in
    for i in board_weights:
        if board_weights[i] == largest_weight:
            indexes.append(i)


    ## If the largest weight value appears more than once, randomly choose one of the indexes it's at and place it there
    random_index: int = randint(0, len(indexes) - 1)
    random_weighted_position: int = indexes[random_index]
    place(computer_symbol, random_weighted_position + 1)


print("==================================================\n\n")
print("Welcome to tictactoe!\n\n")

while True:
    if gamemode_break:
        break

    symbol_break = False

    gamemode = input("Gamemode chooser: Go against player (1) or computer (2)?\n?: ")

    if not DEBUGMODE and gamemode == "ballLife":
        print("DEBUGMODE ACTIVATED\n")
        DEBUGMODE = True
        continue

    if gamemode == "1":
        gamemode = "pvp"
        print("Player vs player gamemode chosen.\n")
    elif gamemode == "2":
        gamemode = "pvc"
        print("Player vs computer gamemode chosen.\n")

        while True:
            difficulty: str = input("Computer difficulty chooser: Would you like to go against easy (1) medium (2) or hard (3)?\n?: ")

            if difficulty == "1":
                computer_difficulty = "easy"
                print("Easy computer difficulty chosen.\n")
                break
            elif difficulty == "2":
                computer_difficulty = "medium"
                break
            elif difficulty == "3":
                computer_difficulty = "hard"
                break
            else:
                print("Please type 1 for easy difficulty, 2 for medium difficulty, or 3 for hard difficulty.\n")

    else:
        print("Please type 1 for player or 2 for computer.\n")
        continue

    while True:
        if symbol_break:
            break

        gameplay_break = False

        player1_symbol = input("Player 1: What symbol would you like? X (1) or O (2)\n?: ")
        
        if player1_symbol == "1" and gamemode == "pvp":
            player1_symbol = "X"
            player2_symbol = "O"
            print("Player 1 is X and Player 2 is O.\n")
        elif player1_symbol == "2" and gamemode == "pvp":
            player1_symbol = "O"
            player2_symbol = "X"
            print("Player 1 is O and Player 2 is X.\n")
        elif player1_symbol == "1" and gamemode == "pvc":
            player1_symbol = "X"
            computer_symbol = "O"
            print("Player 1 is X and The Computer is O.\n")
        elif player1_symbol == "2" and gamemode == "pvc":
            player1_symbol = "O"
            computer_symbol = "X"
            print("Player 1 is O and The Computer is X.\n")
        else:
            print("Please type 1 for X or 2 for O.\n")
            continue

        while True:
            if gameplay_break:
                break

            if instructions_not_printed_flag:
                print(instructions)
                instructions_not_printed_flag = False
            
            print(draw_board())

            if turn_is_player1:
                prompt_place(player1_symbol)
            elif gamemode == "pvp":
                prompt_place(player2_symbol)
            elif computer_difficulty == "easy":
                easy_computer_ai()
            elif computer_difficulty == "medium":
                medium_computer_ai()
            elif computer_difficulty == "hard":
                hard_computer_ai()
            
            turn_is_player1 = not turn_is_player1

            result: str = find_win()


            if result == "Draw":
                print("\nThe game has ended in a draw!")

                print("\n" + draw_board() +"\n")

                print("The current scores are:\n")
                print("Player 1: " + str(player1_score))
                if gamemode == "pvp":
                    print("Player 2: " + str(player2_score))
                else:
                    print("Computer: " + str(computer_score))

                print()
                reset_board()
                prompt_retry()
            elif result == player1_symbol:
                print("\nPlayer 1 (" + player1_symbol + ") has won the game!")
                player1_score += 1

                print("\n" + draw_board() +"\n")


                print("The current scores are:\n")
                print("Player 1: " + str(player1_score))
                print("Player 2: " + str(player2_score))

                print()
                reset_board()
                prompt_retry()
            elif result == player2_symbol:
                print("\nPlayer 2 (" + player2_symbol + ") has won the game!")
                player2_score += 1

                print("\n" + draw_board() +"\n")


                print("The current scores are:\n")
                print("Player 1: " + str(player1_score))
                
                if gamemode == "pvp":
                    print("Player 2: " + str(player2_score))
                else:
                    print("Computer: " + str(computer_score))

                print()
                reset_board()
                prompt_retry()
            elif result == computer_symbol:
                print("\nThe Computer (" + computer_symbol + ") has won the game!")
                computer_score += 1

                print("\n" + draw_board() +"\n")


                print("The current scores are:\n")
                print("Player 1: " + str(player1_score))
                print("Computer: " + str(computer_score))

                print()
                reset_board()
                prompt_retry()


print("\n\nThank you for playing!! Goodbye!!")
print("\n\n==================================================")
input()