package hello

import hello.model.User
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.ModelAttribute


/**
 * Created by Kamil on 9/23/2017.
 */
@Controller
class UserController {

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

    @GetMapping("/register")
    fun register(model : ModelMap): String {
        val user = User()
        model.addAttribute("user", user)
        return "register.html"
    }
}