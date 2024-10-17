// GameViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    private val gameDao: GameDao = GameDatabase.getDatabase(ApplicationProvider.getApplicationContext()).gameDao()

    fun insertGame(game: Game) {
        viewModelScope.launch {
            gameDao.insert(game)
        }
    }

    suspend fun getAllGames(): List<Game> {
        return gameDao.getAllGames()
    }

    fun updateGame(game: Game) {
        viewModelScope.launch {  }
    }

}
