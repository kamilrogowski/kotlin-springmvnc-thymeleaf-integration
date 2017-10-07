package recruitment

import recruitment.model.Customer
import recruitment.repository.CustomerRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import recruitment.model.Role
import recruitment.repository.RoleRepository

@SpringBootApplication
open class Application{

	private val log = LoggerFactory.getLogger(Application::class.java)

	@Bean
	fun init(repository: RoleRepository) = CommandLineRunner {
			// save a couple of customers
            repository.save(Role("Guest"))
      		repository.save(Role("Recruiter"))
       		repository.save(Role("Manager"))
//			repository.save(Customer("Jack", "Bauer"))
//			repository.save(Customer("Chloe", "O'Brian"))
//			repository.save(Customer("Kim", "Bauer"))
//			repository.save(Customer("David", "Palmer"))
//			repository.save(Customer("Michelle", "Dessler"))
//
//			// fetch all customers
//			log.info("Customers found with findAll():")
//			log.info("-------------------------------")
            repository.findAll().forEach { role -> log.info(role.roleName) }
//			log.info("")
//
//			// fetch an individual customer by ID
//			val customer = repository.findById(1L)
//			customer.ifPresent {
//				log.info("Customer found with findOne(1L):")
//				log.info("--------------------------------")
//				log.info(it.toString())
//				log.info("")
//			}
//
//			// fetch customers by last name
//			log.info("Customer found with findByLastName('Bauer'):")
//			log.info("--------------------------------------------")
//			for (bauer in repository.findByLastName("Bauer")) {
//				log.info(bauer.toString())
//			}
	}

}

fun main(args: Array<String>) {
	SpringApplication.run(Application::class.java, *args)
}
