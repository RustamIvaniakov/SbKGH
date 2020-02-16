package ru.enlvl.stock.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import ru.enlvl.stock.dto.QuoteDto
import ru.enlvl.stock.exception.VerificationException
import ru.enlvl.stock.service.impl.VerificationServiceImpl


class VerificationServiceTest {

    private val verificationService = VerificationServiceImpl()

    @Test
    fun `shuld fail for incorrect digit`() {

        val quoteDto = QuoteDto(isin = "ERr3234223423", bid = 100.5f, ask = 103.3f)
        Assertions.assertThrows(VerificationException::class.java,
                (Executable { verificationService.verify(quoteDto) })
        )
    }

    @Test
    fun `shuld pass for correct digit`() {
        val quoteDto = QuoteDto(isin = "RU0007661625", bid = 100.5f, ask = 103.3f)
        Assertions.assertDoesNotThrow((Executable { verificationService.verify(quoteDto) })
        )
    }
}