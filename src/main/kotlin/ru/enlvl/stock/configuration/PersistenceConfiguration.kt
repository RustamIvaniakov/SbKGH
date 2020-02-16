package ru.enlvl.stock.configuration

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EntityScan("ru.enlvl.stock.dao.model")
@EnableJpaRepositories(basePackages = ["ru.enlvl.stock.dao.repository"])
@EnableTransactionManagement
class PersistenceConfiguration