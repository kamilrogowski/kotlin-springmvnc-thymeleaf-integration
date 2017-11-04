package recruitment.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.PageRequest

import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.core.Ordered
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import recruitment.repository.UserRepository
import recruitment.web.authorization.SSUserDetailsService


@Configuration
@ComponentScan(basePackages = arrayOf("recruitment.*"))
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
class WebConfig : WebMvcConfigurer {

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>?) {
        val resolver = PageableHandlerMethodArgumentResolver()
        resolver.setPageParameterName("page.page")
        resolver.setSizeParameterName("page.size")
        resolver.setOneIndexedParameters(true)
        resolver.setFallbackPageable(PageRequest(0, 15))
        argumentResolvers!!.add(resolver)
        super.addArgumentResolvers(argumentResolvers)
    }

    override fun addViewControllers(registry: ViewControllerRegistry?) {
        registry!!.addViewController("/login").setViewName("login")
    }


}