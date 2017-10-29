package recruitment.web

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import recruitment.model.Advertisement
import recruitment.repository.AdvertisementRepository
import java.text.SimpleDateFormat
import org.springframework.beans.propertyeditors.CustomDateEditor
import org.springframework.data.domain.Pageable
import org.springframework.ui.Model
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
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
    fun add(@RequestParam("file") file: MultipartFile, @ModelAttribute advertisement: Advertisement, bindingResult: BindingResult, redirectAttributes: RedirectAttributes, model: ModelMap): String {
//        advertisementRepository.save(advertisement)
        advertisement.companyImage = file.bytes
        redirectAttributes.addFlashAttribute("advertisement", advertisement)
        return "redirect:/advertisement/confirm"
    }

    @GetMapping("/advertisement/confirm")
    fun confirm(@ModelAttribute("advertisement") advertisement: Advertisement, bindingResult: BindingResult, model: ModelMap, rediirect: RedirectAttributes): String {
//        if (bindingResult.hasErrors()) {
//            return "advertisement.html"
//        }

        rediirect.addFlashAttribute("advertisement", advertisement)
        rediirect.addFlashAttribute("advertisement1", advertisement)
        advertisementRepository.save(advertisement)
        return "advertisementDetails.html"
    }

    @GetMapping("/advertisements")
    fun showaAllOffers(model: ModelMap, pageable: Pageable): String {

        val jobOffers = mutableListOf<Advertisement>()
        for (advertisement in advertisementRepository.findAll()) {
            advertisement.imageConverted = advertisement.toStreamingURI()
            jobOffers.add(advertisement)
        }
        model.addAttribute("advertisments", jobOffers)
        return "offersList.html"
    }

    @GetMapping("/advertisement/details/{id}")
    fun fetchAdvertisementAndUpdateVisitCounter(@PathVariable("id") id: String, model: Model): String {
        val offerFound = advertisementRepository.findById(id.toLong()).get()
        offerFound.visitCounter++
        advertisementRepository.save(offerFound)
        model.addAttribute("advertisement", offerFound)
        return "advertisementDetails.html"
    }


}

