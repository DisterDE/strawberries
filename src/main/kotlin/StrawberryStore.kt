import kotlin.math.min

fun main() {
    val res = StrawberryStore().buy(intArrayOf(5, 10, 5, 20))
    val res2 = StrawberryStore().buy(intArrayOf(5, 20, 5, 20))
    val res3 = StrawberryStore().buy(intArrayOf(5, 5, 10, 10, 20))
    println(res) // true
    println(res2) // false
    println(res3) //false
}


class StrawberryStore(allowedBills: IntArray = intArrayOf(5, 10, 20)) {
    private val decreasingBills = allowedBills.toSortedSet().reversed()
    private val bank = allowedBills.associateTo(mutableMapOf()) { it to 0 }

    fun buy(bills: IntArray): Boolean {
        require(bills.isNotEmpty()) { "No bills" }
        for (bill in bills) {
            require(bill in bank) { "Wrong bill: $bill, only ${bank.keys} Euro allowed" }

            bank[bill] = bank[bill]!! + 1

            var change = bill - PRICE
            if (change == 0) continue

            val debitBills = IntArray(bank.size)

            for ((i, currBill) in decreasingBills.withIndex()) {
                if (change >= currBill) {
                    val min = min(bank[currBill]!!, change / currBill)
                    change -= currBill * min
                    debitBills[i] = min
                }

                if (change == 0) break
            }
            if (change > 0) return false

            for ((i, currBill) in decreasingBills.withIndex()) {
                bank[currBill] = bank[currBill]!! - debitBills[i]
            }
        }
        return true
    }

    companion object {
        private const val PRICE = 5
    }
}

