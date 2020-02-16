package ru.enlvl.stock.dao.model

import javax.persistence.*

@Entity
@Table(name = "se_elvls")
data class Elvl(

        @field:Id
        @field:Column(name = "id", updatable = false, nullable = false, unique = true)
        val isin: String,

        @field:OneToMany(fetch = FetchType.LAZY, mappedBy = "elvl")
        val quotes: List<Quote> = emptyList(),

        @field:Column(name = "elvl", nullable = false)
        var elvl: Float,

        @field:Version
        @field:Column(name = "ver")
        var version: Long? = null
)