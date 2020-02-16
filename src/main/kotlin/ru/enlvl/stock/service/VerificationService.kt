package ru.enlvl.stock.service

import ru.enlvl.stock.dto.QuoteDto

interface VerificationService {

    fun verify(quoteDto: QuoteDto)

}