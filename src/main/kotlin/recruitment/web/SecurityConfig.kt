package recruitment.web

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.PageRequest
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.authorization.AuthenticatedAuthorizationManager.authenticated
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import recruitment.web.authorization.SSUserDetailsService
import org.springframework.security.core.userdetails.UserDetailsService
import recruitment.repository.UserRepository
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
@ComponentScan(basePackages = arrayOf("recruitment.*"))
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var userRepository: UserRepository

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/", "/advertisements", "/join", "/css/**", "bootstrap/**", "/bootstrap/css/**").permitAll().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
        http.csrf().disable()
    }


    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsServiceBean())
    }


    @Throws(Exception::class)
    override fun userDetailsServiceBean(): UserDetailsService {
        return SSUserDetailsService(userRepository)
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsServiceBean())
        auth.authenticationProvider(authProvider())
    }

    @Bean
    fun authProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsServiceBean())
        authProvider.setPasswordEncoder(BCryptPasswordEncoder())
        return authProvider
    }

}