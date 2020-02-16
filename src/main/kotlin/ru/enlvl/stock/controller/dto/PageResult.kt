package ru.enlvl.stock.controller.dto

import org.springframework.data.domain.Page

data class PagedResult<T>(
        val entities: List<T>,
        val totalCount: Long,
        val totalPages: Int
)

fun Page<out Any>.toDto() = PagedResult(
        entities = content,
        totalCount = totalElements,
        totalPages = totalPages
)