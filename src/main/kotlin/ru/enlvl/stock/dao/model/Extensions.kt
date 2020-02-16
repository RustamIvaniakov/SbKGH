package ru.enlvl.stock.dao.model

import ru.enlvl.stock.dto.ElvlDto
import ru.enlvl.stock.dto.QuoteDto

fun Elvl.toDto() = ElvlDto(
        isin = isin,
        elvl = elvl
)

fun Quote.toDto() = QuoteDto(
        isin = isin,
        bid = bid,
        ask = ask
)