package ru.enlvl.stock.service.impl

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.enlvl.stock.dao.repository.ElvlRepository
import ru.enlvl.stock.dao.repository.QuoteRepository
import ru.enlvl.stock.dto.ElvlDto
import ru.enlvl.stock.dto.QuoteDto
import ru.enlvl.stock.dto.toModel
import ru.enlvl.stock.service.CalculatorService
import ru.enlvl.stock.service.DataService
import java.time.ZonedDateTime
import java.util.concurrent.ConcurrentHashMap
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class DataServiceImpl(
        private val quoteRepository: QuoteRepository,
        private val elvlRepository: ElvlRepository,
        private val calculatorService: CalculatorService
) : DataService {

    override fun getQuotes() = Cache.quotes
    override fun getIlvls() = Cache.elvls
    override fun setQuote(quoteDto: QuoteDto) {
        Cache.quotes.put(quoteDto.createdAt, quoteDto)
    }

    override fun setIlvl(elvlDto: ElvlDto) {
        Cache.elvls.put(elvlDto.isin, elvlDto.elvl)
    }

    @Scheduled(fixedRate = 100)
    fun saveQuotesToDb() {
        val quoteIterator = getQuotes().values.toMutableList().iterator()
        var time: ZonedDateTime? = null
        while (quoteIterator.hasNext()) {
            val quote = quoteIterator.next()
            quoteRepository.save(quote.toModel())
            // удалить из кеша те, что уже сохранили
            getQuotes().remove(quote.createdAt)
            // считаем только те квоты, что пришли последними
            if (time == null || time < quote.createdAt) {
                val isin = getIlvls().get(quote.isin)
                val elvlDto = calculatorService.calculateElvl(isin, quote)
                setIlvl(elvlDto)
                time = quote.createdAt
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    private fun saveElvl(elvlDto: ElvlDto) {
        elvlRepository.save(elvlDto.toModel())
    }
}

private object Cache {
    val quotes: ConcurrentHashMap<ZonedDateTime, QuoteDto>
    val elvls: ConcurrentHashMap<String, Float>

    init {
        quotes = ConcurrentHashMap()
        elvls = ConcurrentHashMap()
    }
}