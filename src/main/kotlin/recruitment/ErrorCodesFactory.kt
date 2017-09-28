package recruitment

class ErrorsFactory{
    companion object {
        val EMAIL_EXISTS: String= "EMAIL_EXISTS"
        val LOGIN_EXISTS: String= "LOGIN_EXISTS"
        val ERROR_MESSAGES = mapOf(EMAIL_EXISTS to "User with that email already exists",
                LOGIN_EXISTS to "User with that login already exists")

    }
}
