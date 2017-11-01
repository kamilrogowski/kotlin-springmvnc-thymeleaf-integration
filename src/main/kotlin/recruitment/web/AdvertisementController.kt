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
import recruitment.dto.FilterSearchDto
import recruitment.web.wrappers.PageWrapper
import java.util.*

@Controller
class AdvertisementController(private val advertisementRepository: AdvertisementRepository) {

    @InitBinder
    fun initBinder(binder: WebDataBinder) {
        binder.registerCustomEditor(Date::class.java,
                CustomDateEditor(SimpleDateFormat("yyyy-MM-dd"), true, 10))
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


    @GetMapping("/advertisement/details/{id}")
    fun fetchAdvertisementAndUpdateVisitCounter(@PathVariable("id") id: String, model: Model): String {
        val offerFound = advertisementRepository.findById(id.toLong()).get()
        offerFound.visitCounter++
        advertisementRepository.save(offerFound)
        model.addAttribute("advertisement", offerFound)
        return "advertisementDetailsList.html"
    }


    @PostMapping("/advertisement/subscribe/{id}")
    fun subscribeJobOffer(@PathVariable("id") id: String, model: Model): String {
        throw NotImplementedError("Not implemented yet")
    }

    @PostMapping("/advertisements/search")
    fun search(@ModelAttribute("searchFilter") searchFilter: FilterSearchDto, model: ModelMap, pageable: Pageable): String {
        val pages: PageWrapper<Advertisement>
        val searchFilterTitleQuery ="%" + searchFilter.title + "%"
        val searchFilterCityQuery ="%" + searchFilter.city + "%"

        if (searchFilter.title.isNotEmpty()) {
            pages = PageWrapper<Advertisement>(advertisementRepository.findByTitleLikeAndIsActiveTrue(searchFilterTitleQuery,
                    pageable), "/advertisements")
        } else if (searchFilter.city.isNotEmpty()) {
            pages = PageWrapper<Advertisement>(advertisementRepository.findByCompany_CityLikeAndIsActiveTrue(
                    searchFilterCityQuery, pageable), "/advertisements")
        } else if (searchFilter.city.isNotEmpty() and searchFilter.title.isNotEmpty()) {
            pages = PageWrapper<Advertisement>(advertisementRepository.findByCompany_CityLikeAndTitleLikeAndIsActiveTrue(
                    searchFilterCityQuery,searchFilterTitleQuery, pageable), "/advertisements")
        } else {
            pages = PageWrapper<Advertisement>(advertisementRepository.findByIsActiveTrue(pageable), "/advertisements")
        }

        val jobOffers = mutableListOf<Advertisement>()
        for (advertisement in pages.content) {
            advertisement.imageConverted = advertisement.toStreamingURI()
            jobOffers.add(advertisement)
        }
        model.addAttribute("page", pages)
        model.addAttribute("advertisments", jobOffers)
        model.addAttribute("searchFilter", FilterSearchDto())
        return "offersList.html"
    }

}

