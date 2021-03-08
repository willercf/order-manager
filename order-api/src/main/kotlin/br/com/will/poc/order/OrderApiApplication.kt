package br.com.will.poc.order

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import java.util.stream.Collectors
import java.util.stream.Stream

@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync
class OrderApiApplication

val LOGGER: Logger = LoggerFactory.getLogger(OrderApiApplication::class.java)

fun main(args: Array<String>) {
	runApplication<OrderApiApplication>(*args)
	LOGGER.info(Stream.of("",
		"---------------------------------------",
		" Order Api - Started ",
		"---------------------------------------"
	).collect(Collectors.joining("\n")));
}