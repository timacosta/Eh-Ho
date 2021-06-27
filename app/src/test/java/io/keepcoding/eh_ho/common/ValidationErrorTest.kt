package io.keepcoding.eh_ho.common

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class ValidationErrorTest {
    private val validationError: ValidationError = ValidationError
    var usernameFalse = ""
    var usernameTrue = ""
    var passwordFalse = ""
    var passwordTrue = ""
    var emailTrue = ""
    var emailFalse = ""

    @BeforeEach
    fun init() {
        usernameFalse = "User"
        usernameTrue = "Username"

        passwordFalse = "1234"
        passwordTrue = "Summer$1"

        emailFalse = "name,surname@provider,com"
        emailTrue = "name.surname@provider.com"
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

    //Apparently not able to test TextUtils, See:
    // http://tools.android.com/tech-docs/unit-testing-support#TOC-Method-...-not-mocked.-
//    @Test
//    fun validateEmailFalse_Test() {
//        assertFalse(validationError.validateEmail(emailFalse))
//    }
//
//    @Test
//    fun validateEmailTrue_Test() {
//        assertFalse(validationError.validateEmail(emailTrue))
//    }

    @Test
    fun confirmPasswordTrue_Test() {
        assertTrue(validationError.validateConfirmPassword(passwordTrue, passwordTrue))
    }

    @Test
    fun confirmPasswordFalse_Test() {
        assertFalse(validationError.validateConfirmPassword(passwordTrue, passwordFalse))
    }

}

