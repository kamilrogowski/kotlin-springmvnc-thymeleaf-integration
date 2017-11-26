package recruitment.web

import recruitment.model.User
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.validation.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import recruitment.ErrorsFactory
import recruitment.ErrorsFactory.Companion.USER_BLOCKED
import recruitment.ErrorsFactory.Companion.WRONG_CREDENTIALS
import recruitment.forms.UserForm
import recruitment.repository.RoleRepository
import recruitment.repository.UserRepository
import recruitment.service.interfaces.IUserService
import javax.validation.Valid


/**
 * Created by Kamil on 9/23/2017.
 */
@Controller
class UserController(
        private val userRepository: UserRepository,
        private val roleRepository: RoleRepository,
        private val userService: IUserService)  {

    private val log = LoggerFactory.getLogger(UserController::class.java)
    private val REDIRECT_REGISTER = "redirect:/join"
    private val REDIRECT_LOGIN = "redirect:/login"
    private val REGISTER_HOME = "register.html"

    @GetMapping("/login")
    fun login(model: ModelMap): String {
        val user = User()
        model.addAttribute("user", user)
        return "login.html"
    }

    @PostMapping("/login")
    fun login1(@ModelAttribute user: User, redirectAttributes: RedirectAttributes): String {
        val foundUser = userRepository.findByLoginAndPassword(user.login, user.password)

        foundUser?.let {
            if (!foundUser.isActive){
                redirectAttributes.addFlashAttribute("error", ErrorsFactory.ERROR_MESSAGES[USER_BLOCKED])
                return REDIRECT_LOGIN
            }
        } ?: run{
            redirectAttributes.addFlashAttribute("error", ErrorsFactory.ERROR_MESSAGES[WRONG_CREDENTIALS])
            return REDIRECT_LOGIN

        }

        return "login.html"
    }


    @GetMapping("/user/details/{id}")
    fun fetchDetailsOfUser(@PathVariable("id") id : String, model : Model) : String {
        model.addAttribute("user", userRepository.findById(id.toLong()).get())
        return "userDetails.html"
    }


    @PostMapping("/users/availability/{id}")
    fun blockOrActivateUser(@PathVariable("id") id : String, model : Model, redirectAttributes: RedirectAttributes): String {
        val userToBlock = userRepository.findById(id.toLong()).get()
        userToBlock.isActive = !userToBlock.isActive
        userRepository.save(userToBlock)
        redirectAttributes.addFlashAttribute("success", "User with login: ${userToBlock.login} has been successfully blocked/activated")
        return "redirect:/users"
    }


    @PostMapping("/join")
    fun join(@ModelAttribute("userForm") @Valid userForm: UserForm, bindingResult: BindingResult, redirectAttributes: RedirectAttributes): String {
        log.info(userForm.toString())
        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute("allRoles", roleRepository.findAll())
//            return REGISTER_HOME
        }

        val code = userService.checkUserAvailability(userForm)
        code?.let {
            redirectAttributes.addFlashAttribute("error", ErrorsFactory.ERROR_MESSAGES[code])
            return REDIRECT_REGISTER
        }
        val user = User()
        user.login = userForm.login
        user.password = BCryptPasswordEncoder().encode(userForm.password.split(",")[0])
        user.email = userForm.email
        user.userDetails.age = userForm.age
        user.userDetails.name = userForm.name
        user.userDetails.surname = userForm.surname
        user.userDetails.phone = userForm.phone
        user.userDetails.gender = userForm.gender

        if(userForm.role.id == "-1".toLong()){
            user.roles.add(roleRepository.findByRoleName("USER"))
        }
        else{
            user.roles.add(userForm.role)
        }
        userRepository.save(user)
        redirectAttributes.addFlashAttribute("success", "You have been successfully registered")
        return REDIRECT_REGISTER
    }
}