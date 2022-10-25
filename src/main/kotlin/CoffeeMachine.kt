enum class CoffeeMachineState {
    ACTION, BUY, FILL, TAKE, REMAINING, EXIT
}

enum class CoffeeMachineProduct(
    val idValue: Int,
    val water: Int,
    val milk: Int,
    val beans: Int,
    val price: Int
) {
    NULL(-1, 0, 0, 0, 0),
    ESPRESSO(1, 250, 0, 16, 4),
    LATTE(2, 350, 75, 20, 7),
    CAPPUCCINO(3, 200, 100, 12, 6),
}

class CoffeeMachine(
    private var waterQu: Int = 400,
    private var milkQu: Int = 540,
    private var beanQu: Int = 120,
    private var cupsQu: Int = 9,
    private var money: Int = 550
) {
    var currentMachineState = CoffeeMachineState.ACTION

    fun inputAction(userAction: String) {
        currentMachineState = when (userAction) {
            "buy" -> CoffeeMachineState.BUY
            "fill" -> CoffeeMachineState.FILL
            "take" -> CoffeeMachineState.TAKE
            "remaining" -> CoffeeMachineState.REMAINING
            else -> CoffeeMachineState.EXIT
        }
    }

    fun findCoffee(coffeeId: Int): CoffeeMachineProduct {
        for (enum in CoffeeMachineProduct.values()) {
            if (enum.idValue == coffeeId) return enum
        }
        return CoffeeMachineProduct.NULL
    }

    fun actionBuy(coffeeChosen: CoffeeMachineProduct): Int {
        if (waterQu < coffeeChosen.water) return 1
        else if (milkQu < coffeeChosen.milk) return 2
        else if (beanQu < coffeeChosen.beans) return 3
        else if (cupsQu < 1) return 4

        waterQu -= coffeeChosen.water
        milkQu -= coffeeChosen.milk
        beanQu -= coffeeChosen.beans
        money += coffeeChosen.price
        cupsQu -= 1

        return 0
    }

    fun actionFill(
        waterToAdd: Int, milkToAdd: Int, beansToAdd: Int, cupsToAdd: Int
    ) {
        waterQu += waterToAdd
        milkQu += milkToAdd
        beanQu += beansToAdd
        cupsQu += cupsToAdd
    }

    fun actionTake() {
        println("\nI gave you \$$money\n")
        money = 0
    }

    fun actionRemaining() {
        println("\nThe coffee machine has:")
        println("$waterQu ml of water")
        println("$milkQu ml of milk")
        println("$beanQu g of coffee beans")
        println("$cupsQu disposable cups")
        println("\$$money of money\n")
    }
}
