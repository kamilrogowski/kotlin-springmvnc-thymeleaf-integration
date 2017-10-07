package recruitment.model

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime

import javax.persistence.*


@Entity
data class Advertisement(

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1,
        val title: String = "",
        val description: String = "",
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        val startDate: LocalDateTime = LocalDateTime.now(),
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        val endDate: LocalDateTime = LocalDateTime.now()
//        @NotNull
//        @OneToOne(cascade = arrayOf(CascadeType.ALL))
//        val companyOwner: Company = Company()
)