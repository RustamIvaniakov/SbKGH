package ru.enlvl.stock.service

import ru.enlvl.stock.dto.ElvlDto
import ru.enlvl.stock.dto.QuoteDto


interface CalculatorService {
    fun calculateElvl(elvl: Float?, quoteDto: QuoteDto): ElvlDto
}