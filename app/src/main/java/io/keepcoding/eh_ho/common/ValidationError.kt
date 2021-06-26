package io.keepcoding.eh_ho.common

import android.text.TextUtils

//using objects because of https://www.baeldung.com/kotlin/objects, "an object instead represents a single static instance"
object ValidationError {

    private val PATTERN_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$"

    fun validateUserName(username: String): Boolean {
        return username.length >= 5
    }

    fun validatePassword(password: String) : Boolean = PATTERN_PASSWORD.toRegex().matches(password)

    fun validateEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}