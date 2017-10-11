package recruitment.model

import org.hibernate.validator.constraints.NotEmpty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.*

import javax.persistence.*
import javax.validation.constraints.Size


@Entity
open class Advertisement(

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1,
        @NotEmpty
        @Size(min=4, max=30)
        var title: String = "",
        @NotEmpty
        @Size(min=50, max=1000)
        var description: String = "",
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        var startDate: Date? = Date(),
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        var endDate: Date? = Date(),
        @OneToOne(cascade = arrayOf(CascadeType.ALL))
        var company: Company = Company()  )



