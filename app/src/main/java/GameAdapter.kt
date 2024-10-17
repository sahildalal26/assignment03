import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.GameDetailActivity
import com.example.assignment3.R


class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.gameTitle)
        val descriptionTextView: TextView = itemView.findViewById(R.id.gameDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val currentGame = games[position]
        holder.titleTextView.text = currentGame.title
        holder.descriptionTextView.text = currentGame.description

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, GameDetailActivity::class.java).apply {
                putExtra("TITLE", currentGame.title)
                putExtra("DESCRIPTION", currentGame.description)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = games.size
}
