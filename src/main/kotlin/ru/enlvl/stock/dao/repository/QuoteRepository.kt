package ru.enlvl.stock.dao.repository

import org.springframework.data.repository.PagingAndSortingRepository
import ru.enlvl.stock.dao.model.Quote
import java.util.*

interface QuoteRepository : PagingAndSortingRepository<Quote, UUID> {

}