package hello

import hello.model.Customer
import hello.repository.CustomerRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller("/")
class CustomerController(private val repository: CustomerRepository) {

		@GetMapping("/")
		fun welcome() = "index.html"

		@GetMapping("/all")
		fun findByLastName() = repository.findAll()

	}

