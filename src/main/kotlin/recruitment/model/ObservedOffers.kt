package recruitment.model

import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.*

@Entity
open class ObservedOffers(

        @field:EmbeddedId
        var id : ObserveUserId = ObserveUserId(),

        @field:ManyToOne(fetch = FetchType.LAZY)
        @field:MapsId("userId")
        var user: User = User(),

        @field:ManyToOne(fetch = FetchType.LAZY)
        @field:MapsId("advertisementId")
        var advertisement : Advertisement = Advertisement(),

        val isObserved: Boolean = false,
        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        var dateStart: Date? = Date()

)
