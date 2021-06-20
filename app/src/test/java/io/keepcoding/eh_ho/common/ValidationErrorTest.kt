package io.keepcoding.eh_ho.common

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class ValidationErrorTest {
    private val validationError: ValidationError = ValidationError()
    var usernameFalse = ""
    var usernameTrue = ""
    var passwordFalse = ""
    var passwordTrue = ""

    @BeforeEach
    fun init() {
        usernameFalse = "User"
        usernameTrue = "Username"

        passwordFalse = "1234"
        passwordTrue = "Summer$1"
    }

    @Test
    fun validateUsernameFalse_Test() {
        assertFalse(validationError.validateUserName(usernameFalse))
    }

    @Test
    fun validateUsernameTrue_Test() {
        assertTrue(validationError.validateUserName(usernameTrue))
    }

    @Test
    fun validatePasswordFalse_Test() {
        assertFalse(validationError.validatePassword(passwordFalse))
    }

    @Test
    fun validatePasswordTrue_Test() {
        assertTrue(validationError.validatePassword(passwordTrue))
    }

}

