package hello.model

import org.hibernate.validator.constraints.Email
import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * Created by Kamil on 9/22/2017.
 */
@Entity
class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1,
        val login: String  = "",
        val password: String = "",
        @Email
        val email : String = "",
        val active : Boolean = false,
        @NotNull
        @OneToOne
        var userDetails: UserDetails = UserDetails(),

        @ManyToMany
        @JoinTable(name = "USER_ROLE",
                joinColumns = arrayOf(JoinColumn(name = "USER_ID", updatable = false, nullable = false)),
                inverseJoinColumns = arrayOf(JoinColumn(name = "ROLE_ID",  nullable = false, updatable = false)))
        val roles : Set<Role> = setOf()
) {
        override fun toString(): String {
                return "User(id=$id, login='$login', password='$password', email='$email', active=$active, userDetails=$userDetails, roles=$roles)"
        }
}
