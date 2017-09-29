package recruitment

class ErrorsFactory{
    companion object {
        val EMAIL_EXISTS: String= "EMAIL_EXISTS"
        val LOGIN_EXISTS: String= "LOGIN_EXISTS"
        val PASSWORDS_NOT_MATCH: String = "PASSWORDS_NOT_MATCH"
        val ERROR_MESSAGES = mapOf(EMAIL_EXISTS to "User with that email already exists",
                LOGIN_EXISTS to "User with that login already exists",
                PASSWORDS_NOT_MATCH to "Passwords are not the same")

    }
}
