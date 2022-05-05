package el.ka.photomanger

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import el.ka.photomanger.models.PhotoFile
import java.io.File

class PhotosAdapter(
    private val context: Context,
    private val photos: MutableList<PhotoFile>
) :
    RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.photo_grid_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val file = photos[position]
        val uri = Uri.fromFile(File(file.path))
        Glide.with(context).load(uri).placeholder(R.drawable.ic_launcher_foreground).centerCrop()
            .into(holder.itemView as ImageView)
//        holder.itemView.imageView.loadFromSrc(uri)
    }

}

class PA(
    private val context: Context,
    private val photos: MutableList<PhotoFile>
) : BaseAdapter() {
    override fun getCount(): Int = photos.size
    override fun getItem(position: Int) = position
    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = if (convertView == null) {
            val picturesView = ImageView(context)
            picturesView.scaleType = ImageView.ScaleType.FIT_CENTER
            return picturesView
//            picturesView.layoutParams = GridView.LayoutParams(270, 270)
        } else {
            convertView as ImageView
        }

        val file = photos[position]
        val uri = Uri.fromFile(File(file.path))
        Glide.with(context).load(uri).placeholder(R.drawable.ic_launcher_foreground).centerCrop()
            .into(view)

        return view
    }

}

