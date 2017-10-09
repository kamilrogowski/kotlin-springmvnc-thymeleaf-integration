package recruitment.model

import org.hibernate.validator.constraints.NotEmpty
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
open class Company(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1,
        @get:NotEmpty
        @get:Size(min=1, max=150)
        var companyName: String = "",
        @get:Size(min=1, max=150)
        var city: String = "",
        @get:Size(min=1, max=150)
        var email: String = "",
        @get:Size(min=1, max=150)
        var name: String  = "",
        @get:Size(min=1, max=150)
        var surname: String = ""


)
