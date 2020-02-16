package ru.enlvl.stock.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.enlvl.stock.dto.QuoteDto
import ru.enlvl.stock.service.ProcessorService

@Api("Методы для работы с квотами")
@RestController
@RequestMapping("$API_V1/quote", produces = [MediaType.APPLICATION_JSON_VALUE])
class QuoteController(private val processorService: ProcessorService) {


    @ApiOperation("Сохраняет квоты")
    @PostMapping(value = [""])
    fun saveQuote(@RequestBody dto: QuoteDto): ResponseEntity<Any> {
        processorService.saveQuoteInfo(dto)
        println("dto - $dto")
        return ResponseEntity(HttpStatus.CREATED)
    }
}