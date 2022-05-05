package el.ka.photomanger

import android.icu.number.NumberFormatter.with
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import el.ka.photomanger.models.PhotoFile
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var photoManager: PhotoManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photoManager = PhotoManager(this)
        val photos = photoManager.getAllPPhotos()
        testShowPhoto(photos[0])
        Log.d("PHOTOS", "Size: ${photos.size}")
    }

    private fun testShowPhoto(photoFile: PhotoFile) {
        val uri = Uri.fromFile(File(photoFile.path))
        Picasso.get()
            .load(uri)
            .into(imageView)
    }
}

