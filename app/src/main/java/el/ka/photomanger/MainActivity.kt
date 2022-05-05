package el.ka.photomanger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import el.ka.photomanger.models.PhotoFile
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var photoManager: PhotoManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photoManager = PhotoManager(this)
        val photos = photoManager.getAllPPhotos()
        showPhotos(photos)
    }

    private fun showPhotos(photos: MutableList<PhotoFile>) {
        this.photosGrid.adapter = PhotosAdapter(this, photos)
    }


}


/*

<GridView
android:id="@+id/photosGrid"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:horizontalSpacing="2dp"
android:numColumns="4"
android:padding="2dp"
android:verticalSpacing="2dp"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toTopOf="parent"
tools:listitem="@layout/photo_grid_item" />*/
