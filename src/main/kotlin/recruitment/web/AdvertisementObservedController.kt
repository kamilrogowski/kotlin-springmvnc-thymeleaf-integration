package recruitment.web

import org.springframework.beans.propertyeditors.CustomDateEditor
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import recruitment.dto.FilterSearchDto
import recruitment.model.Advertisement
import recruitment.model.ObservedOffers
import recruitment.repository.AdvertisementRepository
import recruitment.repository.ObservedOffersRepository
import recruitment.repository.UserRepository
import recruitment.web.authorization.LoggedUser
import recruitment.web.wrappers.PageWrapper
import java.text.SimpleDateFormat
import java.util.*

@Controller
class AdvertisementObservedController(private val usersRepository: UserRepository) {

    @InitBinder
    fun initBinder(binder: WebDataBinder) {
        binder.registerCustomEditor(Date::class.java,
                CustomDateEditor(SimpleDateFormat("yyyy-MM-dd"), true, 10))
    }

    @GetMapping("/observed_offers")
    fun fetchUserObservedOffers(model: ModelMap): String {


        model.addAttribute("advertisements", usersRepository.findByLogin(
                LoggedUser.currentlyLoggedUser.username)
                .observeOffers
                .map { it.advertisement })
        return "observed_jobs.html"
    }
}