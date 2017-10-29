package recruitment.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
open class ObservedOffers(

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1,
        @NotNull
        @OneToOne(cascade = arrayOf(CascadeType.ALL))
        var advertisement: Advertisement = Advertisement(),
//        @ManyToMany(mappedBy = "observedOffers")
//        var advertisements: MutableList<User> = mutableListOf(),

        @OneToOne(cascade = arrayOf(CascadeType.ALL))
        var user: User = User(),

        val isObserved: Boolean = false

)
