package tictactoe

class TicTacToe {

    private var board = mutableListOf(
        mutableListOf<Char>(' ', ' ', ' '),
        mutableListOf<Char>(' ', ' ', ' '),
        mutableListOf<Char>(' ', ' ', ' ')
    )
    var currentState = GameStates.GAME_NOT_FINISHED
    private var turnX = true

    fun printBoard() {
        println(
            """
---------
| ${board[0][0]} ${board[0][1]} ${board[0][2]} |
| ${board[1][0]} ${board[1][1]} ${board[1][2]} |
| ${board[2][0]} ${board[2][1]} ${board[2][2]} |
---------
    """.trimIndent()
        )
    }

    private fun isCellFree(x: Int, y: Int) = board[x - 1][y - 1] == ' '

    fun addToCell(a: Int, b: Int) {

        val playerChar = if (turnX) 'X' else 'O'
        if (isCellFree(a, b)) {
            board[a - 1][b - 1] = playerChar
            decideGameState()
            turnX = !turnX
        } else {
            println("This cell is occupied! Choose another one!")
        }
    }

    private fun checkWinX(list: List<Char>) = list.all { it == 'X' }

    private fun checkWinO(list: List<Char>) = list.all { it == 'O' }

    private fun hasEmptySpots() = board.flatten().contains(' ')

    private fun getColumns(): List<List<Char>> {
        return listOf(
            listOf(board[0][0], board[1][0], board[2][0]),
            listOf(board[0][1], board[1][1], board[2][1]),
            listOf(board[0][2], board[1][2], board[2][2])
        )
    }

    private fun getDiagonals(): List<List<Char>> {

        return listOf(
            listOf(board[0][0], board[1][1], board[2][2]),
            listOf(board[0][2], board[1][1], board[2][0])
        )
    }

    private fun decideGameState() {
        val cols = getColumns()
        val diags = getDiagonals()

        val xWins = board.count() { checkWinX(it) } + cols.count() { checkWinX(it) } + diags.count() { checkWinX(it) }
        val oWins = board.count() { checkWinO(it) } + cols.count() { checkWinO(it) } + diags.count() { checkWinO(it) }

        when {
            xWins > 0 && oWins == 0 -> {
                printBoard()
                print(GameStates.X_WIN.message)
                currentState = GameStates.X_WIN
            }

            xWins == 0 && oWins > 0 -> {
                printBoard()
                print(GameStates.O_WIN.message)
                currentState = GameStates.O_WIN
            }

            else -> when (hasEmptySpots()) {
                false -> {
                    printBoard()
                    print(GameStates.DRAW.message)
                    currentState = GameStates.DRAW
                }

                true -> {
                    printBoard()
                    currentState = GameStates.GAME_NOT_FINISHED
                }

            }
        }
    }

}


