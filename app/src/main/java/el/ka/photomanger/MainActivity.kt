package el.ka.photomanger

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import el.ka.photomanger.adapter.CategoriesAdapter
import el.ka.photomanger.adapter.ListListener
import el.ka.photomanger.adapter.PhotosAdapter
import el.ka.photomanger.common.PhotoManager
import el.ka.photomanger.models.PhotoFile
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListListener {
    private lateinit var photoManager: PhotoManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photoManager = PhotoManager(this)
        val photos = photoManager.getAllPPhotos()
        val categories = photoManager.getCategories()
        showCategories(categories)
    }

    private fun showCategories(categories: Set<String>) {
        val adapter = CategoriesAdapter(categories.toList())
        adapter.setListListener(this)
        this.categoriesGrid.adapter = adapter

    }

    override fun onClickListener(obj: Any) {
        Toast.makeText(this, obj as String, Toast.LENGTH_SHORT).show()
    }


}