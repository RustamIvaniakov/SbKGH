package ru.enlvl.stock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class ElvlProviderApplication

fun main(args: Array<String>) {
	runApplication<ElvlProviderApplication>(*args)
}
