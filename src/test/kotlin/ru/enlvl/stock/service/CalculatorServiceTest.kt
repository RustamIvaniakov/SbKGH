package ru.enlvl.stock.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.enlvl.stock.common.now
import ru.enlvl.stock.dto.QuoteDto
import ru.enlvl.stock.service.impl.CalculatorServiceImpl

class CalculatorServiceTest {

    private val calculatorService = CalculatorServiceImpl()

    @Test
    fun `should choose bid as elvl null`() {
        val quoteDto = QuoteDto(
                isin = "RU000A0JX0J2",
                bid = 100.3f,
                ask = 104.5f,
                createdAt = now()

        )
        Assertions.assertEquals(quoteDto.bid, calculatorService.calculateElvl(null, quoteDto).elvl)
    }

    @Test
    fun `should choose a new bid elvl less than the new bid`() {
        val oldElvl = 103.2f
        val quoteDto = QuoteDto(
                isin = "RU000A0JX0J2",
                bid = 105.3f,
                ask = 107.5f,
                createdAt = now()

        )
        Assertions.assertEquals(quoteDto.bid, calculatorService.calculateElvl(oldElvl, quoteDto).elvl)
    }
}