package org.example

import kotlin.system.exitProcess


fun runTerminalGame() {
    val width = 20
    val height = 10
    var x = width / 2
    var y = height / 2

    while (true) {
        // Очистка экрана (ANSI — работает в большинстве современных терминалов)
        print("\u001b[2J\u001b[H")  // Clear + Cursor home

        // Рисуем карту
        for (row in 0 until height) {
            for (col in 0 until width) {
                print(if (row == y && col == x) "@" else ".")
            }
            println()
        }

        println("Управление: WASD или Q — выход")

        val input = readLine()?.uppercase()?.firstOrNull() ?: continue
        when (input) {
            'W' -> if (y > 0) y--
            'S' -> if (y < height - 1) y++
            'A' -> if (x > 0) x--
            'D' -> if (x < width - 1) x++
            'Q' -> exitProcess(0)
        }
    }
}