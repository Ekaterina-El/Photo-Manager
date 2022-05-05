package el.ka.photomanger.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import el.ka.photomanger.MainActivity
import el.ka.photomanger.R
import el.ka.photomanger.adapter.PhotosAdapter
import el.ka.photomanger.common.APP_ACTIVITY
import el.ka.photomanger.models.PhotoFile
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.photos_fragment.*

class PhotosFragment : Fragment(R.layout.photos_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showPhotos()
    }

    private fun showPhotos() {
        val category = arguments?.getString("category", "Camera") ?: "Camera"
        val photos = MainActivity.getPhotoManager(APP_ACTIVITY).getPhotosByCategory(category)
        val adapter = PhotosAdapter(APP_ACTIVITY, photos as MutableList<PhotoFile>)
        this.photosGrid.adapter = adapter
    }
}