package developer.abdusamid.room.dao

import androidx.room.*
import developer.abdusamid.room.entity.News

@Dao
interface NewsDAO {
    @Query("select * from news")
    fun getAllNews(): List<News>

    @Insert
    fun addNews(news: News)

    @Delete
    fun deleteNews(news: News)

    @Update
    fun updateNews(news: News)

    @Query("select * from news where id=:id")
    fun getNewsById(id: Int): News

    @Query("select * from news where news_title=:title and description=:description")
    fun getNewsById(title: String, description: String): Int

    @Insert
    fun addAllNews(vararg news: News)
}