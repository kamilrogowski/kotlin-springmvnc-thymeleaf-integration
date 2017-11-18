package recruitment.model

import org.hibernate.validator.constraints.NotEmpty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.*

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import javax.xml.bind.DatatypeConverter
import kotlin.jvm.Transient


@Entity
open class Advertisement(

        @field:Id @field:GeneratedValue(strategy = GenerationType.AUTO)
        @field:Column(name="ADVERTISEMENT_ID")
        var id: Long = -1,
        @NotEmpty
        @Size(min = 4, max = 30)
        var title: String = "",
        @NotEmpty
        @Size(min = 50, max = 1000)
        var description: String = "",
        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        var startDate: Date? = Date(),
        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        var endDate: Date? = Date(),
        @Size(min = 4, max = 30)
        @field:Lob
        var companyImage: ByteArray = ByteArray(128000000),
        @OneToOne(cascade = arrayOf(CascadeType.ALL))
        var company: Company = Company(),
        @field:Transient
        var imageConverted: String = "",
        var visitCounter: Int = 0,
        @NotNull
        @OneToOne(cascade = arrayOf(CascadeType.ALL))
        var userOwner: User = User(),

        var isActive : Boolean  = true,

        @field:OneToMany(
                mappedBy = "advertisement",
                cascade = arrayOf(CascadeType.ALL)
        )

        var userObserves: MutableSet<ObservedOffers> = mutableSetOf(),

        @field:OneToMany(
                mappedBy = "advertisement",
                cascade = arrayOf(CascadeType.ALL)
        )
        var usersApplied: MutableSet<Application> = mutableSetOf()){


    fun toStreamingURI(): String {
        //We need to encode the byte array into a base64 String for the browser
        val base64 = DatatypeConverter.printBase64Binary(companyImage)

        //Now just return a data string. The Browser will know what to do with it
        return "data:img/png;base64,$base64"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Advertisement

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}



