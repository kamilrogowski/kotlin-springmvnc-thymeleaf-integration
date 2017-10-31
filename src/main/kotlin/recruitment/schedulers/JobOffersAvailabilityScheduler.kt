package recruitment.schedulers

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import recruitment.model.Advertisement
import recruitment.repository.AdvertisementRepository
import java.text.SimpleDateFormat
import java.util.*


@Component
class JobOffersAvailabilityScheduler(private val advertisementRepository: AdvertisementRepository) {

    @Scheduled(cron = "0 0 12 * * ?")
    fun reportCurrentTime() {
        advertisementRepository.findByEndDateBefore(Date()).forEach({ jobOffer ->
            run {
                jobOffer.isActive = false
                advertisementRepository.save(jobOffer)
            }
        })
    }

}