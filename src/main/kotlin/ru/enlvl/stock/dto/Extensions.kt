package ru.enlvl.stock.dto

import ru.enlvl.stock.dao.model.Elvl
import ru.enlvl.stock.dao.model.Quote

fun QuoteDto.toModel() = Quote(
        isin = isin,
        bid = bid,
        ask = ask,
        createdAt = createdAt
)

fun ElvlDto.toModel() = Elvl(
        isin = isin,
        elvl = elvl
)