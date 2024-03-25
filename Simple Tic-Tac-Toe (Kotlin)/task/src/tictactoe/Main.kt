package tictactoe

fun isInputTwoDigits(coords: List<String>) = coords.size == 2 && coords.all { it.all { char -> char.isDigit() } }
fun isInputInInterval(coords: List<String>, min: Int, max: Int) = coords.all { it.toIntOrNull() in 1..3 }

fun main() {

    val tictactoe = TicTacToe()
    tictactoe.printBoard()

    while (tictactoe.currentState !in listOf(GameStates.X_WIN, GameStates.O_WIN, GameStates.DRAW)) {
        var a = -1
        var b = -1
        while (a == -1 || b == -1) {
            val coords = readln().split(" ")
            if (isInputTwoDigits(coords)) {
                if (isInputInInterval(coords, 1, 3)) {
                    a = coords[0].toInt()
                    b = coords[1].toInt()
                } else println("Coordinates should be from 1 to 3!")
            } else {
                println("You should enter numbers!")
            }
        }
        tictactoe.addToCell(a, b)
    }


}