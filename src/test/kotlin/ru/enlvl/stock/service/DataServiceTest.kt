package ru.enlvl.stock.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.enlvl.stock.dao.repository.ElvlRepository
import ru.enlvl.stock.dao.repository.QuoteRepository
import ru.enlvl.stock.dto.QuoteDto
import ru.enlvl.stock.service.impl.CalculatorServiceImpl
import ru.enlvl.stock.service.impl.DataServiceImpl
import java.lang.Exception
import java.time.ZonedDateTime
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Supplier

@ExtendWith(SpringExtension::class)
@DataJpaTest
class DataServiceTest {

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var elvlRepository: ElvlRepository

    @Autowired
    lateinit var quoteRepository: QuoteRepository

    private val executorService : ExecutorService = Executors.newFixedThreadPool(30)

    @Test
    fun `should work correctly within many threads`() {
        val dataService: DataService = DataServiceImpl(quoteRepository, elvlRepository, CalculatorServiceImpl())

        var errors = AtomicInteger()
        var iterations = AtomicInteger()
        val countDownLatch = CountDownLatch(150)
        for (i in 1..150){
            CompletableFuture.supplyAsync((
                    Supplier {
                        val quote = QuoteDto(
                                isin = "RU000A0JX0J2",
                                bid = 100.2f,
                                ask = 102.5f,
                                // треды могут стартовать параллельно время повторяется
                                createdAt = ZonedDateTime.now()
                        )
                        try{
                            dataService.setQuote(quote)
                            iterations.getAndIncrement()
                            countDownLatch.countDown()
                        }catch (e: Exception){
                            errors.getAndIncrement()
                        }
            }), executorService)
        }

        countDownLatch.await(10, TimeUnit.SECONDS)
        Assertions.assertEquals(0, errors.get())
        Assertions.assertEquals(150, iterations.get())
    }

}