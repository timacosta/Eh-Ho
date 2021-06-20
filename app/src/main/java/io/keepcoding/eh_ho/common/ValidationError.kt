package io.keepcoding.eh_ho.common

class ValidationError {

    private val PATTERN_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$"

    fun validateUserName(username: String): Boolean {
        return username.length >= 5
    }

    fun validatePassword(password: String) : Boolean = PATTERN_PASSWORD.toRegex().matches(password)

}