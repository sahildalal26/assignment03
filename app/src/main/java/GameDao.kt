import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDao {
    @Insert
    suspend fun insert(game: Game)

    @Query("SELECT * FROM game")
    suspend fun getAllGames(): List<Game>
}
