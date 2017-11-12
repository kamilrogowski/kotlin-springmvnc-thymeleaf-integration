package recruitment.model

import org.springframework.format.annotation.DateTimeFormat
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
open class Application(

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1,

        @field:ManyToOne(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
        @field:JoinColumn(name = "USER_ID")
        var user: User = User(),

        @field:ManyToOne(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
        @field:JoinColumn(name = "ADVERTISEMENT_ID")
        var advertisement : Advertisement = Advertisement(),

        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        var applyDate: Date? = Date()
)  : Serializable {constructor() : this(0)}