package el.ka.photomanger.common

import android.app.Activity
import android.provider.MediaStore
import el.ka.photomanger.models.PhotoFile

class PhotoManager(
    private val activity: Activity
) {
    private val photos = mutableListOf<PhotoFile>()
//    Если данные уже получались не обязательно их вновь получать, без пренудильного обновления

    private fun getAllPPhotos(update: Boolean = false): List<PhotoFile> {
        if (photos.size == 0 || update) {
            photos.clear()

            val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val args = arrayOf(
                MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.MediaColumns.DATE_ADDED
            )
            val cursor = activity.contentResolver.query(uri, args, null, null, null)

            if (cursor != null) {
                val colIdxData = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
                val colIdxDateAdded =
                    cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_ADDED)
                val colIdxFolderName =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

                while (cursor.moveToNext()) {
                    val path = cursor.getString(colIdxData)
                    val date = cursor.getInt(colIdxDateAdded)
                    val folder = cursor.getString(colIdxFolderName)
                    photos.add(
                        PhotoFile(
                            path, folder, date
                        )
                    )
                }
            }
            cursor?.close()
        }

        return sortPhotos(photos)
    }

    private fun sortPhotos(photos: MutableList<PhotoFile>): List<PhotoFile> {
        return photos.sortedBy { it.date }.reversed()
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