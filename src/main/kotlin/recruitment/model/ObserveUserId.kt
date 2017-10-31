package recruitment.model

import com.sun.xml.internal.ws.developer.Serialization
import javax.persistence.Column
import javax.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class ObserveUserId(
        @field:Column(name = "user_id")
        var userId: Long = -1,

        @field:Column(name = "advertisement_id")
        var advertisementId: Long = -1

)  : Serializable {constructor() : this(0)}
