package el.ka.photomanger.models

import java.io.Serializable

data class PhotoFile(
    val path: String,
    val category: String,
    val date: Int
): Serializable
