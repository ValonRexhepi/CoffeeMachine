fun main() {
    val coffeeMachine = CoffeeMachine()

    var isRunning = true
    while (isRunning) {
        println("Write action (buy, fill, take, remaining, exit):")
        val userActionInput = readln()

        coffeeMachine.inputAction(userActionInput)

        when (coffeeMachine.currentMachineState) {
            CoffeeMachineState.BUY -> {
                println("\n1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
                val valueChosen = readln()

                if (valueChosen != "back") {
                    val coffeeChosen =
                        coffeeMachine.findCoffee(valueChosen.toInt())
                    if (coffeeChosen.idValue != 0) {
                        when (coffeeMachine.actionBuy(coffeeChosen)) {
                            1 -> println("Sorry, not enough water!")
                            2 -> println("Sorry, not enough milk!")
                            3 -> println("Sorry, not enough beans!")
                            4 -> println("Sorry, not enough cups!")
                            else -> println("I have enough resources, making you a coffee!")
                        }
                    }
                }
                println()
            }

            CoffeeMachineState.FILL -> {
                println("\nWrite how many ml of water you want to add:")
                val waterToAdd = readln().toInt()
                println("Write how many ml of milk you want to add:")
                val milkToAdd = readln().toInt()
                println("Write how many grams of coffee beans you want to add:")
                val beansToAdd = readln().toInt()
                println("Write how many disposable cups you want to add:")
                val cupsToAdd = readln().toInt()
                println()

                coffeeMachine.actionFill(
                    waterToAdd, milkToAdd, beansToAdd, cupsToAdd
                )
            }

            CoffeeMachineState.TAKE -> coffeeMachine.actionTake()
            CoffeeMachineState.REMAINING -> coffeeMachine.actionRemaining()
            else -> isRunning = false
        }

        coffeeMachine.currentMachineState = CoffeeMachineState.ACTION
    }
}


