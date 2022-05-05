package el.ka.photomanger

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import el.ka.photomanger.adapter.CategoriesAdapter
import el.ka.photomanger.adapter.ListListener
import el.ka.photomanger.adapter.PhotosAdapter
import el.ka.photomanger.common.APP_ACTIVITY
import el.ka.photomanger.common.PhotoManager
import el.ka.photomanger.models.PhotoFile
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        APP_ACTIVITY = this
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var photoManager: PhotoManager? = null
        fun getPhotoManager(activity: AppCompatActivity): PhotoManager {
            if (photoManager == null) {
                photoManager = PhotoManager(activity)
            }
            return photoManager!!
        }
    }


}