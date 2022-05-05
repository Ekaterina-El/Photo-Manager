package el.ka.photomanger.common

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import el.ka.photomanger.MainActivity
import el.ka.photomanger.R
import java.io.File

lateinit var APP_ACTIVITY: MainActivity

fun loadImage(path: String, view: ImageView) {
    val uri = Uri.fromFile(File(path))
    Glide.with(APP_ACTIVITY).load(uri).placeholder(R.drawable.ic_launcher_foreground).centerCrop()
        .into(view)
}