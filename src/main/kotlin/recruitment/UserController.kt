package recruitment

import recruitment.model.User
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.validation.Errors
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import recruitment.forms.UserForm
import recruitment.repository.CustomerRepository
import recruitment.repository.UserRepository
import javax.validation.Valid


/**
 * Created by Kamil on 9/23/2017.
 */
@Controller
class UserController (private val userRepository: UserRepository) {

    private val log = LoggerFactory.getLogger(UserController::class.java)
    private val REGISTER_HOME = "register.html"
    private val REDIRECT_REGISTER = "redirect:/join"

    @GetMapping("/login")
    fun login(model : ModelMap): String {
        val user = User()
        model.addAttribute("user", user)
        return "login.html"
    }

    @PostMapping("/login")
    fun login1(@ModelAttribute user: User): String {
        return "login.html"
    }

    @GetMapping("/join")
    fun register(model : ModelMap): String {
        model.remove("user")
        model.addAttribute("userForm", UserForm())
        return REGISTER_HOME
    }


    @PostMapping("/join")
    fun join(@ModelAttribute("userForm") @Valid userForm: UserForm,bindingResult: BindingResult, redirectAttributes: RedirectAttributes): String {
        log.info(userForm.toString())
        val user = User()
        user.login = userForm.login
        user.password = userForm.password
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

//    private fun checkUserAvailability(errors: BindException, email: String?, login: String?) {
//        email?.let { email ->
//            if (userRepository.existsByEmail(email)) {
//                errors.addError(FieldError("", "email", "already taken"))
//            }
//        }
//        login?.let { login ->
//            if (userRepository.existsByLogin(login)) {
//                errors.addError(FieldError("", "login", "already taken"))
//            }
//        }
//    }
}