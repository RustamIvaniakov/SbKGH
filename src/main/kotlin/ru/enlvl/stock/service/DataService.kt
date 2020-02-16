package ru.enlvl.stock.service

import ru.enlvl.stock.dto.ElvlDto
import ru.enlvl.stock.dto.QuoteDto
import java.time.ZonedDateTime
import java.util.concurrent.ConcurrentMap


interface DataService {
    fun getQuotes(): ConcurrentMap<ZonedDateTime, QuoteDto>
    fun getIlvls(): ConcurrentMap<String, Float>
    fun setQuote(quoteDto: QuoteDto)
    fun setIlvl(elvlDto: ElvlDto)
}