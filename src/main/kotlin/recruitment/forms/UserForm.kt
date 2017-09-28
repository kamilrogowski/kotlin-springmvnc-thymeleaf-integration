package recruitment.forms

import org.hibernate.validator.constraints.Email
import recruitment.model.Role
import recruitment.model.UserDetails
import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * Created by pcrogowski on 2017-09-28.
 */
data class UserForm(
        val id: Long = -1,
        val login: String  = "",
        val password: String = "",
        val email : String = "",
        val active : Boolean = true,
        val name: String  = "",
        val surname: String = "",
        val age : Int = 0,
        val gender : Char = ' ',
        val phone: String = "")


