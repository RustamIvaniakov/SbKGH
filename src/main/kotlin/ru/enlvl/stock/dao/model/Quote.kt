package ru.enlvl.stock.dao.model
import java.time.ZonedDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "se_quotes")
data class Quote(

        @field:Id
        @field:Column(name = "id", updatable = false, nullable = false)
        val id: UUID = UUID.randomUUID(),

        @field:Column(name = "isin", updatable = false, nullable = false)
        val isin: String,

        @ManyToOne
        @JoinColumn(name = "stock_id", referencedColumnName = "id", nullable = true)
        val elvl: Elvl? = null,

        @field:Column(name = "bid", updatable = false, nullable = false)
        val bid: Float? = null,

        @field:Column(name = "ask", updatable = false, nullable = true)
        val ask: Float? = null,

        @field:Column(name = "created_at", nullable = false)
        val createdAt: ZonedDateTime,

        @field:Version
        @field:Column(name = "ver")
        var version: Long? = null
)