package recruitment.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull

/**
 * Created by Kamil on 9/22/2017.
 */
@Entity
class UserDetails(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1,
        @NotNull
        var name: String  = "",
        @NotNull
        var surname: String = "",
        @NotNull
        var age : Int = -1,
        @NotNull
        var gender : Char = ' ',
        @NotNull
        var phone: String = "") {
        override fun toString(): String {
                return "UserDetails(id=$id, name='$name', surname='$surname', age=$age, gender=$gender, phone='$phone')"
        }
}
