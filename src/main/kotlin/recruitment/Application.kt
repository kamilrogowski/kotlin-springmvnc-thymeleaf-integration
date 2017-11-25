package recruitment

import recruitment.model.Customer
import recruitment.repository.CustomerRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import recruitment.model.Role
import recruitment.repository.RoleRepository

@SpringBootApplication
@EnableScheduling
@EnableAsync
open class Application{

	private val log = LoggerFactory.getLogger(Application::class.java)

	@Bean
	open fun init(repository: RoleRepository) = CommandLineRunner {
			// save a couple of customers
            repository.save(Role("ROLE_USER"))
      		repository.save(Role("ROLE_RECRUITER"))
       		repository.save(Role("ROLE_ADMIN"))
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
