package ru.enlvl.stock.dao

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.enlvl.stock.common.now
import ru.enlvl.stock.dao.model.Elvl
import ru.enlvl.stock.dao.model.Quote
import ru.enlvl.stock.dao.model.toDto
import ru.enlvl.stock.dao.repository.ElvlRepository
import ru.enlvl.stock.dao.repository.QuoteRepository
import ru.enlvl.stock.service.CalculatorService
import ru.enlvl.stock.service.impl.CalculatorServiceImpl

@ExtendWith(SpringExtension::class)
@DataJpaTest
class DaoTests {

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var elvlRepository: ElvlRepository

    @Autowired
    lateinit var quoteRepository: QuoteRepository


    @Test
    fun `should save stock info`() {
        val stockInfo = Quote(
                isin = "RU0007661625",
                bid = 100.2f,
                ask = 101.9f,
                createdAt = now()
        )
        quoteRepository.save(stockInfo)
        Assertions.assertEquals(stockInfo, quoteRepository.findById(stockInfo.id).takeIf { it.isPresent }?.get())
    }

    @Test
    fun `should save elv info`() {

        val calculatorService: CalculatorService = CalculatorServiceImpl()

        val quote = Quote(
                isin = "RU0007661625",
                bid = 100.2f,
                ask = 101.9f,
                createdAt = now()
        )
        quoteRepository.save(quote)

        val elvlEntity = Elvl(
                isin = quote.isin,
                elvl = calculatorService.calculateElvl(104.4f, quote.toDto()).elvl
        )

        elvlRepository.save(elvlEntity)

        Assertions.assertEquals(quote, quoteRepository.findById(quote.id).takeIf { it.isPresent }?.get())
        Assertions.assertEquals(elvlEntity, elvlRepository.findById(elvlEntity.isin).takeIf { it.isPresent }?.get())
    }
}