package el.ka.photomanger.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import el.ka.photomanger.MainActivity
import el.ka.photomanger.R
import el.ka.photomanger.adapter.CategoriesAdapter
import el.ka.photomanger.adapter.ListListener
import el.ka.photomanger.common.APP_ACTIVITY
import kotlinx.android.synthetic.main.categories_fragment.*

class CategoriesFragment : Fragment(R.layout.categories_fragment), ListListener {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categories = MainActivity.getPhotoManager(APP_ACTIVITY).getCategories()
        showCategories(categories)
    }

    private fun showCategories(categories: Set<String>) {
        val adapter = CategoriesAdapter(categories.toList())
        adapter.setListListener(this)
        this.categoriesGrid.adapter = adapter
    }

    override fun onClickListener(obj: Any) {
        Toast.makeText(APP_ACTIVITY, "Move to $obj", Toast.LENGTH_SHORT).show()
        val bundle = bundleOf("category" to obj)
        APP_ACTIVITY.navController.navigate(R.id.action_categoriesFragment_to_photosFragment, bundle)
    }

}