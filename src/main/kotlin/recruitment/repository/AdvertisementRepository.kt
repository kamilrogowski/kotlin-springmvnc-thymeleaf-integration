package recruitment.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import recruitment.model.Advertisement
import recruitment.model.Customer
import java.util.*

interface AdvertisementRepository : JpaRepository<Advertisement, Long> {

    fun findByEndDateBefore(date: Date): Iterable<Advertisement>
    fun findByIsActiveTrue(pageable: Pageable): Page<Advertisement>
    fun findByTitleLike(title: String, pageable: Pageable): Page<Advertisement>
    fun findByCompany_CityLike(company: String, pageable: Pageable): Page<Advertisement>

    fun findByTitleLikeAndIsActiveTrue(title: String, pageable: Pageable): Page<Advertisement>
    fun findByCompany_CityLikeAndIsActiveTrue(company: String, pageable: Pageable): Page<Advertisement>
    fun findByCompany_CityLikeAndTitleLikeAndIsActiveTrue(company: String, title: String, pageable: Pageable): Page<Advertisement>

    fun findByUserOwner_id(userId : Long): Iterable<Advertisement>
}

