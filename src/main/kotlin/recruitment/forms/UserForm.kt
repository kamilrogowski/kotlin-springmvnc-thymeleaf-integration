package recruitment.forms

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty
import recruitment.model.Role
import recruitment.model.UserDetails
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Created by pcrogowski on 2017-09-28.
 */
data class UserForm(
        @NotEmpty
        @Size(min=2, max=30)
        val login: String  = "",
        @NotEmpty
        @Size(min=2, max=30)
        val password: String = "",
        val email : String = "",
        val active : Boolean = true,
        val name: String  = "",
        val surname: String = "",
        val age : Int = 0,
        val gender : Char = ' ',
        val phone: String = "")


