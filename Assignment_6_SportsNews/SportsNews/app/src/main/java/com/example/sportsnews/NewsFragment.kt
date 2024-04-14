import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsnews.News
import com.example.sportsnews.NewsAdapter
import com.example.sportsnews.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NewsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var fabAddNews: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fabAddNews = view.findViewById(R.id.fabAddNews)
        fabAddNews.setOnClickListener {
            showAddNewsDialog()
        }

        // Example data
        val newsList = mutableListOf<News>(
            News("Man City move top of PL with early goal vs Luton LIVE!", "Follow all the action as title-chasers Man City host Luton; kick-off 3pm. Watch free match highlights from 5.15pm. 1", "https://e0.365dm.com/24/04/768x432/skysports-premier-league-manchester-city_6519983.jpg?20240413150914"),
            News("Brentford vs Sheffield United LIVE! ", "Match commentary as Brentford host Sheffield United in the Premier League; kick-off 3pm. Watch free match highlights from 5.15pm.", "https://e0.365dm.com/24/03/384x216/skysports-ivan-toney-brentford_6506027.jpg?20240330211842"),
            News("Kane hits the post vs Cologne LIVE!", "Follow Bayern Munich's hopes of making Bayer Leverkusen wait for the Bundesliga title against FC Cologne. Watch on Sky Sports Football from 7.30pm. ", "https://e2.365dm.com/24/04/384x216/skysports-football-bundesliga_6519931.jpg?20240413144837")
        )

        newsAdapter = NewsAdapter(newsList)
        recyclerView.adapter = newsAdapter

        return view
    }

    private fun showAddNewsDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_news, null)

        val editTextImageUrl = dialogView.findViewById<EditText>(R.id.editTextImageUrl)
        val editTextTitle = dialogView.findViewById<EditText>(R.id.editTextTitle)
        val editTextDescription = dialogView.findViewById<EditText>(R.id.editTextDescription)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add News")
            .setPositiveButton("Add") { dialog, _ ->
                val imageUrl = editTextImageUrl.text.toString().trim()
                val title = editTextTitle.text.toString().trim()
                val description = editTextDescription.text.toString().trim()

                if (title.isNotEmpty() && description.isNotEmpty()) {
                    val newNews = News(title, description, imageUrl)
                    addNews(newNews)
                }

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun addNews(newNews: News) {
        newsAdapter.addItem(newNews)
    }
}
