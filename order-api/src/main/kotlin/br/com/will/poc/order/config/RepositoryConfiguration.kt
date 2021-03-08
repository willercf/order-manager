package br.com.will.poc.order.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EntityScan(basePackages = ["br.com.will.poc", "org.axonframework.eventhandling"])
@ComponentScan(value = ["br.com.will.poc"])
@EnableMongoRepositories(basePackages = ["br.com.will.poc"])
@EnableMongoAuditing
@EnableJpaRepositories
class RepositoryConfiguration