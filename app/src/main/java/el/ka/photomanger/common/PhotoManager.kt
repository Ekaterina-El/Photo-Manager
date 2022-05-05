package el.ka.photomanger.common

import android.app.Activity
import android.provider.MediaStore
import el.ka.photomanger.models.PhotoFile

class PhotoManager(
    private val activity: Activity
) {
//    Если данные уже получались не обязательно их вновь получать, без пренудильного обновления

    fun getAllPPhotos(): MutableList<PhotoFile> {
        val imagesList = mutableListOf<PhotoFile>()

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
                imagesList.add(PhotoFile(
                    path, folder
                ))
            }
        }
        return imagesList
    }

    fun getCategories(): Set<String> {
        val photos = getAllPPhotos()
        val categories = mutableSetOf<String>()
        photos.forEach {
            categories.add(it.category)
        }
        return categories.toSet()
    }
}