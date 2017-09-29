package recruitment

import org.apache.tomcat.jni.Error
import recruitment.model.User
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import recruitment.ErrorsFactory.Companion.WRONG_CREDENTIALS
import recruitment.forms.UserForm
import recruitment.model.Role
import recruitment.repository.CustomerRepository
import recruitment.repository.RoleRepository
import recruitment.repository.UserRepository
import javax.validation.Valid


/**
 * Created by Kamil on 9/23/2017.
 */
@Controller
class UserController (private val userRepository: UserRepository, private val roleRepository: RoleRepository) {

    private val log = LoggerFactory.getLogger(UserController::class.java)
    private val REGISTER_HOME = "register.html"
    private val REDIRECT_REGISTER = "redirect:/join"
    private val REDIRECT_LOGIN = "redirect:/login"

    @GetMapping("/login")
    fun login(model : ModelMap): String {
        val user = User()
        model.addAttribute("user", user)
        return "login.html"
    }

    @PostMapping("/login")
    fun login1(@ModelAttribute user: User, redirectAttributes: RedirectAttributes): String {
        val isUserExists : Boolean = userRepository.existsByLoginAndPassword(user.login, user.password)
        if(!isUserExists){
            redirectAttributes.addFlashAttribute("error", ErrorsFactory.ERROR_MESSAGES[WRONG_CREDENTIALS])
            return REDIRECT_LOGIN
        }
        return "login.html"
    }

    @GetMapping("/join")
    fun register(model : ModelMap): String {
        model.remove("user")
//        var findAll:  = roleRepository.findAll().flatMap { Role::roleName }

        model.addAttribute("userForm", UserForm())
        return REGISTER_HOME
    }


    @PostMapping("/join")
    fun join(@ModelAttribute("userForm") @Valid userForm: UserForm,bindingResult: BindingResult, redirectAttributes: RedirectAttributes): String {
        log.info(userForm.toString())
        if (bindingResult.hasErrors()) {
//            return REGISTER_HOME
        }

        var code = checkUserAvailability(userForm)
                code?.let {
            redirectAttributes.addFlashAttribute("error", ErrorsFactory.ERROR_MESSAGES[code])
            return REDIRECT_REGISTER
        }
        val user = User()
        user.login = userForm.login
        user.password = userForm.password.split(",")[0]
        user.email = userForm.email
        user.userDetails.age = userForm.age
        user.userDetails.name = userForm.name
        user.userDetails.surname = userForm.surname
        user.userDetails.phone = userForm.phone
        user.userDetails.gender = userForm.gender
        userRepository.save(user)
        redirectAttributes.addFlashAttribute("success","You have been successfully registered")
        return REDIRECT_REGISTER
    }

    private fun checkUserAvailability(userForm: UserForm): String? {
        var passwordConfirmed = userForm.password.split(",").toHashSet()
        if(passwordConfirmed.size > 1){
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