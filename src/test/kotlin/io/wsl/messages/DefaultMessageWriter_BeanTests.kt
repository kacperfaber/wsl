package io.wsl.messages

import io.wsl.tests.SpringTests
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals

@SpringTests
class DefaultMessageWriter_BeanTests {

    @Autowired
    lateinit var writer: DefaultMessageWriter

    @Test
    fun `writeMessage does not throw`() {
        assertDoesNotThrow { writer.writeMessage(Message("jebac", "disa")) }
    }

    @Test
    fun `writeMessage returns expected data when name=kacper data=faber`() {
        val msg = Message("kacper", "faber")
        assertEquals("kacper \"faber\"", writer.writeMessage(msg))
    }
}