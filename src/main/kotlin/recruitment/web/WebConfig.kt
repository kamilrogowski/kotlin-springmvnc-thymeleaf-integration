package recruitment.web

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.PageRequest
import org.springframework.data.web.PageableArgumentResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.web.method.support.HandlerMethodArgumentResolver



@Configuration
@ComponentScan(basePackages = arrayOf("recruitment.*"))
open class WebConfig : WebMvcConfigurerAdapter() {

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>?) {
        val resolver = PageableHandlerMethodArgumentResolver()
        resolver.setPageParameterName("page.page")
        resolver.setSizeParameterName("page.size")
        resolver.setOneIndexedParameters(true)
        resolver.setFallbackPageable(PageRequest(0, 15))
        argumentResolvers!!.add(resolver)
        super.addArgumentResolvers(argumentResolvers)
    }
}