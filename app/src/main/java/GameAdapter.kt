import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.R

class GameAdapter(
    private val games: List<Game>,
    private val onGameClick: (Game) -> Unit,
    private val onEditClick: (Game) -> Unit,
    private val onDeleteClick: (Game) -> Unit
) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
        val downloadsTextView: TextView = itemView.findViewById(R.id.downloadsTextView)
        val editIcon: ImageView = itemView.findViewById(R.id.editIcon)
        val deleteIcon: ImageView = itemView.findViewById(R.id.deleteIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.titleTextView.text = game.title
        holder.ratingBar.progress = game.rating.toInt() // Assuming rating is between 0 and 5
        holder.downloadsTextView.text = "${game.downloads} downloads"

        // Set click listener
        holder.itemView.setOnClickListener { onGameClick(game) }

        holder.editIcon.setOnClickListener { onEditClick(game) }

        holder.deleteIcon.setOnClickListener { onDeleteClick(game) }
    }

    override fun getItemCount() = games.size
}
