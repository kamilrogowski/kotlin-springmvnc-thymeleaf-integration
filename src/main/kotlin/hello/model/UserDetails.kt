package hello.model

import org.hibernate.validator.constraints.Email
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Created by Kamil on 9/22/2017.
 */
@Entity
class UserDetails(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1,
        val name: String  = "",
        val surname: String = "",
        val age : Int = -1,
        val gender : Char = ' ',
        val phone: String = "") {
        override fun toString(): String {
                return "UserDetails(id=$id, name='$name', surname='$surname', age=$age, gender=$gender, phone='$phone')"
        }
}
