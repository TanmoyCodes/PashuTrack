import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pashutrack.R
import com.example.pashutrack.model.Animal

class AnimalGridAdapter(private val context: Context) : RecyclerView.Adapter<AnimalGridAdapter.AnimalViewHolder>() {

    // Hardcoded list of animals
    private val animals = listOf(
        Animal("1", "Cow", R.drawable.animal_placeholder),  // Replace with actual image resources
        Animal("2", "Goat", R.drawable.animal_placeholder),
        Animal("3", "Sheep", R.drawable.animal_placeholder),
        Animal("4", "Horse", R.drawable.animal_placeholder),
        Animal("5", "Pig", R.drawable.animal_placeholder)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animals[position]

        // Set the animal image
        holder.imageAnimal.setImageResource(animal.imageResource)
        holder.textAnimalName.text = animal.name
        holder.textAnimalId.text = "ID: ${animal.id}"
    }

    override fun getItemCount(): Int = animals.size

    inner class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageAnimal: ImageView = itemView.findViewById(R.id.ivAnimal)
        val textAnimalName: TextView = itemView.findViewById(R.id.tvAnimalName)
        val textAnimalId: TextView = itemView.findViewById(R.id.tvAnimalId)
        val btnViewDetails: Button = itemView.findViewById(R.id.btnViewDetails)
    }
}
