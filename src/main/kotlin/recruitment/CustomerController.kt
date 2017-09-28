package recruitment

import recruitment.repository.CustomerRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller("/")
class CustomerController(private val repository: CustomerRepository) {

		@GetMapping("/")
		fun welcome() = "index.html"

		@GetMapping("/all")
		fun findByLastName() = repository.findAll()

	}

