package recruitment.model

import org.hibernate.validator.constraints.NotEmpty
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
open class Company(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1,
        @NotEmpty
        @Size(min=1, max=150)
        var companyName: String = "",
        @Size(min=1, max=150)
        var city: String = "",
        @Size(min=1, max=150)
        var email: String = "",
        @Size(min=1, max=150)
        var name: String  = "",
        @Size(min=1, max=150)
        var surname: String = ""

)
