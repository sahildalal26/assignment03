import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class GameViewModel : ViewModel() {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference.child("games")
    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>> get() = _games

    init {
        fetchGames()
    }

    private fun fetchGames() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val gamesList = mutableListOf<Game>()
                for (gameSnapshot in snapshot.children) {
                    val game = gameSnapshot.getValue(Game::class.java)
                    if (game != null) {
                        gamesList.add(game)
                    }
                }
                _games.value = gamesList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("GameViewModel", "Failed to read games.", error.toException())
            }
        })
    }

    fun addGame(game: Game) {
        database.push().setValue(game)
    }

    fun updateGame(gameId: String, game: Game) {
        database.child(gameId).setValue(game)
    }

    fun deleteGame(gameId: String) {
        database.child(gameId).removeValue()
    }
}
