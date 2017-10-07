package recruitment.forms

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty
import org.intellij.lang.annotations.RegExp
import recruitment.model.Role
import recruitment.model.UserDetails
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Created by pcrogowski on 2017-09-28.
 */
data class UserForm(
        @get:NotEmpty
        @get:Size(min=5, max=30)
        val login: String  = "",
        @get:NotEmpty
        @get:Size(min=5, max=30)
        val password: String = "",
        @get:NotEmpty
        @get:Email
        val email : String = "",
        val active : Boolean = true,
        @get:NotEmpty
        @get:Size(min=5, max=30)
        val name: String  = "",
        @get:NotEmpty
        @get:Size(min=6, max=30)
        val surname: String = "",
        val age : Int = 0,
        val gender : Char = ' ',
        val phone: String = "",
        val role: Role = Role())


