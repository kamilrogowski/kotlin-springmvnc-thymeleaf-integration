package recruitment.model

import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.*
import java.io.Serializable

@Entity
open class ObservedOffers(

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1,

        @field:ManyToOne(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
        @field:JoinColumn(name = "USER_ID")
        var user: User = User(),

        @field:ManyToOne(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
        @field:JoinColumn(name = "ADVERTISEMENT_ID")
        var advertisement : Advertisement = Advertisement(),

        val isObserved: Boolean = false,
        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        var dateStart: Date? = Date()
)  : Serializable {constructor() : this(0)}
