package ru.enlvl.stock.dto

import ru.enlvl.stock.common.now
import java.time.ZonedDateTime

data class QuoteDto(val isin: String, val bid: Float?, val ask: Float?, val createdAt : ZonedDateTime = now())