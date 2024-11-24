import kotlin.collections.setOf

fun main() {
    val res = StrawberryStore2().buy(intArrayOf(5, 10, 5, 20))
    val res2 = StrawberryStore2().buy(intArrayOf(5, 20, 5, 20))
    val res3 = StrawberryStore2().buy(intArrayOf(5, 5, 10, 10, 20))
    println(res) // true
    println(res2) // false
    println(res3) // false
}


class StrawberryStore2 {
    // YAGNI says
    // that we don't need
    // to keep the balance of the 20s because they are not needed in the calculations,
    // but I'm not sure if you want me to remove them
    private val bank = IntArray(3)
    private val allowedBills = setOf(5, 10, 20)

    fun buy(bills: IntArray): Boolean {
        require(bills.isNotEmpty()) { "No bills" }

        for (bill in bills) {
            require(bill in allowedBills) { "Wrong bill: $bill, only $allowedBills Euro allowed" }

            bank[bill / 10]++

            var change = bill - PRICE
            if (change == 0) continue

            if (change > 10 && bank[1] > 0) {
                bank[1]--
                change -= 10
            }

            val fives = change / 5
            if (fives > bank[0]) return false

            bank[0] -= fives
        }
        return true
    }

    companion object {
        private const val PRICE = 5
    }
}

