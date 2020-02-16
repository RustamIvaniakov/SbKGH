package ru.enlvl.stock.controller

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.enlvl.stock.service.ProcessorService

@RestController
@RequestMapping("$API_V1/elvl", produces = [MediaType.APPLICATION_JSON_VALUE])
class ElvlController(private val processorService: ProcessorService) {

    @ApiOperation("Вернуть Elvl по ISIN ")
    @GetMapping(value = ["/isin/{isin}/"])
    fun getElvlByIsin(@ApiParam @RequestParam isin: String) = processorService.getElvlByIsin(isin)


    @ApiOperation("Вернуть Elvl по ISIN ")
    @GetMapping(value = [""])
    fun getAllElvls() = processorService.getAllElvls()
}