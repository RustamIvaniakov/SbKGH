package ru.enlvl.stock.service.impl

import org.springframework.stereotype.Service
import ru.enlvl.stock.dto.ElvlDto
import ru.enlvl.stock.dto.QuoteDto
import ru.enlvl.stock.service.CalculatorService

@Service
class CalculatorServiceImpl : CalculatorService {

    override fun calculateElvl(elvl: Float?, quoteDto: QuoteDto): ElvlDto {
        var calcElvl: Float = if (elvl == null) {
            quoteDto.bid ?: quoteDto.ask!!
        } else {
            quoteDto.bid
                    ?.let {
                        if (quoteDto.bid > elvl) {
                            quoteDto.bid
                        } else {
                            quoteDto.ask!!
                        }
                    }
                    ?: quoteDto.ask!!

        }
        println("calculated elvl for ISIN - ${quoteDto.isin} is $calcElvl")
        return ElvlDto(quoteDto.isin, calcElvl)
    }

}