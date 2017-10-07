package recruitment.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Company(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1,
        val companyName: String = "",
        val streetName: String = "",
        val streetNumber: String = "",
        @NotNull
        @OneToOne(cascade = arrayOf(CascadeType.ALL))
        val contactPerson: User = User(),
        val city: String = "",
        val email: String = ""
)
