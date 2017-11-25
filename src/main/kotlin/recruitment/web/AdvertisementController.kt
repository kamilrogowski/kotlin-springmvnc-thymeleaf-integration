package recruitment.web

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import recruitment.model.Advertisement
import java.text.SimpleDateFormat
import org.springframework.beans.propertyeditors.CustomDateEditor
import org.springframework.data.domain.Pageable
import org.springframework.ui.Model
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import recruitment.dto.FilterSearchDto
import recruitment.model.Application
import recruitment.model.Attachment
import recruitment.model.ObservedOffers
import recruitment.repository.*
import recruitment.web.authorization.LoggedUser
import recruitment.web.wrappers.PageWrapper
import java.util.*
import com.sun.xml.internal.ws.streaming.XMLStreamWriterUtil.getOutputStream
import org.apache.catalina.manager.StatusTransformer.setContentType
import javax.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.GetMapping
import java.io.*
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_PDF
import java.io.IOException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMapping


@Controller
class AdvertisementController(private val advertisementRepository: AdvertisementRepository,
                              private val observedOffersRepository: ObservedOffersRepository,
                              private val usersRepository: UserRepository,
                              private val roleRepository: RoleRepository,
                              private val applicationRepository: ApplicationRepository) {

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
        advertisement.userOwner = usersRepository.findByLogin(LoggedUser.currentlyLoggedUser.username)
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

    @GetMapping("/advertisement/subscribe/details/{id}")
    fun showDetailsOfJobOffer(@PathVariable("id") id: String, model: Model): String {
        model.addAttribute("advertisement", false)
        val advertisement = advertisementRepository.findById(id.toLong()).get()
        model.addAttribute("advertisement", advertisement)
        return "advertisementDetails.html"
    }

    @PostMapping("/advertisement/subscribe/{id}")
    fun subscribeJobOffer(@PathVariable("id") id: String, model: Model, redirectAttributes: RedirectAttributes): String {
        val currentlyLoggedUser = LoggedUser.currentlyLoggedUser
        val userFound = usersRepository.findByLogin(currentlyLoggedUser.username)
        val advFound = advertisementRepository.findById(id.toLong()).get()

        val observedOffer = ObservedOffers()
        observedOffer.advertisement = advFound
        observedOffer.user = userFound!!
        userFound.observeOffers.add(observedOffer)
        advFound.userObserves.add(observedOffer)
//        usersRepository.save(userFound)
//        advertisementRepository.save(advFound)
        observedOffersRepository.save(observedOffer)
        redirectAttributes.addFlashAttribute("success", "Job offer has been added to observed")
        return "redirect:/advertisements"
    }

    @PostMapping("/advertisement/apply/{id}")
    fun applyFoAndJob(@RequestParam("file") file: MultipartFile, @PathVariable("id") id: String, model: Model, redirectAttributes: RedirectAttributes): String {
        val currentlyLoggedUser = LoggedUser.currentlyLoggedUser
        val user = usersRepository.findByLoginAndIsActiveTrue(currentlyLoggedUser.username)
        val adv = advertisementRepository.findById(id.toLong()).get()
        val application = Application()
        application.advertisement = adv
        application.user = user!!
        val attachement = Attachment()
        attachement.userAttachment = file.bytes
        attachement.contentType = file.contentType
        attachement.name = file.originalFilename
        application.attachment = attachement

        adv.usersApplied.add(application)
        user.applicatons.add(application)
        applicationRepository.save(application)

        redirectAttributes.addFlashAttribute("success", "Operation completed")
        return "redirect:/advertisements"
    }


    @PostMapping("/advertisements/search")
    fun search(@ModelAttribute("searchFilter") searchFilter: FilterSearchDto, model: ModelMap, pageable: Pageable): String {
        val pages: PageWrapper<Advertisement>
        val searchFilterTitleQuery = "%" + searchFilter.title + "%"
        val searchFilterCityQuery = "%" + searchFilter.city + "%"

        pages = when {
            searchFilter.title.isNotEmpty() -> PageWrapper<Advertisement>(advertisementRepository.findByTitleLikeAndIsActiveTrue(searchFilterTitleQuery,
                    pageable), "/advertisements")
            searchFilter.city.isNotEmpty() -> PageWrapper<Advertisement>(advertisementRepository.findByCompany_CityLikeAndIsActiveTrue(
                    searchFilterCityQuery, pageable), "/advertisements")
            searchFilter.city.isNotEmpty() and searchFilter.title.isNotEmpty() -> PageWrapper<Advertisement>(advertisementRepository.findByCompany_CityLikeAndTitleLikeAndIsActiveTrue(
                    searchFilterCityQuery, searchFilterTitleQuery, pageable), "/advertisements")
            else -> PageWrapper<Advertisement>(advertisementRepository.findByIsActiveTrue(pageable), "/advertisements")
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


    @GetMapping("/my_advertisements/{id}")
    fun fetchMyAdvertisements(@PathVariable("id") id: String, model: Model, redirectAttributes: RedirectAttributes): String {
        val application = applicationRepository.findByAdvertisement_id(id.toLong())
        application?.let {
            model.addAttribute("advertisement", application.advertisement.usersApplied.map {
                it.user})
            model.addAttribute("advID", id.toLong())
        } ?: run {
            redirectAttributes.addFlashAttribute("error", "No one has applied for this offer yet")
            return "redirect:/my_advertisements"
        }
        return "users_applied.html"
    }

    @GetMapping("/my_advertisements/cv/{userID}/{advId}")
    fun downloadCVFile(@PathVariable("userID") userID: String,@PathVariable("advId") advId: String, model: Model, redirectAttributes: RedirectAttributes): HttpEntity<ByteArray> {
        val document = applicationRepository.findByAdvertisement_idAndUser_id(advId.toLong(),userID.toLong())
        document?.let {
            val header = HttpHeaders()
            header.contentType = MediaType.APPLICATION_PDF
            header.set(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=" + document.attachment.name)

            header.contentLength =  document.attachment.userAttachment.size.toLong()

            return HttpEntity(document.attachment.userAttachment, header)
        }

        return HttpEntity(document!!.attachment.userAttachment)

//        return "users_applied.html"
    }


}

