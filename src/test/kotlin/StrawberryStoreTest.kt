import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class StrawberryStoreTest {

    private lateinit var gs: StrawberryStore

    @BeforeEach
    fun init() {
        gs = StrawberryStore()
    }

    @Test
    fun `should return true`() {
        var actual = gs.buy(intArrayOf(5, 10, 5, 20))
        assertTrue(actual)
    }

    @Test
    fun `should return false`() {
        val actual = gs.buy(intArrayOf(5, 20, 5, 20))
        assertFalse(actual)
    }

    @Test
    fun `should throw an exception when wrong amount`() {
        assertThrows<IllegalArgumentException> { gs.buy(intArrayOf(5, 10, 5, 50)) }.message?.let {
        }
    }

    @Test
    fun `should throw an exception when array is empty`() {
        val e = assertThrows<IllegalArgumentException> { gs.buy(intArrayOf(5, 10, 5, 50)) }
        assertEquals("No bills", e.message)
    }
}