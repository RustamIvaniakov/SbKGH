package ru.enlvl.stock.service.impl

import org.springframework.stereotype.Service
import ru.enlvl.stock.dto.ElvlDto
import ru.enlvl.stock.dto.QuoteDto
import ru.enlvl.stock.service.DataService
import ru.enlvl.stock.service.ProcessorService
import ru.enlvl.stock.service.VerificationService

@Service
class ProcessorServiceImpl(
        private val verificationService: VerificationService,
        private val dataService: DataService
) : ProcessorService {

    override fun saveQuoteInfo(quoteDto: QuoteDto) {
        verificationService.verify(quoteDto)
        dataService.setQuote(quoteDto)
    }

    override fun getAllElvls(): List<ElvlDto> =
            dataService.getIlvls().map {
                ElvlDto(it.key, it.value)
            }

    override fun getElvlByIsin(isin: String) =
            dataService.getIlvls().get(isin)
                    ?.let { ElvlDto(isin, it) }

}