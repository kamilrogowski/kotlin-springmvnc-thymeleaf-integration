package recruitment.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import recruitment.model.Advertisement
import recruitment.model.Application
import java.util.*

interface ApplicationRepository : JpaRepository<Application, Long> {

    fun findByUser_id(id: Long?): Iterable<Application>

    fun findByAdvertisement_id(id: Long?): Application?

    fun findByAdvertisement(id: Long?): Application?

}