package recruitment.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import recruitment.model.Advertisement
import recruitment.model.ObservedOffers
import java.util.*

interface ObservedOffersRepository : JpaRepository<ObservedOffers, Long> {

}