package recruitment.model

import org.hibernate.validator.constraints.NotEmpty
import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import javax.xml.bind.DatatypeConverter

@Entity
open class Attachment(

        @field:Id @field:GeneratedValue(strategy = GenerationType.AUTO)
        @field:Column()
        var id: Long = -1,
        @field:Lob
        var userAttachment: ByteArray = ByteArray(128000000),
        var name : String = "",
        var contentType: String = "")
