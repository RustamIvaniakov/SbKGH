package ru.enlvl.stock.common

import java.time.ZoneId
import java.time.ZonedDateTime

const val DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ssX"
const val DATE_FORMAT = "dd.MM.yyyy"

fun now() = ZonedDateTime.now(ZoneId.of("UTC"))!!