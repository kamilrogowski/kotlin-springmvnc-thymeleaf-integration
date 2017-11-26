package recruitment.web.authorization

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.HashSet
import org.springframework.security.core.userdetails.UsernameNotFoundException
import recruitment.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import recruitment.model.User


@Transactional
@Service
class SSUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        try {
            val user = userRepository.findByLoginAndIsActiveTrue(username) ?: return null
            return org.springframework.security.core.userdetails.User(user!!.login, user!!.password, getAuthorities(user))
        } catch (e: Exception) {
            throw UsernameNotFoundException("User not found")
        }
    }

    private fun getAuthorities(user : User): Set<GrantedAuthority> {
        val authorities = HashSet<GrantedAuthority>()
        for (role in user.roles) {
            val grantedAuthority = SimpleGrantedAuthority(role.role)
            authorities.add(grantedAuthority)
        }
        return authorities
    }



}