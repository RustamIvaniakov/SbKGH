package ru.enlvl.stock.dao.repository

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import ru.enlvl.stock.dao.model.Elvl

@Repository
interface ElvlRepository : PagingAndSortingRepository<Elvl, String> {
    fun findFirstByIsin(isin: String): Elvl?
}