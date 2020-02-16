package ru.enlvl.stock.service

import ru.enlvl.stock.dto.ElvlDto
import ru.enlvl.stock.dto.QuoteDto

interface ProcessorService {
    fun saveQuoteInfo(quoteDto: QuoteDto)
    fun getElvlByIsin(isin:String): ElvlDto?
    fun getAllElvls(): List<ElvlDto>
}