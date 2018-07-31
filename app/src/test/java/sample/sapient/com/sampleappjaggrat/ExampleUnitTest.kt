package sample.sapient.com.sampleappjaggrat

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnitPlatform::class)
object ExampleUnitTest : Spek({
    val calculator by memoized { () }

    it("should be 4") {
        assertThat(calculator.add(2, 2), equalTo(4))
    }
})
