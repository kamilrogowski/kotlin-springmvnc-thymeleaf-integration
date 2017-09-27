package hello.repository

import hello.model.Customer
import hello.model.User
import org.springframework.data.repository.CrudRepository

/**
 * Created by Kamil on 9/23/2017.
 */
interface UserRepository  : CrudRepository<User, Long> {

}