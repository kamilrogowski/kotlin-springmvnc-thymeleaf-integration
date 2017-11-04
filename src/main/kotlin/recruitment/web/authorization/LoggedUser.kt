package recruitment.web.authorization

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User

object LoggedUser {

    /**
     * Metoda zwracająca aktualnie zalogowanego użytkownika.
     *
     * @return User Aktualnie zalogowany użytkownik
     */
    val currentlyLoggedUser: User
        get() {
            val auth = SecurityContextHolder.getContext().authentication
            return auth.principal as User
        }
}
