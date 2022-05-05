package el.ka.photomanger.common

import android.app.Activity
import android.provider.MediaStore
import el.ka.photomanger.models.PhotoFile

class PhotoManager(
    private val activity: Activity
) {
    private val photos = mutableListOf<PhotoFile>()
//    Если данные уже получались не обязательно их вновь получать, без пренудильного обновления

    fun getAllPPhotos(update: Boolean = false): MutableList<PhotoFile> {
        if (photos.size == 0 || update) {
            photos.clear()

            val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val args = arrayOf(
                MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            )
            val cursor = activity.contentResolver.query(uri, args, null, null, null)

            if (cursor != null) {
                val colIdxData = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
                val colIdxFolderName =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

                while (cursor.moveToNext()) {
                    val path = cursor.getString(colIdxData)
                    val folder = cursor.getString(colIdxFolderName)
                    photos.add(
                        PhotoFile(
                            path, folder
                        )
                    )
                }
            }
        }

        return photos
    }

    fun getCategories(update: Boolean = false): Set<String> {
        val photos = getAllPPhotos(update)
        val categories = mutableSetOf<String>()
        photos.forEach {
            categories.add(it.category)
        }
        return categories.toSet()
    }

    fun getPhotosByCategory(category: String, update: Boolean = false): List<PhotoFile> {
        val photos = getAllPPhotos(update)
        return photos.filter { it.category == category }
    }
}