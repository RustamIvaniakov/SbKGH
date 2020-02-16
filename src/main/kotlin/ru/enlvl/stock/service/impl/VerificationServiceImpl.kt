package ru.enlvl.stock.service.impl

import org.apache.commons.validator.routines.checkdigit.ISINCheckDigit
import org.springframework.stereotype.Service
import ru.enlvl.stock.dto.QuoteDto
import ru.enlvl.stock.exception.VerificationException
import ru.enlvl.stock.service.VerificationService

@Service
class VerificationServiceImpl : VerificationService {

    override fun verify(quoteDto: QuoteDto) {
        var message = StringBuffer()

        if (!verifyIsin(quoteDto.isin)) message.appendln("ISIN is incorrect")
        quoteDto.bid?.let {
            quoteDto.ask?.let {
                if (!checkBidAck(quoteDto.bid, quoteDto.ask)) message.appendln("BID must be less than ASK")
            }
        }

        if (message.length > 0) throw VerificationException(message.toString())
    }

    /**
     * Позитивная проверка, что ISIN корректный
     */
    private fun verifyIsin(isin: String) =
            ISINCheckDigit.ISIN_CHECK_DIGIT.isValid(isin)

    /**
     * Позитивная проверка, что BID меньше ACK
     */
    private fun checkBidAck(bid: Float, ask: Float) = bid < ask

}