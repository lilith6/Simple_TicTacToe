package tictactoe

enum class GameStates(val message: String, val rule: String) {
    GAME_NOT_FINISHED("Game not finished", "when neither side has three in a row but the grid still has empty cells"),
    DRAW("Draw", "when no side has a three in a row and the grid has no empty cells"),
    X_WIN("X wins", "when the grid has three X’s in a row (including diagonals"),
    O_WIN("O wins", "when the grid has three O’s in a row (including diagonals)"),
}