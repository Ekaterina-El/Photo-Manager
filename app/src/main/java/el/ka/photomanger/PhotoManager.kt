package el.ka.photomanger

import android.app.Activity
import android.provider.MediaStore
import el.ka.photomanger.models.PhotoFile

class PhotoManager(
    private val activity: Activity
) {
    fun getAllPPhotos(): MutableList<PhotoFile> {
        val imagesList = mutableListOf<PhotoFile>()

        val uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val args = arrayOf(
            MediaStore.MediaColumns.DATA,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        )
        val cursor = activity.contentResolver.query(uri, args, null, null, null)

        if (cursor != null) {
            var col_idx_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            var col_idx_folder_name =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

            while (cursor.moveToNext()) {
                val path = cursor.getString(col_idx_data)
                val folder = cursor.getString(col_idx_folder_name)
                imagesList.add(PhotoFile(
                    path, folder
                ))
            }
        }
        return imagesList
    }
}