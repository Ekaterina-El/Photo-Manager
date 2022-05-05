package el.ka.photomanger.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import el.ka.photomanger.MainActivity
import el.ka.photomanger.R
import el.ka.photomanger.adapter.ListListener
import el.ka.photomanger.adapter.PhotosAdapter
import el.ka.photomanger.common.APP_ACTIVITY
import el.ka.photomanger.common.loadImage
import el.ka.photomanger.models.PhotoFile
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detail_photo_fragment.*
import kotlinx.android.synthetic.main.photos_fragment.*

class DetailPhotoFragment : Fragment(R.layout.detail_photo_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showPhoto()
    }

    private fun showPhoto() {
        val photo = arguments?.getSerializable("photoInfo") as PhotoFile
        loadImage(photo.path, this.detail_photo)
    }
}