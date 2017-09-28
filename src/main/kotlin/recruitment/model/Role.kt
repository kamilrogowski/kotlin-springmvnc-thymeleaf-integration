package recruitment.model

import javax.persistence.*

/**
 * Created by Kamil on 9/22/2017.
 */


@Entity
class Role(
        val roleName: String,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1,
        @ManyToMany(mappedBy = "roles")
        var students: List<User> = mutableListOf<User>()
)
