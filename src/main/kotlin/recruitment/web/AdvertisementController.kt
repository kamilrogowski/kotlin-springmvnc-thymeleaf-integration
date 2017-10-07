package recruitment.web

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import recruitment.model.Advertisement

@Controller()
class AdvertisementController {

    @GetMapping("/add")
    fun addAdvertisement(model: ModelMap): String {
        model.addAttribute("advertisement", Advertisement())
        return "advertisement.html"
    }

}
