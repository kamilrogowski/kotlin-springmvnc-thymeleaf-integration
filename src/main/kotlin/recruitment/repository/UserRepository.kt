package recruitment.repository

import org.springframework.data.jpa.repository.JpaRepository
import recruitment.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Created by Kamil on 9/23/2017.
 */
@Repository
interface UserRepository  : JpaRepository<User, Long> {

    fun existsByEmail(email: String): Boolean
    fun existsByLogin(username: String): Boolean
    fun existsByLoginAndPassword(login: String, password: String): Boolean

}