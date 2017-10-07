package recruitment.repository

import org.springframework.data.jpa.repository.JpaRepository
import recruitment.model.Advertisement

interface AdvertisementRepository : JpaRepository<Advertisement, Long>

