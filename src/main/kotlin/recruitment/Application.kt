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
open class Application {

    private val log = LoggerFactory.getLogger(Application::class.java)

    @Bean
    open fun init(repository: RoleRepository) = CommandLineRunner {
        // save a couple of customers
        val role: Role = Role()
        role.roleName = "USER"
        role.role = "ROLE_USER"

        val role2 = Role()
        role2.roleName = "RECRUITER"
        role2.role = "ROLE_RECRUITER"

        val role3 = Role()
        role3.role = "ROLE_ADMIN"
        role3.roleName = "ADMIN"
        repository.save(role)
        repository.save(role2)
        repository.save(role3)
        repository.findAll().forEach { role -> log.info(role.roleName) }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
