package developer.abdusamid.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import developer.abdusamid.room.entity.Category
import developer.abdusamid.room.entity.CategoryNews

@Dao
interface CategoryDAO {
    @Transaction
    @Query("select * from category")
    fun getCategoryByNews(): List<CategoryNews>

    @Query("select * from category")
    fun getAllCategories(): List<Category>

    @Insert
    fun addCategory(category: Category)

    @Insert
    fun addAllCategory(vararg category: Category)
}