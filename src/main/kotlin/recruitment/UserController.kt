package recruitment

import recruitment.model.User
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import recruitment.forms.UserForm
import recruitment.repository.CustomerRepository
import recruitment.repository.UserRepository


/**
 * Created by Kamil on 9/23/2017.
 */
@Controller
class UserController (private val userRepository: UserRepository) {
    private val log = LoggerFactory.getLogger(UserController::class.java)

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
        return "register.html"
    }

    @PostMapping("/join")
    fun join(@ModelAttribute userForm: UserForm, redirectAttributes: RedirectAttributes): String {
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
        userRepository.save(user),


        redirectAttributes.addFlashAttribute("success","Udało się")
        return "register.html"
    }
}