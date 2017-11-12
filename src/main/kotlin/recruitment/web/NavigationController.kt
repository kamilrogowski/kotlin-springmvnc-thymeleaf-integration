package recruitment.web

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import recruitment.dto.FilterSearchDto
import recruitment.forms.UserForm
import recruitment.model.Advertisement
import recruitment.repository.AdvertisementRepository
import recruitment.repository.RoleRepository
import recruitment.repository.UserRepository
import recruitment.web.authorization.LoggedUser
import recruitment.web.wrappers.PageWrapper

/**
 * Created by Kamil on 11/1/2017.
 */
@Controller
class NavigationController(private val userRepository: UserRepository,
                           private val advertisementRepository: AdvertisementRepository,
                           private val roleRepository: RoleRepository){

    private val USERS_LIST = "usersList.html"
    private val ABOUT_US = "about_us.html"
    private val REGISTER_HOME = "register.html"

    @GetMapping("/users")
    fun fetchAllUsers(model: ModelMap): String {
        model.addAttribute("usersCounter", userRepository.count())
        model.addAttribute("users", userRepository.findAll())
        return USERS_LIST
    }

    @GetMapping("/about_us")
    fun redirectToCareer(model: ModelMap): String {
        return ABOUT_US
    }

    @GetMapping("/advertisements")
    fun showAllOffers(model: ModelMap, pageable: Pageable): String {

        val jobOffers = mutableListOf<Advertisement>()
        val pages = PageWrapper<Advertisement>(advertisementRepository.findByIsActiveTrue(pageable), "/advertisements")
        for (advertisement in pages.content) {
            advertisement.imageConverted = advertisement.toStreamingURI()
            jobOffers.add(advertisement)
        }
        model.addAttribute("page", pages)
        model.addAttribute("advertisments", jobOffers)
        model.addAttribute("searchFilter", FilterSearchDto())
        return "offersList.html"
    }


    @GetMapping("/advertisement/add")
    fun addAdvertisement(model: ModelMap): String {
        model.addAttribute("advertisement", Advertisement())
        return "advertisement.html"
    }

    @GetMapping("/join")
    fun register(model: ModelMap): String {
        model.remove("user")
        model.addAttribute("allRoles", roleRepository.findAll())
        model.addAttribute("userForm", UserForm())
        return REGISTER_HOME
    }


    @GetMapping("/my_advertisements")
    fun myAdvertisements(model: ModelMap): String {
        val user = userRepository.findByLogin(LoggedUser.currentlyLoggedUser.username)
        model.addAttribute("advertisements", advertisementRepository.findByUserOwner_id(user.id))
        return "my_advertisements.html"
    }




}