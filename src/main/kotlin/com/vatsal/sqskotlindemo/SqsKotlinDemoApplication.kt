package com.vatsal.sqskotlindemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SqsKotlinDemoApplication

fun main(args: Array<String>) {
	runApplication<SqsKotlinDemoApplication>(*args)
}
