import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.R

class GameAdapter(
    private val games: List<Game>,
    private val onGameClick: (Game) -> Unit
) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val ratingProgressBar: ProgressBar = itemView.findViewById(R.id.ratingProgressBar)
        val downloadsTextView: TextView = itemView.findViewById(R.id.downloadsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.titleTextView.text = game.title
        holder.ratingProgressBar.progress = game.rating.toInt() // Assuming rating is between 0 and 5
        holder.downloadsTextView.text = "${game.downloads} downloads"

        // Set click listener
        holder.itemView.setOnClickListener { onGameClick(game) }
    }

    override fun getItemCount() = games.size
}
