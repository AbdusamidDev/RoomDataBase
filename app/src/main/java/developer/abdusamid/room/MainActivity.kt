package developer.abdusamid.room

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import developer.abdusamid.room.adapters.CategoryAdapter
import developer.abdusamid.room.adapters.NewsAdapter
import developer.abdusamid.room.database.AppDataBase
import developer.abdusamid.room.databinding.ActivityMainBinding
import developer.abdusamid.room.databinding.ItemDialogBinding
import developer.abdusamid.room.entity.Category
import developer.abdusamid.room.entity.News

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var appDatabase: AppDataBase
    lateinit var list: ArrayList<News>
    lateinit var newsAdapter: NewsAdapter
    private lateinit var categoryList: ArrayList<Category>
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDatabase = AppDataBase.getInstance(this)

        val category1 = Category("Global")
        val category2 = Category("Technology")
        val category3 = Category("Related")

        appDatabase.categoryDao().addAllCategory(category1, category2, category3)

        categoryList = ArrayList()
        categoryList.addAll(appDatabase.categoryDao().getAllCategories())

        categoryAdapter = CategoryAdapter(categoryList)
        binding.spinner.adapter = categoryAdapter


        list = ArrayList()

        list.addAll(appDatabase.newDao().getAllNews())

        newsAdapter = NewsAdapter(list, object : NewsAdapter.RvItemClick {
            override fun itemDelete(news: News, position: Int) {

                appDatabase.newDao().deleteNews(news)
                list.remove(news)
                newsAdapter.notifyItemRemoved(position)
                newsAdapter.notifyItemRangeChanged(position, list.size)
            }

            override fun itemEdit(news: News, position: Int) {
                val dialog = AlertDialog.Builder(this@MainActivity)
                val dialogBinding = ItemDialogBinding.inflate(layoutInflater)
                dialog.setView(dialogBinding.root)
                dialogBinding.edit1.setText(news.title)
                dialogBinding.edit2.setText(news.description)

                dialog.setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val title1 = dialogBinding.edit1.text.toString()
                        val desc1 = dialogBinding.edit2.text.toString()

                        news.title = title1
                        news.description = desc1
                        appDatabase.newDao().updateNews(news)
                        newsAdapter.notifyItemChanged(position)
                    }
                })
                dialog.show()
            }
        })

        binding.rv.adapter = newsAdapter

        binding.btnAdd.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val description = binding.edtDescription.text.toString()

            val news = News()
            news.title = title
            news.description = description

            val selectedItemPosition = binding.spinner.selectedItemPosition
            val category = categoryList[selectedItemPosition]
            news.category = category.id

            appDatabase.newDao().addNews(news)

            val id = appDatabase.newDao().getNewsById(title, description)
            news.id = id

            list.add(news)
            newsAdapter.notifyItemInserted(list.size)
        }
    }
}