package org.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["org.example.*"])
open class LaptopStoreApplication

fun main(args: Array<String>) {
    runApplication<LaptopStoreApplication>(*args)
}
