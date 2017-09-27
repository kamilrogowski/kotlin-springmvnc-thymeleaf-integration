package hello.repository

import hello.model.Customer
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<Customer, Long> {

	fun findByLastName(lastName: String): Iterable<Customer>
}
