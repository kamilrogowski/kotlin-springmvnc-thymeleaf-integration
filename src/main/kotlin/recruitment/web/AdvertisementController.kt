package recruitment.web

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import recruitment.model.Advertisement
import recruitment.repository.AdvertisementRepository
import javax.validation.Valid
import java.text.SimpleDateFormat
import org.springframework.beans.propertyeditors.CustomDateEditor
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import java.util.*


@Controller
class AdvertisementController(private val advertisementRepository: AdvertisementRepository) {

    @InitBinder
    fun initBinder(binder: WebDataBinder) {
        binder.registerCustomEditor(Date::class.java,
                CustomDateEditor(SimpleDateFormat("yyyy-MM-dd"), true, 10))
    }

    @GetMapping("/advertisement/add")
    fun addAdvertisement(model: ModelMap): String {
        model.addAttribute("advertisement", Advertisement())
        return "advertisement.html"
    }

    @PostMapping("/advertisement/add")
    fun add(@ModelAttribute advertisement: Advertisement, bindingResult: BindingResult, redirectAttributes: RedirectAttributes, model:ModelMap): String{
//        advertisementRepository.save(advertisement)
        redirectAttributes.addAttribute("advertisement", advertisement)
        return "redirect:/advertisementDetails"
    }
}
