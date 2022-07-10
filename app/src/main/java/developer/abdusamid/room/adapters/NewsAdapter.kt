package developer.abdusamid.room.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.abdusamid.room.databinding.ItemRvBinding
import developer.abdusamid.room.entity.News

class NewsAdapter(private var list: List<News>, var rvItemClick: RvItemClick) :
    RecyclerView.Adapter<NewsAdapter.VH>() {
    inner class VH(private var itemRvBinding: ItemRvBinding) :
        RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(news: News, position: Int) {
            itemRvBinding.txt1.text = news.title
            itemRvBinding.txt2.text = news.description
            itemRvBinding.btnDelete.setOnClickListener {
                rvItemClick.itemDelete(news, position)
            }
            itemRvBinding.btnEdit.setOnClickListener {
                rvItemClick.itemEdit(news, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
    interface RvItemClick {
        fun itemDelete(news: News, position: Int)
        fun itemEdit(news: News, position: Int)
    }
}