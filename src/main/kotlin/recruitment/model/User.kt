package recruitment.model

import org.hibernate.annotations.Cascade
import org.hibernate.validator.constraints.Email
import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * Created by Kamil on 9/22/2017.
 */
@Entity
open class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1,
        @Column(unique = true)
        @NotNull
        var login: String = "",
        @NotNull
        var password: String = "",
        @Email
        var email: String = "",
        var isActive: Boolean = true,
        @NotNull
        @OneToOne(cascade = arrayOf(CascadeType.ALL))
        var userDetails: UserDetails = UserDetails(),

        @ManyToMany(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
        @JoinTable(name = "USER_ROLE",
                joinColumns = arrayOf(JoinColumn(name = "USER_ID", updatable = false, nullable = false)),
                inverseJoinColumns = arrayOf(JoinColumn(name = "ROLE_ID", nullable = false, updatable = false)))

        val roles: MutableSet<Role> = mutableSetOf(),
        @field:OneToMany(fetch = FetchType.EAGER,
                mappedBy = "user",
                cascade = arrayOf(CascadeType.ALL))
        var observeOffers: MutableSet<ObservedOffers> = mutableSetOf(),

        @field:OneToMany(fetch = FetchType.EAGER,
                mappedBy = "user",
                cascade = arrayOf(CascadeType.ALL))
        var applicatons: MutableSet<Application> = mutableSetOf()


) {
    override fun toString(): String {
        return "User(id=$id, login='$login', password='$password', email='$email', active=$isActive, userDetails=$userDetails, roles=$roles)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
