package developer.abdusamid.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import developer.abdusamid.room.dao.CategoryDAO
import developer.abdusamid.room.dao.NewsDAO
import developer.abdusamid.room.entity.Category
import developer.abdusamid.room.entity.News

@Database(entities = [News::class, Category::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun newDao(): NewsDAO
    abstract fun categoryDao(): CategoryDAO

    companion object {
        private var instance: AppDataBase? = null

        @Synchronized
        fun getInstance(context: Context): AppDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDataBase::class.java, "news_db")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
            return instance!!
        }
    }
}