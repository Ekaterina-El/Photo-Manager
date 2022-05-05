package el.ka.photomanger.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import el.ka.photomanger.MainActivity
import el.ka.photomanger.R
import el.ka.photomanger.adapter.ListListener
import el.ka.photomanger.adapter.PhotosAdapter
import el.ka.photomanger.common.APP_ACTIVITY
import el.ka.photomanger.models.PhotoFile
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.photos_fragment.*

class PhotosFragment : Fragment(R.layout.photos_fragment), ListListener {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showPhotos()
    }

    private fun showPhotos() {
        val category = arguments?.getString("category", "Camera") ?: "Camera"
        val photos = MainActivity.getPhotoManager(APP_ACTIVITY).getPhotosByCategory(category)
        val adapter = PhotosAdapter(APP_ACTIVITY, photos as MutableList<PhotoFile>)
        adapter.setListListener(this)
        this.photosGrid.adapter = adapter
    }

    override fun onClickListener(obj: Any) {
        val photo = obj as PhotoFile
        APP_ACTIVITY.navController.navigate(R.id.action_photosFragment_to_detailPhotoFragment, bundleOf("photoInfo" to photo))
    }
}