package el.ka.photomanger.common

import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadFromSrc(uri: Uri) {
    Picasso.get().load(uri).into(this)
}