fun main() {
    val res = StrawberryStore2().buy(intArrayOf(5, 10, 5, 20))
    val res2 = StrawberryStore2().buy(intArrayOf(5, 20, 5, 20))
    val res3 = StrawberryStore2().buy(intArrayOf(5, 5, 10, 10, 20))
    println(res) // true
    println(res2) // false
    println(res3) //false
}


class StrawberryStore2 {
    private val bank = IntArray(3)

    fun buy(bills: IntArray): Boolean {
        for (bill in bills) {
            when (bill) {
                5 -> bank[0]++
                10 -> {
                    if (bank[0] == 0) return false
                    bank[0]--
                    bank[1]++
                }

                20 -> {
                    if (bank[1] > 0 && bank[0] > 0) {
                        bank[1]--
                        bank[0]--
                    } else if (bank[0] >= 3) {
                        bank[0] -= 3
                    } else {
                        return false
                    }
                    bank[2]++
                }

                else -> throw IllegalArgumentException( "Wrong bill: $bill, only 5, 10 and 20 Euro allowed" )
            }
        }
        return true
    }
}

