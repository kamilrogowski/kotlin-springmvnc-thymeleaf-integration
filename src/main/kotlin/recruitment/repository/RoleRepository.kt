package recruitment.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import recruitment.model.Role
import recruitment.model.User

/**
 * Created by pcrogowski on 2017-09-29.
 */
@Repository
interface RoleRepository  : JpaRepository<Role, Long>

