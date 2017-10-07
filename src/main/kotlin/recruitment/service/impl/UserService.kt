package recruitment.service.impl

import org.springframework.stereotype.Service
import recruitment.ErrorsFactory
import recruitment.forms.UserForm
import recruitment.repository.UserRepository
import recruitment.service.interfaces.IUserService

@Service
class UserService( val userRepository : UserRepository) : IUserService {

    override fun checkUserAvailability(userForm: UserForm): String? {
        var passwordConfirmed = userForm.password.split(",").toHashSet()
        if (passwordConfirmed.size > 1) {
            return ErrorsFactory.PASSWORDS_NOT_MATCH
        }

        val email: String = userForm.email
        val login: String = userForm.login
        email.let { email ->
            if (userRepository.existsByEmail(email)) {
                return ErrorsFactory.EMAIL_EXISTS
            }
        }
        login.let { login ->
            if (userRepository.existsByLogin(login)) {
                return ErrorsFactory.LOGIN_EXISTS
            }
        }
        return null
    }
}