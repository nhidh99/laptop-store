package org.example.constant


object TokenConstants {
    const val VERIFY_TOKEN_EXPIRATION_IN_MINUTE = 15L
    const val ACCESS_TOKEN_EXPIRATION_IN_MILLISECOND = 900_000L         // 15 minutes
    const val REFRESH_TOKEN_EXPIRATION_IN_MILLISECOND = 2_592_000_000L  // 30 days

    const val RESET_PASSWORD = "RESET_PASSWORD"
    const val VERIFY_EMAIL = "VERIFY_EMAIL"
}