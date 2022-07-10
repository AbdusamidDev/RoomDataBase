package developer.abdusamid.room.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import developer.abdusamid.room.databinding.ItemCategoryBinding
import developer.abdusamid.room.entity.Category

class CategoryAdapter(var list: List<Category>) : BaseAdapter() {
    private lateinit var categoryViewHolder: CategoryViewHolder
    override fun getCount(): Int = list.size
    override fun getItem(position: Int): Category = list[position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        categoryViewHolder = CategoryViewHolder(ItemCategoryBinding.bind(convertView))
        categoryViewHolder.itemCategoryBinding.txtTimeSpinner.text = list[position].name
        return categoryViewHolder.itemView
    }

    inner class CategoryViewHolder(var itemCategoryBinding: ItemCategoryBinding) {
        var itemView: View = itemCategoryBinding.root
    }
}