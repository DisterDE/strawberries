import kotlin.collections.sortedSetOf
import kotlin.math.min

fun main() {
    val res = StrawberryStore().buy(intArrayOf(5, 10, 5, 20))
    val res2 = StrawberryStore().buy(intArrayOf(5, 20, 5, 20))
    val res3 = StrawberryStore().buy(intArrayOf(5, 5, 10, 10, 20))
    println(res) // true
    println(res2) // false
    println(res3) //false
}


class StrawberryStore {
    private val bank = BILLS.associateTo(mutableMapOf()) { it to 0 }

    fun buy(bills: IntArray): Boolean {
        require(bills.isNotEmpty()) { "No bills" }
        for (bill in bills) {
            require(bill in BILLS) { "Wrong bill: $bill, only $BILLS Euro allowed" }

            bank[bill] = bank[bill]!! + 1

            var change = bill - PRICE
            if (change == 0) continue

            val temp = IntArray(BILLS.size)

            for((i, n) in BILLS.withIndex()) {
                if (change >= n) {
                    val min = min(bank[n]!!, change / n)
                    change -= n * min
                    temp[i] = min
                }

                if (change == 0) break
            }
            if (change > 0) return false

            for((i, n) in BILLS.withIndex()) {
                bank[n] = bank[n]!! - temp[i]
            }
        }
        return true
    }

    companion object {
        private const val PRICE = 5
        private val BILLS = sortedSetOf(Comparator.reverseOrder<Int>(), 5, 10, 20)
    }
}
