package org.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["org.example.*"])
class LaptopStoreApplication

fun main(args: Array<String>) {
    runApplication<LaptopStoreApplication>(*args)
}
