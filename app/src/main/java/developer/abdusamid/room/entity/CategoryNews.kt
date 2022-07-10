package developer.abdusamid.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryNews(
    @Embedded
    var category: Category,
    @Relation(parentColumn = "id", entityColumn = "category_id")
    var newsList: List<News>
    //To Connect to each other News and Category classes
)