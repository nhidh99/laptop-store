package org.example.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(scanBasePackages = ["org.example.*"])
class LaptopStoreApplication

fun main(args: Array<String>) {
    runApplication<LaptopStoreApplication>(*args)
}
