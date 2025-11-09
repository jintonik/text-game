package org.example

import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextCharacter.fromString
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory

fun runLanternaGame() {
    val terminal = DefaultTerminalFactory().createTerminal()
    val screen = TerminalScreen(terminal).apply {
        terminal.enterPrivateMode()
        terminal.setCursorVisible(false)
        startScreen()
    }

    var x = 10
    var y = 5
    val width = 30
    val height = 15

    val screenCharacter: Array<TextCharacter> = fromString(".")

    try {
        while (true) {
            // Очистка + отрисовка
            screen.clear()
            for (row in 0 until height) {
                for (col in 0 until width) {
                    screen.setCharacter(col, row, screenCharacter[0])
                }
            }
            screen.setCharacter(x, y, fromString("@")[0].apply { TextColor.ANSI.GREEN })

            screen.refresh()

            // Ждём нажатие (без Enter!)
            val key = screen.readInput()
            when (key.keyType) {
                KeyType.ArrowUp -> if (y > 0) y--
                KeyType.ArrowDown -> if (y < height - 1) y++
                KeyType.ArrowLeft -> if (x > 0) x--
                KeyType.ArrowRight -> if (x < width - 1) x++
                KeyType.EOF, KeyType.Escape, KeyType.valueOf("Q") -> break
                else -> {}
            }
        }
    } finally {
        screen.stopScreen()
        terminal.exitPrivateMode()
    }
}